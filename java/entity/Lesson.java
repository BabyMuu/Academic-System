package entity;

import java.util.Objects;

public class Lesson {
    private int lesId;
    private String lesName;
    private String context;
    private int score;
    private int hours;
    private String key1;
    private int key2;

    public Lesson() {
        super();
    }

    public Lesson(int lesId, String lesNmae, String context, int score, int hours) {
        this.lesId = lesId;
        this.lesName = lesNmae;
        this.context = context;
        this.score = score;
        this.hours = hours;
    }

    public Lesson(String lesName, String context, int score, int hours) {
        this.lesName = lesName;
        this.context = context;
        this.score = score;
        this.hours = hours;
    }

    public Lesson(int lesId, String lesName, String context, int score, int hours, String key1, int key2) {
        this.lesId = lesId;
        this.lesName = lesName;
        this.context = context;
        this.score = score;
        this.hours = hours;
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public int getKey2() {
        return key2;
    }

    public void setKey2(int key2) {
        this.key2 = key2;
    }

    public int getLesId() {
        return lesId;
    }

    public void setLesId(int lesId) {
        this.lesId = lesId;
    }

    public String getLesName() {
        return lesName;
    }

    public void setLesName(String lesName) {
        this.lesName = lesName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lesId=" + lesId +
                ", lesName='" + lesName + '\'' +
                ", context='" + context + '\'' +
                ", score=" + score +
                ", hours=" + hours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return lesId == lesson.lesId && score == lesson.score && hours == lesson.hours && key2 == lesson.key2 && Objects.equals(lesName, lesson.lesName) && Objects.equals(context, lesson.context) && Objects.equals(key1, lesson.key1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lesId, lesName, context, score, hours, key1, key2);
    }
}
