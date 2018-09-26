package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;

public class ProgramSlotListActivity extends AppCompatActivity {

    private static final String TAG = "ProgramSlotListActivity";
    SimpleDateFormat sdformat   = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    private List<AnnualSchedule> annualScheduleLst;
    List<String> yearLst = new ArrayList<String>();
    Spinner yearSpinner;
    Spinner weekSpinner;
    ArrayAdapter arrayAdapter;
    private ListView annualScheduleList;
    private ListView slotList;
    private ProgramSlotAdapter slotAdapter;
    private ListView programSlotListView;
    private ArrayList<ProgramSlot> programSlotLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_slot_list);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);

        programSlotListView = (ListView) findViewById(R.id.radio_slot_list);
        programSlotLst = new ArrayList<ProgramSlot>();
        slotAdapter = new ProgramSlotAdapter(this, programSlotLst);
        programSlotListView.setAdapter(slotAdapter);

        if(annualScheduleList!=null) {
            annualScheduleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                    ProgramSlot programSlot = (ProgramSlot) adapterView.getItemAtPosition(position);
                    Intent intent = new Intent(ProgramSlotListActivity.this, MaintainProgramSlotActivity.class);
                    intent.putExtra("ProgramSlot", programSlot);
                    startActivity(intent);
                    return true;
                }
            });


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_create_program_slot);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /**Create program Slot dummy object to copy and persist data**/
                    int annualYear = 2018;
                    String programName = "charity";
                    String dateInStr = "22/09/2018";
                    String timeInStr = "06:30:00";
                    String weekDateInStr = "17/09/2018";
                    String progDuration = "00:30:00";
                    String producer = "dogbert";
                    String presenter = "dilbert";

                    ProgramSlot slotObj = new ProgramSlot(programName, dateInStr, timeInStr, progDuration);
                    ControlFactory.getMaintainScheduleController().copyProgramSlot(slotObj);
                }
            });
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getMaintainScheduleController().displayAnnualList(this);
        ControlFactory.getMaintainScheduleController().displaySlotList(this);
        programSlotListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        programSlotListView.setSelection(0);
    }

    /**
     * API to populate the Spinner
     * for Annual Year
     * @param annualSchedules
     */
    public void showAnnualLst(List<AnnualSchedule> annualSchedules){
        annualScheduleLst = annualSchedules;

        for(AnnualSchedule year : annualScheduleLst){
            yearLst.add(String.valueOf(year.getYear()));
        }
       /* ArrayAdapter adapterForAnnualSchedule = new ArrayAdapter(this, android.R.layout.simple_spinner_item,yearLst );
        adapterForAnnualSchedule.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        yearSpinner.setAdapter(adapterForAnnualSchedule);
        Log.d(TAG,"List of schedules received :: size"+yearLst.size());*/
    }

    public void showSlotList(List<ProgramSlot> programSlots){
        slotAdapter.clear();
        Log.v(TAG, "List of Slots is :" + programSlots.isEmpty());
        for (int i = 0; i < programSlots.size(); i++) {
            slotAdapter.add(programSlots.get(i));
        }
    }

}
