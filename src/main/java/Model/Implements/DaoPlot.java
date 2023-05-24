package Model.Implements;

import Model.DaoObject.Plot;
import Model.DaoObject.User;
import Model.DaoObject.tblPlot;

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

    }

    @Override
    public void Update(Plot tblPlot, String fieldname, String value)
    {

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
                Plot plot = new Plot( rs.getInt("fldPlotID"),
                        rs.getString("fldLocation"),
                        rs.getString("fldDescription"),
                        rs.getString("fldImage"),
                        rs.getInt("fldPlotSizeID"),
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
    public List<tblPlot> GetAll()
    {
        List<tblPlot> plotList = new ArrayList<>();
        try (java.sql.Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call getAllPlots()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                plotList.add(new tblPlot(
                        rs.getInt("fldPlotID"),
                        rs.getString("fldLocation"),
                        rs.getString("fldDescription"),
                        rs.getString("fldImage"),
                        rs.getInt("fldPlotSizeID"),
                        rs.getInt("fldZipcode")));

            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return plotList;

    }
}
