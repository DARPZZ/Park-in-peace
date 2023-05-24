package Model.Implements;

import Model.DaoObject.PlotOwner;
import Model.DaoObject.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoOwner extends Connection implements DaoInterface<PlotOwner>
{
    public DaoOwner()
    {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }

    @Override
    public void Create(PlotOwner plotOwner)
    {

    }

    @Override
    public void Update(PlotOwner plotOwner, String fieldname, String value)
    {

    }

    @Override
    public void Delete(PlotOwner plotOwner, int ID)
    {

    }

    @Override
    public PlotOwner Get(int ID)
    {

        return null;
    }

    @Override
    public List<PlotOwner> GetAll()
    {
        List<PlotOwner> plotOwnerList = new ArrayList<>();
        try (java.sql.Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call getOwner()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                plotOwnerList.add(new PlotOwner(rs.getInt("fldPlotID"),
                        rs.getString("fldLocation"),
                        rs.getInt("fldZipcode"),
                        rs.getInt("fldUserID"),
                        rs.getDate("fldStartDate"),
                        rs.getDate("fldEndDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return plotOwnerList;
    }

}

