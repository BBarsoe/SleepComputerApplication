import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

class SleepModel {

    private static String student_id;
    static ArrayList<String> sleep_time;
    static ArrayList<String> awoke_time;
    private Integer sleep_quality;
    private Integer caffeine_intake;
    private Integer alcohol_intake;
    private Integer screen_time;
    private Integer physical_activity;
    private Integer work_in_bedroom;
    private Integer worries_in_bedroom;
    private Integer smoking;
    private Integer tired_before_bed;

     String getStudent_id() {
        return student_id;
    }

     void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

     ArrayList<String> getSleep_time() {
        return sleep_time;
    }

     void setSleep_time(ArrayList<String> sleep_time) {
        this.sleep_time = sleep_time;
    }

     ArrayList<String> getAwoke_time() {
        return awoke_time;
    }

     void setAwoke_time(ArrayList<String> awoke_time) {
        this.awoke_time = awoke_time;
    }


}

