/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vconsumo;
import Datos.vhabitacion;
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
public class fconsumo {
    
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    public Double totalconsumo;
    
    
    
    public DefaultTableModel mostrar(String buscar){
        
        DefaultTableModel modelo;
        
        //array para almacenar los titulos no necesarimente tiene que ser igual a las columnas de nuestra base de datos
        String [] titulos = {"ID","idreserva","idproducto","Producto","Cantidad","Precio Venta","Estado"};
        
        //array para almacenar los registros de los titulos
        String []  registro = new String [7];
        
        totalregistros=0;
        totalconsumo=0.0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL="select c.idconsumo,c.idreserva,c.idproducto,p.nombre,c.cantidad,c.precio_venta"
                + ",c.estado from consumo c inner join producto p on c.idproducto=p.idproducto"
                + " where c.idreserva ="+ buscar + " order by c.idconsumo desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //hago nabegacion de todos los registros recorriendolos de 1 en 1 todas las columnas que por ende las columnas tienen que ser iguales a las de la base de datos 
            
            while(rs.next()){
            registro [0]=rs.getString("idconsumo");
            registro [1]=rs.getString("idreserva");
            registro [2]=rs.getString("idproducto");
            registro [3]=rs.getString("nombre");
            registro [4]=rs.getString("cantidad");
            registro [5]=rs.getString("precio_venta");
            registro [6]=rs.getString("estado");
            
            totalregistros=totalregistros+1;
            totalconsumo=totalconsumo+ (rs.getDouble("cantidad") * rs.getDouble("precio_venta"));
            modelo.addRow(registro);
            
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    //INSERTAR
    public boolean insertar (vconsumo dts){
        //cuando es una consulta sql se debe escribir tal cual esta en la base de datos
        sSQL="insert into consumo (idreserva,idproducto,cantidad,precio_venta,estado)"+
                "values (?,?,?,?,?)";
        try {
         
            PreparedStatement pst=cn.prepareStatement(sSQL);
            //ahora se va a enviar todos los valores al registros
            pst.setInt(1, dts.getIdreserva());
            pst.setInt(2, dts.getIdproducto());
            pst.setDouble(3, dts.getCantidad());
            pst.setDouble(4, dts.getPrecio_venta());
            pst.setString(5, dts.getEstado());

            
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
    public boolean editar (vconsumo dts){
        sSQL="update consumo set idreserva=?,idproducto=?,cantidad=?,precio_venta=?,estado=?"+
                " where idconsumo=?";
        
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            //ahora se va a enviar todos los valores al registros
            pst.setInt(1, dts.getIdreserva());
            pst.setInt(2, dts.getIdproducto());
            pst.setDouble(3, dts.getCantidad());
            pst.setDouble(4, dts.getPrecio_venta());
            pst.setString(5, dts.getEstado());;

            pst.setInt(6, dts.getIdconsumo());
            
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
    public boolean eliminar (vconsumo dts){
        sSQL=" delete from consumo where idconsumo=?";
        
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
           
            pst.setInt(1, dts.getIdconsumo());
            
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
