import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowData {

    public static void showEmployees(){
        try {
            PreparedStatement preparedStatement = GestionDB.connection.prepareStatement(
                    String.format("SELECT * FROM %s", SchemeDB.TABLE_BD2));
            ResultSet resultSetEmployees = preparedStatement.executeQuery();
            System.out.println("Listado de empleados: \n");
            while(resultSetEmployees.next()){
                int idEmployees = resultSetEmployees.getInt("id");
                String nombreEmployees = resultSetEmployees.getString("nombre");
                String apellidosEmployees = resultSetEmployees.getString("apellidos");
                String correoEmployees = resultSetEmployees.getString("correo");

                System.out.println(idEmployees + " " + nombreEmployees + " " + apellidosEmployees + " " +
                        correoEmployees);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showProducts(){
        try {
            PreparedStatement preparedStatement = GestionDB.connection.prepareStatement(
                    String.format("SELECT * FROM %s", SchemeDB.TABLE_BD));
            ResultSet resultSetProducts = preparedStatement.executeQuery();
            System.out.println("\nListado de productos: \n");
            while(resultSetProducts.next()){
                int idProducts = resultSetProducts.getInt("id");
                String nombreProducts = resultSetProducts.getString("nombre");
                String categoryProducts = resultSetProducts.getString("categoria");
                String descriptionProducts = resultSetProducts.getString("descripcion");
                String stockProducts = resultSetProducts.getString("cantidad");
                String priceProducts = resultSetProducts.getString("precio");

                System.out.println(idProducts + " " + nombreProducts + " " + categoryProducts + " " +
                        descriptionProducts + " " + stockProducts + " " + priceProducts);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showOrders(){
        try {
            PreparedStatement preparedStatement = GestionDB.connection.prepareStatement(
                    String.format("SELECT * FROM %s", SchemeDB.TABLE_BD3));
            ResultSet resultSetOrders = preparedStatement.executeQuery();
            System.out.println("\nListado de pedidos: \n");
            while(resultSetOrders.next()){
                int idOrders = resultSetOrders.getInt("id");
                int idProducts = resultSetOrders.getInt("id_producto");
                String descriptionProducts = resultSetOrders.getString("descripcion");
                String cantidadOrders = resultSetOrders.getString("cantidad");
                String priceTotally = resultSetOrders.getString("precio_total");

                System.out.println(idOrders + " " + idProducts + " " +
                        descriptionProducts + " " + cantidadOrders + " " + priceTotally);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showProductsEconomy(){
        try {
            PreparedStatement preparedStatement = GestionDB.connection.prepareStatement(
                    String.format("SELECT * FROM %s WHERE %s < 600", SchemeDB.TABLE_BD, SchemeDB.COLUM_PRICE));
            ResultSet resultSetEconomy = preparedStatement.executeQuery();
            System.out.println("\nListado de productos con un precio inferior a 600€: \n");
            while(resultSetEconomy.next()){
                int idProducts = resultSetEconomy.getInt("id");
                String nombreEconomy = resultSetEconomy.getString("nombre");
                String priceEconomy = resultSetEconomy.getString("precio");

                System.out.println(idProducts + " " + nombreEconomy + " " + priceEconomy);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
