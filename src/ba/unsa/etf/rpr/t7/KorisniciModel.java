package ba.unsa.etf.rpr.t7;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();
    private Connection konekcija;
    private PreparedStatement dajSveKorisnike, azurirajKorisnika, pretragaKorisnika, brisanjeKorisnika;

    public KorisniciModel() {
        String url = "jdbc:sqlite:korisnici.db";
        try {
            konekcija = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }
        try {
            dajSveKorisnike = konekcija.prepareStatement("SELECT * FROM korisnik");
        } catch (SQLException e) {
            izgradiBazu();
            try {
                dajSveKorisnike = konekcija.prepareStatement("SELECT * FROM korisnik");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            azurirajKorisnika = konekcija.prepareStatement("UPDATE korisnik SET ime=?, prezime=?, email=?, username=?, password=? WHERE id=?");
            pretragaKorisnika = konekcija.prepareStatement("SELECT k.id FROM korisnik k WHERE k.ime=? AND k.prezime=? AND k.username=?");
            brisanjeKorisnika = konekcija.prepareStatement("DELETE FROM korisnik WHERE korisnik.id = ?;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public Connection getKonekcija() {
        return konekcija;
    }

    private void izgradiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("korisnici.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.length() > 1 && sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = konekcija.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka… nastavljam sa praznom bazom");
        }
    }

    public void napuni() {
        korisnici.clear();
        try {
            ResultSet rez = dajSveKorisnike.executeQuery();
            ArrayList<Korisnik> sviKorisnici = new ArrayList<>();
            while (rez.next()) {
                korisnici.add(new Korisnik(rez.getInt(1), rez.getString(2), rez.getString(3), rez.getString(4), rez.getString(5), rez.getString(6)));
            }
            korisnici.addAll(sviKorisnici);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        trenutniKorisnik.set(null);
    }

    public void vratiNaDefault() {

        String query = "DROP TABLE IF EXISTS \"korisnik\";";
        Statement upit = null;
        try {
            upit = konekcija.createStatement();
            upit.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        izgradiBazu();
    }

    public void diskonektuj() {
        try {
            konekcija.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
        for (Korisnik korisnik : korisnici) {
            izmijeniKorisnika(korisnik);
        }
    }

    public void setTrenutniKorisnik(int i) {
        this.trenutniKorisnik.set(korisnici.get(i));
        for (Korisnik korisnik : korisnici) {
            izmijeniKorisnika(korisnik);
        }
    }

    public void izmijeniKorisnika(Korisnik korisnik) {

        try {
            azurirajKorisnika.setString(1, korisnik.getIme());
            azurirajKorisnika.setString(2, korisnik.getPrezime());
            azurirajKorisnika.setString(3, korisnik.getEmail());
            azurirajKorisnika.setString(4, korisnik.getUsername());
            azurirajKorisnika.setString(5, korisnik.getPassword());
            azurirajKorisnika.setInt(6, korisnik.getId());
            azurirajKorisnika.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private int pronadjiIdKorisnika(Korisnik korisnik) {
        try {
            pretragaKorisnika.setString(1, korisnik.getIme());
            pretragaKorisnika.setString(2, korisnik.getPrezime());
            pretragaKorisnika.setString(3, korisnik.getUsername());
            ResultSet rezultat = pretragaKorisnika.executeQuery();
            rezultat.next();
            return rezultat.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void obrisiKorisnika(Korisnik korisnik)
    {
        try {
            brisanjeKorisnika.setInt(1, korisnik.getId());
            brisanjeKorisnika.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        korisnici.clear();
        napuni();
    }

    public void zapisiDatoteku(File file) {
        if(file != null) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Korisnik korisnik: getKorisnici()){
                printWriter.println(kreirajPassFormat(korisnik));
            }
            printWriter.close();
        }
    }

    private String kreirajPassFormat(Korisnik korisnik) {
        String upis = "";
        upis+=korisnik.getUsername() + ":";
        upis+=korisnik.getPassword() + ":";
        upis+=korisnik.getId() + ":";
        upis+=korisnik.getId() + ":";
        upis+=korisnik.getIme() + " " + korisnik.getPrezime() + "::";
        return upis;
    }
}
