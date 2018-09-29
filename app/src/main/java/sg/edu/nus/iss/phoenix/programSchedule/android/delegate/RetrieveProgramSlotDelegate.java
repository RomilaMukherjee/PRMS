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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sg.edu.nus.iss.phoenix.programSchedule.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;
import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_PROGRAM_SCHEDULE;


/**
 * Created by Romila on 26/9/2018.
 */

public class RetrieveProgramSlotDelegate extends AsyncTask<String, Void, String> {
    private MaintainScheduleController maintainScheduleController = null;
    private static final String TAG = RetrieveProgramSlotDelegate.class.getName();

    public RetrieveProgramSlotDelegate(MaintainScheduleController maintainScheduleController) {
        this.maintainScheduleController = maintainScheduleController;
    }

    @Override
    protected String doInBackground(String... params) {
        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_PROGRAM_SCHEDULE).buildUpon().build();
        Uri builtUri = Uri.withAppendedPath(builtUri1, "all_programslots").buildUpon().build();
        //builtUri.withAppendedPath(builtUri, params[0]).buildUpon().build();

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
        List<ProgramSlot> programSlotList = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONObject reader = new JSONObject(result);
                Log.v(TAG, reader.toString());
                JSONArray psArray = reader.getJSONArray("slotList");

                for (int i = 0; i < psArray.length(); i++) {
                    JSONObject asJson = psArray.getJSONObject(i);
                    //String description = asJson.getString("description");
                    String startTime = asJson.getString("startTime");
                    String slotName = asJson.getString("programSlotName");
                    String duration = asJson.getString("duration");
                    String dateofProgram = asJson.getString("dateofProgram");

                    programSlotList.add(new ProgramSlot(slotName, dateofProgram, startTime, duration));
                }
                Log.v(TAG, "Json response :" + programSlotList.get(0).getDateOfProgram());
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if (maintainScheduleController != null) {
            programSlotList = new ArrayList<ProgramSlot>();
            ProgramSlot slot = new ProgramSlot("charity","2018-08-26 00:00:00","2018-08-26 12:00:00","00:30:00");
            programSlotList.add(slot);
            maintainScheduleController.programSlotRetrieved(programSlotList);
        }
    }
}
