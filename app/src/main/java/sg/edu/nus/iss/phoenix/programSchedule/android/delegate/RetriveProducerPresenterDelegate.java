package sg.edu.nus.iss.phoenix.programSchedule.android.delegate;

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

import sg.edu.nus.iss.phoenix.programSchedule.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.user.entity.User;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;

/**
 * Created by Ragu on 26/9/2018.
 */

public class RetriveProducerPresenterDelegate extends AsyncTask<String, Void, String> {

    private MaintainScheduleController maintainScheduleController = null;
    private static final String TAG = RetriveProducerPresenterDelegate.class.getName();

    public RetriveProducerPresenterDelegate(MaintainScheduleController maintainScheduleController) {
        this.maintainScheduleController = maintainScheduleController;
    }

    @Override
    protected String doInBackground(String... params) {
        Uri builtUri = Uri.parse( PRMS_BASE_URL_USER).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, "allUserByRole").buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, params[0]).buildUpon().build();
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
        List<User> userList = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                //JSONObject reader = new JSONObject(result);
                Log.v(TAG, result.toString());
                //JSONArray rpArray = reader.getJSONArray("userList");
                JSONArray uArray = new JSONArray(result);
                for (int i = 0; i < uArray.length(); i++) {
                    //rpArray.getJSONObject(i).remove("roles");
                    JSONObject asJson = uArray.getJSONObject(i);
                    //String description = asJson.getString("description");
                    String userId = asJson.getString("id");
                    String userName = asJson.getString("name");

                    userList.add(new User(userId, userName));
                }
                Log.v(TAG, "Json response :" + userList.get(0).getName());
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }
        Log.v(TAG, "RetriveProducerPresenterDelegate :" + userList.get(0).getName());
        if (maintainScheduleController != null)
            Log.v(TAG, "inside if"+maintainScheduleController);
            maintainScheduleController.usersRetrieved(userList);
    }
}
