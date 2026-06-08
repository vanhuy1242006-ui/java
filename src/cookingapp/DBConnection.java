package cookingapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {

            String url =
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=CookingApp;"
            + "encrypt=true;"
            + "trustServerCertificate=true";

            String user = "sa";
            String password = "1";
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url,user,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}