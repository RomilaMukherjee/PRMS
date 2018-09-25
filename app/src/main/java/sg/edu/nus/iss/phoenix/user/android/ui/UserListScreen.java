package sg.edu.nus.iss.phoenix.user.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.RadioProgramAdapter;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.android.entity.User;

public class UserListScreen extends AppCompatActivity {
    private static final String TAG = UserListScreen.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        ArrayList<User> radioPrograms = new ArrayList<User>();
//        mRPAdapter = new RadioProgramAdapter(this, radioPrograms);
    }
}
