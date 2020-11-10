package servlets;

import beans.distritosB;
import daos.ClienteD;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BodegaDistritoServlet", urlPatterns = "/BodegaDistritoServlet")
public class BodegaDistritoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClienteD clientDao = new ClienteD();
        int idCodigo = 5;

        String pag = request.getParameter("pag") == null ? "1" : request.getParameter("pag");

        int cantPag = clientDao.calcularCantPagDistrito(idCodigo);

        int paginaAct;
        try{
            paginaAct = Integer.parseInt(pag); //try
            if(paginaAct>cantPag){
                paginaAct = 1;
            }
        }catch(NumberFormatException e){
            paginaAct = 1;
        }


        ArrayList<distritosB> listaBodegas = clientDao.listarBodegasDistrito(paginaAct,idCodigo);

        request.setAttribute("listaBodegas", listaBodegas);
        request.setAttribute("cantPag", cantPag);
        request.setAttribute("paginaAct", paginaAct);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listaBodegaDistrito.jsp");
        requestDispatcher.forward(request, response);
    }
}

