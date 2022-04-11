package org.apache.jsp.enterprises;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import models.Enterprise;
import java.util.List;

public final class detalle_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>WEOS</title>\n");
      out.write("        <!--ponemos una nueva hoja de estilos-->\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" integrity=\"sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==\" crossorigin=\"anonymous\" />\n");
      out.write("        <link href=\"./assets/css/styles-detail.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../layout/header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"container-detail\">\n");
      out.write("            <!--Obetnemos la empresa que nos llega desde el controller-->\n");
      out.write("            <div class=\"enterp-container\">\n");
      out.write("\n");
      out.write("                <div class=\"enterp-img\">\n");
      out.write("                    <img src='./assets/img/img3.jpg'>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"enterp-info\">\n");
      out.write("                    <h1>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.nombre}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h1>\n");
      out.write("                    <p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.descripcion}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("\n");
      out.write("                    <p class=\"enterp-email\">Contacto: <spam>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.correo}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</spam></p>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"enterp-link\">\n");
      out.write("                <div class=\"social-icon\">\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.link_facebook}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"><i class=\"fab fa-facebook\"></i></a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"social-icon\">\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.link_instagram}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"><i class=\"fab fa-instagram-square\"></i></a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"social-icon\">\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.link_youtube}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"><i class=\"fab fa-youtube\"></i></a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"social-icon\">\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.link_twitter}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"><i class=\"fab fa-twitter\"></i></a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"social-icon\">\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.link_whatsapp}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"><i class=\"fab fa-whatsapp-square\"></i></a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("                \n");
      out.write("                    ");
 if(session.getAttribute("user") != null){ 
      out.write("\n");
      out.write("                    <a href=\"./ProductosServiciosController?opcion=ver&NITempresa=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empresa.NITempresa}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Realizar una cotizacion</a>\n");
      out.write("                    \n");
      out.write("                    <div class=\"products-detail\" id=\"products\">\n");
      out.write("                        <p>Hola mundo</p>\n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    ");
} else if(session.getAttribute("admin") != null || session.getAttribute("enterprise") != null){
      out.write("\n");
      out.write("                    <h2>Debes estar registrado como usuario para realizar una cotizacion</h2>\n");
      out.write("                    \n");
      out.write("                    ");
} else{
      out.write("\n");
      out.write("                    <h2>Debes estar registrado en la pagina para realizar una cotizacion</h2>\n");
      out.write("                    \n");
      out.write("                    ");
}
      out.write("\n");
      out.write("\n");
      out.write("        \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    \n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../layout/footer.jsp", out, false);
      out.write("\n");
      out.write("        <script src=\"./assets/js/buttons.js\">\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
