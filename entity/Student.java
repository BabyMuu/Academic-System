package entity;

import java.util.Date;
import java.util.Objects;

public class Student {
    private String stuNum;
    private int clsId;
    private String stuName;
    private Date stuBtd;
    private String password;
    private int sta;
    private boolean isMale;
    private int roleId;
    private String key1;
    private int key2;

    public Student() {
        super();
    }

    public Student(String stuNum, int clsId, String stuName, Date stuBtd, String password, int sta, boolean isMale, int roleId) {
        this.stuNum = stuNum;
        this.clsId = clsId;
        this.stuName = stuName;
        this.password = password;
        this.stuBtd = stuBtd;
        this.sta = sta;
        this.isMale = isMale;
        this.roleId = roleId;
    }

    public Student(String stuNum, int clsId, String stuName, Date stuBtd, String password, int sta, boolean isMale, int roleId, String key1, int key2) {
        this.stuNum = stuNum;
        this.clsId = clsId;
        this.stuName = stuName;
        this.stuBtd = stuBtd;
        this.password = password;
        this.sta = sta;
        this.isMale = isMale;
        this.roleId = roleId;
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

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public int getClsId() {
        return clsId;
    }

    public void setClsId(int clsId) {
        this.clsId = clsId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStuBtd() {
        return stuBtd;
    }

    public void setStuBtd(Date stuBtd) {
        this.stuBtd = stuBtd;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        this.isMale = male;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNum='" + stuNum + '\'' +
                ", clsId=" + clsId +
                ", stuName='" + stuName + '\'' +
                ", stuBtd=" + stuBtd +
                ", password='" + password + '\'' +
                ", sta=" + sta +
                ", isMale=" + isMale +
                ", roleId=" + roleId +
                ", key1='" + key1 + '\'' +
                ", key2=" + key2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return clsId == student.clsId && sta == student.sta && isMale == student.isMale && roleId == student.roleId && key2 == student.key2 && Objects.equals(stuNum, student.stuNum) && Objects.equals(stuName, student.stuName) && Objects.equals(password, student.password) && Objects.equals(stuBtd, student.stuBtd) && Objects.equals(key1, student.key1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuNum, clsId, stuName, password, stuBtd, sta, isMale, roleId, key1, key2);
    }
}
