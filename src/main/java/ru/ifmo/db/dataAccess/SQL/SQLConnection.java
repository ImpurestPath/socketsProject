package ru.ifmo.db.dataAccess.SQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
    public SQLConnection() {
        String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=OnlineCinema;user=user;password=user";
        try {
            // Load SQL Server JDBC driver and establish connection.
            //System.out.print("Connecting to SQL Server ... ");
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Connection connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }
}