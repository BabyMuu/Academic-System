package frame.score;

import dao.imp.ScoreDAOImp;
import entity.Score;
import frame.Login.LoginFrame;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class ScoreSelect extends SelectFrame {
    ScoMoreQuery scoMoreQuery;
    public static boolean ISCONFIRM;

    public ScoreSelect() {
        super();
    }

    public ScoreSelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    public void initPersonalization() {
        if (LoginFrame.ROLE.equals("Student")) {
            super.label1.setText("课程名称");
        } else {
            super.label1.setText("学号");
        }
        super.btnMore.setVisible(true);
        super.label2.setText("考试编号");
        super.jInternalFrame.setTitle("分数查询");
    }


    @Override
    protected void setPage() {
        // 清除所有页数内容
        super.comboNowPage.removeAllItems();
        // 获取数据页数
        if (ISALL) {
            super.pageCount = new ScoreDAOImp().getPageCount(pageSize, null);
            System.out.println("ISALL");
            ISALL = false;
        } else {
            super.pageCount = new ScoreDAOImp().getPageCount(pageSize, attrValue);
        }
        System.out.println(pageCount);
        super.labPageCount.setText("共" + super.pageCount + "页");
        // 将页数添加到组件里
        for (int i = 1; i <= super.pageCount; i++) super.comboNowPage.addItem("第" + i + "页");
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };
        String[] colNames = new String[]{"分数编号", "考试编号", "学生编号", "分数"};
        if (LoginFrame.ROLE.equals("Student")) {
            colNames[2] = "课程名称";
        }
        System.out.println(LoginFrame.ROLE);
        for (String colName : colNames) {
            model.addColumn(colName);
        }
        List<Score> scores = new ScoreDAOImp().findByMap(attrValue, nowPage, pageSize);

        if (scores == null) {
            JOptionPane.showMessageDialog(null, "查询失败,请检查查询条件");
            return false;
        }
        for (Score score : scores) {
            model.addRow(new Object[]{score.getScoreId(), score.getEsId(), score.getStuNum(),
                    score.getScore()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String attr = LoginFrame.ROLE.equals("Student") ? "lesName" : "stuNum";
        String stuNum = super.text1.getText().trim();
        String esId = super.text2.getText().trim();
        if (stuNum.equals("") && esId.equals("")) {
            ISALL = true;
        } else if (!stuNum.equals("") && !esId.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (stuNum.equals("")) {
            attrValue.put("esId", esId);
        } else {
            attrValue.put(attr, stuNum);
        }
        setData();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String scoreId = get_key_by_row_col("分数编号");
        if (scoreId.equals("")) {
            return;
        }
        ScoreUpdate scoreUpdate = new ScoreUpdate(scoreId);
        scoreUpdate.setVisible(true);

        setData();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
        scoMoreQuery = new ScoMoreQuery(attrValue, staticAttr);
        scoMoreQuery.setModal(true); // 设置为模式窗体
        scoMoreQuery.setVisible(true);    // 设置可见
        if (ISCONFIRM) {    // 如果在more窗体内点了确定
            setData();
            ISCONFIRM = false; // 重新设置为默认值
        }
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String scoreId = get_key_by_row_col("分数编号");
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
