package Model.Implements;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class Connection
{
    public static String userName = "sa";
    public static String password = "123456";
    public static String databaseName = "dbParkInPeace";
    public static String Port = "1433";
    public static java.sql.Connection con;
    public CallableStatement callableStatement;
    private boolean conClosed;
    void createConnection() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);
            } catch (SQLException e) {
                System.err.println("Database connection fail" + e.getMessage());
            }
        }
    }


