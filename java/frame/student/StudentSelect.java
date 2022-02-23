package frame.student;

import dao.imp.ClassInfoDAOImp;
import dao.imp.StudentDaoImp;
import entity.Student;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class StudentSelect extends SelectFrame {
    public StudentSelect() {
        super();
    }

    public StudentSelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("姓名");
        super.label2.setText("学生编号");
        super.jInternalFrame.setTitle("学生查询");
    }

    protected void setPage(String attr, String value) {
    }

    @Override
    protected void setPage() {
        // 清除所有页数内容
        super.comboNowPage.removeAllItems();
        // 获取数据页数
        if (ISALL) {
            super.pageCount = new StudentDaoImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new StudentDaoImp().getPageCount(pageSize, attrValue);
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
        for (String colName : new String[]{"编号", "班级", "名字", "生日", "性别", "状态"}) {
            model.addColumn(colName);
        }
        List<Student> stus = null;
        stus = new StudentDaoImp().findByMap(attrValue, nowPage, pageSize);
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
        attrValue.clear(); // 清除残余数据
        String name = super.text1.getText().trim();
        String num = super.text2.getText().trim();
        if (name.equals("") && num.equals("")) {
            ISALL = true;
        } else if (!name.equals("") && !num.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (name.equals("")) {   // 根据编号查询
            attrValue.put("stuNum", num);
        } else {    // 根据姓名查询
            attrValue.put("stuName", name);
        }
        // 加载数据
        setData();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String stuNum = get_key_by_row_col("编号"); // 根据选中的行列获取 教师编号
        if (stuNum.equals("")) { // 如果返回的是"" 则表示没有选中任何行则返回
            return;
        }
        // 将所得编号传入更新窗体
        new StudentUpdate(stuNum).frame1.setVisible(true); // 弹出窗体并设置可见
        // 更新数据
        setData();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {

    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String stuNum = get_key_by_row_col("编号"); // 根据选中的行列获取 教师编号
        String stuName = get_key_by_row_col("名字");
        if (stuNum.equals("")) { // 如果返回的是"" 则表示没有选中任何行则返回
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "您确定要删除" + stuName + "吗?");
        if (deleteConfirm == 0) {
            StudentDaoImp studentDaoImp = new StudentDaoImp();
            boolean test = studentDaoImp.delete(stuNum);
            System.out.println(test);
            setData();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        String grade = getGrade();
        if (grade.equals("-1")) return;
        StudentFrame studentFrame = new StudentFrame(grade + "");// 弹出新增窗体并设置可见
        studentFrame.frame1.setVisible(true);
        setData();
    }

    private String getGrade() {
        String grade;
        while (true) {
            grade = JOptionPane.showInputDialog("请输入您想加入学生的年级");
            if (grade == null){
                return "-1";
            }
            if (2030 < Integer.parseInt(grade) || Integer.parseInt(grade) < 2015) {
                int index = JOptionPane.showConfirmDialog(null, "年级范围2015~2030, 是否重新输入", "错误", JOptionPane.YES_NO_OPTION);
                if (index == 0) {
                    continue;
                } else {
                    return "-1";
                }
            }
            if (new ClassInfoDAOImp().findByGrade(grade, true).size() == 0) {
                int index = JOptionPane.showConfirmDialog(null, "该年级暂时没有班级, 是否重新输入", "错误", JOptionPane.YES_NO_OPTION);
                if (index == 0) {
                    continue;
                } else {
                    return "-1";
                }
            }
            break;
        }
        return grade;
    }
}
