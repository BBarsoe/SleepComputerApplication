import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class MeetingController implements Initializable {
    MeetingModel meetingModel = new MeetingModel();
    ObservableList<String> meetingList = FXCollections.observableArrayList();
    ObservableList<String> arranged_meetingList = FXCollections.observableArrayList();

    @FXML
    public ChoiceBox<String> elevList;
    public DatePicker selectMeetingTime;
    public ListView potentialMeeting;

    private void handlePotentialMeeting() {
        meetingModel.load();
        ArrayList<String> studentList = MeetingModel.participatingStudent_id;
        ArrayList<Date> meeting_time = MeetingModel.getMeetingTime();
        String[] user_id = MeetingModel.getParticipatingCoordinator().toArray(new String[0]);
        for (int i = 0; i < studentList.size(); i++) {
            int readyForMeeting = Integer.parseInt(user_id[i]);
            if (readyForMeeting != 0) {
                arranged_meetingList.add("Elev: "+studentList.get(i)+" møde dato: "+ meeting_time.get(i));
                //arranged_meetingList.addAll(String.valueOf(meeting_time.get(i)));
                ///meetingViewer.getItems().addAll(arranged_meetingList);

            } else {
            }


        }
    }


    private void showMeetings() throws SQLException {
        meetingModel.load();
        ArrayList<String> studentList = MeetingModel.participatingStudent_id;
        String[] user_id = MeetingModel.getParticipatingCoordinator().toArray(new String[0]);
        for (int i = 0; i < studentList.size(); i++) {
            int readyForMeeting = Integer.parseInt(user_id[i]);
            if ( readyForMeeting == 0 ){
                meetingList.removeAll(meetingList);
                meetingList.addAll(studentList.get(i));
                elevList.getItems().addAll(meetingList);
            }else {

            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            potentialMeeting.setItems(arranged_meetingList);
            showMeetings();
            handlePotentialMeeting();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleReturn(){
        new MainController().goToMainView();
    }

    public void handleRecommendMeetingButton(){
        if ((elevList.getValue()!= null) && (selectMeetingTime.getValue() != null)) {
            String student_id = elevList.getValue();
            LocalDate date = selectMeetingTime.getValue();
            String user_id = new UserModel().getUser_id();
            meetingModel.update(student_id,user_id,date);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Der er arrangeret møde med elev: " +student_id+ " denne dato: "+date);
            alert.show();
            new MainController().goToMainView();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Du har ikke valgt elev og/eller dato");
            alert.show();

            }
        }


    }

