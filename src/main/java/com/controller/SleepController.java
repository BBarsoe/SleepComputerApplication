import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;


import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class SleepController implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList();
    private SleepModel sleepModel = new SleepModel();
    private StudentListModel studentListModel = new StudentListModel();
    @FXML
    private DatePicker startDatePicker;

    @FXML
    private LineChart PreviousSleep;
    @FXML
    private CategoryAxis x;
    @FXML

    private NumberAxis y;

    @FXML
    private Button displayValueBtn;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ChoiceBox<String> listChooseStudent;


    @FXML
    private void handleIndData() {
        startDatePicker.setDisable(false);
        endDatePicker.setDisable(false);
        listChooseStudent.setDisable(false);
        displayValueBtn.setDisable(false);
    }

    @FXML
    private void handlePopData() {
        startDatePicker.setDisable(false);
        endDatePicker.setDisable(false);
        displayValueBtn.setDisable(false);
        sleepModel.loadModel(null);
        ArrayList<String> sleep_time = sleepModel.getSleep_time();
        ArrayList<String> sleep_awoke = sleepModel.getAwoke_time();
        LineChart(sleep_time, sleep_awoke);
    }

    @FXML
    private void handlePrevReturnButton() {
        new MainController().goToMain();
    }

    private void listChooseStudent() throws SQLException {
        list.removeAll(list);
        studentListModel.loadModel();
        ArrayList<String> studentList = StudentListModel.studentList_id;
        list.addAll(studentList);
        listChooseStudent.getItems().addAll(list);
    }

    @FXML
    private void handlePrevOKButton(ActionEvent event) throws SQLException {
        String elev = listChooseStudent.getValue();

        if ((elev != null) && (String.valueOf(startDatePicker) != null) && (String.valueOf(endDatePicker) != null)) {
            sleepModel.loadModel(elev);
            ArrayList<String> sleep_time = sleepModel.getSleep_time();
            ArrayList<String> sleep_awoke = sleepModel.getAwoke_time();
            LineChart(sleep_time, sleep_awoke);
            //System.out.println(sleep_time);
            //System.out.println(sleep_awoke);

        } else {
//            screen.setText("Elev eller datointerval ikke valgt");
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
            listChooseStudent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        screen.setDisable(true);
        startDatePicker.setDisable(true);
        endDatePicker.setDisable(true);
        listChooseStudent.setDisable(true);
        displayValueBtn.setDisable(true);
    }

    private void LineChart(ArrayList<String> sleep_time, ArrayList<String> awoke_time) {
        ObservableList<XYChart.Series> DataList = FXCollections.observableArrayList();
        XYChart.Series series = new XYChart.Series();

        y.setLabel("sovet(min)");
        x.setLabel("dag");
        long[] diff = new long[sleep_time.size()];
        String[] date_array = new String[sleep_time.size()];

        for (int i = 0; i < sleep_time.size(); i++) {
            String awoke = awoke_time.get(i);
            String time = sleep_time.get(i);
            String[] splittime = time.split(" ");
            String[] splitawoke = awoke.split(" ");
            Time hours_bedTime = Time.valueOf((splittime[1]));
            Time hours_awokeTime = Time.valueOf(splitawoke[1]);
            date_array[i] = ((splittime[0]));

                diff[i] = TimeUnit.MILLISECONDS.toMinutes(hours_awokeTime.getTime() - hours_bedTime.getTime());
            if (diff[i] < 0)
                diff[i] = diff[i] + 1440; // 24 timer

            if (i > 0) {
                if (date_array[i - 1].equals(date_array[i])) {
                    diff[i] = diff[i] + diff[i - 1];

                } else if (diff[i-1] != 0) {
                    series.getData().add(new XYChart.Data<String, Number>("" + date_array[i - 1] + "", (Number) diff[i - 1]));
                }

                if (sleep_time.size() - 1 == i && diff[i] != 0) {
                    series.getData().add(new XYChart.Data<String, Number>("" + date_array[i] + "", (Number) diff[i]));
                }
            }
        }
        DataList.addAll(series);
        PreviousSleep.setData(DataList);

    }
}







