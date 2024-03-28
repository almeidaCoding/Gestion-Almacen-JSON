import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertTableProductsFav {

    public static void getTableProductsFav(){
        try {
            PreparedStatement statementProductosFav = GestionDB.connection.prepareStatement(
                    String.format("SELECT id FROM %s WHERE %s > 1000", SchemeDB.TABLE_BD,
                            SchemeDB.COLUM_PRICE));
            ResultSet resultSetProductosFav = statementProductosFav.executeQuery();
            while (resultSetProductosFav.next()){
                int idProducto = resultSetProductosFav.getInt("id");

                PreparedStatement preparedStatement = GestionDB.connection.prepareStatement(
                        String.format("INSERT INTO %s (%s) VALUES (?)", SchemeDB.TABLE_BD4,
                                SchemeDB.COLUM_IDPRODUCTO2));
                preparedStatement.setInt(1, idProducto);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}