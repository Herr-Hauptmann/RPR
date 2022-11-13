package ba.unsa.etf.rpr.zadaca2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Fakultet {
    String nazivFakulteta;
    private final Map<Integer, Ciklus> ciklusi;
    private final Map<String, Student> studenti;
    private final List<Profesor> profesori;

    public Fakultet(String nazivFakulteta) {
        this.nazivFakulteta = nazivFakulteta;
        ciklusi = new HashMap<>();
        studenti = new HashMap<>();
        profesori = new ArrayList<>();
    }

    public Map<Integer, Ciklus> getCiklusi() {
        return ciklusi;
    }

    public Map<String, Student> getStudenti() {
        return studenti;
    }

    public Semestar getSemestar(int redniBrojCiklusa, int redniBrojSemestra) {
        return ciklusi.get(redniBrojCiklusa).getSemestar(redniBrojSemestra);
    }
    public String getNazivFakulteta() {
        return nazivFakulteta;
    }

    public void dodajStudenta(Student student) {
        if (student.getZbirEECTSBodova() < 30)
            throw new IllegalArgumentException("Student nema dovoljno EECTS bodova da upise semestar!");
        studenti.put(student.getBrojIndexa(), student);
    }

    public List<Profesor> dajListuProfesora() {
        return profesori;
    }

    public Profesor dajProfesora(int index){
        return profesori.get(index);
    }

    public void dodajProfesora(Profesor noviProfesor) {
        profesori.add(noviProfesor);
    }

    public void dodajCiklus(int indexCiklusa) {
        ciklusi.put(indexCiklusa, new Ciklus(indexCiklusa));
    }

    private List<Predmet> dajPredmeteKojiImajuStudente()
    {
        List<Predmet> predmetiSStudentima = new ArrayList<>();
        studenti.values()
                .forEach(student-> student.getOcjene().keySet()
                        .forEach(predmet -> {
                            if (!predmetiSStudentima.contains(predmet))
                                predmetiSStudentima.add(predmet);
                        }));
        return predmetiSStudentima;
    }

    private void obracunajNormuProfesorima()
    {
        profesori.forEach(Profesor::resetujNormu);
        List<Predmet> predmetiSStudentima = dajPredmeteKojiImajuStudente();
        predmetiSStudentima
                .forEach(predmet -> predmet.getProfesor().dodajCasNaNormu(predmet.getBrojCasovaSedmicno()));
    }

    private void obracunajBrojStudenataZaProfesora(Profesor profa)
    {
        AtomicInteger brojStudenata = new AtomicInteger();
        studenti.values()
                .forEach(student-> student.getOcjene().keySet()
                        .forEach(predmet -> {
                            if (predmet.getProfesor() == profa)
                                brojStudenata.addAndGet(1);
                        }));
        profa.setBrojStudenata(brojStudenata.get());
    }

    public void ispisiProfesoreBezNorme()
    {
        obracunajNormuProfesorima();
        profesori.stream()
                .filter(profesor -> profesor.getBrojCasova() < 120)
                .sorted(Comparator.comparing(Profesor::getBrojCasova))
                .forEach(System.out::println);
    }

    public void ispisiProfesoreSNormom()
    {
        obracunajNormuProfesorima();
        profesori.stream()
                .filter(profesor -> profesor.getBrojCasova() >= 120 && profesor.getBrojCasova() <= 150)
                .sorted(Comparator.comparing(Profesor::getBrojCasova))
                .forEach(System.out::println);
    }

    public void ispisiProfesorePrekoNorme()
    {
        obracunajNormuProfesorima();
        profesori.stream()
                .filter(profesor -> profesor.getBrojCasova() > 150)
                .sorted(Comparator.comparing(Profesor::getBrojCasova))
                .forEach(System.out::println);
    }

    public void dodajOcjenuStudentu(String index, int ocjena, Predmet predmet)
    {
        if (!studenti.containsKey(index))
            throw new IllegalArgumentException("Nepostojeci index");
        if (ocjena < 5 || ocjena > 10)
            throw new IllegalArgumentException("Nevalidna ocjena");
        if (!studenti.get(index).getOcjene().containsKey(predmet))
            throw new IllegalArgumentException("Student ne slusa dati predmet");
        studenti.get(index).upisiOcjenu(ocjena, predmet);
    }

    public void sortirajProfesorePoNormi()
    {
        obracunajNormuProfesorima();
        profesori.sort(Comparator.comparing(Profesor::getBrojCasova));
    }

    public void sortirajProfesorePoBrojuStudenata()
    {
        profesori.forEach(this::obracunajBrojStudenataZaProfesora);
        profesori.sort(Comparator.comparing(Profesor::getBrojStudenata));
    }
}
