package ba.unsa.etf.rpr;

import java.util.*;

public class Kviz {
    private String naziv;
    private SistemBodovanja sistemBodovanja;
    private List<Pitanje> pitanja = new ArrayList<>();

    public Kviz(String nazivKviza, SistemBodovanja sistem) {
        naziv = nazivKviza;
        sistemBodovanja = sistem;
    }


    public void dodajPitanje(Pitanje pitanje) {
        if (!pitanja.contains(pitanje))
        {
            pitanja.add(pitanje);
        }
        else throw new IllegalArgumentException("Ne možete dodati pitanje sa tekstom koji već postoji");
    }

    public String getNaziv() {
        return naziv;
    }

    public SistemBodovanja getSistemBodovanja() {
        return sistemBodovanja;
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setSistemBodovanja(SistemBodovanja sistem) {
        sistemBodovanja = sistem;
    }

    public void setNaziv(String nazivKviza) {
        naziv = nazivKviza;
    }

    @Override
    public String toString() {
        String ispis = "Kviz \"";
        ispis += naziv + "\" boduje se ";
        ispis += getSistemBodovanja().dajIspis(sistemBodovanja.ordinal()) + ". Pitanja:";
        int i = 1;
        for (Pitanje pitanje : pitanja)
        {
            ispis+= "\n" + i + ". " + pitanje.getTekst() + "(" + pitanje.getBrojPoena() + "b)";
            Map<String, Odgovor> odgovori = pitanje.getOdgovori();
            for (String id : odgovori.keySet())
            {
                Odgovor odgovor = odgovori.get(id);
                ispis+= "\n\t" + id + ": " + odgovor.toString();
                if(odgovor.isTacno())
                    ispis+="(T)";
            }
            if(i<pitanja.size())
                ispis+="\n";
            i++;
        }
        return ispis;
    }

    public RezultatKviza predajKviz(Map<Pitanje, ArrayList<String>> zaokruzeniOdgovori) {
        HashMap<Pitanje, Double> finalniBodovi = new HashMap<Pitanje, Double>();
        for (Pitanje pitanje : zaokruzeniOdgovori.keySet()) {
            Double bodovi = pitanje.izracunajPoene(zaokruzeniOdgovori.get(pitanje), sistemBodovanja);
            finalniBodovi.put(pitanje, bodovi);
        }
        for (Pitanje pitanje : pitanja) {
            if (!zaokruzeniOdgovori.containsKey(pitanje))
                finalniBodovi.put(pitanje, (double) 0);
        }

        RezultatKviza rezultat = new RezultatKviza(this);
        rezultat.setBodovi(finalniBodovi);
        rezultat.setTotal(rezultat.sumaPoena());
        return rezultat;
    }
}
