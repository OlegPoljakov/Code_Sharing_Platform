package platform;

import ch.qos.logback.classic.pattern.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "codestorage")
public class CodeInformation {

    private static int numofobjects = 0;  // static int noOfObjects = 0;
    private String code;
    private String date =  Date.getDate();
    private long time;
    //private String time;
    private int views;

    //@GeneratedValue(strategy= GenerationType.AUTO)
    //private int id = Date.getNewUUID();
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id = Date.getNewUUID();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String title;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    boolean timeLimit;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    long startSeconds;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    LocalDateTime startTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    boolean viewLimit;

    public CodeInformation() {}

    public CodeInformation(String code) {
        this.code = code;
        this.date = Date.getDate();
        numofobjects++;
        this.id = String.valueOf(numofobjects);
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public long getStartSeconds() {
        return startSeconds;
    }

    public boolean isViewLimit() {
        return viewLimit;
    }

    public boolean isTimeLimit() {
        return timeLimit;
    }

    public int getViews() {
        return views;
    }

    public long getTime() {
        return time;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setStartSeconds(long startSeconds) {
        this.startSeconds = startSeconds;
    }

    public void setViewLimit(boolean viewlimit) {
        this.viewLimit = viewlimit;
    }

    public void setTimeLimit(boolean timelimit) {
        this.timeLimit = timelimit;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /*
    private String SetDate() {
        LocalDateTime nowdate = LocalDateTime.now();
        LocalTime nowtime = LocalTime.now();
        String formattedDate = nowdate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " " + nowtime.withNano(0);
        return formattedDate;
    }
    */
}