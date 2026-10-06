import Controller.Controlador;
import Pedidos.Pedido_modelo;
import Vista.Pedido_vista;

public class Main {
    public static void main(String[] args) {
        Pedido_modelo modelo = new Pedido_modelo();
        Pedido_vista vista = new Pedido_vista();
        Controlador controlador = new Controlador(modelo, vista);

        controlador.iniciar();
    }
}
