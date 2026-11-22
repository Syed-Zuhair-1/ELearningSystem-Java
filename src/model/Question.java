package model;

/**
 * A multiple-choice question. A Quiz is composed of Question objects.
 */
public class Question {

    private String questionText;
    private String[] options;
    private int correctOption; // 1-based (1 = first option)
    private int marks;

    // Constructor 1: every question is worth 10 marks
    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.marks = 10;
    }

    // Constructor 2 (overloaded): custom marks
    public Question(String questionText, String[] options, int correctOption, int marks) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.marks = marks;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public boolean isCorrect(int answer) {
        return answer == correctOption;
    }

    public void display(int number) {
        System.out.println("Q" + number + ". " + questionText + " (" + marks + " marks)");
        for (int i = 0; i < options.length; i++) {
            System.out.println("   " + (i + 1) + ") " + options[i]);
        }
    }
}
