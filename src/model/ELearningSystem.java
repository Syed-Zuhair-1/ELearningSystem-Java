package model;

/**
 * The "manager" class of the platform. It stores every object in arrays and
 * contains the main operations (register, enroll, quiz, certificate...).
 */
public class ELearningSystem {

    private static final int MAX_USERS = 50;
    private static final int MAX_COURSES = 20;
    private static final int MAX_ENROLLMENTS = 200;
    private static final String TODAY = "22-11-2026";

    private User[] users = new User[MAX_USERS];   // holds Student, Instructor and Admin objects
    private int userCount = 0;
    private Student[] students = new Student[MAX_USERS];
    private int studentCount = 0;
    private Instructor[] instructors = new Instructor[MAX_USERS];
    private int instructorCount = 0;
    private Course[] courses = new Course[MAX_COURSES];
    private int courseCount = 0;
    private Enrollment[] enrollments = new Enrollment[MAX_ENROLLMENTS];
    private int enrollmentCount = 0;

    private int nextStudentId = 1;
    private int nextCourseId = 1;
    private int nextEnrollmentId = 1;
    private int nextCertificateId = 1001;

    // ---------------------------------------------------------------- getters
    public User[] getUsers() {
        return users;
    }

    public int getUserCount() {
        return userCount;
    }

    public Course[] getCourses() {
        return courses;
    }

    public int getCourseCount() {
        return courseCount;
    }

    // ------------------------------------------------------------ sample data
    public void loadSampleData() {
        Instructor ahmed = new Instructor(101, "Dr. Ahmed Khan", "ahmed@learnhub.pk", "Programming", 12);
        Instructor fatima = new Instructor(102, "Ms. Fatima Noor", "fatima@learnhub.pk", "Design", 5);
        Admin admin = new Admin(901, "Bilal Hussain", "admin@learnhub.pk", "Operations", 3);
        addInstructor(ahmed);
        addInstructor(fatima);
        users[userCount] = admin;
        userCount++;

        registerStudent("Ali Raza", "ali@mail.com");
        registerStudent("Sara Malik", "sara@mail.com");

        // Course 1 - uses BOTH versions of the overloaded addLesson()
        Course java = addCourse("Java Programming Basics", 5000.0, 30, ahmed);
        java.addLesson(new Lesson(1, "Introduction to Java", 20));
        java.addLesson("Variables and Data Types", 25);
        java.addLesson("Decisions and Loops", 30);
        java.addLesson("Classes and Objects", 35);
        Quiz javaQuiz = new Quiz(1, "Java Basics Quiz", 60);
        javaQuiz.addQuestion(new Question("Which keyword creates a child class?",
                new String[] { "implements", "extends", "super", "new" }, 2));
        javaQuiz.addQuestion(new Question("Which data type stores decimal numbers?",
                new String[] { "int", "boolean", "double", "char" }, 3));
        javaQuiz.addQuestion(new Question("What is the entry point of a Java program?",
                new String[] { "start()", "run()", "main()", "init()" }, 3));
        java.setQuiz(javaQuiz);

        Course web = addCourse("Web Design with HTML and CSS", 4000.0, 25, fatima);
        web.addLesson("HTML Structure", 20);
        web.addLesson("Styling with CSS", 25);
        web.addLesson("Layouts and Flexbox", 30);
        Quiz webQuiz = new Quiz(2, "Web Design Quiz");
        webQuiz.addQuestion(new Question("Which tag creates a hyperlink?",
                new String[] { "<a>", "<p>", "<img>", "<div>" }, 1));
        webQuiz.addQuestion(new Question("Which language styles a web page?",
                new String[] { "Java", "CSS", "SQL", "C" }, 2));
        webQuiz.addQuestion(new Question("Which tag holds the biggest heading?",
                new String[] { "<h6>", "<head>", "<h1>", "<title>" }, 3));
        web.setQuiz(webQuiz);

        Course dsa = addCourse("Data Structures with Arrays", 6500.0, 20, ahmed);
        dsa.addLesson("What is an Array?", 20);
        dsa.addLesson("Searching in Arrays", 30);
        dsa.addLesson("Sorting Basics", 35);
        Quiz dsaQuiz = new Quiz(3, "Arrays Quiz", 50);
        dsaQuiz.addQuestion(new Question("Array indexes start from?",
                new String[] { "0", "1", "-1", "10" }, 1));
        dsaQuiz.addQuestion(new Question("Which loop is common for walking an array?",
                new String[] { "if", "switch", "for", "case" }, 3));
        dsaQuiz.addQuestion(new Question("Array length is read using?",
                new String[] { ".size()", ".length", ".count", ".max" }, 2));
        dsa.setQuiz(dsaQuiz);

        // Course with only 1 seat, used to demonstrate the "course is full" check
        Course ai = addCourse("Intro to Artificial Intelligence", 8000.0, 1, fatima);
        ai.addLesson("What is AI?", 20);
        ai.addLesson("Machine Learning Basics", 40);
        Quiz aiQuiz = new Quiz(4, "AI Quiz", 50);
        aiQuiz.addQuestion(new Question("AI stands for?",
                new String[] { "Auto Input", "Artificial Intelligence", "Active Index", "Array Item" }, 2));
        aiQuiz.addQuestion(new Question("Machine learning learns from?",
                new String[] { "Data", "Paper", "Printers", "Cables" }, 1));
        aiQuiz.addQuestion(new Question("Which is an AI application?",
                new String[] { "Calculator", "Chatbot", "Ruler", "Stapler" }, 2));
        ai.setQuiz(aiQuiz);
    }

