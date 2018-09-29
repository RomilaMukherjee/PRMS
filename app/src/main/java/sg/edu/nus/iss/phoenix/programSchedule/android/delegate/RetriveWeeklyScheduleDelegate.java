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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import sg.edu.nus.iss.phoenix.programSchedule.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.WeeklySchedule;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_PROGRAM_SCHEDULE;

/**
 * Created by Ragu on 25/9/2018.
 */

public class RetriveWeeklyScheduleDelegate extends AsyncTask<Integer, Void, String> {

    private MaintainScheduleController maintainScheduleController = null;
    public RetriveWeeklyScheduleDelegate(MaintainScheduleController maintainScheduleController) {
        this.maintainScheduleController = maintainScheduleController;
    }

    private static final String TAG = RetriveAnnualScheduleDelegate.class.getName();
    @Override
    protected String doInBackground(Integer... params) {
        Uri builtUri = Uri.parse( PRMS_BASE_URL_PROGRAM_SCHEDULE).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, "all_weeklySchedule").buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, String.valueOf(params[0])).buildUpon().build();
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
        List<WeeklySchedule> weeklySchedules = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONObject reader = new JSONObject(result);
                Log.v(TAG, reader.toString());
                JSONArray rpArray = reader.getJSONArray("weeklySchedules");

                for (int i = 0; i < rpArray.length(); i++) {
                    JSONObject asJson = rpArray.getJSONObject(i);
                    //String description = asJson.getString("description");
                    String startDate = asJson.getString("startDate");
                    String assignedBy = asJson.getString("assignedBy");
                    int year = asJson.getInt("year");
                    Date startDateObj = null;
                    try {
                        startDateObj = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(startDateObj != null)
                    weeklySchedules.add(new WeeklySchedule(startDateObj, assignedBy, year));
                }
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if(maintainScheduleController != null) {
            maintainScheduleController.weeklySchedulesRetrived(weeklySchedules);
        }
    }
}
