package entity;

import java.util.Objects;

public class RestPwd {
    private String stuNum;
    private String ques1;
    private String ans1;
    private String ques2;
    private String ans2;
    private String ques3;
    private String ans3;
    private int requestCount;
    private String key1;
    private int key2;

    public RestPwd() {
        super();
    }

    public RestPwd(String stuNum, String ques1, String ans1, String ques2, String ans2, String ques3, String ans3, int requestCount) {
        this.stuNum = stuNum;
        this.ques1 = ques1;
        this.ans1 = ans1;
        this.ques2 = ques2;
        this.ans2 = ans2;
        this.ques3 = ques3;
        this.ans3 = ans3;
        this.requestCount = requestCount;
    }

    public RestPwd(String stuNum, String ques1, String ans1, String ques2, String ans2, String ques3, String ans3, int requestCount, String key1, int key2) {
        this.stuNum = stuNum;
        this.ques1 = ques1;
        this.ans1 = ans1;
        this.ques2 = ques2;
        this.ans2 = ans2;
        this.ques3 = ques3;
        this.ans3 = ans3;
        this.requestCount = requestCount;
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

    public String getQues1() {
        return ques1;
    }

    public void setQues1(String ques1) {
        this.ques1 = ques1;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getQues2() {
        return ques2;
    }

    public void setQues2(String ques2) {
        this.ques2 = ques2;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getQues3() {
        return ques3;
    }

    public void setQues3(String ques3) {
        this.ques3 = ques3;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public String toString() {
        return "RestPwd{" +
                "stuNum='" + stuNum + '\'' +
                ", ques1='" + ques1 + '\'' +
                ", ans1='" + ans1 + '\'' +
                ", ques2='" + ques2 + '\'' +
                ", ans2='" + ans2 + '\'' +
                ", ques3='" + ques3 + '\'' +
                ", ans3='" + ans3 + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestPwd restPwd = (RestPwd) o;
        return requestCount == restPwd.requestCount && key2 == restPwd.key2 && Objects.equals(stuNum, restPwd.stuNum) && Objects.equals(ques1, restPwd.ques1) && Objects.equals(ans1, restPwd.ans1) && Objects.equals(ques2, restPwd.ques2) && Objects.equals(ans2, restPwd.ans2) && Objects.equals(ques3, restPwd.ques3) && Objects.equals(ans3, restPwd.ans3) && Objects.equals(key1, restPwd.key1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuNum, ques1, ans1, ques2, ans2, ques3, ans3, requestCount, key1, key2);
    }
}
