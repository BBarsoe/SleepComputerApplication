import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.*;
import javafx.scene.control.*;


import javax.sound.sampled.Line;
import java.net.URL;
import java.sql.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;


//import static java.time.OffsetDateTime.now;


public class SleepController implements Initializable {
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    public DatePicker startDatePicker;

    @FXML
    public LineChart LineChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;


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
 //       screen.setDisable(false);
        startDatePicker.setDisable(false);
        endDatePicker.setDisable(false);
        elevList.setDisable(false);
        displayValueBtn.setDisable(false);
    }

    @FXML
    private void handleSePopData() {
//        screen.setDisable(false);
        startDatePicker.setDisable(false);
        endDatePicker.setDisable(false);
        displayValueBtn.setDisable(false);
        DatabaseController.loadPopSleepModel();
        ArrayList<String> sleep_time = new SleepModel().getSleep_time();
        ArrayList<String> sleep_awoke = new SleepModel().getAwoke_time();
        LineChart(sleep_time,sleep_awoke);
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
            ArrayList<String> sleep_time = new SleepModel().getSleep_time();
            ArrayList<String> sleep_awoke = new SleepModel().getAwoke_time();
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
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        screen.setDisable(true);
        startDatePicker.setDisable(true);
        endDatePicker.setDisable(true);
        elevList.setDisable(true);
        displayValueBtn.setDisable(true);

    }

    public void LineChart(ArrayList<String> sleep_time, ArrayList<String> awoke_time) {
        ObservableList<XYChart.Series> DataList = FXCollections.observableArrayList();
        XYChart.Series series = new XYChart.Series();


        //XYChart.Series series = new XYChart.Series<>(x,y);
         //ObservableList<XYChart.Data<String, Long>> data = FXCollections.<XYChart.Data<String, Long>>observableArrayList();

        y.setLabel("sovet(min)");
        x.setLabel("dag");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int[] diff = new int[sleep_time.size()];
        String[] date_array = new String[sleep_time.size()];

        for (int i = 0; i < sleep_time.size(); i++) {
            String awoke = awoke_time.get(i);
            String time = sleep_time.get(i);
            String[] splittime = time.split(" ");
            String[] splitawoke = awoke.split(" ");
            Time hours_bedTime = Time.valueOf((splittime[1]));
            Time hours_awokeTime = Time.valueOf(splitawoke[1]);
            date_array[i] = ((splittime[0]));

            diff[i] = ((int)hours_awokeTime.getTime() - (int)hours_bedTime.getTime());

            if (diff[i]>0) {
                series.getData().add(new XYChart.Data<String,Number>(""+date_array[i]+"",(Number) diff[i]));

        }else{

            }
        }
        DataList.addAll(series);
        LineChart.setData(DataList);
    }
}




