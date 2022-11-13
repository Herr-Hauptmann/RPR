package ba.unsa.etf.rpr;

public class Odgovor {
    private String odgovor;
    private boolean tacan;

    public Odgovor(String odgovor, boolean tacan) {
        this.odgovor = odgovor;
        this.tacan = tacan;
    }

    public String getTekstOdgovora() {
        return odgovor;
    }

    public boolean isTacno() {
        return tacan;
    }

    public void setTacno(boolean b) {
        this.tacan = b;
    }

    public void setTekstOdgovora(String odgovor) {
        this.odgovor = odgovor;
    }

    public int hashCode() {
        return odgovor.hashCode();
    }

    @Override
    public boolean equals(Object drugi) {
        if (drugi != null && getClass() == drugi.getClass()) {
            Odgovor drugiOdgovor = (Odgovor) drugi;
            return drugiOdgovor.getTekstOdgovora().equals(odgovor) && drugiOdgovor.isTacno() == tacan;
        }
        return false;
    }

    @Override
    public String toString() {
        return odgovor;
    }
}