package ba.unsa.etf.rpr;

import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NoviController {
    public TextField fldIme;
    public ProgressBar progressBar;

    public void izracunajProgress()
    {
        double daljina = (double)fldIme.getText().length()/10;
        if (daljina < 1)
        {
            progressBar.getStyleClass().removeAll("zeleniProgress");
            progressBar.getStyleClass().add("crveniProgress");
        }
        else
        {
            progressBar.getStyleClass().removeAll("crveniProgress");
            progressBar.getStyleClass().add("zeleniProgress");
        }

        progressBar.setProgress(daljina);
    }


    public void buttonOkClicked()
    {
        if (fldIme.getText().length() < 10)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neispravno ime");
            alert.setHeaderText("Neispravno ime studenta");
            alert.setContentText("Ime studenta treba biti najmanje 10 karaktera dugačko");
            alert.showAndWait();
            return;
        }
        Controller.studenti.add(fldIme.getText());
        Stage prozor = (Stage)progressBar.getScene().getWindow();
        prozor.close();
    }

    public void buttonCancelClicked()
    {
        Stage prozor = (Stage)progressBar.getScene().getWindow();
        prozor.close();
    }

}
