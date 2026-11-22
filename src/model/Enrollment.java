package model;

/**
 * Links one Student to one Course and tracks progress, quiz result and certificate.
 * COMPOSITION: an Enrollment holds references to a Student and a Course.
 */
public class Enrollment {

    private int enrollmentId;
    private Student student;
    private Course course;
    private double amountPaid;
    private boolean[] lessonsDone;
    private boolean quizTaken;
    private int quizPercent;
    private boolean quizPassed;
    private boolean certificateIssued;

    // Constructor 1
    public Enrollment(int enrollmentId, Student student, Course course, double amountPaid) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.amountPaid = amountPaid;
        this.lessonsDone = new boolean[Course.MAX_LESSONS];
        this.quizTaken = false;
        this.quizPercent = 0;
        this.quizPassed = false;
        this.certificateIssued = false;
    }

    // Constructor 2 (overloaded): free enrollment
    public Enrollment(int enrollmentId, Student student, Course course) {
        this(enrollmentId, student, course, 0.0);
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public boolean isLessonDone(int index) {
        return index >= 0 && index < Course.MAX_LESSONS && lessonsDone[index];
    }

    public boolean isQuizTaken() {
        return quizTaken;
    }

    public int getQuizPercent() {
        return quizPercent;
    }

    public boolean isQuizPassed() {
        return quizPassed;
    }

    public boolean isCertificateIssued() {
        return certificateIssued;
    }

    public void setCertificateIssued(boolean certificateIssued) {
        this.certificateIssued = certificateIssued;
    }

    // Method overloading 1: complete a lesson by its position (0-based index)
    public boolean completeLesson(int index) {
        if (index >= 0 && index < course.getLessonCount() && !lessonsDone[index]) {
            lessonsDone[index] = true;
            return true;
        }
        return false;
    }

    // Method overloading 2: complete a lesson by its title
    public boolean completeLesson(String lessonTitle) {
        for (int i = 0; i < course.getLessonCount(); i++) {
            if (course.getLesson(i).getTitle().equalsIgnoreCase(lessonTitle)) {
                return completeLesson(i);
            }
        }
        return false;
    }

    public int countLessonsDone() {
        int done = 0;
        for (int i = 0; i < course.getLessonCount(); i++) {
            if (lessonsDone[i]) {
                done++;
            }
        }
        return done;
    }

    // Type casting: (double) for decimal division, (int) to drop the decimals
    public int getProgressPercent() {
        int total = course.getLessonCount();
        if (total == 0) {
            return 0;
        }
        return (int) ((double) countLessonsDone() * 100 / total);
    }

    public void recordQuizResult(int percent, boolean passed) {
        this.quizTaken = true;
        this.quizPercent = percent;
        this.quizPassed = passed;
    }

    public void showSummary() {
        String quizText = "not taken";
        if (quizTaken) {
            quizText = quizPercent + "% (" + (quizPassed ? "PASSED" : "FAILED") + ")";
        }
        System.out.println(course.getTitle() + " | Progress: " + getProgressPercent() + "% ("
                + countLessonsDone() + "/" + course.getLessonCount() + " lessons) | Quiz: " + quizText
                + " | Paid: Rs. " + amountPaid + " | Certificate: " + (certificateIssued ? "Yes" : "No"));
    }
}
