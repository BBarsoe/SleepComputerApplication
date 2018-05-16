import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import sun.tools.jar.Main;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class MeetingController implements Initializable {
    ObservableList<String> meetingList = FXCollections.observableArrayList();
    ObservableList<String> arranged_meetingList = FXCollections.observableArrayList();

    int potentialMeetings = 10;

    @FXML
    public ChoiceBox<String> elevList;
    public ChoiceBox meetingViewer;
    public DatePicker Datepicker;

    private void handlePotentialMeeting(){
       DatabaseController.loadMeetingModel();
        ArrayList<String> studentList = MeetingModel.participatingStudent_id;
        ArrayList<Date> meeting_time = MeetingModel.getMeetingTime();
        String[] user_id = MeetingModel.getParticipatingCoordinator().toArray(new String[0]);
        for (int i=0; i<studentList.size(); i++) {
            int readyForMeeting = Integer.parseInt(user_id[i]);
            if (readyForMeeting != 0) {
                System.out.println(studentList.get(i));
                arranged_meetingList.removeAll(arranged_meetingList);
                arranged_meetingList.addAll(studentList.get(i));
                arranged_meetingList.addAll(String.valueOf(meeting_time.get(i)));
                meetingViewer.getItems().addAll(arranged_meetingList);

            } else {
            }


        }
        }




    private void showMeetings() throws SQLException {
        DatabaseController.loadMeetingModel();
        String[] studentList = MeetingModel.participatingStudent_id.toArray(new String[0]);
        String[] user_id = MeetingModel.getParticipatingCoordinator().toArray(new String[0]);
        for (int i = 0; i < studentList.length; i++) {
            int readyForMeeting = Integer.parseInt(user_id[i]);
            //System.out.println(user_id[i]);
            if ( readyForMeeting == 0 ){
                meetingList.removeAll(meetingList);
                meetingList.addAll(studentList);
                elevList.getItems().addAll(meetingList);
            }else {
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
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
        if ((elevList.getValue()!= null) && (Datepicker.getValue() != null)) {
            String student_id = elevList.getValue();
            LocalDate date = Datepicker.getValue();
            String user_id = new UserModel().getUser_id();
            DatabaseController.updateMeetingModel(student_id,user_id,date);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Du har ikke valgt elev og/eller dato");
            alert.show();

            }
        }


    }

