/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class probar {
    //vamos a probar la conexion
    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException{
    Conexion c = new Conexion();
    
    if(c.conectar() != null){
        System.out.println("La conexion es correcta");
    }else{
        System.out.println("La conexion no es correcta");
    }
    }
    
}
