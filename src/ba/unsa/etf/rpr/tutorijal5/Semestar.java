package ba.unsa.etf.rpr.tutorijal5;

import java.util.ArrayList;
import java.util.List;

public class Semestar {
    private List<Predmet> predmeti;
    private int redniBrojSemestra;

    public Semestar() {
        this.predmeti = new ArrayList<Predmet>();
        redniBrojSemestra = 1;
    }
    public Semestar(int broj) {
        this.predmeti = new ArrayList<Predmet>();
        redniBrojSemestra = broj;
    }

    public int dajZbirECTS(){
        return 0;
    }

    public void dodajIzborniPredmet(){

    }

    public void dodajPredmetUSemestar(Predmet predmet)
    {
        predmeti.add(predmet);
    }

    public List<Predmet> dajListuPredmeta(){
        return predmeti;
    }
}
