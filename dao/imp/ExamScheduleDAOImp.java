package dao.imp;

import dao.IExamSchedule;
import db.DBManager;
import entity.ExamSchedule;
import lib.tools.Common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExamScheduleDAOImp implements IExamSchedule {
    List<String> colName = new ArrayList<>(Arrays.asList("esId", "caId", "examDate", "static", "context"));

    @Override
    public List<ExamSchedule> findAll(int nowPage, int pageSize) {
        return searchAll("", nowPage, pageSize, true);
    }

    @Override
    public List<ExamSchedule> findById(String id) {
        return searchAll("where esID like '%" + id + "%'", 1, 1, false);
    }

    @Override
    public boolean insert(ExamSchedule ExamSchedule) {
        DBManager dbm = new DBManager();
        String sql = "insert into ExamSchedule (caId, examDate, static, context) values ("
                + "'" + ExamSchedule.getCaId() + "', "
                + "'" + Common.dateChangeToString(ExamSchedule.getExamDate()) + "', "
                + "'" + ExamSchedule.getState() + "', "
                + "'" + ExamSchedule.getContext() + "')";
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean update(ExamSchedule ExamSchedule) {
        DBManager dbm = new DBManager();
        String sql = "update ExamSchedule set "
                + "caId = '" + ExamSchedule.getCaId() + "', "
                + "examDate = '" + Common.dateChangeToString(ExamSchedule.getExamDate()) + "', "
                + "static = '" + ExamSchedule.getState() + "', "
                + "context = '" + ExamSchedule.getContext() + "' "
                + "where esId = " + ExamSchedule.getEsId();
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean delete(int id) {
        DBManager dbm = new DBManager();
        String sql = "delete from ExamSchedule where esId = " + id;
        return dbm.update(sql) == 1;
    }

    private StringBuffer assembleSQLWithPri(Map<String, String> Condition, StringBuffer sql, boolean isPrecise) {
        if (isPrecise) {
            for (String key : Condition.keySet()) {
                if (colName.contains(key)) {
                    sql.append(" and ExamSchedule.").append(key).append(" like '%").append(Condition.get(key)).append("%'");
                } else {
                    sql.append(" and ").append(key).append(" = '").append(Condition.get(key)).append("'");
                }
            }
        } else {
            for (String key : Condition.keySet()) {
                if (colName.contains(key)) {
                    sql.append(" and ExamSchedule.").append(key).append(" like '%").append(Condition.get(key)).append("%'");
                } else {
                    sql.append(" and ").append(key).append(" = '").append(Condition.get(key)).append("'");
                }
            }
        }
        return sql;
    }

    private StringBuffer assembleSQL(Map<String, String> Condition, boolean isPrecise) {
        StringBuffer sql = new StringBuffer("where 1=1");
        return assembleSQLWithPri(Condition, sql, false);
    }

    public List<ExamSchedule> findByMap(Map<String, String> Condition, int nowPage, int pageSize) {
        return searchAll(assembleSQL(Condition, false).toString(), nowPage, pageSize, true);
    }

    public List<ExamSchedule> findByMap_clsId(Map<String, String> Condition) {
        StringBuffer sql = new StringBuffer("INNER JOIN courseArrangement on (courseArrangement.caId = examSchedule.caId) WHERE 1=1");
        return searchAll(assembleSQLWithPri(Condition, sql, true).toString(), 1, 1, false);
    }

    public int getPageCount(int pageSize, Map<String, String> Condition) {
        if (Condition == null) {
            return (int) Math.ceil((double) getRowCount("") / (double) pageSize);
        } else {
            return (int) Math.ceil((double) getRowCount(assembleSQL(Condition, false).toString()) / (double) pageSize);
        }
    }

    public int getRowCount(String sql_extra) {
        int rowCount = 0;
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select count(*) from examSchedule " + sql_extra);
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

    private List<ExamSchedule> searchAll(String sql_extra, int nowPage, int pageSize, boolean isLimit) {
        List<ExamSchedule> ExamSchedules = new ArrayList<>();
        DBManager dbm = new DBManager();
        StringBuffer sql = new StringBuffer("select ExamSchedule.esId, ExamSchedule.caId, ExamSchedule.examDate, ExamSchedule.static, ExamSchedule.context from ExamSchedule " + sql_extra);
        if (isLimit) {
            nowPage = nowPage <= 0 ? 1 : nowPage;
            sql.append(" limit ").append((nowPage - 1) * pageSize).append(",").append(pageSize);
        }
        System.out.println(sql);
        try {
            ResultSet rs = dbm.query(sql.append(";").toString());
            while (rs.next()) {
                ExamSchedule es = new ExamSchedule();
                es.setEsId(rs.getInt(1));
                es.setCaId(rs.getInt(2));
                es.setExamDate(rs.getDate(3));
                es.setState(rs.getInt(4));
                es.setContext(rs.getString(5));
                ExamSchedules.add(es);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbm.close();
        }
        return ExamSchedules;
    }

}
