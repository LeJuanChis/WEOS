<%-- 
    Document   : actualizarProductos
    Created on : 15/11/2021, 07:47:56 PM
    Author     : Juan Carlos Osorio
--%>
<%
    if (session.getAttribute("enterprise") != null) {
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <jsp:include page="../layout/header.jsp" />
        <h2 class="tit">Tus productos y servicios</h2>
        <div class="datatable-container">


            <!--Llamamos la funcion del js-->
            <div class="buscar">
                <input id="searchTerm" type="text" onkeyup="doSearch()" class="search-input" placeholder="Buscar">
            </div>




            <table id="datos" class="datatable">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Precio por unidad</th>
                        <th>Descripcion</th>
                        <th>Tipo</th>
                        <th>Acciones</th>
                    </tr>

                </thead>

                <tbody>
                    <!--Recorremos la lista y la mostramos-->
                <c:forEach var="prod" items="${productos}">

                    <tr>
                        <td>${prod.getNombre()}</td>
                        <td>${prod.getPrecioUnidad()}</td>
                        <td>${prod.getDescripcion()}</td>
                        <td>${prod.getTipo_productos_servicios()}</td>
                        <td>
                            <a href="#">Eliminar</a>
                            <a href="./ProductosServiciosController?opcion=verDetalleProducto&idProd=${prod.getCodigo()}&NITempresa=${enterprise.NITempresa}">Actualizar</a>
                        </td>
                    </tr>

                </c:forEach>



                </tbody>

            </table>
            <!--Mostramos los mensajes que nos llega desde el controller-->
            <h2>${msgEliminarProd}</h2>
            <h2>${msgActualizarProd}</h2>
        </div>
        <script src="./assets/js/busca.js"></script>
        <jsp:include page="../layout/footer.jsp" />
        <%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>


