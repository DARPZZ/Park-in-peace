package Model.Implements;

import Model.DaoObject.User;
import Model.DaoObject.tblZipCode;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoZipcodeCity extends Connection implements DaoInterface<tblZipCode>
{
    @Override
    public void Create(tblZipCode tblZipCode)
    {

    }

    @Override
    public void Update(tblZipCode tblZipCode, String fieldname, String value)
    {

    }

    @Override
    public void Delete(tblZipCode tblZipCode, int ID)
    {
        try {
            java.sql.Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call deletezipCode(?)}");
            stmt.setInt(1, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public tblZipCode Get(int ID)
    {
        try {
            java.sql.Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call getZipcode(?)}");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tblZipCode tblZipCode = new tblZipCode(rs.getInt("fldZipcode"),
                        rs.getString("fldCity"));
                return tblZipCode;
            }
        }catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<tblZipCode> GetAll()
    {
        List<tblZipCode> daoZipcodeCityList = new ArrayList<>();
        try (java.sql.Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call getAllZipcode()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                daoZipcodeCityList.add(new tblZipCode(
                        rs.getInt("fldZipcode"),
                        rs.getString("fldCity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return daoZipcodeCityList;
    }
}

