package entity;

/**
 * @Author : BabyMuu
 * @File : FindCA
 * @Time : 2021/12/22 20:59
 */
public class FindCA {
    String clsId;
    String stuNum;
    String stuName;
    String lesId;
    String lesName;
    int score;
    String teaNum;
    String clsName;
    String year;
    String semester;


    public FindCA() {
        super();
    }

    public FindCA(String lesName, String clsName, String year, String semester) {
        this.lesName = lesName;
        this.clsName = clsName;
        this.year = year;
        this.semester = semester;
    }

    public FindCA(String clsId, String lesName, String clsName, String year, String semester) {
        this.clsId = clsId;
        this.lesName = lesName;
        this.clsName = clsName;
        this.year = year;
        this.semester = semester;
    }

    public FindCA(String stuNum, String stuNam, String lesId, String lesName, int score, String teaNum, String clsId) {
        this.stuNum = stuNum;
        this.stuName = stuNam;
        this.lesId = lesId;
        this.lesName = lesName;
        this.score = score;
        this.teaNum = teaNum;
        this.clsId = clsId;
    }

    public String getClsId() {
        return clsId;
    }

    public void setClsId(String clsId) {
        this.clsId = clsId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(String teaNum) {
        this.teaNum = teaNum;
    }

    public String getLesName() {
        return lesName;
    }

    public void setLesName(String lesName) {
        this.lesName = lesName;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "FindCA{" +
                "lesName='" + lesName + '\'' +
                ", clsName='" + clsName + '\'' +
                ", year='" + year + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }

    public String New_toString() {
        return "FindCEntity{" +
                "stuNum='" + stuNum + '\'' +
                ", stuNam='" + stuName + '\'' +
                ", lesId='" + lesId + '\'' +
                ", lesName='" + lesName + '\'' +
                ", score=" + score +
                ", teaNum='" + teaNum + '\'' +
                '}';
    }
}
