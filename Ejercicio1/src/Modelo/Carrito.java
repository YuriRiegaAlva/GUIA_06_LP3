package Modelo;

import java.util.HashMap;
import java.util.Map;

public class Carrito {
    private Map<Producto,Integer> productosEnCarrito;
    private double  precioTotal;
    public Carrito(){
        productosEnCarrito = new HashMap<>();
        precioTotal = 0.0;
    }

    public void agregarProducto (Producto p,int cantidad){
        int actual = this.productosEnCarrito.getOrDefault(p, 0);
        this.productosEnCarrito.put(p,cantidad+actual);
    }
    public void eliminarProducto (Producto p){
        this.productosEnCarrito.remove(p);
    }
    public Map<Producto,Integer> getCarrito(){
        return this.productosEnCarrito;
    }

    public double calcularSubtotalItem(Producto p){
        int cantidad = this.productosEnCarrito.getOrDefault(p, 0);
        return p.getPrecio() * cantidad;
    }

    public double calculaSubtotal(){
        double subTotal = 0;
        for(Producto p : this.productosEnCarrito.keySet()){
            subTotal = subTotal +(p.getPrecio()*this.productosEnCarrito.get(p));
        }
        return subTotal;
    }
    public double aplicarDescuento(double subtotal,double descuento){
        return subtotal *(descuento/100);
    }
    
    public double calcularEnvio(double subtotal){
       return   (subtotal*0.05);
    }

    public double calcularTotal(double porcentaje_descuento){
        double subtotal = calculaSubtotal();
        
        return this.precioTotal=(subtotal-this.aplicarDescuento(subtotal,porcentaje_descuento)+this.calcularEnvio(subtotal));
    }
    public void setPrecioTotal(double precio){
        this.precioTotal = precio;
    }
    public double getPrecioTotal(){
        return this.precioTotal;
    }
    public void vaciarCarrito(){
        this.productosEnCarrito.clear();
    }
}
