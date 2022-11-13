package ba.unsa.etf.rpr;

import java.io.StringBufferInputStream;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void igrajKviz(Kviz kviz)
    {
        Scanner in = new Scanner(System.in);
        HashMap<Pitanje, ArrayList<String>> zaokruzeniOdgovori = new HashMap<Pitanje, ArrayList<String>>();
        for(Pitanje pitanje : kviz.getPitanja())
        {
            ArrayList<String> odgovori = new ArrayList<String>();
            System.out.println(pitanje.toString());
            System.out.println("Unesi odgovore koje smatras tacnim (odvojene zrezom, 0 za prazan odgovor):");
            String unosOdgovora = in.nextLine();
            if(unosOdgovora.equals("0"))
            {
                zaokruzeniOdgovori.put(pitanje, odgovori);
                continue;
            }
            odgovori.addAll(Arrays.asList(unosOdgovora.split(",")));
            zaokruzeniOdgovori.put(pitanje, odgovori);
        }

        System.out.println(kviz.predajKviz(zaokruzeniOdgovori).toString());
        System.out.println("Da li zelite pogledati tacne odgovore kviza? (true ili false)");
        try{

            if(in.nextBoolean() == true)
            {
                System.out.println(kviz.toString());
            }
        }catch(java.util.InputMismatchException e)
        {
            System.out.println("Niste unijeli vrijednost true ili false! Sad ne zasluzujete da znate odgovore.");
        }

    }

    public static Kviz kreirajKviz() {
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        System.out.println("Dobrodošli u generator kvizova!");

        //Unos naziva
        String naziv;
        System.out.println("Unesite naziv kviza: ");
        naziv = in.nextLine();

        //Odabir nacina bodovanja
        System.out.println("Odaberite nacin bodovanja kviza:");
        for (int i = 0; i < 3;) {
            System.out.println("" + ++i + ") " + SistemBodovanja.dajIspis(i-1) + "");
        }

        int sis = -1;
        while (sis < 1 || sis > 3)
        {
            try{
                sis = in.nextInt();
            }catch (java.util.InputMismatchException e){
                System.out.println("Unesi cifru od 1 do 3!");
                in.nextLine();
            }

            if(sis < 1 || sis > 3){
                System.out.println("Neispravn unos, probaj opet");
            }
        }

        SistemBodovanja sistem = SistemBodovanja.values()[sis-1];

        Kviz kviz = new Kviz(naziv, sistem);

        //Unos pitanja
        String unos;
        System.out.println("Uneste pitanja (\"gotovo\" za kraj)!");
        int i = 1;
        for(;;)
        {
            System.out.println("Unesi tekst " + i + ". pitanja:");
            unos = in2.nextLine();
            if (unos.equals("gotovo"))
                break;
            System.out.println("Unesite broj bodova koje nosi ovo pitanje: ");
            double bodovi = -1;
            while(bodovi == -1)
            {
                try{
                    bodovi = in.nextDouble();
                }catch(java.util.InputMismatchException e){
                    System.out.println("Unesite validan broj bodova!");
                    in.nextLine();
                }
            }


            in.nextLine();
            Pitanje pitanje = new Pitanje(unos,bodovi);

            //Unos odgovora
            System.out.println("Uneste odgovore (\"gotovo\" za kraj)!");
            for(;;)
            {
                System.out.println("Unesite id odgovora: ");
                String id = in.nextLine();
                if (id.equals("gotovo"))
                    break;
                System.out.println("Unesite tekst odgovora: ");
                String tekstOdgovora = in.nextLine();
                if (tekstOdgovora.equals("gotovo"))
                    break;
                System.out.println("Da li je odgovor tacan?(true, false)");
                int unesenBool = -1;
                while(unesenBool == -1)
                {
                    try{
                        boolean tacan = in.nextBoolean();
                        unesenBool = 1;
                        pitanje.dodajOdgovor(id, tekstOdgovora, tacan);
                    }catch (java.util.InputMismatchException e){
                        System.out.println("Unesi ili \'true\' ili 'false'!");
                        in.nextLine();
                    }
                }
                in.nextLine();
            }
            i++;
            kviz.dodajPitanje(pitanje);
        }
        return kviz;
    }

    public static void main(String[] args) {
        Kviz kviz = kreirajKviz();
        igrajKviz(kviz);
    }
}
