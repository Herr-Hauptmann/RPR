package ba.unsa.etf.rpr.tutorijal8;

import java.io.Serializable;
import java.util.Objects;

public class Grad implements Serializable {
    private String naziv = "";
    private int brojStanovnika = 0;
    private double[] temperature = new double[1000];

    public Grad() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grad grad = (Grad) o;
        return Objects.equals(naziv, grad.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }
}
