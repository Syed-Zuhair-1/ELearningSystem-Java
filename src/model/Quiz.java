package model;

/**
 * A quiz attached to a course. Holds an array of Question objects (composition).
 */
public class Quiz {

    public static final int MAX_QUESTIONS = 10;

    private int quizId;
    private String title;
    private int passPercent;
    private Question[] questions;
    private int questionCount;

    // Constructor 1
    public Quiz(int quizId, String title, int passPercent) {
        this.quizId = quizId;
        this.title = title;
        this.passPercent = passPercent;
        this.questions = new Question[MAX_QUESTIONS];
        this.questionCount = 0;
    }

    // Constructor 2 (overloaded): default pass mark of 50%
    public Quiz(int quizId, String title) {
        this(quizId, title, 50);
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPassPercent() {
        return passPercent;
    }

    public void setPassPercent(int passPercent) {
        this.passPercent = passPercent;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questionCount) {
            return questions[index];
        }
        return null;
    }

    public boolean addQuestion(Question question) {
        if (questionCount < MAX_QUESTIONS) {
            questions[questionCount] = question;
            questionCount++;
            return true;
        }
        return false;
    }

    public int getTotalMarks() {
        int total = 0;
        for (int i = 0; i < questionCount; i++) {
            total += questions[i].getMarks();
        }
        return total;
    }

    // An array is passed to this method: answers[i] is the chosen option for question i
    public int evaluate(int[] answers) {
        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            if (i < answers.length && questions[i].isCorrect(answers[i])) {
                score += questions[i].getMarks();
            }
        }
        return score;
    }

    // Type casting: int -> double for accurate division, then double -> int
    public int calculatePercent(int score) {
        int total = getTotalMarks();
        if (total == 0) {
            return 0;
        }
        return (int) ((double) score * 100 / total);
    }

    public boolean isPassed(int percent) {
        return percent >= passPercent;
    }
}
