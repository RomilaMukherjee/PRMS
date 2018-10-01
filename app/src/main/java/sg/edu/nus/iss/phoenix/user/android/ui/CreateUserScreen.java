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
    private User userObj = null;
    private EditText idField, passwordField;
    private Spinner spinnerValue;
    private boolean editMode = false;
    private Button submitButton, updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        nameField = (EditText) findViewById(R.id.EditTextName);
        idField = (EditText) findViewById(R.id.EditTextID);
        passwordField = (EditText) findViewById(R.id.EditTextPassword);
        spinnerValue = (Spinner)findViewById(R.id.Role_Spinner);

        ArrayList<Role> rolesList = new ArrayList<Role>();

        UserController userController= new UserController();

        Toast fieldEmptyToast = Toast.makeText(this,"One of the fields is Empty. Please Check",Toast.LENGTH_LONG);
        userObj = (User) getIntent().getSerializableExtra("selectedUserForEdit");

        if(userObj != null) {
            nameField.setText(userObj.getName());
            idField.setText(userObj.getId());
            passwordField.setText(userObj.getPassword());
            spinnerValue.setSelection(0);
            editMode = true;
        }

        submitButton = (Button)findViewById(R.id.ButtonAddUser);

        if(editMode) {
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nameField.getText().toString().length()==0 || idField.getText().toString().length()==0 || passwordField.getText().toString().length()==0 || spinnerValue.getSelectedItem().toString().length()==0){

                        fieldEmptyToast.show(); }
                    else{
                        Log.v(TAG, "role :" + spinnerValue.getSelectedItem().toString());

                        //Adds a role to arraylist of roles specific to this user
                        rolesList.add(new Role(spinnerValue.getSelectedItem().toString()));
                        User updateUser = new User(idField.getText().toString(),passwordField.getText().toString(),nameField.getText().toString(),rolesList);
                        Log.v(TAG, "newuser :" + updateUser.getRoles().get(0).getRole());
                        ControlFactory.getUserController().updateUser(updateUser);
                        //userController.saveUser(newuser);
                    }
                }
            });
        }
        else {
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
        //new RetrieveRoleDelegate().execute("allroles");

    }

}
