package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;

public class ProgramSlotListActivity extends AppCompatActivity {

    private static final String TAG = "ProgramSlotListActivity";
    SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_slot_list);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_create_program_slot);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /**Create program Slot dummy object to copy and persist data**/
                ProgramSlot slotObj = new ProgramSlot();

                int annualYear=2018;
                String programName="charity";
                String dateInStr = "22/09/2018";
                String timeInStr = "06:30:00";
                String weekDateInStr = "17/09/2018";
                String progDuration = "00:30:00";
                String producer = "dogbert";
                String presenter = "dilbert";
                try{
                    Date programStartDate = sdformat.parse(dateInStr);
                    Date programStartTime = timeFormat.parse(timeInStr);
                    Date weekStartDate = sdformat.parse(weekDateInStr);
                    slotObj.setAnnualYear(annualYear);
                    slotObj.setProgramName(programName);
                    slotObj.setDateOfProgram(programStartDate);
                    slotObj.setStartTime(programStartTime);
                    slotObj.setWeekStartDate(weekStartDate);
                    slotObj.setTime(progDuration);
                    slotObj.setProducerName(producer);
                    slotObj.setPresenterName(presenter);
                }catch(ParseException exceptObj){
                   Log.e(TAG,"Error while parsing");
                }
                ControlFactory.getMaintainScheduleController().copyProgramSlot(slotObj);
            }
        });
    }
}
