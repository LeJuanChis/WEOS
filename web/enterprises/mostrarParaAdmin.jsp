<%-- 
    Document   : mostrarParaEmpresas
    Created on : 17/11/2021, 09:52:38 AM
    Author     : Juan Carlos Osorio
--%>
<!--Verificamos la sesion-->
<%
    if (session.getAttribute("admin") != null) {
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <jsp:include page="../layout/header.jsp" />

        <h2 style="text-align: center;">Todas las empresas asociadasa nuestra pagina</h2>
        <div class="datatable-container">

            <div class="buscar">
                <input id="searchTerm" type="text" onkeyup="doSearch()" class="search-input" placeholder="Buscar">
            </div>




            <table id="datos" class="datatable">
                <thead>
                    <tr>
                        <th>NIT de la empresa</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>Telefono</th>
                        <th>Descripcion</th>
                        <th>Avatar</th>
                    </tr>

                </thead>

                <tbody>
                    <!--Creamos el bucle y mostramos todas las empresas-->
                    <c:forEach var="emp" items="${empresas}">

                        <tr>
                            <td>${emp.getNITempresa()}</td>
                            <td>${emp.getNombre()}</td>
                            <td>${emp.getCorreo()}</td>
                            <td>${emp.getTelefono()}</td>
                            <td>${emp.getDescripcion()}</td>
                            <td><img src="./avatarEnterprises/${emp.getAvatar()}" width="100px" height="100px" alt="Esta empresa no tiene imagen"></td>
                        </tr>

                    </c:forEach>



                </tbody>

            </table>

        </div>


<script src="./assets/js/buscar.js"></script>
        <jsp:include page="../layout/footer.jsp" />
        
        <%
    } else {
        request.setAttribute("msgLogin", "Debe loguearse en la pagina para ingresar a ella");
        response.sendRedirect("./users/login.jsp");
    }
%>

        
