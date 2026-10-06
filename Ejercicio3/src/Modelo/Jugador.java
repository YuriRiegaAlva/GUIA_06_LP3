package Modelo;

public class Jugador {
    private String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    private InventarioModelo inventario;
    private Item armaEquipada;
    private int ultimoDanoCausado;
    private int ultimaCuracion;

    public Jugador(String nombre, int salud, int nivel, InventarioModelo inventario) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = salud;
        this.nivel = nivel;
        this.inventario = inventario;
        this.armaEquipada = null;
        this.ultimoDanoCausado = 0;
        this.ultimaCuracion = 0;
    }

    public int calcularAtaque() {
        int baseNivel = this.nivel * 5;
        int danoArma = (this.armaEquipada != null) ? this.armaEquipada.getValor() : 5;
        int variacion = (int) (Math.random() * 4);
        return baseNivel + danoArma + variacion;
    }

    public void atacar(Enemigo enemigo) {
        int dano = calcularAtaque();
        this.ultimoDanoCausado = dano;
        if (enemigo != null) {
            enemigo.recibirDano(dano);
        }
    }

    public boolean usarObjeto(String nombreItem) {
        if (this.inventario == null || nombreItem == null) {
            return false;
        }
        Item item = this.inventario.buscarItem(nombreItem);
        if (item == null) {
            return false;
        }
        String tipo = item.getTipo();
        if (tipo == null) {
            return false;
        }
        String tipoNormalizado = tipo.trim().toLowerCase();
        if (!tipoNormalizado.contains("pocion") && !tipoNormalizado.contains("poción")) {
            return false;
        }
        if (item.getCantidad() <= 0) {
            return false;
        }

        int curacion = item.getValor() > 0 ? item.getValor() : 25;
        int saludPrevia = this.salud;
        this.salud = Math.min(this.saludMaxima, this.salud + curacion);
        this.ultimaCuracion = this.salud - saludPrevia;

        boolean usado = item.usarItem();
        if (usado && item.getCantidad() <= 0) {
            this.inventario.eliminarItem(item);
        }
        return usado;
    }

    public boolean equiparArma(String nombreArma) {
        if (this.inventario == null || nombreArma == null) {
            return false;
        }
        Item item = this.inventario.buscarItem(nombreArma);
        if (item == null) {
            return false;
        }
        String tipo = item.getTipo();
        if (tipo != null && tipo.trim().equalsIgnoreCase("Arma")) {
            this.armaEquipada = item;
            return true;
        }
        return false;
    }

    public void recibirDano(int dano) {
        if (dano > 0) {
            this.salud = Math.max(0, this.salud - dano);
        }
    }

    public boolean estaVivo() {
        return this.salud > 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return this.salud;
    }

    public void setSalud(int salud) {
        this.salud = Math.max(0, Math.min(this.saludMaxima, salud));
    }

    public int getSaludMaxima() {
        return this.saludMaxima;
    }

    public void setSaludMaxima(int saludMaxima) {
        this.saludMaxima = saludMaxima;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public InventarioModelo getInventario() {
        return this.inventario;
    }

    public void setInventario(InventarioModelo inventario) {
        this.inventario = inventario;
    }

    public Item getArmaEquipada() {
        return this.armaEquipada;
    }

    public void setArmaEquipada(Item armaEquipada) {
        this.armaEquipada = armaEquipada;
    }

    public int getUltimoDanoCausado() {
        return this.ultimoDanoCausado;
    }

    public int getUltimaCuracion() {
        return this.ultimaCuracion;
    }
}
