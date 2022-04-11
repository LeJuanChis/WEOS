<%-- 
    Document   : actualizar
    Created on : 14/11/2021, 04:26:34 PM
    Author     : Juan Carlos Osorio
--%>
<!--Creamos la condicion para verificar la sesion-->
<%
    if (session.getAttribute("enterprise") != null) {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />
<h1>Mi informacion</h1>
<!--Incluimos el header-->
<div class="container-info-enterp">

    <!--Ponemos los datos de las empresas-->
    <form class="form-crearCuentaUsuario actualizar" method="POST" action="./enterpriseController?opcion=actualizar&NITempresa=${empresa.NITempresa}">
        <h2>Informacion de la empresa</h2>
        <div class="inputlabel-container">

            <label for="NITempresa">NIT</label>
            <input type="number" name="NITempresa" required="required" value="${empresa.NITempresa}" readonly="" pattern="[0-9 ]+"><br><br>

            <label for="nombre">Nombre</label>
            <input type="text" name="nombre" required="required" value="${empresa.nombre}" pattern="[a-zA-Z ]+"><br><br>

            <label for="correo">Correo</label>
            <input type="Email" name="correo" value="${empresa.correo}" required="required"><br><br>

            <label for="telefono">Teléfono</label>
            <input type="number" name="telefono" value="${empresa.telefono}" required="required" pattern="[0-9 ]+"><br><br>

            <label for="descripcion" >Descripcion</label>
            <textarea name="descripcion" value="" required="required">
                ${empresa.descripcion}
            </textarea>



            <label for="link_facebook" >Link facebook</label>
            <input type="text" name="link_facebook" value="${empresa.link_facebook}" ><br><br>

            <label for="link_instagram" >Link instagram</label>
            <input type="text" name="link_instagram" value="${empresa.link_instagram}"><br><br>

            <label for="link_youtube" >Link youtube</label>
            <input type="text" name="link_youtube" value="${empresa.link_youtube}"><br><br>

            <label for="link_instagram" >Link twitter</label>
            <input type="text" name="link_twitter" value="${empresa.link_twitter}"><br><br>

            <label for="link_whatsapp" >Link whatsapp</label>
            <input type="text" name="link_whatsapp" value="${empresa.link_whatsapp}"><br><br>

            <div class="btnCrearCuenta-container">
                <input type="submit" class="btnEnviar" value="Actualizar datos">
            </div>
        </div>
    </form>


            <!--Ponemos los datos de los usuarios-->
    <form class="form-crearCuentaUsuario actualizar" method="POST" action="./enterpriseController?opcion=actualizarUsuario&id=${user.documento}&NITempresa=${empresa.NITempresa}">
        <h2>Informacion del usuario asociado a esta cuenta</h2>
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

            <label for="contrasena" class="con">Contraseña</label>
            <input type="text" name="pass" value="${user.contraseña}" required="required" readonly=""><br><br>

            <label for="fechaNac" class="fenac">Fecha de nacimiento</label>
            <input type="date" name="fechaNac" readonly="" value="${user.fecha_nacimiento}"><br><br>

            <div class="btnCrearCuenta-container">
                <input type="submit" class="btnEnviar" value="Actualizar datos">
            </div>
        </div>
    </form>
</div>


<h3>${msgInsertar}</h3>
<!--Incluimos el footer-->
<jsp:include page="../layout/footer.jsp" />

<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>

