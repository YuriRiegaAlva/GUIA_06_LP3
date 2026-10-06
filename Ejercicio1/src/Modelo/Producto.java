package Modelo;

import java.util.Objects;


public class Producto {
    private String nombre;
    private String id;
    private double precio;
    private int stock;

    public Producto(String nombre, String id,double precio, int stock){
        this.nombre = nombre;
        this.id = id;
        this.stock = stock;
        this.precio = precio;
        
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre (){
        return this.nombre;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }

    public void setStock(int stock){
        this.stock = stock;
    }
    public int getStock(){
        return this.stock;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }
    public double getPrecio(){
        return this.precio;
    }
    @Override 
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object == null || object.getClass() != this.getClass()){
            return false;
        }
        Producto producto = (Producto)object;
        return (this.id.equals(producto.id));
    }

    @Override 
    public int hashCode(){
        return Objects.hash(this.id);
    }
    
}
