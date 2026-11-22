package main;

import java.util.Scanner;

import model.CardPayment;
import model.Certificate;
import model.Course;
import model.ELearningSystem;
import model.Enrollment;
import model.Instructor;
import model.MobilePayment;
import model.Payable;
import model.Question;
import model.Quiz;
import model.Student;

/**
 * Console program for the E-Learning System.
 * Run this class: it starts from public static void main(String[] args).
 */
public class Main {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ELearningSystem system = new ELearningSystem();
        system.loadSampleData();

        System.out.println("=== Welcome to LearnHub E-Learning System ===");
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
            case 1:
                registerStudent(system);
                break;
            case 2:
                system.listCourses();
                break;
            case 3:
                enrollInCourse(system);
                break;
            case 4:
                studyLesson(system);
                break;
            case 5:
                takeQuiz(system);
                break;
            case 6:
                viewProgress(system);
                break;
            case 7:
                getCertificate(system);
                break;
            case 8:
                addNewCourse(system);
                break;
            case 9:
                system.showAllDashboards(system.getUsers(), system.getUserCount());
                break;
            case 0:
                System.out.println("Thank you for using LearnHub. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number from 0 to 9.");
            }
        } while (choice != 0);
    }

    // ---------------------------------------------------------------- menu
    private static void printMenu() {
        System.out.println();
        System.out.println("------------- MAIN MENU -------------");
        System.out.println("1. Register a new student");
        System.out.println("2. View available courses");
        System.out.println("3. Enroll in a course (with payment)");
        System.out.println("4. Study a lesson");
        System.out.println("5. Take a course quiz");
        System.out.println("6. View my progress");
        System.out.println("7. Get my certificate");
        System.out.println("8. Add a new course (instructor)");
        System.out.println("9. Show all user dashboards");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------");
    }

    // ------------------------------------------------- use case: register
    private static void registerStudent(ELearningSystem system) {
        String name = readLine("Full name: ");
        String email = readLine("Email: ");

        // Relational + logical operators for validation
        if (name.length() < 3 || !email.contains("@")) {
            System.out.println("Registration failed: name needs 3+ characters and email must contain '@'.");
            return;
        }
        Student s = system.registerStudent(name, email);
        System.out.println("Registration successful! Your Student ID is " + s.getUserId() + ".");
    }

    // ------------------------------------------------- use case: enroll + pay
    private static void enrollInCourse(ELearningSystem system) {
        Student student = system.findStudent(readInt("Student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        system.listCourses();
        Course course = system.findCourse(readInt("Course ID to enroll in: "));
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("Payment method: 1) Card   2) Mobile wallet (EasyPaisa/JazzCash)");
        int method = readInt("Choose 1 or 2: ");

        // Parent/interface reference holding a child object (runtime polymorphism)
        Payable payment;
        if (method == 1) {
            String number = readLine("Card number (16 digits): ");
            payment = new CardPayment(student.getName(), number);
        } else if (method == 2) {
            String number = readLine("Mobile number (03XXXXXXXXX): ");
            payment = new MobilePayment("EasyPaisa", number);
        } else {
            System.out.println("Invalid payment method.");
            return;
        }
        system.enrollStudent(student, course, payment);
    }

    // ------------------------------------------------- use case: study lesson
    private static void studyLesson(ELearningSystem system) {
        Enrollment e = askForEnrollment(system);
        if (e == null) {
            return;
        }
        Course course = e.getCourse();
        System.out.println("---- Lessons in " + course.getTitle() + " ----");
        for (int i = 0; i < course.getLessonCount(); i++) {
            String mark = "[ ]";
            if (e.isLessonDone(i)) {
                mark = "[x]";
            }
            System.out.println((i + 1) + ". " + mark + " " + course.getLesson(i).describe());
        }
        int number = readInt("Lesson number to complete: ");
        if (e.completeLesson(number - 1)) {
            System.out.println("Lesson completed! Progress is now " + e.getProgressPercent() + "%.");
        } else {
            System.out.println("Could not complete that lesson (invalid number or already done).");
        }
    }

    // ------------------------------------------------- use case: take quiz
    private static void takeQuiz(ELearningSystem system) {
        Enrollment e = askForEnrollment(system);
        if (e == null) {
            return;
        }
        Quiz quiz = e.getCourse().getQuiz();
        if (quiz == null) {
            System.out.println("This course has no quiz yet.");
            return;
        }
        if (e.getProgressPercent() < 50) {
            System.out.println("Complete at least 50% of the lessons before taking the quiz.");
            return;
        }

        System.out.println("---- " + quiz.getTitle() + " (pass mark " + quiz.getPassPercent() + "%) ----");
        int[] answers = new int[quiz.getQuestionCount()];
        for (int i = 0; i < quiz.getQuestionCount(); i++) {
            Question q = quiz.getQuestion(i);
            q.display(i + 1);
            answers[i] = readInt("Your answer (1-" + q.getOptions().length + "): ");
        }

        int score = quiz.evaluate(answers);
        int percent = quiz.calculatePercent(score);
        boolean passed = quiz.isPassed(percent);
        e.recordQuizResult(percent, passed);

        System.out.println("Score: " + score + "/" + quiz.getTotalMarks() + " (" + percent + "%)");
        if (passed) {
            System.out.println("Congratulations, you PASSED the quiz!");
        } else {
            System.out.println("You did not pass. Review the lessons and try again.");
        }
    }

    // ------------------------------------------------- use case: view progress
    private static void viewProgress(ELearningSystem system) {
        Student student = system.findStudent(readInt("Student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student.showDashboard();
        system.showStudentProgress(student);
    }

    // ------------------------------------------------- use case: certificate
    private static void getCertificate(ELearningSystem system) {
        Enrollment e = askForEnrollment(system);
        if (e == null) {
            return;
        }
        Certificate cert = system.issueCertificate(e);
        if (cert != null) {
            cert.printCertificate();
        }
    }

    // ------------------------------------------------- use case: add course
    private static void addNewCourse(ELearningSystem system) {
        Instructor teacher = system.findInstructor(readInt("Instructor ID (101 or 102): "));
        if (teacher == null) {
            System.out.println("Instructor not found.");
            return;
        }
        String title = readLine("Course title: ");
        int price = readInt("Price (Rs.): ");
        int seats = readInt("Number of seats: ");
        if (title.length() == 0 || price <= 0 || seats <= 0) {
            System.out.println("Invalid course details. Course not created.");
            return;
        }
        Course c = system.addCourse(title, (double) price, seats, teacher);
        c.addLesson("Welcome and Overview", 15);
        System.out.println("Course created with ID " + c.getCourseId() + " by " + teacher.getName() + ".");
    }

    // --------------------------------------------------------------- helpers
    private static Enrollment askForEnrollment(ELearningSystem system) {
        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");
        Enrollment e = system.findEnrollment(studentId, courseId);
        if (e == null) {
            System.out.println("No enrollment found for that student and course.");
        }
        return e;
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    // Returns -1 when the user types something that is not a whole number
    // (1 to 9 digits, so the value always fits in an int)
    private static int readInt(String prompt) {
        String text = readLine(prompt);
        if (text.matches("[0-9]{1,9}")) {
            return Integer.parseInt(text);
        }
        return -1;
    }
}
