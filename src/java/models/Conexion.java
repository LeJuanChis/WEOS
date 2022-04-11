/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Conexion {
    
    //creamos las constantes
    private final String url = "jdbc:mysql://localhost:3306/weos";//definimos la ruta
    private final String user = "root";//el usuario
    private final String pass = "";//y la contraseña

    public Connection conectar() throws SQLException, InstantiationException, IllegalAccessException {
        Connection cnn = null;
        ResultSet resulset = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cnn = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al conectar" + ex.getMessage());

        }

        return cnn;
    }
}
