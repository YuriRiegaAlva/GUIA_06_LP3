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

    // ---  MOSTRAR DATOS ---

    public void mostrarPedidos(List<Pedido> lista){
        if(lista.isEmpty()){
            System.out.println("No hay pedidos existentes");
        }else{
            System.out.println("PEDIDOS: ");
            for(Pedido p : lista){
                System.out.println("Pedido: " + p.getNombre());
            }
        }
    }

    public void printmessage(String message){
        System.out.println(message);
    }

    // --- MENÚS Y SUBMENÚS ---

    public void Menu(){
        System.out.println("OPCIONES DE PEDIDO:");
        System.out.println("1.Agregar");
        System.out.println("2.Mostrar");
        System.out.println("3.Salir");
    }

    // --- RECURSOS / CIERRE ---

    public void cerrarScanner(){
        input.close();
    }
}
