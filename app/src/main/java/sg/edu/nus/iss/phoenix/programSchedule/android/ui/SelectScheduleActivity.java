package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;

public class SelectScheduleActivity extends AppCompatActivity {

    private Button program_annual_schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_annual_schedule);
        program_annual_schedule = (Button) findViewById(R.id.program_annual_schedule);
        program_annual_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getAnnualScheduleController().startAnnualSchedule();
            }
        });
    }

}
