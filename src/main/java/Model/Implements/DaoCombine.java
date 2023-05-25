package Model.Implements;

import Model.DaoObject.Combine;
import Model.DaoObject.User;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCombine extends Connection implements DaoInterface<Combine>
{
    public DaoCombine()
    {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }

    @Override
    public void Create(Combine combine)
    {

    }

    @Override
    public void Update(Combine combine, String fieldname, String value)
    {
        
    }

    @Override
    public void Delete(Combine combine, int ID)
    {

    }

    @Override
    public Combine Get(int ID)
    {
        return null;
    }

    @Override
    public List<Combine> GetAll()
    {
        List<Combine> combineList = new ArrayList<>();
        try (
             CallableStatement stmt = con.prepareCall("{call combine()}")) {
            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                combineList.add(new Combine(
                        rs.getInt("fldUserID"),
                        rs.getInt("fldPlotID"),
                        rs.getInt("fldreservationID"),
                        rs.getString("fldLocation"),
                        rs.getString("fldDescription"),
                        rs.getString("fldPlotSize"),
                        rs.getInt("fldZipcode"),
                        rs.getString("fldImage"),
                        rs.getBoolean("fldToilet"),
                        rs.getBoolean("fldElectric"),
                        rs.getBoolean("fldWater"),
                        rs.getDate("fldStartDate"),
                        rs.getDate("fldEndDate"),
                        rs.getString("fldSeasonName"),
                        rs.getFloat("fldLowSeasonPrice"),
                        rs.getFloat("fldMediumSeasonPrice"),
                        rs.getFloat("fldHighSeasonPrice")));
            }
        } catch (SQLException e) {
            System.out.println(e);
            // Handle the exception appropriately
        }
        return combineList;
    }
}



