import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import javafx.scene.control.TextField;
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
        list.addAll(a, b, c);
        elevList.getItems().addAll(list);
    }

    @FXML
    private void displayValue(ActionEvent event) {
        String elev = elevList.getValue();

        if (elev == null) {
            screen.setText("Elev ikke valgt");
        } else {
            screen.setText("Du har valgt elev" + elev);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
}



