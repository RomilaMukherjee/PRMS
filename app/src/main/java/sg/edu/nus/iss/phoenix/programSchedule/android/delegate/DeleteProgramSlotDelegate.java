package sg.edu.nus.iss.phoenix.programSchedule.android.delegate;

import android.arch.persistence.room.Delete;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import sg.edu.nus.iss.phoenix.programSchedule.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.radioprogram.android.controller.ProgramController;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_RADIO_PROGRAM;

public class DeleteProgramSlotDelegate extends AsyncTask<ProgramSlot, Void, Boolean> {
    // Tag for logging
    private static final String TAG = DeleteProgramSlotDelegate.class.getName();
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    private final MaintainScheduleController maintainScheduleController;

    public DeleteProgramSlotDelegate(MaintainScheduleController maintainScheduleController) {
        this.maintainScheduleController = maintainScheduleController;
    }

    @Override
    protected Boolean doInBackground(ProgramSlot... programSlots) {
        // Encode the name of radio program in case of the presence of special characters.

        Uri builtUri = Uri.parse(PRMS_BASE_URL_RADIO_PROGRAM).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri,"delete").buildUpon().build();
       // builtUri = Uri.withAppendedPath(builtUri, name).buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return new Boolean(false);
        }

        JSONObject json = new JSONObject();
        try {
            json.put("dateofProgram", sdformat.format(programSlots[0].getDateOfProgram()));
            json.put("startTime", sdformat.format(programSlots[0].getStartTime()));
        } catch (JSONException e) {
            Log.v(TAG, e.getMessage());
        }
        boolean success = false;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            httpURLConnection.setUseCaches (false);
            System.out.println(httpURLConnection.getResponseCode());
            Log.v(TAG, "Http DELETE response " + httpURLConnection.getResponseCode());
            success = true;
        } catch (IOException exception) {
            Log.v(TAG, exception.getMessage());
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
        return new Boolean(success);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        maintainScheduleController.programSlotDeleted(result.booleanValue());
    }
}
