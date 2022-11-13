package ba.unsa.etf.rpr.zadaca2;

public class Predmet {
    private int ects;
    private String nazivPredmeta;
    private int semestar;
    private int ciklus;
    private Profesor profesor;
    private int brojCasovaSedmicno;

    public Predmet(int ects, String nazivPredmeta, int semestar, int ciklus, Profesor profesor, int brojCasovaSedmicno) {
        this.ects = ects;
        this.nazivPredmeta = nazivPredmeta;
        this.semestar = semestar;
        this.profesor = profesor;
        this.ciklus = ciklus;
        this.brojCasovaSedmicno = brojCasovaSedmicno;
    }

    public int getEcts() {
        return ects;
    }

    public int getBrojCasovaSedmicno() {
        return brojCasovaSedmicno;
    }

    public void setBrojCasovaSedmicno(int brojCasovaSedmicno) {
        this.brojCasovaSedmicno = brojCasovaSedmicno;
    }

    public int getCiklus() {
        return ciklus;
    }

    public void setCiklus(int ciklus) {
        this.ciklus = ciklus;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return getNazivPredmeta();
    }

    @Override
    public int hashCode() {
        return nazivPredmeta.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predmet predmet1 = (Predmet) o;
        return nazivPredmeta.equals(predmet1.nazivPredmeta) && getSemestar() == predmet1.getSemestar() && ciklus == predmet1.ciklus;
    }
}