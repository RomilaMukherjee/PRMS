package sg.edu.nus.iss.phoenix.programSchedule.android.controller;

import android.content.Intent;

import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.CreateScheduleActivity;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.MaintainScheduleProgramActivity;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;

/**
 * Created by Ragu on 18/9/2018.
 */

public class MaintainScheduleController {
    public void startUseCase() {
        Intent intent = new Intent(MainController.getApp(), MaintainScheduleProgramActivity.class);
        MainController.displayScreen(intent);
    }

    public void startCreateSchedule() {
        Intent intent = new Intent(MainController.getApp(), CreateScheduleActivity.class);
        MainController.displayScreen(intent);
    }

    public void selectCreateSchedule(AnnualSchedule annualSchedule) {
        new CreateScheduleDelegate(this).execute(annualSchedule);
    }

    public void annualScheduleCreated(Boolean success) {
        startUseCase();
    }
}
