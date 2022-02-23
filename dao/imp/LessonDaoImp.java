package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ILessonDao;
import db.DBManager;
import entity.Lesson;


public class LessonDaoImp implements ILessonDao {
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

    public List<Lesson> findByMap(Map<String, String> Condition, int nowPage, int pageSize) {
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

    public int getRowCount(String sql_extra) {
        int rowCount = 0;
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select count(*) from lesson " + sql_extra);
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
    public List<Lesson> findAll(int nowPage, int pageSize) {
        return searchAll("", nowPage, pageSize, true);
    }

    public List<Lesson> findAll(int nowPage, int pageSize, boolean isLimit) {
        return searchAll("", nowPage, pageSize, isLimit);
    }

    @Override
    public List<Lesson> findById(String id) {
        return searchAll("where lesId = " + id, 1, 1, false);
    }

    public List<Lesson> findById(String id, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where lesId like '%" + id + "%'", nowPage, pageSize, isLimit);
    }
    @Override
    public List<Lesson> findByLesName(String lesName, int nowPage, int pageSize) {
        return searchAll("where lesName like '%" + lesName + "%'", nowPage, pageSize, true);
    }

    public List<Lesson> findByLesName(String lesName, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where lesName like '%" + lesName + "%'", nowPage, pageSize, isLimit);
    }

    public List<Lesson> searchAll(String sql_extra, int nowPage, int pageSize, boolean isLimit) {
        StringBuffer sql = new StringBuffer("select lesId, lesName, Context, score, hours from lesson " + sql_extra);
        List<Lesson> lessons = new ArrayList<>();
        if (isLimit) {
            nowPage = nowPage <= 0 ? 1 : nowPage;
            sql.append(" limit ").append((nowPage - 1) * pageSize).append(",").append(pageSize);
        }
        DBManager dbm = new DBManager();
        System.out.println(sql);
        try {
            ResultSet rs = dbm.query(sql.toString());
            while (rs.next()) {
                Lesson les = new Lesson();
                les.setLesId(rs.getInt(1));
                les.setLesName(rs.getString(2));
                les.setContext(rs.getString(3));
                les.setScore(rs.getInt(4));
                les.setHours(rs.getInt(5));
                lessons.add(les);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbm.close();
        }
        return lessons;
    }

    // 返回总行数
    public int getRowCount(String attr, String value) {
        int rowCount = 0;
        String sql = "select count(*) from lesson ";
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

    // 返回页数
    public int getPageCount(int pageSize, String attr, String value) {
        if (attr == null) {
            return (int) Math.ceil((double) getRowCount(null, null) / (double) pageSize);
        } else {
            return (int) Math.ceil((double) getRowCount(attr, value) / (double) pageSize);
        }
    }

    @Override
    public boolean insert(Lesson lesson) {
        DBManager dbm = new DBManager();
        String sql = "insert into lesson (lesName, Context, score, hours) values ("
                + "'" + lesson.getLesName() + "', "
                + "'" + lesson.getContext() + "', "
                + "'" + lesson.getScore() + "', "
                + "'" + lesson.getHours() + "')";
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean update(Lesson lesson) {
        DBManager dbm = new DBManager();
        String sql = "update lesson set "
                + "lesName = '" + lesson.getLesName() + "', "
                + "Context = '" + lesson.getContext() + "', "
                + "score = '" + lesson.getScore() + "', "
                + "hours = '" + lesson.getHours() + "' "
                + "where lesId = " + lesson.getLesId();
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean delete(String id) {
        DBManager dbm = new DBManager();
        String sql = "delete from lesson where lesId = '" + id + "';";
        return dbm.update(sql) == 1;
    }

}	
