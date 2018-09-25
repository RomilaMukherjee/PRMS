package sg.edu.nus.iss.phoenix.user.android.entity;

import java.util.ArrayList;

public class User {
    private String id;
    private String password;
    private String name;
    private ArrayList<Role> roles = new ArrayList<Role>();
}
