package model;

/**
 * A system administrator. Child of User (hierarchical inheritance).
 */
public class Admin extends User {

    private String department;
    private int accessLevel;
    private int coursesApproved;

    // Constructor 1
    public Admin(int userId, String name, String email, String department, int accessLevel) {
        super(userId, name, email);
        this.department = department;
        this.accessLevel = accessLevel;
        this.coursesApproved = 0;
    }

    // Constructor 2 (overloaded): basic admin with access level 1
    public Admin(int userId, String name, String email, String department) {
        this(userId, name, email, department, 1);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getCoursesApproved() {
        return coursesApproved;
    }

    public void setCoursesApproved(int coursesApproved) {
        this.coursesApproved = coursesApproved;
    }

    // Relational + logical operators: only active admins with level 2+ may approve
    public boolean canApproveCourses() {
        return accessLevel >= 2 && isActive();
    }

    // Operation from the class diagram
    public boolean approveCourse(Course course) {
        if (canApproveCourses() && course != null) {
            coursesApproved++;
            return true;
        }
        return false;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public void showDashboard() {
        System.out.println(super.getProfileSummary());
        System.out.println("   Department: " + department + " | Access level: " + accessLevel
                + " | Courses approved: " + coursesApproved);
    }
}
