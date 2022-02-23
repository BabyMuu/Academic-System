package entity;

import java.util.StringTokenizer;

public class Score {
    private int scoreId;
    private int esId;
    private String stuNum;
    private int score;
    private String stuName;
    private String lesId;
    private String lesName;
    private String key1;
    private int key2;

    public Score() {
        super();
    }

    public Score(int esId, String stuNum, int score) {
        this.esId = esId;
        this.stuNum = stuNum;
        this.score = score;
    }

    public Score(int scoreId, int esId, String stuNum, int score) {
        this.scoreId = scoreId;
        this.esId = esId;
        this.stuNum = stuNum;
        this.score = score;
    }

    public Score(int scoreId, int esId, String stuNum, int score, String stuName, String lesId, String lesName) {
        this.scoreId = scoreId;
        this.esId = esId;
        this.stuNum = stuNum;
        this.score = score;
        this.stuName = stuName;
        this.lesId = lesId;
        this.lesName = lesName;
    }

    public Score(int scoreId, int esId, String stuNum, int score, String key1, int key2) {
        this.scoreId = scoreId;
        this.esId = esId;
        this.stuNum = stuNum;
        this.score = score;
        this.key1 = key1;
        this.key2 = key2;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getEsId() {
        return esId;
    }

    public void setEsId(int esId) {
        this.esId = esId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getLesId() {
        return lesId;
    }

    public void setLesId(String lesId) {
        this.lesId = lesId;
    }

    public String getLesName() {
        return lesName;
    }

    public void setLesName(String lesName) {
        this.lesName = lesName;
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

    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", esId=" + esId +
                ", stuNum='" + stuNum + '\'' +
                ", score=" + score +
                ", key1='" + key1 + '\'' +
                ", key2=" + key2 +
                '}';
    }
}
