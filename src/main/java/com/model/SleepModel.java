import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

class SleepModel {

    private static String student_id;
    private static ArrayList<String> sleep_time;
    private static ArrayList<String> awoke_time;

     public String getStudent_id() {
        return student_id;
    }

     public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

     public ArrayList<String> getSleep_time() {
        return sleep_time;
    }

     public void setSleep_time(ArrayList<String> sleep_time) {
        this.sleep_time = sleep_time;
    }

     public ArrayList<String> getAwoke_time() {
        return awoke_time;
    }

     public void setAwoke_time(ArrayList<String> awoke_time) {
        this.awoke_time = awoke_time;
    }

    public void loadModel(String student){
         if (student != null) {
             DatabaseController.loadSleepModel(student);
         }else{
             DatabaseController.loadPopSleepModel();
         }
    }

}

