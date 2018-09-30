package sg.edu.nus.iss.phoenix.user.entity;

public class Role {
    /**
     * For eclipse based unique identity
     */
    private static final long serialVersionUID = 6660587452178950544L;

    /**
     * Persistent Instance variables. This data is directly mapped to the
     * columns of database table.
     */
    private String role;
    private String accessPrivilege;

    public Role(String role) {
        this.role = role;
        this.accessPrivilege = null;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessPrivilege() {
        return accessPrivilege;
    }

    public void setAccessPrivilege(String accessPrivilege) {
        this.accessPrivilege = accessPrivilege;
    }
}
