package Controlador;

import Modelo.Carrito;
import Modelo.Catalogo;
import Modelo.Compra;
import Modelo.Historial;
import Modelo.Producto;
import Vista.Sistema_vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controlador_sistema {
    private final Catalogo catalogo;
    private final Carrito carrito;
    private final Historial historial;
    private final Sistema_vista vista;

    public Controlador_sistema(Catalogo catalogo, Carrito carrito, Historial historial, Sistema_vista vista) {
        this.catalogo = catalogo;
        this.carrito = carrito;
        this.historial = historial;
        this.vista = vista;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            int opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    agregarProductoAlCatalogo();
                    break;
                case 2:
                    listarCatalogo();
                    break;
                case 3:
                    agregarProductoAlCarrito();
                    break;
                case 4:
                    gestionarCarrito();
                    break;
                case 5:
                    realizarCompra();
                    break;
                case 6:
                    verHistorial();
                    break;
                case 7:
                    vista.mostrarMensaje("Gracias por utilizar el sistema de compras. Hasta pronto!");
                    this.vista.cerrarScanner();
                    salir = true;
                    
                    break;
                default:
                    vista.mostrarError("Opcion no valida. Seleccione un numero entre 1 y 7.");
            }
        }
    }

    private void agregarProductoAlCatalogo() {
        vista.mostrarMensaje("--- Registrar nuevo producto en Catalogo ---");
        String id = vista.pedirTexto("Ingrese ID del producto: ");
        if (id.isEmpty()) {
            vista.mostrarError("El ID no puede estar vacio.");
            return;
        }

        if (catalogo.buscarProducto(id) != null) {
            vista.mostrarError("Ya existe un producto con el ID: " + id);
            return;
        }

        String nombre = vista.pedirTexto("Ingrese nombre del producto: ");
        if (nombre.isEmpty()) {
            vista.mostrarError("El nombre no puede estar vacio.");
            return;
        }

        double precio = vista.pedirDouble("Ingrese precio del producto: ");
        if (precio < 0) {
            vista.mostrarError("El precio no puede ser negativo.");
            return;
        }

        int stock = vista.pedirEntero("Ingrese stock disponible: ");
        if (stock < 0) {
            vista.mostrarError("El stock no puede ser negativo.");
            return;
        }

        Producto nuevo = new Producto(nombre, id, precio, stock);
        catalogo.agregarProductos(nuevo);
        vista.mostrarMensaje("Producto registrado con exito en el catalogo!");
    }

    private void listarCatalogo() {
        List<Producto> lista = catalogo.listarProductos();
        vista.mostrarCatalogo(lista);
    }

    private void agregarProductoAlCarrito() {
        List<Producto> disponibles = catalogo.listarProductos();
        if (disponibles.isEmpty()) {
            vista.mostrarError("El catalogo esta vacio. Registre productos primero.");
            return;
        }

        vista.mostrarCatalogo(disponibles);
        int seleccion = vista.pedirEntero("Seleccione el numero de producto que desea agregar: ");
        if (seleccion < 1 || seleccion > disponibles.size()) {
            vista.mostrarError("Seleccion fuera de rango.");
            return;
        }

        Producto seleccionado = disponibles.get(seleccion - 1);
        if (seleccionado.getStock() <= 0) {
            vista.mostrarError("El producto '" + seleccionado.getNombre() + "' no tiene stock disponible.");
            return;
        }

        int cantidadDeseada = vista.pedirEntero("Ingrese cantidad a agregar: ");
        if (cantidadDeseada <= 0) {
            vista.mostrarError("La cantidad debe ser mayor a 0.");
            return;
        }

        int cantidadEnCarrito = carrito.getCarrito().getOrDefault(seleccionado, 0);
        if (cantidadEnCarrito + cantidadDeseada > seleccionado.getStock()) {
            vista.mostrarError("Stock insuficiente. Stock disponible: " + seleccionado.getStock() + " (en carrito: " + cantidadEnCarrito + ")");
            return;
        }

        carrito.agregarProducto(seleccionado, cantidadDeseada);
        vista.mostrarMensaje("Se agregaron " + cantidadDeseada + " unidades al carrito.");
    }

    private void gestionarCarrito() {
        Map<Producto, Integer> items = carrito.getCarrito();
        if (items.isEmpty()) {
            vista.mostrarMensaje("El carrito esta vacio. No hay productos para gestionar.");
            return;
        }

        vista.mostrarItemsCarrito(carrito);
        List<Producto> listaCarrito = new ArrayList<>(items.keySet());

        int seleccion = vista.pedirEntero("Seleccione el numero de producto a gestionar (0 para cancelar): ");
        if (seleccion == 0) {
            return;
        }
        if (seleccion < 1 || seleccion > listaCarrito.size()) {
            vista.mostrarError("Seleccion fuera de rango.");
            return;
        }

        Producto productoElegido = listaCarrito.get(seleccion - 1);
        int cantidadActual = items.get(productoElegido);
        int opcionSubmenu = vista.mostrarSubmenuItem(productoElegido, cantidadActual);

        switch (opcionSubmenu) {
            case 1:
                int adicional = vista.pedirEntero("Cuantas unidades adicionales desea anadir?: ");
                if (adicional <= 0) {
                    vista.mostrarError("La cantidad debe ser mayor a 0.");
                    return;
                }
                if (cantidadActual + adicional > productoElegido.getStock()) {
                    vista.mostrarError("Supera el stock disponible (" + productoElegido.getStock() + ")");
                    return;
                }
                carrito.agregarProducto(productoElegido, adicional);
                vista.mostrarMensaje("Cantidad actualizada con exito.");
                break;
            case 2:
                carrito.eliminarProducto(productoElegido);
                vista.mostrarMensaje("Producto eliminado del carrito.");
                break;
            case 3:
                // Volver
                break;
            default:
                vista.mostrarError("Opcion no valida.");
        }
    }

    private void realizarCompra() {
        Map<Producto, Integer> items = carrito.getCarrito();
        if (items.isEmpty()) {
            vista.mostrarError("El carrito esta vacio. No puede realizar una compra.");
            return;
        }

        // Validar stock antes de proceder
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            Producto p = entry.getKey();
            int cant = entry.getValue();
            if (cant > p.getStock()) {
                vista.mostrarError("Stock insuficiente para " + p.getNombre() + " (Stock actual: " + p.getStock() + ")");
                return;
            }
        }

        double subtotal = carrito.calculaSubtotal();
        double porcentajeDescuento = vista.pedirDouble("Ingrese porcentaje de descuento (0 si no aplica): ");
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            vista.mostrarError("El porcentaje de descuento debe estar entre 0 y 100.");
            return;
        }

        double montoDescuento = carrito.aplicarDescuento(subtotal, porcentajeDescuento);
        double envio = carrito.calcularEnvio(subtotal);
        double totalFinal = carrito.calcularTotal(porcentajeDescuento);

        vista.mostrarResumenCompra(subtotal, montoDescuento, envio, totalFinal);
        String confirmacion = vista.pedirTexto("Desea confirmar la compra? (S/N): ");

        if (!confirmacion.equalsIgnoreCase("S") && !confirmacion.equalsIgnoreCase("SI")) {
            vista.mostrarMensaje("Compra cancelada por el usuario.");
            return;
        }

        // Ejecutar compra: descontar stock y guardar en historial
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            Producto p = entry.getKey();
            int cant = entry.getValue();
            p.setStock(p.getStock() - cant);
            Compra registro = new Compra(p, cant);
            historial.agregarAlHistorial(registro);
        }

        carrito.vaciarCarrito();
        vista.mostrarMensaje("Compra realizada con exito.");
    }

    private void verHistorial() {
        List<Compra> lista = historial.getHistorial();
        vista.mostrarHistorial(lista);
    }
}
