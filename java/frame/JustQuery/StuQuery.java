package frame.JustQuery;

import dao.imp.StudentDaoImp;
import dao.imp.TeacherDaoImp;
import entity.Student;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @Author : BabyMuu
 * @File : Findstu
 * @Time : 2021/12/24 23:25
 */
public class StuQuery extends SelectFrame {
    List<Integer> clsIds;

    @Override
    protected void setPage() {
        super.pageCount = 1;
        super.pageSize = 1000;
    }

    public StuQuery(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, null, false);
        clsIds = new TeacherDaoImp().getClsidByTeaNum(staticAttr.get("teaNum"), staticAttr.get("teaTypeId"));
        StringBuilder sb = new StringBuilder();
        for (Integer clsId : clsIds) {
            sb.append(clsId).append(" ");
        }
        staticAttr.remove("teaTypeId");
        staticAttr.remove("teaNum");
        attrValue.put("clsId", sb.toString());
        staticAttr.clear();
        setData();
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("学生学号");
        super.label2.setText("学生班级");
        super.frame1.setTitle("学生管理");
        super.hidePageControl();
        super.confirm.setVisible(false);
        super.change.setVisible(false);
        super.ret.setVisible(false);
        super.delete.setVisible(false);
    }

    @Override
    protected boolean setModel() {

        super.jInternalFrame.setTitle("学生查询");
        model = new DefaultTableModel();
        for (String colName : new String[]{"编号", "班级", "名字", "生日", "性别", "状态"}) {
            model.addColumn(colName);
        }
        List<Student> stus;

        stus = new StudentDaoImp().findByMap(attrValue, 1, 1000);
        attrValue.clear();
        if (stus == null) {
            JOptionPane.showMessageDialog(null, "查询学生失败,请检查查询条件");
            return false;
        }
        for (Student t : stus) {
            model.addRow(new Object[]{t.getStuNum(), t.getClsId(), t.getStuName(),
                    t.getStuBtd(), t.isMale() ? "男" : "女", t.getSta()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        // 获取用户填写的数据
        String stuNum = super.text1.getText().trim(); // 获取第一个文本框的内容
        String clsid = super.text2.getText().trim(); // 获取第二个文本框的内容
        // 判断用户查询条件
        if (stuNum.equals("") && clsid.equals("")) {
            StringBuilder sb = new StringBuilder();
            for (Integer clsId : clsIds) {
                sb.append(clsId).append(" ");
            }
            attrValue.put("clsId", sb.toString());
        } else if (!stuNum.equals("") && !clsid.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (stuNum.equals("")) {   // 根据班级名查询
            if (clsIds.contains(Integer.parseInt(clsid))) {
                attrValue.put("clsId", clsid);
            } else {
                JOptionPane.showMessageDialog(null, "输入的clsid不存在, 请重新输入");
                return;
            }
        } else {    // 根据学号查询
            attrValue.put("StuNum", stuNum);
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
