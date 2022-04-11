<%-- 
    Document   : indexUsers
    Created on : 11/10/2021, 08:07:25 PM
    Author     : Admin
--%>
<%
    if (session.getAttribute("user") != null) {
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" />
<!--Mostramos una pequeña informacion para el index de los usuarios-->
<main class="mainUsersIndex">
    <h1>Bienvenido, ${user.nombre}</h1>
    <section class="infoUsersIndex">


        <div class="infoContainer">
            <h2 class="Ques">Que deseas hacer?</h2>
            <section class="main-index-empresa">
                <p class="inf-empresa">Puedes contratar con las diferentes empresas y ademas, conocer mas 
                    sobre ellas, y complementar toda la informacion que desees</p>
            </section>
        </div>
        <div class="infoContainer">
            <h2 class="Ques">Quienes somos?</h2>
            <section class="main-index-empresa">
                <p class="inf-empresa">WEOS es una compañia en pro de los negocios, 
                    y gran intermediario entre las organizadoras de eventos
                    y sus clientes con el fin de ofrecer siempre las mejores experiencias</p>
            </section>
        </div>
    </section>
</main>



<jsp:include page="layout/footer.jsp" />
<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>


