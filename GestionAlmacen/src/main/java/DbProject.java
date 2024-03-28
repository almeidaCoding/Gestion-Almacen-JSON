import java.sql.Connection;

public class DbProject {
    public static void main(String[] args) {

        //Conexión con la BD
        Connection connection = GestionDB.getConnection();

        //Insertar productos del JSON en la tabla 'productos'
        InsertTableProducts.getJsonFromProducts();

        //Insertar empleados del JSON en la tabla 'empleados'
        InsertTableEmployees.getJsonFromEmployees();

        //Insertar productos en la tabla 'pedidos'
        InsertTableOrders.getOrders();

        //Insertar productos > 1000€ en la tabla 'products_fav'
        InsertTableProductsFav.getTableProductsFav();

        //Mostrar datos de las tablas
        ShowData.showEmployees();
        ShowData.showProducts();
        ShowData.showOrders();
        ShowData.showProductsEconomy();
    }
}