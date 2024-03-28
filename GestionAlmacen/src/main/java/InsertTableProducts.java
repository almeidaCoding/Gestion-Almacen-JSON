import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTableProducts {
    static String name = "";
    static String category = "";
    static String description = "";
    static int stock;
    static double price;

    public static void getJsonFromProducts(){
        try {

            //conexion y lectura del JSON
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connectProducts = (HttpURLConnection) url.openConnection();
            BufferedReader readProducts = new BufferedReader(new InputStreamReader(connectProducts.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = readProducts.readLine()) != null){
                stringBuffer.append(line);
            }

            // For que recorre el JSON y devuelve los valores solicitados
            JSONObject json = new JSONObject(stringBuffer.toString());
            JSONArray productos = json.getJSONArray("products");
            for(int i = 0; i < productos.length(); i++){
                JSONObject producto = productos.getJSONObject(i);
                name = producto.getString("title");
                category = producto.getString("category");
                description = producto.getString("description");
                stock = producto.getInt("stock");
                price = producto.getInt("price");
                insertProductsDatabase(name, category, description, stock, price);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertProductsDatabase(String name, String category, String description,
                                              int stock, double price){
        try {

            //Reinicia el AUTOINCREMENT a 1
            PreparedStatement alterTable = GestionDB.connection.prepareStatement(
                    String.format("ALTER TABLE %s AUTO_INCREMENT = 1", SchemeDB.TABLE_BD));
            alterTable.executeUpdate();
            alterTable.close();

            //Inserta en la base de datos los valores solicitados del JSON
            PreparedStatement preparedStatement = GestionDB.connection.prepareStatement(
                    String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)", SchemeDB.TABLE_BD,
                            SchemeDB.COLUM_NAME, SchemeDB.COLUM_CATEGORY, SchemeDB.COLUM_DESCRIPTION,
                            SchemeDB.COLUM_STOCK, SchemeDB.COLUM_PRICE));
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, stock);
            preparedStatement.setDouble(5, price);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
