package model;

/**
 * Abstract parent class for every person who uses the E-Learning System.
 * Child classes (Student, Instructor, Admin) must override the abstract methods.
 */
public abstract class User {

    private int userId;
    private String name;
    private String email;
    private boolean active;

    // Constructor 1
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.active = true;
    }

    // Constructor 2 (overloaded)
    public User(int userId, String name, String email, boolean active) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Abstract methods: every child class MUST provide its own version.
    public abstract String getRole();

    public abstract void showDashboard();

    // Concrete method shared by all children.
    public String getProfileSummary() {
        String status = "";
        if (!active) {
            status = " (inactive)";
        }
        return "[" + getRole() + " #" + userId + "] " + name + " <" + email + ">" + status;
    }
}
