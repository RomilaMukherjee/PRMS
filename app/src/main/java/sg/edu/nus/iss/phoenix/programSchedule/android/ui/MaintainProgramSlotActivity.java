package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 * Created by Hp on 23-09-2018.
 */

public class MaintainProgramSlotActivity extends AppCompatActivity {

    private EditText mPNameEditText;
    private EditText mPSStartDateText;
    private EditText mPSStartTimeText;
    private EditText mPSDurationEditText;
    private ProgramSlot programToCopy = null;
    private static final String TAG = "MaintainProgramSlotActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_program);

        // Find all relevant views that we will need to read user input from
        mPNameEditText = (EditText) findViewById(R.id.maintain_program_name_text_view);
        mPSStartDateText = (EditText) findViewById(R.id.maintain_program_startDate_text_view);
        mPSStartTimeText=(EditText) findViewById(R.id.maintain_program_slot_start_time_view);
        mPSDurationEditText = (EditText) findViewById(R.id.maintain_program_slot_duration_text_view);

        try{

        }catch(Exception e){
            Log.e(TAG,"Error while creating copy");
        }
        // Keep the KeyListener for name EditText so as to enable editing after disabling it.

    }


}
