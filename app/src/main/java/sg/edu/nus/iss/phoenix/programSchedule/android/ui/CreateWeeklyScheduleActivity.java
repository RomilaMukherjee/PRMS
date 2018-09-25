package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.WeeklySchedule;

public class CreateWeeklyScheduleActivity extends AppCompatActivity {

    private EditText weekStartDate;
    private DatePickerDialog chooseDate;
    private AnnualSchedule annualSchedule;
    private Button createWeeklySchedule;
    private SharedPreferences sharedPreferences;
    private static final String TAG = CreateWeeklyScheduleActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_weekly_schedule);

        annualSchedule = new AnnualSchedule();
        annualSchedule = (AnnualSchedule) getIntent().getSerializableExtra("AnnualSchedule");
        weekStartDate = (EditText) findViewById(R.id.week_startdate);
        createWeeklySchedule = (Button) findViewById(R.id.create_weekly_schedule);

        Log.v(TAG, "intent :" + annualSchedule.getYear());
        weekStartDate.setOnClickListener(new View.OnClickListener() {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View view) {
                chooseDate = new DatePickerDialog(CreateWeeklyScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        weekStartDate.setText(day+"-"+month+"-"+year);
                    }
                }, mYear, mMonth, mDay);
                Date currentDate = new Date();
                chooseDate.getDatePicker().setMinDate(currentDate.getTime());
                chooseDate.show();
            }
        });

        try {
            createWeeklySchedule.setOnClickListener(new View.OnClickListener() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date startDate = simpleDateFormat.parse(weekStartDate.getText().toString());
                @Override
                public void onClick(View view) {
                    WeeklySchedule weeklySchedule = new WeeklySchedule(startDate, sharedPreferences.getString("username", "No Name found"), annualSchedule.getYear());
                    ControlFactory.getMaintainScheduleController().selectCreateWeeklySchedule(weeklySchedule);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
