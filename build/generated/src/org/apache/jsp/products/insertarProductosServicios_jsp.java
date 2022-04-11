package org.apache.jsp.products;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class insertarProductosServicios_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>WEOS</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../assets/css/main.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../layout/header.jsp", out, false);
      out.write("\n");
      out.write("        <h1>Insertar un producto o servicio</h1>\n");
      out.write("\n");
      out.write("        <form class=\"form-subirDatos\" method=\"POST\" action=\"../ProductosServiciosController?opcion=insertar\">\n");
      out.write("            <div class=\"inputlabel-container\">\n");
      out.write("            <label for=\"nombre\">Nombre:</label>\n");
      out.write("            <input type=\"text\" name=\"nombre\">\n");
      out.write("\n");
      out.write("            <label for=\"precioU\">Precio por unidad:</label>\n");
      out.write("            <input type=\"number\" name=\"precioUnidad\">\n");
      out.write("\n");
      out.write("            <label for=\"descripcion\">Descripcion:</label>\n");
      out.write("            <input type=\"text\" name=\"descripcion\">\n");
      out.write("\n");
      out.write("            <label for=\"tipo\">Tipo (producto o servicio)</label>\n");
      out.write("            <select name=\"tipo_producto_servicio\">\n");
      out.write("                <option selected=\"selected\" value=\"producto\" name=\"tipo_producto_servicio\">Producto</option>\n");
      out.write("                <option value=\"servicio\" name=\"tipo_producto_servicio\">Servicio</option>\n");
      out.write("            </select>\n");
      out.write("            \n");
      out.write("            <label for=\"NITempresa\">NIT de la empresa</label>\n");
      out.write("            <input type=\"text\" name=\"NITempresa\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${enterprise.NITempresa}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("            \n");
      out.write("             \n");
      out.write("                    <input type=\"submit\" class=\"btnEnviar enviar-datos\" value=\"Enviar\">\n");
      out.write("               \n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../layout/footer.jsp", out, false);
      out.write("\n");
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
