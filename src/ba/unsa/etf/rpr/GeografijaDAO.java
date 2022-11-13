package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance;
    private Connection konekcija;
    private PreparedStatement upitGradovi, glavniGradUpit, dajDrzavuUpit, obrisiDrzavuUpit, obrisiGradoveZaDrzavu, nadjiDrzavuUpit, dajSveGradoveUpit,
            dodajGradUpit, dodajDrzavuUpit, odrediIdGradUpit, odrediIdDrzavaUpit, promijeniGradUpit, dajGradUpit;
    public static GeografijaDAO getInstance()
    {
        if (instance == null)
            instance = new GeografijaDAO();
        return instance;
    }

    public static void removeInstance(){
        if(instance==null) return;
        instance.close();
        instance=null;
    }

    public void close(){
        try{
            konekcija.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private GeografijaDAO(){
        try {
            konekcija = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Statement foreignKeyConstraint = konekcija.createStatement();
            foreignKeyConstraint.execute("PRAGMA foreign_keys = OFF");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            upitGradovi = konekcija.prepareStatement("SELECT g.id \"gradID\", \n" +
                    "d.id \"drzavaID\", \n" +
                    "g.naziv \"gradNaziv\", \n" +
                    "d.naziv \"drzavaNaziv\", \n" +
                    "g.broj_stanovnika \"brojStanovnika\", \n" +
                    "glavni.id \"glavniGradID\", \n" +
                    "glavni.naziv \"glavniNaziv\", \n" +
                    "glavni.broj_stanovnika \"glavniBrStanovnika\"\n" +
                    "FROM grad g, drzava d, grad glavni\n" +
                    "WHERE g.drzava = d.id AND glavni.id = d.glavni_grad\n" +
                    "ORDER BY g.naziv DESC;");
        } catch (SQLException throwables) {
            regenerisiBazu();
            try {
                upitGradovi = konekcija.prepareStatement("SELECT g.id \"gradID\", \n" +
                        "d.id \"drzavaID\", \n" +
                        "g.naziv \"gradNaziv\", \n" +
                        "d.naziv \"drzavaNaziv\", \n" +
                        "g.broj_stanovnika \"brojStanovnika\", \n" +
                        "glavni.id \"glavniGradID\", \n" +
                        "glavni.naziv \"glavniNaziv\", \n" +
                        "glavni.broj_stanovnika \"glavniBrStanovnika\"\n" +
                        "FROM grad g, drzava d, grad glavni\n" +
                        "WHERE g.drzava = d.id AND glavni.id = d.glavni_grad\n" +
                        "ORDER BY g.naziv DESC;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            dajDrzavuUpit = konekcija.prepareStatement("SELECT * FROM DRZAVA WHERE id=?");
            dajGradUpit = konekcija.prepareStatement("SELECT * FROM grad WHERE id=?");
            obrisiDrzavuUpit = konekcija.prepareStatement("DELETE FROM drzava WHERE naziv=?");
            obrisiGradoveZaDrzavu = konekcija.prepareStatement("DELETE FROM grad WHERE drzava=?");
            nadjiDrzavuUpit=konekcija.prepareStatement("SELECT * FROM drzava WHERE naziv=?");
            dajSveGradoveUpit=konekcija.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
            dodajGradUpit=konekcija.prepareStatement("INSERT INTO grad VALUES(?,?,?,?)");
            odrediIdGradUpit=konekcija.prepareStatement("SELECT Max(id)+1 FROM grad");
            dodajDrzavuUpit=konekcija.prepareStatement("INSERT INTO drzava VALUES(?,?,?)");
            odrediIdDrzavaUpit=konekcija.prepareStatement("SELECT Max(id)+1 FROM drzava");
            promijeniGradUpit=konekcija.prepareStatement("UPDATE grad SET naziv=?, broj_stanovnika=?, drzava=? WHERE id=?");
            glavniGradUpit=konekcija.prepareStatement("SELECT grad.id, grad.naziv, grad.broj_stanovnika, grad.drzava FROM grad, drzava WHERE grad.drzava=drzava.id AND drzava.naziv=?");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement foreignKeyConstraint = konekcija.createStatement();
            foreignKeyConstraint.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void regenerisiBazu() {

        try {
            Scanner ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext())
            {
                String line = ulaz.nextLine();
                sqlUpit+=line;
                if (sqlUpit.contains(";")) {
                    try {
                        Statement kveri = konekcija.createStatement();
                        kveri.execute(sqlUpit);
                        sqlUpit="";
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Grad glavniGrad(String drzava){
        try {
            glavniGradUpit.setString(1,drzava);
            ResultSet rs = glavniGradUpit.executeQuery();
            if(!rs.next()) return null;
            return dajGradIzResultSeta(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Grad dajGradIzResultSeta(ResultSet rs) throws SQLException {
        Grad grad = new Grad(rs.getInt(1), rs.getString(2), rs.getInt(3), null);
        grad.setDrzava(dajDrzavu(rs.getInt(4), grad));
        return grad;
    }

    private Drzava dajDrzavu(int id, Grad grad) {
        try {
            dajDrzavuUpit.setInt(1,id); //prepared statement smo vec u konstruktoru DAO definisali, ovdje ga fillujemo podacima
            ResultSet rs = dajDrzavuUpit.executeQuery();
            if(!rs.next()) return null; //ne postoji drzava sa tim id-om
            return dajDrzavuIzResultSeta(rs,grad);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Grad dajGrad(int id) {
        try {
            dajGradUpit.setInt(1,id); //prepared statement smo vec u konstruktoru DAO definisali, ovdje ga fillujemo podacima
            ResultSet rs = dajGradUpit.executeQuery();
            if(!rs.next()) return null; //ne postoji grad sa tim id-om
            return dajGradIzResultSeta(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Drzava dajDrzavuIzResultSeta(ResultSet rs, Grad grad) throws SQLException {
        return new Drzava(rs.getInt(1), rs.getString(2), grad);  //grad moramo vući kako bi formirali državu
    }

    public void obrisiDrzavu(String nazivDrzava){
        try {
            nadjiDrzavuUpit.setString(1,nazivDrzava);
            ResultSet rs=nadjiDrzavuUpit.executeQuery();
            if(!rs.next()) return;
            Drzava drzava=dajDrzavuIzResultSeta(rs,null); //nadjemo drzavu sa tim nazivom, odnosno njen id
            obrisiGradoveZaDrzavu.setInt(1,drzava.getId()); //iz tabele grad uklonimo grad koji ima id drzave kao i nadjena drzava
            obrisiGradoveZaDrzavu.executeUpdate();
            obrisiDrzavuUpit.setInt(1, drzava.getId());
            obrisiDrzavuUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> rezultat = new ArrayList<>();
        try {
            ResultSet rs=dajSveGradoveUpit.executeQuery(); //upit vraca set gradova u descendingu
            while(rs.next())
            {
                Grad grad = dajGradIzResultSeta(rs);
                rezultat.add(grad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    public void dodajGrad(Grad grad) {
        try {
            ResultSet rs=odrediIdGradUpit.executeQuery();
            int id=1;
            if(rs.next()) id=rs.getInt(1);
            dodajGradUpit.setInt(1,id);
            dodajGradUpit.setString(2,grad.getNaziv());
            dodajGradUpit.setInt(3, grad.getBrojStanovnika());
            dodajGradUpit.setInt(4, grad.getDrzava().getId());
            dodajGradUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void dodajDrzavu(Drzava drzava){
        try {
            ResultSet rs=odrediIdDrzavaUpit.executeQuery();
            int id=1;
            if(rs.next()) id=rs.getInt(1);
            dodajDrzavuUpit.setInt(1,id+1);
            dodajDrzavuUpit.setString(2,drzava.getNaziv());
            dodajDrzavuUpit.setInt(3, drzava.getGlavniGrad().getId());
            dodajDrzavuUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad){
        try {
            promijeniGradUpit.setString(1,grad.getNaziv());
            promijeniGradUpit.setInt(2,grad.getBrojStanovnika());
            promijeniGradUpit.setInt(3, grad.getDrzava().getId());
            promijeniGradUpit.setInt(4, grad.getId());
            promijeniGradUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String nazivDrzava) {
        try {
            nadjiDrzavuUpit.setString(1, nazivDrzava);
            ResultSet rs = nadjiDrzavuUpit.executeQuery();
            if(!rs.next()) return null; //nema drzave
            return dajDrzavuIzResultSeta(rs,dajGrad(rs.getInt(3)));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
