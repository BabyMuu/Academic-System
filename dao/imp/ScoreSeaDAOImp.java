package dao.imp;

import db.DBManager;
import entity.ScoreResearch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : BabyMuu
 * @File : ScoreSeaDAOImp
 * @Time : 2021/12/23 16:40
 */
public class ScoreSeaDAOImp {

    public int getPageCount(int pageSize, String clsId) {
        return (int) Math.ceil((double) getRowCount(clsId) / (double) pageSize);
    }

    public int getRowCount(String clsId) {
        int rowCount = 0;
        DBManager dbm = new DBManager();
        String sql = "call getScore(" + clsId + ",0,0,0)";
        System.out.println(sql);
        try {
            ResultSet rs = dbm.query(sql);
            if (rs.next())
                rowCount = rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return rowCount;
    }

    public List<ScoreResearch> findByClsId(String clsId, int nowPage, int pageSize) {
        return searchAll("call getScore(" + clsId + ",1," + (nowPage - 1) * pageSize + "," + pageSize + ")");
    }

    public List<ScoreResearch> findAll(int nowPage, int pageSize) {
        return searchAll("call getScore(0,1," + (nowPage - 1) * pageSize + "," + pageSize + ")");
    }

    public List<ScoreResearch> searchAll(String sql) {
        System.out.println(sql);
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        List<ScoreResearch> srs = new ArrayList<>();
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
                srs.add(sr);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return srs;
    }
}
