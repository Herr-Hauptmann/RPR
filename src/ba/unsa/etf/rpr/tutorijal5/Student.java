package ba.unsa.etf.rpr.tutorijal5;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Fakultet fakultet;
    private ArrayList<Predmet> predmeti;

    Student(int redniBrojSemestra)
    {
        predmeti = new ArrayList<Predmet>();
    }
    public ArrayList<IzborniPredmet> upisiSemestar(int redniBrojSemestra)
    {
        ArrayList<IzborniPredmet> izborni = new ArrayList<IzborniPredmet>();
        for (Predmet predmet : fakultet.getSemestar(redniBrojSemestra).dajListuPredmeta())
        {
            if (predmet instanceof ObavezniPredmet)
                predmeti.add(predmet);
            else izborni.add((IzborniPredmet) predmet);
        }
        return izborni;
    }

    public int dajZbirECTS(List<IzborniPredmet> predmeti)
    {
        int zbir = 0;
        for (Predmet predmet : predmeti)
        {
            zbir += predmet.dajECTS();
        }
        return zbir;
    }

    public int dajZbirECTS()
    {
        int zbir = 0;
        for (Predmet predmet : predmeti)
        {
            zbir += predmet.dajECTS();
        }
        return zbir;
    }

    public void upisiIzbornePredmete(List<IzborniPredmet> izborniPredmeti) throws InstantiationException {
        if (dajZbirECTS(izborniPredmeti)+dajZbirECTS() < 30)
            throw new InstantiationException("Nedovoljan broj ECTS bodova u zbiru");
        for(Predmet predmet : izborniPredmeti)
            predmeti.add(predmet);
    }

    public List<Predmet> dajListuPredmeta()
    {
        return predmeti;
    }
}
