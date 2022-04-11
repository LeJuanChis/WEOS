/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
//heredamos la clase conexion
public class User extends Conexion {

    public String documento;
    public String nombre;
    public String apellidos;
    public String telefono;
    public String correo;
    public String contraseña;
    public String avatar;
    public String ciudad;
    public String fecha_nacimiento;
    public int estado;
    public String rol;
    //consultamos a otras tablas con inner join
    public Roles roles;
    public Enterprise enterprise;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    Connection cnn = null;

    //creamos los contructores
    public User() {

    }

    public User(String documento,
            String nombre,
            String apellidos,
            String telefono,
            String correo,
            String contraseña,
            String avatar,
            String ciudad,
            String fecha_nacimiento,
            int estado,
            String rol) {

        this.documento = documento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.avatar = avatar;
        this.ciudad = ciudad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado = estado;
        this.rol = rol;

    }

    public User(String documento, String nombre, String apellidos, String telefono, String correo, String avatar, String ciudad, String fecha_nacimiento, Roles roles, Enterprise enterprise) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.avatar = avatar;
        this.ciudad = ciudad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.roles = roles;
        this.enterprise = enterprise;
    }

    public User(String documento, String nombre, String apellidos, String telefono, String correo, String avatar, String ciudad, String fecha_nacimiento, Roles roles) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.avatar = avatar;
        this.ciudad = ciudad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.roles = roles;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    //seleccionar el usuario asociado a una empresa
    public User seleccionarPorEmpresa(int NITempresa) throws SQLException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        Conexion con;
        User user = new User();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT US.nombre, US.docIdentidad, US.apellido, "
                + "US.contrasenia, US.telefono, US.correo, US.fecha_nacimiento from "
                + "tblusuarios as US Inner Join tblempresa as EM on US.docIdentidad = EM.usuario "
                + "WHERE EM.NITempresa = '" + NITempresa + "'";
        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement();
        rs = st.executeQuery(sql);

        if (rs.next() == true) {
            user.setDocumento(rs.getString("docIdentidad"));
            user.setNombre(rs.getString("nombre"));
            user.setApellidos(rs.getString("apellido"));
            user.setContraseña(rs.getString("contrasenia"));
            user.setTelefono(rs.getString("telefono"));
            user.setCorreo(rs.getString("correo"));
            user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
        }
        return user;

    }

    //seleccionamos la informacion de todos los usuarios que tengan una empresa asociada para un administrador
    public List<User> listarTodosConEmpresa() throws SQLException, InstantiationException, IllegalAccessException {
        List<User> usuarios = new ArrayList<User>();
        Conexion con;
        User user = new User();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT US.docIdentidad, US.nombre, US.apellido, US.telefono, US.correo, US.avatar, US.ciudad, US.fecha_nacimiento, RL.nombreRol, EM.NITempresa, EM.nombre FROM tblusuarios as US Inner Join tblempresa as EM on US.docIdentidad = EM.usuario inner Join tblroles as RL on RL.codigo = US.rol";

        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql);

        rs.beforeFirst();

        while (rs.next()) {
            user.setDocumento(rs.getString("docIdentidad"));
            user.setNombre(rs.getString("nombre"));
            user.setApellidos(rs.getString("apellido"));
            user.setTelefono(rs.getString("telefono"));
            user.setCorreo(rs.getString("correo"));
            user.setAvatar(rs.getString("avatar"));
            user.setCiudad(rs.getString("ciudad"));
            user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
            user.setRoles(new Roles());
            user.getRoles().setNombreRol(rs.getString("nombreRol"));
            user.setEnterprise(new Enterprise());
            user.getEnterprise().setNITempresa(rs.getString("NITempresa"));
            user.getEnterprise().setNombre(rs.getString("nombre"));
            usuarios.add(new User(rs.getString("docIdentidad"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"),
                    rs.getString("correo"), rs.getString("avatar"), rs.getString("ciudad"), rs.getString("fecha_nacimiento"),
                    user.roles, user.enterprise));
        }

        return usuarios;

    }

    //seleccionamos la informacion de todos los usuarios que NO tengan una empresa asociada para un administrador
    public List<User> listarTodosSinEmpresa() throws SQLException, InstantiationException, IllegalAccessException {
        List<User> usuarios = new ArrayList<User>();
        Conexion con;
        User user = new User();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT US.docIdentidad, US.nombre, US.apellido, US.telefono, US.correo, US.avatar, US.ciudad, US.fecha_nacimiento, RL.nombreRol FROM tblusuarios as US Inner Join tblroles as RL on RL.codigo = US.rol AND RL.nombreRol <> 'Empresa' AND RL.nombreRol <> 'Admin'";

        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql);

        rs.beforeFirst();

        while (rs.next()) {
            user.setDocumento(rs.getString("docIdentidad"));
            user.setNombre(rs.getString("nombre"));
            user.setApellidos(rs.getString("apellido"));
            user.setTelefono(rs.getString("telefono"));
            user.setCorreo(rs.getString("correo"));
            user.setAvatar(rs.getString("avatar"));
            user.setCiudad(rs.getString("ciudad"));
            user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
            user.setRoles(new Roles());
            user.getRoles().setNombreRol(rs.getString("nombreRol"));
            usuarios.add(new User(rs.getString("docIdentidad"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"),
                    rs.getString("correo"), rs.getString("avatar"), rs.getString("ciudad"), rs.getString("fecha_nacimiento"),
                    user.roles));
        }

        return usuarios;

    }
        //listamos todos los administradores
        public List<User> listarTodosAdministradores() throws SQLException, InstantiationException, IllegalAccessException {
        List<User> usuarios = new ArrayList<User>();
        Conexion con;
        User user = new User();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT US.docIdentidad, US.nombre, US.apellido, US.telefono, US.correo, US.avatar, US.ciudad, US.fecha_nacimiento, RL.nombreRol FROM tblusuarios as US Inner Join tblroles as RL on RL.codigo = US.rol AND RL.nombreRol = 'Admin'";

        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql);

        rs.beforeFirst();

        while (rs.next()) {
            user.setDocumento(rs.getString("docIdentidad"));
            user.setNombre(rs.getString("nombre"));
            user.setApellidos(rs.getString("apellido"));
            user.setTelefono(rs.getString("telefono"));
            user.setCorreo(rs.getString("correo"));
            user.setAvatar(rs.getString("avatar"));
            user.setCiudad(rs.getString("ciudad"));
            user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
            user.setRoles(new Roles());
            user.getRoles().setNombreRol(rs.getString("nombreRol"));
            usuarios.add(new User(rs.getString("docIdentidad"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"),
                    rs.getString("correo"), rs.getString("avatar"), rs.getString("ciudad"), rs.getString("fecha_nacimiento"),
                    user.roles));
        }

        return usuarios;

    }

    public User seleccionarUnUsuario(int docIdent) throws SQLException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        Conexion con;
        User user = new User();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT docIdentidad, nombre, apellido, contrasenia, telefono, correo, fecha_nacimiento "
                + "FROM tblUsuarios where docIdentidad= '" + docIdent + "'";
        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement();
        rs = st.executeQuery(sql);

        if (rs.next() == true) {
            user.setDocumento(rs.getString("docIdentidad"));
            user.setNombre(rs.getString("nombre"));
            user.setApellidos(rs.getString("apellido"));
            user.setContraseña(rs.getString("contrasenia"));
            user.setTelefono(rs.getString("telefono"));
            user.setCorreo(rs.getString("correo"));
            user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
        }
        return user;

    }

    public boolean crearCuenta(User user) throws SQLException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {
        try {
            Conexion con;
            User usu = new User();
            Connection cn = null;
            Statement st = null;
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            String sql = "INSERT INTO tblUsuarios VALUES('" + user.getDocumento() + "', '" + user.getNombre() + "',"
                    + "'" + user.getApellidos() + "', '" + getMD5(user.getContraseña()) + "'"
                    + ",'" + user.getTelefono() + "','" + user.getCorreo() + "', "
                    + "'" + user.getAvatar() + "', null, "
                    + "'" + user.getFecha_nacimiento() + "' ,'1', null);";
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

    public void actualizarRolUsuario(String docIdent) throws SQLException, InstantiationException, IllegalAccessException {
        Conexion con;
        Connection cn = null;
        Statement st = null;
        con = new Conexion();
        cn = con.conectar();
        st = cn.createStatement();
        String sql = "UPDATE tblUsuarios SET rol=2 where docIdentidad='" + docIdent + "'";
        st.executeUpdate(sql);
    }

    public List<User> getAll() throws SQLException {
        List<User> allPerson = new ArrayList<User>();
        Statement st = cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = st.executeQuery("SELECT * FROM tblUsuarios");

        rs.beforeFirst();
        while (rs.next()) {
            allPerson.add(new User(rs.getString("docIdentidad"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"),
                    rs.getString("correo"), rs.getString("contrasenia"), rs.getString("avatar"), rs.getString("ciudad"), rs.getString("fecha_nacimiento"), rs.getInt("estado"), rs.getString("rol")));

        }

        return allPerson;
    }

    public User login(User user) throws SQLException, IllegalAccessException, NoSuchAlgorithmException {
        Conexion con;
        User usu = new User();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT US.docIdentidad,US.nombre, US.apellido, US.telefono, "
                + "US.correo, US.fecha_nacimiento, US.estado, RL.nombreRol FROM tblUsuarios as "
                + "US Inner Join tblRoles as RL on RL.codigo = US.rol WHERE US.correo= '" + user.getCorreo() + "' AND "
                + "US.contrasenia ='" + getMD5(user.getContraseña()) + "'";
        int i = 0;
        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next() == true) {

                usu.setDocumento(rs.getString("docIdentidad"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellidos(rs.getString("apellido"));
                usu.setTelefono(rs.getString("telefono"));
                usu.setCorreo(rs.getString("correo"));
                usu.setFecha_nacimiento(rs.getString("fecha_nacimiento"));

                usu.setRoles(new Roles());
                usu.getRoles().setNombreRol(rs.getString("nombreRol"));

                //usu.setEstado(rs.getInt("estado"));
            } else {

            }

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;

            if (st != null && st.isClosed() == false) {
                st.close();
            }
            st = null;

            if (cn != null && cn.isClosed() == false) {
                cn.close();
            }
            cn = null;
        }

        return usu;
    }

    public boolean actualizarUsuario(User user, int docIdent) throws SQLException, NoSuchAlgorithmException {

        try {
            Conexion con;
            Connection cn = null;
            Statement st = null;
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            String sql = "UPDATE tblUsuarios SET docIdentidad='" + user.getDocumento() + "', nombre='" + user.getNombre() + "',"
                    + "apellido='" + user.getApellidos() + "', telefono='" + user.getTelefono() + "',"
                    + "correo='" + user.getCorreo() + "' where docIdentidad='" + docIdent + "'";
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

    private void setRol(Roles rl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //encriptar la contraseña
    private String getMD5(String password) throws NoSuchAlgorithmException {
        String encriptacionString = "null";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] encriptacionBytes = md.digest(password.getBytes());

            BigInteger numero = new BigInteger(1, encriptacionBytes);

            encriptacionString = numero.toString(16);

            while (encriptacionString.length() < 32) {
                encriptacionString = "0" + encriptacionString;
            }

        } catch (Exception e) {

            System.out.println(e);;
        }

        return encriptacionString;
    }

}
