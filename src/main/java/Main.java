import beans.MiBodegaProductosBean;
import daos.MiBodegaProductosDao;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<MiBodegaProductosBean> productosPorPagina = MiBodegaProductosDao.listarProductoBodega(0);
        for(MiBodegaProductosBean producto: productosPorPagina ){
            System.out.println(producto.getNombreProducto());
        }
    }
}
