import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

 class MeetingModel {
    private static ArrayList<String> participatingStudent_id;
    private static ArrayList<String> participatingCoordinator;
    private static ArrayList<Date> meetingTime;
    private static ArrayList<String> meetingLocation;

    public void setParticipatingCoordinator(ArrayList<String> participatingCoordinator){
        MeetingModel.participatingCoordinator =participatingCoordinator;
    }
     public ArrayList<String> getParticipatingCoordinator(){
        return participatingCoordinator;
    }

     public void setMeetingTime(ArrayList<Date> meetingTime) {
        MeetingModel.meetingTime = meetingTime;
    }

     public ArrayList<Date> getMeetingTime(){
        return meetingTime;
    }

     public void setparticipatingStudent_id(ArrayList<String> participatingStudent_id){
        MeetingModel.participatingStudent_id = participatingStudent_id;
    }

     public ArrayList<String> getParticipatingStudent_id(){
     return participatingStudent_id;
    }

     public ArrayList<String> getMeetingLocation() {
         return meetingLocation;
     }

     public void setMeetingLocation(ArrayList<String> meetingLocation) {
         MeetingModel.meetingLocation = meetingLocation;
     }

     public void loadModel(){
        DatabaseController.loadMeetingModel();
    }
     public void updateModel(String student_id, String user_id, LocalDate date){
        DatabaseController.updateMeetingModel(student_id,user_id,date);
    }
}
