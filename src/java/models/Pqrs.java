/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Carlos Osorio
 */
public class Pqrs extends Conexion{
    private int codigo;
    private String docIdentidad_usuario;
    private String descripcion;
    private String fecha;

    public Pqrs() {
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDocIdentidad_usuario() {
        return docIdentidad_usuario;
    }

    public void setDocIdentidad_usuario(String docIdentidad_usuario) {
        this.docIdentidad_usuario = docIdentidad_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    //insertamos la pqrs
    public boolean insertarPqrs(Pqrs pqrs){
            try {
            Conexion con;
            User usu = new User();
            Connection cn = null;
            Statement st = null;
            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            String sql = "INSERT INTO tblOpinionesPagina VALUES(null, '"+pqrs.getDocIdentidad_usuario()+"', '"+pqrs.getDescripcion()+"', NOW())";
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
