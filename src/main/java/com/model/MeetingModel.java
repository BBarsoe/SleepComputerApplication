import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MeetingModel {
    static ArrayList<String> participatingStudent_id;
    static ArrayList<String> participatingCoordinator;
    static ArrayList<Date> meetingTime;

    public static void setParticipatingCoordinator(ArrayList<String> participatingCoordinator){
        MeetingModel.participatingCoordinator =participatingCoordinator;
    }
    public static ArrayList<String> getParticipatingCoordinator(){
        return participatingCoordinator;
    }

    public static void setMeetingTime(ArrayList<Date> meetingTime) {
        MeetingModel.meetingTime = meetingTime;
    }

    public static ArrayList<Date> getMeetingTime(){
        return meetingTime;
    }

    public void load(){
        DatabaseController.loadMeetingModel();
    }
    public void update(String student_id, String user_id, LocalDate date){
        DatabaseController.updateMeetingModel(student_id,user_id,date);
    }
}
