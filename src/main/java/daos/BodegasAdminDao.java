package daos;

import beans.BodegasAdminBean;

import java.sql.*;
import java.util.ArrayList;

public class BodegasAdminDao {

    public static int calcularCantPag() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

        String sql = "select ceil(count(ruc)/5) from bodega where idAdministrador=5;";

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




    public ArrayList<BodegasAdminBean> obtenerListaBodegas(int pagina){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

        ArrayList<BodegasAdminBean> listaBodegas = new ArrayList<>();

        int limit = (pagina-1)*5;
        String sql = "select * from bodega where idAdministrador=5 limit ?,5;";

        //String sql= "select codigo, estado from pedido where idUsuario=1 limit ?,5;";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, limit);

            try(ResultSet rs = pstmt.executeQuery();){
                while(rs.next()){
                    BodegasAdminBean bodega = new BodegasAdminBean();
                    bodega.setRucBodega(rs.getInt("ruc"));
                    bodega.setNombreBodega(rs.getString("nombreBodega"));
                    bodega.setEstadoBodega(rs.getString("estado"));

                    listaBodegas.add(bodega);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return listaBodegas;

    }

}
