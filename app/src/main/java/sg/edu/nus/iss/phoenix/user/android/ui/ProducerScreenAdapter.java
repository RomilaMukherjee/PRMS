package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by Ragu on 26/9/2018.
 */

public class ProducerScreenAdapter extends ArrayAdapter<User>{

    private static final String TAG = ProducerScreenAdapter.class.getName();

    public ProducerScreenAdapter(Context context, ArrayList<User> producerList) {
        super(context, 0, producerList);

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_producer_item, parent, false);
        }

        User producer = getItem(position);
        Log.v(TAG, "annual schedule" + producer.getName());
        TextView annualYear = (TextView) listItemView.findViewById(R.id.producer_item);
        annualYear.setText(producer.getName()+"");

        return listItemView;
    }
}
