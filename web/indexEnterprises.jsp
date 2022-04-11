<%-- 
    Document   : indexEnterprises
    Created on : 12/10/2021, 11:14:03 AM
    Author     : Admin
--%>

<%
    if (session.getAttribute("enterprise") != null) {
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="layout/header.jsp" />
<!--Mostramos una pequeña informacion para el index de las empresas-->
<main class="mainUsersIndex">

<h1>Bajo las espectativas esperadas ahora eres una empresa, bienvenido a Weos</h1>
<h2 class="Ques">¿Qué deseas hacer?</h2>
<section class="main-index-empresa">
    <p class="inf-empresa">La realización de eventos y la certificación de los datos están dados bajo su criterio de honestidad frente a nuestra entidad, 
        por ello es necesario aclararle que nuestra empresa está netamente asociada con la realización de las cotizaciones entre 
        los usuarios y las empresas, a fin de facilitar la comunicación entre los mismos, asi que, esperamos que este servicio 
        sea de gran ayuda tanto para ustedes como para sus usuarios.</p>
</section>

</main>
<jsp:include page="layout/footer.jsp" />



<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>

