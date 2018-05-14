import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.OffsetDateTime;
import java.util.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.SQLException;


//import static java.time.OffsetDateTime.now;
import static java.time.LocalDate.*;



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
    private void loadData() throws SQLException {
        list.removeAll(list);
        DatabaseController.loadStudentListModel();
        ArrayList<String> studentList = StudentListModel.StudentList_id;
        list.addAll(studentList);
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


    @FXML
    public DatePicker startDatePicker;

    @FXML
    public DatePicker endDatePicker;

    @FXML
    public Label fromLabel;

    @FXML
    public Label toLabel;

    public void ShowDate(ActionEvent event) {
        startDatePicker = new DatePicker();
        endDatePicker = new DatePicker();
        LocalDate today = LocalDate.of(2018, Month.MAY, 14);
        LocalDate localDate = today.minusWeeks(2);

       // LocalDate today = LocalDate.now();
       // LocalDate weeks = today.minusWeeks(1);

       // startDatePicker.setValue(startDatePicker.getValue(now()).minusWeeks(2));
        //endDatePicker.setValue(today);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



