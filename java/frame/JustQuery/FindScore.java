package frame.JustQuery;

import dao.imp.FindCaDaoImp;
import dao.imp.ScoreDAOImp;
import db.DBManager;
import entity.FindCA;
import entity.Score;
import frame.score.ScoMoreQuery;
import frame.score.ScoreFrame;
import frame.score.ScoreUpdate;
import frame.select.SelectFrame;

import javax.annotation.processing.SupportedOptions;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author : BabyMuu
 * @File : FinaScore
 * @Time : 2021/12/26 14:31
 */
public class FindScore extends SelectFrame {


    @Override
    protected void setPage() {
        super.pageCount = 1;
        super.pageSize = 1000;
    }
    public FindScore(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }
    @Override
    protected void initPersonalization() {
        super.label1.setText("班级编号");
        super.label2.setText("课程名称");
        super.jInternalFrame.setTitle("分数管理查询");
        super.btnMore.setVisible(true);
        super.hidePageControl();
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        // 设置表格列名
        for (String colName : new String[]{"学号","姓名","课程号","课程","分数","班级号"}) {
            model.addColumn(colName);
        }

        // 根据查询条件 查询数据
        List<FindCA> teas = new FindCaDaoImp().findByMap(attrValue);
        System.out.println(teas);
        if (teas == null) {     // 如果数据量为0 则表示没有查询到数据
            JOptionPane.showMessageDialog(null, "没有找到您想要找的教师\n请重新查看您的筛选条件");
            return false;
        }
        // 添加数据行到表格
        for (FindCA t : teas) {
            model.addRow(new Object[]{t.getStuNum(),t.getStuName(),t.getLesId(),t.getLesName(),t.getScore(),t.getClsId()});
        }
        this.tableList.setModel(model); // 给表格列表添加数据
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear(); // 清除残余数据
        // 获取用户填写的数据
        String clsId = super.text1.getText().trim(); // 获取第一个文本框的内容
        String lesName = super.text2.getText().trim(); // 获取第二个文本框的内容
        // 判断用户查询条件
        if (clsId.equals("") && lesName.equals("")) {
            ISALL = true;
        } else if (!clsId.equals("") && !lesName.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (clsId.equals("")) {   // 根据编号查询
            attrValue.put("lesName", lesName);
        } else {    // 根据姓名查询
            attrValue.put("clsid", clsId);
        }

        setData();
    }
    private String getScoreId(){
        // 获取选中行 信息
        int rowSelected = tableList.getSelectedRow(); // 获取选中的行
        if (rowSelected == -1) { // 表示未选中任何一行
            JOptionPane.showMessageDialog(null, "请选择要修改的数据行");
            return "";
        }
        // 获取选中行的教师编号的所在列
        int idNum;
        for (idNum = 0; ; idNum++) {
            if ("学号".equals(tableList.getColumnName(idNum)))
                break;
        }
        String stuNum = tableList.getValueAt(rowSelected, idNum).toString();
        DBManager dbManager =new DBManager();
        String sql = "SELECT scoreId from score " +
                " INNER JOIN examschedule on (examschedule.esId = score.esId)" +
                " INNER JOIN coursearrangement on (examschedule.caId = coursearrangement.caId)" +
                " WHERE coursearrangement.teaNum= '"+staticAttr.get("TeaNum")+"' AND stuNum='"+stuNum+"'";
        System.out.println(sql);
        ResultSet rs = dbManager.query(sql);
        try {
            if (rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }
    @Override
    protected void changeMousePressed(MouseEvent e) {
        String scoreId = getScoreId();
        if (scoreId.equals("")) {
            return;
        }
        ScoreUpdate scoreUpdate = new ScoreUpdate(scoreId);
        scoreUpdate.setVisible(true);

        setData();
    }
    public static boolean ISCONFIRM = false;
    ScoMoreQuery scoMoreQuery;
    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
        scoMoreQuery = new ScoMoreQuery(attrValue, staticAttr, this);
        scoMoreQuery.setModal(true); // 设置为模式窗体
        scoMoreQuery.setVisible(true);    // 设置可见
        if (ISCONFIRM) {    // 如果在more窗体内点了确定
            setData();
            ISCONFIRM = false; // 重新设置为默认值
        }
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String scoreId = getScoreId();
        if (scoreId.equals("")) {
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "您确定要删除" + scoreId + "吗?");
        if (deleteConfirm == 0) {
            ScoreDAOImp scoreDAOImp = new ScoreDAOImp();
            boolean test = scoreDAOImp.delete(scoreId);
            System.out.println(test);
            setData();

        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        ScoreFrame scoreFrame = new ScoreFrame();
        scoreFrame.ScoreFrame.setVisible(true);
        setData();
    }
}
