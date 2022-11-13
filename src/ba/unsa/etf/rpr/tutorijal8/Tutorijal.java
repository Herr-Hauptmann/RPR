package ba.unsa.etf.rpr.tutorijal8;

import com.github.tsijercic1.xml.common.InvalidXMLException;
import com.github.tsijercic1.xml.common.Node;
import com.github.tsijercic1.xml.parser.XMLParser;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Tutorijal {

    public static void main(String[] args) {
        ArrayList<Grad> gradovi = ucitajGradove();
        UN un = ucitajXml(gradovi);
        zapisiXml(un);
    }

    public static ArrayList<Grad> ucitajGradove() {
        ArrayList<Grad> gradovi = new ArrayList<>();
        try {
            File mjerenja = new File("mjerenja.txt");
            Scanner ulaz = new Scanner(mjerenja);
            int brojac = 0;
            while (ulaz.hasNextLine() && brojac < 1000) {
                String ucitaniGrad = ulaz.nextLine();
                String[] informacije = ucitaniGrad.split(",");
                Grad noviGrad = new Grad();
                noviGrad.setNaziv(informacije[0]);
                ArrayList<Double> temperature = new ArrayList<>();
                for (int i = 1; i < informacije.length; i++) {
                    temperature.add(Double.parseDouble(informacije[i]));
                }
                noviGrad.setTemperature(temperature.stream().mapToDouble(Double::doubleValue).toArray());
                gradovi.add(noviGrad);
                brojac++;
            }
            ulaz.close();

        } catch (FileNotFoundException e) {
            System.out.println("Nije pronađen fajl");
            e.printStackTrace();
        }
        return gradovi;
    }

    public static UN ucitajXml(ArrayList<Grad> gradovi) {
        UN ujedinjeneNacije = new UN();

        try {
            XMLParser xml = new XMLParser("drzave.xml");
            Node drzaveNode = xml.getDocumentRootNode();
            ArrayList<Node> drzave = drzaveNode.getChildNodes();
            HashMap<String, String> podaci = new HashMap<>();
            for (Node drzava : drzave) {
                podaci.put("drzavaStanovnika", drzava.getAttributeValues().stream().findFirst().get());
                ArrayList<Node> informacijeODrzavi = drzava.getChildNodes();
                informacijeODrzavi.forEach((node -> {
                    podaci.putAll(node.getAttributes());
                    if (node.getName().equalsIgnoreCase("Povrsina"))
                        podaci.put("povrsina", node.getContent());
                    if (node.getName().equalsIgnoreCase("glavnigrad")) {
                        podaci.put("glGrad", node.getChildNode("naziv").getContent());
                    }
                    if (node.getName().equalsIgnoreCase("naziv"))
                    {
                        podaci.put("nazivDrzave", node.getContent());
                    }
                }));

                Grad glavni = new Grad();
                glavni.setNaziv(podaci.get("glGrad"));
                if(gradovi.contains(glavni))
                {
                    glavni = gradovi.get(gradovi.indexOf(glavni));
                }
                glavni.setBrojStanovnika(Integer.parseInt(podaci.get("stanovnika")));

                Drzava novaDrzava = new Drzava();
                novaDrzava.setBrojStanovnika(Integer.parseInt(podaci.get("drzavaStanovnika")));
                novaDrzava.setNaziv(podaci.get("nazivDrzave"));
                novaDrzava.setGlavniGrad(glavni);
                novaDrzava.setPovrsina(Double.parseDouble(podaci.get("povrsina")));
                novaDrzava.setJedinica(podaci.get("jedinica"));
                ujedinjeneNacije.dodajDrzavu(novaDrzava);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidXMLException e) {
            e.printStackTrace();
        }
        return ujedinjeneNacije;
    }

    public static void zapisiXml(UN un){
        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("un.xml")));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File un.xml");
        }
        encoder.writeObject(un);
        encoder.close();
    }
}
