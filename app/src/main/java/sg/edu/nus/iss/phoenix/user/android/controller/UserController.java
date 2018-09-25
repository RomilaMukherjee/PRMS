package sg.edu.nus.iss.phoenix.user.android.controller;


import android.content.Intent;

import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.user.android.ui.MaintainUserScreen;
import sg.edu.nus.iss.phoenix.user.android.ui.UserListScreen;

public class UserController {
    // Tag for logging.
    private static final String TAG = UserController.class.getName();
    private MaintainUserScreen maintainUserScreen;

    public void startUseCase() {
        Intent intent = new Intent(MainController.getApp(), UserListScreen.class);
        MainController.displayScreen(intent);
    }
}
