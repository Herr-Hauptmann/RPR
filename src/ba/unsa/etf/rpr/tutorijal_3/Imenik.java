package ba.unsa.etf.rpr.tutorijal_3;

import java.util.HashMap;

public class Imenik {
    private HashMap<String, TelefonskiBroj> imenik = new HashMap<String, TelefonskiBroj>();

    public void dodaj(String ime, TelefonskiBroj broj)
    {
        imenik.put(ime,broj);
    }
    public String dajBroj(String ime)
    {
        TelefonskiBroj broj =  imenik.get(ime);
        return broj.ispisi();
    }
    public String dajIme(TelefonskiBroj broj)
    {
        for (String kljuc : imenik.keySet())
        {
            if (broj.equals(imenik.get(kljuc)))
                return kljuc;
        }
        return null;
    }
    public String naSlovo(char s)
    {
        HashMap<String, TelefonskiBroj> naSlovo = new HashMap<String, TelefonskiBroj>();
        for (String kljuc : imenik.keySet())
            if (s ==kljuc.charAt(0) || Character.toUpperCase(s) == kljuc.charAt(0))
                naSlovo.put(kljuc, imenik.get(kljuc));
        String finalniString = "";
        int i = 1;
        for (String kljuc : naSlovo.keySet())
        {
            if (!finalniString.isEmpty())
            {
                finalniString+="\n";
            }
            finalniString+= i + ". " + kljuc + " - " + naSlovo.get(kljuc).ispisi();
        }
        return finalniString;
    }
}
