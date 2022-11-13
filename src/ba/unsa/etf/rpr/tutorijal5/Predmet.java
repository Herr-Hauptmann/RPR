package ba.unsa.etf.rpr.tutorijal5;

abstract public class Predmet {
    private int ECTS;
    private String nazivPredmeta;
    private int semestar;

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public int dajECTS(){
        return ECTS;
    }


}
