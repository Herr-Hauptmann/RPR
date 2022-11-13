package ba.unsa.etf.rpr.zadaca2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static HashMap<Integer, Fakultet> fakulteti = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("Dobrodošli na ISSS za sirotinju!");
        menu();
    }

    public static void menu()
    {
        for(;;)
        {
            System.out.println("Odaberite opciju:");
            System.out.println("1 - Dodaj fakultet");
            System.out.println("2 - Dodaj profesora");
            System.out.println("3 - Dodaj studenta");
            System.out.println("4 - Dodaj predmet");
            System.out.println("5 - Dodaj semestar");
            System.out.println("6 - Dodaj ciklus");
            System.out.println("7 - Ispisi profesore s normom");
            System.out.println("8 - Ispisi profesore preko norme");
            System.out.println("9 - Ispisi profesore bez norme");
            System.out.println("10 - Dodaj ocjenu studentu");
            System.out.println("11 - Ispisi prepis ocijena za studenta");
            System.out.println("0 - Izlaz");

            Scanner in = new Scanner(System.in);
            int unos = in.nextInt();
            if (unos == 0)
                break;
            switch (unos) {
                case 1 -> {
                    try {
                        kreirajFakultet();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        kreirajProfesora();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        kreirajStudenta();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        kreirajPredmet();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        kreirajSemestar();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> {
                    try {
                        kreirajCiklus();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 7 ->
                        {
                            Fakultet fakultet = odaberiFakultet();
                            if (fakultet != null)
                                fakultet.ispisiProfesoreSNormom();

                        }
                case 8 -> {
                    Fakultet fakultet = odaberiFakultet();
                    if (fakultet != null)
                        fakultet.ispisiProfesorePrekoNorme();

                }
                case 9 -> {
                    Fakultet fakultet = odaberiFakultet();
                    if (fakultet != null)
                        fakultet.ispisiProfesoreBezNorme();

                }
                case 10 -> ocijeniStudenta();
                case 11 -> prepisOcjena();
                case 12 -> {
                    Fakultet fakultet = odaberiFakultet();
                    if (fakultet != null)
                        fakultet.sortirajProfesorePoBrojuStudenata();
                }
                case 13 -> {
                    Fakultet fakultet = odaberiFakultet();
                    if (fakultet != null)
                        fakultet.sortirajProfesorePoNormi();
                }
                default -> System.out.println("Nevalidan izbor!");
            }
        }
    }

    private static void prepisOcjena() {
        Fakultet fakultet = odaberiFakultet();
        if (fakultet == null) return;
        Student student = odaberiStudenta(fakultet);
        if (student == null)
            return;

        student.ispisiOcjene();
    }

    private static void ocijeniStudenta() {
        Fakultet fakultet = odaberiFakultet();
        if (fakultet == null) return;
        Student student = odaberiStudenta(fakultet);
        if (student == null)
            return;

        System.out.println("Odaberi predmet iz kog zelite unijeti ocjenu");

        int i = 1;
        for(Predmet predmet : student.getOcjene().keySet())
        {
            System.out.println(""+i +" - " + predmet);
        }
        Scanner in = new Scanner(System.in);
        int unos = in.nextInt();

        if (unos <= 0 || unos>student.getOcjene().keySet().size())
        {
            System.out.println("Nevalidan predmet");
            return;
        }

        List keys = new ArrayList(student.getOcjene().keySet());
        Predmet predmet = (Predmet) keys.get(unos-1);

        System.out.println("Unesite ocjenu: ");
        int ocjena = in.nextInt();

        student.upisiOcjenu(ocjena,predmet);
    }

    //Funkcije koje sluze za odabir

    public static Student odaberiStudenta(Fakultet fakultet)
    {
        System.out.println("Unesite broj indexa: ");
        Scanner in = new Scanner(System.in);
        String indeks = in.nextLine();

        Student student = fakultet.getStudenti().get(indeks);
        if (student == null){
            System.out.println("Ne postoji student s ovim brojem indeksa");;
        }
        return student;
    }

    public static Fakultet odaberiFakultet()
    {
        System.out.println("Odaberi fakultet: ");
        if (fakulteti.isEmpty())
        {
            System.out.println("Niste unijeli nijedan fakultet!");
            return null;
        }

        for (Integer i : fakulteti.keySet())
            System.out.println(""+i+" - "+fakulteti.get(i).getNazivFakulteta());
        Scanner in = new Scanner(System.in);
        int odabir = 0;
        while(true)
        {
            odabir = in.nextInt();
            if (!fakulteti.containsKey(odabir))
                System.out.println("Loš izbor!");
            else
                break;
        }
        return fakulteti.get(odabir);
    }

    public static Profesor odaberiProfesora(Fakultet fakultet)
    {
        if (fakultet.dajListuProfesora().isEmpty())
        {
            System.out.println("Ne postoji nijedan profesor na " +fakultet.getNazivFakulteta() + "!");
            return null;
        }
        System.out.println("Molimo odaberite profesora: ");
        Scanner in = new Scanner(System.in);
        int odabir;
        int opcije = 1;
        while(true)
        {
            for(Profesor profa : fakultet.dajListuProfesora())
            {
                System.out.println(""+ opcije + " - " +profa.getIme() + " " + profa.getPrezime());
                opcije++;
            }
            odabir = in.nextInt();
            if ( odabir < 1 || odabir > fakultet.dajListuProfesora().size())
            {
                System.out.println("Neispravan izbor!");
                opcije = 1;
            }
            else break;
        }

        return fakultet.dajProfesora(odabir-1);
    }

    //Funkcije koje sluze za kreiranje objekata

    public static void kreirajFakultet()
    {
        System.out.println("Unesite naziv fakulteta");
        Scanner in = new Scanner(System.in);
        Fakultet noviFakultet = new Fakultet(in.nextLine());
        fakulteti.put(fakulteti.size()+1, noviFakultet);
        System.out.println("Uspjesno ste dodali fakultet!");
    }

    public static void kreirajStudenta() {
        Fakultet fakultet = odaberiFakultet();
        if (fakultet==null)
            return;
        Scanner in = new Scanner(System.in);
        System.out.println("Unesite ime studenta: ");
        String ime = in.nextLine();
        System.out.println("Unesite prezime studenta: ");
        String prezime = in.nextLine();
        System.out.println("Unesite broj indexa: ");
        String index = in.nextLine();
        System.out.println("Unesite ciklus na koji student trenutno pohađa: ");
        int ciklus = in.nextInt();
        System.out.println("Unesite semestar na koji student trenutno pohađa: ");
        int semestar = in.nextInt();

        Student noviStudent = new Student(ime, prezime, index, ciklus, semestar);

        noviStudent.upisiObaveznePredmete(fakultet.getSemestar(ciklus,semestar).getListaObaveznihPredmeta());

        List<Predmet> izborniPredmeti = fakultet.getSemestar(ciklus,semestar).getListaIzbornihPredmeta();
        boolean ponoviUnos = false;

        if (!izborniPredmeti.isEmpty())
            System.out.println("Molimo izaberite izborne predmete!");
        int odabir;
        while ((noviStudent.getZbirEECTSBodova() < 30 || ponoviUnos) && !izborniPredmeti.isEmpty())
        {
            System.out.println("Trenutno EECTS opterecenje semestra: " + noviStudent.getZbirEECTSBodova());
            int opcije = 1;

            for(Predmet predmet : izborniPredmeti)
            {
                System.out.println(""+ opcije + " - " + predmet.getNazivPredmeta());
                opcije++;
            }
            odabir = in.nextInt();
            if ( odabir < 1 || odabir > izborniPredmeti.size())
            {
                System.out.println("Neispravan izbor!");
                continue;
            }
            else
            {
                noviStudent.upisiIzborniPredmet(izborniPredmeti.get(odabir-1));
                izborniPredmeti.remove(odabir-1);
            }
            if (noviStudent.getZbirEECTSBodova() >= 30)
            {
                System.out.println("Da li zelite dodati novi predmet(true, false): ");
                ponoviUnos = in.nextBoolean();
            }
        }
        fakultet.dodajStudenta(noviStudent);
        System.out.println("Uspjesno ste dodali novog studenta!");
    }

    public static void kreirajProfesora()
    {
        Fakultet fakultet = odaberiFakultet();
        if (fakultet==null)
        {
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Unesite ime profesora: ");
        String imeProfesora = in.nextLine();
        System.out.println("Unesite prezime profesora: ");
        String prezimeProfesora = in.nextLine();
        Profesor noviProfesor = new Profesor(imeProfesora, prezimeProfesora);

        if (fakultet.dajListuProfesora().contains(noviProfesor)){
            System.out.println("Već ste dodali ovog profesora");
            return;
        }
        fakultet.dodajProfesora(noviProfesor);
        System.out.println("Uspjesno ste dodali novog profesora");
    }

    public static void kreirajCiklus()
    {
        Fakultet fakultet = odaberiFakultet();
        if (fakultet == null)
            return;
        Scanner in = new Scanner(System.in);
        System.out.println("Unesite redni broj ciklusa: ");
        int indexCiklusa = in.nextInt();
        if (fakultet.getCiklusi().containsKey(indexCiklusa))
            System.out.println("Već postoji ciklus s tim rednim brojem na "+fakultet.getNazivFakulteta()+ "!");
        fakultet.dodajCiklus(indexCiklusa);
        System.out.println("Uspjesno ste dodali novi ciklus");
    }

    public static void kreirajSemestar()
    {
        Fakultet fakultet = odaberiFakultet();
        if (fakultet == null)
            return;
        Scanner in = new Scanner(System.in);
        int indexCiklusa;
        while (true)
        {
            System.out.println("Unesite redni broj ciklusa kom pripada dati semestar: ");
            indexCiklusa = in.nextInt();
            if (!fakultet.getCiklusi().containsKey(indexCiklusa))
            {
                System.out.println("Ne postoji ciklus s tim rednim brojem!");
            }
            else break;
        }
        Ciklus ciklus = fakultet.getCiklusi().get(indexCiklusa);

        System.out.println("Unesite redni broj semestra: ");
        int indexSemestra = in.nextInt();
        if (ciklus.getSemestri().containsKey(indexSemestra))
            System.out.println("Već postoji semestar s tim rednim brojem na "+ciklus.getRedniBrojCiklusa()+ ". ciklusu!");

        Semestar noviSemestar = new Semestar(indexSemestra);

        boolean ponoviUnos = false;
        try{
            System.out.println("Pokrenuto dodavanje predmeta u semestar.");
            while (noviSemestar.getTrenutnoOpterecenje() < 30 || ponoviUnos)
            {
                System.out.println("Trenutno EECTS opterecenje semestra: " + noviSemestar.getTrenutnoOpterecenje());

                kreirajPredmet(fakultet, noviSemestar, indexCiklusa);

                if (noviSemestar.getTrenutnoOpterecenje() >= 30)
                {
                    System.out.println("Da li zelite dodati novi predmet(true, false): ");
                    ponoviUnos = in.nextBoolean();
                }
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Ne mozete kreirati semestar ako nemate unesene profesore!");
            return;
        }


        ciklus.dodajSemestar(noviSemestar);
        System.out.println("Uspjesno ste dodali novi semestar u " + ciklus.getRedniBrojCiklusa() + ". ciklus!");
    }

    public static void kreirajPredmet() {
        Fakultet fakultet = odaberiFakultet();
        if (fakultet==null)
            return;
        Profesor profesor = odaberiProfesora(fakultet);

        if (profesor == null)
            return;

        Scanner in = new Scanner(System.in);
        System.out.println("Unesite naziv predmeta: ");
        String nazivPredmeta = in.nextLine();
        System.out.println("Unesite broj EECTS bodova koje predmet nosi: ");
        int eects = in.nextInt();
        System.out.println("Unesite redni broj ciklusa predmeta");
        int ciklus = in.nextInt();
        System.out.println("Unesite redni broj semestra");
        int semestar = in.nextInt();
        System.out.println("Unesite broj časova po sedmici za ovaj predmet: ");
        int brojCasovaSedmicno = in.nextInt();
        System.out.println("Da li je predmet izborni (true, false): ");
        boolean daLiJeIzborni = in.nextBoolean();

        //Provjeriti postojanje semestra
        //Provjeriti postojanje ciklusa

        Predmet noviPredmet;
        if (daLiJeIzborni)
            noviPredmet = new IzborniPredmet(eects, nazivPredmeta, semestar, ciklus, profesor, brojCasovaSedmicno);
        else
            noviPredmet = new ObavezniPredmet(eects, nazivPredmeta, semestar, ciklus, profesor, brojCasovaSedmicno);
        if (fakultet.getSemestar(ciklus, semestar).getListaPredmeta().contains(noviPredmet))
        {
            System.out.println("Već ste unijeli ovaj predmet!");
            return;
        }
        fakultet.getSemestar(ciklus, semestar).dodajPredmetUSemestar(noviPredmet);
        System.out.println("Uspjesno ste dodali novi predmet!");
    }

    public static void kreirajPredmet(Fakultet fakultet, Semestar semestar, int ciklus) {
        Profesor profesor = odaberiProfesora(fakultet);
        if (profesor == null)
            throw new RuntimeException("Ne postoji nijedan profesor na fakultetu");
        Scanner in = new Scanner(System.in);
        System.out.println("Unesite naziv predmeta: ");
        String nazivPredmeta = in.nextLine();
        System.out.println("Unesite broj EECTS bodova koje predmet nosi: ");
        int eects = in.nextInt();
        System.out.println("Unesite broj časova po sedmici za ovaj predmet: ");
        int brojCasovaSedmicno = in.nextInt();
        System.out.println("Da li je predmet izborni (true, false): ");
        boolean daLiJeIzborni = in.nextBoolean();

        Predmet noviPredmet;
        if (daLiJeIzborni)
            noviPredmet = new IzborniPredmet(eects, nazivPredmeta, semestar.getRedniBrojSemestra(), ciklus, profesor, brojCasovaSedmicno);
        else
            noviPredmet = new ObavezniPredmet(eects, nazivPredmeta, semestar.getRedniBrojSemestra(), ciklus, profesor, brojCasovaSedmicno);
        if (semestar.getListaPredmeta().contains(noviPredmet))
        {
            System.out.println("Već ste unijeli ovaj predmet!");
            return;
        }
        semestar.dodajPredmetUSemestar(noviPredmet);
        System.out.println("Uspjesno ste dodali novi predmet!");
    }
}
