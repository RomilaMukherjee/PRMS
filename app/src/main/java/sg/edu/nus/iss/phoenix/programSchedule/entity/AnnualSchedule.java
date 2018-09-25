package sg.edu.nus.iss.phoenix.programSchedule.entity;

import java.io.Serializable;

/**
 * Created by Ragu on 19/9/2018.
 */

public class AnnualSchedule implements Serializable {
    private int year;
    private String assignedBy;

    public AnnualSchedule() {

    }

    public AnnualSchedule(int year, String assignedBy) {
        this.year = year;
        this.assignedBy = assignedBy;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }
}
