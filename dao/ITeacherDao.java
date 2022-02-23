package dao;

import entity.Teacher;

import java.util.List;

public interface ITeacherDao {
    public List<Teacher> findAll(int nowPage, int pageSize);

    public boolean insert(Teacher teacher);

    public boolean update(Teacher teacher);

    public boolean delete(String id);
}
