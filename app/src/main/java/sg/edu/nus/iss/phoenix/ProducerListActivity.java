package sg.edu.nus.iss.phoenix;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.android.entity.User;
import sg.edu.nus.iss.phoenix.user.android.ui.ProducerScreenAdapter;

public class ProducerListActivity extends AppCompatActivity {

    private ListView producerListView;
    private ProducerScreenAdapter producerScreenAdapter;
    private String role;
    private static final String TAG = ProducerListActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_list);

        producerListView = (ListView) findViewById(R.id.producer_list);
        role = (String) getIntent().getSerializableExtra("role");
        final ArrayList<User> producerList = new ArrayList<>();
        producerScreenAdapter = new ProducerScreenAdapter(this, producerList);

        producerListView.setAdapter(producerScreenAdapter);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        producerListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        producerListView.setSelection(0);

        ControlFactory.getMaintainScheduleController().displayProducerList(this,role);
    }

    public void showProducerList(List<User> producerList) {
        producerScreenAdapter.clear();
        for (int i = 0; i < producerList.size(); i++) {
            Log.v(TAG, "producerList :" + producerList.get(i).getName());
            producerScreenAdapter.add(producerList.get(i));
        }
    }
}
