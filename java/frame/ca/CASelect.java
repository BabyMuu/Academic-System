package frame.ca;

import dao.imp.CourseArrangementDAOImp;
import entity.CourseArrangement;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class CASelect extends SelectFrame {
    public CASelect() {
        super();
    }

    public CASelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("班级编号");
        super.label2.setText("课程名称");
        super.jInternalFrame.setTitle("课程安排查询");
    }

    @Override
    protected void setPage() {
        super.comboNowPage.removeAllItems();
        if (ISALL) {
            super.pageCount = new CourseArrangementDAOImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new CourseArrangementDAOImp().getPageCount(pageSize, attrValue);
        }
        System.out.println(super.pageCount);
        for (int i = 1; i <= super.pageCount; i++) {
            super.comboNowPage.addItem("第" + i + "页");
        }
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        for (String colName : new String[]{"课程安排编号", "课程名称", "班级编号", "教师编号", "学年", "学期"}) {
            model.addColumn(colName);
        }
        List<CourseArrangement> courseArrangements = new CourseArrangementDAOImp().findByMap(attrValue, nowPage, pageSize);
        if (courseArrangements == null) {
            JOptionPane.showMessageDialog(null, "查询失败,请检查查询条件");
            return false;
        }
        for (CourseArrangement ci : courseArrangements) {
            model.addRow(new Object[]{ci.getCaId(), ci.getLesName(), ci.getClsId(), ci.getTeaNum(), ci.getYear(), ci.getSemester()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String clsId = super.text1.getText().trim();
        String lesName = super.text2.getText().trim();
        if (lesName.equals("") && clsId.equals("")) {
            ISALL = true;
        } else if (!lesName.equals("") && !clsId.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (lesName.equals("")) {
            attrValue.put("clsId", clsId);
        } else {
            attrValue.put("lesName", lesName);
        }
        setData();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        int rowSelected = tableList.getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(null, "请选择要修改的数据行");
            return;
        }
        if (!"课程安排编号".equals(tableList.getColumnName(0))) {
            JOptionPane.showMessageDialog(null, "请将编号设置为第一列");
            return;
        }
        String caId = tableList.getValueAt(rowSelected, 0).toString();
        CAUpdate caUpdate = new CAUpdate(caId);
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String lesId = get_key_by_row_col("课程编号");
        String clsId = get_key_by_row_col("班级编号");
        if (lesId.equals("")) {
            return;
        }
        int lesId1 = Integer.parseInt(lesId);
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "您确定要删除班级： " + clsId + "  " + " 课程编号： " + lesId + "吗?");
        if (deleteConfirm == 0) {
            CourseArrangementDAOImp courseArrangementDAOImp = new CourseArrangementDAOImp();
            boolean test = courseArrangementDAOImp.delete(lesId1);
            System.out.println(test);
            setData();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        CAFrame caFrame = new CAFrame();
        caFrame.courseFrame.setVisible(true);
        setModel();
        setPage();
    }
}
