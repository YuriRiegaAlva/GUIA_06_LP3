package Controller;

import Modelo.Enemigo;
import Modelo.Item;
import Modelo.Jugador;
import Vista.CombateVista;
import java.util.ArrayList;
import java.util.List;

public class CombateController {
    private Jugador jugador;
    private Enemigo enemigo;
    private CombateVista vista;

    public CombateController(Jugador jugador, Enemigo enemigo, CombateVista vista) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.vista = vista;
    }

    public void iniciarCombate() {
        this.vista.mostrarMensaje("\n==================================================");
        this.vista.mostrarMensaje("           ¡COMIENZA EL COMBATE!");
        this.vista.mostrarMensaje(" Un peligroso " + this.enemigo.getNombre() + " se interpone en tu camino.");
        this.vista.mostrarMensaje("==================================================");

        boolean enCombate = true;
        while (enCombate && this.jugador.estaVivo() && this.enemigo.estaVivo()) {
            this.vista.mostrarEstadoCombate(this.jugador, this.enemigo);
            enCombate = turnoJugador();

            if (!enCombate) {
                this.vista.mostrarMensaje(">> Has escapado a salvo del combate.");
                break;
            }

            if (!this.enemigo.estaVivo()) {
                break;
            }

            turnoEnemigo();
        }

        if (enCombate) {
            resolverFinCombate();
        }
    }

    private boolean turnoJugador() {
        while (true) {
            this.vista.mostrarMenuCombate();
            int opcion = this.vista.leerEntero("Seleccione una accion (1-4): ");
            switch (opcion) {
                case 1:
                    this.jugador.atacar(this.enemigo);
                    this.vista.mostrarMensaje(">> ¡" + this.jugador.getNombre() + " ataca valientemente a "
                            + this.enemigo.getNombre() + " causando " + this.jugador.getUltimoDanoCausado() + " puntos de daño!");
                    return true;

                case 2:
                    List<Item> pociones = obtenerItemsPorTipo("Pocion");
                    if (pociones.isEmpty()) {
                        this.vista.mostrarMensaje(">> No tienes pociones en tu inventario.");
                        break;
                    }
                    this.vista.mostrarMensaje("-- Pociones en Inventario --");
                    for (Item p : pociones) {
                        this.vista.mostrarMensaje("- " + p.getNombre() + " | Cant: " + p.getCantidad()
                                + " | Cura: +" + p.getValor() + " HP");
                    }
                    String nombrePocion = this.vista.leerTexto("Nombre de la pocion a usar (o 'cancelar'): ");
                    if (nombrePocion.equalsIgnoreCase("cancelar")) {
                        break;
                    }
                    boolean curado = this.jugador.usarObjeto(nombrePocion);
                    if (curado) {
                        this.vista.mostrarMensaje(">> ¡Bebiste " + nombrePocion + "! Recuperaste "
                                + this.jugador.getUltimaCuracion() + " HP. (Salud actual: "
                                + this.jugador.getSalud() + "/" + this.jugador.getSaludMaxima() + ")");
                        return true;
                    } else {
                        this.vista.mostrarMensaje(">> No se pudo usar la pocion. Verifica el nombre ingresado.");
                        break;
                    }

                case 3:
                    List<Item> armas = obtenerItemsPorTipo("Arma");
                    if (armas.isEmpty()) {
                        this.vista.mostrarMensaje(">> No tienes armas en tu inventario.");
                        break;
                    }
                    this.vista.mostrarMensaje("-- Armas en Inventario --");
                    for (Item a : armas) {
                        this.vista.mostrarMensaje("- " + a.getNombre() + " | Ataque: +" + a.getValor());
                    }
                    String nombreArma = this.vista.leerTexto("Nombre del arma a equipar (o 'cancelar'): ");
                    if (nombreArma.equalsIgnoreCase("cancelar")) {
                        break;
                    }
                    boolean equipado = this.jugador.equiparArma(nombreArma);
                    if (equipado) {
                        this.vista.mostrarMensaje(">> ¡Has equipado con exito "
                                + this.jugador.getArmaEquipada().getNombre() + "! (+"
                                + this.jugador.getArmaEquipada().getValor() + " ATQ)");
                        return true;
                    } else {
                        this.vista.mostrarMensaje(">> No se pudo equipar el arma. Verifica el nombre ingresado.");
                        break;
                    }

                case 4:
                    this.vista.mostrarMensaje(">> ¡Decidiste huir a toda prisa del combate!");
                    return false;

                default:
                    this.vista.mostrarMensaje(">> Opcion invalida. Debe elegir entre 1 y 4.");
                    break;
            }
        }
    }

    private void turnoEnemigo() {
        this.vista.mostrarMensaje("\n--- Turno de " + this.enemigo.getNombre() + " ---");
        int prob = (int) (Math.random() * 100);

        if (prob < 20) {
            // 20% de probabilidad: Fallo
            this.vista.mostrarMensaje(">> ¡" + this.enemigo.getNombre() + " lanza un golpe descuidado pero falla completamente!");
        } else if (prob < 75) {
            // 55% de probabilidad: Ataque normal
            this.enemigo.atacar(this.jugador);
            this.vista.mostrarMensaje(">> ¡" + this.enemigo.getNombre() + " te asesta un golpe certero e inflige "
                    + this.enemigo.getUltimoDanoCausado() + " puntos de daño!");
        } else {
            // 25% de probabilidad: Ataque especial / Golpe crítico
            this.enemigo.atacar(this.jugador, 1.6);
            this.vista.mostrarMensaje(">> ¡¡GOLPE CRITICO!! ¡" + this.enemigo.getNombre() + " desata toda su furia causando "
                    + this.enemigo.getUltimoDanoCausado() + " puntos de daño!");
        }
    }

    private void resolverFinCombate() {
        this.vista.mostrarMensaje("\n==================================================");
        if (!this.enemigo.estaVivo()) {
            this.vista.mostrarMensaje("               ¡¡VICTORIA HEROICA!!");
            this.vista.mostrarMensaje(" Has derrotado a " + this.enemigo.getNombre() + ".");
            this.vista.mostrarMensaje("==================================================");

            Item recompensa = generarRecompensa();
            this.jugador.getInventario().agregarItem(recompensa);
            this.vista.mostrarMensaje(">> Recompensa añadida al inventario: " + recompensa.getNombre()
                    + " (" + recompensa.getTipo() + ", Cant: " + recompensa.getCantidad()
                    + ", Poder/Curacion: " + recompensa.getValor() + ")");
        } else if (!this.jugador.estaVivo()) {
            this.vista.mostrarMensaje("             HAS SIDO DERROTADO...");
            this.vista.mostrarMensaje(" " + this.enemigo.getNombre() + " te ha vencido en el campo de batalla.");
            this.vista.mostrarMensaje("==================================================");
            this.jugador.setSalud(this.jugador.getSaludMaxima() / 2);
            this.vista.mostrarMensaje(">> Has sido rescatado y descansas para recuperar "
                    + this.jugador.getSalud() + " puntos de salud.");
        }
    }

    private Item generarRecompensa() {
        int r = (int) (Math.random() * 3);
        if (r == 0) {
            return new Item("Pocion de Vida Mayor", 1, "Pocion", "Restaura 40 HP en combate", 40);
        } else if (r == 1) {
            return new Item("Espada de Acero Templado", 1, "Arma", "Espada afilada y resistente", 24);
        } else {
            return new Item("Elixir Vital", 2, "Pocion", "Pociones magicas de recuperacion", 30);
        }
    }

    private List<Item> obtenerItemsPorTipo(String tipoBuscado) {
        List<Item> filtrados = new ArrayList<>();
        if (this.jugador == null || this.jugador.getInventario() == null) {
            return filtrados;
        }
        for (Item item : this.jugador.getInventario().obtenerItems()) {
            if (item.getTipo() != null) {
                String tipoNorm = item.getTipo().trim().toLowerCase();
                String buscadoNorm = tipoBuscado.trim().toLowerCase();
                if (tipoNorm.contains(buscadoNorm) ||
                    (buscadoNorm.equals("pocion") && tipoNorm.contains("poción"))) {
                    filtrados.add(item);
                }
            }
        }
        return filtrados;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Enemigo getEnemigo() {
        return this.enemigo;
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    public CombateVista getVista() {
        return this.vista;
    }

    public void setVista(CombateVista vista) {
        this.vista = vista;
    }
}
