package ba.unsa.etf.rpr;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Zivotinja {
    private String id;
    private String ime;

    private boolean provjeriId(String id, String ime)
    {
        if (!id.equals(id.toLowerCase()))
            return false;
        if (!id.contains("-"))
            return false;
        String razdvojenId = id.split("-")[0];
        String razdvojenId2;
        try{
            razdvojenId2 = id.split("-")[1];
        }catch(Exception e)
        {
            return false;
        }

        String privremenoIme = new String(ime);
        privremenoIme= privremenoIme.replace('č', 'c');
        privremenoIme= privremenoIme.replace('ć', 'c');
        privremenoIme= privremenoIme.replace('ž', 'z');
        privremenoIme= privremenoIme.replace('đ', 'd');
        privremenoIme= privremenoIme.replace('š', 's');
        privremenoIme= privremenoIme.replace(" ", "");
        privremenoIme= privremenoIme.replace('Č', 'c');
        privremenoIme= privremenoIme.replace('Ć', 'c');
        privremenoIme= privremenoIme.replace('Ž', 'z');
        privremenoIme= privremenoIme.replace('Đ', 'd');
        privremenoIme= privremenoIme.replace('Š', 's');
        privremenoIme= privremenoIme.replace(" ", "");
        privremenoIme=privremenoIme.replaceAll("[^a-zA-Z]", "");
        if (!razdvojenId.toLowerCase().equals(privremenoIme.toLowerCase()))
            return false;
        String provjera = "[0-9]+";
        Pattern p = Pattern.compile(provjera);
        Matcher m = p.matcher(razdvojenId2);
        return m.matches();
    }

    public Zivotinja(String id, String ime) throws NeispravanFormatIdaException {
        if (ime.isEmpty() || ime.isBlank())
            throw new IllegalArgumentException("Ime ne može biti prazno");
        if (id.isEmpty() || id.isBlank())
            throw new IllegalArgumentException("Id ne može biti prazan");
        if (!provjeriId(id, ime))
            throw new NeispravanFormatIdaException("Neispravan id");
        this.id = id;
        this.ime = ime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.isEmpty() || id.isBlank())
            throw new IllegalArgumentException("Id ne može biti prazan");
        if (!provjeriId(id, ime))
            throw new NeispravanFormatIdaException("Neispravan id");
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) throws NeispravanFormatIdaException {
        if (ime.isEmpty() || ime.isBlank())
            throw new IllegalArgumentException("Ime ne može biti prazno");
        if (!provjeriId(id, ime))
            throw new NeispravanFormatIdaException("Neispravan id");
        this.ime = ime;
    }

    public abstract String glas();
}
