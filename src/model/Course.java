package model;

/**
 * A course offered on the platform.
 * COMPOSITION: a Course has an Instructor, an array of Lesson objects and a Quiz.
 */
public class Course {

    public static final int MAX_LESSONS = 10;

    private int courseId;
    private String title;
    private double price;
    private int capacity;
    private int enrolledCount;

    private Instructor instructor;   // composition (reference to another class)
    private Lesson[] lessons;        // array of objects
    private int lessonCount;
    private Quiz quiz;               // composition

    // Constructor 1
    public Course(int courseId, String title, double price, int capacity, Instructor instructor) {
        this.courseId = courseId;
        this.title = title;
        this.price = price;
        this.capacity = capacity;
        this.instructor = instructor;
        this.enrolledCount = 0;
        this.lessons = new Lesson[MAX_LESSONS];
        this.lessonCount = 0;
        this.quiz = null;
    }

    // Constructor 2 (overloaded): default capacity of 30 seats
    public Course(int courseId, String title, double price, Instructor instructor) {
        this(courseId, title, price, 30, instructor);
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Lesson getLesson(int index) {
        if (index >= 0 && index < lessonCount) {
            return lessons[index];
        }
        return null;
    }

    // Method overloading 1: add a ready-made Lesson object
    public boolean addLesson(Lesson lesson) {
        if (lessonCount < MAX_LESSONS) {
            lessons[lessonCount] = lesson;
            lessonCount++;
            return true;
        }
        return false;
    }

    // Method overloading 2: same name, different parameters (creates the Lesson itself)
    public boolean addLesson(String lessonTitle, int minutes) {
        Lesson lesson = new Lesson(lessonCount + 1, lessonTitle, minutes);
        return addLesson(lesson);
    }

    // Relational + logical operators
    public boolean hasSeat() {
        return enrolledCount < capacity && lessonCount > 0;
    }

    public int getSeatsLeft() {
        return capacity - enrolledCount;
    }

    // Operation from the class diagram
    public void addEnrollment() {
        enrolledCount++;
    }

    public int getTotalDuration() {
        int total = 0;
        for (int i = 0; i < lessonCount; i++) {
            total += lessons[i].getDurationMinutes();
        }
        return total;
    }

    public void showDetails() {
        System.out.println("ID " + courseId + " | " + title + " | Instructor: " + instructor.getName()
                + " | Price: Rs. " + price + " | Seats left: " + getSeatsLeft()
                + " | Lessons: " + lessonCount + " (" + getTotalDuration() + " min)");
    }
}
