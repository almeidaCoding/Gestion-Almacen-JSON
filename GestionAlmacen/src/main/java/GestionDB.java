import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionDB {

    static Connection connection;

    private static void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s/%s", SchemeDB.HOST, SchemeDB.NAME_BD);
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){

        if(connection == null){
            createConnection();
        }
        return connection;
    }

}
