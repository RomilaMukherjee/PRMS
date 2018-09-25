package sg.edu.nus.iss.phoenix.authenticate.android.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import sg.edu.nus.iss.phoenix.authenticate.android.delegate.LoginDelegate;
import sg.edu.nus.iss.phoenix.authenticate.android.ui.LoginScreen;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.ui.MainScreen;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;

public class LoginController {
    // Tag for logging.
    private static final String TAG = LoginController.class.getName();
    private SharedPreferences sharedPreferences;

    private LoginScreen loginScreen;

    public LoginController() {
    }

    public void onDisplay(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }

    public void login(String userName, String password) {
        loginScreen.showLoadingIndicator();
        new LoginDelegate(this).execute(userName, password);
    }

    public void loggedIn(boolean success, String username) {
        loginScreen.hideLoadingIndicator();
        if (!success) { loginScreen.showErrorMessage(); return; }
        sharedPreferences.edit().putString("username", username).commit();
        ControlFactory.getMainController().startUseCase(username);
    }

    public void getPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void logout() {
        Intent intent = new Intent(MainController.getApp(), LoginScreen.class);
        MainController.displayScreen(intent);
    }
}
