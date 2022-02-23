package dao.imp;

import dao.IScoreDAO;
import db.DBManager;
import entity.Score;
import frame.Login.LoginFrame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ScoreDAOImp implements IScoreDAO {
    List<String> colName = new ArrayList<>(Arrays.asList("scoreId", "esId", "score", "stuNum"));

    @Override
    public List<Score> findAll(int nowPage, int pageSize) {
        return searchAll("", nowPage, pageSize, true);
    }

    @Override
    public List<Score> findById(int id) {
        return searchAll("where scoreId =" + id, 1, 1, false);
    }

    @Override
    public boolean insert(Score ca) {
        DBManager dbm = new DBManager();
        String sql = "insert into Score(esId, stuNum, score) values( "
                + "'" + ca.getEsId() + "', "
                + "'" + ca.getStuNum() + "', "
                + "'" + ca.getScore() + "') ";
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean update(Score ca) {
        DBManager dbm = new DBManager();
        String sql = "update Score set "
                + "esId = '" + ca.getEsId() + "',"
                + "stuNum = '" + ca.getStuNum() + "',"
                + "score = '" + ca.getScore() + "'"
                + " where scoreId = " + ca.getScoreId();
        System.out.println(sql);
        return dbm.update(sql) == 1;
    }

    public boolean delete(String id) {
        DBManager dbm = new DBManager();
        String sql = "delete from Score where scoreId = " + id;
        return dbm.update(sql) == 1;
    }

    private StringBuffer assembleSQLWithPri(Map<String, String> Condition, StringBuffer sql, boolean isPrecise) {
        if (isPrecise) {
            for (String key : Condition.keySet()) {
                if (key.equals("bet")) {
                    String[] value = Condition.get(key).split(" ");
                    sql.append(" and score BETWEEN ").append(value[0]).append(" and ").append(value[1]);
                } else {
                    if (colName.contains(key)) {
                        sql.append(" and score.").append(key).append(" like '%").append(Condition.get(key)).append("%'");
                    } else {
                        sql.append(" and ").append(key).append(" = '").append(Condition.get(key)).append("'");
                    }
                }
            }
        } else {
            for (String key : Condition.keySet()) {
                if (key.equals("bet")) {
                    String[] value = Condition.get(key).split(" ");
                    sql.append(" and score BETWEEN ").append(value[0]).append(" and ").append(value[1]);
                } else {
                    if (colName.contains(key)) {
                        sql.append(" and score.").append(key).append(" like '%").append(Condition.get(key)).append("%'");
                    } else {
                        sql.append(" and ").append(key).append(" = '").append(Condition.get(key)).append("'");
                    }
                }
            }
        }
        return sql;
    }

    private StringBuffer assembleSQL(Map<String, String> Condition, boolean isPrecise) {
        StringBuffer sql = new StringBuffer("where 1=1");
        return assembleSQLWithPri(Condition, sql, isPrecise);
    }

    public List<Score> findByMap(Map<String, String> Condition, int nowPage, int pageSize) {
        return searchAll(assembleSQL(Condition, false).toString(), nowPage, pageSize, true);
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
        StringBuilder sql = new StringBuilder();
        switch (LoginFrame.ROLE) {
            case "Student":
                sql.append("SELECT count(*) from score INNER JOIN  examSchedule ON (examSchedule.esId = score.esId) INNER JOIN courseArrangement on (courseArrangement.caId = examSchedule.caId) INNER JOIN lesson ON (lesson.lesId = courseArrangement.lesId) ").append(sql_extra).append(" ");
                break;
            default:
                sql.append("select count(*) from Score ").append(sql_extra).append(" ");
                break;
        }
        System.out.println(sql);
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

    public List<Score> searchAll(String sql_extra, int nowPage, int pageSize, boolean isLimit) {
        DBManager dbm = new DBManager();
        List<Score> scores = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        if ("Student".equals(LoginFrame.ROLE)) {
            sql.append("SELECT score.scoreId as scoreId, score.esId as esId, lesson.lesName as lesName, score.score as score from score INNER JOIN  examschedule ON (examschedule.esId = score.esId)\n" + "\tINNER JOIN coursearrangement on (coursearrangement.caId = examschedule.caId)\n" + "\tINNER JOIN lesson ON (lesson.lesId = coursearrangement.lesId) ").append(sql_extra).append(" ");
        } else {
            sql.append("select scoreId, esId, stuNum, score from Score ").append(sql_extra).append(" ");
        }

        if (isLimit) {
            nowPage = nowPage <= 0 ? 1 : nowPage;
            sql.append("limit ").append((nowPage - 1) * pageSize).append(",").append(pageSize);
        }
        System.out.println(sql);
        try {
            ResultSet rs = dbm.query(sql.append(";").toString());
            while (rs.next()) {
                Score score = new Score(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                scores.add(score);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return scores;
    }

}
