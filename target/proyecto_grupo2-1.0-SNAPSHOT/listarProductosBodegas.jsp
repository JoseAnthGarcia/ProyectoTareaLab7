<%@ page import="beans.ProductoBodegasBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaProductoBodegas" scope="request" type="java.util.ArrayList<beans.ProductoBodegasBean>"/>
<jsp:useBean id="cantPag" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="paginaAct" scope="request" type="java.lang.Integer"/>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="bootstrapRepository.jsp"/>
    <!-- para los iconos como botones -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .btn {
            background-color: #d6d2c4;
            border: none;
            color: black;
            padding: 12px 16px;
            font-size: 15px;
            cursor: pointer;
        }
        /* Darker background on mouse-over */
        .btn:hover {
            background-color: #f05454;
        }
        .margen{
            margin-top: 2%;
        }
        .container-fluid{
            text-align: center;
            padding: 3% 15% ;
        }
        .page-item .page-link {
            color: #767676;
            border-color: #767676;
        }
        .page-item.active .page-link {
            border-color: #767676;
            background-color: #767676;
        }
    </style>

    <title>Bienvenido Bodega!</title>
</head>
<body>

<!-- todo:  corregir el espaciado entre Mi Bodega, Pedidos y Productos -->
<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">

        </div>
    </div>
    <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
            <a href="#" class="navbar-brand d-flex align-items-center">
                <strong>Anacleto.com</strong>
            </a>
            <a href="#" class="navbar-brand d-flex align-items-center">
                <strong>Mi Bodega</strong>
            </a>
            <a href="#" class="navbar-brand d-flex align-items-center">
                <strong>Productos</strong>
            </a>
            <a href="#" class="navbar-brand d-flex align-items-center">
                <strong>Pedidos</strong>
            </a>
        </div>
    </div>
</header>

<div class="container" style="margin-top: 65px">
    <!-- Presentacion de productos -->
    <% int cant = 0;
    for(int i=0; i<2; i++){
    %>
    <div class="row">
        <% int min = i*4;
            int max = (i+1)*4;
        for(int j=min; j<max; j++){
            if(cant < listaProductoBodegas.size()){
        %>
                <div class="col-sm-3"> <!-- Probar medidas "sm-3"? -->
                    <img src="./src/imagenes/Azucar.jpg" width="100" class="img-thumbnail">
                    <p class="mb-1"><b>Producto: </b> <%=listaProductoBodegas.get(j).getNombreProducto()%> </p>
                    <p class="mb-0"><b>Precio: </b> <%=listaProductoBodegas.get(j).getPrecioProducto()%> </p>
                    <p class="mb-3"><b>Bodega: </b> <%=listaProductoBodegas.get(j).getNombreBodega()%> </p>
                </div>
            <% } else{ %>
                <div class="col-sm-3"> <!-- Probar medidas "sm-3"? -->
                </div>
            <%}%>
        <% cant++;
            } %>
    </div>
    <% } %>

    <!-- paginacion -->
    <div class="row">
        <nav aria-label="Page navigation example" class = "mx-auto"> <!-- Recordar centro !! -->
            <ul class="pagination justify-content-center">
                <%if(paginaAct==1){%>
                    <li class="page-item disabled">
                        <span class="page-link">Anterior</span>
                    </li>
                <%}else{%>
                    <li class="page-item">
                        <a class="page-link" href="<%=request.getContextPath()%>/ClientServlet?pag=<%=paginaAct-1%>">Anterior</a>
                    </li>
                <%}%>

                <% for(int k=1; k<=cantPag; k++){
                    if(k==paginaAct){%>
                        <li class="page-item active">
                          <span class="page-link"><%=k%><span class="sr-only">(current)</span>
                          </span>
                        </li>
                <%      }else{%>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/ClientServlet?pag=<%=k%>"><%=k%></a></li>
                <%      }
                    } %>


                <%if(paginaAct==cantPag){%>
                <li class="page-item disabled">
                    <span class="page-link">Siguiente</span>
                </li>
                <%}else{%>
                <li class="page-item">
                    <a class="page-link" href="<%=request.getContextPath()%>/ClientServlet?pag=<%=paginaAct+1%>">Siguiente</a>
                </li>
                <%}%>

            </ul>
        </nav>
    </div>


</div>

<footer class="page-footer font-small blue" style="margin-top: 20px">
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="#">MiMarca</a>
    </div>
</footer>



</div>


</body>
</html>