    // ------------------------------------------------------------ registration
    // Method overloading 1: default level
    public Student registerStudent(String name, String email) {
        Student s = new Student(nextStudentId, name, email);
        nextStudentId++;
        storeStudent(s);
        return s;
    }

    // Method overloading 2: chosen level
    public Student registerStudent(String name, String email, String level) {
        Student s = new Student(nextStudentId, name, email, level);
        nextStudentId++;
        storeStudent(s);
        return s;
    }

    private void storeStudent(Student s) {
        students[studentCount] = s;
        studentCount++;
        users[userCount] = s;
        userCount++;
    }

    private void addInstructor(Instructor i) {
        instructors[instructorCount] = i;
        instructorCount++;
        users[userCount] = i;
        userCount++;
    }

    public Course addCourse(String title, double price, int capacity, Instructor instructor) {
        Course c = new Course(nextCourseId, title, price, capacity, instructor);
        nextCourseId++;
        courses[courseCount] = c;
        courseCount++;
        instructor.recordCourseCreated();
        return c;
    }

    // ----------------------------------------------------------------- finders
    public Student findStudent(int id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getUserId() == id) {
                return students[i];
            }
        }
        return null;
    }

    public Instructor findInstructor(int id) {
        for (int i = 0; i < instructorCount; i++) {
            if (instructors[i].getUserId() == id) {
                return instructors[i];
            }
        }
        return null;
    }

    public Course findCourse(int id) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseId() == id) {
                return courses[i];
            }
        }
        return null;
    }

    public Enrollment findEnrollment(int studentId, int courseId) {
        for (int i = 0; i < enrollmentCount; i++) {
            if (enrollments[i].getStudent().getUserId() == studentId
                    && enrollments[i].getCourse().getCourseId() == courseId) {
                return enrollments[i];
            }
        }
        return null;
    }

    // ----------------------------------------------------------------- display
    public void listCourses() {
        System.out.println("---- Available Courses ----");
        for (int i = 0; i < courseCount; i++) {
            courses[i].showDetails();
        }
        System.out.println("Courses with open seats: " + countAvailableCourses(courses, courseCount)
                + " of " + courseCount);
    }

    // An ARRAY is passed to this method
    public int countAvailableCourses(Course[] list, int count) {
        int available = 0;
        for (int i = 0; i < count; i++) {
            if (list[i].hasSeat()) {
                available++;
            }
        }
        return available;
    }

    // RUNTIME POLYMORPHISM: users[i] is a User reference, but the object is a
    // Student, Instructor or Admin, so each one prints its own dashboard.
    public void showAllDashboards(User[] list, int count) {
        for (int i = 0; i < count; i++) {
            list[i].showDashboard();
        }
    }

    public void showStudentProgress(Student student) {
        int found = 0;
        for (int i = 0; i < enrollmentCount; i++) {
            if (enrollments[i].getStudent().getUserId() == student.getUserId()) {
                enrollments[i].showSummary();
                found++;
            }
        }
        if (found == 0) {
            System.out.println("This student is not enrolled in any course yet.");
        }
    }

    // ------------------------------------------------------------- enrollment
    /**
     * Enrolls a student and takes payment. The parameter type is the INTERFACE
     * Payable, so it works with CardPayment, MobilePayment or any future method.
     */
    public Enrollment enrollStudent(Student student, Course course, Payable payment) {
        if (findEnrollment(student.getUserId(), course.getCourseId()) != null) {
            System.out.println("Enrollment failed: " + student.getName() + " is already enrolled.");
            return null;
        }
        if (!course.hasSeat()) {
            System.out.println("Enrollment failed: this course is full or has no lessons.");
            return null;
        }

        // Arithmetic operators: discount, payment fee, total
        double basePrice = course.getPrice();
        double discount = basePrice * student.getDiscountRate();
        double afterDiscount = basePrice - discount;
        double total = payment.calculateAmount(afterDiscount);

        System.out.println("---- Payment Summary ----");
        System.out.println("Course price      : Rs. " + basePrice);
        System.out.println("Loyalty discount  : Rs. " + discount);
        System.out.println("Payment method    : " + payment.getPaymentMethod());
        System.out.println("Total to pay      : Rs. " + total);

        if (!payment.processPayment(total)) {
            System.out.println("Enrollment cancelled because the payment failed.");
            return null;
        }

        Enrollment e = new Enrollment(nextEnrollmentId, student, course, total);
        nextEnrollmentId++;
        enrollments[enrollmentCount] = e;
        enrollmentCount++;
        course.addEnrollment();
        student.registerForCourse();
        System.out.println("Payment successful! " + student.getName() + " is now enrolled in \""
                + course.getTitle() + "\".");
        return e;
    }

    // ------------------------------------------------------------ certificate
    public Certificate issueCertificate(Enrollment e) {
        if (e.isCertificateIssued()) {
            System.out.println("A certificate was already issued for this course.");
            return null;
        }
        // Relational + logical operators
        if (e.getProgressPercent() == 100 && e.isQuizTaken() && e.isQuizPassed()) {
            Certificate cert = new Certificate(nextCertificateId, e.getStudent().getName(),
                    e.getCourse().getTitle(), TODAY, e.getQuizPercent());
            nextCertificateId++;
            e.setCertificateIssued(true);
            e.getStudent().markCourseCompleted();
            return cert;
        }
        System.out.println("Certificate not available yet. Requirements: 100% lessons completed "
                + "AND quiz passed.");
        System.out.println("Your status -> Progress: " + e.getProgressPercent() + "%, Quiz passed: "
                + e.isQuizPassed());
        return null;
    }
}
