package sg.edu.nus.iss.phoenix.programSchedule.android.delegate;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import sg.edu.nus.iss.phoenix.programSchedule.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.android.delegate.RetrieveUsersDelegate;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_PROGRAM_SCHEDULE;

public class CreateProgramSlotDelegate extends AsyncTask<ProgramSlot, Void, Boolean> {

private final MaintainScheduleController maintainScheduleController;

private static final String TAG = CreateProgramSlotDelegate.class.getName();
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

public CreateProgramSlotDelegate(MaintainScheduleController maintainScheduleController) {
        this.maintainScheduleController = maintainScheduleController;
        }

@Override
protected Boolean doInBackground(ProgramSlot... programSlot) {
        Uri builtUri = Uri.parse(PRMS_BASE_URL_PROGRAM_SCHEDULE).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri,"createProgramSlot").buildUpon().build();
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
        json.put("programName", programSlot[0].getProgramName());
        json.put("dateofProgram", sdformat.format(programSlot[0].getDateOfProgram()));
        json.put("duration", timeFormat.format(programSlot[0].getDuration()));
        json.put("startTime", sdformat.format(programSlot[0].getStartTime()));
        json.put("weekStartDate", sdformat.format(programSlot[0].getWeekStartDate()));
        json.put("producer", programSlot[0].getProducerName());
        json.put("presenter", programSlot[0].getPresenterName());
        } catch (JSONException e) {
        Log.v(TAG, e.getMessage());
        }

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
        dos.write(256);
        Log.v(TAG, "Http PUT response " + httpURLConnection.getResponseCode());
        Log.v("SendingData" , json.toString());
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

@Override
protected void onPostExecute(Boolean result) {

        if (result) {
              //  Toast.makeText(context, "Program Slot Created successfully", Toast.LENGTH_SHORT).show();
                maintainScheduleController.programSlotCreated(result.booleanValue());
        } else {
               // Toast.makeText(context, "Could not create Program Slot", Toast.LENGTH_SHORT).show();
        }
        }
        }

