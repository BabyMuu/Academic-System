package dao;

import entity.ClassInfo;

import java.util.List;

public interface IClassInfoDao {

    List<ClassInfo> findAll(int nowPage, int pageSize);

    List<ClassInfo> findById(String id, int nowPage, int pageSize);

    List<ClassInfo> findByClsName(String name, int nowPage, int pageSize);

    public boolean insert(ClassInfo cls);

    public boolean update(ClassInfo cls);

    public boolean delete(int id);

    public int findByNameAndGrade(String clsName, String grade);
}
