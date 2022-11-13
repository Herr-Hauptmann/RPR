package ba.unsa.etf.rpr.tutorijal02;

public class Interval<toString> {
    private double pocetnaTacka;
    private double krajnjaTacka;
    private boolean pocetnaUIntervalu;
    private boolean krajnjaUIntervalu;

    public Interval(double x, double y, boolean xUntura, boolean xVani){
        pocetnaTacka = x;
        krajnjaTacka = y;
        pocetnaUIntervalu = xUntura;
        krajnjaUIntervalu = xVani;
        if (x > y)
            throw new IllegalArgumentException("Pocetna tacka intervala je veca od krajnje");
    }
    public String toString()
    {
        char prva = '(', druga = ')';
        if (pocetnaUIntervalu)
            prva = '[';
        if (krajnjaUIntervalu)
            druga = ']';
        String novi = "" + prva + pocetnaTacka + "," + krajnjaTacka + druga;

        return novi;
    }

    public boolean isIn(double tacka)
    {
        if (tacka > pocetnaTacka && tacka < krajnjaTacka)
            return true;
        if (tacka == pocetnaTacka && pocetnaUIntervalu)
            return true;
        if (tacka == krajnjaTacka && krajnjaUIntervalu)
            return true;
        return false;
    }


//    public boolean isNull() {
//    }
}
