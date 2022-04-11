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

/**
 *
 * @author Juan Carlos Osorio
 */
public class Cotizacion extends Conexion {

    private int codigo;
    private int NITempresa;
    private String docIdentidad_usuario;
    private String fecha;
    private int calificacion;
    private String comentario;

    public Cotizacion(int codigo, int NITempresa, String docIdentidad_usuario, String fecha, int calificacion, String comentario) {
        this.codigo = codigo;
        this.NITempresa = NITempresa;
        this.docIdentidad_usuario = docIdentidad_usuario;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public Cotizacion() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNITempresa() {
        return NITempresa;
    }

    public void setNITempresa(int NITempresa) {
        this.NITempresa = NITempresa;
    }

    public String getDocIdentidad_usuario() {
        return docIdentidad_usuario;
    }

    public void setDocIdentidad_usuario(String docIdentidad_usuario) {
        this.docIdentidad_usuario = docIdentidad_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    //insertamos los productos a la cotizacion
    public boolean insertarCotizacion(Cotizacion coti) throws InstantiationException, IllegalAccessException {
        Conexion con;
        Connection cn = null;
        Statement st = null;
        
        String sql = "INSERT INTO tblCotizacion VALUES(null, '"+coti.getNITempresa()+"', '"+coti.getDocIdentidad_usuario()+"', CURDATE(), null, null)";

        try {

            con = new Conexion();
            cn = con.conectar();
            st = cn.createStatement();
            
            st.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    //seleccionamos el codigo de la ultima cotizacion que se realizo
    public Cotizacion seleccionarCodigo(int NITempresa, String docIdentidad_usuario) throws SQLException, InstantiationException, IllegalAccessException{
        Conexion con;
        Cotizacion coti = new Cotizacion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        
        String sql = "SELECT codigo FROM tblcotizacion where NITempresa= '"+NITempresa+"' AND docIdentidad_usuario = '"+docIdentidad_usuario+"' Order by codigo desc LIMIT 1";
    
        con = new Conexion();
        cn = con.conectar();
        st=cn.createStatement();
        rs=st.executeQuery(sql);
        
        if(rs.next()==true){
            coti.setCodigo(rs.getInt("codigo"));
        }
        
        return coti;
    }

}
