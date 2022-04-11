<%-- 
    Document   : actualizar
    Created on : 14/11/2021, 05:50:30 PM
    Author     : Juan Carlos Osorio
--%>
<%
    if (session.getAttribute("user") != null) {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />
<h1>Mi perfil</h1>

<!--Mostramos los datos del usuario que nos llegan del controller-->
<form class="form-crearCuentaUsuario" method="POST" action="./userController?opcion=actualizar&id=${user.documento}">
    <div class="inputlabel-container">

        <label for="docIdentidad" class="doc">Documento</label>
        <input type="number" name="docIdentidad" value="${user.documento}" required="required" pattern="[0-9 ]+"><br><br>

        <label for="nombre" class="nom">Nombres</label>
        <input type="text" name="nombre" value="${user.nombre}" required="required" pattern="[a-zA-Z ]+"><br><br>

        <label for="apellido"  class="ape">Apellidos</label>
        <input type="text" name="apellidos" value="${user.apellidos}" required="required" pattern="[a-zA-Z ]+"><br><br>

        <label for="telefono" class="tel" >Teléfono</label>
        <input type="number" name="telefono" value="${user.telefono}" required="required" pattern="[0-9 ]+"><br><br>

        <label for="correo" class="cor">Email</label>
        <input type="email" name="correo" value="${user.correo}" required="required"><br><br>

        <label for="contrasena" class="con">Contraseña (Esta cifrada y no se puede cambiar para proteger tus datos)</label>
        <input type="text" name="pass" value="${user.contraseña}" required="required" readonly=""><br><br>

        <label for="fechaNac" class="fenac">Fecha de nacimiento</label>
        <input type="date" name="fechaNac" readonly="" value="${user.fecha_nacimiento}"><br><br>

        <div class="btnCrearCuenta-container">
            <input type="submit" class="btnEnviar" value="Actualizar datos">
        </div>
    </div>
</form>
<h3>${msgInsertar}</h3>
<script src="./assets/js/main.js"></script>

<jsp:include page="../layout/footer.jsp" />
<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>



