package ba.unsa.etf.rpr;

public class Drzava {
    private int id;
    private boolean olimpijska;
    private String naziv;
    private Grad glavniGrad;

    public Drzava(int id, String naziv, Grad glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    public Drzava(int id, String naziv, Grad glavniGrad, boolean olimpijska) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
        this.olimpijska = olimpijska;
    }

    public Drzava() {
    }

    public boolean isOlimpijska() {
        return olimpijska;
    }

    public void setOlimpijska(boolean olimpijska) {
        this.olimpijska = olimpijska;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }

    @Override
    public String toString() { return naziv; }
}
