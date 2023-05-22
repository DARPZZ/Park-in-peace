package Model.Implements;

import Model.DaoObject.Plot;
import Model.DaoObject.User;
import Model.DaoObject.tblPlot;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPlot extends Connection implements DaoInterface<tblPlot>
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
    public void Create(tblPlot tblPlot)
    {

    }

    @Override
    public void Update(tblPlot tblPlot, String fieldname, String value)
    {

    }

    @Override
    public void Delete(tblPlot tblPlot, int ID)
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
    public tblPlot Get(int ID)
    {
        try {
            java.sql.Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call getPlot(?)}");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tblPlot tblPlot = new tblPlot( rs.getInt("fldPlotID"),
                        rs.getString("fldLocation"),
                        rs.getString("fldDescription"),
                        rs.getString("fldImage"),
                        rs.getInt("fldPlotSizeID"),
                        rs.getInt("fldZipcode"));
                return tblPlot;
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
