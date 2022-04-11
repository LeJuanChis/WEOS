<%
    if (session.getAttribute("user") != null) {
%>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />
<div class="datatable-container">


    <div class="search">
        <input type="text" name="" class="search-input">
    </div>




    <table id="datatable" class="datatable">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Precio por unidad</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Total</th>
                <th>Acciones</th>
            </tr>

        </thead>

        <tbody>
            <!--Creamos el bucle y mostramos los elementos del carrito-->

            <c:forEach var="car" items="${carrito}">

                <tr>
                    <td>${car.getNombre()}</td>
                    <td>    <div id="prueba" onload="numberSet()" >${car.getPrecio()}</div></td>
                    <td>${car.getDescripcion()}</td>
                    <td>
                        <!--Envio los parametros al JS para cambiar la cantidad automaticamente-->
                        <input type="hidden" id="idProd" value="${car.getIdProducto()}">
                        <input type="hidden" id="NITempresa" value="${NITempresa}">
                        <input type="number" id="cantidad" value="${car.getCantidad()}" min="1">
                    </td>
                    <td class="">${car.getTotal()}</td>
                    <td>
                        <!--Elimino un producto-->
                        <a href="./ProductosServiciosController?opcion=eliminarProductoCarrito&idProd=${car.getIdProducto()}&NITempresa=${NITempresa}">Eliminar</a>
                    </td>
                </tr>

            </c:forEach>



        </tbody>

    </table>


    <div class="options">
        <!--Volvemos a ver los productos de la empresa-->
        <a href="./ProductosServiciosController?opcion=ver&NITempresa=${NITempresa}">Volver a los productos</a>

        <!--Verificamos si la cotizacion se inserto y mostramos las opciones del pdf y del correo-->
        <% if (request.getAttribute("confirmacion") != null) { %>

        <a href="./ProductosServiciosController?opcion=enviarMail&NITempresa=${NITempresa}&idUser=${user.documento}">Enviar correo a la empresa con los datos de la cotizacion</a>
        <a href="./reportes?NITempresa=${NITempresa}&idUser=${user.documento}">Descargar un pdf con los datos</a>
        <% }%>

        <!--Realizamos la cotizacion-->
        <a href="./ProductosServiciosController?opcion=cotizar&NITempresa=${NITempresa}&idUser=${user.documento}">Realizar cotizacion</a>

    </div>
    <div class="card-products-car">
        <!--Mostramos el total de la cotizacion-->
        <label>Total cotizacion: </label>
        <input type="text" value="${cotizacion}COP" readonly="">
    </div>






    <h2>${msgInsertCoti}</h2>
    <h2>${msgInsertPsc}</h2>
    <h2>${confirmacionCor}</h2>



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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="./assets/js/function.js"></script>

<script src="./assets/js/number.js">

</script>

<jsp:include page="../layout/footer.jsp" />
<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>
