package Model.Implements;

import Model.DaoObject.Resevations;
import Model.DaoObject.User;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DaoResevations extends Model.Implements.Connection implements DaoInterface<Resevations>
{
    /*
    private static String userName = "sa";
    private static String password = "123456";
    private static String databaseName = "dbParkInPeace";
    private static String Port = "1433";
    private static Connection con;
    private CallableStatement callableStatement;

     */
    public DaoResevations()
    {
        createConnection();
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }



    @Override
    public void Create(Resevations resevations)
    {
        createConnection();
        try {
             CallableStatement stmt = con.prepareCall("{call insertResevation(?,?,?,?)}");
            stmt.setString(1, String.valueOf(resevations.getStartDate()));
            stmt.setString(2, String.valueOf(resevations.getEndDate()));
            stmt.setInt(3, resevations.getUserID());
            stmt.setInt(4,resevations.getPlotID());
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            resevations.setReservationID(resultSet.getInt(1));
        } catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void Update(Resevations resevations, String fieldname, String value)
    {
        createConnection();
        try{Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call updateResevations(?,?,?)}");
            stmt.setInt(1,resevations.getReservationID());
            stmt.setString(2, fieldname);
            stmt.setString(3, value);
            stmt.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);}
    }

    @Override
    public void Delete(Resevations resevations, int ID)
    {
        createConnection();
        try {Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call delteResevations(?)}");
            stmt.setInt(1, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Resevations Get(int ID)
    {
        createConnection();
            try {Connection conn = con;
                CallableStatement stmt = conn.prepareCall("{call getresevastion(?)}");
                stmt.setInt(1, ID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Resevations resevations = new Resevations(rs.getInt("fldreservationID"),
                            rs.getDate("fldStartDate").toLocalDate(),
                            rs.getDate("fldEndDate").toLocalDate(),
                            rs.getInt("flduserID"),
                            rs.getInt("fldPlotID"));
                    return resevations;
                }
            }catch (Exception e) {

            }
            return null;
        }

    @Override
    public List<Resevations> GetAll()
    {
        createConnection();
        List<Resevations> resevationsList = new ArrayList<>();
        try (
             CallableStatement stmt = con.prepareCall("{call getAllResevations()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                resevationsList.add(new Resevations(
                        rs.getInt("fldreservationID"),
                        rs.getDate("fldStartDate").toLocalDate(),
                        rs.getDate("fldEndDate").toLocalDate(),
                        rs.getInt("flduserID"),
                        rs.getInt("fldPlotID")));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return resevationsList;
    }
}
