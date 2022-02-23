package dao.imp;

import dao.ICourseArrangementDAO;
import db.DBManager;
import entity.CourseArrangement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseArrangementDAOImp implements ICourseArrangementDAO {
    @Override
    public List<CourseArrangement> findAll(int nowPage, int pageSize) {
        return searchAll("", nowPage, pageSize, true);
    }

    public List<CourseArrangement> findAll(int nowPage, int pageSize, boolean isLimit) {
        return searchAll("", nowPage, pageSize, isLimit);
    }

    @Override
    public List<CourseArrangement> findById(String id, int nowPage, int pageSize) {
        return searchAll("where caId like '%" + id + "%'", nowPage, pageSize, true);
    }

    public List<CourseArrangement> findById(String id, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where caId like '%" + id + "%'", nowPage, pageSize, isLimit);
    }

    @Override
    public List<CourseArrangement> findByClsId(String id, int nowPage, int pageSize) {
        return searchAll("where clsId like '%" + id + "%'", nowPage, pageSize, true);
    }

    public List<CourseArrangement> findByClsId(String id, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where clsId like '%" + id + "%'", nowPage, pageSize, isLimit);
    }

    @Override
    public List<CourseArrangement> findByLesId(String id, int nowPage, int pageSize) {
        return searchAll("where lesId like '%" + id + "%'", nowPage, pageSize, true);
    }

    public List<CourseArrangement> findByLesId(String id, int nowPage, int pageSize, boolean isLimit) {
        return searchAll("where lesId like '%" + id + "%'", nowPage, pageSize, isLimit);
    }

    // 返回总行数
    public int getRowCount(String attr, String value) {
        int rowCount = 0;
        String sql = "select count(*) from courseArrangement ";
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

    public List<CourseArrangement> findByMap(Map<String, String> Condition, int nowPage, int pageSize) {
        StringBuffer sql = new StringBuffer("where 1=1");
        for (String key : Condition.keySet()) {
            sql.append(" and ").append(key).append(" like '%").append(Condition.get(key)).append("%'");
        }
        return searchAll(sql.toString(), nowPage, pageSize, true);
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

                sql.append(" and ").append(key).append(" like '%").append(Condition.get(key)).append("%'");

            }
            return (int) Math.ceil((double) getRowCount(sql.toString()) / (double) pageSize);
        }
    }

    public int getRowCount(String sql_extra) {
        int rowCount = 0;
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select count(*) from courseArrangement INNER JOIN lesson on (lesson.lesId = courseArrangement.lesId) " + sql_extra);
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
    public boolean insert(CourseArrangement ca) {
        DBManager dbm = new DBManager();
        String sql = "insert into courseArrangement(lesId, teaNum, clsId, year, semester) values( "
                + "'" + ca.getLesId() + "', "
                + "'" + ca.getTeaNum() + "', "
                + "'" + ca.getClsId() + "', "
                + "'" + ca.getYear() + "', "
                + "'" + ca.getSemester() + "') ";
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean update(CourseArrangement ca) {
        DBManager dbm = new DBManager();
        String sql = "update courseArrangement set "
                + "lesId = '" + ca.getLesId() + "',"
                + "teaNum = '" + ca.getTeaNum() + "',"
                + "clsId = '" + ca.getClsId() + "',"
                + "year = '" + ca.getYear() + "',"
                + "semester = '" + ca.getSemester() + "'" +
                " where caId = " + ca.getCaId();

        return dbm.update(sql) == 1;
    }

    @Override
    public boolean delete(int id) {
        DBManager dbm = new DBManager();
        String sql = "delete from courseArrangement where caId = " + id;
        return dbm.update(sql) == 1;
    }


    private List<CourseArrangement> searchAll(String sql_extra, int nowPage, int pageSize, boolean isLimit) {
        List<CourseArrangement> cas = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select caId, lesName, teaNum, clsId, year, semester from CourseArrangement INNER JOIN lesson on (lesson.lesId = coursearrangement.lesId) " + sql_extra);
        if (isLimit) {
            nowPage = nowPage <= 0 ? 1 : nowPage;
            sql.append(" limit ").append((nowPage - 1) * pageSize).append(",").append(pageSize);
        }
        System.out.println(sql);
        DBManager dbm = new DBManager();
        try {
            ResultSet rs = dbm.query(sql.append(";").toString());
            while (rs.next()) {
                CourseArrangement ca = new CourseArrangement(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)
                );
                cas.add(ca);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return cas;
    }

}
