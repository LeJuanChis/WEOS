<%-- 
    Document   : verPorEmpresa
    Created on : 4/11/2021, 08:42:20 AM
    Author     : Juan Carlos Osorio
--%>
<%
    if (session.getAttribute("user") != null) {
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="models.ProductoServicios"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />

<div class="datatable-container">

    <h2 class="tit">Productos y servicios que ofrece esta empresa</h2>
    <div class="buscar">
        <input id="searchTerm" type="text" onkeyup="doSearch()" class="search-input" placeholder="Buscar">
    </div>





    <table id="datos" class="datatable">
        <thead class="tr">
            <tr>
                <th>Agregar al carrito</th><!--Agregamos al carrito-->
                <th>Nombre</th>
                <th>Precio por unidad</th>
                <th class="tDdescription">Descripcion</th>
                <th>Tipo</th>
            </tr>

        </thead>

        <tbody>


            <!--Hacemos el bucle y mostramos todos los productos de una empresa-->
            <c:forEach var="p" items="${productos}">
                <tr>
                    <td><a href='./ProductosServiciosController?opcion=agregarCarrito&id=${p.getCodigo()}&NITempresa=${NITempresa}'>Agregar a la cotizacion</a></td>
                    <td>${p.getNombre()}</td>
                    <td>${p.getPrecioUnidad()}</td>
                    <td>${p.getDescripcion()}</td>
                    <td>${p.getTipo_productos_servicios()}</td>
                </tr>
            </c:forEach>



        </tbody>

    </table>
    <!--Mostramos el numero d productos que el usuario ha metido en la cotizacion-->
    <span class="contador">Numero de productos en la cotizacion: (${contador})</span>           
    <a href="./ProductosServiciosController?opcion=carrito&NITempresa=${NITempresa}" class="verCotizacion">Ver la cotizacion</a>






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
<!--
        <script src="./assets/js/tables.js">

        </script>             

        <script>
            const dt = new DataTable('#datatable');

            dt.parse()

        </script>-->
<script src="./assets/js/busca.js"></script>
<jsp:include page="../layout/footer.jsp" />
<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>
