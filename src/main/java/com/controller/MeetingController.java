import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MeetingController implements Initializable {
    private MeetingModel meetingModel = new MeetingModel();
    private UserModel userModel = new UserModel();
    private ObservableList<String> meetingList = FXCollections.observableArrayList();
    private ObservableList<String> arranged_meetingList = FXCollections.observableArrayList();

    @FXML
    public ChoiceBox<String> elevList;
    public DatePicker selectMeetingTime;
    public ListView potentialMeeting;


    private void handleShowMeetings() {
        meetingModel.loadModel();
        ArrayList<String> studentList = meetingModel.getParticipatingStudent_id();
        ArrayList<Date> meeting_time = meetingModel.getMeetingTime();
        String[] user_id = meetingModel.getParticipatingCoordinator().toArray(new String[0]);
        ArrayList<String> meeting_location = meetingModel.getMeetingLocation();

        for (int i = 0; i < studentList.size(); i++) {
            int readyForMeeting = Integer.parseInt(user_id[i]);
            if (readyForMeeting != 0) {
                arranged_meetingList.add("Elev: "+studentList.get(i)+" møde dato: "+ meeting_time.get(i)+ " lokation: "+meeting_location.get(i));

            } else {
            }


        }
    }

   private LocalDate handleSelectMeetingTimeButton() {
        LocalDate date = selectMeetingTime.getValue();
        return date;
   }

    private void handlePotentialMeeting() throws SQLException {
        meetingModel.loadModel();
        ArrayList<String> studentList = meetingModel.getParticipatingStudent_id();
        String[] user_id = meetingModel.getParticipatingCoordinator().toArray(new String[0]);
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
            handleShowMeetings();
            handlePotentialMeeting();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleReturn(){
        new MainController().goToMain();
    }

    public void handleRecommendMeetingButton(){
        for (int i=0; i < meetingModel.getParticipatingStudent_id().size(); i++) {
            if (meetingModel.getParticipatingStudent_id().get(i).equals(elevList.getValue())){

                if ((elevList.getValue()!= null) && (selectMeetingTime.getValue() != null)) {
                    String student_id = elevList.getValue();
                    LocalDate date = handleSelectMeetingTimeButton();
                    String user_id = userModel.getUser_id();
                    String meeting_location = meetingModel.getMeetingLocation().get(i);
                    meetingModel.updateModel(student_id,user_id,date);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Der er arrangeret møde med elev: " +student_id+ " denne dato: "+date+ " dette sted: "+meeting_location);
                    alert.show();
                    new MainController().goToMain();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Du har ikke valgt elev og/eller dato");
                    alert.show();

                }
            }
            }
        }

}

