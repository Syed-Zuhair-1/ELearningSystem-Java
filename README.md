# LearnHub E-Learning System (Console Version)

Software Design & Analysis – Assignment 4 (Java implementation of the Assignment 3 class diagram)

- **Student:** Syed Zuhair Asim
- **Registration / Roll No.:** 24P-0584 (Section BCS-9A)
- **Language / IDE:** Java (JDK 17+), Eclipse IDE
- **Libraries:** none (only `java.util.Scanner` for console input)

## About the project

A console-based E-Learning platform. Students register, enroll in courses and pay
(card or mobile wallet), study lessons, take a quiz and receive a certificate.
Instructors create courses, and admins can view and approve them.

## Project structure

```
ELearningSystem/
├── README.md
└── src/
    ├── model/   (all system classes)
    └── main/    (Main.java - program entry point)
```

## List of classes

| Class | Type | Package | Purpose |
|---|---|---|---|
| `User` | abstract class | model | Parent of all users; abstract `getRole()` and `showDashboard()` |
| `Student` | concrete (extends User) | model | Learner; loyalty discount, level tracking |
| `Instructor` | concrete (extends User) | model | Teacher who creates courses |
| `Admin` | concrete (extends User) | model | Manages the platform, approves courses |
| `Course` | concrete | model | Course with price, seats, `Lesson[]`, `Instructor` and `Quiz` (composition) |
| `Lesson` | concrete | model | One lesson inside a course |
| `Quiz` | concrete | model | Quiz with a `Question[]` array; evaluates answers |
| `Question` | concrete | model | One multiple-choice question |
| `Enrollment` | concrete | model | Links a Student to a Course; progress, quiz result, certificate |
| `Payable` | interface | model | Contract for payment methods |
| `CardPayment` | concrete (implements Payable) | model | Pay by card |
| `MobilePayment` | concrete (implements Payable) | model | Pay by mobile wallet (EasyPaisa / JazzCash) |
| `Certificate` | concrete | model | Certificate issued after completion |
| `ELearningSystem` | concrete | model | Manager class holding the arrays and main operations |
| `Main` | concrete | main | Console menu and program entry point |

## How to run

### In Eclipse
1. `File > New > Java Project`, name it `ELearningSystem`.
2. Copy the `model` and `main` folders from `src/` into the project's `src` folder.
3. Right-click `src/main/Main.java` → `Run As > Java Application`.
4. Use the console at the bottom: type a menu number and press Enter.

### From the command line
```
javac -d out src/model/*.java src/main/Main.java
java -cp out main.Main
```

## Sample data (loaded at start-up)

- Students: ID 1 (Ali Raza), ID 2 (Sara Malik)
- Instructors: ID 101 (Dr. Ahmed Khan), ID 102 (Ms. Fatima Noor); Admin: ID 901
- Courses 1–4 (course 4 has only **1 seat**, used to demonstrate the "course is full" check)

## Quick demo

1. Menu **1** – register a new student (you receive Student ID 3).
2. Menu **3** – enroll in Course 1, pay with a 16-digit card number (e.g. `4111111111111111`).
3. Menu **4** – complete all 4 lessons (repeat 4 times).
4. Menu **5** – take the quiz (correct answers for Course 1: `2`, `3`, `3`).
5. Menu **7** – get the certificate.
