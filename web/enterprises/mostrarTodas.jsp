<%-- 
    Document   : mostrarTodas
    Created on : 15/11/2021, 08:45:45 AM
    Author     : Juan Carlos Osorio
--%>

<%@page import="java.util.List"%>
<%@page import="models.Enterprise"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />
<h1>Todas las empresas asociadas a la pagina</h1>

<div class="container">
    
    <!--Recibimos la lista-->

    <%
        List<Enterprise> allEnterprises = (List<Enterprise>) request.getAttribute("empresas");
    %>
    <!--Recorremos la lista y mostramos la info-->
    <% for (Enterprise enterpris : allEnterprises) {
            out.print("<div class='card'>");
            if (enterpris.getAvatar() != null) {
                out.print("<img src='avatarEnterprises/" + enterpris.getAvatar() + "'>");
            } else {
                out.print("<img src='assets/img/img3.jpg'>");
            }

            out.print("<h4>" + enterpris.getNombre() + "</h4>");

            out.print("<p maxlength='10'>" + enterpris.getDescripcion() + "</p>");
            out.print("<a href='enterpriseController?opcion=detalle&id=" + enterpris.getNITempresa() + "'>Leer mas</a>");
            out.print("</div>");
        }%>
</div>


<jsp:include page="../layout/footer.jsp" />


