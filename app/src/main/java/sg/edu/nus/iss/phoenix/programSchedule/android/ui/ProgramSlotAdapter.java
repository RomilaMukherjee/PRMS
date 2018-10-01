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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.programSchedule.android.delegate.RetrieveProgramSlotDelegate;
import sg.edu.nus.iss.phoenix.programSchedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programSchedule.entity.ProgramSlot;

/**
 * Created by Romila on 24/9/2018.
 */

public class ProgramSlotAdapter extends ArrayAdapter<ProgramSlot> {
    private static final String TAG = ProgramSlotAdapter.class.getName();

    public ProgramSlotAdapter(@NonNull Context context, ArrayList<ProgramSlot> programSlots) {
        super(context, 0, programSlots);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_program_slot_list_details, parent, false);
        }

        ProgramSlot programSlot = getItem(position);
        Log.v(TAG, "program slot" + programSlot.getProgramName());
        TextView programName = (TextView) listItemView.findViewById(R.id.slot_programname_listview);
        programName.setText(programSlot.getProgramName()+"");

        TextView progWeekDate = (TextView) listItemView.findViewById(R.id.slot_week_date);
        if(programSlot.getWeekStartDate()!=null) {
            String weekdate = new SimpleDateFormat("yyyy-MM-dd").format(programSlot.getWeekStartDate());
            Log.d(TAG,"week date::"+weekdate);
            progWeekDate.setText(weekdate);
        }

        return listItemView;
    }
}
