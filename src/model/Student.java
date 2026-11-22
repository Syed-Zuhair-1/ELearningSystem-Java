package model;

/**
 * A learner who enrolls in courses. Child of User (hierarchical inheritance).
 */
public class Student extends User {

    private int coursesEnrolled;
    private int coursesCompleted;
    private String learningLevel;

    // Constructor 1: new student with default values
    public Student(int userId, String name, String email) {
        super(userId, name, email);
        this.coursesEnrolled = 0;
        this.coursesCompleted = 0;
        this.learningLevel = "Beginner";
    }

    // Constructor 2 (overloaded): student with a chosen starting level
    public Student(int userId, String name, String email, String learningLevel) {
        super(userId, name, email);
        this.coursesEnrolled = 0;
        this.coursesCompleted = 0;
        this.learningLevel = learningLevel;
    }

    public int getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(int coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public int getCoursesCompleted() {
        return coursesCompleted;
    }

    public void setCoursesCompleted(int coursesCompleted) {
        this.coursesCompleted = coursesCompleted;
    }

    public String getLearningLevel() {
        return learningLevel;
    }

    public void setLearningLevel(String learningLevel) {
        this.learningLevel = learningLevel;
    }

    // Operation from the class diagram: called after a successful enrollment
    public void registerForCourse() {
        coursesEnrolled++;
        updateLevel();
    }

    // Operation from the class diagram: called after a certificate is issued
    public void markCourseCompleted() {
        coursesCompleted++;
    }

    // Loyalty discount: 10% once the student is enrolled in 2 or more courses
    public double getDiscountRate() {
        if (coursesEnrolled >= 2 && isActive()) {
            return 0.10;
        }
        return 0.0;
    }

    private void updateLevel() {
        if (coursesEnrolled >= 4) {
            learningLevel = "Advanced";
        } else if (coursesEnrolled >= 2) {
            learningLevel = "Intermediate";
        }
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public void showDashboard() {
        System.out.println(super.getProfileSummary());
        System.out.println("   Level: " + learningLevel + " | Enrolled: " + coursesEnrolled
                + " | Completed: " + coursesCompleted);
    }
}
