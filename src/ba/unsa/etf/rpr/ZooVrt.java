package ba.unsa.etf.rpr;

import java.util.HashMap;
import java.util.Map;

public class ZooVrt {
    Map<String, Zivotinja> zivotinje;

    public ZooVrt() {
        zivotinje = new HashMap<>();
    }

    public int broj()
    {
        return zivotinje.size();
    }



//    public String dajTabelu()
//    {
//        String zivotinice = "";
//        for (String id : zivotinje.keySet())
//        {
//
//        }
//    }

//    public void dodaj(Class TipKlase, String ime, String s) {
//        TipKlase nova = new TipKlase(ime, id);
//    }
}
