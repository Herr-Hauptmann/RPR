package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Supermarket {
    private ArrayList<Artikl> inventar = new ArrayList<Artikl>();

    public void dodajArtikl(Artikl artikl){
        inventar.add(artikl);
    }

    public Artikl[] getArtikli() {
        Artikl[] povratni = new Artikl[inventar.size()];
        povratni = inventar.toArray(povratni);
        return povratni;
    }

    public Artikl izbaciArtiklSaKodom(String kod) {
        Artikl izbaci = null;
        for(Artikl a : inventar)
        {
            if (kod.equals(a.getKod()))
                izbaci = a;
        }
        if (izbaci != null)
            inventar.remove(izbaci);
        return izbaci;
    }
}
