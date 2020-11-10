<%@ page import="beans.PedidosBodegaBean" %><%--
  Created by IntelliJ IDEA.
  User: lizbe
  Date: 8/11/2020
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import= "beans.PedidosBodegaBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaPedidosBodega" scope="request" type="java.util.ArrayList<beans.PedidosBodegaBean>"/>
<jsp:useBean id="cantPag" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="paginaAct" scope="request" type="java.lang.Integer"/>
<html>
<head>
    <title>Title</title>
    <jsp:include page="bootstrapRepository.jsp"/>
</head>
<body>
<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">

        </div>
    </div>
    <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
            <a href="#" class="navbar-brand d-flex align-items-center">
                <strong>MiMarca.com</strong>
            </a>
            <a href="../MiBodega.html" class="navbar-brand d-flex align-items-center">
                <strong>Mi Bodega</strong>
            </a>
            <a href="<%=request.getContextPath()%>/ClientServlet" class="navbar-brand d-flex align-items-center">
                <strong>Productos</strong>
            </a>
            <a href="<%=request.getContextPath()%>/PedidosServlet" class="navbar-brand d-flex align-items-center">
                <strong>Pedidos</strong>
            </a>
        </div>
    </div>
</header>
<div class="container" style="margin-top: 20px">
    <div class="row">
        <table class="table table-hover">
            <tr>
                <th>idPedido</th>
                <th>Codigo</th>
                <th>Estado</th>
                <th></th>
                <th></th>
            </tr>
            <% for (PedidosBodegaBean pedidos : listaPedidosBodega) { %>
            <tr>
                <td><%=pedidos.getIdPedido()%>
                </td>
                <td><a href="<%=request.getContextPath()%>/PedidosServlet?accion=mostrar&idPedido=<%=pedidos.getIdPedido() %>"><%=pedidos.getCodigo()%></a>
                </td>
                <td><%=pedidos.getEstado()%>
                </td>
                <td><a href="<%=request.getContextPath()%>/PedidosServlet?accion=entregar&idPedido=<%=pedidos.getIdPedido()%>" class="btn btn-outline-success">Pedido Entregado</a></td>
                <td><a href="<%=request.getContextPath()%>/PedidosServlet?accion=cancelar&idPedido=<%=pedidos.getIdPedido()%>" class="btn btn-outline-danger">Cancelar Pedido</a></td>
            </tr>
            <% } %>
        </table>
        <div class="row">
            <nav aria-label="Page navigation example" class = "mx-auto"> <!-- Recordar centro !! -->
                <ul class="pagination justify-content-center">
                    <%if(paginaAct==1){%>
                    <li class="page-item disabled">
                        <span class="page-link">Anterior</span>
                    </li>
                    <%}else{%>
                    <li class="page-item">
                        <a class="page-link" href="<%=request.getContextPath()%>/PedidosServlet?pag=<%=paginaAct-1%>">Anterior</a>
                    </li>
                    <%}%>

                    <% for(int k=1; k<=cantPag; k++){
                        if(k==paginaAct){%>
                    <li class="page-item active">
                          <span class="page-link"><%=k%><span class="sr-only">(current)</span>
                          </span>
                    </li>
                    <%      }else{%>
                    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/PedidosServlet?pag=<%=k%>"><%=k%></a></li>
                    <%      }
                    } %>


                    <%if(paginaAct==cantPag){%>
                    <li class="page-item disabled">
                        <span class="page-link">Siguiente</span>
                    </li>
                    <%}else{%>
                    <li class="page-item">
                        <a class="page-link" href="<%=request.getContextPath()%>/PedidosServlet?pag=<%=paginaAct+1%>">Siguiente</a>
                    </li>
                    <%}%>

                </ul>
            </nav>
        </div>
    </div>
<footer class="page-footer font-small blue" style="margin-top: 60px">
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="#"> MiMarca.com</a>
    </div>
</footer>
</div>
</body>
</html>
