package Model.Implements;

import Model.DaoObject.Plot;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPlot extends Connection implements DaoInterface<Plot>
{
    private int toiletID=1;
    private int waterID=2;
    private int electricID=3;

    public DaoPlot()
    {
        createConnection();
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }

    }
    @Override
    public void Create(Plot tblPlot)
    {
        createConnection();
        try
        {
            int plotid=0;
            CallableStatement createRawPlot = con.prepareCall("{CALL createPlotNoPlotSize(?,?,?,?,?)}");
            createRawPlot.setString(1,tblPlot.getLocation());
            createRawPlot.setString(2,tblPlot.getDescription());
            createRawPlot.setString(3,tblPlot.getImagePath());
            createRawPlot.setInt(4,tblPlot.getZipCode());
            createRawPlot.setInt(5,tblPlot.getUserID());
            ResultSet resultSet = createRawPlot.executeQuery();
            resultSet.next();
            plotid = resultSet.getInt(1);
            System.out.println("rawdone");

            CallableStatement firstPassInsert = con.prepareCall("{CALL insertSeasonServiceSize(?,?,?,?,?,?,?,?)}");
            if(tblPlot.isToilet() == true)
            {firstPassInsert.setInt(1,toiletID);}
            else
            {firstPassInsert.setInt(1,0);}
            if(tblPlot.isElectric()==true)
            {firstPassInsert.setInt(2,electricID);}
            else
            {firstPassInsert.setInt(2,0);}
            if (tblPlot.isWater()==true)
            {firstPassInsert.setInt(3,waterID);}
            else
            {firstPassInsert.setInt(3,0);}
            firstPassInsert.setFloat(4,tblPlot.getLowPrice());
            firstPassInsert.setFloat(5,tblPlot.getMidPrice());
            firstPassInsert.setFloat(6,tblPlot.getHighPrice());
            firstPassInsert.setString(7,tblPlot.getPlotSize());
            firstPassInsert.setInt(8,plotid);
            firstPassInsert.executeUpdate();
            resultSet.next();

            tblPlot.setPlotID(plotid);

            System.out.println("Done done");



        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void Update(Plot tblPlot, String fieldname, String value)
    {
        createConnection();
        switch (fieldname) {
            case "fldLowSeasonPrice","fldMidSeasonPrice","HighSeasonPrice":
            {
                try
                {
                 CallableStatement updatePricing = con.prepareCall("{CALL updatePlotPricing(?,?,?)}");
                 updatePricing.setString(1,fieldname);
                 updatePricing.setFloat(2,Float.parseFloat(value));
                 updatePricing.setInt(3,tblPlot.getPlotID());
                 updatePricing.executeUpdate();

                }catch (Exception e){
                    System.out.println(e);
                }
            }
            case "fldToilet":
            {
                int trueValue = Integer.parseInt(value);
                updateServices(toiletID, tblPlot.getPlotID(),1);

            }
            case "fldWater":
            {
                int trueValue = Integer.parseInt(value);
                updateServices(toiletID, tblPlot.getPlotID(),2);

            }
            case "fldElectric":
            {
                int trueValue = Integer.parseInt(value);
                updateServices(toiletID, tblPlot.getPlotID(),3);

            }
            case "fldLocation","fldDescription","fldImage","fldZipCode":
            {
                try
                {
                    CallableStatement updateString = con.prepareCall("{CALL updatePlotStrings(?,?,?)}");
                    updateString.setString(1,fieldname);
                    updateString.setString(2,value);
                    updateString.setInt(3,tblPlot.getPlotID());
                    updateString.executeUpdate();
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
            case "fldUserID", "fldPlotID":
            {
                try {
                    CallableStatement updateInt = con.prepareCall("{CALL updatePlotIntegers(?,?,?)}");
                    updateInt.setString(1,fieldname);
                    updateInt.setInt(2,Integer.parseInt(value));
                    updateInt.setInt(3,tblPlot.getPlotID());
                    updateInt.executeUpdate();

                }catch (Exception e){
                    System.out.println(e);
                }

            }
            case "fldPlotSize":
            {
                try
                {
                CallableStatement getPlotSizeID = con.prepareCall("{CALL getPlotSizeIDFromPlotID(?)}");
                getPlotSizeID.setInt(1, tblPlot.getPlotID());
                ResultSet resultSet = getPlotSizeID.executeQuery();
                resultSet.next();
                CallableStatement updatePlotSize = con.prepareCall("CALL updatePlotSize(?,?)");

                updatePlotSize.setInt(2, resultSet.getInt("fldPlotSizeID"));
                updatePlotSize.executeUpdate();

            } catch (Exception e)
            {
                System.out.println(e);
            }
                break;}
        }
    }

    @Override
    public void Delete(Plot tblPlot, int ID) // member to set ON DELETE CASCADE i DB CREATE SCRIPT
    {
        createConnection();
        try {
            java.sql.Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call deletePlot(?)}");
            stmt.setInt(1, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Plot Get(int ID)
    {
        createConnection();
        try {
            CallableStatement stmt = con.prepareCall("{call getPlot(?)}");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int plotid = rs.getInt(1);
            CallableStatement servicesCS = con.prepareCall("{call getPlotServices(?)}");
            servicesCS.setInt(1,plotid);
            ResultSet servicesRS = servicesCS.executeQuery();
            boolean[] services = new boolean[3];
            for (int i = 0; i <3 ; i++)
            {
                if (servicesRS.next())
                services[i] = true;
            }
            CallableStatement price = con.prepareCall("{call getPlotPrices(?)}");
            price.setInt(1,plotid);
            ResultSet priceRS = price.executeQuery();

            float[] prices = new float[3];
            for (int i = 0; i <3 ; i++)
            {
                prices[i] = priceRS.getFloat(i+1);
            }
                Plot plot = new Plot(
                        rs.getInt("fldUserID"),
                        rs.getInt("fldPlotID"),
                        rs.getString("fldLocation"),
                        rs.getString("fldDescription"),
                        rs.getString("fldImage"),
                        rs.getString("fldPlotSize"),
                        rs.getInt("fldZipcode"),
                        services[0],services[1],services[2],
                        prices[0],prices[1],prices[2]);
                return plot;

        }catch (Exception e) {

        }
        return null;
    }

    @Override
    public ArrayList<Plot> GetAll()
    {
        createConnection();
        ArrayList<Integer> plotIDs =new ArrayList<>();
        ArrayList<Plot> plotList = new ArrayList<Plot>();
        boolean[] services = new boolean[3];
        float[] prices = new float[3];
        System.out.println("mem");
        try
             {
                 CallableStatement stmt = con.prepareCall("{call getAllPlots()}");
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next())
                 {
                    plotIDs.add(rs.getInt(1)) ;
                 }
                 System.out.println("me2");
                 for (Integer j:plotIDs)
                 {
                     CallableStatement servicesCS = con.prepareCall("{call getPlotServices(?)}");
                     servicesCS.setInt(1,j);
                     ResultSet servicesRS = servicesCS.executeQuery();
                     for (int i = 0; i <3 ; i++)
                     {
                         if (servicesRS.next())
                             services[i] = true;
                     }
                     CallableStatement price = con.prepareCall("{call getPlotPrices(?)}");
                     price.setInt(1,j);
                     ResultSet priceRS = price.executeQuery();

                     for (int i = 0; i <3 ; i++)
                     {
                         prices[i] = priceRS.getFloat(i+1);
                     }
                plotList.add(new Plot(
                        rs.getInt("fldUserID"),
                        rs.getInt("fldPlotID"),
                        rs.getString("fldLocation"),
                        rs.getString("fldDescription"),
                        rs.getString("fldImage"),
                        rs.getString("fldPlotSize"),
                        rs.getInt("fldZipcode"),
                        services[0],services[1],services[2],
                        prices[0],prices[1],prices[2]));
                 }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return plotList;

    }

    private void updateServices(int ServiceID,int PlotID, int key)
    {
        if (key ==1)
        {
            try {

                CallableStatement updateServices = con.prepareCall("{CALL insertServicePlot(?,?)}");
                updateServices.setInt(1,PlotID);
                updateServices.setInt(2,ServiceID);
                updateServices.executeUpdate();

            }
            catch (Exception e){
                System.out.println(e);
            }

        }
        else if (key ==0)
        {
            try {
                CallableStatement updateServices = con.prepareCall("{CALL deleteServicePlot(?,?)}");
                updateServices.setInt(1, PlotID);
                updateServices.setInt(2,ServiceID);
                updateServices.executeUpdate();
            }catch (Exception e ){
                System.out.println(e);
            }

        }
        else {
            System.out.println("VALUE WRONG:"+ServiceID+"Should be 1 for true, 0 for false");
        }
    }
}
