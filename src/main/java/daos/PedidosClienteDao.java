package daos;

import beans.PedidosClienteBean;
import beans.ProductoBodegasBean;

import java.sql.*;
import java.util.ArrayList;

public class PedidosClienteDao {
    public int calcularCantPag(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

        String sql = "select ceil(count(codigo)/5) from pedido where idUsuario=1;";

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

    public ArrayList<PedidosClienteBean> listarPedidosCliente (int pagina){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usuario="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

        ArrayList<PedidosClienteBean> listaPedidos=  new ArrayList<>();
        int limit = (pagina-1)*5;
        String sql= "select codigo, estado \n" +
                "from pedido\n" +
                "where idUsuario=1\n" +
                "limit ?,5;";


        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, limit);

            try(ResultSet rs = pstmt.executeQuery();){
                while(rs.next()){
                    PedidosClienteBean pedidosClienteBean = new PedidosClienteBean();
                    pedidosClienteBean.setCodigoPedido(rs.getString(1));
                    pedidosClienteBean.setEstadoPedido(rs.getString(2));
                    listaPedidos.add(pedidosClienteBean);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return listaPedidos;
    }
}
