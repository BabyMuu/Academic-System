package entity;

/**
 * @Author : BabyMuu
 * @File : ScoreResearch
 * @Time : 2021/12/23 16:37
 */
public class ScoreResearch {
    String grade;
    String clsName;
    String lesName;
    String esId;
    float minScore;
    float AvgScore;
    float MaxScore;
    String passingNum;

    public ScoreResearch() {
        super();
    }

    public ScoreResearch(String grade, String clsName, String lesName, String esId, float minScore, float avgScore, float maxScore, String passingNu) {
        this.grade = grade.substring(0, 4);
        this.clsName = clsName;
        this.lesName = lesName;
        this.esId = esId;
        this.minScore = minScore;
        AvgScore = avgScore;
        MaxScore = maxScore;
        this.passingNum = passingNu;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getLesName() {
        return lesName;
    }

    public void setLesName(String lesName) {
        this.lesName = lesName;
    }

    public String getEsId() {
        return esId;
    }

    public void setEsId(String esId) {
        this.esId = esId;
    }

    public float getMinScore() {
        return minScore;
    }

    public void setMinScore(float minScore) {
        this.minScore = minScore;
    }

    public float getAvgScore() {
        return AvgScore;
    }

    public void setAvgScore(float avgScore) {
        AvgScore = avgScore;
    }

    public float getMaxScore() {
        return MaxScore;
    }

    public void setMaxScore(float maxScore) {
        MaxScore = maxScore;
    }

    public String getPassingNum() {
        return passingNum;
    }

    public void setPassingNum(String passingNum) {
        this.passingNum = passingNum;
    }

    @Override
    public String toString() {
        return "ScoreResearch{" +
                "grade='" + grade + '\'' +
                ", clsName='" + clsName + '\'' +
                ", lesName='" + lesName + '\'' +
                ", esId='" + esId + '\'' +
                ", minScore=" + minScore +
                ", AvgScore=" + AvgScore +
                ", MaxScore=" + MaxScore +
                ", passingNu='" + passingNum + '\'' +
                '}';
    }
}
