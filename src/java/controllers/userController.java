/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Enterprise;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "userController", urlPatterns = {"/userController"})
public class userController extends HttpServlet {

    private User user = new User();
    private Enterprise enterp = new Enterprise();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String opcion = request.getParameter("opcion");

            switch (opcion) {

                case "ver":
                    this.ver(request, response);
                    break;

                case "insertar":
                    this.insertar(request, response);
                    break;

                case "registrar":
                    request.getRequestDispatcher("users/insertarUsuario.jsp").forward(request, response);
                    break;
                case "login":
                    request.getRequestDispatcher("users/login.jsp").forward(request, response);
                    break;

                case "loginTrue":
                    this.login(request, response);
                    break;

                case "cerrarSesion":
                    this.cerrarSesion(request, response);
                    break;

                case "verPerfil":
                    //obtenemos un usuario por id y lo enviamos a otro lugar
                    int documento = Integer.parseInt(request.getParameter("id"));
                    User usuario = user.seleccionarUnUsuario(documento);

                    request.setAttribute("user", usuario);
                    request.getRequestDispatcher("users/actualizar.jsp").forward(request, response);
                    break;

                case "actualizar":
                    int documentoUser = Integer.parseInt(request.getParameter("id"));
                    this.actualizar(request, response, documentoUser);
                    break;

                case "crearCuentaEmpresa":
                    request.getRequestDispatcher("enterprises/crearCuentaEmpresa.jsp").forward(request, response);
                    break;

                case "pqrs":
                    request.getRequestDispatcher("pqrs/insertarPqrs.jsp").forward(request, response);
                    break;

                case "ayuda":
                    request.getRequestDispatcher("ayuda/ayuda.jsp").forward(request, response);
                    break;
            }
        }
    }

    public void insertar(HttpServletRequest request, HttpServletResponse response) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException, NoSuchAlgorithmException {

        try {

            String docIdentidad = request.getParameter("docIdentidad");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String pass = request.getParameter("pass");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String avatar = request.getParameter("avatar");
            String fechaNac = request.getParameter("fechaNac");

            String errores[];
            //verificamos los datos
            boolean error = true;
            if (docIdentidad.length() == 0) {
                error = false;
            } else if (nombre.length() == 0) {
                error = false;
            } else if (apellidos.length() == 0) {
                error = false;
            } else if (pass.length() == 0) {
                error = false;
            } else if (telefono.length() == 0) {
                error = false;
            } else if (correo.length() == 0) {
                error = false;
            } else if (fechaNac.length() == 0) {
                error = false;
            }

            if (error == true) {

                //instanciar las clases
                //setear los datos
                user.setDocumento(docIdentidad);
                user.setNombre(nombre);
                user.setApellidos(apellidos);
                user.setContraseña(pass);
                user.setTelefono(telefono);
                user.setCorreo(correo);
                user.setAvatar(avatar);
                user.setFecha_nacimiento(fechaNac);
                //llamamos al conectar
                //insertamos los datos
                user.conectar();
                boolean estado1 = user.crearCuenta(user);
                //verificamos si se inserto con exito
                if (estado1) {
                    request.setAttribute("msgInsertar", "La insercion se realizo con exito");
                } else {
                    request.setAttribute("msgInsertar", "La insercion NO se pudo realizar, por favor intente de nuevo");
                }
            } else {
                request.setAttribute("msgInsertar", "La insercion NO se pudo realizar, por favor intente de nuevo");
            }
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("msgInsertar", "La insercion NO se pudo realizar, por favor intente de nuevo");
        }

        request.getRequestDispatcher("/users/insertarUsuario.jsp").forward(request, response);
    }

    public boolean ver(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, InstantiationException, IllegalAccessException {
        user.conectar();
        List<User> lista = user.getAll();
        request.setAttribute("allUsers", lista);//llamamos a todos los usuarios y los enviamos a la vista
        request.getRequestDispatcher("/users/listarUsuarios.jsp").forward(request, response);
        return true;

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {
        try {
            HttpSession sesion;
            //creamos la variable de sesion

            String correo = request.getParameter("correo");
            String pass = request.getParameter("pass");//recibumos los parametros
            user.conectar();
            user.setCorreo(correo);//seteamos los parametros
            user.setContraseña(pass);
            user = user.login(user);

            //para obtener la sesion de las empresas
            int documento = Integer.parseInt(user.getDocumento());
            enterp = enterp.seleccionarParaUsuarios(documento);

            if (user != null && user.getRoles().getNombreRol().equals("Admin")) {

                sesion = request.getSession();
                sesion.setAttribute("admin", user);//manipulamos la variable de sesion
                request.getRequestDispatcher("./indexAdmin.jsp").forward(request, response);

            } else if (user != null && user.getRoles().getNombreRol().equals("Usuario")) {
                sesion = request.getSession();

                sesion.setAttribute("user", user);//manipulamos la variable de sesion
                /*this.getServletConfig().getServletContext()*/
                request.getRequestDispatcher("./indexUsers.jsp").forward(request, response);

            } else if (user != null && user.getRoles().getNombreRol().equals("Empresa")) {
                sesion = request.getSession();

                System.out.println(enterp.NITempresa);
                sesion.setAttribute("enterprise", enterp);//manipulamos la variable de sesion
                request.getRequestDispatcher("./indexEnterprises.jsp").forward(request, response);

            } else {
                request.setAttribute("msgLogin", "Los datos son incorrectos, por favor revisa de nuevo!!");

            }
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("msgLogin", "Los datos son incorrectos");
        }
        request.getRequestDispatcher("/users/login.jsp").forward(request, response);

    }

    public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession sesion = request.getSession();
        sesion.removeAttribute("admin");
        sesion.removeAttribute("enterprise");
        sesion.removeAttribute("user");//remover el atributo del nombre
        request.getRequestDispatcher("/index1.jsp").forward(request, response);
    }

    public boolean actualizar(HttpServletRequest request, HttpServletResponse response, int docIdent) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException, NoSuchAlgorithmException {
        //obtenemos los datos y los enviamos al actualizar
        String docIdentidad = request.getParameter("docIdentidad");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        user.setDocumento(docIdentidad);
        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setTelefono(telefono);
        user.setCorreo(correo);
        //insertamos los datos
        boolean estado1 = user.actualizarUsuario(user, docIdent);
        if (estado1) {
            request.setAttribute("msgInsertar", "Se actualizo el registro con exito");
        } else {
            request.setAttribute("msgInsertar", "No se pudo actualizar el registro, por favor intente de nuevo");
        }
        request.getRequestDispatcher("userController?opcion=verPerfil").forward(request, response);
        return estado1;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
