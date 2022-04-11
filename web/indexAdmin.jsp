<%-- 
    Document   : indexAdmin
    Created on : 12/10/2021, 09:18:33 AM
    Author     : Admin
--%>

<%
    if (session.getAttribute("admin") != null) {
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" />

<!--Mostramos una pequeña informacion para el index de los administradores-->
<main class="mainUsersIndex">
    <h1>Bienvenido, ${admin.nombre}</h1>
    <section class="infoUsersIndex">

        <div class="infoContainer">
            <h2 class="Ques">Quienes somos?</h2>
            <section class="main-index-empresa">
                <p class="inf-empresa">WEOS es una compañia en pro de los negocios, 
                    y gran intermediario entre las organizadoras de eventos
                    y sus clientes con el fin de ofrecer siempre las mejores experiencias</p>
            </section>
        </div>

    </section>

    <h2>${msgBackup}</h2>

</main>
<jsp:include page="layout/footer.jsp" />



<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>
