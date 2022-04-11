<%-- 
    Document   : login
    Created on : 2/09/2021, 01:39:18 PM
    Author     : Valentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />
<main class="mainUsersIndex">
<h1>Login</h1>
<!--Creamos el formulario para el login-->
<div  class="login">
    <form method="POST" action="./userController?opcion=loginTrue">
        <div class="content">
            <label>Correo</label><br>
            <input type="email" name="correo"><br><br>
            <label>Contraseña</label><br>
            <input type="password" name="pass"><br><br>
        </div>

        <button>Enviar</button>
    </form>
    
    <h2>${msgLogin}</h2>

</div>
</main>
<jsp:include page="../layout/footer.jsp" />

