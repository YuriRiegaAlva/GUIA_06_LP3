// Comments start with // and are ignored by the compiler.
// They exist for you and for anyone else reading the code.

// This project has no package: classes live directly inside src/.

import Controller.Controlador;
import Pedidos.Pedido_modelo;
import Vista.Pedido_vista;

/**
 * Main.java
 *
 * This is the entry point of the program: it contains the `main` method,
 * which is the first thing Java runs.
 *
 *   public  : anyone can call it
 *   static  : it belongs to the class, no object needed
 *   void    : it returns nothing
 *   String[] args : command-line arguments
 */
public class Main {
    public static void main(String[] args) {
        Pedido_modelo modelo = new Pedido_modelo();
        Pedido_vista vista = new Pedido_vista();
        Controlador controlador = new Controlador(modelo, vista);

        controlador.iniciar();
        // Try it: add your own lines below, then run:
        //   make run          (or `javetas run`)
    }
}
