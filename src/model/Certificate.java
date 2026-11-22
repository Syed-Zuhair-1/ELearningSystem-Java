package model;

/**
 * Certificate awarded when a student finishes a course and passes the quiz.
 */
public class Certificate {

    private int certificateId;
    private String studentName;
    private String courseTitle;
    private String issueDate;
    private char grade;

    // Constructor 1
    public Certificate(int certificateId, String studentName, String courseTitle,
            String issueDate, char grade) {
        this.certificateId = certificateId;
        this.studentName = studentName;
        this.courseTitle = courseTitle;
        this.issueDate = issueDate;
        this.grade = grade;
    }

    // Constructor 2 (overloaded): grade worked out from the quiz percentage
    public Certificate(int certificateId, String studentName, String courseTitle,
            String issueDate, int quizPercent) {
        this(certificateId, studentName, courseTitle, issueDate, gradeFromPercent(quizPercent));
    }

    private static char gradeFromPercent(int percent) {
        if (percent >= 85) {
            return 'A';
        } else if (percent >= 70) {
            return 'B';
        } else {
            return 'C';
        }
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public void printCertificate() {
        System.out.println("==========================================");
        System.out.println("        CERTIFICATE OF COMPLETION");
        System.out.println("==========================================");
        System.out.println(" Certificate No : CERT-" + certificateId);
        System.out.println(" Awarded to     : " + studentName);
        System.out.println(" Course         : " + courseTitle);
        System.out.println(" Grade          : " + grade);
        System.out.println(" Date of issue  : " + issueDate);
        System.out.println("==========================================");
    }
}
