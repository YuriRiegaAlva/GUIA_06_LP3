package Modelo;
import java.util.List;
import java.util.ArrayList;


public class Historial {
    private List<Compra> historial;

    public Historial(){
        this.historial = new ArrayList<>();
    }


    public void agregarAlHistorial(Compra p){
        this.historial.add(p);
    }
    public List<Compra> getHistorial(){
        return this.historial;
    }
}
