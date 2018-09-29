package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.utility.ApplicationConstant;

/**
 * Created by Hp on 23-09-2018.
 */

public class MaintainProgramSlotActivity extends AppCompatActivity {

    private EditText mPNameEditText;
    private EditText mPSStartDateText;
    private EditText mPSStartTimeText;
    private EditText mPSDurationEditText;
    private EditText mPSWeekEditText;
    private EditText mPSYearEditText;
    private EditText mPSProducerText;
    private EditText mPSPresenterText;
    private Button mbtn_btnCopySlot;
    private ProgressBar mslot_loading_indicator;
    private ProgramSlot programSlot;
    boolean editMode = false;
    private static final String TAG = "MaintainProgramSlotActivity";
    SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_slot);
        programSlot = new ProgramSlot();
        programSlot = (ProgramSlot) getIntent().getSerializableExtra("SlotSelected");
        // Find all relevant views that we will need to read user input from
        mPNameEditText = (EditText) findViewById(R.id.maintain_program_name_text_view);
        mPSStartDateText = (EditText) findViewById(R.id.maintain_program_startDate_text_view);
        mPSStartTimeText=(EditText) findViewById(R.id.maintain_program_slot_start_time_view);
        mPSDurationEditText = (EditText) findViewById(R.id.maintain_program_slot_duration_text_view);
        mPSWeekEditText = (EditText)findViewById(R.id.maintain_program_slot_week_text_view);
        mPSYearEditText = (EditText)findViewById(R.id.maintain_program_slot_year_text_view);
        mPSProducerText = (EditText)findViewById(R.id.maintain_program_slot_producer_text_view);
        mPSPresenterText = (EditText)findViewById(R.id.maintain_program_slot_presenter_text_view);
        mslot_loading_indicator = (ProgressBar) findViewById(R.id.pb_slot_loading_indicator);

 //       try {
            // final ProgramSlot editSlotObj = (ProgramSlot) getIntent().getSerializableExtra(ApplicationConstant.programNameParam);
//            if (programSlot != null) {
//                Log.v("ps",programSlot.toString());
//                editMode = true;
//                mPNameEditText.setText(programSlot.getProgramName());
//                mPSStartDateText.setText(programSlot.getDateOfProgram());
//                mPSStartTimeText.setText(programSlot.getStartTime());
//                mPSDurationEditText.setText(programSlot.getTime());
//
//                if (null != programSlot.getWeekStartDate()) {
//                    mPSWeekEditText.setText(programSlot.getWeekStartDate());
//                }
//
//                Log.d(TAG, "weekstart date" + programSlot.getWeekStartDate());
//                mPSYearEditText.setText(programSlot.getAnnualYear());
//                mPSProducerText.setText(programSlot.getProducerName());
//                mPSPresenterText.setText(programSlot.getPresenterName());
//            }
//        }catch(Exception e){
//            Log.e(TAG,"Error while viewing::"+e);
//        }
        // Keep the KeyListener for name EditText so as to enable editing after disabling it.

        if(editMode==true)
        {
            ProgramSlot slotObj = new ProgramSlot(programSlot.getProgramName(), programSlot.getDateOfProgram(),
                    programSlot.getStartTime(), programSlot.getTime(),programSlot.getWeekStartDate(),
                    programSlot.getProducerName(),programSlot.getPresenterName());
            ControlFactory.getMaintainScheduleController().copyProgramSlot(slotObj);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getMaintainScheduleController().onDisplayProgram(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main_context_menu_programslot, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new radioprogram, hide the "Delete" menu item.
        if (editMode == false) {
            MenuItem menuItem = menu.findItem(R.id.delete_slot_menu_item);
            menuItem.setVisible(false);
            menuItem= menu.findItem(R.id.copy_slot_menu_item);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save radio program.
                if (editMode == false) { // Newly created.
                    Log.v(TAG, "Saving program slot" + mPNameEditText.getText().toString() + "...");
                    ProgramSlot ps = new ProgramSlot(mPNameEditText.getText().toString(), mPSStartDateText.getText().toString(),
                            mPSDurationEditText.getText().toString(),mPSStartTimeText.getText().toString(),mPSWeekEditText.getText().toString(),
                            mPSPresenterText.getText().toString(), mPSProducerText.getText().toString());
                    ControlFactory.getMaintainScheduleController().selectCreateProgramSlot(ps);
                }
                else { // Edited.
                    Log.v(TAG, "Saving program slot" + programSlot.getProgramName() + "...");
                    programSlot.setProgramName(mPNameEditText.getText().toString());
                    programSlot.setDateOfProgram(mPSStartDateText.getText().toString());
                    programSlot.setStartTime(mPSStartTimeText.getText().toString());
                    programSlot.setTime(mPSDurationEditText.getText().toString());
                    programSlot.setWeekStartDate(mPSWeekEditText.getText().toString());
                    programSlot.setPresenterName(mPSPresenterText.getText().toString());
                    programSlot.setProducerName(mPSProducerText.getText().toString());
                    ControlFactory.getMaintainScheduleController().selectUpdateProgramSlot(programSlot);
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                Log.v(TAG, "Deleting program slot " + programSlot.getStartTime() + "...");
                ControlFactory.getMaintainScheduleController().selectDeleteProgramSlot(programSlot);
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:
                Log.v(TAG, "Canceling creating/editing  program slot...");
                ControlFactory.getMaintainScheduleController().selectCancelCreateEditProgramSlot();
                return true;
        }

        return true;
    }


    public void showLoadingIndicatorForSlot() {
        mslot_loading_indicator.setVisibility(View.VISIBLE);
    }

    public void createProgramSlot() {
        editMode=false;
        mPNameEditText.setText("", TextView.BufferType.EDITABLE);
        mPSStartTimeText.setText("", TextView.BufferType.EDITABLE);
        mPSDurationEditText.setText("", TextView.BufferType.EDITABLE);
        mPSStartDateText.setText("",TextView.BufferType.EDITABLE);
    }

    public void editProgramSlot(ProgramSlot psedit) {
        if (psedit != null) {
            mPNameEditText.setText(psedit.getProgramName(), TextView.BufferType.NORMAL);
            mPSStartDateText.setText(psedit.getDateOfProgram(), TextView.BufferType.EDITABLE);
            mPSDurationEditText.setText(psedit.getTime(), TextView.BufferType.EDITABLE);
            mPSWeekEditText.setText(psedit.getWeekStartDate(), TextView.BufferType.EDITABLE);
            mPSStartTimeText.setText(psedit.getStartTime(), TextView.BufferType.EDITABLE);
            mPSStartTimeText.setKeyListener(null);
        }
    }

}
