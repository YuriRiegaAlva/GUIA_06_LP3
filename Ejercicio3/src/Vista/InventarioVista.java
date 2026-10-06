package Vista;

import java.util.Scanner;
import Modelo.Item;

import java.util.List;

public class InventarioVista {
    private Scanner input;

    public InventarioVista(){
        this.input = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("--GESTION INVENTARIO--");
        System.out.println("1. Ver inventario");
        System.out.println("2. Agregar item");
        System.out.println("3. Eliminar item");
        System.out.println("4. Buscar item");
        System.out.println("5. Ver detalles de item");
        System.out.println("6. Usar item");
        System.out.println("7. Modo Combate (RPG)");
        System.out.println("8. Salir");
        System.out.println("----------------------");
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return this.input.nextLine().trim();
    }

    public void mostrarInventario(List<Item> inventario){
        System.out.println("--INVENTARIO--");
        if(inventario.isEmpty()){
            System.out.println("Inventario Vacio");
            return;
        }
        int contador = 0;
        for(Item i : inventario){
            ++contador;
            System.out.println(contador+"."+i.getNombre()+"|"+i.getCantidad()+"|"+i.getTipo());
        }
        System.out.println("--------------------");
    }
    public void mostrarMensaje(String message){
        System.out.println(message);
    }
    public void mostrarDetallesItem(Item item){
        if(item == null){
            System.out.println("Item inexistente");
            return;
        }
        System.out.println("--ITEM: "+item.getNombre()+"--");
        System.out.println("Cantidad en inventario: "+item.getCantidad());
        System.out.println("Tipo de item: "+item.getTipo());
        System.out.println("Descripcion: "+item.getDescription());
        System.out.println("Valor: "+item.getValor());
    }

    public void cerrarScanner(){
        this.input.close();
    }
}
