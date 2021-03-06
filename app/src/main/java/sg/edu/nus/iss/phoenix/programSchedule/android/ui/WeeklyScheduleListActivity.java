package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
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
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.WeeklySchedule;

public class WeeklyScheduleListActivity extends AppCompatActivity {
    private ListView weeklyScheduleList;
    private WeeklyScheduleAdapter weeklyScheduleAdapter;
    private AnnualSchedule annualSchedule;
    private static final String TAG = WeeklyScheduleListActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule_list);

        annualSchedule = new AnnualSchedule();
        annualSchedule = (AnnualSchedule) getIntent().getSerializableExtra("AnnualScheduleToGetWeek");

        weeklyScheduleList = (ListView) findViewById(R.id.weekly_schedule_list);
        final ArrayList<WeeklySchedule> weeklySchedules = new ArrayList<>();

        weeklyScheduleAdapter = new WeeklyScheduleAdapter(this, weeklySchedules);
        weeklyScheduleList.setAdapter(weeklyScheduleAdapter);

        weeklyScheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                WeeklySchedule weekSchedule = (WeeklySchedule) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(WeeklyScheduleListActivity.this, ProgramSlotListActivity.class);
                intent.putExtra("WeekForSelectedAnnual", weekSchedule.getStartDate().toString());
                Log.v("WeekSelected", weekSchedule.getStartDate().toString());
                startActivity(intent);
                //WeeklyScheduleListActivity.this.finish();
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        weeklyScheduleList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        weeklyScheduleList.setSelection(0);

        ControlFactory.getMaintainScheduleController().getWeeklyListByYear(this, annualSchedule);
    }

    public void showWeeklySchedules(List<WeeklySchedule> weeklySchedules) {
        weeklyScheduleAdapter.clear();
        for (int i = 0; i < weeklySchedules.size(); i++) {
            weeklyScheduleAdapter.add(weeklySchedules.get(i));
        }
    }
}
