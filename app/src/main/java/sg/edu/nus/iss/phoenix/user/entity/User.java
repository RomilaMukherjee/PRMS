package sg.edu.nus.iss.phoenix.user.entity;

import java.util.ArrayList;

public class User {
    private String id;
    private String password;
    private String name;
    private ArrayList<Role> roles = new ArrayList<Role>();

    public User(String id, String password, String name, ArrayList<Role> roles) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
}
