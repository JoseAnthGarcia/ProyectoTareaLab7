package servlets;

import beans.PedidosClienteBean;
import beans.ProductoBodegasBean;
import daos.PedidosClienteDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PedidosClienteServlet",urlPatterns = {"/pedidosCliente"})
public class PedidosClienteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PedidosClienteDao pedidosClienteDao = new PedidosClienteDao();




        String pag = request.getParameter("pag") == null ?
                "1" : request.getParameter("pag");
        int paginaAct = Integer.parseInt(pag); //try

        int cantPag = pedidosClienteDao.calcularCantPag();

        ArrayList<PedidosClienteBean> listaPedidos = pedidosClienteDao.listarPedidosCliente(paginaAct);

        request.setAttribute("listaPedidos", listaPedidos);
        request.setAttribute("cantPag", cantPag);
        request.setAttribute("paginaAct",paginaAct);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listarPedidosCliente.jsp");
        requestDispatcher.forward(request,response);
    }
}
