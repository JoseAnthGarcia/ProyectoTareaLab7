
package daos;

import beans.distritosB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteD {
    public ClienteD() {
    }

    public int calcularCantPag() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        String sql = "select ceil(count(nombreFoto)/8) from bodega ;";
        int cantPag = 0;

        try {
            Connection conn = DriverManager.getConnection(url, "root", "root");
            Throwable var5 = null;

            try {
                Statement stmt = conn.createStatement();
                Throwable var7 = null;

                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    Throwable var9 = null;

                    try {
                        rs.next();
                        cantPag = rs.getInt(1);
                    } catch (Throwable var59) {
                        var9 = var59;
                        throw var59;
                    } finally {
                        if (rs != null) {
                            if (var9 != null) {
                                try {
                                    rs.close();
                                } catch (Throwable var58) {
                                    var9.addSuppressed(var58);
                                }
                            } else {
                                rs.close();
                            }
                        }

                    }
                } catch (Throwable var62) {
                    var7 = var62;
                    throw var62;
                } finally {
                    if (stmt != null) {
                        if (var7 != null) {
                            try {
                                stmt.close();
                            } catch (Throwable var57) {
                                var7.addSuppressed(var57);
                            }
                        } else {
                            stmt.close();
                        }
                    }

                }
            } catch (Throwable var64) {
                var5 = var64;
                throw var64;
            } finally {
                if (conn != null) {
                    if (var5 != null) {
                        try {
                            conn.close();
                        } catch (Throwable var56) {
                            var5.addSuppressed(var56);
                        }
                    } else {
                        conn.close();
                    }
                }

            }
        } catch (SQLException var66) {
            var66.printStackTrace();
        }

        return cantPag;
    }

    public ArrayList<distritosB> listarBodegas(int pagina) {
        ArrayList listaBodegas = new ArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        int limit = (pagina - 1) * 8;
        String sql = "select nombreFoto,rutaFoto ,nombreBodega, direccion\n" +
                "from bodega where lower(estado) = \"activo\" limit ?,8;;";

        try {
            Connection conn = DriverManager.getConnection(url, "root", "root");
            Throwable var7 = null;

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                Throwable var9 = null;

                try {
                    pstmt.setInt(1, limit);
                    ResultSet rs = pstmt.executeQuery();
                    Throwable var11 = null;

                    try {
                        while(rs.next()) {
                            distritosB bodega = new distritosB();
                            bodega.setNombreFoto(rs.getString(1));
                            bodega.setRutaFoto(rs.getString(2));
                            bodega.setNombreBodega(rs.getString(3));
                            bodega.setDireccion(rs.getBigDecimal(4));
                            listaBodegas.add(bodega);
                        }
                    } catch (Throwable var62) {
                        var11 = var62;
                        throw var62;
                    } finally {
                        if (rs != null) {
                            if (var11 != null) {
                                try {
                                    rs.close();
                                } catch (Throwable var60) {
                                    var11.addSuppressed(var60);
                                }
                            } else {
                                rs.close();
                            }
                        }

                    }
                } catch (Throwable var64) {
                    var9 = var64;
                    throw var64;
                } finally {
                    if (pstmt != null) {
                        if (var9 != null) {
                            try {
                                pstmt.close();
                            } catch (Throwable var59) {
                                var9.addSuppressed(var59);
                            }
                        } else {
                            pstmt.close();
                        }
                    }

                }
            } catch (Throwable var66) {
                var7 = var66;
                throw var66;
            } finally {
                if (conn != null) {
                    if (var7 != null) {
                        try {
                            conn.close();
                        } catch (Throwable var58) {
                            var7.addSuppressed(var58);
                        }
                    } else {
                        conn.close();
                    }
                }

            }
        } catch (SQLException var68) {
            var68.printStackTrace();
        }

        return listaBodegas;
    }
}