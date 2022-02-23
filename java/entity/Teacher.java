package entity;

import java.util.Date;
import java.util.Objects;

public class Teacher {
    private String teaNum;
    private String teaName;
    private String teaTitle;
    private String teaTypeId;
    private Date teaBtd;
    private String password;
    private int sta;
    private int roleId;
    private String key1;
    private int key2;

    public Teacher() {
        super();
    }

    public Teacher(String teaNum, String teaName, String teaTitle,
                   String teaTypeId, Date teaBtd, String password,
                   int sta, int roleId) {
        this.teaNum = teaNum;
        this.teaName = teaName;
        this.teaTitle = teaTitle;
        this.teaTypeId = teaTypeId;
        this.teaBtd = teaBtd;
        this.password = password;
        this.sta = sta;
        this.roleId = roleId;
    }

    public Teacher(String teaNum, String teaName, String teaTitle, String teaTypeId, Date teaBtd, String password, int sta, int roleId, String key1, int key2) {
        this.teaNum = teaNum;
        this.teaName = teaName;
        this.teaTitle = teaTitle;
        this.teaTypeId = teaTypeId;
        this.teaBtd = teaBtd;
        this.password = password;
        this.sta = sta;
        this.roleId = roleId;
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(String teaNum) {
        this.teaNum = teaNum;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaTitle() {
        return teaTitle;
    }

    public void setTeaTitle(String teaTitle) {
        this.teaTitle = teaTitle;
    }

    public String getTeaTypeId() {
        return teaTypeId;
    }

    public void setTeaTypeId(String teaTypeId) {
        this.teaTypeId = teaTypeId;
    }

    public Date getTeaBtd() {
        return teaBtd;
    }

    public void setTeaBtd(Date teaBtd) {
        this.teaBtd = teaBtd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
        return "Teacher{" +
                "teaNum='" + teaNum + '\'' +
                ", teaName='" + teaName + '\'' +
                ", teaTitle='" + teaTitle + '\'' +
                ", teaTypeId='" + teaTypeId + '\'' +
                ", teaBtd=" + teaBtd +
                ", password='" + password + '\'' +
                ", sta=" + sta +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return sta == teacher.sta && roleId == teacher.roleId && Objects.equals(teaNum, teacher.teaNum) && Objects.equals(teaName, teacher.teaName) && Objects.equals(teaTitle, teacher.teaTitle) && Objects.equals(teaTypeId, teacher.teaTypeId) && Objects.equals(teaBtd, teacher.teaBtd) && Objects.equals(password, teacher.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teaNum, teaName, teaTitle, teaTypeId, teaBtd, password, sta, roleId);
    }
}
