package ba.unsa.etf.rpr.zadaca2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CiklusTest {
    static Semestar treciSemestar = new Semestar(3);

    @BeforeAll
    static void kreirajValidanSemestar()
    {
        Predmet RPR = new ObavezniPredmet(10, "Razvoj programskih rješenja", 3, 1, new Profesor ("Vedran", "Ljubović"),3);
        Predmet OBP = new ObavezniPredmet(10, "Osnove baza podataka", 3,1, new Profesor ("Emir", "Buza"),3);
        Predmet ASP = new ObavezniPredmet(10, "Algoritmi i strukutre podataka", 3,1, new Profesor ("", "Ljubović"),3);
        treciSemestar.dodajPredmetUSemestar(RPR);
        treciSemestar.dodajPredmetUSemestar(OBP);
        treciSemestar.dodajPredmetUSemestar(ASP);
    }


    @Test
    void getRedniBrojCiklusa() {
        Ciklus prviCiklus = new Ciklus(1);
        assertEquals(1, prviCiklus.getRedniBrojCiklusa());
    }

    @Test
    void setRedniBrojCiklusa() {
        Ciklus prviCiklus = new Ciklus(1);
        prviCiklus.setRedniBrojCiklusa(2);
        assertEquals(2, prviCiklus.getRedniBrojCiklusa());
    }

    @Test
    void setNevalidanRedniBrojCiklusa() {
        Ciklus prviCiklus = new Ciklus(11);
        assertThrows(
                IllegalArgumentException.class, () -> prviCiklus.setRedniBrojCiklusa(-2)
        );
    }

    @Test
    void getSemestriPrazan() {
        Ciklus prviCiklus = new Ciklus(1);
        assertTrue(prviCiklus.getSemestri().isEmpty());
    }

    @Test
    void getSemestri()
    {
        Ciklus prviCiklus = new Ciklus(1);
        prviCiklus.dodajSemestar(treciSemestar);
        assertAll(
                () -> assertEquals(1, prviCiklus.getSemestri().size()),
                () -> assertTrue(prviCiklus.getSemestri().containsValue(treciSemestar)),
                () -> assertTrue(prviCiklus.getSemestri() instanceof HashMap)
        );
    }

    @Test
    void getSemestar() {
        Ciklus prviCiklus = new Ciklus(1);
        prviCiklus.dodajSemestar(treciSemestar);
        assertEquals(prviCiklus.getSemestar(3), treciSemestar);
    }
    @Test
    void getNevalidanSemestar() {
        Ciklus prviCiklus = new Ciklus(1);
        prviCiklus.dodajSemestar(treciSemestar);
        assertThrows(
          IllegalArgumentException.class, () -> prviCiklus.getSemestar(2)
        );
    }

    @Test
    void dodajSemestarManjePredmeta() {
        Semestar treciSemestarNevalidan = new Semestar(3);
        Predmet RPR = new ObavezniPredmet(10, "Razvoj programskih rješenja", 3,1, new Profesor ("Vedran", "Ljubović"),3);
        Predmet OBP = new ObavezniPredmet(10, "Osnove baza podataka", 3, 1,new Profesor ("Emir", "Buza"),3);
        treciSemestarNevalidan.dodajPredmetUSemestar(RPR);
        treciSemestarNevalidan.dodajPredmetUSemestar(OBP);

        Ciklus drugiCiklus = new Ciklus(2);
        assertThrows(
               IllegalArgumentException.class, ()->  drugiCiklus.dodajSemestar(treciSemestarNevalidan)
        );
    }

    @Test
    void dodajDupliSemestar(){
        Ciklus drugiCiklus = new Ciklus(2);
        drugiCiklus.dodajSemestar(treciSemestar);
        assertThrows(
                IllegalArgumentException.class, ()->  drugiCiklus.dodajSemestar(treciSemestar)
        );
    }

    @Test
    void dodajIstiSemestar()
    {
        Ciklus drugiCiklus = new Ciklus(2);
        drugiCiklus.dodajSemestar(treciSemestar);
        Semestar treciSemestarNevalidan = new Semestar(3);
        Predmet RPR = new ObavezniPredmet(10, "Razvoj programskih rješenja", 3,1, new Profesor ("Vedran", "Ljubović"),3);
        Predmet OBP = new ObavezniPredmet(10, "Osnove baza podataka", 3,1, new Profesor ("Emir", "Buza"),3);
        Predmet ASP = new ObavezniPredmet(10, "Algoritmi i strukutre podataka", 3,1, new Profesor ("", "Ljubović"),3);
        treciSemestarNevalidan.dodajPredmetUSemestar(RPR);
        treciSemestarNevalidan.dodajPredmetUSemestar(OBP);
        treciSemestarNevalidan.dodajPredmetUSemestar(ASP);

        assertThrows(
                IllegalArgumentException.class, ()->  drugiCiklus.dodajSemestar(treciSemestarNevalidan)
        );

    }
}