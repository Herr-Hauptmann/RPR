package ba.unsa.etf.rpr;

public class Pas extends Zivotinja{
    private String glas;

    Pas(String id, String ime) throws NeispravanFormatIdaException {
        super(id, ime);
    }

    @Override
    public String glas() {
        if (this instanceof Vuk)
            return "auuu";
        else return "av";
    }
}
