package Model.Implements;

import Model.DaoObject.Reservations;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DaoReservations extends Model.Implements.Connection implements DaoInterface<Reservations>
{
    /*
    private static String userName = "sa";
    private static String password = "123456";
    private static String databaseName = "dbParkInPeace";
    private static String Port = "1433";
    private static Connection con;
    private CallableStatement callableStatement;

     */
    public DaoReservations()
    {
        createConnection();
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }



    @Override
    public void Create(Reservations reservations)
    {
        createConnection();
        try {
             CallableStatement stmt = con.prepareCall("{call insertResevation(?,?,?,?)}");
            stmt.setString(1, String.valueOf(reservations.getStartDate()));
            stmt.setString(2, String.valueOf(reservations.getEndDate()));
            stmt.setInt(3, reservations.getUserID());
            stmt.setInt(4, reservations.getPlotID());
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            reservations.setReservationID(resultSet.getInt(1));
        } catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void Update(Reservations reservations, String fieldname, String value)
    {
        createConnection();
        try{Connection conn = con;
            CallableStatement stmt = conn.prepareCall("{call updateResevations(?,?,?)}");
            stmt.setInt(1, reservations.getReservationID());
            stmt.setString(2, fieldname);
            stmt.setString(3, value);
            stmt.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);}
    }

    @Override
    public void Delete(Reservations reservations, int ID)
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
    public Reservations Get(int ID)
    {
        createConnection();
            try {Connection conn = con;
                CallableStatement stmt = conn.prepareCall("{call getresevastion(?)}");
                stmt.setInt(1, ID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Reservations reservations = new Reservations(rs.getInt("fldreservationID"),
                            rs.getDate("fldStartDate").toLocalDate(),
                            rs.getDate("fldEndDate").toLocalDate(),
                            rs.getInt("flduserID"),
                            rs.getInt("fldPlotID"));
                    return reservations;
                }
            }catch (Exception e) {

            }
            return null;
        }

    @Override
    public List<Reservations> GetAll()
    {
        createConnection();
        List<Reservations> reservationsList = new ArrayList<>();
        try (
             CallableStatement stmt = con.prepareCall("{call getAllResevations()}")) {

            // Execute the stored procedure
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                reservationsList.add(new Reservations(
                        rs.getInt("fldreservationID"),
                        rs.getDate("fldStartDate").toLocalDate(),
                        rs.getDate("fldEndDate").toLocalDate(),
                        rs.getInt("flduserID"),
                        rs.getInt("fldPlotID")));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return reservationsList;
    }
}
