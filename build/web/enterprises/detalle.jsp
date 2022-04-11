<%-- 
    Document   : detalle
    Created on : 2/11/2021, 09:14:57 AM
    Author     : Juan Carlos Osorio
--%>


<%@page import="models.Enterprise"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <jsp:include page="../layout/header.jsp" />

        <div class="container-detail">
            <!--Obetnemos la empresa que nos llega desde el controller-->
            <div class="enterp-container">

                <div class="enterp-img">
                    <!--Verificamos si existe la imagen-->
                    <%
                    if(request.getAttribute("confirmacion") != null){
                    
                    %>
                    <img src='avatarEnterprises/${empresa.avatar}'>
                    <%}else{%>
                    <img src='./assets/img/img3.jpg'>
                    <%}%>
                    
                </div>
                <div class="enterp-info">
                    <h1>${empresa.nombre}</h1>
                    <p>"${empresa.descripcion}"</p>
                    <p class="enterp-email">Contacto: <span>${empresa.correo}</span></p>
                </div>
            </div>


            <div class="enterp-link">
                <div class="social-icon">
                    <a href="${empresa.link_facebook}"><i class="fab fa-facebook"></i></a>
                </div>

                <div class="social-icon">
                    <a href="${empresa.link_instagram}"><i class="fab fa-instagram-square"></i></a>
                </div>

                <div class="social-icon">
                    <a href="${empresa.link_youtube}"><i class="fab fa-youtube"></i></a>
                </div>

                <div class="social-icon">
                    <a href="${empresa.link_twitter}"><i class="fab fa-twitter"></i></a>
                </div>

                <div class="social-icon">
                    <a href="${empresa.link_whatsapp}"><i class="fab fa-whatsapp-square"></i></a>
                </div>

            </div>



        </div>
                
                <!--Verificamos si tiene una sesion activa para realizar la cotizacion-->
                    <% if(session.getAttribute("user") != null){ %>
                    <a class="btnSend" href="./ProductosServiciosController?opcion=ver&NITempresa=${empresa.NITempresa}">Realizar una cotizacion</a>
                    
                    <div class="products-detail" id="products">
                        
                        
                    </div>
                    
                    <%} else if(session.getAttribute("admin") != null || session.getAttribute("enterprise") != null){%>
                    <h2>Debes estar registrado como usuario para realizar una cotizacion</h2>
                    
                    <%} else{%>
                    <h2>Debes estar registrado en la pagina para realizar una cotizacion</h2>
                    
                    <%}%>

        


        <script src="./assets/js/buttons.js">
            
        </script>
                    
        <jsp:include page="../layout/footer.jsp" />
