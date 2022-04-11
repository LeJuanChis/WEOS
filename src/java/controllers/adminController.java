/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Enterprise;
import models.User;

/**
 *
 * @author Juan Carlos Osorio
 */
public class adminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String opcion = request.getParameter("opcion");

            switch (opcion) {

                case "crearBackup":

                    try {
                        Process p = Runtime
                                .getRuntime()
                                .exec("C:/wamp/bin/mysql/mysql5.7.31/bin/mysqldump -u root weos");//buscamos la base de datos

                        InputStream is = p.getInputStream();
                        FileOutputStream fos = new FileOutputStream("C:\\Users\\Janus\\Desktop\\weos\\WeosProject\\backup_WEOS.sql");//ponemos donde crearla
                        
                        byte[] buffer = new byte[1000];

                        int leido = is.read(buffer);
                        while (leido > 0) {
                            fos.write(buffer, 0, leido);
                            leido = is.read(buffer);

                        }
                        
                        fos.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("msgBackup", "El backup fue creado con exito, revisa la carpeta raiz del proyecto");
                        
                    request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);

                    break;

                case "verEmpresas":
                    this.mostrarTodasEmpresas(request, response);
                    break;

                case "verUsuarios":
                    this.mostrarTodosUsuarios(request, response);
                    break;
            }
        }
    }

    public void mostrarTodosUsuarios(HttpServletRequest request, HttpServletResponse response) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException {
        User user = new User();
        List<User> usuariosConEmpresas = user.listarTodosConEmpresa();//mostramos los usuarios con empresas
        List<User> usuariosNormales = user.listarTodosSinEmpresa();//mostramos los usuarios sin empresas
        List<User> administradores = user.listarTodosAdministradores();//mostramos los usuarios administradores
        //seteamos los atributos y los mandamos a la vista
        request.setAttribute("usuariosEnterp", usuariosConEmpresas);
        request.setAttribute("usuariosNorm", usuariosNormales);
        request.setAttribute("usuariosAdmin", administradores);
        request.getRequestDispatcher("./users/mostrarParaAdmin.jsp").forward(request, response);

    }

    public void mostrarTodasEmpresas(HttpServletRequest request, HttpServletResponse response) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException {
        Enterprise enterp = new Enterprise();

        List<Enterprise> empresas = enterp.listarParaAdministradores();//llamamos a todas las empresas
        request.setAttribute("empresas", empresas);//seteamos el atributo
        request.getRequestDispatcher("./enterprises/mostrarParaAdmin.jsp").forward(request, response);//lo enviamos a la vista

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
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
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
