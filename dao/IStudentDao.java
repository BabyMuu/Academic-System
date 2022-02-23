package dao;

import entity.Student;

import java.util.List;

public interface IStudentDao {
    public List<Student> findAll();

    public List<Student> findById(String id, int nowPage, int pageSize);

    public List<Student> findByName(String stuName, int nowPage, int pageSize);

    public boolean insert(Student stu);

    public boolean update(Student stu);

    public boolean delete(String stuNum);
}
