package ba.unsa.etf.rpr;

import java.util.Objects;

public class Drzava {
    private int id = 5;
    private String naziv;
    private Grad glavniGrad;

    public Drzava() {
    }

    public Drzava(int id, String naziv, Grad glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
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
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drzava drzava = (Drzava) o;
        return id == drzava.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
