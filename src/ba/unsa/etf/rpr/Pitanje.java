package ba.unsa.etf.rpr;

import java.util.*;

public class Pitanje {
    private String pitanje;
    private double bodovi = 0;
    private Map<String, Odgovor> odgovori = new HashMap<String, Odgovor>();

    public Pitanje(String pitanje, double bod) {
        this.pitanje = pitanje;
        this.bodovi = bod;
    }

    public String getTekst() {
        return pitanje;
    }

    public double getBrojPoena() {
        return bodovi;
    }

    public Map<String, Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setBrojPoena(double poeni) {
        bodovi = poeni;
    }

    public void setTekst(String novoPitanje) {
        pitanje = novoPitanje;
    }

    public void dodajOdgovor(String id, String tekstOdgovora, boolean daLiJeTacan) {
        if(odgovori.containsKey(id))
            throw new IllegalArgumentException("Id odgovora mora biti jedinstven");
        Odgovor odgovor = new Odgovor(tekstOdgovora, daLiJeTacan);
        odgovori.put(id, odgovor);
    }

    public void obrisiOdgovor(String id) {
        if(!odgovori.containsKey(id))
            throw new IllegalArgumentException("Odgovor s ovim id-em ne postoji");
        odgovori.remove(id);
    }

    public List<Odgovor> dajListuOdgovora() {
        ArrayList<Odgovor> listaOdgovora = new ArrayList<Odgovor>();
        for(Odgovor odgovor : odgovori.values())
        {
            listaOdgovora.add(odgovor);
        }
        return listaOdgovora;
    }

    @Override
    public String toString() {
        String odgovor = pitanje + "(" + bodovi + "b)";
        for(String kljuc : odgovori.keySet())
        {

            odgovor+= "\n\t" + kljuc + ": ";
            odgovor += odgovori.get(kljuc).toString();
        }
        return odgovor;
    }

    public double izracunajPoene(List<String>zaokruzeni, SistemBodovanja sistem)
    {
        //Provjera da li nijedan odgovor nije zaokruzen
        //Prva provjera jel je malkice efikasnije
        if(zaokruzeni.isEmpty())
            return 0;

        //Konverzija apstraktne liste u Array listu
        ArrayList<String> zaokruzeniOdgovori = new ArrayList<>(zaokruzeni.size());
        zaokruzeniOdgovori.addAll(zaokruzeni);

        //Provjera da li je ukljucen nepostojeci ID odgovora
        for (String id : zaokruzeniOdgovori)
        {
            if (odgovori.get(id) == null)
                throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
        }

        //Provjera da li postoje dupli odgovori
        //Dodatne zagrade zbog stednje memorije
        {
            ArrayList<String> unikatni = new ArrayList<String>();
            for (String odgovor:zaokruzeniOdgovori)
            {
                if(!unikatni.contains(odgovor))
                    unikatni.add(odgovor);
                else
                    throw new IllegalArgumentException("Postoje duplikati među odabranim odgovorima");
            }
        }

        //Izdvajanje tacnih odgovora
        ArrayList<String> tacniOdgovori = new ArrayList<String>();
        for(String id : odgovori.keySet())
        {
            if (!odgovori.get(id).isTacno())
                continue;
            else
                tacniOdgovori.add(id);
        }

        if (sistem == SistemBodovanja.BINARNO)
        {
            //Nema sve zaokruzene odgovore ili ima viska zaokruzenih
            if(tacniOdgovori.size() != zaokruzeniOdgovori.size())
                return 0;

            //Zaokruzen jedan od netacnih odgovora
            for(String id : zaokruzeniOdgovori)
            {
                if (!tacniOdgovori.contains(id))
                    return 0;
            }
            return bodovi;
        }
        else if(sistem == SistemBodovanja.PARCIJALNO)
        {
            //Ima viska zaokruzenih odgovora, dakle jedan nije tacan
            if(zaokruzeniOdgovori.size() > tacniOdgovori.size())
                return 0;

            //Zaokruzen netacan odgovor
            for(String id : zaokruzeniOdgovori)
            {
                if (!tacniOdgovori.contains(id))
                    return 0;
            }

            //Zaokruzeni svi tacni odgovori
            if (tacniOdgovori.size()==zaokruzeniOdgovori.size())
                return bodovi;

            //Nema smisla da se dijeli s brojem odgovora, vec s brojem tacnih odgovora, ali AT ovo nalaze.
            return bodovi*zaokruzeniOdgovori.size()/odgovori.size();
        }

        else if (sistem ==SistemBodovanja.PARCIJALNO_SA_NEGATIVNIM)
        {
            //Ima viska zaokruzenih odgovora, dakle jedan nije tacan
            if(zaokruzeniOdgovori.size() > tacniOdgovori.size())
                return -bodovi/2;

            //Zaokruzen netacan odgovor
            for(String id : zaokruzeniOdgovori)
            {
                if (!tacniOdgovori.contains(id))
                    return -bodovi/2;
            }

            //Zaokruzeni svi tacni odgovori
            if (tacniOdgovori.size()==zaokruzeniOdgovori.size())
                return bodovi;

            //Nema smisla da se dijeli s brojem odgovora, vec s brojem tacnih odgovora, ali AT ovo nalaze.
            return bodovi*zaokruzeniOdgovori.size()/odgovori.size();
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitanje pitanje1 = (Pitanje) o;
        return pitanje.equalsIgnoreCase(pitanje1.pitanje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitanje);
    }
}
