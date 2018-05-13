import java.sql.Time;
import java.util.Date;

public class SleepModel {

    private String student_id;
    private Date sleep_time;
    private Date awoke_time;
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

    public Date getSleep_time() {
        return sleep_time;
    }

    public void setSleep_time(Date sleep_time) {
        this.sleep_time = sleep_time;
    }

    public Date getAwoke_time() {
        return awoke_time;
    }

    public void setAwoke_time(Date awoke_time) {
        this.awoke_time = awoke_time;
    }

}

