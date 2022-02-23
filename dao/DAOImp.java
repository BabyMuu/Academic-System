package dao;

import entity.ClassInfo;

import java.util.List;

public interface DAOImp {
    public List<Object> findAll();

    public Object findById(int id);

    public boolean insert(ClassInfo cls);

    public boolean update(ClassInfo cls);

    public boolean delete(int id);

    public Object findByNameAndGrade(String clsName, String grade);
}
