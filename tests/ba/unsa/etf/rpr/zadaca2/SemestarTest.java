package ba.unsa.etf.rpr.zadaca2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemestarTest {

    Predmet RPR = new ObavezniPredmet(5, "Razvoj programskih rješenja", 3, 1, new Profesor ("Vedran", "Ljubović"), 3);
    Predmet OBP = new ObavezniPredmet(5, "Osnove baza podataka", 3, 1,  new Profesor ("Emir", "Buza"), 3);
    Predmet ASP = new ObavezniPredmet(5, "Algoritmi i strukutre podataka", 3, 1,  new Profesor ("", "Ljubović"), 3);
    Semestar treci;

    @BeforeEach
    void kreirajSemsetar(){
        treci = new Semestar(3);
        treci.dodajPredmetUSemestar(RPR);
        treci.dodajPredmetUSemestar(OBP);
        treci.dodajPredmetUSemestar(ASP);
    }

    @Test
    void dajZbirECTS() {
        assertEquals(15, treci.getTrenutnoOpterecenje());
    }

    @Test
    void provjeraRednogBrojaSemestra(){
        assertEquals(3, treci.getRedniBrojSemestra());
    }

    @Test
    void dodajPredmetUSemestar() {
        Predmet LD = new ObavezniPredmet(5, "Logički Dizajn", 3, 1, new Profesor("Novica", "Nosović"), 3);
        treci.dodajPredmetUSemestar(LD);
        assertAll(
                () -> assertTrue(treci.getListaPredmeta().contains(LD)),
                () -> assertEquals(20, treci.getTrenutnoOpterecenje())
        );
    }

    @Test
    void provjeraIspravnostiDodavanjaIzbornogPredmeta() {
        Predmet SP = new IzborniPredmet(5, "Sistemsko programiranje", 3, 1, new Profesor("Samir", "Ribić"),3);
        treci.dodajPredmetUSemestar(SP);
        assertAll(
                () -> assertTrue(treci.getListaPredmeta().contains(SP)),
                () -> assertEquals(20, treci.getTrenutnoOpterecenje())
        );
    }

    @Test
    void provjeraListePredmeta() {
        assertAll(
                () -> assertEquals(3, treci.getListaPredmeta().size()),
                () -> assertTrue(treci.getListaPredmeta().contains(OBP)),
                () -> assertTrue(treci.getListaPredmeta().contains(ASP)),
                () -> assertTrue(treci.getListaPredmeta().contains(RPR))
        );
    }

    @Test
    void pokusajUbacivanjeNepripadajucegPredmeta()
    {
        Predmet UUP = new ObavezniPredmet(6, "Uvod u programiranje", 1,3, new Profesor("Vedran", "Ljubović"),1);
        assertThrows(IllegalArgumentException.class,() -> treci.dodajPredmetUSemestar(UUP));
    }

    @Test
    void pokusajUbacivanjaDuplogPredmeta()
    {
        assertThrows(
                IllegalArgumentException.class, ()->treci.dodajPredmetUSemestar(ASP)
        );
    }

    @Test
    void getListaObaveznihPredmetaTest()
    {
        assertAll(
        () -> assertEquals(3, treci.getListaObaveznihPredmeta().size()),
                () -> assertTrue(treci.getListaObaveznihPredmeta().contains(OBP)),
                () -> assertTrue(treci.getListaObaveznihPredmeta().contains(ASP)),
                () -> assertTrue(treci.getListaObaveznihPredmeta().contains(RPR))
        );
    }

    @Test
    void getListaIzbornihPredmetaTest()
    {
        Predmet NA = new IzborniPredmet(5, "Numericki algoritmi", 3, 1, new Profesor("Zeljko", "Juric"), 3);
        treci.dodajPredmetUSemestar(NA);
        assertAll(
                () -> assertEquals(1, treci.getListaIzbornihPredmeta().size()),
                () -> assertTrue(treci.getListaIzbornihPredmeta().contains(NA))
        );
    }
}