<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--Llamamos las librerias que necesitamos y creamos el header para llamarlo en nuestras paginas jsp-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.2.0/css/all.css">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="js/jquery.dataTables.min.js"></script>
        <title>WEOS</title>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link href="./assets/css/style-detail.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <header class="encabezado">
            <nav class="navegacion">
                <div>               
                    <img src="./assets/img/logo.png" alt="">
                </div>     
                <!--Creamos el menu de navegacion con la verificacion de las sesiones de los distintos tips de roles-->
                <ul class="menu">
                    <% if (session.getAttribute("user") != null) { %>
                    <li><a href="./index1.jsp">Inicio</a></li>
                    <li><a href="./enterpriseController?opcion=listarTodas">Empresas</a></li> 
                    <li><a href="./prod_serv_cotiController?opcion=verCotizacion&docIdentidad=${user.documento}">Mis cotizaciones</a></li> 
                    <li><a href="./userController?opcion=verPerfil&id=${user.documento}">Perfil</a></li> 
                    <li><a href="./userController?opcion=crearCuentaEmpresa">Crear Cuenta como empresa</a>
                    <li><a href="./userController?opcion=cerrarSesion">Cerrar Sesion</a></li>

                    <% } else if (session.getAttribute("admin") != null) { %>
                    <li><a href="./index1.jsp">Inicio</a></li>
                    <li><a href="./adminController?opcion=verUsuarios">Gestionar usuarios</a></li> 
                    <li><a href="./adminController?opcion=verEmpresas">Gestionar empresas</a></li> 
                    <li><a href="./adminController?opcion=crearBackup">Crear copia de seguridad de la database</a></li>
                    <li><a href="./userController?opcion=cerrarSesion">Cerrar Sesion</a></li>

                    <% } else if (session.getAttribute("enterprise") != null) { %>
                    <li><a href="./index1.jsp">Inicio</a></li>
                    <li><a href="./enterpriseController?opcion=verPerfil&NITempresa=${enterprise.NITempresa}">Perfil</a></li> 
                    <li><a href="./enterpriseController?opcion=verProductos&NITempresa=${enterprise.NITempresa}">Mis productos</a></li> 
                    <li><a href="./enterpriseController?opcion=insertarProductos">Subir datos de contratacion</a></li> 
                    <li><a href="./prod_serv_cotiController?opcion=verCotizacionesUsuario&NITempresa=${enterprise.NITempresa}">Ver las cotizaciones</a></li>     
                    <li><a href="./userController?opcion=cerrarSesion">Cerrar Sesion</a></li>
                        <% } else { %>
                    <li><a href="./index1.jsp">Inicio</a></li>
                    <li><a href="./enterpriseController?opcion=listarTodas">Empresas</a></li> 
                    <li><a href="./userController?opcion=registrar">Crear cuenta</a></li>           
                    <li><a href="./userController?opcion=login">Iniciar sesion</a></li>  

                    <% }%>                                   
                    <li><a href="#"><i class="fas fa-search"></i></a></li>                            
                    <li><a href="#"><i class="fas fa-sort-down"></i></a>
                        <ul class="submenu">
                            <li><a href="#">cumpleaños</a></li>
                            <li><a href="#">bebyshower</a></li>
                            <li><a href="#">birth Day</a></li>
                            <li><a href="#">quinces</a></li>
                            <li><a href="#">bodas</a></li>
                            <li><a href="#">picni</a></li>
                            <li><a href="#">otras...</a></li>
                        </ul>
                    </li>           
                </ul>
            </nav>
        </header>

