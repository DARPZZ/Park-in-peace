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
    }


    @Override
    public void Create(User user)
    {
        createConnection();
        try (Connection conn = con)
        {
            int userid=0;
            CallableStatement stmt = conn.prepareCall("{call insertUser(?, ?,?,?,?,?)}");
            stmt.setString(1,user.getName());
            stmt.setString(2, user.getPhoneNumber());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getEmail());
            stmt.setInt(6, user.getZipCode());


            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            userid =resultSet.getInt(1);

            for (Integer i: user.getBlackList()) // could be better with samlet string storedprocedure
            {
                CallableStatement blackListInsert = con.prepareCall("{CALL insertBlacklist(?,?)}");
                blackListInsert.setInt(1,i);
                blackListInsert.setInt(2,userid);
            }
            user.setUserId(userid);


        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void Update( User user, String fieldname, String value)
    {
        createConnection();
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
        createConnection();
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
        createConnection();
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
        createConnection();
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
        createConnection();
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
        createConnection();
        try {
            CallableStatement add = con.prepareCall("{CALL addBlackList(?,?)}");
            add.setInt(1,user.getUserId());
            add.setInt(2,blacklist);

        }catch (Exception e){
            System.out.println(e);
        }

    }
    public ArrayList<Integer> getBlackListedBy(User user)
    {
        createConnection();
        ArrayList<Integer> blacklist = new ArrayList<>();
        try {
            CallableStatement list = con.prepareCall("{CALL getBlackListedBy(?)}");
            list.setInt(1,user.getUserId());
            ResultSet resultSet = list.executeQuery();
            while (resultSet.next())
            {
                blacklist.add(resultSet.getInt(1));
            }
            return blacklist;
        }catch (Exception e ){
            System.out.println(e);
        }
        return null;
    }
    public User checkLogin (String username, String password)
    {
        createConnection();
        System.out.println("checklogin start"+ System.currentTimeMillis());
        try {
            CallableStatement checklogin = con.prepareCall("{CALL userLoginCheck(?,?)}");
            checklogin.setString(1, password);
            checklogin.setString(2, username);
            ResultSet resultSet = checklogin.executeQuery();
            resultSet.next();
            int acNR =0;
            try {
                acNR = resultSet.getInt("fldAcountNumber");
            }catch (Exception e){
                System.out.println("AccountNR null proceed");
            }

            User user = new User
                    (resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),acNR,resultSet.getString(7),resultSet.getInt(8));
            return user;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}

