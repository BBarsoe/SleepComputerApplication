import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MeetingController implements Initializable {
    private static Stage primaryStage;
    private MeetingModel meetingModel = new MeetingModel();
    private UserModel userModel = new UserModel();
    private ObservableList<String> meetingList = FXCollections.observableArrayList();
    private ObservableList<String> arranged_meetingList = FXCollections.observableArrayList();

    @FXML
    public ChoiceBox<String> elevList;
    public DatePicker selectMeetingTime;
    public ListView potentialMeeting;

    /**
     * Metoden handleShowMeetings() starter med at kalde loadModel() fra klassen MeetingModel.
     * Herefter defineres fire ArrayLists, ved studentList sættes data ind fra MeetingModel med metoden getParticipatingStudent_id.
     * Dette gøres også ved de andre ArrayLists med getMeetingTime, getParticipatingCoordinator og getMeetingLocation.
     * Herefter benyttes en for løkke, så kører igennem for antalet elever der ønsker møde.
     * I for løkken er der en if løkke, hvor den er sand, hvis user_id ikke er lig med 0, hvilket betyder der er tildelt
     * en sundhedskoordinator. Hvis dette er sandt, benyttes add til arranged_meetingList, som tilføjer eleven med student_id
     * møde tidspunktet meeting_time og lokationen meeting_location.
     */
    private void handleShowMeetings() {
        meetingModel.loadModel();
        ArrayList<String> studentList = meetingModel.getParticipatingStudent_id();
        ArrayList<Date> meeting_time = meetingModel.getMeetingTime();
        ArrayList<String> user_id = meetingModel.getParticipatingCoordinator();
        ArrayList<String> meeting_location = meetingModel.getMeetingLocation();

        for (int i = 0; i < studentList.size(); i++) {
            if (user_id.get(i) != "0") {
                arranged_meetingList.add("ElevID: "+studentList.get(i)+"    møde dato: "+ meeting_time.get(i)+ "    lokation: "+meeting_location.get(i));

            }
        }
    }

    /**
     * I metoden handleSelectMeetingTimeButton() defineres en variable date af typen LocalDate, som er lig med selectMeetingTime.getValue,
     * som læser den værdi der valgt i Datepickeren i MeetingView.
     * Denne værdi retuneres.
     */
   private LocalDate handleSelectMeetingTimeButton() {
        LocalDate date = selectMeetingTime.getValue();
        return date;
   }

    /**
     * Metoden handlePotentialMeeting() starter med at kalde loadModel() fra klassen MeetingModel.
     * Herefter defineres 2 ArrayLists, ved studentList sættes data ind fra MeetingModel med metoden getParticipatingStudent_id.
     * Dette gøres også ved den anden ArrayList med getParticipatingCoordinator.
     * Herefter benyttes en for løkke, så kører igennem for antalet elever der ønsker møde.
     * I for løkken er der en if løkke, hvor den er sand, hvis user_id er lig med 0, hvilket betyder der ikke er tildelt
     * en sundhedskoordinator. Hvis dette er sandt, benyttes add til meetingList.
     */
    private void handlePotentialMeeting() {
        meetingModel.loadModel();
        ArrayList<String> studentList = meetingModel.getParticipatingStudent_id();
        ArrayList<String> user_id = meetingModel.getParticipatingCoordinator();
        for (int i = 0; i < studentList.size(); i++) {
            if ( user_id.get(i) == "0" ){
                meetingList.removeAll(meetingList);
                meetingList.addAll(studentList.get(i));
                elevList.getItems().addAll(meetingList);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            potentialMeeting.setItems(arranged_meetingList);
            handleShowMeetings();
            handlePotentialMeeting();
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

