package ba.unsa.etf.rpr.zadaca2;

import java.util.HashMap;
import java.util.Map;

public class Ciklus {
    private int redniBrojCiklusa;
    private final Map<Integer, Semestar> semestri;

    public Ciklus(int redniBrojCiklusa) {
        this.redniBrojCiklusa = redniBrojCiklusa;
        semestri = new HashMap<>();
    }

    public int getRedniBrojCiklusa() {
        return redniBrojCiklusa;
    }

    public void setRedniBrojCiklusa(int redniBrojCiklusa) {
        if (redniBrojCiklusa <= 0)
            throw new IllegalArgumentException("Neispravan redni broj ciklusa!");
        this.redniBrojCiklusa = redniBrojCiklusa;
    }

    public Map<Integer, Semestar> getSemestri() {
        return semestri;
    }

    public void dodajSemestar(Semestar semestar) {
        //Provjere validnosti semestra
        if (semestar.getTrenutnoOpterecenje() < 30)
            throw new IllegalArgumentException("Semestar nema dovoljan broj predmeta!");
        if (semestri.containsValue(semestar))
            throw new IllegalArgumentException("Već ste dodali ovaj semestar!");
        if (semestri.containsKey(semestar.getRedniBrojSemestra()))
            throw new IllegalArgumentException("Već postoji semestar s ovim rednim brojem!");

        semestri.put(semestar.getRedniBrojSemestra(), semestar);
    }

    public Semestar getSemestar(int redniBrojSemestra) {
        if (!semestri.containsKey(redniBrojSemestra))
            throw new IllegalArgumentException("Nepostojeci broj semestra!");
        return semestri.get(redniBrojSemestra);
    }
}
