package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class UserListScreen extends AppCompatActivity {
    private static final String TAG = UserListScreen.class.getName();
    private ListView mListView;
    private User selectedUser = null;
    private UserAdapter mUAdapter;
    private FloatingActionButton createAUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        ArrayList<User> users = new ArrayList<User>();
        mUAdapter = new UserAdapter(this, users);

        mListView = (ListView) findViewById(R.id.user_list);
        createAUserButton = (FloatingActionButton)findViewById(R.id.fab);

        mListView.setAdapter(mUAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "Radio program at position " + position + " selected.");
                User user = (User) adapterView.getItemAtPosition(position);
                // Log.v(TAG, "Radio program name is " + rp.getRadioProgramName());
                selectedUser = user;
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });

        //Setup button's listener
        createAUserButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){

                Intent goToCreateUser = new Intent(UserListScreen.this,CreateUserScreen.class);
                startActivity(goToCreateUser);
            }
        });
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setSelection(0);

        ControlFactory.getUserController().onDisplayUserList(this);
    }


    public void showUsers(List<User> users) {
        mUAdapter.clear();
        for (int i = 0; i < users.size(); i++) {
            mUAdapter.add(users.get(i));
        }
    }
}
