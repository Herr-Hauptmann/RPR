package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KorpaTest {

    @Test
    void dodajArtikl() {
        Artikl artikl = new Artikl("Avion", 1800, "1");
        Korpa korpa = new Korpa();
        assertTrue(korpa.dodajArtikl(artikl));
    }

    @Test
    void getArtikli() {
        Artikl artikl1 = new Artikl("Avion", 1800, "1");
        Artikl artikl2 = new Artikl("Auto", 1200, "2");
        Artikl artikl3 = new Artikl("Avion", 1000, "3");
        Korpa korpa = new Korpa();
        korpa.dodajArtikl(artikl1);
        korpa.dodajArtikl(artikl2);
        korpa.dodajArtikl(artikl3);
        assertEquals(artikl2.getCijena(), korpa.getArtikli()[1].getCijena());
    }

//    @Test
//    void izbaciArtiklSaKodom() {
//    }
//
//    @Test
//    void dajUkupnuCijenuArtikala() {
//    }
}