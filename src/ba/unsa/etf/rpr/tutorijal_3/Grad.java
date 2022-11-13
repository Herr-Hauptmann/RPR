package ba.unsa.etf.rpr.tutorijal_3;

public enum Grad {
    BIHAĆ,
    ORAŠJE,
    TUZLA,
    ZENICA,
    GORAŽDE,
    TRAVNIK,
    MOSTAR,
    ŠIROKI_BRIJEG,
    SARAJEVO,
    LIVNO,
    BRCKO,
    MRKONJIĆ_GRAD,
    BANJA_LUKA,
    PRIJEDOR,
    DOBOJ,
    SAMAC,
    BILJELJINA,
    ZVORNIK,
    PALE,
    FOČA,
    TREBINJE;

    private final String[] pozivniBrojevi = {
            "037", //Bihać
            "031", //Orasje
            "035", //Tuzla
            "032", //Zenica
            "038", //Gorazde
            "030", //Travnik
            "036", //Mostar
            "039", //Siroki
            "033", //Sarajevo
            "034", //Livno
            "049", //Brcko
            "050", //Mrkonjic grad
            "051", //Banjaluka
            "052", //Prijedor
            "053", //Doboj
            "054", //Samac
            "055", //Bijeljina
            "056", // Zvornik
            "057", //pale
            "058", //Foca
            "059" //Trebinje
    };

    public String dajPozivniBroj(int index) {
        return pozivniBrojevi[index];
    }
}
