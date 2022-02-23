package frame.examSche;

import dao.imp.ExamScheduleDAOImp;
import entity.ExamSchedule;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class ESSelect extends SelectFrame {

    public ESSelect() {
        super();
    }

    public ESSelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("考试编号");
        super.label2.setText("课程安排编号");
        super.jInternalFrame.setTitle("考试安排查询");
    }

    @Override
    protected void setPage() {
        // 清除所有页数内容
        super.comboNowPage.removeAllItems();
        // 获取数据页数
        if (ISALL) {
            super.pageCount = new ExamScheduleDAOImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new ExamScheduleDAOImp().getPageCount(pageSize, attrValue);
        }
        System.out.println(super.pageCount);
        // 将页数添加到组件里
        for (int i = 1; i <= super.pageCount; i++) {
            super.comboNowPage.addItem("第" + i + "页");
        }
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        for (String colName : new String[]{"考试编号", "课程安排编号", "考试概述", "考试时间", "考试状态"}) {
            model.addColumn(colName);
        }
        List<ExamSchedule> examSchedules = new ExamScheduleDAOImp().findByMap(attrValue, nowPage, pageSize);

        if (examSchedules == null) {
            JOptionPane.showMessageDialog(null, "查询失败,请检查查询条件");
            return false;
        }
        for (ExamSchedule es : examSchedules) {
            model.addRow(new Object[]{es.getEsId(), es.getCaId(), es.getContext(), es.getExamDate(), es.getState()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String caId = super.text1.getText().trim();
        String esId = super.text2.getText().trim();
        if (esId.equals("") && caId.equals("")) {
            ISALL = true;
        } else if (!esId.equals("") && !caId.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (esId.equals("")) {
            attrValue.put("caId", caId);
        } else {
            attrValue.put("esId", esId);
        }
        setPage();
        setModel();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String esId = get_key_by_row_col("考试编号"); // 根据选中的行列获取 教师编号
        if (esId.equals("")) { // 如果返回的是"" 则表示没有选中任何行则返回
            return;
        }
        // 将所得编号传入更新窗体
        new ESUpdate(esId).esFrame.setVisible(true); // 弹出窗体并设置可见
        // 更新数据
        setPage();
        setModel();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {

    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String esId = get_key_by_row_col("考试编号"); // 根据选中的行列获取 教师编号
        int esId1 = Integer.parseInt(esId);
        if (esId.equals("")) { // 如果返回的是"" 则表示没有选中任何行则返回
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "您确定要删除课程编号：" + esId + "吗?");
        if (deleteConfirm == 0) {
            ExamScheduleDAOImp examScheduleDAOImp = new ExamScheduleDAOImp();
            boolean test = examScheduleDAOImp.delete(esId1);
            System.out.println(test);
            setPage();
            setModel();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        ESJFrame esjFrame = new ESJFrame();
        esjFrame.esFrame.setVisible(true);
        setPage();
        setModel();
    }
}
