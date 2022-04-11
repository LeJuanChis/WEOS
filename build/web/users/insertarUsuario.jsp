<%-- 
    Document   : CRUDusuarios
    Created on : 5/07/2021, 09:47:57 AM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <jsp:include page="../layout/header.jsp" />
        <h1>"REGISTRATE Y CONOCE NUESTRO MUNDO DE EVENTOS"</h1>
        <!--Creamos el formulario para insertar el usuario-->
        <form class="form-crearCuentaUsuario" method="POST" action="./userController?opcion=insertar">
            <div class="inputlabel-container">

                <label for="docIdentidad" class="doc">Documento</label>
                <input type="number" name="docIdentidad" placeholder="Documento de identidad" required="required" pattern="[0-9 ]+"><br><br>

                <label for="nombre" class="nom">Nombres</label>
                <input type="text" name="nombre" placeholder="Nombre" required="required" pattern="[a-zA-Z ]+"><br><br>

                <label for="apellido"  class="ape">Apellidos</label>
                <input type="text" name="apellidos" placeholder="Apellidos" required="required" pattern="[a-zA-Z ]+"><br><br>

                <label for="telefono" class="tel" >Teléfono</label>
                <input type="number" name="telefono" placeholder="Telefono" required="required" pattern="[0-9 ]+"><br><br>

                <label for="correo" class="cor">Email</label>
                <input type="email" name="correo" placeholder="Correo" required="required"><br><br>


                
                <label for="contrasena" class="con">Contraseña</label>
                <input type="password" name="pass" placeholder="Contraseña" required="required"><br><br>

                <label for="fechaNac" class="fenac">Fecha de nacimiento</label>
                <input type="date" name="fechaNac" placeholder="Fecha de nacimiento"><br><br>

                <div class="btnCrearCuenta-container">
                        <input type="submit" class="btnEnviar" value="Enviar">
                </div>
            </div>
        </form>
        <h3>${msgInsertar}</h3>
        <script src="./assets/js/main.js"></script>

        <jsp:include page="../layout/footer.jsp" />