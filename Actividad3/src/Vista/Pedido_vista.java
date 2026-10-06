package Vista;
import java.util.List;
import java.util.Scanner;
import Pedidos.Pedido;

public class Pedido_vista {
    private Scanner input;

    public Pedido_vista(){
        this.input = new Scanner(System.in);
    }

    // ---  INGRESOS ---

    public String solicitarOpcion(){
        System.out.print("Ingrese la opcion: ");
        return this.input.nextLine();
    }

    public String solicitarNombrePedido(){
        System.out.print("Ingrese el nombre del pedido: ");
        return this.input.nextLine();
    }

    public String solicitarNuevoNombrePedido(){
        System.out.print("Ingrese el nuevo nombre del pedido: ");
        return this.input.nextLine();
    }

    public String solicitarTipoPedido(){
        System.out.println("Ingrese el tipo del plato");
        return this.input.nextLine();
    }

    // ---  MOSTRAR DATOS ---

    public void mostrarPedidos(List<Pedido> lista){
        if(lista.isEmpty()){
            System.out.println("No hay pedidos existentes");
        }else{
            System.out.println("PEDIDOS: ");
            for(Pedido p : lista){
                System.out.println("Pedido: " + p.getNombre() + " | Tipo: " + p.getTipo() + " | Estado: " + p.getEstado());
            }
        }
    }

    public void mostrarPedido(Pedido ped){
        if ( ped == null){
            System.out.println("Pedido no existente");
            return;
        }
        System.out.print("Pedido:  "+ped.getNombre()+"-"+ped.getTipo()+" ["+ped.getEstado()+"]");
    }

    public void mostrarTotal(int total, int entrada, int fondo, int postre){
        System.out.println("CANTIDAD DE PEDIDOS:");
        System.out.println("Total: "+total);
        System.out.println("Entradas: "+entrada);
        System.out.println("Fondo: "+fondo);
        System.out.println("Postre: "+postre);
    }

    public void printmessage(String message){
        System.out.println(message);
    }

    // --- MENÚS Y SUBMENÚS ---

    public void Menu(){
        System.out.println("OPCIONES DE PEDIDO:");
        System.out.println("1.Agregar");
        System.out.println("2.Mostrar");
        System.out.println("3.Buscar Pedido");
        System.out.println("4.Actualizar Pedido");
        System.out.println("5.Eliminar Pedido");
        System.out.println("6.Contar Pedidos Pendientes");
        System.out.println("7.Completar Pedido");
        System.out.println("8.Mostrar Pedidos por Estado");
        System.out.println("9.Mostrar Historial");
        System.out.println("10.Salir");
    }

    public void subMenu_tipo(){
        System.out.println("TIPOS DE PEDIDO:");
        System.out.println("1.Entrada");
        System.out.println("2.Fondo");
        System.out.println("3.Postre");
        System.out.println("4.Volver al menu principal");
    }

    public void subMenu_estados(){
        System.out.println("ESTADOS DE PEDIDO:");
        System.out.println("1.Pendiente");
        System.out.println("2.Completado");
        System.out.println("3.Volver al menu principal");
    }

    public void subMenu_busqueda(){
        System.out.println("BUSQUEDA POR:");
        System.out.println("1.Nombre");
        System.out.println("2.Tipo");
        System.out.println("3.Salir");
    }

    // --- RECURSOS / CIERRE ---

    public void cerrarScanner(){
        input.close();
    }
}
