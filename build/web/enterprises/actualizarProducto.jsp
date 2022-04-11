<%-- 
    Document   : actualizarProducto
    Created on : 15/11/2021, 08:27:02 PM
    Author     : Juan Carlos Osorio
--%>
<!--Verificamos la sesion-->
<%
    if (session.getAttribute("enterprise") != null) {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <jsp:include page="../layout/header.jsp" />
        <h1>Insertar un producto o servicio</h1>
        <!--Ponemos los datos del producto a actualizar-->
        <form class="form-subirDatos" method="POST" action="./ProductosServiciosController?opcion=actualizarProducto&codigoProd=${producto.codigo}&NITempresa=${enterprise.NITempresa}">
            <div class="inputlabel-container">
                <label for="nombre">Nombre:</label>
                <input type="text" name="nombre" value="${producto.nombre}">

                <label for="precioU">Precio por unidad:</label>
                <input type="number" name="precioUnidad" value="${producto.precioUnidad}"> 

                <label for="descripcion">Descripcion:</label>
                <textarea name="descripcion">
                    ${producto.descripcion}
                </textarea>

                <label for="tipo">Tipo (producto o servicio)</label>
                <select name="tipo_producto_servicio">
                    <option selected="selected" value="${producto.tipo_productos_servicios}" name="tipo_producto_servicio">Producto</option>
                </select>

                <label for="NITempresa">NIT de la empresa</label>
                <input type="text" name="NITempresa" value="${enterprise.NITempresa}">


                <input type="submit" class="btnEnviar enviar-datos" value="Actualizar">

            </div>
        </form>

        <jsp:include page="../layout/footer.jsp" />

<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>
