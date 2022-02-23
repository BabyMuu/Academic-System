package dao.imp;

import db.DBManager;
import entity.FindCA;
import entity.ScoreResearch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : BabyMuu
 * @File : FindCaDaoImp
 * @Time : 2021/12/23 11:42
 */
public class FindCaDaoImp {
    public List<ScoreResearch> getScores(String teaNum) {
        List<Integer> clsids = new ArrayList<>();
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query("SELECT DISTINCT clsId FROM coursearrangement WHERE teaNum = '" + teaNum + "'");
        try {
            while (rs.next()) {
                clsids.add(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<ScoreResearch> scores = new ArrayList<>();
        for (Integer clsid : clsids) {
            rs = dbManager.query("call getScore(" + clsid + ", 1, 0,1000)");
            System.out.println("call getScore(" + clsid + ", 1, 0,1000)");
            try {
                while (rs.next()) {
                    ScoreResearch sr = new ScoreResearch(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getFloat(6),
                            rs.getFloat(7),
                            rs.getString(8)
                    );
                    scores.add(sr);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return scores;
    }

    public List<FindCA> getCA(String teaNum) {
        DBManager dbm = new DBManager();
        ResultSet rs = dbm.query("call findca(" + teaNum + ")");
        List<FindCA> findCA = new ArrayList<>();
        try {
            while (rs.next()) {
                FindCA aa = new FindCA(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                findCA.add(aa);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return findCA;
    }

    public List<FindCA> findByMap(Map<String, String> Condition) {
        StringBuffer sql = new StringBuffer("where 1=1");
        for (String key : Condition.keySet()) {
            if (key.equals("bet")) {
                String[] value = Condition.get(key).split(" ");
                sql.append(" and score BETWEEN ").append(value[0]).append(" and ").append(value[1]);
            } else {
                sql.append(" and ").append(key).append(" like '%").append(Condition.get(key)).append("%'");
            }
        }
        return searchAll(sql.toString());
    }

    private List<FindCA> searchAll(String extra_sql) {
        DBManager dbm = new DBManager();
        String sql = "SELECT distinct * FROM `stu-lesnum_lesname_score` " + extra_sql;
        System.out.println(sql);
        ResultSet rs = dbm.query(sql);
        List<FindCA> findscore = new ArrayList<>();
        try {
            while (rs.next()) {
                FindCA aa = new FindCA(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                findscore.add(aa);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return findscore;
    }
}
