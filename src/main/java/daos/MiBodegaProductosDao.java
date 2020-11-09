package daos;

// A futuro:
// TODO: nombre base de datos ->
// TODO: se ha hardcodeado el id de la bodega

import beans.MiBodegaProductosBean;
import beans.ProductoBodegasBean;

import java.sql.*;
import java.util.ArrayList;

public class MiBodegaProductosDao {

    public static int calcularCantPag(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

        // TODO: idBodega se ha hardcodeado
        String sql = "select ceil(count(*)/5) from producto where idBodega=1";  // numero de paginas

        int cantPag = 0;
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {

            rs.next();
            cantPag = rs.getInt(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cantPag;
    }

    public static ArrayList<MiBodegaProductosBean> listarProductoBodega(int pagina){

        ArrayList<MiBodegaProductosBean> listaProductos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

        int limit = (pagina-1)*5;
        String sql = "select nombreFoto, rutaFoto, nombreProducto,descripcion,stock,precioUnitario from producto WHERE idBodega = 1 limit ?,5;";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, limit);

            try(ResultSet rs = pstmt.executeQuery();){
                while (rs.next()) {
                    MiBodegaProductosBean producto = new MiBodegaProductosBean();
                    producto.setNombreFoto(rs.getString(1));
                    producto.setRutaFoto(rs.getString(2));
                    producto.setNombreProducto(rs.getString(3));
                    producto.setDescripcion(rs.getString(4));
                    producto.setStock(rs.getInt(5));
                    producto.setPrecioProducto(rs.getBigDecimal(6));
                    listaProductos.add(producto);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listaProductos;
    }
}
