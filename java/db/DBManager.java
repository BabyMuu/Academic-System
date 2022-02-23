package db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBManager {
    private int row;
    private Connection conn;
    private Statement sta;
    private ResultSet rs;
    public DBManager() {
        final String id = "root";
        final String pas = "123456";
        final  String url = "jdbc:mysql://127.0.0.1:3306/pas";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, id, pas);
            sta = conn.createStatement();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "无法加载到数据库");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sql: 要执行的sql语句
     * @return sql执行后影响的行数
     */
    public int update(String sql) {
        try {
            row = sta.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return row;
    }

    public ResultSet query(String sql) {
        try {
            rs = sta.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (sta != null) {
                sta.close();
                sta = null;
            }
            if (conn != null) {
                conn.close();
                sta = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
