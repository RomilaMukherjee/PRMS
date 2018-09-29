package sg.edu.nus.iss.phoenix.programSchedule.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Romila on 22-09-2018.
 */

public class ProgramSlot implements Serializable {

    private String programName;
    private String dateOfProgram;
    private String startTime;
    private String time;
    private int annualYear;
    private String weekStartDate;
    private String producerName;
    private String presenterName;

    public  ProgramSlot(){

    }
    public ProgramSlot(String programName, String dateOfProgram, String startTime,String time,
                       String weekStartDate, String producerName,String presenterName) {
        this.programName = programName;
        this.dateOfProgram = dateOfProgram;
        this.startTime = startTime;
        this.time =time;
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

    public String getDateOfProgram() {
        return dateOfProgram;
    }

    public void setDateOfProgram(String dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
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

    public String getWeekStartDate() {return weekStartDate;}

    public void setWeekStartDate(String weekStartDate) {this.weekStartDate = weekStartDate;}

    public String getProducerName() {return producerName;}

    public void setProducerName(String producerName) {this.producerName = producerName;}

    public String getPresenterName() {return presenterName;}

    public void setPresenterName(String presenterName) {this.presenterName = presenterName;}
}
