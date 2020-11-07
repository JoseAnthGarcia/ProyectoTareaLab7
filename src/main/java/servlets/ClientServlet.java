package servlets;

import beans.ProductoBodegasBean;
import com.mysql.cj.xdevapi.Client;
import daos.ClientDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ClientServlet", urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientDao clientDao = new ClientDao();
        String pag = request.getParameter("pag") == null ?
                "1" : request.getParameter("pag");
        int paginaAct = Integer.parseInt(pag); //try

        int cantPag = clientDao.calcularCantPag();

        ArrayList<ProductoBodegasBean> listaProductos = clientDao.listarProductoBodegas(paginaAct);

        request.setAttribute("listaProductoBodegas", listaProductos);
        request.setAttribute("cantPag", cantPag);
        request.setAttribute("paginaAct",paginaAct);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listarProductosBodegas.jsp");
        requestDispatcher.forward(request,response);
    }
}
