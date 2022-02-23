package frame.student;

import dao.imp.ExamScheduleDAOImp;
import entity.ExamSchedule;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

/**
 * @Author : BabyMuu
 * @File : ExamSchSelect
 * @Time : 2021/12/23 12:46
 */
public class ExamSchSelect extends SelectFrame {

    public ExamSchSelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    protected void setPage() {
        pageSize = 1;
        pageCount = 1000;
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("考试编号");
        super.label2.setText("课程名称");
        super.jInternalFrame.setTitle("考试安排查询");
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        for (String colName : new String[]{"考试编号", "课程安排编号", "考试概述", "考试时间", "考试状态"}) {
            model.addColumn(colName);
        }
        List<ExamSchedule> examSchedules = new ExamScheduleDAOImp().findByMap_clsId(attrValue);

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
        String esId = super.text1.getText().trim();
        String lesName = super.text2.getText().trim();
        if (esId.equals("") && lesName.equals("")) {
            ISALL = true;
        } else if (!esId.equals("") && !lesName.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (esId.equals("")) {
            attrValue.put("context", lesName);
        } else {
            attrValue.put("esId", esId);
        }
        setData();
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
