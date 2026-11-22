package model;

/**
 * A teacher who creates courses. Child of User (hierarchical inheritance).
 */
public class Instructor extends User {

    private String subject;
    private int experienceYears;
    private int coursesCreated;

    // Constructor 1
    public Instructor(int userId, String name, String email, String subject, int experienceYears) {
        super(userId, name, email);
        this.subject = subject;
        this.experienceYears = experienceYears;
        this.coursesCreated = 0;
    }

    // Constructor 2 (overloaded): new instructor with no experience entered
    public Instructor(int userId, String name, String email, String subject) {
        this(userId, name, email, subject, 0);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int getCoursesCreated() {
        return coursesCreated;
    }

    public void setCoursesCreated(int coursesCreated) {
        this.coursesCreated = coursesCreated;
    }

    // Operation from the class diagram
    public void recordCourseCreated() {
        coursesCreated++;
    }

    // Extra method of this child class
    public String getSeniorityLabel() {
        if (experienceYears >= 10) {
            return "Senior";
        } else if (experienceYears >= 4) {
            return "Experienced";
        } else {
            return "Junior";
        }
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public void showDashboard() {
        System.out.println(super.getProfileSummary());
        System.out.println("   Subject: " + subject + " | " + getSeniorityLabel() + " ("
                + experienceYears + " yrs) | Courses created: " + coursesCreated);
    }
}
