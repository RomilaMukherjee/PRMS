package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import sg.edu.nus.iss.phoenix.R;

import static java.lang.String.*;

public class CreateScheduleActivity extends AppCompatActivity {

    private EditText annualScheduleyear;
    private DatePickerDialog chooseYear;
    private Button createSchedule;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        annualScheduleyear = (EditText) findViewById(R.id.annula_schedule_year);
        createSchedule = (Button) findViewById(R.id.create_annual_schedule);

        annualScheduleyear.setOnClickListener(new View.OnClickListener() {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            @Override
            public void onClick(View view) {
                chooseYear = new DatePickerDialog(CreateScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        annualScheduleyear.setText(""+year);
                    }
                }, mYear, mMonth, mYear);
                Date currentDate = new Date();
                chooseYear.getDatePicker().setMinDate(currentDate.getTime());
                chooseYear.show();
            }
        });

        //chooseYear.on
    }
}
