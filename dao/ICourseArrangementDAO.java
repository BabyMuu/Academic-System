package dao;

import entity.CourseArrangement;

import java.util.List;

public interface ICourseArrangementDAO {

    List<CourseArrangement> findAll(int nowPage, int pageSize);

    List<CourseArrangement> findById(String id, int nowPage, int pageSize);

    List<CourseArrangement> findByClsId(String id, int nowPage, int pageSize);

    List<CourseArrangement> findByLesId(String id, int nowPage, int pageSize);

    public boolean insert(CourseArrangement ca);

    public boolean update(CourseArrangement ca);

    public boolean delete(int id);
}
