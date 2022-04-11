/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Pqrs;

/**
 *
 * @author Juan Carlos Osorio
 */
public class pqrsController extends HttpServlet {

    Pqrs pqrs = new Pqrs();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String opcion = request.getParameter("opcion");
            
            switch(opcion){
            
                case "insertarPqrs":
                    this.insertarPqrs(request, response);
                    break;
            }
            
        }
    }
    
    public void insertarPqrs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //insertamos las preguntas, quejas, opiniones y comentarios
        String docIdentidad_usuario = request.getParameter("docIdentidad_usuario");
        String descripcion = request.getParameter("descripcion");
        
        pqrs.setDocIdentidad_usuario(docIdentidad_usuario);
        pqrs.setDescripcion(descripcion);
        System.out.println(docIdentidad_usuario);
        System.out.println("HOLAAAAA");
        boolean estado = pqrs.insertarPqrs(pqrs);
        
        if(estado){
        request.setAttribute("msgPqrs", "Tu comentarios ha sido enviado con exito, muchas gracias :)");
        }else{
        request.setAttribute("msgPqrs", "Tu comentarios no se pudo enviar, por favor intenta de nuevo");
        }
        
        request.getRequestDispatcher("./pqrs/insertarPqrs.jsp").forward(request, response);
        
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
