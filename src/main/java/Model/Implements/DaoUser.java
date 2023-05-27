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
        try (Connection conn = con)
        {
            int userid=0;
            CallableStatement stmt = conn.prepareCall("{call insertUser(?, ?,?,?,?,?,?)}");
            stmt.setString(1,user.getName());
            stmt.setString(2, user.getPhoneNumber());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getAddress());
            stmt.setInt(5, user.getAcounterNumber());
            stmt.setString(6, user.getEmail());
            stmt.setInt(7, user.getZipCode());


            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            userid =resultSet.getInt(1);
            for (Integer i: user.getBlackList()) // could be better with samlet string storedprocedure
            {
                CallableStatement blackListInsert = con.prepareCall("{CALL insertBlacklist(?,?)}");
                blackListInsert.setInt(1,i);
                blackListInsert.setInt(2,userid);
                blackListInsert.close();
            }
            user.setUserId(userid);


        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void Update( User user, String fieldname, String value)
    {
        try
        {Connection conn = con;
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
            // Process the result set
            int currentID =0;
            while (rs.next()) {
                currentID = rs.getInt("fldUserID");
                ArrayList<Integer> blacklist = new ArrayList<>();
                CallableStatement getBlacklist = conn.prepareCall(" {call getBlacklist(?)}");
                getBlacklist.setInt(1,currentID);
                ResultSet getblackListResult = getBlacklist.executeQuery();
                while (getblackListResult.next())
                {
                    blacklist.add(getblackListResult.getInt("fldBlackList"));
                }


                userList.add(new User( currentID,
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

    public void removeBlackList(User user, int blacklist)
    {
        try {
            CallableStatement remove = con.prepareCall("{CALL removeBlackList(?,?)}");
            remove.setInt(1,user.getUserId());
            remove.setInt(2,blacklist);

        }catch (Exception e){
            System.out.println(e);
        }


    }
    public void addBlackList(User user, int blacklist)
    {
        try {
            CallableStatement add = con.prepareCall("{CALL addBlackList(?,?)}");
            add.setInt(1,user.getUserId());
            add.setInt(2,blacklist);

        }catch (Exception e){
            System.out.println(e);
        }

    }

}

