package Model.Implements;

import Model.DaoObject.Plot;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPlot extends Connection implements DaoInterface<Plot>
{
    public DaoPlot()
    {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }
    @Override
    public void Create(Plot tblPlot)
    {
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
            createRawPlot.close();
            plotid = resultSet.getInt(1);
            System.out.println("rawdone");

            CallableStatement firstPassInsert = con.prepareCall("{CALL insertSeasonServiceSizeFirstPass(?,?,?,?,?,?,?,?)}");
            firstPassInsert.setBoolean(1,tblPlot.isToilet());
            firstPassInsert.setBoolean(2,tblPlot.isElectric());
            firstPassInsert.setBoolean(3,tblPlot.isWater());
            firstPassInsert.setFloat(4,tblPlot.getLowPrice());
            firstPassInsert.setFloat(5,tblPlot.getMidPrice());
            firstPassInsert.setFloat(6,tblPlot.getHighPrice());
            firstPassInsert.setString(7,tblPlot.getPlotSize());
            resultSet = firstPassInsert.executeQuery();
            firstPassInsert.close();
            resultSet.next();

            System.out.println("firstpass done");

            CallableStatement lastInsert = con.prepareCall("{CALL insertPlotLastPass (?,?,?,?)}");
            lastInsert.setInt(1,plotid);
            lastInsert.setInt(2,resultSet.getInt(1));
            lastInsert.setInt(3,resultSet.getInt(2));
            lastInsert.setInt(4,resultSet.getInt(3));
            lastInsert.executeUpdate();
            lastInsert.close();
            resultSet.close();

            System.out.println("Done done");



        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void Update(Plot tblPlot, String fieldname, String value)
    {
        switch (fieldname) {
            case "fldLowSeasonPrice","fldMidSeasonPrice","HighSeasonPrice":
            {
                try
                {
                 CallableStatement getSeasonID= con.prepareCall("{CALL getPlotSeasonID(?)}");
                 getSeasonID.setInt(1,tblPlot.getPlotID());
                 ResultSet resultSet = getSeasonID.executeQuery();
                 resultSet.next();
                 getSeasonID.close();
                 CallableStatement updatePricing = con.prepareCall("{CALL updatePlotPricing(?,?,?)}");
                 updatePricing.setString(1,fieldname);
                 updatePricing.setFloat(2,Float.parseFloat(value));
                 updatePricing.setInt(3,tblPlot.getPlotID());
                 updatePricing.executeUpdate();
                 updatePricing.close();

                }catch (Exception e){
                    System.out.println(e);
                }
            }
            case "fldToilet","fldWater","fldElectric":
            {
                int trueValue = Integer.parseInt(value);
                if (trueValue <=1 && trueValue >0)
                {
                    try {
                        CallableStatement idGet = con.prepareCall("{CALL getPlotServiceID(?)}");
                        idGet.setInt(1,tblPlot.getPlotID());
                        ResultSet resultSet = idGet.executeQuery();
                        resultSet.next();
                        idGet.close();
                        CallableStatement updateServices = con.prepareCall("{CALL updatePlotServices(?,?,?)}");
                        updateServices.setString(1,fieldname);
                        updateServices.setString(2,String.valueOf(trueValue));
                        updateServices.setInt(3,resultSet.getInt(1));
                        updateServices.executeUpdate();
                        updateServices.close();

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }

                }
                else {
                    System.out.println("VALUE WRONG:"+value+"Should be 1 for true, 0 for false");
                }
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
                    updateString.close();
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
                    updateInt.close();

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
                getPlotSizeID.close();
                CallableStatement updatePlotSize = con.prepareCall("CALL updatePlotSize(?,?)");

                updatePlotSize.setInt(2, resultSet.getInt("fldPlotSizeID"));
                updatePlotSize.executeUpdate();
                resultSet.close();
                updatePlotSize.close();

            } catch (Exception e)
            {
                System.out.println(e);
            }
                break;}
        }
    }

    @Override
    public void Delete(Plot tblPlot, int ID)
    {
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
        try {
            java.sql.Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call getPlot(?)}");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            CallableStatement servicesPrice = conn.prepareCall("{call getPlotPricingServices(?)}");
            ResultSet servicesPriceRS = servicesPrice.executeQuery();
            servicesPriceRS.next();
            boolean[] services = new boolean[3];
            for (int i = 0; i <3 ; i++)
            {
                services[i] = servicesPriceRS.getBoolean(i);
            }
            float[] prices = new float[3];
            for (int i = 0; i <3 ; i++)
            {
                prices[i+3] = servicesPriceRS.getFloat(i);
            }
            if (rs.next()) {
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
            }
        }catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<Plot> GetAll()
    {
        List<Plot> plotList = new ArrayList<>();
        try (java.sql.Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call getAllPlots()}"))
        {
            CallableStatement servicesPrice = conn.prepareCall("{call getAllPlotPricingServices(?)}");
            ResultSet servicesPriceRS = servicesPrice.executeQuery();
            servicesPriceRS.next();
            boolean[] services = new boolean[3];
            for (int i = 0; i <3 ; i++)
            {
                services[i] = servicesPriceRS.getBoolean(i);
            }
            float[] prices = new float[3];
            for (int i = 0; i <3 ; i++)
            {
                prices[i+3] = servicesPriceRS.getFloat(i);
            }

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next())
            {
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
}
