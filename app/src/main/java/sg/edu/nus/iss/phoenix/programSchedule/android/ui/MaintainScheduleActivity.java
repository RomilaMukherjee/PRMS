package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;

public class MaintainScheduleActivity extends AppCompatActivity {

    private Button annualScheduleButton;
    private Button programSlotButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_schedule);

        annualScheduleButton = (Button) findViewById(R.id.annaul_schedule);
        programSlotButton = (Button) findViewById(R.id.program_slot);

        annualScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getAnnualScheduleController().startAnnualSchedule();
            }
        });

        programSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getProgramSlotController().startProgramSlot();
            }
        });
    }
}
