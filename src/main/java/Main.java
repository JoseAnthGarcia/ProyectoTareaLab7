import beans.MiBodegaProductosBean;
import daos.MiBodegaProductosDao;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        /*
        ArrayList<MiBodegaProductosBean> productosPorPagina = MiBodegaProductosDao.listarProductoBodega(0);
        for(MiBodegaProductosBean producto: productosPorPagina ){
            System.out.println(producto.getNombreProducto());
        }*/
        MiBodegaProductosDao.crearProducto("coca", "tewgh", 20, BigDecimal.valueOf(24.00));

    }
}
