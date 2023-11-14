package DbConnect;

import java.sql.*;

public class DbConnect {
    private Connection connection = null;
    private static DbConnect instance = null;

    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "hola1213";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/DB_Facultad";

    private DbConnect(){}

    public static DbConnect instance(){
        if(instance == null){
            instance = new DbConnect();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        } catch (Exception e) {
            System.err.print(e.getMessage() + "");
        }
        return null;
    }
}