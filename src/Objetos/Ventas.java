/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

public class Ventas {
    int idVenta; 
    int unidades;
    int precioVenta;
    String fechaVenta;
    int idProducto; 
    int idUsuario;

    public Ventas() {
        
        this.idUsuario = ObAn.usuario.idUsuario;
    }

    public Ventas(int idVenta, int unidades, int precioVenta, String fechaVenta, int idProducto, int idUsuario) {
        this.idVenta = idVenta;
        this.unidades = unidades;
        this.precioVenta = precioVenta;
        this.fechaVenta = fechaVenta;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
    }
    
    
    
    
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
