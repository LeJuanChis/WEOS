/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Cotizacion;
import models.Productos_servicios_cotizacion;

/**
 *
 * @author Juan Carlos Osorio
 */
@WebServlet(name = "reportes", urlPatterns = {"/reportes"})
public class reportes extends HttpServlet {

    private Cotizacion coti = new Cotizacion();
    private Productos_servicios_cotizacion psc = new Productos_servicios_cotizacion();
    List<Productos_servicios_cotizacion> pscList = new ArrayList();
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        
        try {

            try {
                double totalCotizacion=0.0;
                int cantidad =0;
                int idEmpresa = Integer.parseInt(request.getParameter("NITempresa"));
                String docUsuario = request.getParameter("idUser");

                coti = coti.seleccionarCodigo(idEmpresa, docUsuario);

                int codigoCotizacion = coti.getCodigo();

                pscList = psc.seleccionarUnaCotizacion(codigoCotizacion);
                
                
                // coti = coti.seleccionarCodigo(1040, docIdentidad_usuario);
                Document documento = new Document();
                PdfWriter.getInstance(documento, out);

                documento.open();

                Paragraph par1 = new Paragraph();
                Paragraph par2 = new Paragraph();
                Paragraph total = new Paragraph();

                Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
                Font fontDescrip = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
                par2.add("");
                //agregamos el titulo y lo centramos
                par1.add(new Phrase("Tu cotizacion", fontTitulo));
                par1.setAlignment(Element.ALIGN_CENTER);
                //agregamos un salto de linea
                par1.add(new Phrase(Chunk.NEWLINE));
                par1.add(new Phrase(Chunk.NEWLINE));
                //añadimos el titulo al pdf
                documento.add(par1);
                //creamos las celdas superiores de la tabla
                PdfPTable tabla = new PdfPTable(4);
                PdfPCell celda3 = new PdfPCell(new Paragraph("Nombre del producto", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.GRAY)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Precio por unidad", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.GRAY)));
                PdfPCell celda5 = new PdfPCell(new Paragraph("Descripcion", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.GRAY)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("Cantidad de productos", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.GRAY)));

                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda5);
                tabla.addCell(celda6);
                //obtenemos los datos que no se van a repetir
                String NITempresa = pscList.get(0).getNITempresa().getNombre();
                String correoEmpresa = pscList.get(0).getNITempresa().getCorreo();
                int codigoCoti = pscList.get(0).getCodigo_cotizacion();
                String nombreUsuario = pscList.get(0).getUsuario().getNombre();

                //añadimos la informacion de la cotizacion
                Paragraph parInfo = new Paragraph();
                Font infoFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);

                //creamos un texto para el usuario
                parInfo.add(new Phrase("El nombre del usuario es: " + nombreUsuario + ""
                        + ", el correo de la empresa es: " + correoEmpresa + ", y puedes buscar la cotizacion en la pagina con el numero: " + codigoCoti + ", gracias por confiar en nosotros :)", infoFont));
                parInfo.setAlignment(Element.ALIGN_JUSTIFIED);
                parInfo.add(new Phrase(Chunk.NEWLINE));
                parInfo.add(new Phrase(Chunk.NEWLINE));
                documento.add(parInfo);


                
                //añadimos los productos a la tabla
                for (int i = 0; i < pscList.size(); i++) {
                    cantidad = pscList.get(i).getCantidad_productos();
                    tabla.addCell(pscList.get(i).getNombreProducto());
                    tabla.addCell(Double.toString(pscList.get(i).getPrecio_unidad()));//BIEN
                    
                    totalCotizacion +=pscList.get(i).getPrecio_unidad()*cantidad;
                    tabla.addCell(pscList.get(i).getDescripcionProducto());
                    tabla.addCell(Integer.toString(pscList.get(i).getCantidad_productos()));

                }

                total.add(new Phrase("El total de la cotizacion es: " + Double.toString(totalCotizacion)));
                total.setAlignment(Element.ALIGN_CENTER);
                total.add(new Phrase(Chunk.NEWLINE));
                total.add(new Phrase(Chunk.NEWLINE));
                documento.add(total);

                //añadimos el logo
                Image imagen = Image.getInstance("C:\\Users\\Janus\\Desktop\\weos\\WeosProject\\build\\web\\files\\logo.png");
                imagen.setAlignment(Element.ALIGN_CENTER);
                documento.add(imagen);

                documento.add(tabla);

                documento.close();
                pscList.clear();
                
            } catch (Exception ex) {
                ex.getMessage();
            }

        } finally {
            out.close();
        }
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
