package Controller;

import java.util.List;
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
            String tipo = "";
            this.vista.Menu();
            opcion = this.vista.solicitarOpcion();
            switch (opcion) {
               	case "1":
                    //Agregar pedido
                    String opcion_tipo;
                    nombre = this.vista.solicitarNombrePedido();
                    this.vista.subMenu_tipo();
                    opcion_tipo = this.vista.solicitarOpcion();
                    
                    tipo = opcion_switch_tipo(opcion_tipo);
                    if(tipo == null || tipo.isEmpty()){
                        break;
                    }
              		this.agregarPedido(nombre,tipo);
                    break;

                case "2":
                    //Mostrar Pedidos
                    this.mostrarPedidos();
                    break;

                case "3":
                    // Buscar
                    this.vista.subMenu_busqueda();
                    String retorno;
                    String opcion_busqueda;
                    opcion_busqueda = this.vista.solicitarOpcion();
                    
                    retorno = this.opcion_switch_busqueda(opcion_busqueda);
                    if(retorno == null || retorno.isEmpty()){
                        break;
                    }
                    if (retorno.equals("Entrada")  || retorno.equals("Fondo")   || retorno.equals("Postre") ){
                        List<Pedido> pedidosPorTipo = this.modelo.buscarPedidoTipo(retorno);
                        this.vista.mostrarPedidos(pedidosPorTipo);
                    }else{
                        this.vista.mostrarPedido(this.modelo.buscarPedidoNombre(retorno));
                        System.out.println();
                    }
                    break;

                case "4":
                    // Actualizar
                    String nombreActual = this.vista.solicitarNombrePedido();
                    String nuevoNombre = this.vista.solicitarNuevoNombrePedido();
                    this.actualizarPedido(nombreActual, nuevoNombre);
                    break;
    
                case "5":
                    // Eliminar
                    String nombreEliminar = this.vista.solicitarNombrePedido();
                    this.eliminarPedido(nombreEliminar);
                    break;
    
                case "6":
                    // Contar Pedidos
                    this.mostrarTotal();
                    break;

                case "7":
                    this.vista.printmessage("Saliendo");
                    break;

               	default:
                    this.vista.printmessage("Opcion no valida");
              		break;

            }
        }while(!opcion.equals("7"));
        this.vista.cerrarScanner();
    }

    // =========================================================================
    // OPERACIONES SOBRE PEDIDOS
    // =========================================================================

    public void agregarPedido(String nombrePedido, String tipo){
        if(!nombrePedido.isEmpty()||!tipo.isEmpty()){
            this.modelo.agregarPedido(new Pedido(nombrePedido, tipo));
            this.vista.printmessage("Pedido agregado: "+ nombrePedido);
        }else{
            this.vista.printmessage("El "+(nombrePedido.isBlank()?"Nombre del pedido vacio":nombrePedido.isBlank()?"Tipo vacio":"")+ " no puede estar vacio");
        }
    }

    public void actualizarPedido(String nombrePedido, String nuevoNombre){
        if(nombrePedido.isBlank()){
            this.vista.printmessage("El nombre del pedido no puede estar vacio");
            return;
        }
        
        Pedido pedido = this.modelo.buscarPedidoNombre(nombrePedido);
        if(pedido == null){
            this.vista.printmessage("Pedido inexistente");
            return;
        }
        if(nuevoNombre.isBlank()){
            this.vista.printmessage("El nuevo nombre del pedido esta vacio");
            return;
        }
        this.modelo.actualizarPedido(pedido, nuevoNombre);
        this.vista.printmessage("Pedido actualizado: " + nuevoNombre);
    }

    public void eliminarPedido(String nombre){
        if(nombre.isBlank()){
            this.vista.printmessage("Nombre del pedido a eliminar vacio");
            return;
        }
        Pedido pedido = this.modelo.buscarPedidoNombre(nombre);
        if(pedido == null){
            this.vista.printmessage("Pedido inexistente");
            return;
        }
        this.modelo.eliminarPedido(pedido);
        this.vista.mostrarPedido(pedido);
        this.vista.printmessage(" Eliminado");
    }

    // =========================================================================
    // VISUALIZACIÓN
    // =========================================================================

    public void mostrarPedidos(){
        this.vista.mostrarPedidos(this.modelo.getPedidos());
    }

    public void mostrarTotal(){
        int total = this.modelo.contarTotal();
        int entrada = this.modelo.contarTipo("Entrada");
        int fondo = this.modelo.contarTipo("Fondo");
        int postre = this.modelo.contarTipo("Postre");
        this.vista.mostrarTotal(total, entrada, fondo, postre);
    }

    // =========================================================================
    // CONVERSIÓN DE SUBMENÚS
    // =========================================================================

    public String opcion_switch_tipo (String opcion_tipo){
        String tipo = null;
        switch(opcion_tipo){
            case "1":
                tipo = "Entrada";
                break;
            case "2":
                tipo = "Fondo";
                break;
            case "3":
                tipo = "Postre";
                break;
            case "4":
                tipo = "";
                this.vista.printmessage("Volviendo menu principal");
                break;
            default:
                this.vista.printmessage("Opcion No valida");
                break;
        }
        return tipo;
    }

    public String opcion_switch_busqueda ( String opcion_busqueda){
        String busqueda = null;
        switch(opcion_busqueda){
            case "1":
                this.vista.printmessage("Ingrese el nombre del pedido a buscar");
                String nombreIngresado = this.vista.solicitarNombrePedido();
                if(nombreIngresado.isBlank()){
                    this.vista.printmessage("Nombre de pedido no valido");
                } else {
                    busqueda = nombreIngresado;
                }
                break;
            case "2":
                this.vista.subMenu_tipo();
                busqueda = this.opcion_switch_tipo(this.vista.solicitarOpcion());
                break;
            case "3":
                busqueda = "";
                this.vista.printmessage("Volviendo al menu principal");
                break;
            default:
                this.vista.printmessage("Opcion no valida");
                break;
        }
        return busqueda;
    }
}
