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
        MiBodegaProductosDao bodegaDao = new MiBodegaProductosDao();

        String pag = request.getParameter("pag") == null ?
                "1" : request.getParameter("pag");
        int paginaAct = Integer.parseInt(pag); //try

        int cantPag = bodegaDao.calcularCantPag();

        ArrayList<MiBodegaProductosBean> listaProductos = bodegaDao.listarProductoBodega(paginaAct);

        request.setAttribute("listaProductoBodegas", listaProductos);
        request.setAttribute("cantPag", cantPag);
        request.setAttribute("paginaAct",paginaAct);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MiBodegaProductos.jsp");
        requestDispatcher.forward(request,response);
    }
}
