package frame.JustQuery;

import db.DBManager;
import frame.select.SelectFrame;
import lib.tools.OwnTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author : BabyMuu
 * @File : ExamQuery
 * @Time : 2021/12/24 14:35
 */
public class ExamQuery extends SelectFrame {
    public ExamQuery() {
        super();
    }

    @Override
    protected void setPage() {
        super.pageCount = 1;
        super.pageSize = 1000;
    }

    @Override
    protected void initPersonalization() {
        super.jInternalFrame.getContentPane().add(super.scrollPane1);
        super.jInternalFrame.setTitle("每场考试信息查询");
        super.scrollPane1.setBounds(50, 135, 920, 550);
        super.label1.setText("考试编号");
//        super.label2.setText("学生学号");
        super.text2.setVisible(false);
        super.button1.setVisible(false);
        super.button2.setVisible(false);
        super.button3.setVisible(false);
        super.button4.setVisible(false);
        super.comboNowPage.setVisible(false);

        hideBtn();
    }

    String extra_sql = "";

    @Override
    protected boolean setModel() {
        if (extra_sql == null) {
            extra_sql = "";
        }
        model = new DefaultTableModel();
        for (String colName : new String[]{"考试编号", "平均分", "最高分", "最低分", "参加人数", "及格人数"}) {
            model.addColumn(colName);
        }
        DBManager dbManager = new DBManager();
        String sql = "select " +
                "`score`.`esId` AS `esId`," +
                "avg(`score`.`score`) AS `avg(score)`" +
                ",max(`score`.`score`) AS `max(score)`" +
                ",min(`score`.`score`) AS `min(score)`" +
                ",count(0) AS `参加人数`,`s2`.`amount` AS `及格人数` " +
                "from (`score` join (select count(0) AS `amount`,`score`.`esId` AS `esId` from `score` where (`score`.`score` >= 60) group by `score`.`esId`) `s2` on((`s2`.`esId` = `score`.`esId`))) " +
                " " + extra_sql + " " +
                "group by `score`.`esId` " +
                "order by `score`.`esId`";
        System.out.println(sql);
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6)});
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        super.tableList.setModel(model);
        OwnTable.rowSortByInt(tableList, 0);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        extra_sql = "";
        String esId = super.text1.getText().trim();
        if (esId.equals("")) {
            JOptionPane.showMessageDialog(null, "请填入查询条件");
        }
        extra_sql = "where score.esId =" + esId;
        setModel();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {

    }
}
