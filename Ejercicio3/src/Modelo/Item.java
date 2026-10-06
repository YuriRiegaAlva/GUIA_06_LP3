package Modelo;

/**
 * Item.java
 *
 * A class is a blueprint for objects.
 *
 * - Fields store state (what the object knows).
 * - Methods define behavior (what the object can do).
 *
 * Add yours below, then use Item from Main:
 *   Item x = new Item();
 */
public class Item {
    private String nombre;
    private int cantidad;
    private String tipo;
    private String description;
    private int valor;

    public Item(String nombre, int cantidad, String tipo, String description) {
        this(nombre, cantidad, tipo, description, calcularValorPorDefecto(tipo));
    }

    public Item(String nombre, int cantidad, String tipo, String description, int valor) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.description = description;
        this.valor = valor;
    }

    private static int calcularValorPorDefecto(String tipo) {
        if (tipo == null) {
            return 10;
        }
        String normalizado = tipo.trim().toLowerCase();
        if (normalizado.contains("arma")) {
            return 15;
        } else if (normalizado.contains("pocion") || normalizado.contains("poción")) {
            return 25;
        }
        return 10;
    }
    public boolean usarItem(){
        if(this.cantidad > 0){
            this.cantidad -=1;
            return true;
        }
        return false;
        
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public int getCantidad (){
        return this.cantidad;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    public String getTipo(){
        return this.tipo;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
