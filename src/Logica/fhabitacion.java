/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vhabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GRACIELA
 */
public class fhabitacion {
    
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    
    
    public DefaultTableModel mostrar(String buscar){
        
        DefaultTableModel modelo;
        
        //array para almacenar los titulos no necesarimente tiene que ser igual a las columnas de nuestra base de datos
        String [] titulos = {"ID","Numero","Piso","Descripcion","Caracteristicas","Precio","Estado","Tipo habitacion"};
        
        //array para almacenar los registros de los titulos
        String []  registro = new String [8];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL="select * from habitacion where piso like '%"+ buscar + "%' order by idhabitacion";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //hago nabegacion de todos los registros recorriendolos de 1 en 1 todas las columnas que por ende las columnas tienen que ser iguales a las de la base de datos 
            
            while(rs.next()){
            registro [0]=rs.getString("idhabitacion");
            registro [1]=rs.getString("numero");
            registro [2]=rs.getString("piso");
            registro [3]=rs.getString("descripcion");
            registro [4]=rs.getString("caracteristicas");
            registro [5]=rs.getString("precio_diario");
            registro [6]=rs.getString("estado");
            registro [7]=rs.getString("tipo_habitacion");
            
            totalregistros=totalregistros+1;
            modelo.addRow(registro);
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    //INSERTAR
    public boolean insertar (vhabitacion dts){
        //cuando es una consulta sql se debe escribir tal cual esta en la base de datos
        sSQL="insert into habitacion (numero,piso,descripcion,caracteristicas,precio_diario,estado,tipo_habitacion)"+
                "values (?,?,?,?,?,?,?)";
        try {
         
            PreparedStatement pst=cn.prepareStatement(sSQL);
            //ahora se va a enviar todos los valores al registros
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
    }
    //EDITAR
    public boolean editar (vhabitacion dts){
        /*sSQL="update habitacion set numero=?,piso=?,descripcion=?,caracteristicas=?,precio_diario=?,estado=?,tipo_habitacion=? "+
                "where idhabitacion=?";*/
        
        sSQL = "update habitacion set numero=?,piso=?,descripcion=?,caracteristicas=?,precio_diario=?,estado=?,tipo_habitacion=?" +
       " where idhabitacion=?";

        
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            //ahora se va a enviar todos los valores al registros
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            pst.setInt(8, dts.getIdhabitacion());
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
        
        
    }    
        
    //EDITAR
    public boolean eliminar (vhabitacion dts){
        sSQL=" delete from habitacion where idhabitacion=?";
        
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
           
            pst.setInt(1, dts.getIdhabitacion());
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
    }
    
     public DefaultTableModel mostrarvista(String buscar){
        
        DefaultTableModel modelo;
        
        //array para almacenar los titulos no necesarimente tiene que ser igual a las columnas de nuestra base de datos
        String [] titulos = {"ID","Numero","Piso","Descripcion","Caracteristicas","Precio","Estado","Tipo habitacion"};
        
        //array para almacenar los registros de los titulos
        String []  registro = new String [8];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL="select * from habitacion where piso like '%"+ buscar + "%' and estado='Disponible' order by idhabitacion";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //hago nabegacion de todos los registros recorriendolos de 1 en 1 todas las columnas que por ende las columnas tienen que ser iguales a las de la base de datos 
            
            while(rs.next()){
            registro [0]=rs.getString("idhabitacion");
            registro [1]=rs.getString("numero");
            registro [2]=rs.getString("piso");
            registro [3]=rs.getString("descripcion");
            registro [4]=rs.getString("caracteristicas");
            registro [5]=rs.getString("precio_diario");
            registro [6]=rs.getString("estado");
            registro [7]=rs.getString("tipo_habitacion");
            
            totalregistros=totalregistros+1;
            modelo.addRow(registro);
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
     public boolean desocupar (vhabitacion dts){
        /*sSQL="update habitacion set numero=?,piso=?,descripcion=?,caracteristicas=?,precio_diario=?,estado=?,tipo_habitacion=? "+
                "where idhabitacion=?";*/
        
        sSQL = "update habitacion set estado='Disponible'" +
       " where idhabitacion=?";

        
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            //ahora se va a enviar todos los valores al registros
            pst.setInt(1, dts.getIdhabitacion());
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
        
        
    }
     
    public boolean ocupar (vhabitacion dts){
        /*sSQL="update habitacion set numero=?,piso=?,descripcion=?,caracteristicas=?,precio_diario=?,estado=?,tipo_habitacion=? "+
                "where idhabitacion=?";*/
        
        sSQL = "update habitacion set estado='Ocupado'" +
       " where idhabitacion=?";

        
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            //ahora se va a enviar todos los valores al registros
            pst.setInt(1, dts.getIdhabitacion());
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
        
        
    }    
    
    

}

