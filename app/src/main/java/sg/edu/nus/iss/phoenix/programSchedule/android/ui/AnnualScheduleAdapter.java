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

/**
 * Created by Ragu on 24/9/2018.
 */

public class AnnualScheduleAdapter extends ArrayAdapter<AnnualSchedule> {
    private static final String TAG = RetriveAnnualScheduleDelegate.class.getName();

    public AnnualScheduleAdapter(@NonNull Context context, ArrayList<AnnualSchedule> annualSchedules) {
        super(context, 0, annualSchedules);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_annual_schedule, parent, false);
        }

        AnnualSchedule annualSchedule = getItem(position);
        Log.v(TAG, "annual schedule" + annualSchedule.getYear());
        TextView annualYear = (TextView) listItemView.findViewById(R.id.annula_schedule_year_listview);
        annualYear.setText(annualSchedule.getYear()+"");

        return listItemView;
    }
}
