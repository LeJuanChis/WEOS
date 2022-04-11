/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Carrito;
import models.Cotizacion;
import models.EmailSender;
import models.Enterprise;
import models.ProductoServicios;
import models.Productos_servicios_cotizacion;

/**
 *
 * @author USUARIO
 */
@WebServlet(name = "ProductosServiciosController", urlPatterns = {"/ProductosServiciosController"})

public class ProductosServiciosController extends HttpServlet {

    private Productos_servicios_cotizacion psc = new Productos_servicios_cotizacion();
    private ProductoServicios productosServicios = new ProductoServicios();
    private Enterprise enterp = new Enterprise();
    private Cotizacion coti = new Cotizacion();
    private Carrito car = new Carrito();
    //vamos a crear unas variables para el carrito de compras

    List<Carrito> listaCarrito = new ArrayList();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, MessagingException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            //obtenemos el parametro para la accion
            String opcion = request.getParameter("opcion");
            //creamos la condicionpara decidir que opcion realizar
            switch (opcion) {

                case "insertar":
                    this.insertar(request, response);
                    break;

                case "ver":
                    int NITempresa = Integer.parseInt(request.getParameter("NITempresa"));
                    this.listar(request, response, NITempresa);
                    break;

                case "agregarCarrito":
                    //agregamos el producto al carrito
                    int pos = 0;
                    cantidad = 1;
                    int idProducto = Integer.parseInt(request.getParameter("id"));
                    //verificamos si el carrito tiene elementos
                    if (listaCarrito.size() > 0) {

                        for (int i = 0; i < listaCarrito.size(); i++) {
                            if (idProducto == listaCarrito.get(i).getIdProducto()) {
                                //conocemos la posicion del prodcuto
                                pos = i;
                            }
                        }
                        //verificamos si el producto ya existe, si es asi le sumamos la cantidad
                        if (listaCarrito.get(pos).getIdProducto() == idProducto) {
                            //sumamos la cantidad
                            cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
                            //calculamos el valor
                            double subTotal = listaCarrito.get(pos).getPrecio() * cantidad;
                            listaCarrito.get(pos).setCantidad(cantidad);
                            listaCarrito.get(pos).setTotal(subTotal);

                        } else {//sino es asi llamamos al productio de la database
                            productosServicios = productosServicios.seleccionarPorId(idProducto);
                            //sumamos los productos agregados
                            item += 1;
                            Carrito car = new Carrito();
                            //ahora seteamos los atributos del carrito
                            car.setItem(item);
                            car.setIdProducto(productosServicios.getCodigo());
                            
                            car.setNombre(productosServicios.getNombre());
                            car.setDescripcion(productosServicios.getDescripcion());
                            car.setPrecio(productosServicios.getPrecioUnidad());
                            car.setCantidad(cantidad);
                            car.setTotal(cantidad * productosServicios.getPrecioUnidad());
                            //agregamos el producto a la lista
                            listaCarrito.add(car);
                        }
                    } else {

                        productosServicios = productosServicios.seleccionarPorId(idProducto);
                        //sumamos los productos agregados
                        item += 1;
                        //ahora seteamos los atributos del carrito
                        car.setItem(item);
                        car.setIdProducto(productosServicios.getCodigo());

                        car.setNombre(productosServicios.getNombre());
                        car.setDescripcion(productosServicios.getDescripcion());
                        car.setPrecio(productosServicios.getPrecioUnidad());
                        car.setCantidad(cantidad);
                        car.setTotal(cantidad * productosServicios.getPrecioUnidad());
                        //agregamos el producto a la lista
                        listaCarrito.add(car);

                    }
                    //enviamos el contador
                    request.setAttribute("contador", listaCarrito.size());
                    request.getRequestDispatcher("ProductosServiciosController?opcion=ver").forward(request, response); //direccionamos de nuevo al controlador para ver los productos
                    break;

                case "carrito":
                    int empresa = Integer.parseInt(request.getParameter("NITempresa"));
                    //enviamos los datos del carrito a una pagina especifica
                    request.setAttribute("carrito", listaCarrito);

                    request.setAttribute("NITempresa", empresa);
                    request.getRequestDispatcher("/products/carrito.jsp").forward(request, response);//direccionamos de nuevo al controlador para ver los productos
                    break;

                case "cotizar":
                    int IDempresa = Integer.parseInt(request.getParameter("NITempresa"));
                    //verificamos si el carrito tiene productos
                    if (listaCarrito.isEmpty()) {
                        request.setAttribute("msgInsertCoti", "No ha agregado ningun producto a la cotizacion");
                    } else {
                        //obtenemos el valor a pagar con la cantidad
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            totalPagar = totalPagar + listaCarrito.get(i).getTotal();
                        }
                        //obtenemos el id del usuario e insertamos la ctoizacion a la base de datos
                        String idUser = request.getParameter("idUser");

                        coti.setNITempresa(IDempresa);
                        coti.setDocIdentidad_usuario(idUser);
                        boolean estado = coti.insertarCotizacion(coti);
                        //verificamos si se inserto con exito

                        if (estado) {
                            request.setAttribute("msgInsertCoti", "La cotizacion fue guardada con exito");
                            request.setAttribute("confirmacion", "El correo se envio con exito");
                        } else {
                            request.setAttribute("msgInsertCoti", "La cotizacion no pudo ser guardada");
                        }

                        //ahora vamos a insertar la tabla productos_servicios_cotizacion    
                        boolean estadoPsc = false;
                        //creamos un bucle para insertar cada registro de cada producto
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            int idProd = listaCarrito.get(i).getIdProducto();

                            psc.setCodigo_productos_servicios(idProd);
                            coti = coti.seleccionarCodigo(IDempresa, idUser);
                            psc.setCodigo_cotizacion(coti.getCodigo());
                            psc.setCantidad_productos(listaCarrito.get(i).getCantidad());
                            psc.setPrecio_unidad(listaCarrito.get(i).getPrecio());
                            psc.setNombreProducto(listaCarrito.get(i).getNombre());
                            psc.setDescripcionProducto(listaCarrito.get(i).getDescripcion());
                            estadoPsc = psc.insertar(psc);
                        }
                        //seteamos el total a pagar y la lista (Quer deberia estar vacia)
                        request.setAttribute("cotizacion", totalPagar);
                        request.setAttribute("carrito", listaCarrito);
                        totalPagar = 0.0;//reseteamos el total a pagar
                    }

                    listaCarrito.clear();//limpiamos la lista
                    request.setAttribute("NITempresa", IDempresa);

                    request.getRequestDispatcher("/products/carrito.jsp").forward(request, response);//direccionamos a la vista del carrito
                    break;

                case "eliminarProductoCarrito":
                    //obtenemos el id del producto y de la empresa
                    int Idempresa = Integer.parseInt(request.getParameter("NITempresa"));
                    int idProd = Integer.parseInt(request.getParameter("idProd"));
                    //re3corremos la lista
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (listaCarrito.get(i).getIdProducto() == idProd) {
                            listaCarrito.remove(i);//si el id del producto en la lista es igual al id del producto lo eliminamos
                        }
                    }
                    //enviamos l lista y el id de la empresa
                    request.setAttribute("carrito", listaCarrito);
                    request.setAttribute("NITempresa", Idempresa);
                    request.getRequestDispatcher("/products/carrito.jsp").forward(request, response);//enviamos a la lista del carrito
                    break;

                case "actualizarCantidad":
                    //obtenemos el id del producto y la cantidad actual del js
                    int ideProd = Integer.parseInt(request.getParameter("idProd"));
                    int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    String NIT = request.getParameter("NITempresa");
                    //recorremos la lista
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (listaCarrito.get(i).getIdProducto() == ideProd) {
                            //si el id del producto es igual al id de la lista actualizamos la cantidad y el valor total
                            listaCarrito.get(i).setCantidad(cantidad);
                            double tt = listaCarrito.get(i).getPrecio() * cantidad;
                            listaCarrito.get(i).setTotal(tt);

                        }
                    }
                    //enviamos al controlador para ver los productos
                    request.getRequestDispatcher("ProductosServiciosController?opcion=carrito&NITempresa=" + NIT).forward(request, response);

                    break;

                case "enviarMail":
                    //obtenemos el nit de la empresa y del id del usuario para el correo
                    int nitEmpresa = Integer.parseInt(request.getParameter("NITempresa"));
                    String idUser = request.getParameter("idUser");
                    EmailSender mail = new EmailSender();
                    //seteamos los datos que necesitamos
                    String remitente = "WEOSenterprise@gmail.com";
                    String clave = "weos123456";
                    Cotizacion cot = coti.seleccionarCodigo(nitEmpresa, idUser);//obtenemos el codigo de la cotizacion
                    String cuerpo = "Un ha hecho una cotizacion en tus productos, buscala en la pagina con el codigo numero:" + coti.getCodigo();
                    //obtenemos el correo de la empresa
                    Enterprise enter = enterp.seleccionarUnaEmpresa(nitEmpresa);
                    String destinatario = enter.getCorreo();
                    String asunto = "Nueva cotizacion a tu empresa";
                    //llamamos al enviar correo y le pasamos los datos
                    mail.sendEmailSender(remitente, destinatario, clave, asunto, cuerpo);
                    request.setAttribute("NITempresa", nitEmpresa);
                    request.setAttribute("confirmacionCor", "El correo se envio con exito");
                    request.setAttribute("confirmacion", "El correo se envio con exito");
                    request.getRequestDispatcher("/products/carrito.jsp").forward(request, response);//enviamos a la vista del carrito
                    break;

                case "verDetalleProducto":
                    //obtenemos los datos
                    int codigoProd = Integer.parseInt(request.getParameter("idProd"));
                    String codEmpresa = request.getParameter("NITempresa");
                    productosServicios = productosServicios.seleccionarPorId(codigoProd);//obtenemos el producto y lo enviamos para actualizar

                    request.setAttribute("producto", productosServicios);
                    request.getRequestDispatcher("/enterprises/actualizarProducto.jsp").forward(request, response);

                    break;

                case "actualizarProducto":
                    //actualizamos el producto
                    int codiEmpresa = Integer.parseInt(request.getParameter("NITempresa"));
                    this.actualizarProductoServicios(request, response, codiEmpresa);

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
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ProductosServiciosController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void insertar(HttpServletRequest request, HttpServletResponse response) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException {
        boolean estado1 = false;
        //insertamos a la base de datos
        try {
            String nombre = request.getParameter("nombre");
            int precioUnidad = Integer.parseInt(request.getParameter("precioUnidad"));
            String descripcion = request.getParameter("descripcion");
            String tipo_producto_servicio = request.getParameter("tipo_producto_servicio");
            String NITempresa = request.getParameter("NITempresa");

            //insertar los datos
            productosServicios.setNombre(nombre);
            productosServicios.setPrecioUnidad(precioUnidad);
            productosServicios.setDescripcion(descripcion);
            productosServicios.setTipo_productos_servicios(tipo_producto_servicio);
            productosServicios.setNITempresa(NITempresa);
            //productosServicios.

            productosServicios.conectar();

            estado1 = productosServicios.InsertarProductos(productosServicios);

        } catch (Exception e) {
            System.out.println(e);
        }

        if (estado1) {
            request.setAttribute("msgInsertarProducto", "La insercion se realizo con exito");
        } else {
            request.setAttribute("msgInsertarProducto", "La insercion NO se pudo realizar, por favor intente de nuevo");
        }

        request.getRequestDispatcher("/products/insertarProductosServicios.jsp").forward(request, response);

    }

    public boolean actualizarProductoServicios(HttpServletRequest request, HttpServletResponse response, int codEmpresa) throws SQLException, InstantiationException, IllegalAccessException, ServletException, IOException, NoSuchAlgorithmException {

        //recibimos los datos y los seteamos 
        int codigo = Integer.parseInt(request.getParameter("codigoProd"));
        String nombre = request.getParameter("nombre");
        int precioUnidad = Integer.parseInt(request.getParameter("precioUnidad"));
        String descripcion = request.getParameter("descripcion");
        String tipo_producto_servicio = request.getParameter("tipo_producto_servicio");

        productosServicios.setCodigo(codigo);
        productosServicios.setNombre(nombre);
        productosServicios.setPrecioUnidad(precioUnidad);
        productosServicios.setDescripcion(descripcion);
        productosServicios.setTipo_productos_servicios(tipo_producto_servicio);

        boolean estado1 = productosServicios.actualizarProductoServicio(productosServicios, codigo);//actualizamos los datos

        if (estado1) {
            request.setAttribute("msgActualizarProd", "El producto se actualizo con exito");
        } else {
            request.setAttribute("msgActualizarProd", "El producto no se actualizo con exito, intente de nuevo");
        }

        request.getRequestDispatcher("enterpriseController?opcion=verProductos&NITempresa=" + codEmpresa + "").forward(request, response);

        return estado1;
    }


    public void listar(HttpServletRequest request, HttpServletResponse response, int NITempresa) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException {
        List<ProductoServicios> productos = new ArrayList<>();//creamos la lista
        productos = productosServicios.seleccionar(NITempresa);//llamamos a todos los productos
        //seteamos los atributos
        request.setAttribute("productos", productos);
        request.setAttribute("NITempresa", NITempresa);
        request.getRequestDispatcher("/products/verPorEmpresa.jsp").forward(request, response);

    }

}
