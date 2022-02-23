package dao.imp;

import dao.IClassInfoDao;
import db.DBManager;
import entity.ClassInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lib.tools.Common;

public class ClassInfoDAOImp implements IClassInfoDao {
    @Override
    public List<ClassInfo> findAll(int nowPage, int pageSize) {
        return searchAll("", nowPage, pageSize, true);
    }

    public List<ClassInfo> findAll(int nowPage, int pageSize, boolean isLimit) {
        return searchAll("", nowPage, pageSize, isLimit);
    }

    @Override
    public List<ClassInfo> findById(String id, int nowPage, int pageSize) {
        return searchAll("where clsId like '%" + id + "%'", nowPage, pageSize, true);
    }

    public List<ClassInfo> findById(String id, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where clsId like '%" + id + "%'", nowPage, pageSize, isLimit);
    }

    @Override
    public List<ClassInfo> findByClsName(String name, int nowPage, int pageSize) {
        return searchAll("where teaNum clsName '%" + name + "%'", nowPage, pageSize, true);
    }

    public List<ClassInfo> findByClsName(String name, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where teaNum clsName '%" + name + "%'", nowPage, pageSize, isLimit);
    }

    // 返回页数
    public int getPageCount(int pageSize, String attr, String value) {
        if (attr == null) {
            return (int) Math.ceil((double) getRowCount(null, null) / (double) pageSize);
        } else {
            return (int) Math.ceil((double) getRowCount(attr, value) / (double) pageSize);
        }
    }

    public int getPageCount(int pageSize, Map<String, String> Condition) {
        if (Condition == null) {
            return (int) Math.ceil((double) getRowCount(null, null) / (double) pageSize);
        } else {
            StringBuffer sql = new StringBuffer("where 1=1");
            for (String key : Condition.keySet()) {
                if (key.equals("teaTypeId") || key.equals("teaTitle") || key.equals("state")) {
                    sql.append(" and ").append(key).append(" in ('a'");
                    for (String s : Condition.get(key).split("\\s")) {
                        sql.append(",'").append(s).append("'");
                    }
                    sql.append(") ");
                } else {
                    sql.append(" and ").append(key).append(" like '%").append(Condition.get(key)).append("%'");
                }
            }
            return (int) Math.ceil((double) getRowCount(sql.toString()) / (double) pageSize);
        }
    }

    public List<ClassInfo> findByMap(Map<String, String> Condition, int nowPage, int pageSize) {
        StringBuffer sql = new StringBuffer("where 1=1");
        for (String key : Condition.keySet()) {
            if (key.equals("grade")) {
                sql.append(" and ").append(key).append(" like '%").append(Condition.get(key)).append("-01-01").append("%'");
            } else {
                sql.append(" and ").append(key).append(" like '%").append(Condition.get(key)).append("%'");
            }
        }
        return searchAll(sql.toString(), nowPage, pageSize, true);
    }

    public int getRowCount(String sql_extra) {
        int rowCount = 0;
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select count(*) from classinfo " + sql_extra);
        try {
            ResultSet rs = dbm.query(sql.append(";").toString());
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return rowCount;
    }

    // 返回总行数
    public int getRowCount(String attr, String value) {
        int rowCount = 0;
        String sql = "select count(*) from classInfo ";
        if (attr != null) {
            sql += "where " + attr + " like '" + value + "%';";
        }
        DBManager dbm = new DBManager();
        try {
            ResultSet rs = dbm.query(sql);
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return rowCount;
    }

    @Override
    public boolean insert(ClassInfo cls) {
        DBManager dbm = new DBManager();
        String sql = "insert into classInfo(clsName, grade, stuCount, teaNum, key1, key2) values "
                + "('" + cls.getClsName() + "', "
                + "'" + Common.dateChangeToString(cls.getGrade()) + "', "
                + "" + cls.getStuCount() + ", "
                + "" + cls.getTeaNum() + ", "
                + "" + cls.getKey1() + ", "
                + "'" + cls.getKey2() + "')" + ";";
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean update(ClassInfo cls) {
        DBManager dbm = new DBManager();
        String sql = "update classInfo set "
                + "clsName = '" + cls.getClsName() + "', "
                + "grade = '" + Common.dateChangeToString(cls.getGrade()) + "', "
                + "stuCount = '" + cls.getStuCount() + "', "
                + "teaNum = '" + cls.getTeaNum() + "', "
                + "key1 = '" + cls.getKey1() + "', "
                + "key2 = '" + cls.getKey2() + "' "
                + "where clsid = " + cls.getClsId() + ";";

        return dbm.update(sql) == 1;
    }

    public List<ClassInfo> findByGrade(String grade, boolean isLimit) {
        return searchAll("where grade = '" + grade + "-01-01'", 1, 1, isLimit);
    }

    @Override
    public boolean delete(int id) {
        DBManager dbm = new DBManager();
        String sql = "delete from classInfo where clsId = " + id + ";";
        return dbm.update(sql) == 1;
    }

    private List<ClassInfo> searchAll(String sql_extra, int nowPage, int pageSize, boolean isLimit) {
        List<ClassInfo> classes = new ArrayList<ClassInfo>();
        StringBuffer sql = new StringBuffer("select clsid, clsName, grade, stuCount, teaNum, key1, key2 from classInfo " + sql_extra);
        if (isLimit) {
            nowPage = nowPage <= 0 ? 1 : nowPage;
            sql.append(" limit ").append((nowPage - 1) * pageSize).append(",").append(pageSize);
        }
        System.out.println(sql);
        DBManager dbm = new DBManager();
        try {
            ResultSet rs = dbm.query(sql.append(";").toString());
            while (rs.next()) {
                ClassInfo cls = new ClassInfo();
                cls.setClsId(rs.getInt(1));
                cls.setClsName(rs.getString(2));
                cls.setGrade(rs.getDate(3));
                cls.setStuCount(rs.getInt(4));
                cls.setTeaNum(rs.getString(5));
                cls.setKey1(rs.getString(6));
                cls.setKey2(rs.getInt(7));
                classes.add(cls);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return classes;
    }

    @Override
    public int findByNameAndGrade(String clsName, String grade) {
        List<ClassInfo> classes = new ArrayList<ClassInfo>();
        DBManager dbm = new DBManager();
        String sql = "select clsid from classInfo " +
                "where clsName = '" + clsName + "' AND grade = '" + grade + "-01-01';";
        System.out.println(sql);
        int clsId = 0;
        try {
            ResultSet rs = dbm.query(sql);
            while (rs.next()) {
                clsId = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return clsId;
    }
}
