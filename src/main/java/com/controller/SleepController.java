import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.chart.*;
import javafx.scene.control.*;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;


import javax.print.attribute.standard.NumberUp;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


//import static java.time.OffsetDateTime.now;


public class SleepController implements Initializable {
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    public DatePicker startDatePicker;

    @FXML
    public LineChart<?, ?> LineChart;
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
        screen.setDisable(false);
        startDatePicker.setDisable(false);
        endDatePicker.setDisable(false);
        elevList.setDisable(false);
        displayValueBtn.setDisable(false);
    }

    @FXML
    private void handleSePopData() {
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
            ArrayList<String> sleep_time = new SleepModel().getSleep_time();
            ArrayList<String> sleep_awoke = new SleepModel().getAwoke_time();
            LineChart(sleep_time, sleep_awoke);
            //System.out.println(sleep_time);
            //System.out.println(sleep_awoke);

        } else {
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

    public void LineChart(ArrayList<String> sleep_time, ArrayList<String> awoke_time) {
        //XYChart.Series series = new XYChart.Series();
        ObservableList<XYChart.Data<String, Long>> data = FXCollections.<XYChart.Data<String, Long>>observableArrayList();

        y.setLabel("sovet(min)");
        x.setLabel("dag");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < sleep_time.size(); i++) {
            long[] diff = new long[sleep_time.size()];
            String awoke = awoke_time.get(i);
            String time = sleep_time.get(i);
            String[] splittime = time.split(" ");
            String[] splitawoke = awoke.split(" ");
            Time hours_bedTime = Time.valueOf((splittime[1]));
            Time hours_awokeTime = Time.valueOf(splitawoke[1]);
            String date = ((splittime[0]));

            diff[i] = (hours_awokeTime.getTime() - hours_bedTime.getTime());

            if (diff[i]>0) {
                System.out.println(diff[i]);
                data.add(new XYChart.Data<String,Long>(date,diff[i]));
                XYChart.Series series = new XYChart.Series(data);
                LineChart.getData().addAll(series);
        }else{

            }

        //System.out.println();
        }
    }
}



