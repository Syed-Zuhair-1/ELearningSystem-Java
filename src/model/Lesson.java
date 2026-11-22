package model;

/**
 * One lesson inside a course. A Course is composed of Lesson objects.
 */
public class Lesson {

    private int lessonId;
    private String title;
    private int durationMinutes;
    private boolean freePreview;

    // Constructor 1
    public Lesson(int lessonId, String title, int durationMinutes) {
        this.lessonId = lessonId;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.freePreview = false;
    }

    // Constructor 2 (overloaded): default length of 15 minutes
    public Lesson(int lessonId, String title) {
        this.lessonId = lessonId;
        this.title = title;
        this.durationMinutes = 15;
        this.freePreview = false;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public boolean isFreePreview() {
        return freePreview;
    }

    public void setFreePreview(boolean freePreview) {
        this.freePreview = freePreview;
    }

    public String describe() {
        return title + " (" + durationMinutes + " min)";
    }
}
