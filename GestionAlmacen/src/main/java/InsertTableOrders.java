import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class InsertTableOrders {

    public static void getOrders(){
        try {
            PreparedStatement statementProductos = GestionDB.connection.prepareStatement(
                    String.format("SELECT id,%s,%s FROM %s ORDER BY RAND() LIMIT 15", SchemeDB.COLUM_DESCRIPTION,
                            SchemeDB.COLUM_PRICE, SchemeDB.TABLE_BD));
            ResultSet resultSetProductos = statementProductos.executeQuery();
            while (resultSetProductos.next()){
                int idProducto = resultSetProductos.getInt("id");
                String descripcionProducto = resultSetProductos.getString("descripcion");
                int precioProducto = resultSetProductos.getInt("precio");

                int cantidadProductos = obtenerCantidadAleatoria();
                double precioTotalPedido = precioProducto * cantidadProductos;

                PreparedStatement preparedStatement = GestionDB.connection.prepareStatement(
                        String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)", SchemeDB.TABLE_BD3,
                                SchemeDB.COLUM_IDPRODUCTO, SchemeDB.COLUM_DESCRIPCION, SchemeDB.COLUM_CANTIDAD,
                                SchemeDB.COLUM_PRECIOTOTAL));
                preparedStatement.setInt(1, idProducto);
                preparedStatement.setString(2, descripcionProducto);
                preparedStatement.setInt(3, cantidadProductos);
                preparedStatement.setDouble(4, precioTotalPedido);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int obtenerCantidadAleatoria() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}
