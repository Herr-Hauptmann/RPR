package ba.unsa.etf.rpr.tutorijal_3;

public class MobilniBroj extends TelefonskiBroj {
    private final int mobilnaMreza;
    private final String broj;

    MobilniBroj(int mobilnaMreza, String broj) {
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        String finalniBrojTelefona = "0";
        finalniBrojTelefona += mobilnaMreza;
        finalniBrojTelefona += "/";
        finalniBrojTelefona += broj;
        return finalniBrojTelefona;
    }

    @Override
    public int hashCode() {
        return broj.hashCode();
    }
}
