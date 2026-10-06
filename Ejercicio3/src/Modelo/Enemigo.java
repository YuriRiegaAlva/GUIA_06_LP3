package Modelo;

public class Enemigo {
    private String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    private String tipo;
    private int ultimoDanoCausado;

    public Enemigo(String nombre, int salud, int nivel, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = salud;
        this.nivel = nivel;
        this.tipo = tipo;
        this.ultimoDanoCausado = 0;
    }

    public int calcularAtaque() {
        int base = this.nivel * 5;
        int variacion = (int) (Math.random() * 6); // 0 a 5
        return Math.max(1, base + variacion);
    }

    public void atacar(Jugador jugador) {
        int dano = calcularAtaque();
        this.ultimoDanoCausado = dano;
        if (jugador != null) {
            jugador.recibirDano(dano);
        }
    }

    public void atacar(Jugador jugador, double multiplicador) {
        int dano = (int) Math.round(calcularAtaque() * multiplicador);
        this.ultimoDanoCausado = dano;
        if (jugador != null) {
            jugador.recibirDano(dano);
        }
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

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getUltimoDanoCausado() {
        return this.ultimoDanoCausado;
    }
}
