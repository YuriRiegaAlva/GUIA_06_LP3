package Vista;

import Modelo.Carrito;
import Modelo.Compra;
import Modelo.Producto;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sistema_vista {
    private Scanner scanner;

    public Sistema_vista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n--- MENU DE LA TIENDA ---");
        System.out.println("1. Agregar producto al catalogo");
        System.out.println("2. Listar productos");
        System.out.println("3. Agregar producto al carrito");
        System.out.println("4. Ver carrito");
        System.out.println("5. Realizar compra");
        System.out.println("6. Ver historial de compras");
        System.out.println("7. Salir");
        System.out.print("Elija una opcion: ");
        int op = scanner.nextInt();
        scanner.nextLine(); // Limpiar salto de linea
        return op;
    }

    public void mostrarCatalogo(List<Producto> productos) {
        System.out.println("\n--- CATALOGO DE PRODUCTOS ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el catalogo.");
            return;
        }
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.println((i + 1) + ". " + p.getNombre() + " (ID: " + p.getId() + ") - Precio: S/." + p.getPrecio() + " - Stock: " + p.getStock());
        }
    }

    public void mostrarItemsCarrito(Carrito carrito) {
        System.out.println("\n--- TU CARRITO DE COMPRAS ---");
        Map<Producto, Integer> items = carrito.getCarrito();
        if (items.isEmpty()) {
            System.out.println("El carrito esta vacio.");
            return;
        }

        int i = 1;
        for (Producto p : items.keySet()) {
            int cant = items.get(p);
            double sub = carrito.calcularSubtotalItem(p);
            System.out.println(i + ". " + p.getNombre() + " - Cantidad: " + cant + " - Subtotal: S/." + sub);
            i++;
        }
        System.out.println("Subtotal total: S/." + carrito.calculaSubtotal());
    }

    public int mostrarSubmenuItem(Producto p, int cantidadActual) {
        System.out.println("\n--- Opciones para: " + p.getNombre() + " (En carrito: " + cantidadActual + ") ---");
        System.out.println("1. Agregar mas unidades");
        System.out.println("2. Eliminar del carrito");
        System.out.println("3. Volver al menu principal");
        System.out.print("Elija una opcion: ");
        int op = scanner.nextInt();
        scanner.nextLine();
        return op;
    }

    public void mostrarResumenCompra(double subtotal, double montoDescuento, double envio, double totalFinal) {
        System.out.println("\n--- RESUMEN DE LA COMPRA ---");
        System.out.println("Subtotal: S/." + subtotal);
        System.out.println("Descuento: -S/." + montoDescuento);
        System.out.println("Costo de envio (5%): +S/." + envio);
        System.out.println("Total a pagar: S/." + totalFinal);
    }

    public void mostrarHistorial(List<Compra> historial) {
        System.out.println("\n--- HISTORIAL DE COMPRAS ---");
        if (historial.isEmpty()) {
            System.out.println("No hay compras registradas.");
            return;
        }
        for (int i = 0; i < historial.size(); i++) {
            Compra c = historial.get(i);
            Producto p = c.getProductoComprado();
            System.out.println((i + 1) + ". " + p.getNombre() + " - Cantidad: " + c.getCantidadComprada() + " - Total: S/." + c.calcularTotal());
        }
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int pedirEntero(String mensaje) {
        System.out.print(mensaje);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    public double pedirDouble(String mensaje) {
        System.out.print(mensaje);
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.out.println("Error: " + error);
    }
    public void cerrarScanner(){
        this.scanner.close();
    }
}
