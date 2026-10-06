package Pedidos;
import java.util.ArrayList;
import java.util.List;

public class Pedido_modelo {
    private List<Pedido> pedidos;

    public Pedido_modelo(){
        this.pedidos = new ArrayList<>();
    }

    // --- OPERACIONES  ---

    public void agregarPedido(Pedido ped){
        this.pedidos.add(ped);
    }

    public void actualizarPedido(Pedido ped, String nuevoNombre){
        ped.setNombre(nuevoNombre);
    }

    public boolean eliminarPedido(Pedido ped){
        return this.pedidos.remove(ped);
    }

    // --- BÚSQUEDAS ---

    public Pedido buscarPedidoNombre(String nombre){
        for(Pedido pedido : this.pedidos){
            if(pedido.getNombre().equalsIgnoreCase(nombre)){
                return pedido;
            }
        }
        return null;
    }

    public List<Pedido> buscarPedidoTipo(String tipo){
        List<Pedido> concidencias = new ArrayList<>();
        for(Pedido pedido : this.pedidos){
            if(pedido.getTipo().equalsIgnoreCase(tipo)){
                concidencias.add(pedido);
            }
        }
        return concidencias;
    }

    // --- CONTEOS  ---

    public int contarTotal(){
        return this.pedidos.size();
    }

    public int contarTipo(String tipo){
        int contador = 0;
        for (Pedido ped : this.pedidos){
            if(ped.getTipo().equalsIgnoreCase(tipo)){
                contador++;
            }
        }
        return contador;
    }

    // --- GETTERS ---

    public List<Pedido> getPedidos(){
        return this.pedidos;
    }
}
