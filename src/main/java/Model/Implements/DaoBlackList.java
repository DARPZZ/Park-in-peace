package Model.Implements;

import Model.DaoObject.tblBlackList;
import Model.DaoObject.User;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DaoBlackList implements DaoInterface<tblBlackList>
{
    private static String userName = "sa";
    private static String password = "123456";
    private static String databaseName = "dbParkInPeace";
    private static String Port = "1433";
    private static Connection con;
    private CallableStatement callableStatement;
    public DaoBlackList()
    {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }


    @Override
    public void Create(tblBlackList blackList)
    {

    }

    @Override
    public void Update(tblBlackList blackList, String fieldname, String value)
    {

    }

    @Override
    public void Delete(tblBlackList blackList, int ID)
    {

    }

    @Override
    public tblBlackList Get(int ID)
    {
        try {Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call getUser(?)}");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tblBlackList blackList = new tblBlackList(rs.getInt("fldBlackListID"),
                        rs.getBoolean("fldBlackList"));
                return blackList;
            }
        }catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<tblBlackList> GetAll()
    {
        List<tblBlackList> blackLists = new ArrayList<>();
        try (Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call getAllBlackList()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                blackLists.add(new tblBlackList(rs.getInt("fldBlackListID"),
                        rs.getBoolean("fldBlackList")));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return blackLists;
    }

}


