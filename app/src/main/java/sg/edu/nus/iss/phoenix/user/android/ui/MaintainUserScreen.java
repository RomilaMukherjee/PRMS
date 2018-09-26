package sg.edu.nus.iss.phoenix.user.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sg.edu.nus.iss.phoenix.R;

public class MaintainUserScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = MaintainUserScreen.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

    }
}
