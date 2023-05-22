package Model.Implements;
/*
import Model.DaoObject.BlackList;
import Model.DaoObject.User;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DaoBlackList implements DaoInterface<BlackList>
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
    public void Create(BlackList blackList)
    {

    }

    @Override
    public void Update(BlackList blackList, String fieldname, String value)
    {

    }

    @Override
    public void Delete(BlackList blackList, int ID)
    {

    }

    @Override
    public BlackList Get(int ID)
    {
        try {Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call getUser(?)}");
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                BlackList blackList = new BlackList(rs.getInt("fldBlackListID"),
                        rs.getBoolean("fldBlackList"));
                return blackList;
            }
        }catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<BlackList> GetAll()
    {
        List<BlackList> blackLists = new ArrayList<>();
        try (Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call getAllBlackList()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                blackLists.add(new BlackList(rs.getInt("fldBlackListID"),
                        rs.getBoolean("fldBlackList")));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return blackLists;
    }

}

 */
