package Model.Implements;

import Model.DaoObjects.User;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DaoUser implements DaoInterface<User>
{
    private static String userName = "sa";
    private static String password = "123456";
    private static String databaseName = "dbCanteenSystem";
    private static String Port = "1433";
    private static Connection con;
    private CallableStatement callableStatement;

    public DaoUser()
    {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }

    @Override
    public void Create(User user)
    {
        try (Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call create_user(?, ?,?,?,?,?,?,?,?)}")) {
            stmt.setInt(1, user.getUserId());
            stmt.setString(2, password);


            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
        }
    }

    @Override
    public void Update(User user, String fieldname, String value)
    {

    }

    @Override
    public void Delete(User user, int ID)
    {

    }

    @Override
    public User Get(int ID)
    {
        return null;
    }

    public List<User> GetAll() {
        ArrayList<User> userList = new ArrayList<>();
        try (Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call  dbo.getAllUser()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                // Assuming the User class has appropriate constructor and getters
                User user = new User(
                        rs.getInt("fldUserID"),
                        rs.getString("fldName"),
                        rs.getString("fldPhoneNumber"),
                        rs.getString("fldPassword"),
                        rs.getString("fldAddress"),
                        rs.getInt("fldAcountNumber"),
                        rs.getString("fldEmail"),
                        rs.getInt("fldZipcode"),
                        rs.getInt("fldBlackListID")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return userList;
    }

}

