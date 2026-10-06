package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * InventarioModelo.java
 *
 * A class is a blueprint for objects.
 *
 * - Fields store state (what the object knows).
 * - Methods define behavior (what the object can do).
 *
 * Add yours below, then use InventarioModelo from Main:
 *   InventarioModelo x = new InventarioModelo();
 */
public class InventarioModelo {
    private List<Item> items;

    public InventarioModelo(){
        this.items= new ArrayList<>();
    }

    public boolean agregarItem(Item item){
        if(item == null){
            return false;
        }
        this.items.add(item);
        return true;
    }
    public boolean eliminarItem (Item item){
        if(item == null){
            return false;
        }
        this.items.remove(item);
        return true;
    }
    public List<Item> obtenerItems(){
        return this.items;
    }

    public Item buscarItem(String nombre) {
        if (nombre == null || this.items.isEmpty()) {
            return null;
        }
        for (Item item : this.items) {
            if (item.getNombre().equalsIgnoreCase(nombre.trim())) {
                return item;
            }
        }
        return null;
    }
}
