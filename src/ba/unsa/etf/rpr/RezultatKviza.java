package ba.unsa.etf.rpr;

import java.util.HashMap;
import java.util.Map;

public class RezultatKviza {
    private Kviz kviz;
    private double total = 0;
    private Map<Pitanje, Double> bodovi = new HashMap<Pitanje, Double>();

    public RezultatKviza(Kviz kviz) {
        this.kviz = kviz;
        total = 0;
    }

    public void setBodovi(Map<Pitanje, Double> bodovi) {
        this.bodovi = bodovi;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Kviz getKviz() {
        return kviz;
    }

    public double getTotal() {
        return total;
    }

    public Map<Pitanje, Double> getBodovi() {
        return bodovi;
    }

    public double sumaPoena(){
        double sumaBodova = 0;
        for(Pitanje pitanje : bodovi.keySet())
        {
            sumaBodova+= bodovi.get(pitanje);
        }
        return sumaBodova;
    }

    @Override
    public String toString() {
        String rezultatiKviza = "Na kvizu \"" + kviz.getNaziv() + "\" ostvarili ste ukupno ";
        rezultatiKviza += sumaPoena() + " poena. Raspored po pitanjima:";
        for (Pitanje pitanje : bodovi.keySet())
        {
            rezultatiKviza += "\n" + pitanje.getTekst() + " - " + bodovi.get(pitanje) + "b";
        }
        return rezultatiKviza;
    }
}
