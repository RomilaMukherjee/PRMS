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
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.radioprogram.android.delegate.RetrieveProgramsDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

import static android.content.ContentValues.TAG;
import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_PROGRAM_SCHEDULE;

/**
 * Created by Ragu on 24/9/2018.
 */

public class RetriveAnnualScheduleDelegate extends AsyncTask<String, Void, String>{
    private MaintainScheduleController maintainScheduleController = null;
    private static final String TAG = RetriveAnnualScheduleDelegate.class.getName();

    public RetriveAnnualScheduleDelegate(MaintainScheduleController maintainScheduleController) {
        this.maintainScheduleController = maintainScheduleController;
    }

    @Override
    protected String doInBackground(String... params) {
        Uri builtUri1 = Uri.parse( PRMS_BASE_URL_PROGRAM_SCHEDULE).buildUpon().build();
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
        List<AnnualSchedule> annualSchedules = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONObject reader = new JSONObject(result);
                Log.v(TAG, reader.toString());
                JSONArray rpArray = reader.getJSONArray("ASList");

                for (int i = 0; i < rpArray.length(); i++) {
                    JSONObject asJson = rpArray.getJSONObject(i);
                    //String description = asJson.getString("description");
                    int year = asJson.getInt("year");
                    String assignedBy = asJson.getString("assignedBy");

                    annualSchedules.add(new AnnualSchedule(year, assignedBy));
                }
                Log.v(TAG, "Json response :" + annualSchedules.get(0).getYear());
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if (maintainScheduleController != null)
            maintainScheduleController.programsRetrieved(annualSchedules);
    }
}
