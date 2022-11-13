package ba.unsa.etf.rpr.tutorijal5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fakultet {
    private Map<Integer, Semestar> semestri;
    private Map<Integer, Student> studenti;
    Fakultet(){
        semestri = new HashMap<Integer, Semestar>();
        studenti = new HashMap<Integer, Student>();
    }

    public Semestar getSemestar(int redniBrojSemestra)
    {
        return semestri.get(redniBrojSemestra);
    }
    public ArrayList<Predmet> dajIzborneBezStudenata()
    {
        HashMap<Predmet, Boolean> predmeti = new HashMap<Predmet, Boolean>();
        for (Semestar semestar : semestri.values())
        {
            for (Predmet predmet :semestar.dajListuPredmeta())
            {
                predmeti.put(predmet, false);
            }
        }

        for (Student student : studenti.values()) {
            for (Predmet predmet : student.dajListuPredmeta()) {
                predmeti.remove(predmet);
            }
        }

        return (ArrayList<Predmet>) predmeti.keySet();
    }
}
