package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.content.SharedPreferences;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Button producerButton,presenterButton, programNameButton;
    private ProgressBar mslot_loading_indicator;
    private ProgramSlot programSlot,editProgramSlot;
    private String producer,presenter,programName;
    boolean editMode = false;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String TAG = "MaintainProgramSlotActivity";
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate= null;
    Date weekStartDate =null;
    Date dateofProgram=null;
    Date duration=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_slot);
        programSlot = new ProgramSlot();
        editProgramSlot = (ProgramSlot) getIntent().getSerializableExtra("SlotSelected");
        producer = (String) getIntent().getSerializableExtra("producer");
        presenter = (String) getIntent().getSerializableExtra("presenter");
        programName = (String) getIntent().getSerializableExtra("programName");
        producerButton = (Button) findViewById(R.id.view_producer);
        presenterButton = (Button) findViewById(R.id.view_presenter);
        programNameButton = (Button) findViewById(R.id.view_program);
        sharedPreferences = getSharedPreferences("programslot", MODE_PRIVATE);
        editor = sharedPreferences.edit();;
        // Find all relevant views that we will need to read user input from
        mPNameEditText = (EditText) findViewById(R.id.maintain_program_name_text_view);
        mPSStartDateText = (EditText) findViewById(R.id.maintain_program_startDate_text_view);
        mPSStartTimeText=(EditText) findViewById(R.id.maintain_program_slot_start_time_view);
        mPSDurationEditText = (EditText) findViewById(R.id.maintain_program_slot_duration_text_view);
        mPSWeekEditText = (EditText)findViewById(R.id.maintain_program_slot_week_text_view);
        mPSProducerText = (EditText)findViewById(R.id.maintain_program_slot_producer_text_view);
        mPSPresenterText = (EditText)findViewById(R.id.maintain_program_slot_presenter_text_view);
        mslot_loading_indicator = (ProgressBar) findViewById(R.id.pb_slot_loading_indicator);

        if(presenter!=null) {
            mPSPresenterText.setText(presenter);
            editor.putString("presenter",presenter);
            editor.commit();
            String producer=sharedPreferences.getString("producer",null);
            String programName=sharedPreferences.getString("programName",null);
            if(producer!=null)
                mPSProducerText.setText((producer));
            if(programName!=null)
                mPNameEditText.setText((programName));
        }

        if(producer!=null) {
            mPSProducerText.setText((producer));
            editor.putString("producer",producer);
            editor.commit();
            String presenter=sharedPreferences.getString("presenter",null);
            String programName=sharedPreferences.getString("programName",null);
            if(presenter!=null)
                mPSPresenterText.setText((presenter));
            if(programName!=null)
                mPNameEditText.setText((programName));
        }

        if(programName!=null) {
            mPNameEditText.setText((programName));
            editor.putString("programName",programName);
            editor.commit();
            String producer=sharedPreferences.getString("producer",null);
            String presenter=sharedPreferences.getString("presenter",null);
            if(producer!=null)
                mPSProducerText.setText((producer));
            if(presenter!=null)
                mPSPresenterText.setText((presenter));
        }

        // Keep the KeyListener for name EditText so as to enable editing after disabling it.

        if(editProgramSlot!=null)
        {
            Log.v("editSlot",editProgramSlot.getProgramName());
            ProgramSlot slotObj = new ProgramSlot(editProgramSlot.getProgramName(),
                    editProgramSlot.getDateOfProgram(),
                    editProgramSlot.getStartTime(), editProgramSlot.getDuration(),
                    editProgramSlot.getWeekStartDate(),
                    editProgramSlot.getProducerName(),editProgramSlot.getPresenterName());
            //ControlFactory.getMaintainScheduleController().copyProgramSlot(slotObj);
            editMode=true;

            mPNameEditText.setText(slotObj.getProgramName());
                mPSStartDateText.setText(slotObj.getDateOfProgram().toString());
                mPSStartTimeText.setText(slotObj.getStartTime().toString());
                mPSDurationEditText.setText(slotObj.getDuration().toString());

                if (null != slotObj.getWeekStartDate()) {
                    mPSWeekEditText.setText(slotObj.getWeekStartDate().toString());
                }

                Log.d(TAG, "weekstart date" + slotObj.getWeekStartDate());
                mPSProducerText.setText(slotObj.getProducerName());
                mPSPresenterText.setText(slotObj.getPresenterName());
        }

        producerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getMaintainScheduleController().startProducerScreen("producer");
            }
        });

        presenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getMaintainScheduleController().startProducerScreen("presenter");
            }
        });

        programNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getReviewSelectProgramController().startUseCase();
            }
        });
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
        if (editProgramSlot ==null) {
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
            case R.id.save_slot_menu_item:
                // Save radio program.
                if (editProgramSlot==null) { // Newly created.
                    //Log.v(TAG, "Creating program slot" + editProgramSlot.getProgramName() + "...");
                    if((mPSStartDateText.getText().toString() != null || mPSStartDateText.getText().toString() != " " || !mPSStartDateText.getText().toString().isEmpty())
                          &&(mPSWeekEditText.getText().toString() != null || mPSWeekEditText.getText().toString() != " " || !mPSWeekEditText.getText().toString().isEmpty())
                    &&(mPSStartTimeText.getText().toString() != null || mPSStartTimeText.getText().toString() != " " || !mPSStartTimeText.getText().toString().isEmpty())){
                        try {
                            startDate = simpleDateFormat.parse(mPSStartTimeText.getText().toString());
                            weekStartDate = simpleDateFormat.parse(mPSWeekEditText.getText().toString());
                            dateofProgram= simpleDateFormat.parse(mPSStartDateText.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    if(mPSDurationEditText.getText().toString() != null || mPSDurationEditText.getText().toString() != " " || !mPSDurationEditText.getText().toString().isEmpty())
                    {
                        try {
                            duration = timeFormat.parse(mPSDurationEditText.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    ProgramSlot ps = new ProgramSlot(mPNameEditText.getText().toString(), dateofProgram,startDate, duration,weekStartDate,
                            mPSProducerText.getText().toString(),mPSPresenterText.getText().toString());
                    Log.v(TAG,"Program slot :"+ ps.getWeekStartDate() + " " + ps.getStartTime() + " " + ps.getDateOfProgram());
                    ControlFactory.getMaintainScheduleController().selectCreateProgramSlot(ps);
                    Toast.makeText(this,"Slot Created Successfully",Toast.LENGTH_LONG);
                    editor.clear();
                }
                else { // Edited.
                    Log.v(TAG, "Saving program slot" + mPSStartDateText.getText().toString() + "...");
                    programSlot.setProgramName(mPNameEditText.getText().toString());
                    programSlot.setPresenterName(mPSPresenterText.getText().toString());
                    programSlot.setProducerName(mPSProducerText.getText().toString());

                    try {
                            duration = timeFormat.parse(mPSDurationEditText.getText().toString());
                            startDate = simpleDateFormat.parse(mPSStartTimeText.getText().toString());
                            weekStartDate = simpleDateFormat.parse(mPSWeekEditText.getText().toString());
                            dateofProgram= simpleDateFormat.parse(mPSStartDateText.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    ProgramSlot ps = new ProgramSlot(programSlot.getProgramName().toString(), dateofProgram,startDate, duration,weekStartDate,
                            programSlot.getProducerName().toString(),programSlot.getPresenterName().toString());
                    ControlFactory.getMaintainScheduleController().selectUpdateProgramSlot(programSlot);
                    editor.clear();
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.delete_slot_menu_item:
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
//        mPSStartTimeText.setText("", TextView.BufferType.EDITABLE);
//        mPSDurationEditText.setText("", TextView.BufferType.EDITABLE);
//        mPSStartDateText.setText("",TextView.BufferType.EDITABLE);
}

    public void editProgramSlot(ProgramSlot psedit) {
        if (psedit != null) {
            mPNameEditText.setText(psedit.getProgramName(), TextView.BufferType.NORMAL);
            mPSStartDateText.setText(psedit.getDateOfProgram().toString(), TextView.BufferType.EDITABLE);
            mPSDurationEditText.setText(psedit.getDuration().toString(), TextView.BufferType.EDITABLE);
            mPSWeekEditText.setText(psedit.getWeekStartDate().toString(), TextView.BufferType.EDITABLE);
            mPSStartTimeText.setText(psedit.getStartTime().toString(), TextView.BufferType.EDITABLE);
            mPSStartTimeText.setKeyListener(null);
        }
    }

}
