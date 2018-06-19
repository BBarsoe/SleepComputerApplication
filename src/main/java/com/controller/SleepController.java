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


    /**
     * Har til formål give mulighed for at kunne bruge datePicker start og end og listen over elever.
     */
    @FXML
    private void handleIndData() {
        startDatePicker.setDisable(false);
        endDatePicker.setDisable(false);
        listChooseStudent.setDisable(false);
        displayValueBtn.setDisable(false);
    }

    /**
     * Har til formål give mulighed for at kunne bruge datePicker start og end og listen over elever.
     * Udfylder linechartet med det data der findes i sleepModel.
     */
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

    /**
     * Har til formål at ændret view'et til hovedmenuen, ved at kalde mainklassens medetode goToMain(), hvis der trykkes på "tilbage"-knappen.
     */
    @FXML
    private void handlePrevReturnButton() {
        new MainController().goToMain();
    }

    /**
     * Har til formål at udfylde listen med elever det har givet samtykke.
     * Først udfyldes modellen studentListModel, og tilføjes dem til dropdown-menuen.
     */
    private void listChooseStudent() {
        list.removeAll(list);
        studentListModel.loadModel();
        ArrayList<String> studentList = StudentListModel.studentList_id;
        list.addAll(studentList);
        listChooseStudent.getItems().addAll(list);
    }

    /**
     * Har til formål at give lineChart data fra en individuel elev.
     * Først hentes den valgte elev med getValue(), og hvis der er valgt en elev, en start dato og slut dato, hentes data for den elev.
     * Det data, som er opvågningstidspunkter og sovetider tilføjes til lineChart'et.
     */
    @FXML
    private void handlePrevOKButton() {
        String elev = listChooseStudent.getValue();

        if ((elev != null) && (String.valueOf(startDatePicker) != null) && (String.valueOf(endDatePicker) != null)) {
            sleepModel.loadModel(elev);
            ArrayList<String> sleep_time = sleepModel.getSleep_time();
            ArrayList<String> sleep_awoke = sleepModel.getAwoke_time();
            LineChart(sleep_time, sleep_awoke);
        }
    }

    /**
     * Har til formål at udfylde listen emd elever og deaktivere startDatePicker, endDatePicker, listChooseStudent og displayValueBtn.
     * Så udfyldes listen med elever med metoden listChooseStudent().
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listChooseStudent();
        startDatePicker.setDisable(true);
        endDatePicker.setDisable(true);
        listChooseStudent.setDisable(true);
        displayValueBtn.setDisable(true);
    }

    /**
     * Har til formål at oprette et lineChart med data, angivet ved argumenterne sleep_time og awoke_time.
     * Der oprettes en liste med XYChart, navngivet dataList, x og y labes sættes.
     * Derefter laves en for løkke som for hvert element i sleep_time, beregner sovetiden mellem awoke_time og sleep_time.
     * Disse data tilføjes i listen dataList, og dataList tilføjes til lineChart
     */
    private void LineChart(ArrayList<String> sleep_time, ArrayList<String> awoke_time) {
        ObservableList<XYChart.Series> dataList = FXCollections.observableArrayList();
        XYChart.Series series = new XYChart.Series();

        y.setLabel("Søvnlængde (timer)");
        x.setLabel("Dag");
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
                if (date_array[i - 1].equals(date_array[i])) { // Tjekker om man er gået i seng to gange på et døgn, hvis det er sandt, lægger dem sammen.
                    diff[i] = diff[i] + diff[i - 1];

                } else if (diff[i-1] != 0) { // Tjekker om det er sovet, og hvis det er sandt, tilføjes data til series
                    float value = diff[i - 1];
                    value = value / 60;
                    series.getData().add(new XYChart.Data<String, Number>(date_array[i - 1], value ));
                }

                if (sleep_time.size() - 1 == i && diff[i] != 0) { // Hvis det er det sidste element i søvndata, og det ikke er nul, tilføjes data til series
                    float value = diff[i];
                    value = value / 60;
                    series.getData().add(new XYChart.Data<String, Number>(date_array[i], value));
                }
            }
        }
        dataList.addAll(series);
        PreviousSleep.setData(dataList);

    }
}







