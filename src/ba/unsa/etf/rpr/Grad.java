package ba.unsa.etf.rpr;

public class Grad {
    private int id;
    private boolean olimpijski = false;
    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava, boolean olimpijski) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
        if (olimpijski && (drzava == null || !this.getDrzava().isOlimpijska()))
            throw new NotOlimpicCountryException("Država " + this.getDrzava().getNaziv() + " nije olimpijska država");
        this.olimpijski = olimpijski;
    }

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;

    }

    public Grad() {
    }

    public boolean isOlimpijski() {
        return olimpijski;
    }

    public void setOlimpijski(boolean olimpijski) throws NotOlimpicCountryException {
        if (olimpijski && (drzava == null || !this.getDrzava().isOlimpijska()))
        {
            if (drzava!=null)
                throw new NotOlimpicCountryException("Država " + this.getDrzava().getNaziv() + " nije olimpijska država");
            else{
                throw new NotOlimpicCountryException("Niste odabrali drzavu!");
            }
        }
        this.olimpijski = olimpijski;
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

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    @Override
    public String toString() { return naziv; }
}
