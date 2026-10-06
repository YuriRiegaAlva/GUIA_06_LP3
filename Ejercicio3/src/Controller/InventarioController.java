package Controller;

import Modelo.InventarioModelo;
import Modelo.Item;
import Modelo.Jugador;
import Modelo.Enemigo;
import Vista.InventarioVista;
import Vista.CombateVista;

public class InventarioController {
    private InventarioVista vista;
    private InventarioModelo modelo_InventarioModelo;

    public InventarioController(InventarioModelo modelo, InventarioVista vista) {
        this.modelo_InventarioModelo = modelo;
        this.vista = vista;
    }

    private int leerEntero(String mensaje) {
        while (true) {
            String texto = this.vista.leerTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                this.vista.mostrarMensaje("Error: Debe ingresar un numero entero valido.");
            }
        }
    }

    public void agregarItem() {
        String nombre = this.vista.leerTexto("Ingrese el nombre del item: ");
        int cantidad = leerEntero("Ingrese la cantidad: ");
        String tipo = this.vista.leerTexto("Ingrese el tipo (Arma, Pocion, etc.): ");
        String descripcion = this.vista.leerTexto("Ingrese la descripcion: ");
        Item item = new Item(nombre, cantidad, tipo, descripcion);
        this.modelo_InventarioModelo.agregarItem(item);
        this.vista.mostrarMensaje("Item agregado con exito.");
    }

    public void eliminarItem() {
        String nombre = this.vista.leerTexto("Ingrese el nombre del item a eliminar: ");
        Item item = this.modelo_InventarioModelo.buscarItem(nombre);
        if (item != null) {
            this.modelo_InventarioModelo.eliminarItem(item);
            this.vista.mostrarMensaje("Item eliminado con exito.");
        } else {
            this.vista.mostrarMensaje("Item no encontrado.");
        }
    }

    public void verInventario() {
        this.vista.mostrarInventario(this.modelo_InventarioModelo.obtenerItems());
    }

    public void mostrarDetalles() {
        String nombre = this.vista.leerTexto("Ingrese el nombre del item: ");
        Item item = this.modelo_InventarioModelo.buscarItem(nombre);
        this.vista.mostrarDetallesItem(item);
    }

    public void buscarItem() {
        String nombre = this.vista.leerTexto("Ingrese el nombre del item a buscar: ");
        Item item = this.modelo_InventarioModelo.buscarItem(nombre);
        if (item != null) {
            this.vista.mostrarMensaje("Item encontrado: " + item.getNombre() + " (Cantidad: " + item.getCantidad() + ", Tipo: " + item.getTipo() + ")");
        } else {
            this.vista.mostrarMensaje("Item no encontrado.");
        }
    }

    public void usarItem() {
        String nombre = this.vista.leerTexto("Ingrese el nombre del item a usar: ");
        Item item = this.modelo_InventarioModelo.buscarItem(nombre);
        if (item != null) {
            if (item.usarItem()) {
                this.vista.mostrarMensaje("Item usado. Cantidad restante: " + item.getCantidad());
                if (item.getCantidad() == 0) {
                    this.modelo_InventarioModelo.eliminarItem(item);
                    this.vista.mostrarMensaje("El item se ha agotado y fue eliminado del inventario.");
                }
            } else {
                this.vista.mostrarMensaje("No se pudo usar el item (sin unidades disponibles).");
            }
        } else {
            this.vista.mostrarMensaje("Item no encontrado.");
        }
    }

    private void iniciarModoCombate() {
        if (this.modelo_InventarioModelo.obtenerItems().isEmpty()) {
            this.vista.mostrarMensaje("Tu inventario esta vacio. Se te otorga equipamiento basico para la batalla:");
            this.modelo_InventarioModelo.agregarItem(new Item("Espada de Hierro", 1, "Arma", "Espada basica de combate", 15));
            this.modelo_InventarioModelo.agregarItem(new Item("Pocion de Vida", 2, "Pocion", "Restaura 25 HP", 25));
            this.vista.mostrarMensaje("+ Espada de Hierro añadida.");
            this.vista.mostrarMensaje("+ 2x Pocion de Vida añadidas.");
        }

        String nombreJugador = this.vista.leerTexto("Ingrese el nombre de su heroe (Enter para 'Guerrero'): ");
        if (nombreJugador.isEmpty()) {
            nombreJugador = "Guerrero";
        }
        Jugador jugador = new Jugador(nombreJugador, 100, 1, this.modelo_InventarioModelo);

        Enemigo[] enemigos = {
            new Enemigo("Goblin del Bosque", 50, 1, "Bestia"),
            new Enemigo("Orco Saqueador", 70, 2, "Guerrero"),
            new Enemigo("Esqueleto Maldito", 60, 1, "No-muerto"),
            new Enemigo("Dragon Menor", 90, 3, "Dragon")
        };
        Enemigo enemigo = enemigos[(int) (Math.random() * enemigos.length)];

        CombateVista combateVista = new CombateVista();
        CombateController combateController = new CombateController(jugador, enemigo, combateVista);
        combateController.iniciarCombate();
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            this.vista.mostrarMenu();
            int opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1:
                    verInventario();
                    break;
                case 2:
                    agregarItem();
                    break;
                case 3:
                    eliminarItem();
                    break;
                case 4:
                    buscarItem();
                    break;
                case 5:
                    mostrarDetalles();
                    break;
                case 6:
                    usarItem();
                    break;
                case 7:
                    iniciarModoCombate();
                    break;
                case 8:
                    this.vista.mostrarMensaje("Saliendo del sistema...");
                    this.vista.cerrarScanner();
                    salir = true;
                    break;
                default:
                    this.vista.mostrarMensaje("Opcion no valida. Intente nuevamente.");
                    break;
            }
        }
    }
}
