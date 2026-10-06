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

    // --- GETTERS ---

    public List<Pedido> getPedidos(){
        return this.pedidos;
    }
}
