package ba.unsa.etf.rpr.zadaca2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakultetTest {
    Fakultet fakultet;
    static Student student = new Student("Tarik", "Horozovic", "18541", 1, 3);
    static Predmet RPR = new ObavezniPredmet(10, "Razvoj programskih rješenja", 3, 1, new Profesor ("Vedran", "Ljubović"), 3);
    static Predmet OBP = new ObavezniPredmet(10, "Osnove baza podataka", 3, 1,  new Profesor ("Emir", "Buza"), 3);
    static Predmet ASP = new ObavezniPredmet(10, "Algoritmi i strukutre podataka", 3, 1,  new Profesor ("", "Ljubović"), 3);
    static List<Predmet> predmeti = new ArrayList<>();
    static Profesor profesor = new Profesor("Tarik", "Horozovic");
    @BeforeAll
    static void upisiPredmete()
    {
        predmeti.add(RPR);
        predmeti.add(OBP);
        predmeti.add(ASP);
        student.upisiObaveznePredmete(predmeti);
    }
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @BeforeEach
    void setUp() {
        fakultet = new Fakultet("Elektrotehnicki fakultet Univerziteta u Sarajevu");
        fakultet.dodajCiklus(1);
        Semestar treci = new Semestar(3);
        treci.dodajPredmetUSemestar(RPR);
        treci.dodajPredmetUSemestar(OBP);
        treci.dodajPredmetUSemestar(ASP);
        fakultet.getCiklusi().get(1).dodajSemestar(treci);
        fakultet.dodajStudenta(student);
    }

    @Test
    void getCiklusi() {
        assertAll(
                () -> assertEquals(1, fakultet.getCiklusi().size()),
                ()-> assertTrue(fakultet.getCiklusi().containsKey(1))
        );
    }

    @Test
    void getStudenti() {
        assertAll(
                () -> assertEquals(1, fakultet.getStudenti().size()),
                () -> assertTrue(fakultet.getStudenti().containsValue(student))
        );
    }

    @Test
    void getSemestar() {
        assertEquals(30, fakultet.getSemestar(1,3).getTrenutnoOpterecenje());
    }

    @Test
    void getNazivFakulteta() {
        assertEquals("Elektrotehnicki fakultet Univerziteta u Sarajevu", fakultet.getNazivFakulteta());
    }

    @Test
    void dodajStudenta() {
        Student novi = new Student("Safet", "Isovic", "12345", 1, 3);
        novi.upisiObaveznePredmete(predmeti);
        fakultet.dodajStudenta(novi);
        assertAll(
                () -> assertEquals(2, fakultet.getStudenti().size()),
                () -> assertTrue(fakultet.getStudenti().containsValue(novi))
        );
    }

    @Test
    void dajListuProfesora() {
        assertTrue(fakultet.dajListuProfesora().isEmpty());
    }

    @Test
    void dodajProfesora() {
        fakultet.dodajProfesora(profesor);
        assertEquals(profesor, fakultet.dajProfesora(0));
    }

}