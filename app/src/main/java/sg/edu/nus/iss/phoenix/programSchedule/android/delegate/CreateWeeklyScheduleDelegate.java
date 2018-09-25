package sg.edu.nus.iss.phoenix.programSchedule.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import sg.edu.nus.iss.phoenix.programSchedule.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.programSchedule.android.ui.CreateWeeklyScheduleActivity;
import sg.edu.nus.iss.phoenix.programSchedule.entity.WeeklySchedule;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_PROGRAM_SCHEDULE;

/**
 * Created by Ragu on 24/9/2018.
 */

public class CreateWeeklyScheduleDelegate extends AsyncTask<WeeklySchedule, Void, Boolean> {

    private final MaintainScheduleController maintainScheduleController;
    private static final String TAG = CreateWeeklyScheduleActivity.class.getName();
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public CreateWeeklyScheduleDelegate(MaintainScheduleController maintainScheduleController) {
        this.maintainScheduleController = maintainScheduleController;
    }

    @Override
    protected Boolean doInBackground(WeeklySchedule... weeklySchedules) {
        Uri builtUri = Uri.parse(PRMS_BASE_URL_PROGRAM_SCHEDULE).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri,"create_weeklyschedule").buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return new Boolean(false);
        }

        JSONObject json = new JSONObject();
        String startDate = sdformat.format(weeklySchedules[0].getStartDate());

        try {
            json.put("startDate", startDate);
            json.put("assignedBy", weeklySchedules[0].getAssignedBy());
        } catch (JSONException e) {
            Log.v(TAG, e.getMessage());
        }
        Log.v(TAG, "JSON :" + json.toString());
        boolean success = false;
        HttpURLConnection httpURLConnection = null;
        DataOutputStream dos = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.writeUTF(json.toString());
            dos.write(512);
            Log.v(TAG, "Http PUT response " + httpURLConnection.getResponseCode());
            success = true;
        } catch (IOException exception) {
            Log.v(TAG, exception.getMessage());
        } finally {
            if (dos != null) {
                try {
                    dos.flush();
                    dos.close();
                } catch (IOException exception) {
                    Log.v(TAG, exception.getMessage());
                }
            }
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
        return new Boolean(success);
    }
    // private final MaintainScheduleController maintainScheduleController;
    @Override
    protected void onPostExecute(Boolean result) {
        maintainScheduleController.annualScheduleCreated(result.booleanValue());
    }
}
