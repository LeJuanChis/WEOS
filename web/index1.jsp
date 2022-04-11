<%-- 
    Document   : index1
    Created on : 30/08/2021, 01:45:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Enterprise"%>
<%@page import="controllers.enterpriseController"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>

<jsp:include page="layout/header.jsp" />

<!--Llamamos al controlador direactamente para mostrar de buenas a primeras tres empresas aleatorias-->
<% enterpriseController enterprise = new enterpriseController();
    enterprise.mostrarTodas(request, response);
    List<Enterprise> allEnterprises = (List<Enterprise>) request.getAttribute("empresas");
%>

<h2>${msgInsertarEmpresa}</h2>
<h1 class="txt1">COTIZACION DE EVENTOS</h1>
<!-- slideR -->
<div class="slide-contenedor">

    <!--Mostramos las imagenes de las empresas aleatorias que llamamos-->
    <% for (Enterprise enterp : allEnterprises) {
            out.print("<div class='miSlider fade'>");

            if (enterp.getAvatar() != null) {
                out.print("<img src='avatarEnterprises/" + enterp.getAvatar() + "'>");
            } else {
                out.print("<img src='assets/img/img3.jpg'>");
            }
            out.print("</div>");
        } %>
        <!--Le damos la arquitectura al slider-->
    <div class="direcciones">
        <a href="#" class="atras" onclick="avanzaSlide(-1)">&#10094;</a>
        <a href="#" class="adelante" onclick="avanzaSlide(1)">&#10095;</a>
    </div>
    <div class="barras">
        <span class="barra active" onclick="posicionSlide(1)"></span>
        <span class="barra" onclick="posicionSlide(2)"></span>
        <span class="barra" onclick="posicionSlide(3)"></span>
    </div>
</div>
<!-- targetas -->
<h1 class="title"></h1>

<div class="container">
    <!--creamos el bucle y mostramos las 3 empresas aleatoriamente-->

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


<h1 class="f">"Los lugares mas populares para ralizar evententos"</h1>
<div class="imagelugares">
    <img src="assets/img/img1.jpg">
    <img src="assets/img/img1.jpg">
    <img src="assets/img/img1.jpg">
    <img src="assets/img/img1.jpg">
</div>
<script src="assets/js/main.js"></script>
<jsp:include page="layout/footer.jsp" />


