package ba.unsa.etf.rpr.tutorijal5.util;


import ba.unsa.etf.rpr.tutorijal5.Korisnik;

import java.util.List;

public class Kredit {

    public static Double proracunKreditneSposobnosti(KreditnaSposobnost funkcija, Korisnik k1) {
        return funkcija.provjeri(k1.getRacun());
    }

    public static void bezPrekoracenja (List<Korisnik> korisnici) {
        korisnici.stream().filter((Korisnik jedanKorisnik) -> {
            return jedanKorisnik.getRacun().getStanjeRacuna() >= 0;
        }).forEach((Korisnik korisnik) -> {
            System.out.println(korisnik.toString());
        });
    }

    public static void odobriPrekoracenje(List<Korisnik> korisnici)
    {
        korisnici.stream().filter((Korisnik jedanKorisnik) -> {
            return jedanKorisnik.getRacun().getStanjeRacuna() >=10000;
        }).forEach((Korisnik korisnik) ->{
            korisnik.getRacun().odobriPrekoracenje(1000.);
        });
    }

}
