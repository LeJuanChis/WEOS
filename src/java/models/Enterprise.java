/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class Enterprise extends Conexion {

    public String NITempresa;
    public String nombre;
    public String ciudad;
    public String correo;
    public String telefono;
    public String descripcion;
    public String avatar;
    public String usuario;
    public String direccion;
    public String link_facebook;
    public String link_instagram;
    public String link_youtube;
    public String link_twitter;
    public String link_whatsapp;

    public Enterprise() {

    }

    public Enterprise(String NITempresa,
            String nombre,
            String ciudad,
            String correo,
            String telefono,
            String descripcion,
            String avatar,
            String usuario,
            String direccion,
            String link_facebook,
            String link_instagram,
            String link_youtube,
            String link_twitter,
            String link_whatsapp) {

        this.NITempresa = NITempresa;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.correo = correo;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.usuario = usuario;
        this.direccion = direccion;
        this.link_facebook = link_facebook;
        this.link_instagram = link_instagram;
        this.link_youtube = link_youtube;
        this.link_twitter = link_twitter;
        this.link_whatsapp = link_whatsapp;
    }

//    private Enterprise(String NITempresa, String nombre, String descripcion, String correo, String avatar, String direccion) {
//        
//    }
//    
    private Enterprise(String NITempresa, String nombre, String descripcion, String correo, String avatar, String direccion) {
        this.NITempresa = NITempresa;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.correo = correo;
        this.avatar = avatar;
        this.direccion = direccion;
    }

    public Enterprise(String NITempresa, String nombre, String ciudad, String correo, String telefono, String descripcion, String avatar, String direccion, String link_facebook, String link_instagram, String link_youtube, String link_twitter, String link_whatsapp) {
        this.NITempresa = NITempresa;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.correo = correo;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.direccion = direccion;
        this.link_facebook = link_facebook;
        this.link_instagram = link_instagram;
        this.link_youtube = link_youtube;
        this.link_twitter = link_twitter;
        this.link_whatsapp = link_whatsapp;
    }

    public String getNITempresa() {
        return NITempresa;
    }

    public void setNITempresa(String NITempresa) {
        this.NITempresa = NITempresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLink_facebook() {
        return link_facebook;
    }

    public void setLink_facebook(String link_facebook) {
        this.link_facebook = link_facebook;
    }

    public String getLink_instagram() {
        return link_instagram;
    }

    public void setLink_instagram(String link_instagram) {
        this.link_instagram = link_instagram;
    }

    public String getLink_youtube() {
        return link_youtube;
    }

    public void setLink_youtube(String link_youtube) {
        this.link_youtube = link_youtube;
    }

    public String getLink_twitter() {
        return link_twitter;
    }

    public void setLink_twitter(String link_twitter) {
        this.link_twitter = link_twitter;
    }

    public String getLink_whatsapp() {
        return link_whatsapp;
    }

    public void setLink_whatsapp(String link_whatsapp) {
        this.link_whatsapp = link_whatsapp;
    }

    //insertamos los datos de la empresa
    public boolean CrearCuenta(Enterprise enterprise) throws SQLException, InstantiationException, IllegalAccessException {

        Conexion con;
        Connection cn = null;
        Statement st = null;
        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement();
        String sql = "INSERT INTO tblempresa VALUES ('" + enterprise.getNITempresa() + "', '" + enterprise.getNombre() + "',  null, '" + enterprise.getCorreo() + "', '" + enterprise.getTelefono() + "', '" + enterprise.getDescripcion() + "','" + enterprise.getAvatar() + "', '" + enterprise.getUsuario() + "',null,'" + enterprise.getLink_facebook() + "', '" + enterprise.getLink_instagram() + "', '" + enterprise.getLink_youtube() + "','" + enterprise.getLink_twitter() + "', '" + enterprise.getLink_whatsapp() + "')";

        st.executeUpdate(sql);

        return true;

        //String sql = "INSERT INTO tblempresa VALUES ('" + enterprise.getNITempresa() + "', '" + enterprise.getNombre() + "',  null, '" + enterprise.getCorreo() + "', '" + enterprise.getTelefono() + "', '" + enterprise.getDescripcion() + "','"+enterprise.getAvatar()+"', '" + enterprise.getUsuario() + "',null,'" + enterprise.getLink_facebook() + "', '" + enterprise.getLink_instagram() + "', '" + enterprise.getLink_youtube() + "','" + enterprise.getLink_twitter() + "', '" + enterprise.getLink_whatsapp() + "')";
    }

    //seleccionamos el detalle de la empresa
    public Enterprise seleccionarUnaEmpresa(int NITempresa) throws SQLException, InstantiationException, IllegalAccessException {
        Conexion con;
        Enterprise enter = new Enterprise();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT NITempresa, nombre, correo, telefono, descripcion, avatar, "
                + "direccion, link_facebook, link_instagram, link_youtube, link_twitter, link_whatsapp "
                + "FROM tblEmpresa WHERE NITempresa='" + NITempresa + "'";
        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement();
        rs = st.executeQuery(sql);

        if (rs.next() == true) {
            enter.setNITempresa(rs.getString("NITempresa"));
            enter.setNombre(rs.getString("nombre"));
            enter.setCorreo(rs.getString("correo"));
            enter.setTelefono(rs.getString("telefono"));
            enter.setDescripcion(rs.getString("descripcion"));
            enter.setAvatar(rs.getString("avatar"));
            enter.setDireccion(rs.getString("direccion"));
            enter.setLink_facebook(rs.getString("link_facebook"));
            enter.setLink_instagram(rs.getString("link_instagram"));
            enter.setLink_youtube(rs.getString("link_youtube"));
            enter.setLink_twitter(rs.getString("link_twitter"));
            enter.setLink_whatsapp(rs.getString("link_whatsapp"));
        }

        return enter;

    }

    //seleccionar una empresa por usuario para cambiar la sesion
    public Enterprise seleccionarParaUsuarios(int documentoUsuario) throws SQLException, InstantiationException, IllegalAccessException {
        Conexion con;
        Enterprise enter = new Enterprise();
        Quote quo = new Quote();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT NITempresa, nombre, correo, telefono FROM"
                + " tblEmpresa WHERE usuario = '" + documentoUsuario + "'";
        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement();
        rs = st.executeQuery(sql);

        if (rs.next() == true) {
            //sacamos lo unico que necesitamos tener en la sesion
            enter.setNITempresa(rs.getString("NITempresa"));
            enter.setNombre(rs.getString("nombre"));
            enter.setCorreo(rs.getString("correo"));
            enter.setTelefono(rs.getString("telefono"));
        } else {

        }
        return enter;
    }

    //seleccionamos 3 empresasd de manera aleatoria
    public List<Enterprise> seleccionarTodas() throws SQLException, InstantiationException, IllegalAccessException {
        List<Enterprise> allEnterprise = new ArrayList<Enterprise>();
        Conexion con;
        Enterprise enter = new Enterprise();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT NITempresa, nombre,descripcion ,correo, avatar, direccion FROM tblEmpresa ORDER BY RAND() LIMIT 3";

        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql);

        rs.beforeFirst();

        while (rs.next()) {
            allEnterprise.add(new Enterprise(rs.getString("NITempresa"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("correo"),
                    rs.getString("avatar"), rs.getString("direccion")));
        }

        return allEnterprise;
        
        

    }

    //mostramos la imagen de las empresas
    public void listarImg(String id, HttpServletResponse response) throws IOException, SQLException, InstantiationException, IllegalAccessException {
        Conexion con;
        Enterprise enter = new Enterprise();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        //aca usamos el * sin problemas ya que solo sera para las imagenes
        String sql = "SELECT * FROM tblEmpresa where NITempresa = '" + id + "'";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");

            outputStream = response.getOutputStream();
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                inputStream = rs.getBinaryStream("avatar");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;

            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
            


    }

    //seleccionamos un poco de informacion de todas las empresas
    public List<Enterprise> listarTodas() throws SQLException, InstantiationException, IllegalAccessException {
        List<Enterprise> empresas = new ArrayList<Enterprise>();
        Conexion con;
        Enterprise enter = new Enterprise();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT NITempresa, nombre,descripcion ,correo, avatar, direccion FROM tblEmpresa";

        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql);

        rs.beforeFirst();

        while (rs.next()) {
            empresas.add(new Enterprise(rs.getString("NITempresa"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("correo"),
                    rs.getString("avatar"), rs.getString("direccion")));
        }

        return empresas;

    }
    //obtenemos la informacion de una empresa para los administradores

    public List<Enterprise> listarParaAdministradores() throws SQLException, InstantiationException, IllegalAccessException {
        List<Enterprise> empresas = new ArrayList<Enterprise>();
        Conexion con;
        Enterprise enter = new Enterprise();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT EM.NITempresa, EM.nombre, EM.ciudad, EM.correo, EM.telefono, EM.descripcion, EM.avatar, EM.direccion, EM.link_facebook, EM.link_instagram, EM.link_youtube, EM.link_twitter, EM.link_whatsapp FROM tblempresa as EM Inner Join tblusuarios as US on us.docIdentidad = EM.usuario";

        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql);

        rs.beforeFirst();

        while (rs.next()) {
            enter.setNITempresa(rs.getString("NITempresa"));
            enter.setNombre("nombre");
            enter.setCiudad(rs.getString("ciudad"));
            enter.setCorreo(rs.getString("correo"));
            enter.setTelefono(rs.getString("telefono"));
            enter.setDescripcion(rs.getString("descripcion"));
            enter.setAvatar(rs.getString("avatar"));
            enter.setDireccion(rs.getString("direccion"));
            enter.setLink_facebook(rs.getString("link_facebook"));
            enter.setLink_instagram(rs.getString("link_instagram"));
            enter.setLink_youtube(rs.getString("link_youtube"));
            enter.setLink_twitter(rs.getString("link_twitter"));
            enter.setLink_whatsapp(rs.getString("link_whatsapp"));

            empresas.add(new Enterprise(rs.getString("NITempresa"), rs.getString("nombre"), rs.getString("ciudad"), rs.getString("correo"),
                    rs.getString("telefono"), rs.getString("descripcion"), rs.getString("avatar"), rs.getString("direccion"), rs.getString("link_facebook"), rs.getString("link_instagram"),
                    rs.getString("link_youtube"), rs.getString("link_twitter"), rs.getString("link_whatsapp")));
        }

        return empresas;

    }

    //actualizamos la info de una empresa
    public boolean actualizarInformacion(Enterprise enterp, int NITempresa) {
        try {
            Conexion con;
            Connection cn = null;
            Statement st = null;
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            String sql = "UPDATE tblEmpresa SET nombre='" + enterp.getNombre() + "', correo='" + enterp.getCorreo() + "', telefono='" + enterp.getTelefono() + "',"
                    + "descripcion='" + enterp.getDescripcion() + "', link_facebook='" + enterp.getLink_facebook() + "', link_instagram='" + enterp.getLink_instagram() + "', "
                    + "link_youtube='" + enterp.getLink_youtube() + "', link_twitter='" + enterp.getLink_twitter() + "', link_whatsapp='" + enterp.getLink_whatsapp() + "' where NITempresa = '" + NITempresa + "'";
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
