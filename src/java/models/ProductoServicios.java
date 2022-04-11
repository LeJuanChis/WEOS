package models;

import com.mysql.jdbc.Connection;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoServicios extends Conexion {

    private int codigo;
    private String nombre;
    private int precioUnidad;
    private String descripcion;
    private String tipo_productos_servicios;
    private String NITempresa;

    public ProductoServicios() {

    }

    public ProductoServicios(int codigo,
            String nombre,
            int precioUnidad,
            String descripcion,
            String tipo_productos_servicios,
            String NITempresa) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.precioUnidad = precioUnidad;
        this.descripcion = descripcion;
        this.tipo_productos_servicios = tipo_productos_servicios;
        this.NITempresa = NITempresa;
    }

    private ProductoServicios(int codigo, String nombre, int precioUnidad, String descripcion, String tipo_productos_servicios) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioUnidad = precioUnidad;
        this.descripcion = descripcion;
        this.tipo_productos_servicios = tipo_productos_servicios;
    }

    public String getNITempresa() {
        return NITempresa;
    }

    public void setNITempresa(String NITempresa) {
        this.NITempresa = NITempresa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(int precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_productos_servicios() {
        return tipo_productos_servicios;
    }

    public void setTipo_productos_servicios(String tipo_productos_servicios) {
        this.tipo_productos_servicios = tipo_productos_servicios;
    }

    //insertamos un producto
    public boolean InsertarProductos(ProductoServicios productoServicios) throws SQLException, InstantiationException, IllegalAccessException {

        Conexion con;

        Connection cn = null;
        Statement st = null;
        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement();
        String sql = "INSERT INTO tblproductosservicios VALUES (null, '" + productoServicios.getNombre() + "'," + productoServicios.getPrecioUnidad() + ",'" + productoServicios.getDescripcion() + "','" + productoServicios.getTipo_productos_servicios() + "', '" + productoServicios.getNITempresa() + "')";

        st.executeUpdate(sql);

        return true;
    }
    //Seleccionamos todas las empresas
    public List<ProductoServicios> seleccionar(int NITempresa) throws SQLException, InstantiationException, IllegalAccessException {
        List<ProductoServicios> allProducts = new ArrayList<ProductoServicios>();
        Conexion con;
        ProductoServicios prod = new ProductoServicios();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT codigo, nombre, precioUnidad, descripcion, tipo_productos_servicios FROM tblproductosservicios where NITempresa='" + NITempresa + "'";
        try {
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                allProducts.add(new ProductoServicios(rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("precioUnidad"), rs.getString("descripcion"), rs.getString("tipo_productos_servicios")));
            }
//            while (rs.next()) {
//                prod.setCodigo(rs.getInt("codigo"));
//                prod.setNombre(rs.getString("nombre"));
//                prod.setPrecioUnidad(rs.getInt("precioUnidad"));
//                prod.setDescripcion(rs.getString("descripcion"));
//                prod.setTipo_productos_servicios(rs.getString("tipo_productos_servicios"));
//                allProducts.add(prod);
//            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return allProducts;

    }
    //listamos varias empresas
    public List listar(int NITempresa) {
        List<ProductoServicios> productos = new ArrayList();
        String sql = "SELECT codigo, nombre, precioUnidad, descripcion, tipo_productos_servicios FROM tblproductosservicios where NITempresa='" + NITempresa + "'";
        Conexion con;

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                ProductoServicios prod = new ProductoServicios();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecioUnidad(rs.getInt("precioUnidad"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setTipo_productos_servicios(rs.getString("tipo_productos_servicios"));
                productos.add(prod);
            }
        } catch (Exception e) {
            System.out.println(e);

        }

        return productos;
    }

    //seleccionamos una empresa por id
    public ProductoServicios seleccionarPorId(int id) {
        String sql = "SELECT codigo, nombre, precioUnidad, descripcion, tipo_productos_servicios FROM tblproductosservicios where codigo = " + id + "";
        Conexion con;
        ProductoServicios prod = new ProductoServicios();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {

                prod.setCodigo(rs.getInt("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecioUnidad(rs.getInt("precioUnidad"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setTipo_productos_servicios(rs.getString("tipo_productos_servicios"));

            }

        } catch (Exception e) {

            System.out.println(e);
        }

        return prod;
    }

        //actualizamos el producto
        public boolean actualizarProductoServicio(ProductoServicios prod, int codigo) throws SQLException, NoSuchAlgorithmException {

        try {
            Conexion con;
            Connection cn = null;
            Statement st = null;
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            String sql = "UPDATE tblProductosServicios SET nombre='" + prod.getNombre() + "',"
                    + "precioUnidad=" + prod.getPrecioUnidad() + ", descripcion='" + prod.getDescripcion() + "',"
                    + "tipo_productos_servicios='" + prod.getTipo_productos_servicios() + "' where codigo='" + codigo + "'";
            st.executeUpdate(sql);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InstantiationException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

}
