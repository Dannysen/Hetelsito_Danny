/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author GRACIELA
 */
public class conexion {
    
    public String db="basereserva";
    //public String url="jdbc:mysql://127.0.0.1/" +db;
    public String url="jdbc:mysql://localhost/" +db;
    public String user="root";
    public String pass="";

    public conexion() {
    }
    
    //funcion para conectarnos a la base de datos
    public Connection conectar(){
        Connection link=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("org.gjt.mm.mysql.Driver");
            link=DriverManager.getConnection(this.url,this.user,this.pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            System.out.println("error "+ e.getMessage());
        }
        
        return link;
    }
            
    
}
