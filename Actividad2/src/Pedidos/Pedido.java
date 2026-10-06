package Pedidos;

import java.util.Objects;


public class Pedido {
    private String nombrePedido;
    private String tipo;

    public Pedido(String nombrePedido, String tipo){
        this.nombrePedido = nombrePedido;
        this.tipo = tipo;
    }

    public String getNombre(){
        return this.nombrePedido;
    }
    public void setNombre(String nombrePedido){
        this.nombrePedido = nombrePedido;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    @Override 
    public boolean equals(Object pedido){
        if(this == pedido){
            return true;
        }
        if(pedido == null || this.getClass()!=pedido.getClass()){
            return false;
        }
        Pedido plato = (Pedido) pedido;

        return this.nombrePedido.equals(plato.nombrePedido)&&this.tipo.equals(plato.tipo);
    }
    @Override 
    public int hashCode(){
        return Objects.hash(this.nombrePedido,this.tipo);
    }
}
