package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sg.edu.nus.iss.phoenix.user.android.controller.UserController;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;


public class RetrieveUsersDelegate extends AsyncTask<String,Void,String > {
    private static final String TAG = RetrieveUsersDelegate.class.getName();
    private UserController userController = null;

    public RetrieveUsersDelegate(UserController userController) {
        this.userController = userController;
    }

    @Override
    protected String doInBackground(String... params) {
        Uri builtUri1 = Uri.parse( PRMS_BASE_URL_USER).buildUpon().build();
        Uri builtUri = Uri.withAppendedPath(builtUri1, params[0]).buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return e.getMessage();
        }

        String jsonResp = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) jsonResp = scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        return jsonResp;
    }

    @Override
    protected void onPostExecute(String result) {
        List<User> users = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONArray uArray = new JSONArray(result);
//                JSONArray rpArray = reader.getJSONArray("rpList");

                for (int i = 0; i < uArray.length(); i++) {
                    ArrayList<Role> roles = new ArrayList<>();
                    JSONObject rpJson = uArray.getJSONObject(i);
                    String id = rpJson.getString("id");
                    JSONArray rlJson = (JSONArray) rpJson.get("roles");
                    for (int j = 0; j < rlJson.length();j++){
                        JSONObject rolesJson = rlJson.getJSONObject(j);
                        String role = (String) rolesJson.get("role");
//                        String accessPrivilege = rolesJson.getString("accessPrivilege");
                        roles.add(new Role(role));
                    }
                    String name = rpJson.getString("name");
                    String password = rpJson.getString("password");

                    users.add(new User(id,password,name,roles));
                }
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if (userController != null)
            userController.usersRetrieved(users);
//        else if (reviewSelectProgramController != null)
//            reviewSelectProgramController.programsRetrieved(users);
    }
}
