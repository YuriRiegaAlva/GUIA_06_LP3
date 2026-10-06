package Controller;

import Pedidos.Pedido;
import Pedidos.Pedido_modelo;
import Vista.Pedido_vista;

public class Controlador {

    private Pedido_modelo modelo;
    private Pedido_vista vista;

    public Controlador(Pedido_modelo modelo, Pedido_vista vista){
        this.vista = vista;
        this.modelo = modelo;
    }

    // =========================================================================
    // BUCLE PRINCIPAL
    // =========================================================================

    public void iniciar(){
        String opcion;
        do{
            String nombre = "";
            this.vista.Menu();
            opcion = this.vista.solicitarOpcion();
            switch (opcion) {
               	case "1":
                    //Agregar pedido
                    nombre = this.vista.solicitarNombrePedido();
              		this.agregarPedido(nombre);
                    break;

                case "2":
                    //Mostrar Pedidos
                    this.mostrarPedidos();
                    break;

                case "3":
                    this.vista.printmessage("Saliendo");
                    break;

               	default:
                    this.vista.printmessage("Opcion no valida");
              		break;

            }
        }while(!opcion.equals("3"));
        this.vista.cerrarScanner();
    }

    // =========================================================================
    // OPERACIONES SOBRE PEDIDOS
    // =========================================================================

    public void agregarPedido(String nombrePedido){
        if(!nombrePedido.isEmpty()){
            this.modelo.agregarPedido(new Pedido(nombrePedido));
            this.vista.printmessage("Pedido agregado: "+ nombrePedido);
        }else{
            this.vista.printmessage("El Nombre del pedido no puede estar vacio");
        }
    }

    // =========================================================================
    // VISUALIZACIÓN
    // =========================================================================

    public void mostrarPedidos(){
        this.vista.mostrarPedidos(this.modelo.getPedidos());
    }
}
