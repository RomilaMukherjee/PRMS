package sg.edu.nus.iss.phoenix.user.android.controller;


import android.content.Context;
import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.user.android.delegate.CreateUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.DeleteUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.RetrieveUsersDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.UpdateUserDelegate;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.user.android.ui.UserListScreen;

public class UserController {
    // Tag for logging.
    private static final String TAG = UserController.class.getName();
    private UserListScreen userListScreen;
    private CreateUserDelegate createUserDelegate;

    public void startUseCase() {
        Intent intent = new Intent(MainController.getApp(), UserListScreen.class);
        MainController.displayScreen(intent);
    }
    public void onDisplayUserList(UserListScreen userListScreen) {
        this.userListScreen = userListScreen;
        new RetrieveUsersDelegate(this).execute("all");
    }

    public void usersRetrieved(List<User> users) {
        userListScreen.showUsers(users);
    }

    public void saveUser(User user){
        new CreateUserDelegate(this).execute(user);
    }

    public void updateUser(User user) {
        new UpdateUserDelegate(this).execute(user);
    }

    public void onDeleteUser(User user, Context context) {
        new DeleteUserDelegate(this, context).execute(user.getId());
    }
}
