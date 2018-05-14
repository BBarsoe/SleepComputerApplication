import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.chart.*;
import javafx.scene.control.*;


import java.net.URL;
import java.time.LocalDate;
import java.time.Month;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


//import static java.time.OffsetDateTime.now;


public class SleepController implements Initializable {
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    public DatePicker startDatePicker;

    @FXML
    public LineChart LinechartSleep;

    @FXML
    public Button displayValueBtn;

    @FXML
    public DatePicker endDatePicker;

    @FXML
    public Label fromLabel;

    @FXML
    public Label toLabel;
    @FXML
    public ChoiceBox<String> elevList;

    @FXML
    private TextField screen;

    @FXML
    private BarChart sleepData;

    //@Override
    //public void initilize(URL, url, ResourceBundle rb) {
    //   loadData();

    // }
    @FXML
    private void handleSeIndData() {
        screen.setDisable(false);
        startDatePicker.setDisable(false);
        endDatePicker.setDisable(false);
        elevList.setDisable(false);
        displayValueBtn.setDisable(false);
    }
    @FXML
    private void handleSePopData(){
        screen.setDisable(false);
        startDatePicker.setDisable(false);
        endDatePicker.setDisable(false);
        displayValueBtn.setDisable(false);
    }

    @FXML
    private void handleReturn() {
        new MainController().goToMainView();
    }

    private void loadData() throws SQLException {
        list.removeAll(list);
        DatabaseController.loadStudentListModel();
        ArrayList<String> studentList = StudentListModel.studentList_id;
        list.addAll(studentList);
        elevList.getItems().addAll(list);
    }

    @FXML
    private void displayValue(ActionEvent event) throws SQLException {
        String elev = elevList.getValue();

        if ((elev != null) && (String.valueOf(startDatePicker) != null) && (String.valueOf(endDatePicker) != null)) {
             DatabaseController.loadSleepModel(elev);
            String sleep_time = new SleepModel().getSleep_time();
            String sleep_awoke = new SleepModel().getAwoke_time();
            System.out.println(sleep_time);
            System.out.println(sleep_awoke);

        }else{
            screen.setText("Elev eller datointerval ikke valgt");
        }
    }


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
        screen.setDisable(true);
        startDatePicker.setDisable(true);
        endDatePicker.setDisable(true);
        elevList.setDisable(true);
        displayValueBtn.setDisable(true);

    }
}



