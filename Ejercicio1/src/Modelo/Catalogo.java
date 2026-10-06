package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Catalogo {
    private Map<String,Producto> catalogo;

    public Catalogo (){
        this.catalogo = new HashMap<>();
    }
    
    public void agregarProductos(Producto producto){
        this.catalogo.put(producto.getId(), producto);
    }
    public List<Producto> listarProductos(){
        return new ArrayList<>(this.catalogo.values());
    }
    public Producto buscarProducto(String key){
        return this.catalogo.get(key);
    }
    
}
