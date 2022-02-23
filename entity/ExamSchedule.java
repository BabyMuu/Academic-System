package entity;

import java.util.Date;

public class ExamSchedule {
    private int esId;
    private int caId;
    private Date examDate;
    private int state;
    private String context;
    private String key1;
    private int key2;

    public ExamSchedule() {
        super();
    }

    public ExamSchedule(int caId, Date examDate, int state, String context) {
        this.caId = caId;
        this.examDate = examDate;
        this.state = state;
        this.context = context;
    }

    public ExamSchedule(int esId, int caId, Date examDate, int state, String context) {
        this.esId = esId;
        this.caId = caId;
        this.examDate = examDate;
        this.state = state;
        this.context = context;
    }

    public ExamSchedule(int caId, Date examDate, int state, String context, String key1, int key2) {
        this.caId = caId;
        this.examDate = examDate;
        this.state = state;
        this.context = context;
        this.key1 = key1;
        this.key2 = key2;
    }

    public ExamSchedule(int esId, int caId, Date examDate, int state, String context, String key1, int key2) {
        this.esId = esId;
        this.caId = caId;
        this.examDate = examDate;
        this.state = state;
        this.context = context;
        this.key1 = key1;
        this.key2 = key2;
    }

    public int getEsId() {
        return esId;
    }

    public void setEsId(int esId) {
        this.esId = esId;
    }

    public int getCaId() {
        return caId;
    }

    public void setCaId(int caId) {
        this.caId = caId;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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
        return "ExamSchedule{" +
                "esId=" + esId +
                ", caId=" + caId +
                ", examDate=" + examDate +
                ", state=" + state +
                ", context='" + context + '\'' +
                ", key1='" + key1 + '\'' +
                ", key2=" + key2 +
                '}';
    }
}
