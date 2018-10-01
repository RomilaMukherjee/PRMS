package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    UserListScreen.DeleteClickListener mDeleteClickListener;
    public UserAdapter(@NonNull Context context, ArrayList<User> users, UserListScreen.DeleteClickListener deleteClickListener) {
        super(context, 0,users);
        this.context = context;
        mDeleteClickListener = deleteClickListener;
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
        Button delete = (Button) listItemView.findViewById(R.id.deleteuser_btn);
        Button update = (Button) listItemView.findViewById(R.id.update_user);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteClickListener.onDelete(currentUser, parent.getContext());
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext().getApplicationContext(), CreateUserScreen.class);
                intent.putExtra("selectedUserForEdit", currentUser);
                //intent.putExtra("editObject", )
                context.getApplicationContext().startActivity(intent);
            }
        });


        userPosition.setText(currentUser.getName());
        userName.setText(currentUser.getId());

        return listItemView;
    }
}
