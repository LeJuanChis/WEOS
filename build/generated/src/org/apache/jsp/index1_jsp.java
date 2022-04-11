package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import models.Enterprise;
import controllers.enterpriseController;
import java.util.List;

public final class index1_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "layout/header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<!--Llamamos al controlador direactamente para mostrar de buenas a primeras tres empresas aleatorias-->\n");
 enterpriseController enterprise = new enterpriseController();
    enterprise.mostrarTodas(request, response);
    List<Enterprise> allEnterprises = (List<Enterprise>) request.getAttribute("empresas");

      out.write("\n");
      out.write("\n");
      out.write("<h2>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${msgInsertarEmpresa}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h2>\n");
      out.write("<h1 class=\"txt1\">COTIZACION DE EVENTOS</h1>\n");
      out.write("<!-- slideR -->\n");
      out.write("<div class=\"slide-contenedor\">\n");
      out.write("\n");
      out.write("    <!--Mostramos las imagenes de las empresas aleatorias que llamamos-->\n");
      out.write("    ");
 for (Enterprise enterp : allEnterprises) {
            out.print("<div class='miSlider fade'>");

            if (enterp.getAvatar() != null) {
                out.print("<img src='avatarEnterprises/" + enterp.getAvatar() + "'>");
            } else {
                out.print("<img src='assets/img/img3.jpg'>");
            }
            out.print("</div>");
        } 
      out.write("\n");
      out.write("        <!--Le damos la arquitectura al slider-->\n");
      out.write("    <div class=\"direcciones\">\n");
      out.write("        <a href=\"#\" class=\"atras\" onclick=\"avanzaSlide(-1)\">&#10094;</a>\n");
      out.write("        <a href=\"#\" class=\"adelante\" onclick=\"avanzaSlide(1)\">&#10095;</a>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"barras\">\n");
      out.write("        <span class=\"barra active\" onclick=\"posicionSlide(1)\"></span>\n");
      out.write("        <span class=\"barra\" onclick=\"posicionSlide(2)\"></span>\n");
      out.write("        <span class=\"barra\" onclick=\"posicionSlide(3)\"></span>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<!-- targetas -->\n");
      out.write("<h1 class=\"title\"></h1>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("    <!--creamos el bucle y mostramos las 3 empresas aleatoriamente-->\n");
      out.write("\n");
      out.write("    ");
 for (Enterprise enterpris : allEnterprises) {
            out.print("<div class='card'>");
            if (enterpris.getAvatar() != null) {
                out.print("<img src='avatarEnterprises/" + enterpris.getAvatar() + "'>");
            } else {
                out.print("<img src='assets/img/img3.jpg'>");
            }

            out.print("<h4>" + enterpris.getNombre() + "</h4>");

            out.print("<p maxlength='10'>" + enterpris.getDescripcion() + "</p>");
            out.print("<a href='enterpriseController?opcion=detalle&id=" + enterpris.getNITempresa() + "'>Leer mas</a>");
            out.print("</div>");
        }
      out.write("\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<h1 class=\"f\">\"Los lugares mas populares para ralizar evententos\"</h1>\n");
      out.write("<div class=\"imagelugares\">\n");
      out.write("    <img src=\"assets/img/img1.jpg\">\n");
      out.write("    <img src=\"assets/img/img1.jpg\">\n");
      out.write("    <img src=\"assets/img/img1.jpg\">\n");
      out.write("    <img src=\"assets/img/img1.jpg\">\n");
      out.write("</div>\n");
      out.write("<script src=\"assets/js/main.js\"></script>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "layout/footer.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
