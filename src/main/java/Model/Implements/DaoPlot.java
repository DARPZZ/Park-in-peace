package Model.Implements;

import Model.DaoObject.Plot;
import Model.DatabaseWorker.PlotList;

import java.sql.*;
import java.util.ArrayList;

public class DaoPlot extends Connection implements DaoInterface<Plot>
{
    private int toiletID=2;
    private int waterID=3;
    private int electricID=4;

    boolean toiletBool = false;
    boolean waterBool= false;
    boolean elBool= false;
    ArrayList<String>plotsizes =new ArrayList<>();

    public DaoPlot()
    {
        createConnection();
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
        plotsizes = getAllSizeTypes();
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


            CallableStatement firstPassInsert = con.prepareCall("{CALL insertSeasonServiceSize(?,?,?,?,?,?,?,?,?)}");
            if(tblPlot.isToilet())
            {firstPassInsert.setInt(1,toiletID);}
            else
            {firstPassInsert.setInt(1,0);}
            if(tblPlot.isElectric())
            {firstPassInsert.setInt(2,electricID);}
            else
            {firstPassInsert.setInt(2,0);}
            if (tblPlot.isWater())
            {firstPassInsert.setInt(3,waterID);}
            else
            {firstPassInsert.setInt(3,0);}
            firstPassInsert.setFloat(4,tblPlot.getLowPrice());
            firstPassInsert.setFloat(5,tblPlot.getMidPrice());
            firstPassInsert.setFloat(6,tblPlot.getHighPrice());
            firstPassInsert.setString(7,tblPlot.getPlotSize());
            firstPassInsert.setInt(8,plotid);
            firstPassInsert.setInt(9,tblPlot.getZipCode());
            firstPassInsert.executeUpdate();
            resultSet.next();

            tblPlot.setPlotID(plotid);
            PlotList.getSingleton().addToList(tblPlot);
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
            case "fldLowSeasonPrice","fldMidSeasonPrice","fldHighSeasonPrice":
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
                updateServices(toiletID, tblPlot.getPlotID(),2);

            }
            case "fldWater":
            {
                int trueValue = Integer.parseInt(value);
                updateServices(toiletID, tblPlot.getPlotID(),3);

            }
            case "fldElectric":
            {
                int trueValue = Integer.parseInt(value);
                updateServices(toiletID, tblPlot.getPlotID(),4);

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

    public void UpdateFull(Plot plot)
    {
        int sizeI=0;
        for (String s:plotsizes)
        {
            if (plot.getPlotSize().equals(s)) {
                sizeI++;
                break;
            }

            sizeI++;
        }
        createConnection();
        System.out.println(sizeI);
        System.out.println(plot.getZipCode());
        try {
            CallableStatement fullUpdate = con.prepareCall("{CALL updatePlotFuLL(?,?,?,?,?,?,?,?,?,?,?,?)}");
            if (plot.isToilet() == true)
                fullUpdate.setInt(1,toiletID);
            else
                fullUpdate.setInt(1,1);
            if (plot.isWater() == true)
                fullUpdate.setInt(2,waterID);
            else
                fullUpdate.setInt(2,1);
            if (plot.isElectric() == true)
                fullUpdate.setInt(3,electricID);
            else
                fullUpdate.setInt(3,1);

            fullUpdate.setFloat(4,plot.getLowPrice());
            fullUpdate.setFloat(5,plot.getMidPrice());
            fullUpdate.setFloat(6,plot.getHighPrice());
            fullUpdate.setInt(7,sizeI);
            fullUpdate.setInt(8,plot.getPlotID());
            fullUpdate.setInt(9,plot.getZipCode());
            fullUpdate.setString(10,plot.getLocation());
            fullUpdate.setString(11,plot.getDescription());
            fullUpdate.setString(12,plot.getImagePath());
            fullUpdate.executeUpdate();


        }catch (Exception e){
            System.out.println(e);
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

        System.out.println("mem"+System.currentTimeMillis() );
        createConnection();
        ArrayList<Integer> plotIDs =new ArrayList<>();
        ArrayList<Plot> plotList = new ArrayList<Plot>();

        try
             {
                 CallableStatement stmt = con.prepareCall("{call getAllPlots()}");
                 ResultSet resultSetPlots = stmt.executeQuery();
                 //resultSetPlots.next();
                 while(resultSetPlots.next())
                 {
                     toiletBool = false;
                     waterBool = false;
                     elBool = false;
                     convertIDtoBool(resultSetPlots.getInt(1));
                     resultSetPlots.next();
                     convertIDtoBool(resultSetPlots.getInt(1));
                     resultSetPlots.next();
                     convertIDtoBool(resultSetPlots.getInt(1));

                     plotList.add(new Plot(
                             resultSetPlots.getInt("fldUserID"),
                             resultSetPlots.getInt("fldPlotID"),
                             resultSetPlots.getString("fldLocation"),
                             resultSetPlots.getString("fldDescription"),
                             resultSetPlots.getString("fldImage"),
                             resultSetPlots.getString("fldPlotSize"),
                             resultSetPlots.getInt("fldZipcode"),
                             toiletBool,waterBool,elBool,
                             resultSetPlots.getFloat("fldLowSeasonPrice"),
                             resultSetPlots.getFloat("fldMediumSeasonPrice"),
                             resultSetPlots.getFloat("fldHighSeasonPrice")));

                 }
             }
         catch (SQLException e) {
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
    private void convertIDtoBool(int id)
    {
        switch (id) {

            case 2: {
                toiletBool = true;
                break;
            }
            case 3: {
                waterBool = true;
                break;
            }
            case 4: {
                elBool = true;
                break;
            }

        }

    }
    public ArrayList<String> getAllSizeTypes()
    {
        createConnection();
        ArrayList<String> sizeTypes = new ArrayList<>();
        try
        {
            CallableStatement sizeTypesCall = con.prepareCall("{CALL getAllSizeTypes}");
            ResultSet sizeTypesReturn = sizeTypesCall.executeQuery();
            while (sizeTypesReturn.next())
            {
                sizeTypes.add(sizeTypesReturn.getString(1));
            }
            return sizeTypes;
        }catch (Exception e ){
            System.out.println(e);
        }
        System.out.println("Result set issue");
        return null;
    }
}
