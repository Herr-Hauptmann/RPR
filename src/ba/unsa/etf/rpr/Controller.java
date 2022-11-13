package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller implements Initializable {
    protected static ObservableList<String> studenti = FXCollections.observableArrayList();
    public TextField fldText;
    public Slider sliderStudents;
    public ListView lvStudents;
    public ChoiceBox choiceColor;
    public Button button0;
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;
    public Button btnUnos;
    ArrayList<Button> dugmad = new ArrayList<>();
    private void dodajDugmad()
    {
        dugmad.add(button0);
        dugmad.add(button1);
        dugmad.add(button2);
        dugmad.add(button3);
        dugmad.add(button4);
        dugmad.add(button5);
        dugmad.add(button6);
        dugmad.add(button7);
        dugmad.add(button8);
        dugmad.add(button9);
    }

    private void popuniListu()
    {
        studenti.clear();
        for (int i = 1; i <= (int)sliderStudents.getValue(); i++)
        {
            if (i == 15)
            {
                studenti.add("Student"+fldText.getText());
                break;
            }
            studenti.add("Student"+i);
        }
    }

    private void promijeniUPlavu()
    {
        dugmad.forEach((dugme)->dugme.getStyleClass().removeAll("buttonRed", "buttonGreen"));
        dugmad.forEach((dugme) -> dugme.getStyleClass().add("buttonBlue"));
    }

    private void promijeniUZelenu()
    {
        dugmad.forEach((dugme)->dugme.getStyleClass().removeAll("buttonRed", "buttonBlue"));
        dugmad.forEach((dugme) -> dugme.getStyleClass().add("buttonGreen"));
    }

    private void promijeniUCrvenu()
    {
        dugmad.forEach((dugme)->dugme.getStyleClass().removeAll("buttonGreen", "buttonBlue"));
        dugmad.forEach((dugme) -> dugme.getStyleClass().add("buttonRed"));
    }

    private void promijeniUDefault()
    {
        dugmad.forEach((dugme)->dugme.getStyleClass().removeAll("buttonRed", "buttonGreen","buttonBlue"));
    }

    private void popuniDropMenu()
    {
        ObservableList<String> opcije = FXCollections.observableArrayList();
        opcije.add("Default");
        opcije.add("Crvena");
        opcije.add("Zelena");
        opcije.add("Plava");
        choiceColor.setItems(opcije);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUnos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Controller.this.napraviNoviProzor();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        lvStudents.setItems(studenti);
        popuniListu();
        popuniDropMenu();
        dodajDugmad();
        sliderStudents.valueProperty().addListener((observableValue, number, t1) -> {
            popuniListu();
        });

        choiceColor.valueProperty().addListener((observableValue, o, t1) -> {
            if (t1.toString().equals("Zelena"))
            {
                promijeniUZelenu();
            }
            if (t1.toString().equals("Crvena"))
            {
                promijeniUCrvenu();
            }
            if (t1.toString().equals("Plava"))
            {
                promijeniUPlavu();
            }
            if (t1.toString().equals("Default"))
            {
                promijeniUDefault();
            }
        });

        fldText.setText("");
    }

    void napraviNoviProzor() throws IOException {
        Stage unosUcenika = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/novi.fxml"));
        unosUcenika.setTitle("Unos ucenika");
        unosUcenika.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        unosUcenika.show();
    }

    public void button1Clicked(){
        String staro = fldText.getText();
        staro+="1";
        fldText.setText(staro);
    }
    public void button2Clicked(){
        String staro = fldText.getText();
        staro+="2";
        fldText.setText(staro);
    }
    public void button3Clicked(){
        String staro = fldText.getText();
        staro+="3";
        fldText.setText(staro);
    }
    public void button4Clicked(){
        String staro = fldText.getText();
        staro+="4";
        fldText.setText(staro);
    }
    public void button5Clicked(){
        String staro = fldText.getText();
        staro+="5";
        fldText.setText(staro);
    }
    public void button6Clicked(){
        String staro = fldText.getText();
        staro+="6";
        fldText.setText(staro);
    }
    public void button7Clicked(){
        String staro = fldText.getText();
        staro+="7";
        fldText.setText(staro);
    }
    public void button8Clicked(){
        String staro = fldText.getText();
        staro+="8";
        fldText.setText(staro);
    }
    public void button9Clicked(){
        String staro = fldText.getText();
        staro+="9";
        fldText.setText(staro);
    }
    public void button0Clicked(){
        String staro = fldText.getText();
        staro+="0";
        fldText.setText(staro);
    }
}
