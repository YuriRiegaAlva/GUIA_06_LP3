package Modelo;

public class Item {
    private String nombre;
    private int cantidad;
    private String tipo;
    private String description;

    public Item(String nombre, int cantidad, String tipo, String description){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.description = description;
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
}
