package dao.imp;

import dao.ITeacherDao;
import db.DBManager;
import entity.FindCA;
import entity.Teacher;
import lib.tools.Common;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherDaoImp implements ITeacherDao {
    public static boolean isAssistant = false;

    public List<Integer> getClsidByTeaNum(String teaNum, String type) {
        List<Integer> clsids = new ArrayList<>();
        DBManager dbManager = new DBManager();
        String table = type.equals("1") ? "courseArrangement" : "classInfo";
        ResultSet rs = dbManager.query("SELECT clsId FROM " + table + " WHERE teaNum = '" + teaNum + "'");
        try {
            while (rs.next()) {
                clsids.add(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Integer clsid : clsids) {
            System.out.print(clsid + "---");
            System.out.println();
        }
        return clsids;
    }

    public List<FindCA> getCA(String teaNum) {
        DBManager dbm = new DBManager();
        ResultSet rs = dbm.query("call getScore(" + teaNum + ")");
        List<FindCA> findCA = new ArrayList<>();
        try {
            while (rs.next()) {
                FindCA aa = new FindCA(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                findCA.add(aa);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return findCA;
    }

    @Override
    public List<Teacher> findAll(int nowPage, int pageSize) {
        return searchAll(isAssistant ? "where teaTypeId = 2" : "", nowPage, pageSize, true);
    }

    public List<Teacher> findAll(int nowPage, int pageSize, boolean isLimit) {
        return searchAll(isAssistant ? "where teaTypeId = 2" : "", nowPage, pageSize, isLimit);
    }

    public List<Teacher> findById(String id) {
        return searchAll("where teaNum = " + id + "", 1, 10, false);
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

    public List<Teacher> findByMap(Map<String, String> Condition, int nowPage, int pageSize) {
        if (isAssistant) {
            Condition.put("teaTypeId", "2");
        }
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
        return searchAll(sql.toString(), nowPage, pageSize, true);
    }

    // 返回总行数
    public int getRowCount(String attr, String value) {
        int rowCount = 0;
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select count(*) from teacher ");
        if (attr != null) {
            sql.append("where ").append(attr).append(" like '%").append(value).append("%'");
        }
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

    public int getRowCount(String sql_extra) {
        int rowCount = 0;
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select count(*) from teacher " + sql_extra);
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

    @Override
    public boolean insert(Teacher teacher) {
        String sql = "insert into teacher(teaNum, teaName, teaTitle, teaTypeId, teaBtd, pwd, state, roleId, key1, key2) values "
                + "('" + teacher.getTeaNum() + "', "
                + "'" + teacher.getTeaName() + "', "
                + "'" + teacher.getTeaTitle() + "', "
                + "'" + teacher.getTeaTypeId() + "', "
                + "'" + Common.dateChangeToString(teacher.getTeaBtd()) + "', "
                + "'" + teacher.getPassword() + "', "
                + "'" + teacher.getSta() + "', "
                + "'" + teacher.getRoleId() + "', "
                + "'" + teacher.getKey1() + "', "
                + "'" + teacher.getKey2() + "') ";
        System.out.println(sql);
        DBManager dbm = new DBManager();
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean update(Teacher tea) {
        DBManager dbm = new DBManager();
        String sql = "update teacher set "
                + "teaName = '" + tea.getTeaName() + "', "
                + "teaTitle = '" + tea.getTeaTitle() + "', "
                + "teaTypeId = '" + tea.getTeaTypeId() + "', "
                + "teaBtd = '" + Common.dateChangeToString(tea.getTeaBtd()) + "', "
                + "pwd = '" + tea.getPassword() + "', "
                + "state = '" + tea.getSta() + "', "
                + "roleId = '" + tea.getRoleId() + "', "
                + "key1 = '" + tea.getKey1() + "', "
                + "key2 = '" + tea.getKey2() + "' "
                + "where teaNum = '" + tea.getTeaNum() + "'";
        System.out.println(sql);
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean delete(String id) {
        DBManager dbm = new DBManager();
        String sql = "delete from teacher where teaNum = " + id;
        return dbm.update(sql) == 1;
    }

    private List<Teacher> searchAll(String sql_extra, int nowPage, int pageSize, boolean isLimit) {
        List<Teacher> teachers = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select teaNum, teaName, teaTitle, teaTypeId, teaBtd, pwd, state, roleId, key1, key2 from teacher " + sql_extra);

        if (isLimit) {
            nowPage = nowPage <= 0 ? 1 : nowPage;
            sql.append(" limit ").append((nowPage - 1) * pageSize).append(",").append(pageSize);
        }
        String s = sql.append(";").toString();
        System.out.println(s);
        DBManager dbm = new DBManager();
        try {
            ResultSet rs = dbm.query(s);
            while (rs.next()) {
                Teacher tea = new Teacher(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10)
                );
                teachers.add(tea);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return teachers.size() == 0 ? null : teachers;
    }
}
