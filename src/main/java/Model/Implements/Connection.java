package Model.Implements;

import java.sql.CallableStatement;

abstract public class Connection
{
    public static String userName = "sa";
    public static String password = "123456";
    public static String databaseName = "dbParkInPeace";
    public static String Port = "1433";
    public static java.sql.Connection con;
    public CallableStatement callableStatement;

}
