package Db.DbConnect;

import java.sql.*;

public class DbConnect {
    private Connection connection = null;
    private static DbConnect instance = null;

    private static final String DB_USER = "yourDbUser";
    private static final String DB_PASS = "yourDbPass";       //Añadir contraseña en caso de ser necesario
    private static final String DB_URL = "yourDbURL";
    //Cambiar URL antes de ejecutar el programa

    private DbConnect(){}

    public static DbConnect instance(){
        if(instance == null){
            instance = new DbConnect();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        try {
            //Verificar que el driver se encuentre en las librerias externas antes de ejecutar el programa
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
