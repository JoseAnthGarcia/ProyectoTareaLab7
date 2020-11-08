package servlets;

import beans.PedidosBodegaBean;
import daos.PedidoBodegaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PedidosServlet", urlPatterns = {"/PedidosServlet"})
public class PedidosBodegaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion") == null?
                "listar":
                request.getParameter("accion");
        PedidoBodegaDao pedidoBodegaDao = new PedidoBodegaDao();
        RequestDispatcher view;
        switch (action){
            case "listar":
                ArrayList<PedidosBodegaBean> listaPedidosBodega = pedidoBodegaDao.obtenerListaPedidosBodega();
                request.setAttribute("listaPedidosBodega", listaPedidosBodega);
                view = request.getRequestDispatcher("listaPedidosBodega.jsp");
                view.forward(request,response);
                break;
            case "crear":
                view = request.getRequestDispatcher("crearPedido.jsp");
                view.forward(request,response);
                break;
            case "mostrar":
                String idPedido = request.getParameter("idPedido");
                PedidosBodegaBean pedidos = pedidoBodegaDao.mostrarPedido(idPedido);
                request.setAttribute("pedido", pedidos);
                view = request.getRequestDispatcher("mostrarPedido.jsp");
                view.forward(request,response);
                break;
        }
    }
}
