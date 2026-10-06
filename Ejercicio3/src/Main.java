import Modelo.InventarioModelo;
import Vista.InventarioVista;
import Controller.InventarioController;

public class Main {
    public static void main(String[] args) {
        InventarioModelo modelo = new InventarioModelo();
        InventarioVista vista = new InventarioVista();
        InventarioController controller = new InventarioController(modelo, vista);
        controller.iniciar();
    }
}
