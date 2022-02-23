package dao;

import entity.ExamSchedule;

import java.util.List;

public interface IExamSchedule {

    public List<ExamSchedule> findAll(int nowPage, int pageSize);

    public List<ExamSchedule> findById(String id);

    public boolean insert(ExamSchedule es);

    public boolean update(ExamSchedule es);

    public boolean delete(int id);
}
