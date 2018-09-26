package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(@NonNull Context context, ArrayList<User> users) {
        super(context, 0,users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) listItemView = LayoutInflater.from(getContext()).inflate(
                R.layout.item_user, parent, false);
        User currentUser = getItem(position);
        TextView userName = (TextView) listItemView.findViewById(R.id.username);
        TextView userPosition = (TextView) listItemView.findViewById(R.id.userposition);

        userPosition.setText(currentUser.getName());
        userName.setText(currentUser.getId());
        return listItemView;
    }
}
