package ba.unsa.etf.rpr.zadaca2;

import java.util.ArrayList;
import java.util.List;

public class Semestar {
    private final List<Predmet> predmeti;
    private int trenutnoOpterecenje;
    private final int redniBrojSemestra;

    public Semestar(int broj) {
        this.predmeti = new ArrayList<>();
        redniBrojSemestra = broj;
        trenutnoOpterecenje = 0;
    }

    public int getRedniBrojSemestra() {
        return redniBrojSemestra;
    }

    public void dodajPredmetUSemestar(Predmet predmet) {
        if (predmet.getSemestar() != redniBrojSemestra)
            throw new IllegalArgumentException("Predmet ne pripada ovom semestru!");
        if (predmeti.contains(predmet))
            throw new IllegalArgumentException("Već ste dodali ovaj predmet");
        predmeti.add(predmet);
        trenutnoOpterecenje += predmet.getEcts();
    }

    public List<Predmet> getListaObaveznihPredmeta() {
        return predmeti.stream()
                .filter(predmet -> predmet instanceof ObavezniPredmet)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public List<Predmet> getListaIzbornihPredmeta() {
        return predmeti.stream()
                .filter(predmet -> predmet instanceof IzborniPredmet)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public List<Predmet> getListaPredmeta() {
        return predmeti;
    }

    public int getTrenutnoOpterecenje() {
        return trenutnoOpterecenje;
    }

}