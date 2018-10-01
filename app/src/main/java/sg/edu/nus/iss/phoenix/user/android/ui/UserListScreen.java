package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

    interface DeleteClickListener {
        public void onDelete (User user, Context context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        ArrayList<User> users = new ArrayList<User>();
        mUAdapter = new UserAdapter(this, users, new DeleteClickListener() {
            @Override
            public void onDelete(User user, Context context) {
                Log.d("asd", "on delete has been clicked " + user.toString());
                ControlFactory.getUserController().onDeleteUser(user, context);
            }
        });

        mListView = (ListView) findViewById(R.id.user_list);
        createAUserButton = (FloatingActionButton)findViewById(R.id.fab);

        mListView.setAdapter(mUAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedUser = (User) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = (User) parent.getItemAtPosition(position);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedUser = (User) adapterView.getItemAtPosition(position);
                Log.v(TAG, "message :" + selectedUser.getName());
                Intent intent = new Intent(UserListScreen.this, CreateUserScreen.class);
                intent.putExtra("selectedUserForEdit", selectedUser);
                //intent.putExtra("editObject", )
                startActivity(intent);
                return true;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "View" menu option
            case R.id.action_select:
                if (selectedUser == null) {
                    // Prompt for the selection of a radio program.
                    Toast.makeText(this, "Select a radio program first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected radio program.");
                }
                else {
                    Log.v(TAG, "Viewing radio program: " + selectedUser.getName() + "...");
                    //ControlFactory.getProgramController().selectEditProgram(selectedRP);
                    Intent intent = new Intent(UserListScreen.this, CreateUserScreen.class);
                    intent.putExtra("selectedUserForEdit", selectedUser);
                    //intent.putExtra("editObject", )
                    startActivity(intent);
                }
        }

        return true;
    }


    public void showUsers(List<User> users) {
        mUAdapter.clear();
        for (int i = 0; i < users.size(); i++) {
            mUAdapter.add(users.get(i));
        }
    }
}
