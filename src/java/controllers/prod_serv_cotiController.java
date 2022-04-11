/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Productos_servicios_cotizacion;

public class prod_serv_cotiController extends HttpServlet {

    private Productos_servicios_cotizacion psc = new Productos_servicios_cotizacion();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String opcion = request.getParameter("opcion");

            switch (opcion) {

                case "verCotizacion":
                    String docUsuario = request.getParameter("docIdentidad");
                    this.ver(request, response, docUsuario);
                    break;

                case "verCotizacionesUsuario":
                    String NITempresa = request.getParameter("NITempresa");
                    this.verPorUsuarios(request, response, NITempresa);
                    break;

            }
        }
    }

    public void verPorUsuarios(HttpServletRequest request, HttpServletResponse response, String NITempresa) throws ServletException, IOException {
        List<Productos_servicios_cotizacion> productosCotizacionesUsuario = new ArrayList<>();//creamos la lista
        productosCotizacionesUsuario = psc.seleccionarPorEmpresa(NITempresa);//obtenemos los productos y servicios de una cotizacion por usuarios
        
        request.setAttribute("productosCotizaciones", productosCotizacionesUsuario);//seteamos la lista y la enviamos
        request.getRequestDispatcher("/quote/verCotizacionEmpresa.jsp").forward(request, response);

    }

    public void ver(HttpServletRequest request, HttpServletResponse response, String docUsuario) throws ServletException, IOException {
        List<Productos_servicios_cotizacion> productosCotizaciones = new ArrayList<>();
        productosCotizaciones = psc.seleccionarPorUsuario(docUsuario);//obtenemos los productos y servicios por empresa 

        request.setAttribute("productosCotizaciones", productosCotizaciones);//seteamos la lista y la  enviamos
        request.getRequestDispatcher("/quote/verCotizacionUsuario.jsp").forward(request, response);

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
