package sg.edu.nus.iss.phoenix.programSchedule.entity;

/**
 * Created by Ragu on 19/9/2018.
 */

public class AnnualSchedule {
    private int year;
    private String assignedBy;

    public AnnualSchedule(int year) {
        this.year = year;
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
