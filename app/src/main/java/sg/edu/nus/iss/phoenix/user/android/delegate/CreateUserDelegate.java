package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;


import sg.edu.nus.iss.phoenix.user.entity.User;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;

public class CreateUserDelegate extends AsyncTask<User, Void, Boolean> {

    private static final String TAG = CreateUserDelegate.class.getName();

    @Override
    protected Boolean doInBackground(User... users) {
        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_USER).buildUpon().build();

        Uri builtUri = Uri.withAppendedPath(builtUri1, "create").buildUpon().build();

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
            json.put("name", users[0].getName());
            json.put("id", users[0].getId());
            json.put("password", users[0].getPassword());
            json.put("roles", users[0].getRoles());
        } catch (JSONException e) {
            Log.v(TAG, e.getMessage());
        }


        return false;
    }
}
