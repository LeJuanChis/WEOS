<%-- 
    Document   : ayuda
    Created on : 17/11/2021, 01:26:11 PM
    Author     : Juan Carlos Osorio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <jsp:include page="../layout/header.jsp" />
    <center>
        <div class="padre">
            <div class="hijo"><h1>¿Tienes alguna pregunta?</h1>
                <input  type="search"  id="inputBusqueda" size="30"> <i class="fas fa-search"></i>
                <div class="search" id="search">
                    <table class="search-table" id="searchTable">
                        <thead>
                            <tr>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><a href="#p1">No me permite ingresar a mi usuario
                                    </a></td>
                            </tr>
                            <tr>
                                <td><a href="#p2">No me carga las paguinas de las empresas</a></td></a>
                            </tr>
                            <tr>
                                <td><a href="#">¿Donde pudo encontrar las empersas?</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Contacto</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Curso Html</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Nuevo Articulo</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Curso de CSS</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Curso Javascript</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Curso Jquery</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Curso Ajax</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Curso PHP</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Curso Angular Js</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Curso Angular 2</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Mas cursos</a></td>
                            </tr>
                            <tr>
                                <td><a href="#">Noticias</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>       
            </div>
        </div>
    </center> 
    <div class="respuestas">
        <h1>Respuestas</h1>
        <h3 id="p1">No me permite ingresar a mi usuario</h3>
        <p id="p1">-Verifica que tu contraseña o correo sean correctos.
        <p id="p1"> -Ya revisaste tus datos, los cuales deben ser corresctos.</p> 
        <h3 id="p2">No me carga las paguinas de las empresas</h3>
        <p id="p1"></p> -Si a un no te permite ingresar dirigete a nuestro apartado de recuperacion de contraseña</p>
    <p id="p2">-Tal vez tienes una conexion muy lenta</p>
    <p id="p2">-Recarga la pagina</p>
    <h3 id="p3">¿Donde pudo encontrar las empersas?</h3>




</div>
<script src="./assets/js/scr.js"></script>
<jsp:include page="../layout/footer.jsp" />

