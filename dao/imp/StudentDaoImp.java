package dao.imp;

import lib.tools.Common;
import dao.IStudentDao;
import db.DBManager;
import entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDaoImp implements IStudentDao {
    @Override
    public List<Student> findAll() {
        return searchAll("", 1, 1, false);
    }

    public List<Student> findAll(int nowPage, int pageSize) {
        return searchAll("", nowPage, pageSize, true);
    }

    public boolean updatePwd(String num, String pwd) {
        String sql =
                "update student " +
                        "set " +
                        "   pwd = '" + pwd + "' " +
                        "where " +
                        "   stuNum ='" + num + "'";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public List<Student> findById(String id, int nowPage, int pageSize) {
        return searchAll("where stuNum like '%" + id + "%'", nowPage, pageSize, true);
    }

    public List<Student> findById(String id) {
        return searchAll("where stuNum = '" + id + "'", 0, 0, false);
    }

    public List<Student> findById(String id, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where stuNum like '%" + id + "%'", nowPage, pageSize, isLimit);
    }

    @Override
    public List<Student> findByName(String stuName, int nowPage, int pageSize) {
        return searchAll("where stuName like '%" + stuName + "%'", nowPage, pageSize, true);
    }

    public List<Student> findByName(String stuName, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where stuName like '%" + stuName + "%'", nowPage, pageSize, isLimit);
    }

    public List<Student> findByTeaNum(String teaNum) {
        return searchAll("INNER JOIN classinfo ON (classinfo.clsId = student.clsId) WHERE teaNum='" + teaNum + "'", 1, 1, false);
    }

    // 返回总行数
    public int getRowCount(String attr, String value) {
        int rowCount = 0;
        String sql = "select count(*) from student ";
        if (attr != null) {
            sql += "where " + attr + " like '%" + value + "%';";
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

    public int getRowCount(String sql_extra) {
        int rowCount = 0;
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select count(*) from student " + sql_extra);
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
                if (key.equals("sex")) {
                    sql.append(" and ").append(key).append(" in ('a'");
                    for (String s : Condition.get(key).split("\\s")) {
                        sql.append(", ").append(s);
                    }
                    sql.append(") ");
                } else {
                    sql.append(" and ").append(key).append(" like '%").append(Condition.get(key)).append("%'");
                }
            }
            return (int) Math.ceil((double) getRowCount(sql.toString()) / (double) pageSize);
        }
    }

    public List<Student> findByMap(Map<String, String> Condition, int nowPage, int pageSize) {
        StringBuffer sql = new StringBuffer("where 1=1");
        for (String key : Condition.keySet()) {
            if (key.equals("teaTypeId") || key.equals("teaTitle") || key.equals("clsId")) {
                sql.append(" and ").append(key).append(" in ('a'");
                for (String s : Condition.get(key).split("\\s")) {
                    sql.append(",'").append(s).append("'");
                }
                sql.append(") ");
            } else {
                sql.append(" and ").append(key).append(" like '%").append(Condition.get(key)).append("%'");
            }
        }
        return searchAll(sql.toString(), nowPage, pageSize, true);
    }

    @Override
    public boolean insert(Student stu) {
        DBManager dbm = new DBManager();
        String sql = "insert into student(stuNum, clsId, stuName, stuBtd, pwd, state, sex , roleId, key1, key2) " +
                "values ("
                + "'" + stu.getStuNum() + "', "
                + "'" + stu.getClsId() + "', "
                + "'" + stu.getStuName() + "', "
                + "'" + Common.dateChangeToString(stu.getStuBtd()) + "', "
                + "'" + stu.getPassword() + "', "
                + "'" + stu.getSta() + "', "
                + "" + Common.boolChangeToBit(stu.isMale()) + ", "
                + "'" + stu.getRoleId() + "', "
                + "'" + stu.getKey1() + "', "
                + "'" + stu.getKey2() + "') ";
        System.out.println(sql);
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean update(Student stu) {
        DBManager dbm = new DBManager();
        String sql = "update student set "
                + "clsId = '" + stu.getClsId() + "', "
                + "stuName = '" + stu.getStuName() + "', "
                + "stuBtd = '" + Common.dateChangeToString(stu.getStuBtd()) + "', "
                + "pwd = '" + stu.getPassword() + "', "
                + "state = '" + stu.getSta() + "', "
                + "sex = " + Common.boolChangeToBit(stu.isMale()) + ", "
                + "roleId = '" + stu.getRoleId() + "', "
                + "key1 = '" + stu.getKey1() + "', "
                + "key2 = '" + stu.getKey2() + "' "
                + "where stuNum = " + stu.getStuNum();
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean delete(String stuNum) {
        DBManager dbm = new DBManager();
        String sql = "delete from student where stuNum = '" + stuNum + "';";
        return dbm.update(sql) == 1;
    }

    public List<Student> searchAll(String sql_extra, int nowPage, int pageSize, boolean isLimit) {
        List<Student> students = new ArrayList<Student>();
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select student.stuNum, student.clsId, student.stuName, student.stuBtd, student.pwd, student.state, student.sex , student.roleId, student.key1, student.key2 from student " + sql_extra + " ");

        if (isLimit) {
            nowPage = nowPage <= 0 ? 1 : nowPage;
            sql.append("limit ").append((nowPage - 1) * pageSize).append(",").append(pageSize);
        }
        System.out.println(sql);
        try {
            ResultSet rs = dbm.query(sql.append(";").toString());
            while (rs.next()) {
                Student stu = new Student(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBoolean(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10)
                );
                students.add(stu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return students.size() == 0 ? null : students;
    }
}
