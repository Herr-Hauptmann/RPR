package ba.unsa.etf.rpr.tutorijal5.util;

import ba.unsa.etf.rpr.tutorijal5.Banka;
import ba.unsa.etf.rpr.tutorijal5.Korisnik;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KreditTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void resetOut() {
        System.setOut(standardOut);
    }


    @Test
    void testBezPrekoracenja() {
        Banka b = new Banka();
        Kredit kredit = new Kredit();

        Korisnik k1 = b.kreirajNovogKorisnika("Ime1", "Prezime1");
        b.kreirajRacunZaKorisnika(k1);

        Korisnik k2 = b.kreirajNovogKorisnika("Ime2", "Prezime2");
        b.kreirajRacunZaKorisnika(k2);

        k1.getRacun().izvrsiUplatu(10000.);
        k2.getRacun().izvrsiUplatu(-1000.);

        kredit.bezPrekoracenja(b.getKorisnici());

        assertEquals("{ime='Ime1', prezime='Prezime1'}", outputStreamCaptor.toString().trim());
    }

    @Test
    void testOdobriPrekoracenje(){
        Banka b = new Banka();
        Kredit kredit = new Kredit();
        Korisnik k1 = b.kreirajNovogKorisnika("Ime1", "Prezime1");
        b.kreirajRacunZaKorisnika(k1);
        Korisnik k2 = b.kreirajNovogKorisnika("Ime2", "Prezime2");
        b.kreirajRacunZaKorisnika(k2);
        Korisnik k3 = b.kreirajNovogKorisnika("Ime2", "Prezime2");
        b.kreirajRacunZaKorisnika(k3);
        Korisnik k4 = b.kreirajNovogKorisnika("Ime2", "Prezime2");
        b.kreirajRacunZaKorisnika(k4);

        k1.getRacun().izvrsiUplatu(15000.);
        k2.getRacun().izvrsiUplatu(1000.);
        k3.getRacun().izvrsiUplatu(10000.);
        k4.getRacun().izvrsiUplatu(9999.999999999999);

        Kredit.odobriPrekoracenje(b.getKorisnici());

        assertEquals(1000., k1.getRacun().getOdobrenjePrekoracenja());
        assertEquals(0, k2.getRacun().getOdobrenjePrekoracenja());
        assertEquals(1000., k3.getRacun().getOdobrenjePrekoracenja());
        assertEquals(0, k4.getRacun().getOdobrenjePrekoracenja());
    }
}