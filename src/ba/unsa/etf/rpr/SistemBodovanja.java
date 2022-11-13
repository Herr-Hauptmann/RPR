package ba.unsa.etf.rpr;

public enum SistemBodovanja {
    BINARNO,
    PARCIJALNO,
    PARCIJALNO_SA_NEGATIVNIM;

    private static final String[] ispis = {"binarno", "parcijalno", "parcijalno sa negativnim bodovima"};
    public static String dajIspis(int i)
    {
        return ispis[i];
    }
}
