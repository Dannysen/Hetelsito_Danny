/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vcliente;
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
public class fcliente {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;

        //array para almacenar los titulos no necesarimente tiene que ser igual a las columnas de nuestra base de datos
        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Doc", "Número Documento", "Direccion", "Teléfono", "Email", "Código"};

        //array para almacenar los registros de los titulos
        String[] registro = new String[10];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombre,p.apaterno,p.amaterno,p.tipo_documento,p.num_documento,"
                + "p.direccion,p.telefono,p.email,c.codigo_cliente from persona p inner join cliente c "
                + "on p.idpersona=c.idpersona where num_documento like '%"
                + buscar + "%' order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            //hago nabegacion de todos los registros recorriendolos de 1 en 1 todas las columnas que por ende las columnas tienen que ser iguales a las de la base de datos 
            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apaterno");
                registro[3] = rs.getString("amaterno");
                registro[4] = rs.getString("tipo_documento");
                registro[5] = rs.getString("num_documento");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("email");
                registro[9] = rs.getString("codigo_cliente");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    //INSERTAR
    public boolean insertar(vcliente dts) {
        //cuando es una consulta sql se debe escribir tal cual esta en la base de datos
        sSQL = "insert into persona (nombre,apaterno,amaterno,tipo_documento,num_documento,direccion,telefono,email)"
                + "values (?,?,?,?,?,?,?,?)";
        //Despues de registrar los datos de una persona, tambien se debe registrar cliente ¿Porque? asi esta en la base de datos
        sSQL2 = "insert into cliente (idpersona,codigo_cliente)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            //ahora se va a enviar todos los valores al registros
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getAmaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());

            pst2.setString(1, dts.getCodigo_cliente());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    //EDITAR
    public boolean editar(vcliente dts) {
        sSQL = "update persona set nombre=?,apaterno=?,amaterno=?,tipo_documento=?,num_documento=?,"
                + "direccion=?, telefono=?, email=? where idpersona=?";
        
        sSQL2 = "update cliente set codigo_cliente=?"
                + " where idpersona=?";

         try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            //ahora se va a enviar todos los valores al registros
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getAmaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getIdpersona());
            

            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getIdpersona());
            

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    //Eliminar
    public boolean eliminar(vcliente dts) {
        sSQL = " delete from cliente where idpersona=?";
        sSQL2 = " delete from persona where idpersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            //ahora se va a enviar todos los valores al registros
           
            pst.setInt(1, dts.getIdpersona());
            

            
            pst2.setInt(1, dts.getIdpersona());
            

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
