package Vista;

import Modelo.Enemigo;
import Modelo.Jugador;
import java.util.Scanner;

public class CombateVista {
    private Scanner input;

    public CombateVista() {
        this.input = new Scanner(System.in);
    }

    public CombateVista(Scanner input) {
        this.input = input;
    }

    public void mostrarEstadoCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n=================== ESTADO DEL COMBATE ===================");
        String armaInfo = (jugador.getArmaEquipada() != null)
                ? jugador.getArmaEquipada().getNombre() + " (+" + jugador.getArmaEquipada().getValor() + " ATQ)"
                : "Ninguna (Puños / +5 ATQ)";
        System.out.println(" [JUGADOR] " + jugador.getNombre() + " | Nivel " + jugador.getNivel());
        System.out.println(" Salud: " + jugador.getSalud() + "/" + jugador.getSaludMaxima() + " "
                + generarBarraSalud(jugador.getSalud(), jugador.getSaludMaxima()));
        System.out.println(" Arma equipada: " + armaInfo);
        System.out.println("----------------------------------------------------------");
        System.out.println(" [ENEMIGO] " + enemigo.getNombre() + " [" + enemigo.getTipo() + "] | Nivel " + enemigo.getNivel());
        System.out.println(" Salud: " + enemigo.getSalud() + "/" + enemigo.getSaludMaxima() + " "
                + generarBarraSalud(enemigo.getSalud(), enemigo.getSaludMaxima()));
        System.out.println("==========================================================");
    }

    private String generarBarraSalud(int actual, int maxima) {
        int totalBloques = 10;
        int bloquesLlenos = (int) Math.round(((double) actual / Math.max(1, maxima)) * totalBloques);
        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < totalBloques; i++) {
            if (i < bloquesLlenos) {
                barra.append("#");
            } else {
                barra.append("-");
            }
        }
        barra.append("]");
        return barra.toString();
    }

    public void mostrarMenuCombate() {
        System.out.println("\n--- Acciones de Combate ---");
        System.out.println("1. Atacar");
        System.out.println("2. Usar Pocion");
        System.out.println("3. Equipar Arma");
        System.out.println("4. Huir");
        System.out.println("---------------------------");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return this.input.nextLine().trim();
    }

    public int leerEntero(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Debe ingresar un numero entero valido.");
            }
        }
    }
}
