import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MeetingController implements Initializable {
    ObservableList<String> meetingList = FXCollections.observableArrayList();

    int potentialMeetings = 10;

    @FXML
    public ChoiceBox<String> elevList;
    public ChoiceBox<String> meetingViewer;




    private void showMeetings() throws SQLException {
        meetingList.removeAll(meetingList);
        DatabaseController.loadMeetingModel();
        ArrayList<String> studentList = MeetingModel.participatingStudent_id;
        meetingList.addAll(studentList);
        elevList.getItems().addAll(meetingList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showMeetings();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
