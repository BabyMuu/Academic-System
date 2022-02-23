package dao.imp;

import dao.IRestPwd;
import db.DBManager;
import entity.RestPwd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestPwdDaoImp implements IRestPwd {

    @Override
    public List<RestPwd> findAll() {
        DBManager dbm = new DBManager();
        List<RestPwd> rps = new ArrayList<>();
        String sql = "select stuNum, question1, answer1, question2, answer2, question3, answer3, requestCount from restpwd";
        try {
            ResultSet rs = dbm.query(sql);
            while (rs.next()) {
                RestPwd rp = new RestPwd(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                );
                rps.add(rp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return rps;
    }

    @Override
    public RestPwd findById(String id) {
        DBManager dbm = new DBManager();
        String sql = "select stuNum, question1, answer1, question2, answer2, question3, answer3, requestCount from restpwd where stuNum = '" + id + "';";
        RestPwd rp = null;
        try {
            ResultSet rs = dbm.query(sql);
            while (rs.next()) {
                rp = new RestPwd(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbm.close();
        }
        return rp;
    }

    @Override
    public boolean insert(RestPwd rp) {
        DBManager dbm = new DBManager();
        String sql = "insert into restpwd(stuNum, question1, answer1, question2, answer2, question3, answer3, requestCount)"
                + " values ("
                + "'" + rp.getStuNum() + "', "
                + "'" + rp.getQues1() + "', "
                + "'" + rp.getAns1() + "', "
                + "'" + rp.getQues2() + "', "
                + "'" + rp.getAns2() + "', "
                + "'" + rp.getQues3() + "', "
                + "'" + rp.getAns3() + "', "
                + "'" + rp.getRequestCount() + "')";

        return dbm.update(sql) == 1;
    }

    @Override
    public boolean update(RestPwd rp) {
        DBManager dbm = new DBManager();
        String sql = "update restpwd set "
                + "question1 = '" + rp.getQues1() + "', "
                + "answer1 = '" + rp.getAns1() + "', "
                + "question2 = '" + rp.getQues2() + "', "
                + "answer2 = '" + rp.getAns2() + "', "
                + "question3 = '" + rp.getQues3() + "', "
                + "answer3 = '" + rp.getAns3() + "', "
                + "requestCount = " + rp.getRequestCount()
                + " where stuNum = " + rp.getStuNum();
        System.out.println(sql);
        return dbm.update(sql) == 1;
    }

    @Override
    public boolean delete(String id) {
        DBManager dbm = new DBManager();
        String sql = "delete from restpwd where stuNum = " + id;
        return dbm.update(sql) == 1;
    }

    public RestPwd findByAnswer(String id, String a1, String a2, String a3) {
        //addRequestNum(id);
        RestPwd rp = null;
        String sql =
                "select " +
                        "   stuNum, question1, answer1, question2, answer2, question3, answer3, requestCount " +
                        "from " +
                        "   restPwd " +
                        "where " +
                        "   stuNum='" + id + "' and answer1 = '" + a1 + "' and answer2 = '" + a2 + "' and answer3 = '" + a3 + "' ";

        System.out.println(sql);
        DBManager dbManager = new DBManager();
        String sql2 =
                "update restPwd  set requestCount = requestCount + 1 where stuNum = '" + id + "'";
        dbManager.update(sql2);
        dbManager = new DBManager();
        try {
            ResultSet rs = dbManager.query(sql);
            if (rs.next()) {
                String stuNum = rs.getString("stuNum");
                String question1 = rs.getString("question1");
                String answer1 = rs.getString("answer1");
                String question2 = rs.getString("question2");
                String answer2 = rs.getString("answer2");
                String question3 = rs.getString("question3");
                String answer3 = rs.getString("answer3");
                int requestCount = rs.getInt("requestCount");
                rp = new RestPwd(stuNum, question1, answer1, question2, answer2, question3, answer3, requestCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return rp;
    }

    public boolean updateRequestNum(String num, int requestNum) {
        String sql =
                "update restPwd  set requestCount = " + requestNum +
                        " where stuNum = '" + num + "'";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }
}
