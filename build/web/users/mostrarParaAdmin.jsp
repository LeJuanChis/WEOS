<%-- 
    Document   : mostrarParaEmpresas
    Created on : 17/11/2021, 09:52:38 AM
    Author     : Juan Carlos Osorio
--%>
<%
    if (session.getAttribute("admin") != null) {
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />

<h2 style="text-align: center;">Usuarios que tienen empresas asociadas</h2>
<div class="datatable-container">


    <div class="buscar">
        <input id="searchTerm" type="text" onkeyup="doSearch()" class="search-input" placeholder="Buscar">
    </div>


    <table id="datos" class="datatable">
        <thead>
            <tr>
                <th>Documento del usuario</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Telefono</th>
                <th>Correo</th>
                <th>Fecha de nacimiento</th>
                <th>Rol</th>
                <th>NIT de la empresa a la que esta asociado</th>
            </tr>

        </thead>

        <tbody>
            <!--Mostramos todos los usuarios que tienen empresas al admin-->
            <c:forEach var="usu" items="${usuariosEnterp}">

                <tr>
                    <td>${usu.getDocumento()}</td>
                    <td>${usu.getNombre()}</td>
                    <td>${usu.getApellidos()}</td>
                    <td>${usu.getTelefono()}</td>
                    <td>${usu.getCorreo()}</td>
                    <td>${usu.getFecha_nacimiento()}</td>
                    <td>${usu.getRoles().getNombreRol()}</td>
                    <td>${usu.getEnterprise().getNITempresa()}</td>
                </tr>

            </c:forEach>



        </tbody>

    </table>

</div>

<h2 style="text-align: center;">Usuarios normales</h2>

<div class="datatable-container">

    <div class="buscar">
        <input id="searchTerm" type="text" onkeyup="doSearch()" class="search-input" placeholder="Buscar">
    </div>

    <table id="datos" class="datatable">
        <thead>
            <tr>
                <th>Documento del usuario</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Telefono</th>
                <th>Correo</th>
                <th>Fecha de nacimiento</th>
                <th>Rol</th>
            </tr>

        </thead>

        <tbody>
            <!--Mostramos todos los usuarios normales al admin-->

            <c:forEach var="usun" items="${usuariosNorm}">

                <tr>
                    <td>${usun.getDocumento()}</td>
                    <td>${usun.getNombre()}</td>
                    <td>${usun.getApellidos()}</td>
                    <td>${usun.getTelefono()}</td>
                    <td>${usun.getCorreo()}</td>
                    <td>${usun.getFecha_nacimiento()}</td>
                    <td>${usun.getRoles().getNombreRol()}</td>
                </tr>

            </c:forEach>



        </tbody>

    </table>

</div>



<h2 style="text-align: center;">Administradores</h2>

<div class="datatable-container">


    <div class="buscar">
        <input id="searchTerm" type="text" onkeyup="doSearch()" class="search-input" placeholder="Buscar">
    </div>




    <table id="datos" class="datatable">
        <thead>
            <tr>
                <th>Documento del usuario</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Telefono</th>
                <th>Correo</th>
                <th>Fecha de nacimiento</th>
                <th>Rol</th>
            </tr>

        </thead>

        <tbody>
            <!--Mostramos todos los administradores al admin-->
            <c:forEach var="usua" items="${usuariosAdmin}">

                <tr>
                    <td>${usua.getDocumento()}</td>
                    <td>${usua.getNombre()}</td>
                    <td>${usua.getApellidos()}</td>
                    <td>${usua.getTelefono()}</td>
                    <td>${usua.getCorreo()}</td>
                    <td>${usua.getFecha_nacimiento()}</td>
                    <td>${usua.getRoles().getNombreRol()}</td>
                </tr>

            </c:forEach>



        </tbody>

    </table>

</div>


<jsp:include page="../layout/footer.jsp" />

<script src="./assets/js/buscar.js"></script>
<%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>
