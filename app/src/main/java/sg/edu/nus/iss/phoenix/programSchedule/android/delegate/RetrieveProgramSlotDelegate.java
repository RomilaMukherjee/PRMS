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
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.radioprogram.android.delegate.RetrieveProgramsDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

import static android.content.ContentValues.TAG;
import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_PROGRAM_SCHEDULE;

/**
 * Created by Ragu on 24/9/2018.
 */

public class RetrieveProgramSlotDelegate extends AsyncTask<String, Void, String>{
    private MaintainScheduleController maintainScheduleController = null;
    private static final String TAG = RetrieveProgramSlotDelegate.class.getName();

    public RetrieveProgramSlotDelegate(MaintainScheduleController maintainScheduleController) {
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
        List<ProgramSlot> slots = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONObject reader = new JSONObject(result);
                Log.v(TAG, reader.toString());
                JSONArray psArray = reader.getJSONArray("slotList");

                for (int i = 0; i < psArray.length(); i++) {
                    JSONObject asJson = psArray.getJSONObject(i);
                    String duration = asJson.getString("duration");
                    String programName = asJson.getString("programSlotName");
                    String dateofProgram = asJson.getString("dateofProgram");
                    String startTime = asJson.getString("startTime");
                    String weekStartDate = asJson.getString("weekStartDate");
                    String presenter = asJson.getString("presenter");
                    String producer = asJson.getString("producer");
                    Date dateOfProgram = null;
                    Date weekDate= null;
                    Date starttime = null;
                    Date durationT= null;
                    try {
                        dateOfProgram = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateofProgram);
                        weekDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(weekStartDate);
                        starttime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startTime);
                        durationT= new SimpleDateFormat("hh:mm:ss").parse(duration);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    slots.add(new ProgramSlot(programName, dateOfProgram ,starttime,durationT,weekDate,
                            producer,presenter));
                }
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if (maintainScheduleController != null)
            maintainScheduleController.programSlotRetrieved(slots);
    }
}
