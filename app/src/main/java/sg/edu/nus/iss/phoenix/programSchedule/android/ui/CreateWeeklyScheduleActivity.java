package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.RetriveWeeklyScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.WeeklySchedule;

public class CreateWeeklyScheduleActivity extends AppCompatActivity {

    private EditText weekStartDate;
    private DatePickerDialog chooseDate;
    private Button createWeeklySchedule;
    private SharedPreferences sharedPreferences;
    private static final String TAG = CreateWeeklyScheduleActivity.class.getName();
    //private RetriveWeeklyScheduleDelegate retriveWeeklyScheduleDelegate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_weekly_schedule);

        //annualSchedule = new AnnualSchedule();
        int annualSchedule = (int) getIntent().getSerializableExtra("AnnualSchedule");
        weekStartDate = (EditText) findViewById(R.id.week_startdate);
        createWeeklySchedule = (Button) findViewById(R.id.create_weekly_schedule);
        sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE);

        weekStartDate.setOnClickListener(new View.OnClickListener() {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(final View view) {
                chooseDate = new DatePickerDialog(CreateWeeklyScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Log.v(TAG, "");
                        if(annualSchedule == year) {
                            weekStartDate.setText(day + "-" + month + "-" + year);
                        }
                        else {
                            Snackbar.make(view, "Week should be created within the year", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                }, mYear, mMonth, mDay);
                Date currentDate = new Date();
                chooseDate.getDatePicker().setMinDate(currentDate.getTime());
                chooseDate.show();
            }
        });
        Log.v(TAG, "intent :");
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            createWeeklySchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date startDate = null;
                    try {
                        if(weekStartDate.getText().toString() != null || weekStartDate.getText().toString() != " " || !weekStartDate.getText().toString().isEmpty())
                            startDate = simpleDateFormat.parse(weekStartDate.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(startDate != null) {
                        AnnualSchedule annualScheduleObj = new AnnualSchedule(annualSchedule, sharedPreferences.getString("username", "No Name found"));
                        ControlFactory.getMaintainScheduleController().selectCreateSchedule(annualScheduleObj);
                        //Log.v(TAG, "Start date :" + startDate.toString());
                        WeeklySchedule weeklySchedule = new WeeklySchedule(startDate, sharedPreferences.getString("username", "No Name found"), annualSchedule);
                        ControlFactory.getMaintainScheduleController().selectCreateWeeklySchedule(weeklySchedule);
                    }
                    else {
                        Snackbar.make(view, "Select start date", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });

//        retriveWeeklyScheduleDelegate = new RetriveWeeklyScheduleDelegate();
//        retriveWeeklyScheduleDelegate.execute("all_weeklySchedule");

    }
}
