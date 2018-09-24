package sg.edu.nus.iss.phoenix.programSchedule.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Romila on 22-09-2018.
 */

public class ProgramSlot implements Serializable {

    private String programName;
    private Date dateOfProgram;
    private Date startTime;
    private String time;
    private int annualYear;
    private Date weekStartDate;
    private String producerName;
    private String presenterName;


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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
