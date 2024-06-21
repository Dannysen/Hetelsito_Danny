/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author ASUS
 */
public class vproducto {
    
    private int idproducto;
    private String nombre;
    private String descripcion;
    private String unidad_medida;
    private Double precio_venta;

    public vproducto() {
    }

    public vproducto(int idproductos, String nombre, String descripcion, String unidad_medida, Double precio_venta) {
        this.idproducto = idproductos;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidad_medida = unidad_medida;
        this.precio_venta = precio_venta;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproductos) {
        this.idproducto = idproductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
    
}
