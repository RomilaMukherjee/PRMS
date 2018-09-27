package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;

public class ProgramSlotListActivity extends AppCompatActivity {

    private static final String TAG = "ProgramSlotListActivity";
    private ListView slotList;
    private ProgramSlotAdapter slotAdapter;
    private ListView programSlotListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_slot_list);
        programSlotListView = (ListView) findViewById(R.id.radio_slot_list);
        ArrayList<ProgramSlot> programSlotLst = new ArrayList<ProgramSlot>();
        slotAdapter = new ProgramSlotAdapter(this, programSlotLst);
        programSlotListView.setAdapter(slotAdapter);

        if(programSlotListView!=null) {
            Log.d(TAG,"Inside programSlotListView when not null");
            programSlotListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        ControlFactory.getMaintainScheduleController().displaySlotList(this);
        programSlotListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        programSlotListView.setSelection(0);
    }

    public void showSlotList(List<ProgramSlot> programSlots){
        slotAdapter.clear();
        Log.v(TAG, "List of Slots is :" + programSlots.size());
        for (int i = 0; i < programSlots.size(); i++) {
            Log.d(TAG,"Indise program slot.."+programSlots.get(i).getProgramName());
            slotAdapter.add(programSlots.get(i));
        }
    }

}
