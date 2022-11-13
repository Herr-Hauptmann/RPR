package ba.unsa.etf.rpr;

public class Macka extends Zivotinja{
    Macka(String id, String ime) throws NeispravanFormatIdaException {
        super(id, ime);
    }

    @Override
    public String glas() {
        if (this instanceof Tigar)
            return "rrrr";
        else if(this instanceof Lav)
            return "roar";
        else return "mjau";
    }
}
