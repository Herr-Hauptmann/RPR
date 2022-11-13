package ba.unsa.etf.rpr.tutorijal8;

import java.io.Serializable;
import java.util.ArrayList;

public class UN implements Serializable {
    private ArrayList<Drzava> drzave = new ArrayList<>();

    public UN() {
    }

    public ArrayList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(ArrayList<Drzava> drzave) {
        this.drzave = drzave;
    }

    public void dodajDrzavu(Drzava drzava)
    {
        drzave.add(drzava);
    }
}
