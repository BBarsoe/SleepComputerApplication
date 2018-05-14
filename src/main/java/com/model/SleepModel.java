import java.sql.Time;
import java.util.Date;

public class SleepModel {

    private static String student_id;
    private static String sleep_time;
    private static String awoke_time;
    private Integer sleep_quality;
    private Integer caffeine_intake;
    private Integer alcohol_intake;
    private Integer screen_time;
    private Integer physical_activity;
    private Integer work_in_bedroom;
    private Integer worries_in_bedroom;
    private Integer smoking;
    private Integer tired_before_bed;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSleep_time() {
        return sleep_time;
    }

    public void setSleep_time(String sleep_time) {
        this.sleep_time = sleep_time;
    }

    public String getAwoke_time() {
        return awoke_time;
    }

    public void setAwoke_time(String awoke_time) {
        this.awoke_time = awoke_time;
    }

}

