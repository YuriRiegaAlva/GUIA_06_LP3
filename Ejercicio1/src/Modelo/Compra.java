package Modelo;

/**
 * Compra.java
 *
 * A class is a blueprint for objects.
 *
 * - Fields store state (what the object knows).
 * - Methods define behavior (what the object can do).
 *
 * Add yours below, then use Compra from Main:
 *   Compra x = new Compra();
 */
public class Compra {
    private Producto comprado;
    private Integer cantidad_comprada;

    public Compra(Producto comprado, Integer cantidad_comprada){
        this.comprado = comprado;
        this.cantidad_comprada = cantidad_comprada;
    }

    public Producto getProductoComprado(){
        return this.comprado;
    }
    public void setProductoComprado(Producto comprado){
        this.comprado = comprado;
    }

    public Integer getCantidadComprada(){
        return this.cantidad_comprada;
    }
    public void setCantidadComprada(Integer cantidad_comprada){
        this.cantidad_comprada = cantidad_comprada;
    }

    public double calcularTotal(){
        return this.comprado.getPrecio() * this.cantidad_comprada;
    }
}
