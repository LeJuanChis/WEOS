<%-- 
    Document   : verCotizacionEmpresa
    Created on : 15/11/2021, 11:10:59 AM
    Author     : Juan Carlos Osorio
--%>

<%
    if (session.getAttribute("enterprise") != null) {
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />
<h1>Tus cotizaciones</h1>
<div class="datatable-container">


    <div class="buscar">
        <input id="searchTerm" type="text" onkeyup="doSearch()" class="search-input" placeholder="Buscar">
    </div>


    <table id="datos" class="datatable">
        <thead>
            <tr>
                <th>Codigo de la cotizacion</th>
                <th>Nombre del producto</th>
                <th>Precio por unidad</th>
                <th>Descripcion</th>
                <th>Cantidad de productos</th>
                <th>Nombre del usuario</th>
                <th>Correo del usuario</th>
                <th>Telefono del usuario</th>
            </tr>

        </thead>

        <tbody>


            <!--Creamos el bucle para mostrar todas las cotizaciones de la empresa-->
            <c:forEach var="psc" items="${productosCotizaciones}">
                <tr>
             <td id="id"><span>${psc.getCotizacion().getCodigo()}</span></td>
                    <td>${psc.getNombreProducto()}</td>
                    <td>${psc.getPrecio_unidad()}</td>
                    <td>${psc.getDescripcionProducto()}</td>
                    <td>${psc.getCantidad_productos()}</td>
                    <td>${psc.getUsuario().getNombre()}</td>
                    <td>${psc.getUsuario().getCorreo()}</td>
                    <td>${psc.getUsuario().getTelefono()}</td>
                </tr>
            </c:forEach>



        </tbody>

    </table>



    <div class="footer-tools">
        <div class="list-items">
            <select name="n-entries" id="n-entries" class="n-entries">
                <option value="15">5</option>
                <option value="10" selected="">10</option>
                <option value="15">15</option>
            </select>
            Productos
        </div>

        <div class="pages">
            <ul>
                <li><span class="active">1</span></li>
                <li><button>2</button></li>
                <li><button>3</button></li>
                <li><button>4</button></li>
                <li><span>...</span></li>
                <li><button>7</button></li>
                <li><button>8</button></li>
                <li><button>9</button></li>
            </ul>
        </div>
    </div>
</div>
<script src="./assets/js/busca.js"></script>
<jsp:include page="../layout/footer.jsp" />

<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>
