<%-- 
    Document   : insertarPqrs
    Created on : 17/11/2021, 12:20:57 PM
    Author     : Juan Carlos Osorio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--Creamos el formulario para insertar las quejas, opiniones o comentarios-->
        <jsp:include page="../layout/header.jsp" />
        <header>
            <h1 class="text">Centro de sugerencias</h1>
        </header>
        <article class="ns">
            <section class="main">
                <form class="form" action="./pqrsController?opcion=insertarPqrs" method="post">
                    <input type="hidden" value="${user.documento}" name="docIdentidad_usuario"><!--Enviamos el documento del usuario -->
                    <input class="nombre"  type="text" name="descripcion" id="des"  placeholder="Espacio para escribir tu comentario :)" >
                    <input class="btn" type="submit" name="registrar" value="Enviar"> 
                </form>
            </section>
            <section class="info">
                <div class="logoContainer">
                    <img class="logoPqrs" src="./files/logo.png" alt="">
                </div>
                <div class="p">
                    <h2>WEOS  es una página intemedaria entre empresas organizadoras de eventos y clientes que deseen realizar eventos y el usuario puede mirar y escoger la de su preferencia sin necesidad de overse de casa, ya que la misma pagina puede chatear directamente con el administrador de la organizadora escogida.</h2  >
                </div>
            </section>
        </article>
        
        <h2>${msgPqrs}</h2>

        <jsp:include page="../layout/footer.jsp" />