package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;

public class MaintainScheduleProgramActivity extends AppCompatActivity {

    //private Button mbtn_schedule;
    private ListView annualScheduleList;
    private AnnualScheduleAdapter annualScheduleAdapter;
    private static final String TAG = MaintainScheduleProgramActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_schedule_program);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Intent intent = getIntent();

        annualScheduleList = (ListView) findViewById(R.id.annual_schedule_list);

        final ArrayList<AnnualSchedule> annualSchedules = new ArrayList<>();
        annualScheduleAdapter = new AnnualScheduleAdapter(this, annualSchedules);

        annualScheduleList.setAdapter(annualScheduleAdapter);

        annualScheduleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AnnualSchedule annualSchedule = (AnnualSchedule) adapterView.getItemAtPosition(position);
                //annualSchedule = (AnnualSchedule) intent.getSerializableExtra("AnnualSchedule");
                Intent intent = new Intent(MaintainScheduleProgramActivity.this, CreateWeeklyScheduleActivity.class);
                intent.putExtra("AnnualSchedule", annualSchedule.getYear());
                startActivity(intent);
                return true;
            }
        });

        annualScheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AnnualSchedule annualSchedule = (AnnualSchedule) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(MaintainScheduleProgramActivity.this, WeeklyScheduleListActivity.class);
                intent.putExtra("AnnualScheduleToGetWeek", annualSchedule);
                startActivity(intent);
                MaintainScheduleProgramActivity.this.finish();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_create_schedule);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                ControlFactory.getMaintainScheduleController().startCreateSchedule();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        annualScheduleList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        annualScheduleList.setSelection(0);

        ControlFactory.getMaintainScheduleController().onDisplayProgramList(this);
    }

    public void showPrograms(List<AnnualSchedule> annualSchedules) {
        annualScheduleAdapter.clear();
        for (int i = 0; i < annualSchedules.size(); i++) {
            annualScheduleAdapter.add(annualSchedules.get(i));
        }
    }

}
