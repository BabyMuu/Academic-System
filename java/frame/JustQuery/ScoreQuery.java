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
 * @File : selectQuery
 * @Time : 2021/12/22 21:05
 */
public class ScoreQuery extends SelectFrame {
    public ScoreQuery() {
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
        super.jInternalFrame.setTitle("ȫ��ѧ��������Ϣ��ѯ");
        super.scrollPane1.setBounds(50, 135, 920, 550);
        super.label1.setText("�γ̱��");
        super.label2.setText("ѧ��ѧ��");
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
        if (extra_sql == null){
            extra_sql = "";
        }
        model = new DefaultTableModel();
        for (String colName : new String[]{"ѧ��", "ѧ������", "�γ̱��", "�γ�����", "����"}) {
            model.addColumn(colName);
        }
        DBManager dbManager = new DBManager();
        String sql = "SELECT * FROM `pas`.`stu-lesnum_lesname_score` "+ extra_sql +" LIMIT 0,1000" ;
        System.out.println(sql);
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5)});
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        super.tableList.setModel(model);
        OwnTable.rowSortByInt(tableList, 4);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        extra_sql = "";
        String lesId = super.text1.getText().trim();
        String stuNum = super.text2.getText().trim();
        if (stuNum.equals("") && lesId.equals("")) {
        } else if (!stuNum.equals("") && !lesId.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (stuNum.equals("")) {
            extra_sql = "where lesId = " + lesId;
        } else {
            extra_sql = "where stuNum like '%" + stuNum + "%'";
        }
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
