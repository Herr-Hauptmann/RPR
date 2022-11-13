package ba.unsa.etf.rpr.zadaca2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfesorTest {
    Profesor profesor;

    @BeforeEach
    void setUp() {
        profesor = new Profesor("Tarik", "Horozovic");

    }

    @Test
    void getBrojStudenata() {
        assertEquals(0, profesor.getBrojStudenata());
    }

    @Test
    void setBrojStudenata() {
        profesor.setBrojStudenata(120);
        assertEquals(120, profesor.getBrojStudenata());
    }

    @Test
    void getBrojCasova() {
        assertEquals(0, profesor.getBrojCasova());
    }

    @Test
    void dodajCasNaNormu() {
        profesor.dodajCasNaNormu(12);
        assertEquals(12, profesor.getBrojCasova());
    }

    @Test
    void resetujNormu() {
        profesor.dodajCasNaNormu(123);
        profesor.resetujNormu();
        assertEquals(0, profesor.getBrojCasova());
    }

    @Test
    void testEquals() {
        Profesor profesor2 = new Profesor("Tarik", "Horozovic");
        Profesor profesor3 = new Profesor("Sinan", "Sakic");
        assertAll(
                ()->assertEquals(profesor, profesor2),
                ()->assertNotEquals(profesor, profesor3)
        );
    }

    @Test
    void testToString() {
        profesor.dodajCasNaNormu(123);
        String ispis = "Tarik Horozovic (123)";
        assertEquals(ispis, profesor.toString());
    }

    @Test
    void testHashCode()
    {
        Profesor profesor2 = new Profesor("Tarik", "Horozovic");
        assertEquals(profesor.hashCode(), profesor2.hashCode());
    }
}