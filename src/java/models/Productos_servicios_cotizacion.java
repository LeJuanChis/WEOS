/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Carlos Osorio
 */
public class Productos_servicios_cotizacion {

    private int codigo_productos_servicios;
    private int codigo_cotizacion;
    private int cantidad_productos;
    private double precio_unidad;
    private String nombreProducto;
    private String descripcionProducto;
    private Enterprise NITempresa;
    private Cotizacion cotizacion;
    private User usuario;

    public Productos_servicios_cotizacion(int codigo_productos_servicios, int codigo_cotizacion, int cantidad_productos, double precio_unidad, String nombreProducto, String descripcionProducto) {
        this.codigo_productos_servicios = codigo_productos_servicios;
        this.codigo_cotizacion = codigo_cotizacion;
        this.cantidad_productos = cantidad_productos;
        this.precio_unidad = precio_unidad;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
    }

    public Productos_servicios_cotizacion(int cantidad_productos, double precio_unidad, String nombreProducto, String descripcionProducto, Enterprise NITempresa, Cotizacion cotizacion) {
        this.cantidad_productos = cantidad_productos;
        this.precio_unidad = precio_unidad;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.NITempresa = NITempresa;
        this.cotizacion = cotizacion;
    }

    public Productos_servicios_cotizacion(int cantidad_productos, double precio_unidad, String nombreProducto, String descripcionProducto, Cotizacion cotizacion, User usuario) {
        this.cantidad_productos = cantidad_productos;
        this.precio_unidad = precio_unidad;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cotizacion = cotizacion;
        this.usuario = usuario;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Productos_servicios_cotizacion() {
    }

    public Enterprise getNITempresa() {
        return NITempresa;
    }

    public void setNITempresa(Enterprise NITempresa) {
        this.NITempresa = NITempresa;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getCodigo_productos_servicios() {
        return codigo_productos_servicios;
    }

    public void setCodigo_productos_servicios(int codigo_productos_servicios) {
        this.codigo_productos_servicios = codigo_productos_servicios;
    }

    public int getCodigo_cotizacion() {
        return codigo_cotizacion;
    }

    public void setCodigo_cotizacion(int codigo_cotizacion) {
        this.codigo_cotizacion = codigo_cotizacion;
    }

    public int getCantidad_productos() {
        return cantidad_productos;
    }

    public void setCantidad_productos(int cantidad_productos) {
        this.cantidad_productos = cantidad_productos;
    }

    public double getPrecio_unidad() {
        return precio_unidad;
    }

    public void setPrecio_unidad(double precio_unidad) {
        this.precio_unidad = precio_unidad;
    }

    //insertamos la xotizacion con los productos
    public boolean insertar(Productos_servicios_cotizacion psc) throws InstantiationException, IllegalAccessException {
        Conexion con;
        Connection cn = null;
        Statement st = null;

        String sql = "INSERT INTO tblproductosservicios_cotizacion VALUES(" + psc.getCodigo_productos_servicios() + " ," + psc.getCodigo_cotizacion() + ", " + psc.getCantidad_productos() + ", " + psc.getPrecio_unidad() + ", '" + psc.getNombreProducto() + "', '" + psc.getDescripcionProducto() + "')";

        try {
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();

            st.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    //seleccionamos por suuario
    public List<Productos_servicios_cotizacion> seleccionarPorUsuario(String idUsuario) {
        List<Productos_servicios_cotizacion> productosCotizaciones = new ArrayList<Productos_servicios_cotizacion>();
        Productos_servicios_cotizacion psc = new Productos_servicios_cotizacion();
        Conexion con;
        Cotizacion coti = new Cotizacion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT PSC.nombreProducto, PSC.precioUnidad, PSC.descripcionProducto, PSC.cantidadProductos, "
                + "CO.NITempresa,CO.codigo ,EM.nombre, EM.correo FROM tblproductosservicios_cotizacion as PSC "
                + "Inner Join tblproductosservicios as PS on PSC.codigoProductosServicios = PS.codigo "
                + "Inner join tblcotizacion as CO on PSC.codigoCotizacion = CO.codigo AND CO.docIdentidad_usuario = '" + idUsuario + "'"
                + "Inner join tblempresa as EM  on EM.NITempresa= CO.NITempresa";

        try {
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = st.executeQuery(sql);

            rs.beforeFirst();

            while (rs.next()) {
                //seteamos los valores de productos servicios cotizacion
                psc.setNombreProducto(rs.getString("nombreProducto"));
                psc.setPrecio_unidad(rs.getInt("precioUnidad"));
                psc.setDescripcionProducto(rs.getString("descripcionProducto"));
                psc.setCantidad_productos(rs.getInt("cantidadProductos"));

                //creamos los valores de empresa y cotizaciones
                psc.setNITempresa(new Enterprise());
                psc.setCotizacion(new Cotizacion());
                //seteamos los valores
                psc.getNITempresa().setNombre(rs.getString("nombre"));
                psc.getNITempresa().setCorreo(rs.getString("correo"));
                psc.getCotizacion().setNITempresa(rs.getInt("NITempresa"));
                psc.getCotizacion().setCodigo(rs.getInt("codigo"));
                productosCotizaciones.add(new Productos_servicios_cotizacion(rs.getInt("cantidadProductos"), rs.getInt("precioUnidad"), rs.getString("nombreProducto"), rs.getString("descripcionProducto"),
                        psc.NITempresa, psc.cotizacion));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return productosCotizaciones;
    }

    //seleccionamos por empresa
    public List<Productos_servicios_cotizacion> seleccionarPorEmpresa(String NITempresa) {
        List<Productos_servicios_cotizacion> cotizacionUsuarios = new ArrayList<Productos_servicios_cotizacion>();
        Productos_servicios_cotizacion psc = new Productos_servicios_cotizacion();
        Conexion con;
        Cotizacion coti = new Cotizacion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT PSC.nombreProducto, PSC.precioUnidad, PSC.descripcionProducto, PSC.cantidadProductos, "
                + "CO.docIdentidad_usuario ,CO.codigo , US.nombre, US.correo, US.telefono FROM tblproductosservicios_cotizacion as PSC "
                + "Inner Join tblproductosservicios as PS on PSC.codigoProductosServicios = PS.codigo "
                + "Inner join tblcotizacion as CO on PSC.codigoCotizacion = CO.codigo AND CO.NITempresa = '" + NITempresa + "' "
                + "Inner join tblempresa as EM  on EM.NITempresa= CO.NITempresa "
                + "Inner Join tblusuarios as US on US.docIdentidad = CO.docIdentidad_usuario";

        try {
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = st.executeQuery(sql);

            rs.beforeFirst();

            while (rs.next()) {
                //seteamos los valores de productos servicios cotizacion
                psc.setNombreProducto(rs.getString("nombreProducto"));
                psc.setPrecio_unidad(rs.getInt("precioUnidad"));
                psc.setDescripcionProducto(rs.getString("descripcionProducto"));
                psc.setCantidad_productos(rs.getInt("cantidadProductos"));

                //creamos los valores de usuario y cotizaciones
                psc.setUsuario(new User());
                psc.setCotizacion(new Cotizacion());
                //seteamos los valores
                psc.getCotizacion().setDocIdentidad_usuario(rs.getString("docIdentidad_usuario"));
                psc.getCotizacion().setCodigo(rs.getInt("codigo"));
                psc.getUsuario().setNombre(rs.getString("nombre"));
                psc.getUsuario().setCorreo(rs.getString("correo"));
                psc.getUsuario().setTelefono(rs.getString("telefono"));
                cotizacionUsuarios.add(new Productos_servicios_cotizacion(rs.getInt("cantidadProductos"), rs.getInt("precioUnidad"), rs.getString("nombreProducto"), rs.getString("descripcionProducto"),
                        psc.cotizacion, psc.usuario));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return cotizacionUsuarios;
    }

    //seleccionamos la ultima cotizacion para el pdf
    public List<Productos_servicios_cotizacion> seleccionarUnaCotizacion(int codigoCotizacion) throws SQLException, InstantiationException, IllegalAccessException {
        List<Productos_servicios_cotizacion> cotizacionUsuario = new ArrayList<Productos_servicios_cotizacion>();
        Productos_servicios_cotizacion psc = new Productos_servicios_cotizacion();
        Conexion con;
        Cotizacion coti = new Cotizacion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT PSC.codigoCotizacion, US.nombre, US.apellido, PSC.nombreProducto, PSC.precioUnidad, PSC.descripcionProducto, PSC.cantidadProductos, EM.nombre, EM.correo from tblproductosservicios_cotizacion as PSC Inner join tblcotizacion as CO on PSC.codigoCotizacion = CO.codigo Inner Join tblusuarios as US on US.docIdentidad = CO.docIdentidad_usuario Inner Join tblempresa as EM on EM.NITempresa = CO.NITempresa \n"
                + "WHERE PSC.codigoCotizacion = '" + codigoCotizacion + "'";
        try {
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = st.executeQuery(sql);

            rs.beforeFirst();

            while (rs.next()) {
            psc.setCodigo_cotizacion(rs.getInt(1));
            psc.setUsuario(new User());
            psc.getUsuario().setNombre(rs.getString(2));
            psc.getUsuario().setApellidos(rs.getString(3));
            psc.setNombreProducto(rs.getString(4));
            psc.setPrecio_unidad(rs.getInt(5));
            psc.setDescripcionProducto(rs.getString(6));
            psc.setCantidad_productos(rs.getInt(7));
            psc.setNITempresa(new Enterprise());
            psc.getNITempresa().setNombre(rs.getString(8));
            psc.getNITempresa().setCorreo(rs.getString(9));
                cotizacionUsuario.add(new Productos_servicios_cotizacion(rs.getInt(1), psc.usuario, rs.getString(4) ,rs.getInt(5), rs.getString(6), rs.getInt(7), psc.NITempresa));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return cotizacionUsuario;

    }

    public Productos_servicios_cotizacion(int codigo_cotizacion, User usuario, String nombreProducto, int precio_unidad, String descripcionProducto, int cantidad_productos ,Enterprise NITempresa) {
        this.codigo_cotizacion = codigo_cotizacion;
        this.cantidad_productos = cantidad_productos;
        this.precio_unidad = precio_unidad;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.NITempresa = NITempresa;
        this.usuario = usuario;
    }


    
    

}


