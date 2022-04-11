<%-- 
    Document   : insertarProductosServicios
    Created on : 19/10/2021, 11:55:18 AM
    Author     : USUARIO
--%>
<%
    if (session.getAttribute("enterprise") != null) {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />
<h1>Insertar un producto o servicio</h1>
<!--Insertamos los productos y servicios de la cotizacion-->
<form class="form-subirDatos" method="POST" action="./ProductosServiciosController?opcion=insertar">
    <div class="inputlabel-container">
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" required="">

        <label for="precioU">Precio por unidad:</label>
        <input type="number" name="precioUnidad" required="">

        <label for="descripcion">Descripcion:</label>
        <textarea name="descripcion" required="">
                
        </textarea>

        <label for="tipo">Tipo (producto o servicio)</label>
        <select name="tipo_producto_servicio" required="">
            <option selected="selected" value="producto" name="tipo_producto_servicio">Producto</option>
            <option value="servicio" name="tipo_producto_servicio">Servicio</option>
        </select>

        <label for="NITempresa">NIT de la empresa</label>
        <input type="text" name="NITempresa" value="${enterprise.NITempresa}"><!--Enviamos el NIT de la empresa para asociar el producto a la empresa-->


        <input type="submit" class="btnEnviar enviar-datos" value="Enviar">

    </div>
</form>
        <h2>${msgInsertarProducto}</h2>

<jsp:include page="../layout/footer.jsp" />
<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>
