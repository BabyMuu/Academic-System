package entity;

import java.util.Objects;

public class CourseArrangement {
    private int caId;
    private int lesId;
    private String lesName;
    private String teaNum;
    private int clsId;
    private int year;
    private int semester;
    private String key1;
    private int key2;

    public CourseArrangement() {
        super();
    }

    public CourseArrangement(int lesId, String teaNum, int clsId, int year, int semester) {
        this.lesId = lesId;
        this.teaNum = teaNum;
        this.clsId = clsId;
        this.year = year;
        this.semester = semester;
    }

    public CourseArrangement(int caId, String lesName, String teaNum, int clsId, int year, int semester) {
        this.caId = caId;
        this.lesName = lesName;
        this.teaNum = teaNum;
        this.clsId = clsId;
        this.year = year;
        this.semester = semester;
    }

    public CourseArrangement(int caId, int lesId, String teaNum, int clsId, int year, int semester) {
        this.caId = caId;
        this.lesId = lesId;
        this.teaNum = teaNum;
        this.clsId = clsId;
        this.year = year;
        this.semester = semester;
    }

    public CourseArrangement(int caId, int lesId, String teaNum, int clsId, int year, int semester, String key1, int key2) {
        this.caId = caId;
        this.lesId = lesId;
        this.teaNum = teaNum;
        this.clsId = clsId;
        this.year = year;
        this.semester = semester;
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getLesName() {
        return lesName;
    }

    public void setLesName(String lesName) {
        this.lesName = lesName;
    }

    public int getCaId() {
        return caId;
    }

    public void setCaId(int caId) {
        this.caId = caId;
    }

    public int getLesId() {
        return lesId;
    }

    public void setLesId(int lesId) {
        this.lesId = lesId;
    }

    public String getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(String teaNum) {
        this.teaNum = teaNum;
    }

    public int getClsId() {
        return clsId;
    }

    public void setClsId(int clsId) {
        this.clsId = clsId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
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
        return "CourseArrangement{" +
                "caId=" + caId +
                ", lesId=" + lesId +
                ", teaNum='" + teaNum + '\'' +
                ", clsId=" + clsId +
                ", year=" + year +
                ", semester=" + semester +
                ", key1='" + key1 + '\'' +
                ", key2=" + key2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseArrangement that = (CourseArrangement) o;
        return caId == that.caId && lesId == that.lesId && clsId == that.clsId && year == that.year && semester == that.semester && key2 == that.key2 && Objects.equals(teaNum, that.teaNum) && Objects.equals(key1, that.key1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caId, lesId, teaNum, clsId, year, semester, key1, key2);
    }
}
