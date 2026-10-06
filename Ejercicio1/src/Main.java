import Controlador.Controlador_sistema;
import Modelo.Carrito;
import Modelo.Catalogo;
import Modelo.Historial;
import Modelo.Producto;
import Vista.Sistema_vista;

public class Main {
    public static void main(String[] args) {
        // Inicializar modelos
        Catalogo catalogo = new Catalogo();
        Carrito carrito = new Carrito();
        Historial historial = new Historial();

        // Productos semilla para pruebas iniciales (nombre, id, precio, stock)
        catalogo.agregarProductos(new Producto("Laptop HP", "P01", 1200.0, 5));
        catalogo.agregarProductos(new Producto("Mouse Optico", "P02", 25.0, 20));
        catalogo.agregarProductos(new Producto("Teclado Mecanico", "P03", 85.0, 10));

        // Inicializar vista y controlador
        Sistema_vista vista = new Sistema_vista();
        Controlador_sistema controlador = new Controlador_sistema(catalogo, carrito, historial, vista);

        // Arrancar aplicación
        controlador.iniciar();
    }
}
