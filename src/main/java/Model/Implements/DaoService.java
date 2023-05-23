package Model.Implements;

import Model.DaoObject.User;
import Model.DaoObject.tblService;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoService extends Connection implements DaoInterface<tblService>
{
    public DaoService()
    {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }
    @Override
    public void Create(tblService tblService)
    {

    }

    @Override
    public void Update(tblService tblService, String fieldname, String value)
    {

    }

    @Override
    public void Delete(tblService tblService, int ID)
    {
        try {
            java.sql.Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call delteService(?)}");
            stmt.setInt(1, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public tblService Get(int ID)
    {
        try {
            java.sql.Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call getService(?)}");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tblService tblService = new tblService(rs.getInt("fldServiceID"),
                        rs.getBoolean("fldToilet"),
                        rs.getBoolean("fldElectric"),
                        rs.getBoolean("fldWater"));
                return tblService;
            }
        }catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<tblService> GetAll()
    {
        List<tblService> tblServiceList = new ArrayList<>();
        try (java.sql.Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call getAllServices()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                tblServiceList.add(new tblService(rs.getInt("fldServiceID"),
                        rs.getBoolean("fldToilet"),
                        rs.getBoolean("fldElectric"),
                        rs.getBoolean("fldWater")));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return tblServiceList;
    }
}
