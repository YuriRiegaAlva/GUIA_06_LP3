package Pedidos;

public class Pedido {
    private String nombrePedido;

    public Pedido(String nombrePedido){
        this.nombrePedido = nombrePedido;
    }

    public String getNombre(){
        return this.nombrePedido;
    }
    public void setNombre(String nombrePedido){
        this.nombrePedido = nombrePedido;
    }
}
