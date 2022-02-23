package dao;

import java.util.List;

import entity.Lesson;

public interface ILessonDao {

    List<Lesson> findAll(int nowPage, int pageSize);

    List<Lesson> findById(String id);

    List<Lesson> findByLesName(String lesName, int nowPage, int pageSize);

    public boolean insert(Lesson lesson);

    public boolean update(Lesson lesson);

    public boolean delete(String id);
}
