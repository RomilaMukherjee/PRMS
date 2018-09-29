package sg.edu.nus.iss.phoenix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.android.entity.User;

public class PresenterListActivity extends AppCompatActivity {

    private ListView presenterListView;
    //private ProducerScreenAdapter producerScreenAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenter_list);

        presenterListView = (ListView) findViewById(R.id.presenter_list);
        final ArrayList<User> presenterList = new ArrayList<>();

    }
}
