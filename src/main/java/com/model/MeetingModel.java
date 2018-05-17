import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

 class MeetingModel {
    static ArrayList<String> participatingStudent_id;
    static ArrayList<String> participatingCoordinator;
    static ArrayList<Date> meetingTime;

    static void setParticipatingCoordinator(ArrayList<String> participatingCoordinator){
        MeetingModel.participatingCoordinator =participatingCoordinator;
    }
    static ArrayList<String> getParticipatingCoordinator(){
        return participatingCoordinator;
    }

    static void setMeetingTime(ArrayList<Date> meetingTime) {
        MeetingModel.meetingTime = meetingTime;
    }

    static ArrayList<Date> getMeetingTime(){
        return meetingTime;
    }

    static void setparticipatingStudent_id(ArrayList<String> participatingStudent_id){
        MeetingModel.participatingStudent_id = participatingStudent_id;
    }
    static ArrayList<String> getParticipatingStudent_id(){
     return participatingStudent_id;
    }

     void loadModel(){
        DatabaseController.loadMeetingModel();
    }
     void updateModel(String student_id, String user_id, LocalDate date){
        DatabaseController.updateMeetingModel(student_id,user_id,date);
    }
}
