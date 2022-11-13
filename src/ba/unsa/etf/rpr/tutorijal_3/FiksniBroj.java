package ba.unsa.etf.rpr.tutorijal_3;

public class FiksniBroj extends TelefonskiBroj {
    private final String broj;
    private final Grad grad;

    public FiksniBroj(Grad grad, String broj) {
        this.broj = broj;
        this.grad = grad;
    }

    @java.lang.Override
    public String ispisi() {
        String cijeliBrojTelefona = "";
        cijeliBrojTelefona += grad.dajPozivniBroj(grad.ordinal());
        cijeliBrojTelefona += "/";
        cijeliBrojTelefona += broj;
        return cijeliBrojTelefona;
    }

    @Override
    public int hashCode() {
        return broj.hashCode();
    }
}
