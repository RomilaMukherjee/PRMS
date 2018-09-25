package sg.edu.nus.iss.phoenix.programSchedule.android.delegate;

import android.os.AsyncTask;

import sg.edu.nus.iss.phoenix.programSchedule.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;

/**
 * Created by Romila on 25-09-2018.
 */

public class CopyProgramSlotDelegate extends AsyncTask<ProgramSlot, Void, String> {

    // Tag for logging
    private static final String TAG = CopyProgramSlotDelegate.class.getName();
    private final MaintainScheduleController maintainScheduleController;

    /**
     * Constructor
     * @param maintainScheduleController
     */
    public CopyProgramSlotDelegate(MaintainScheduleController maintainScheduleController) {
        this.maintainScheduleController = maintainScheduleController;
    }

    @Override
    protected String doInBackground(ProgramSlot... programSlotObj) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}


