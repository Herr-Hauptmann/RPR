package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class ArtiklTest {

    @org.junit.jupiter.api.Test
    void testGetterNaziva() {
        Artikl artikl = new Artikl("Avion", 1800, "1");
        assertEquals(artikl.getNaziv(), "Avion");
    }

    @org.junit.jupiter.api.Test
    void testGetterKoda() {
        Artikl artikl = new Artikl("Avion", 1800, "1");
        assertEquals(artikl.getKod(), "1");
    }

    @org.junit.jupiter.api.Test
    void testGetterCijena() {
        Artikl artikl = new Artikl("Avion", 1800, "1");
        assertEquals(artikl.getCijena(), 1800);
    }
}