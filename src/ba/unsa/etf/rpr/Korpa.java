package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Korpa {
    private ArrayList<Artikl> kupljeno = new ArrayList<Artikl>();
    public boolean dodajArtikl(Artikl a) {
        if (kupljeno.size()>=50)
            return false;
        kupljeno.add(a);
        return true;
    }

    public Artikl[] getArtikli() {
        Artikl[] povratni = new Artikl[kupljeno.size()];
        povratni = kupljeno.toArray(povratni);
        return povratni;
    }

    public Artikl izbaciArtiklSaKodom(String kod) {
        Artikl izbaci = null;
        for(Artikl a : kupljeno)
        {
            if (kod.equals(a.getKod()))
                izbaci = a;
        }
        if (izbaci != null)
            kupljeno.remove(izbaci);
        return izbaci;
    }

    public int dajUkupnuCijenuArtikala() {
        int cijena = 0;
        for (Artikl item : kupljeno)
            cijena+=item.getCijena();
        return cijena;
    }
}
