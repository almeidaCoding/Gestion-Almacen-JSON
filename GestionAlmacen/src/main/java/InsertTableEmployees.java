import com.mysql.cj.xdevapi.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTableEmployees {
    static String nombre ="";
    static String apellido1 ="";
    static String apellido2 ="";
    static String correo ="";

    public static void getJsonFromEmployees(){
        try {
            URL url = new URL("https://api.generadordni.es/v2/profiles/person");
            HttpURLConnection connectEmployees = (HttpURLConnection) url.openConnection();
            BufferedReader readEmployees = new BufferedReader(new InputStreamReader(connectEmployees.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = readEmployees.readLine()) != null){
                stringBuffer.append(line);
            }

            JSONArray json = new JSONArray(stringBuffer.toString());
            for(int i = 0; i < json.length(); i++){
                JSONObject employe = json.getJSONObject(i);
                nombre = employe.getString("name");
                apellido1 = employe.getString("surname");
                apellido2 = employe.getString("surnname2");
                correo = employe.getString("email");
                insertEmployeesDataBase(nombre, apellido1, apellido2, correo);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertEmployeesDataBase(String nombre, String apellido1, String apellido2,
                                              String correo){

        try {

            //Reinicia el AUTOINCREMENT a 1
            PreparedStatement alterTable = GestionDB.connection.prepareStatement(
                    String.format("ALTER TABLE %s AUTO_INCREMENT = 1", SchemeDB.TABLE_BD2));
            alterTable.executeUpdate();
            alterTable.close();

            // Concatenacion de apellido1 y apellido2 en la misma columna
            String apellidos = apellido1 + " " + apellido2;

            //Inserta en la base de datos los valores solicitados del JSON
            PreparedStatement preparedStatement = GestionDB.connection.prepareStatement(
                    String.format("INSERT IGNORE INTO %s (%s,%s,%s) VALUES (?,?,?)", SchemeDB.TABLE_BD2,
                            SchemeDB.COLUM_NAMEBD2, SchemeDB.COLUM_SURNAMESBD2, SchemeDB.COLUM_CORREOBD2));
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setString(3, correo);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
