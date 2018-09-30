package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import sg.edu.nus.iss.phoenix.user.android.controller.UserController;


import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_DELETE_USER;

public class DeleteUserDelegate extends AsyncTask<String, Boolean, Boolean> {

    private static final String TAG = DeleteUserDelegate.class.getName();
    private UserController userController = null;
    private Context context = null;

    public DeleteUserDelegate(UserController userController, Context context) {
        this.userController = userController;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_DELETE_USER).buildUpon().build();
        Uri builtUri = Uri.withAppendedPath(builtUri1, params[0]).buildUpon().build();
        Log.d("asd", "url = " + builtUri);
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return false;
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            urlConnection.setUseCaches (false);
            Log.v(TAG, "Http DELETE response " + urlConnection.getResponseCode());
            Log.d("asd", urlConnection.getRequestMethod());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }
        return true;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            Toast.makeText(context, "User deleted successfully", Toast.LENGTH_SHORT).show();
            new RetrieveUsersDelegate(userController).execute("all");

        } else {
            Toast.makeText(context, "Could not delete user", Toast.LENGTH_SHORT).show();
        }
    }
}
