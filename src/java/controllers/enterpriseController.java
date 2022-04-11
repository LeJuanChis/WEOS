/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.Conexion;
import models.Enterprise;
import models.ProductoServicios;
import models.Quote;
import models.User;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Admin
 */
@MultipartConfig
@WebServlet(name = "enterpriseController", urlPatterns = {"/enterpriseController"})
public class enterpriseController extends HttpServlet {

    private Enterprise enterprise = new Enterprise();
    private ProductoServicios productosServicios = new ProductoServicios();
    private User user = new User();
    private String pathFile = "C:/Users/Janus/Desktop/weos/WeosProject/web/avatarEnterprises/";//ruta
    private File uploads = new File(pathFile);//esta es la referencia a la ruta
    private String[] extens = {".ico", ".png", ".jpg", ".jpeg"};//creamos las extensiones que vamos a admitir 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //recogemos la opcion
            String opcion = request.getParameter("opcion");
            switch (opcion) {

                case "insertarEmpresa":

                    this.insertarEmpresa(request, response);
                    break;

                case "ver":
                    this.mostrarTodas(request, response);
                    request.getRequestDispatcher("/enterprises/destacados.jsp").forward(request, response);
                    break;

                case "detalle":

                    int NITempresa = Integer.parseInt(request.getParameter("id"));
                    this.seleccionarUnaEmpresa(request, response, NITempresa);
                    break;

                case "insertarProductos":
                    request.getRequestDispatcher("/products/insertarProductosServicios.jsp").forward(request, response);
                    break;

                case "verPerfil":
                    //llamamos a la empresa por el id y seteamos los atributos
                    int Idempresa = Integer.parseInt(request.getParameter("NITempresa"));
                    Enterprise empresa = enterprise.seleccionarUnaEmpresa(Idempresa);
                    User usuario = user.seleccionarPorEmpresa(Idempresa);
                    request.setAttribute("user", usuario);
                    request.setAttribute("empresa", empresa);
                    request.getRequestDispatcher("/enterprises/actualizar.jsp").forward(request, response);

                    break;

                case "actualizar":
                    int idempresa = Integer.parseInt(request.getParameter("NITempresa"));
                    this.actualizar(request, response, idempresa);
                    break;

                case "listarTodas":
                    this.listarTodas(request, response);
                    break;

                case "actualizarUsuario":
                    //obtenemos el documento del usuario
                    int documentoUser = Integer.parseInt(request.getParameter("id"));
                    this.actualizarUsuario(request, response, documentoUser);
                    break;

                case "verProductos":
                    int idEmpresa = Integer.parseInt(request.getParameter("NITempresa"));
                    List<ProductoServicios> productos = new ArrayList<>();//creamos la lista
                    productos = productosServicios.seleccionar(idEmpresa);
                    //llamamos a los productos, seteamos los atributos y los enviamos
                    request.setAttribute("productos", productos);
                    request.getRequestDispatcher("enterprises/verProductosServicios.jsp").forward(request, response);

                    break;

            }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(enterpriseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(enterpriseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(enterpriseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(enterpriseController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(enterpriseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(enterpriseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(enterpriseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(enterpriseController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void seleccionarUnaEmpresa(HttpServletRequest request, HttpServletResponse response, int NITempresa) throws SQLException, ServletException, IOException, InstantiationException, IllegalAccessException {

        //la creamos de tipo cotzacion ya que en el otro metodo lo devolvemos asi
        Enterprise empresa = enterprise.seleccionarUnaEmpresa(NITempresa);
        if (empresa.getAvatar() != null) {//llamamos una empresa y verificamos si tiene una imagen
            request.setAttribute("confirmacion", "true");
        }
        request.setAttribute("empresa", empresa);//seteamos los datos y los enviamos
        request.getRequestDispatcher("/enterprises/detalle.jsp").forward(request, response);

    }

    public void mostrarTodas(HttpServletRequest request, HttpServletResponse response) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException {
        List<Enterprise> allEnterprises = enterprise.seleccionarTodas();//creamos la lista y seteamos los datos y los enviamos
        request.setAttribute("empresas", allEnterprises);

    }

    public void listarTodas(HttpServletRequest request, HttpServletResponse response) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException {
        List<Enterprise> empresas = enterprise.listarTodas();//llamamos a la lista y seteamos los atributos
        request.setAttribute("empresas", empresas);
        request.getRequestDispatcher("/enterprises/mostrarTodas.jsp").forward(request, response);//enviamos a la vista
    }

    public void actualizar(HttpServletRequest request, HttpServletResponse response, int idempresa) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException {
        //recibimos los datos y los actualizamos
        String NITempresa = request.getParameter("NITempresa");
        String nombre = request.getParameter("nombre");
        //String ciudad = request.getParameter("ciudad");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String descripcion = request.getParameter("descripcion");
//        System.out.println(sesion.getAttribute("user"));
        String link_facebook = request.getParameter("link_facebook");
        String link_instagram = request.getParameter("link_instagram");
        String link_youtube = request.getParameter("link_youtube");
        String link_twitter = request.getParameter("link_twitter");
        String link_whatsapp = request.getParameter("link_whatsapp");

        //instanciar las clases
        //setaer los datos
        enterprise.setNITempresa(NITempresa);
        enterprise.setNombre(nombre);
        enterprise.setCorreo(correo);
        enterprise.setTelefono(telefono);
        enterprise.setDescripcion(descripcion);
        enterprise.setLink_facebook(link_facebook);
        enterprise.setLink_instagram(link_instagram);
        enterprise.setLink_youtube(link_youtube);
        enterprise.setLink_twitter(link_twitter);
        enterprise.setLink_whatsapp(link_whatsapp);

        boolean estado1 = enterprise.actualizarInformacion(enterprise, idempresa);
        //confirmamos si se actualizo con exito
        if (estado1) {
            request.setAttribute("msgInsertar", "Se actualizo La empresa con exito");
        } else {
            request.setAttribute("msgInsertar", "No se pudo actualizar la empresa con exito, por favor intente de nuevo");
        }
        request.getRequestDispatcher("enterpriseController?opcion=verPerfil&NITempresa=" + idempresa + "").forward(request, response);
    }

    public boolean actualizarUsuario(HttpServletRequest request, HttpServletResponse response, int docIdent) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException, NoSuchAlgorithmException {
        //actualizamos el usuario de la empresa
        int NITempresa = Integer.parseInt(request.getParameter("NITempresa"));
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
            request.setAttribute("msgInsertar", "El usuario se actualizo con exito");
        } else {
            request.setAttribute("msgInsertar", "No se pudo actualizar el usuario, intente de nuevo");
        }
        request.getRequestDispatcher("enterpriseController?opcion=verPerfil&NITempresa=" + NITempresa + "").forward(request, response);
        return estado1;
    }



//aca vamos a guardar el archivo en una carpeta local
    public String saveFile(Part part, File pathUploads) {
        String pathAbsolute = "";//variable de la ruta

        try {
            Path path = Paths.get(part.getSubmittedFileName());
            String fileName = path.getFileName().toString();//obtenemos el nombre de la imagen (lo que guardamos en la DB despues de la ruta)
            InputStream input = part.getInputStream();//este es el archivo en si, es el que guarsamos en la carpeta local

            if (input != null) {//validamos si existe el archivo
                File file = new File(pathUploads, fileName);//creamos una variable tipo file y le damos el nombre del archivo + la ruta de guardado    
                pathAbsolute = file.getAbsolutePath();//aca obtenemos la ruta + el nombre completo del archivo
                Files.copy(input, file.toPath());//ahora copiamos el archivo a nuestra carpeta
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(pathAbsolute);
        return pathAbsolute;
    }

    //verificamos si el archivo que el usuario envio si tiene las extensiones correctas (si es una imagen)    
    public boolean isExtension(String fileName, String[] extensions) {
        for (String et : extensions) {//creamos el bucle para recorrer nuestro arreglo 
            if (fileName.toLowerCase().endsWith(et)) {//si el archivo termina con una de las rutas que le dimos, nos va a retornar true
                return true;
            }
        }
        return false;//por el contrario nos retorna false
    }

    public void insertarEmpresa(HttpServletRequest request, HttpServletResponse response) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException {
        boolean estado1 = false;
        //guardar el avatar
        ArrayList<String> lista = new ArrayList<>();
        try {
            String NITempresa = request.getParameter("NITempresa");
            String nombre = request.getParameter("nombre");
            String ciudad = request.getParameter("ciudad");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            String descripcion = request.getParameter("descripcion");

            String usuario = request.getParameter("usuario");
            HttpSession sesion = request.getSession();
            String link_facebook = request.getParameter("link_facebook");
            String link_instagram = request.getParameter("link_instagram");
            String link_youtube = request.getParameter("link_youtube");
            String link_twitter = request.getParameter("link_twitter");
            String link_whatsapp = request.getParameter("link_whatsapp");

            //añadimos el avatar
            Part avatar = request.getPart("avatar");
            Path path = Paths.get(avatar.getSubmittedFileName());
            String nombreImage = path.getFileName().toString();

            if (isExtension(avatar.getSubmittedFileName(), extens)) {//verificamos si la imagene s de la extension 
                String rutaAvatar = this.saveFile(avatar, uploads);

                //instanciar las clases
                //setaer los datos
                User user = new User();
                enterprise.setNITempresa(NITempresa);
                enterprise.setNombre(nombre);
                enterprise.setCiudad(ciudad);
                enterprise.setCorreo(correo);
                enterprise.setTelefono(telefono);
                enterprise.setDescripcion(descripcion);
                enterprise.setAvatar(nombreImage);
                enterprise.setUsuario(usuario);
                enterprise.setLink_facebook(link_facebook);
                enterprise.setLink_instagram(link_instagram);
                enterprise.setLink_youtube(link_youtube);
                enterprise.setLink_twitter(link_twitter);
                enterprise.setLink_whatsapp(link_whatsapp);

                //cambiamos el rol del usuario
                estado1 = enterprise.CrearCuenta(enterprise);
                user.actualizarRolUsuario(usuario);//actualizamos el rol de usuario

                sesion.removeAttribute("user");//remover la sesion del usuario
            }

            if (estado1) {
                request.setAttribute("msgInsertarEmpresa", "La insercion se realizo con exito");
                request.getRequestDispatcher("/index1.jsp").forward(request, response);//si se inserto enviamos al index sin sesion
            } else {
                request.setAttribute("msgInsertarEmpresa", "La insercion NO se pudo realizar, por favor intente de nuevo");
                request.getRequestDispatcher("enterprises/crearCuentaEmpresa.jsp").forward(request, response);//sino dirigimos de nuevo a la pagina con un mensaje de erro
            }
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("msgInsertarEmpresa", "La insercion NO se pudo realizar, por favor intente de nuevo");
            request.getRequestDispatcher("enterprises/crearCuentaEmpresa.jsp").forward(request, response);
        }

    }
}
