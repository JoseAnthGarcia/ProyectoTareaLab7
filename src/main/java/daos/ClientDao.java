package daos;

import beans.ProductoBodegasBean;

import java.sql.*;
import java.util.ArrayList;

public class ClientDao {

    public ArrayList<ProductoBodegasBean> listarProductoBodegas(int pagina){

        ArrayList<ProductoBodegasBean> listaProductos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

        //0,8,16,etc
        // 1 -> 0, 2-> 8, 3-> 16
        int limit = (pagina-1)*8;

        String sql = "select p.nombreFoto,p.rutaFoto ,p.nombreProducto, p.precioUnitario, b.nombreBodega\n" +
                "from producto p\n" +
                "inner join bodega b on p.idBodega=b.idBodega\n" +
                "order by b.nombreBodega, p.nombreProducto\n" +
                "limit ?,8;";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, limit);

            try(ResultSet rs = pstmt.executeQuery();){
                while (rs.next()) {
                    ProductoBodegasBean producto = new ProductoBodegasBean();
                    producto.setNombreFoto(rs.getString(1));
                    producto.setRutaFoto(rs.getString(2));
                    producto.setNombreProducto(rs.getString(3));
                    producto.setPrecioProducto(rs.getBigDecimal(4));
                    producto.setNombreBodega(rs.getString(5));
                    listaProductos.add(producto);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listaProductos;
    }

}
