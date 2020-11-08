package daos;

import beans.PedidosBodegaBean;

import java.sql.*;
import java.util.ArrayList;

public class PedidoBodegaDao {
    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

    public ArrayList<PedidosBodegaBean> obtenerListaPedidosBodega() {
        ArrayList<PedidosBodegaBean> listaPedidosBodega = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select idPedido, codigo, estado from pedido");
            while (rs.next()) {
                PedidosBodegaBean pedidos = new PedidosBodegaBean();
                pedidos.setIdPedido(rs.getInt(1));
                pedidos.setCodigo(rs.getString(2));
                pedidos.setEstado(rs.getString(3));
                listaPedidosBodega.add(pedidos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPedidosBodega;
    }

    public PedidosBodegaBean mostrarPedido(String idPedido) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT p.codigo, b.nombreBodega, p.fecha_registro, p.fecha_recojo, DATE_ADD(p.fecha_recojo, INTERVAL 1 DAY) as `fecha limite`, count(php.idProducto), t1.`costo total` FROM pedido p LEFT JOIN bodega b ON p.idBodega = b.idBodega left join pedido_has_producto php ON p.idPedido = php.idPedido LEFT JOIN (SELECT t.idPedido, sum(t.`Costo por producto`) as `costo total` FROM (SELECT php.idPedido, php.idProducto, (php.cantidad*p.precioUnitario) as `Costo por producto`FROM pedido_has_producto php LEFT JOIN producto p ON php.idProducto = p.idProducto) t  GROUP BY t.idPedido) t1 ON p.idPedido = t1.idPedido WHERE idPedido = ? GROUP BY p.idPedido;";
        PedidosBodegaBean pedidos = null;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, Integer.parseInt(idPedido));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pedidos = new PedidosBodegaBean();
                    pedidos.setCodigo(rs.getString(2));
                    pedidos.setFecha_registro(rs.getString(4));
                    pedidos.setFecha_recojo(rs.getString(5));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }
    public void entregarPedido(int idPedido) {

        try (Connection conn = DriverManager.getConnection(url, user, pass);) {
            String sql = "update pedido set estado = 'Entregado' where idPedido = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, String.valueOf(idPedido));
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
