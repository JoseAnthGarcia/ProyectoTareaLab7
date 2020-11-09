package servlets;

import beans.MiBodegaProductosBean;
import beans.ProductoBodegasBean;
import daos.ClientDao;
import daos.MiBodegaProductosDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BodegaProductosServlet", urlPatterns = {"/BodegaProductos"})
public class BodegaProductosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        
        String pag = request.getParameter("pag") == null ?
                "1" : request.getParameter("pag");
        int paginaAct = Integer.parseInt(pag); // se trata de obtener la pagina actual, si es null, se asigna la 1 por defecto
        int cantPag = MiBodegaProductosDao.calcularCantPag();

        RequestDispatcher view;
        switch (action) {
            case "lista":
                ArrayList<MiBodegaProductosBean> listaProductos = MiBodegaProductosDao.listarProductoBodega(paginaAct); // se lista los productos de la pagina actual

                request.setAttribute("listaProductoBodegas", listaProductos);
                request.setAttribute("cantPag", cantPag);
                request.setAttribute("paginaAct",paginaAct);
                view = request.getRequestDispatcher("MiBodegaProductos.jsp");
                view.forward(request,response);
                break;
            case "formAdd":
                view = request.getRequestDispatcher("anadirProducto.jsp");
                view.forward(request,response);
        }

    }
}
