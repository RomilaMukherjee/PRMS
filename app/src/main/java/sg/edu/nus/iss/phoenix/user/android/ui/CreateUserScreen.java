package sg.edu.nus.iss.phoenix.user.android.ui;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.android.controller.UserController;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class CreateUserScreen extends AppCompatActivity {

    private static final String TAG = CreateUserScreen.class.getName();
    private EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        nameField = (EditText) findViewById(R.id.EditTextName);


        final EditText idField = (EditText) findViewById(R.id.EditTextID);

        final EditText passwordField = (EditText) findViewById(R.id.EditTextPassword);


        final Spinner spinnerValue = (Spinner)findViewById(R.id.Role_Spinner);

        ArrayList<Role> rolesList = new ArrayList<Role>();

        Button submitButton = (Button)findViewById(R.id.ButtonAddUser);
        UserController userController= new UserController();

        Toast fieldEmptyToast = Toast.makeText(this,"One of the fields is Empty. Please Check",Toast.LENGTH_LONG);

        //new RetrieveRoleDelegate().execute("allroles");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.v(TAG, nameField.getText().toString());
                //Validate Details
                if (nameField.getText().toString().length()==0 || idField.getText().toString().length()==0 || passwordField.getText().toString().length()==0 || spinnerValue.getSelectedItem().toString().length()==0){

                    fieldEmptyToast.show(); }
                else{
                    Log.v(TAG, "role :" + spinnerValue.getSelectedItem().toString());

                    //Adds a role to arraylist of roles specific to this user
                    rolesList.add(new Role(spinnerValue.getSelectedItem().toString()));
                    User newuser = new User(idField.getText().toString(),passwordField.getText().toString(),nameField.getText().toString(),rolesList);
                    Log.v(TAG, "newuser :" + newuser.getRoles().get(0).getRole());
                    ControlFactory.getUserController().saveUser(newuser);
                    //userController.saveUser(newuser);

                }



            }
        });

    }







}
