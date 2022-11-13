package ba.unsa.etf.rpr.zadaca2;

public class Profesor extends Osoba {
    private int brojCasova;
    private int brojStudenata;

    public Profesor(String ime, String prezime) {
        this.setIme(ime);
        this.setPrezime(prezime);
        brojCasova = 0;
        brojStudenata = 0;
    }

    public int getBrojStudenata() {
        return brojStudenata;
    }

    public void setBrojStudenata(int brojStudenata) {
        this.brojStudenata = brojStudenata;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void dodajCasNaNormu(int cas)
    {
        brojCasova+=cas;
    }

    public void resetujNormu() {
        brojCasova = 0;
    }

    @Override
    public int hashCode() {
        String imePrezime = getIme()+getPrezime();
        return imePrezime.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesor profesor1 = (Profesor) o;
        return profesor1.getIme().equalsIgnoreCase(getIme()) && profesor1.getPrezime().equalsIgnoreCase(getPrezime());
    }

    @Override
    public String toString() {
        return ""+ getIme() + " " + getPrezime() + " (" + getBrojCasova() +")";
    }

}
