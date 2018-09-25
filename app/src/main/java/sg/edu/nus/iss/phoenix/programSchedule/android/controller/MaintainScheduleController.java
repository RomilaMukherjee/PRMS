package sg.edu.nus.iss.phoenix.programSchedule.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.CopyProgramSlotDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.CreateWeeklyScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.RetriveAnnualScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.CreateScheduleActivity;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.MaintainProgramSlotActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.MaintainScheduleProgramActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.MaintainScheduleActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.ProgramSlotListActivity;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.programSchedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.utility.ApplicationConstant;

import static android.content.ContentValues.TAG;

/**
 * Created by Ragu on 18/9/2018.
 */

public class MaintainScheduleController {
    private ProgramSlot progSlotObj;
    // Tag for logging.
    private static final String TAG = MaintainScheduleController.class.getName();
    private MaintainProgramSlotActivity slotCreateScreen;
	
	private MaintainScheduleProgramActivity maintainScheduleProgramActivity;

    public void startUseCase() {
        Intent intent = new Intent(MainController.getApp(), MaintainScheduleActivity.class);
        MainController.displayScreen(intent);
    }

    public void startCreateSchedule() {
        Intent intent = new Intent(MainController.getApp(), CreateScheduleActivity.class);
        MainController.displayScreen(intent);
    }

    public void selectCreateSchedule(AnnualSchedule annualSchedule) {
        new CreateScheduleDelegate(this).execute(annualSchedule);
    }

    public void startAnnualSchedule() {
        Intent intent = new Intent(MainController.getApp(), MaintainScheduleProgramActivity.class);
        MainController.displayScreen(intent);
    }

    public void programsRetrieved(List<AnnualSchedule> annualSchedules) {
        maintainScheduleProgramActivity.showPrograms(annualSchedules);
    }

    public void annualScheduleCreated(Boolean success) {
        startUseCase();
    }

    public void onDisplayProgramList(MaintainScheduleProgramActivity maintainScheduleProgramActivity) {
        this.maintainScheduleProgramActivity = maintainScheduleProgramActivity;
        new RetriveAnnualScheduleDelegate(this).execute("all");
    }

    public void startProgramSlot(){
        Intent intent = new Intent(MainController.getApp(), ProgramSlotListActivity.class);
        MainController.displayScreen(intent);
    }

    public void selectCreateWeeklySchedule(WeeklySchedule weeklySchedule) {
        new CreateWeeklyScheduleDelegate(this).execute(weeklySchedule);
    }

    /**
     * API to display copy program
     * slot screen
     * @param progSlotObj
     */
    public void copyProgramSlot(ProgramSlot progSlotObj){
        Log.v(TAG, "Copied radio program: " + progSlotObj.getProgramName());
        Intent intentObj = new Intent(MainController.getApp(), MaintainProgramSlotActivity.class);
        intentObj.putExtra(ApplicationConstant.programNameParam, progSlotObj);
        MainController.displayScreen(intentObj);
    }

    /**
     * API to invoke rest endpoint
     * for creating slot
     * @param slotObj
     */
    public void createSlot(ProgramSlot slotObj) {
        slotCreateScreen.showLoadingIndicatorForSlot();
        new CopyProgramSlotDelegate(this).execute(slotObj);
    }

}
