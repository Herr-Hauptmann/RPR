package ba.unsa.etf.rpr.zadaca2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PredmetTest {
    Predmet RPR;

    @BeforeEach
    void napraviPredmet()
    {
        RPR = new ObavezniPredmet(5, "Razvoj programskih rjesenja", 3,1, new Profesor("Vedran", "Ljubovic"),3);
    }

    @Test
    void getECTS() {
        assertEquals(5, RPR.getEcts());
    }

    @Test
    void setECTS() {
        RPR.setEcts(6);
        assertEquals(6, RPR.getEcts());
    }

    @Test
    void getNazivPredmeta() {
        assertEquals("Razvoj programskih rjesenja", RPR.getNazivPredmeta());
    }

    @Test
    void setNazivPredmeta() {
        RPR.setNazivPredmeta("Razvoj programskih rješenja");
        assertEquals("Razvoj programskih rješenja", RPR.getNazivPredmeta());
    }

    @Test
    void getSemestar() {
        assertEquals(3, RPR.getSemestar());
    }

    @Test
    void setSemestar() {
        RPR.setSemestar(4);
        assertEquals(4, RPR.getSemestar());
    }

    @Test
    void getProfesor() {
        assertAll(
                () -> assertEquals("Ljubovic", RPR.getProfesor().getPrezime() ),
                () -> assertEquals("Vedran", RPR.getProfesor().getIme() )
        );

    }

    @Test
    void setProfesor() {
        RPR.setProfesor(new Profesor ("Samir", "Ribic"));
        assertAll(
                () -> assertEquals("Ribic", RPR.getProfesor().getPrezime() ),
                () -> assertEquals("Samir", RPR.getProfesor().getIme() )
        );
    }

    @Test
    void getBrojPredmeta(){
        assertEquals(3, RPR.getBrojCasovaSedmicno());
    }

    @Test
    void setBrojPredmeta(){
        RPR.setBrojCasovaSedmicno(5);
        assertEquals(5, RPR.getBrojCasovaSedmicno());
    }

    @Test
    void toStringPredmeta()
    {
        assertEquals("Razvoj programskih rjesenja", RPR.toString());
    }

    @Test
    void testEqualsPredmet()
    {
        Predmet OBP = new ObavezniPredmet(5, "Razvoj programskih rjesenja", 3,1, new Profesor("Vedran", "Ljubovic"),3);
        assertEquals(OBP,RPR);
    }

    @Test
    void testToHash()
    {
        Predmet OBP = new ObavezniPredmet(5, "Razvoj programskih rjesenja", 3,1, new Profesor("Vedran", "Ljubovic"),3);
        assertEquals(RPR.hashCode(),OBP.hashCode());
    }

    @Test
    void getCiklusTest()
    {
        assertEquals(1, RPR.getCiklus());
    }
    @Test
    void setCiklusTest()
    {
        RPR.setCiklus(2);
        assertEquals(2, RPR.getCiklus());
    }
}