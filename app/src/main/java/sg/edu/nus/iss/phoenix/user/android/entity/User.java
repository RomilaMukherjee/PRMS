package sg.edu.nus.iss.phoenix.user.android.entity;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.user.entity.Role;

public class User {
    private String id;
    private String password;
    private String name;
    private ArrayList<Role> roles = new ArrayList<Role>();

    public User() {}
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
