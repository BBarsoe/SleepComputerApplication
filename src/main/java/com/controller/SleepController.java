import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ResourceBundle;


public class SleepController implements Initializable {
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    public ChoiceBox<String> elevList;

    @FXML
    private TextField screen;

    //@Override
    //public void initilize(URL, url, ResourceBundle rb) {
    //   loadData();

    // }
    private void loadData() {
        list.removeAll(list);
        String a = "Alle";
        String b = "Anne";
        String c = "Allan";
        String d = "9";
        list.addAll(a, b, c, d);
        elevList.getItems().addAll(list);
    }

    @FXML
    private void displayValue(ActionEvent event) throws SQLException {
        String elev = elevList.getValue();

        if (elev == null) {
            screen.setText("Elev ikke valgt");
        } else {
            screen.setText("Du har valgt elev " + elev);
            DatabaseController.loadSleepModel(elev);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
}



