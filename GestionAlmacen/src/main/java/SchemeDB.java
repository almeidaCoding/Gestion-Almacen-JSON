public interface SchemeDB {
    /* Variables que tienen los valores de la BD, Host
    y tabla productos */
    String NAME_BD = "almacen";
    String HOST = "127.0.0.1:3306";
    String TABLE_BD = "productos";
    String COLUM_NAME = "nombre";
    String COLUM_CATEGORY = "categoria";
    String COLUM_DESCRIPTION = "descripcion";
    String COLUM_STOCK = "cantidad";
    String COLUM_PRICE = "precio";


    /* Variables tabla empleados */
    String TABLE_BD2 = "empleados";
    String COLUM_NAMEBD2 = "nombre";
    String COLUM_SURNAMESBD2 = "apellidos";
    String COLUM_CORREOBD2 = "correo";


    /* Variables tabla pedidos */
    String TABLE_BD3 = "pedidos";
    String COLUM_IDPRODUCTO = "id_producto";
    String COLUM_DESCRIPCION = "descripcion";
    String COLUM_CANTIDAD = "cantidad";
    String COLUM_PRECIOTOTAL = "precio_total";


    /* Variable table productos_fav */
    String TABLE_BD4 = "productos_fav";
    String COLUM_IDPRODUCTO2 = "id_producto";





}
