/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


public class Quote {
    
    private int codigo;
    private Enterprise NITempresa;
    private User docIdentidad_usuario;
    private String fecha;
    private int calificacion;
    private String comentario;
    
    public Quote(){
    
    }
    
    public Quote(int codigo,
    Enterprise NITempresa,
    User docIdentidad_usuario,
    String fecha,
    int calificacion,
    String comentario){
        this.codigo=codigo;
        this.NITempresa=NITempresa;
        this.docIdentidad_usuario=docIdentidad_usuario;
        this.fecha=fecha;
        this.calificacion=calificacion;
        this.comentario=comentario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Enterprise getNITempresa() {
        return NITempresa;
    }

    public void setNITempresa(Enterprise NITempresa) {
        this.NITempresa = NITempresa;
    }

    public User getDocIdentidad_usuario() {
        return docIdentidad_usuario;
    }

    public void setDocIdentidad_usuario(User docIdentidad_usuario) {
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
    
    
    
    
    
}
