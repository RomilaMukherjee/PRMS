package sg.edu.nus.iss.phoenix.programSchedule.android.ui;

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
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.RetriveAnnualScheduleDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.WeeklySchedule;

/**
 * Created by Ragu on 29/9/2018.
 */

public class WeeklyScheduleAdapter extends ArrayAdapter<WeeklySchedule> {

    private static final String TAG = RetriveAnnualScheduleDelegate.class.getName();
    public WeeklyScheduleAdapter(@NonNull Context context, ArrayList<WeeklySchedule> weeklySchedules) {
        super(context, 0, weeklySchedules);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_weekly_schedule_item, parent, false);
        }

        WeeklySchedule weeklySchedule = getItem(position);
        Log.v(TAG, "weekly schedule" + weeklySchedule.getStartDate());
        TextView weekStartDate = (TextView) listItemView.findViewById(R.id.week_startdate);
        weekStartDate.setText(weeklySchedule.getStartDate()+"");

        return listItemView;
    }
}
