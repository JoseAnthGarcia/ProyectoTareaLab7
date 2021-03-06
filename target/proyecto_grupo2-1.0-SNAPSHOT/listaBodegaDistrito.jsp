<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 9/11/2020
  Time: 02:19
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaBodegas" scope="request" type="java.util.ArrayList<beans.distritosB>"/>
<jsp:useBean id="cantPag" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="paginaAct" scope="request" type="java.lang.Integer"/>

<html>
<head>
    <jsp:include page="bootstrapRepository.jsp"/>
    <!-- para los iconos como botones -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .btn {
            background-color: #ffffff;
            border: none;
            color: black;
            padding: 12px 16px;
            font-size: 15px;
            cursor: pointer;
        }
        /* Darker background on mouse-over */
        .btn:hover {
            background-color: #767676;
        }
    </style>

    <title>Bienvenido Bodega!</title>
</head>
<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">

        </div>
    </div>
    <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
            <a href="#" class="navbar-brand d-flex align-items-center">
                <strong>MiBodega.com</strong>
            </a>

            <div class="card text-white bg-dark" ><a href="#" >
                    <img src="imagenes/sigout.png" height="30px" class="rounded"/>
                </a>
            </div>

        </div>
    </div>
</header>
<body>
<div class="container" style="margin-top: 30px" >
    <h2 class="jumbotron-heading">Estas son tus bodegas más cercanas:</h2>
</div>

<div class="container" style="margin-top: 50px">
    <!-- Presentacion de productos -->
    <% int cant = 0;
    %>
    <div class="row">
        <% int min = 0;
            int max = 3;
            for(int j=min; j<max; j++){
                if(cant < listaBodegas.size()){
        %>
        <div class="col-sm-4"> <!-- Probar medidas "sm-4"? -->
            <img src="https://res.cloudinary.com/dps0t3hax/image/upload/v1604908416/shop-vector-icon_hrahx2.jpg" width="230" class="img-thumbnail">
            <p class="mb-1"><b>Bodega: </b> <%=listaBodegas.get(j).getNombreBodega()%> </p>
            <p class="mb-0"><b>Dirección: </b> <%=listaBodegas.get(j).getDireccion()%> </p>
        </div>
        <% } else{ %>
        <div class="col-sm-8"> <!-- Probar medidas "sm-3"? -->
        </div>
        <%}%>
        <% cant++;
        } %>
    </div>

    <!-- paginacion -->
    <div class="row">
        <div class="container" style="margin-top: 30px">
        <nav aria-label="Page navigation example" class = "mx-auto"> <!-- Recordar centro !! -->
            <ul class="pagination justify-content-center">
                <%if(paginaAct==1){%>
                <li class="page-item disabled">
                    <span class="page-link">Anterior</span>
                </li>
                <%}else{%>
                <li class="page-item">
                    <a class="page-link" href="<%=request.getContextPath()%>/BodegaDistritoServlet?pag=<%=paginaAct-1%>">Anterior</a>
                </li>
                <%}%>

                <% for(int k=1; k<=cantPag; k++){
                    if(k==paginaAct){%>
                <li class="page-item active">
                          <span class="page-link"><%=k%><span class="sr-only">(current)</span>
                          </span>
                </li>
                <%      }else{%>
                <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/BodegaDistritoServlet?pag=<%=k%>"><%=k%></a></li>
                <%      }
                } %>


                <%if(paginaAct==cantPag){%>
                <li class="page-item disabled">
                    <span class="page-link">Siguiente</span>
                </li>
                <%}else{%>
                <li class="page-item">
                    <a class="page-link" href="<%=request.getContextPath()%>/BodegaDistritoServlet?pag=<%=paginaAct+1%>">Siguiente</a>
                </li>
                <%}%>

            </ul>
        </nav>
            <div class="pagination justify-content-end">
                <a href="#" class="btn btn-outline-danger" ><h5>Elegir otra Bodega</h5></a>
            </div>
        </div>

    </div>


</div>


<footer class="page-footer font-small blue" style="margin-top: 5px">
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="#">MiBodega.com</a>
    </div>
</footer>
</body>
</html>
