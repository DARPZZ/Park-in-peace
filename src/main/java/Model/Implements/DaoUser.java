package Model.Implements;

import Model.DaoObject.User;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DaoUser extends Model.Implements.Connection implements DaoInterface<User>
{
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
             CallableStatement stmt = conn.prepareCall("{call insertUser(?, ?,?,?,?,?,?,?)}")) {
            stmt.setString(1,user.getName());
            stmt.setString(2, user.getPhoneNumber());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getAddress());
            stmt.setInt(5, user.getAcounterNumber());
            stmt.setString(6, user.getEmail());
            stmt.setInt(7, user.getZipCode());
            stmt.setInt(8, user.getBlackListId());

            stmt.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void Update( User user, String fieldname, String value)
    {
        try{Connection conn = con;
        CallableStatement stmt = conn.prepareCall("{call updateUser(?,?,?)}");
        stmt.setInt(1,user.getUserId());
        stmt.setString(2, fieldname);
        stmt.setString(3, value);
            stmt.executeUpdate();

    }catch (SQLException e) {
            System.out.println(e);}
    }

    @Override
    public void Delete(User user, int ID)
    {
        try {Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call deleteUser(?)}");
            stmt.setInt(1, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public User Get(int ID)
    {
        try {Connection conn = con;
            ArrayList<Integer> blacklist = new ArrayList<>();
            CallableStatement stmt = conn.prepareCall("{call getUser(?)}");
            stmt.setInt(1, ID);
            ResultSet resultSetUser = stmt.executeQuery();
            CallableStatement csBlacklist = conn.prepareCall(" {call getBlacklist(?)}");
            csBlacklist.setInt(1,ID);
            ResultSet blackListResult = csBlacklist.executeQuery();
            while (blackListResult.next())
            {
                blacklist.add(blackListResult.getInt("fldBlackList"));
            }

            if (resultSetUser.next()) {
           User user = new User(resultSetUser.getInt("fldUserID"),
                        resultSetUser.getString("fldName"),
                        resultSetUser.getString("fldPhoneNumber"),
                        resultSetUser.getString("fldPassword"),
                        resultSetUser.getString("fldAddress"),
                        resultSetUser.getInt("fldAcountNumber"),
                        resultSetUser.getString("fldEmail"),
                        resultSetUser.getInt("fldZipcode"),
                        blacklist);

                return user;
            }
        }catch (Exception e) {

        }
        return null;
    }



    public List<User> GetAll() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = con;
             CallableStatement stmt = conn.prepareCall("{call getAllUser()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();
            ArrayList<Integer> blacklist = new ArrayList<>();
            CallableStatement csBlacklist = conn.prepareCall(" {call getAllBlackList()}");
            ResultSet blackListResult = csBlacklist.executeQuery();
            while (blackListResult.next())
            {
                blacklist.add(blackListResult.getInt("fldBlackList"));
            }


            // Process the result set
            while (rs.next()) {
                userList.add(new User( rs.getInt("fldUserID"),
                        rs.getString("fldName"),
                        rs.getString("fldPhoneNumber"),
                        rs.getString("fldPassword"),
                        rs.getString("fldAddress"),
                        rs.getInt("fldAcountNumber"),
                        rs.getString("fldEmail"),
                        rs.getInt("fldZipcode"),
                        blacklist));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return userList;
    }

}

