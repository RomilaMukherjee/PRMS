package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;

public class RetrieveRoleDelegate extends AsyncTask<String, Void, String> {

    // Tag for logging
    private static final String TAG = RetrieveRoleDelegate.class.getName();

    @Override
    protected String doInBackground(String... params) {

        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_USER).buildUpon().build();

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
        }

        finally {
            if (urlConnection != null) urlConnection.disconnect();
        }
        Log.d(TAG,urlConnection.toString());
        Log.d(TAG,jsonResp);

        return jsonResp;
    }





}
