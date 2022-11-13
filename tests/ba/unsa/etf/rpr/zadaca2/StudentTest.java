package ba.unsa.etf.rpr.zadaca2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student student;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @BeforeEach
    void setUp() {
        student = new Student("Tarik", "Horozovic", "18541", 1, 3);
    }

    @Test
    void getBrojIndexa() {
        assertEquals("18541", student.getBrojIndexa());
    }

    @Test
    void getZbirEECTSBodova() {
        assertEquals(0, student.getZbirEECTSBodova());
    }

    @Test
    void getSemestar() {
        assertEquals(3, student.getSemestar());
    }

    @Test
    void getCiklus() {
        assertEquals(1, student.getCiklus());
    }

    @Test
    void setSemestar() {
        student.setSemestar(4);
        assertEquals(4, student.getSemestar());
    }

    @Test
    void setCiklus() {
        student.setCiklus(2);
        assertEquals(2, student.getCiklus());
    }

    @Test
    void getOcjene() {
        assertTrue(student.getOcjene().isEmpty());
    }

    @Test
    void upisiIzborniPredmet() {
        Predmet US = new IzborniPredmet(5, "Ugradbeni sistemi", 4, 1, new Profesor("Samim", "Konicija"), 2);
        student.upisiIzborniPredmet(US);
        assertAll(
                () -> assertTrue(student.getOcjene().containsKey(US)),
                () -> assertEquals(1, student.getOcjene().size()),
                () -> assertEquals(5, student.getOcjene().get(US))
        );
    }

    @Test
    void upisiObaveznePredmete() {
        Predmet RPR = new ObavezniPredmet(10, "Razvoj programskih rješenja", 3, 1, new Profesor ("Vedran", "Ljubović"),3);
        Predmet OBP = new ObavezniPredmet(10, "Osnove baza podataka", 3,1, new Profesor ("Emir", "Buza"),3);
        Predmet ASP = new ObavezniPredmet(10, "Algoritmi i strukutre podataka", 3,1, new Profesor ("", "Ljubović"),3);
        List<Predmet> listaObaveznihPredmeta = new ArrayList<>();
        listaObaveznihPredmeta.add(RPR);
        listaObaveznihPredmeta.add(OBP);
        listaObaveznihPredmeta.add(ASP);
        student.upisiObaveznePredmete(listaObaveznihPredmeta);
        assertAll(
                () -> assertEquals(3, student.getOcjene().size()),
                () -> assertTrue(student.getOcjene().keySet().containsAll(listaObaveznihPredmeta))
        );
    }

    @Test
    void ispisiOcjene() {
        Predmet RPR = new ObavezniPredmet(10, "Razvoj programskih rješenja", 3, 1, new Profesor ("Vedran", "Ljubović"),3);
        Predmet OBP = new ObavezniPredmet(10, "Osnove baza podataka", 3,1, new Profesor ("Emir", "Buza"),3);
        Predmet ASP = new ObavezniPredmet(10, "Algoritmi i strukutre podataka", 3,1, new Profesor ("", "Ljubović"),3);
        List<Predmet> listaObaveznihPredmeta = new ArrayList<>();
        listaObaveznihPredmeta.add(RPR);
        listaObaveznihPredmeta.add(OBP);
        listaObaveznihPredmeta.add(ASP);
        student.upisiObaveznePredmete(listaObaveznihPredmeta);
        student.ispisiOcjene();
        String ispis =
                "Predmet: Algoritmi i strukutre podataka - Ocjena: 5\r\n"+
                "Predmet: Osnove baza podataka - Ocjena: 5\r\n" +
                "Predmet: Razvoj programskih rješenja - Ocjena: 5\r\n";
        assertEquals(ispis, outContent.toString());
    }

    @Test
    void upisiOcjenu() {
        //Kreiranje predmeta
        Predmet RPR = new ObavezniPredmet(10, "Razvoj programskih rješenja", 3, 1, new Profesor ("Vedran", "Ljubović"),3);
        Predmet OBP = new ObavezniPredmet(10, "Osnove baza podataka", 3,1, new Profesor ("Emir", "Buza"),3);
        Predmet ASP = new ObavezniPredmet(10, "Algoritmi i strukutre podataka", 3,1, new Profesor ("", "Ljubović"),3);
        List<Predmet> listaObaveznihPredmeta = new ArrayList<>();
        listaObaveznihPredmeta.add(RPR);
        listaObaveznihPredmeta.add(OBP);
        listaObaveznihPredmeta.add(ASP);
        student.upisiObaveznePredmete(listaObaveznihPredmeta);

        //Upisivanje ocijena
        student.upisiOcjenu(10, RPR);
        student.upisiOcjenu(10, ASP);
        student.upisiOcjenu(10, OBP);

        //Ispisivanje ocijena
        student.ispisiOcjene();
        String ispis =
                "Predmet: Algoritmi i strukutre podataka - Ocjena: 10\r\n"+
                        "Predmet: Osnove baza podataka - Ocjena: 10\r\n" +
                        "Predmet: Razvoj programskih rješenja - Ocjena: 10\r\n";
        //Test
        assertEquals(ispis, outContent.toString());
    }
}