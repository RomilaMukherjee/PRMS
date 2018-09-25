package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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



    private ProgramSlot programToCopy = null;
    boolean editMode = false;
    private static final String TAG = "MaintainProgramSlotActivity";
    SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_slot);

        // Find all relevant views that we will need to read user input from
        mPNameEditText = (EditText) findViewById(R.id.maintain_program_name_text_view);
        mPSStartDateText = (EditText) findViewById(R.id.maintain_program_startDate_text_view);
        mPSStartTimeText=(EditText) findViewById(R.id.maintain_program_slot_start_time_view);
        mPSDurationEditText = (EditText) findViewById(R.id.maintain_program_slot_duration_text_view);
        mPSWeekEditText = (EditText)findViewById(R.id.maintain_program_slot_week_text_view);
        mPSYearEditText = (EditText)findViewById(R.id.maintain_program_slot_year_text_view);
        mPSProducerText = (EditText)findViewById(R.id.maintain_program_slot_producer_text_view);
        mPSPresenterText = (EditText)findViewById(R.id.maintain_program_slot_presenter_text_view);
        mbtn_btnCopySlot = (Button)findViewById(R.id.btnCopySlot);
        mslot_loading_indicator = (ProgressBar) findViewById(R.id.pb_slot_loading_indicator);

        try{
            final ProgramSlot editSlotObj = (ProgramSlot) getIntent().getSerializableExtra(ApplicationConstant.programNameParam);
            if (editSlotObj != null) {
                editMode = true;
                mPNameEditText.setText(editSlotObj.getProgramName());
                mPSStartDateText.setText(sdformat.format(editSlotObj.getDateOfProgram()));
                mPSStartTimeText.setText(timeFormat.format(editSlotObj.getStartTime()));
                mPSDurationEditText.setText(editSlotObj.getTime());

                if(null!=editSlotObj.getWeekStartDate()){
                    mPSWeekEditText.setText(sdformat.format(editSlotObj.getWeekStartDate()));
                }

                Log.d(TAG,"weekstart date"+editSlotObj.getWeekStartDate());
                mPSYearEditText.setText(String.valueOf(editSlotObj.getAnnualYear()));
                mPSProducerText.setText(editSlotObj.getProducerName());
                mPSPresenterText.setText(editSlotObj.getPresenterName());
            }

            // Set a click listener on Login Button
            mbtn_btnCopySlot.setOnClickListener(new View.OnClickListener() {
                // The code in this method will be executed when the numbers category is clicked on.
                @Override
                public void onClick(View view) {
                    ControlFactory.getMaintainScheduleController().createSlot(editSlotObj);

                }

            });
        }catch(Exception e){
            Log.e(TAG,"Error while creating copy::"+e);
        }
        // Keep the KeyListener for name EditText so as to enable editing after disabling it.

    }

    public void showLoadingIndicatorForSlot() {
        mslot_loading_indicator.setVisibility(View.VISIBLE);
    }



}
