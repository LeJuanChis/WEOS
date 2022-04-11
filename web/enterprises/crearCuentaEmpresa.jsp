<%-- 
    Document   : crearCuentaEmpresa
    Created on : 7/10/2021, 06:05:34 PM
    Author     : USUARIO
--%>
<!--Verificar la sesion-->
<%
    if (session.getAttribute("user") != null) {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />


<h1>"REGISTRATE Y CONOCE NUESTRO MUNDO DE EVENTOS"</h1>

<form class="form-crearCuentaEmpresa" method="POST" action="./enterpriseController?opcion=insertarEmpresa" enctype="multipart/form-data">
    <div class="inputlabel-containerEmpresa">

        <label for="nit" class="nitemp">Nit</label>
        <input type="number" name="NITempresa"><br><br>

        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" ><br><br>

        <label for="correo" >Email</label>
        <input type="email" name="correo" ><br><br>

        <label for="telefono" >Teléfono</label>
        <input type="text" name="telefono"><br><br>

        <label for="descripcion">Descripción</label>
        <input type="text" name="descripcion"><br><br>    

        <label for="usuario">Usuario</label>
        <input type="text" name="usuario" value="${user.documento}"><br><br> <!--Usamos el documento del usuario para asignarlo a una empresa-->

        <label for="avatar" class="cor">Avatar</label>
        <input type="file" name="avatar" ><br><br>

        <label for="link_facebook">Link Facebook</label>
        <input type="text" name="link_facebook" ><br><br>

        <label for="link_instagram">Link Instagram</label>
        <input type="text" name="link_instagram" ><br><br>

        <label for="link_youtube">Link Youtube</label>
        <input type="text" name="link_youtube" ><br><br>

        <label for="link_twitter">Link Twitter</label>
        <input type="text" name="link_twitter" ><br><br>

        <label for="link_whatsapp">Link Whatsapp</label>
        <input type="text" name="link_whatsapp" ><br><br>


        <div class="btnCrearCuenta-container">

            <div class="btn-crearCuentaEmpresa">
                <input type="submit" class="btnEnviar" value="Enviar">
            </div>

        </div>
    </div>
</form>
        
        <h2>${msgInsertarEmpresa}</h2>



<jsp:include page="../layout/footer.jsp" />

<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>


