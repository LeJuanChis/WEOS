<%-- 
    Document   : footer
    Created on : 12/10/2021, 09:07:15 AM
    Author     : Admin
--%>
<!--Creamos el footer para luego incluirlo en las paginas jsp-->

<footer class="footer">
    <h1>WEOS</h1>

    <div class="help-container">
        <a href="./userController?opcion=ayuda">
            <i class="fal fa-user-headset"></i>
            <p>¿Ayuda?</p>
        </a>


    </div>


    <% if (session.getAttribute("user") != null) { %>
    <a href="./userController?opcion=pqrs">
        <p>Quejas, opiniones o reclamos</p>
    </a>

    <% }%>

    <!--Cerramos el documento html-->
</footer>
</body>
</html>
