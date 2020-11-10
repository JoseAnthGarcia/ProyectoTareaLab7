<%@ page import="beans.BodegasAdminBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<BodegasAdminBean> listaBodegas = (ArrayList<BodegasAdminBean>) request.getAttribute("lista");
%>

<% int paginaAct = 1;%>
<% int cantPag = 1;%>


<html>
<head>
    <jsp:include page="bootstrapRepository.jsp"/>
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
    <title>Lista de bodegas</title>
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
                <a href="#" class="navbar-brand d-flex align-items-center">
                    <strong>Administración</strong>
                </a>
                <a href="#" class="navbar-brand d-flex align-items-center">
                    <strong>Registrar bodega</strong>
                </a>
                <a href="#" class="navbar-brand d-flex align-items-center">
                    <strong>Lista de bodegas</strong>
                </a>
            </div>
        </div>
    </header>
<div class ='container'>
    <h1 class="margen">Mis Bodegas</h1>
    <div class="container-fluid">
        <table class="table container-fluid">
            <tr>
                <th>RUC de la bodega</th>
                <th>Nombre de la bodega</th>
                <th>Estado</th>
                <th>Bloquear bodega</th>
            </tr>
            <%
                for(BodegasAdminBean bodega : listaBodegas){
            %>
            <tr>
                <td><%= bodega.getRucBodega() %></td>
                <td><%= bodega.getNombreBodega() %></td>
                <td><%= bodega.getEstadoBodega() %></td>
                <td><button type="button" class="btn btn-danger">Bloquear</button></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <div class="row">
        <nav aria-label="Page navigation example" class = "mx-auto"> <!-- Recordar centro !! -->
            <ul class="pagination justify-content-center">
                <%if(paginaAct==1){%>
                <li class="page-item disabled">
                    <span class="page-link">Anterior</span>
                </li>
                <%}else{%>
                <li class="page-item">
                    <a class="page-link" href="<%=request.getContextPath()%>/AdminServlet?pag=<%=paginaAct-1%>">Anterior</a>
                </li>
                <%}%>

                <% for(int k=1; k<=cantPag; k++){
                    if(k==paginaAct){%>
                <li class="page-item active">
                          <span class="page-link"><%=k%><span class="sr-only">(current)</span>
                          </span>
                </li>
                <%      }else{%>
                <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/AdminServlet?pag=<%=k%>"><%=k%></a></li>
                <%      }
                } %>


                <%if(paginaAct==cantPag){%>
                <li class="page-item disabled">
                    <span class="page-link">Siguiente</span>
                </li>
                <%}else{%>
                <li class="page-item">
                    <a class="page-link" href="<%=request.getContextPath()%>/AdminServlet?pag=<%=paginaAct+1%>">Siguiente</a>
                </li>
                <%}%>

            </ul>
        </nav>
    </div>

</div>
</body>
</html>
