package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.android.entity.User;
import sg.edu.nus.iss.phoenix.user.android.ui.ProducerScreenAdapter;

public class ProducerPresenterListActivity extends AppCompatActivity {

    private ListView producerPresenterListView;
    private ProducerScreenAdapter producerPresenterScreenAdapter;
    private String role;
    private static final String TAG = ProducerPresenterListActivity.class.getName();
    private User selectedUser = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_presenter_list);

        producerPresenterListView = (ListView) findViewById(R.id.producer_list);
        role = (String) getIntent().getSerializableExtra("role");
        final ArrayList<User> producerList = new ArrayList<>();
        producerPresenterScreenAdapter = new ProducerScreenAdapter(this, producerList);

        producerPresenterListView.setAdapter(producerPresenterScreenAdapter);

        producerPresenterListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedUser = (User) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        producerPresenterListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        producerPresenterListView.setSelection(0);

        ControlFactory.getMaintainScheduleController().displayProducerList(this,role);
    }

    public void showProducerList(List<User> producerList) {
        producerPresenterScreenAdapter.clear();
        for (int i = 0; i < producerList.size(); i++) {
            Log.v(TAG, "producerList :" + producerList.get(i).getName());
            producerPresenterScreenAdapter.add(producerList.get(i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_producer_presenter_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "View" menu option
            case R.id.action_select_producer_presenter:
                if (selectedUser == null) {
                    // Prompt for the selection of a radio program.
                    Toast.makeText(this, "Select a radio program first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected radio program.");
                }
                else {
                    Log.v(TAG, "Viewing radio program: " + selectedUser.getName() + "...");
                    ControlFactory.getMaintainScheduleController().producerPresenterSelected(selectedUser.getName(),role);
                }
        }

        return true;
    }
}
