package sg.edu.nus.iss.phoenix.programSchedule.entity;

import java.util.Date;

/**
 * Created by Ragu on 24/9/2018.
 */

public class WeeklySchedule {
    private Date startDate;
    private String assignedBy;
    //private int year;

    public WeeklySchedule() {

    }

    public WeeklySchedule(Date startDate, String assignedBy) {
        this.startDate = startDate;
        this.assignedBy = assignedBy;
        //this.year = year;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    /*public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }*/
}
