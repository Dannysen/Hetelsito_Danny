/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vhabitacion;
import Datos.vpago;
import Datos.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class fpago {
    
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    
    
    public DefaultTableModel mostrar(String buscar){
        
        DefaultTableModel modelo;
        
        //array para almacenar los titulos no necesarimente tiene que ser igual a las columnas de nuestra base de datos
        String [] titulos = {"ID","idreserva","Comprobante","Numero","Igv","Total","Fecha Emisión","Fecha Pago"};
        
        //array para almacenar los registros de los titulos
        String []  registro = new String [8];
        
        totalregistros=0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL="select * from pago where idreserva="+ buscar + " order by idpago desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //hago nabegacion de todos los registros recorriendolos de 1 en 1 todas las columnas que por ende las columnas tienen que ser iguales a las de la base de datos 
            
            while(rs.next()){
            registro [0]=rs.getString("idpago");
            registro [1]=rs.getString("idreserva");
            registro [2]=rs.getString("tipo_comprobante");
            registro [3]=rs.getString("num_comprobante");
            registro [4]=rs.getString("igv");
            registro [5]=rs.getString("total_pago");
            registro [6]=rs.getString("fecha_emision");
            registro [7]=rs.getString("fecha_pago");
            
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
    public boolean insertar (vpago dts){
        //cuando es una consulta sql se debe escribir tal cual esta en la base de datos
        sSQL="insert into pago (idreserva,tipo_comprobante,num_comprobante,igv,total_pago,fecha_emision,fecha_pago)"+
                "values (?,?,?,?,?,?,?)";
        try {
         
            PreparedStatement pst=cn.prepareStatement(sSQL);
            //ahora se va a enviar todos los valores al registros
            pst.setInt(1, dts.getIdreserva());
            pst.setString(2, dts.getTipo_comprobante());
            pst.setString(3, dts.getNum_comprobante());
            pst.setDouble(4, dts.getIgv());
            pst.setDouble(5, dts.getTotal_pago());
            pst.setDate(6, dts.getFecha_emision());
            pst.setDate(7, dts.getFecha_pago());

            
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
    public boolean editar (vpago dts){
        sSQL="update pago set idreserva=?,tipo_comprobante=?,num_comprobante=?,igv=?,total_pago=?,fecha_emision=?,fecha_pago=?"+
                " where idpago=?";
        
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            //ahora se va a enviar todos los valores al registros
            pst.setInt(1, dts.getIdreserva());
            pst.setString(2, dts.getTipo_comprobante());
            pst.setString(3, dts.getNum_comprobante());
            pst.setDouble(4, dts.getIgv());
            pst.setDouble(5, dts.getTotal_pago());
            pst.setDate(6, dts.getFecha_emision());
            pst.setDate(7, dts.getFecha_pago());

            pst.setInt(8, dts.getIdpago());
            
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
    public boolean eliminar (vpago dts){
        sSQL=" delete from pago where idpago=?";
        
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
           
            pst.setInt(1, dts.getIdpago());
            
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
