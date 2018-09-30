package sg.edu.nus.iss.phoenix.programSchedule.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Romila on 22-09-2018.
 */

public class ProgramSlot implements Serializable {

    private String programName;
    private Date dateOfProgram;
    private Date startTime;
    private Date duration;
    private int annualYear;
    private Date weekStartDate;
    private String producerName;
    private String presenterName;

    public  ProgramSlot(){

    }
    public ProgramSlot(String programName, Date dateOfProgram, Date startTime,Date time,
                       Date weekStartDate, String producerName,String presenterName) {
        this.programName = programName;
        this.dateOfProgram = dateOfProgram;
        this.startTime = startTime;
        this.duration =time;
        this.weekStartDate=weekStartDate;
        this.producerName=producerName;
        this.presenterName=presenterName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Date getDateOfProgram() {
        return dateOfProgram;
    }

    public void setDateOfProgram(Date dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date time) {
        this.duration = time;
    }

    public int getAnnualYear() { return annualYear;}

    public void setAnnualYear(int annualYear) { this.annualYear = annualYear;}

    public Date getWeekStartDate() {return weekStartDate;}

    public void setWeekStartDate(Date weekStartDate) {this.weekStartDate = weekStartDate;}

    public String getProducerName() {return producerName;}

    public void setProducerName(String producerName) {this.producerName = producerName;}

    public String getPresenterName() {return presenterName;}

    public void setPresenterName(String presenterName) {this.presenterName = presenterName;}
}
