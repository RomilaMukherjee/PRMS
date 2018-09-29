package sg.edu.nus.iss.phoenix.programSchedule.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.CreateProgramSlotDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.DeleteProgramSlotDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.UpdateProgramSlotDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.MaintainScheduleActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.WeeklyScheduleListActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.RetriveWeeklyScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.ProducerPresenterListActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.CopyProgramSlotDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.CreateWeeklyScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.RetrieveProgramSlotDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.RetriveAnnualScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.CreateScheduleActivity;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.MaintainProgramSlotActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.MaintainScheduleProgramActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.ProgramSlotListActivity;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.SelectScheduleActivity;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.programSchedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.RetriveProducerPresenterDelegate;
import sg.edu.nus.iss.phoenix.user.android.entity.User;

/**
 * Created by Ragu on 18/9/2018.
 */

public class MaintainScheduleController {
    private ProgramSlot progSlotObj;
    // Tag for logging.
    private static final String TAG = MaintainScheduleController.class.getName();
    private MaintainProgramSlotActivity slotCreateScreen;
    private ProgramSlotListActivity slotListActivity;
    private ProducerPresenterListActivity producerPresenterListActivity;
	
	private MaintainScheduleProgramActivity maintainScheduleProgramActivity;
	private WeeklyScheduleListActivity weeklyScheduleListActivity;

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

    public void startProducerScreen(String role) {
        Intent intent = new Intent(MainController.getApp(), ProducerPresenterListActivity.class);
        intent.putExtra("role", role);
        MainController.displayScreen(intent);
    }

    public void producerPresenterSelected(String name) {
        Intent intent = new Intent(MainController.getApp(), MaintainProgramSlotActivity.class);
        intent.putExtra("prodpres", name);
        MainController.displayScreen(intent);
    }

    public void programSeleced(String name) {
        Intent intent = new Intent(MainController.getApp(), MaintainProgramSlotActivity.class);
        intent.putExtra("programName", name);
        MainController.displayScreen(intent);
    }



    public void programsRetrieved(List<AnnualSchedule> annualSchedules) {
        Log.d(TAG,"Object of slot list activity"+slotListActivity);
        Log.d(TAG,"Object of maintainScheduleProgramActivity list activity"+maintainScheduleProgramActivity);

        if(maintainScheduleProgramActivity!=null) {
            maintainScheduleProgramActivity.showPrograms(annualSchedules);
        }
    }

    public void annualScheduleCreated(Boolean success) {
        startUseCase();
    }

    public void startCreateProgramSlot() {
        Intent intent = new Intent(MainController.getApp(), MaintainProgramSlotActivity.class);
        MainController.displayScreen(intent);
    }

    public void startUpdateProgramSlot() {
        Intent intent = new Intent(MainController.getApp(), MaintainProgramSlotActivity.class);
        MainController.displayScreen(intent);
    }

    public void programSlotCreated(Boolean success) {
        startUseCase();
    }

    public void programSlotUpdated(Boolean success) {startUseCase(); }

    public void programSlotDeleted(Boolean success) {startUseCase(); }

    public void onDisplayProgramList(MaintainScheduleProgramActivity maintainScheduleProgramActivity) {
        this.maintainScheduleProgramActivity = maintainScheduleProgramActivity;
        new RetriveAnnualScheduleDelegate(this).execute("all");
    }

    public void startProgramSlot(){
        Intent intent = new Intent(MainController.getApp(), SelectScheduleActivity.class);
        MainController.displayScreen(intent);
    }

    public void selectCreateWeeklySchedule(WeeklySchedule weeklySchedule) {
        new CreateWeeklyScheduleDelegate(this).execute(weeklySchedule);
    }

    public void getWeeklyListByYear(WeeklyScheduleListActivity weeklyScheduleListActivity, AnnualSchedule annualSchedule) {
        this.weeklyScheduleListActivity = weeklyScheduleListActivity;
        new RetriveWeeklyScheduleDelegate(this).execute(annualSchedule.getYear());
    }

    /**
     * API to display copy program
     * slot screen
     * @param progSlotObj
     */
    public void copyProgramSlot(ProgramSlot progSlotObj){
        Log.v(TAG, "Copied program slot: " + progSlotObj.getProgramName());
        Intent intentObj = new Intent(MainController.getApp(), MaintainProgramSlotActivity.class);
        intentObj.putExtra("SlotSelected", progSlotObj);
        MainController.displayScreen(intentObj);
    }

    /**
     * API to display create program Slot
     * slot screen
     */
    public void createProgramSlot(){
        Intent intentObj = new Intent(MainController.getApp(), MaintainProgramSlotActivity.class);
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

    /**
     * API to fetch annual list
     * @param
     */
    public void displayAnnualList(ProgramSlotListActivity slotListActivity){
        this.slotListActivity = slotListActivity;
        new RetriveAnnualScheduleDelegate(this).execute("all");
    }

    public void displayProducerList(ProducerPresenterListActivity producerPresenterListActivity, String role) {
        this.producerPresenterListActivity = producerPresenterListActivity;
        new RetriveProducerPresenterDelegate(this).execute(role);
    }

    public void usersRetrieved(List<User> producerList) {
        producerPresenterListActivity.showProducerList(producerList);
    }


    /**
     * API to fetch annual list
     * @param
     */
    public void displaySlotList(ProgramSlotListActivity slotListActivity){
        this.slotListActivity = slotListActivity;
        //new RetrieveProgramSlotDelegate(this).execute("2018-08-26 00:00:00");
        new RetrieveProgramSlotDelegate(this).execute("all_programslots");
    }

    public void programSlotRetrieved(List<ProgramSlot> programSlotList){
        if(slotListActivity!=null) {
           slotListActivity.showSlotList(programSlotList);
        }
    }

    public void weeklySchedulesRetrived(List<WeeklySchedule> weeklySchedules) {
        if(weeklyScheduleListActivity!=null) {
            weeklyScheduleListActivity.showWeeklySchedules(weeklySchedules);
        }
    }

    public void onDisplayProgram(MaintainProgramSlotActivity maintainProgramSlot) {
        this.slotCreateScreen = maintainProgramSlot;
        if (progSlotObj == null)
            maintainProgramSlot.createProgramSlot();
        else
            maintainProgramSlot.editProgramSlot(progSlotObj);
    }

    public void selectCreateProgramSlot(ProgramSlot ps) {
        new CreateProgramSlotDelegate(this).execute(ps);
    }

    public void selectCancelCreateEditProgramSlot() {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    public void selectUpdateProgramSlot(ProgramSlot ps) {
        new UpdateProgramSlotDelegate(this).execute(ps);
    }

    public void selectDeleteProgramSlot(ProgramSlot ps) {
        new DeleteProgramSlotDelegate(this).execute(ps.getStartTime());
    }

}
