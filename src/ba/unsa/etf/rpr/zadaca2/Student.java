package ba.unsa.etf.rpr.zadaca2;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class Student extends Osoba {
    private final String brojIndexa;
    private int zbirEECTSBodova;
    private int semestar;
    private int ciklus;
    private final Map<Predmet, Integer> ocjene;

    public Student(String ime, String prezime, String brojIndexa, int ciklus, int semestar) {
        this.setIme(ime);
        this.setPrezime(prezime);
        this.ciklus = ciklus;
        this.semestar = semestar;
        this.brojIndexa = brojIndexa;
        ocjene = new HashMap<>();
        zbirEECTSBodova = 0;
    }

    public String getBrojIndexa() {
        return brojIndexa;
    }

    public int getZbirEECTSBodova() {
        return zbirEECTSBodova;
    }

    public int getSemestar() {
        return semestar;
    }

    public int getCiklus() {
        return ciklus;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public void setCiklus(int ciklus) {
        this.ciklus = ciklus;
    }

    public Map<Predmet, Integer> getOcjene() {
        return ocjene;
    }

    public void upisiIzborniPredmet(Predmet predmet) {
            zbirEECTSBodova += predmet.getEcts();
            ocjene.put(predmet, 5);
    }

    public void upisiObaveznePredmete(List<Predmet> obavezniPredmeti) {
        obavezniPredmeti.forEach(predmet -> {
            zbirEECTSBodova += predmet.getEcts();
            ocjene.put(predmet, 5);
        });
    }

    public void ispisiOcjene()
    {
        for(Predmet predmet : ocjene.keySet())
        {
            System.out.println("Predmet: "+ predmet.getNazivPredmeta() + " - Ocjena: " + ocjene.get(predmet));
        }
    }

    public void upisiOcjenu(int ocjena, Predmet predmet) {
        ocjene.replace(predmet, ocjena);
    }
}
