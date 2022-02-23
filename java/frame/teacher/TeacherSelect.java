package frame.teacher;

import dao.imp.TeacherDaoImp;
import entity.Teacher;
import frame.select.InfoFrame;
import frame.select.SelectFrame;
import lib.tools.MapTools;

import javax.annotation.processing.SupportedOptions;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class TeacherSelect extends SelectFrame {
    public static boolean ISCONFIRM = false;    // 高级查询中是否是点了 确认而返回的
    private InfoFrame infoFrame;
    private TeaMoreQuery aq;

    public TeacherSelect() {

    }

    public TeacherSelect(boolean isDiolog) {    // 以模式窗体打开
        super(isDiolog);    // 引用父类方法
    }

    public TeacherSelect(boolean isDialog, Map<String, String> staticAttr, InfoFrame infoFrame) {
        super(isDialog, staticAttr);
        this.infoFrame = infoFrame;
    }

    @Override
    protected void initPersonalization() {
        // 设置label名称
        super.label1.setText("姓名");
        super.label2.setText("教师编号");
        super.btnMore.setVisible(true);
        // 设置窗口名称
        if (isDialog) {
            super.jDialog.setTitle("教师查询");
        } else {
            super.jInternalFrame.setTitle("教师查询");
        }
        // ---- 添加按钮 ----
        JButton confirm_choice = new JButton();
        confirm_choice.setText("Confirm"); // 设置按钮名称
        confirm_choice.setBorder(new EmptyBorder(5, 5, 5, 5)); // 设置按钮边框
        // 添加按钮事件
        confirm_choice.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                confirm_choice_MousePressed(e);
            }
        });
        // 将按钮添加到桌布中
        super.jDialog.getContentPane().add(confirm_choice);
        // 设置位置和大小
        confirm_choice.setBounds(460, 640, 80, 60);
    }

    /**
     * 根据map内容设置页数
     */
    @Override
    protected void setPage() {
        // 清除所有页数内容
        super.comboNowPage.removeAllItems();
        // 获取数据页数
        if (ISALL) {
            super.pageCount = new TeacherDaoImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new TeacherDaoImp().getPageCount(pageSize, attrValue);
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
        // 设置表格列名
        for (String colName : new String[]{"编号", "姓名", "职称", "类型", "生日"}) {
            model.addColumn(colName);
        }
        // 根据查询条件 查询数据
        List<Teacher> teas = new TeacherDaoImp().findByMap(attrValue, nowPage, pageSize);

        if (teas == null) {     // 如果数据量为0 则表示没有查询到数据
            JOptionPane.showMessageDialog(null, "没有找到您想要找的教师\n请重新查看您的筛选条件");
            return false;
        }
        // 添加数据行到表格
        for (Teacher t : teas) {
            model.addRow(new Object[]{t.getTeaNum(), t.getTeaName(), t.getTeaTitle(),
                    t.getTeaTypeId(), t.getTeaBtd()});
        }
        super.tableList.setModel(model); // 给表格列表添加数据
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear(); // 清除残余数据
        // 获取用户填写的数据
        String name = super.text1.getText().trim(); // 获取第一个文本框的内容
        String num = super.text2.getText().trim(); // 获取第二个文本框的内容
        // 判断用户查询条件
        if (name.equals("") && num.equals("")) {
            ISALL = true;
        } else if (!name.equals("") && !num.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (name.equals("")) {   // 根据编号查询
            attrValue.put("teaNum", num);
        } else {    // 根据姓名查询
            attrValue.put("teaName", name);
        }
        // 加载数据
        MapTools.mapExtend(attrValue, staticAttr);
        setPage();
        setModel();
    }

    /**
     * 更改教师信息
     */
    @Override
    protected void changeMousePressed(MouseEvent e) {
        String teaNum = get_key_by_row_col("编号"); // 根据选中的行列获取 教师编号
        if (teaNum.equals("")) { // 如果返回的是"" 则表示没有选中任何行则返回
            return;
        }
        // 将所得编号传入更新窗体
        new TeacherUpdate(teaNum).frame1.setVisible(true); // 弹出窗体并设置可见
        // 更新数据
        setPage();
        setModel();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
        aq = new TeaMoreQuery(attrValue, staticAttr);
        aq.setModal(true); // 设置为模式窗体
        aq.setVisible(true);    // 设置可见
        if (ISCONFIRM) {    // 如果在more窗体内点了确定
            setData();
            ISCONFIRM = false; // 重新设置为默认值
        }
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String teaNum = get_key_by_row_col("编号"); // 根据选中的行列获取 教师编号
        String teaName = get_teaName_by_row_col();
        if (teaNum.equals("")) { // 如果返回的是"" 则表示没有选中任何行则返回
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "您确定要删除" + teaName + "吗?");
        if (deleteConfirm == 0) {
            TeacherDaoImp teacherDaoImp = new TeacherDaoImp();
            boolean test = teacherDaoImp.delete(teaNum);
            System.out.println(test);
            setPage();
            setModel();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        TeacherFrame teacherFrame = new TeacherFrame();// 弹出新增窗体并设置可见
        teacherFrame.frame1.setVisible(true);
        setPage();
        setModel();
    }

    /**
     * 当窗体为模式窗体的时候点击确认按钮
     * 设置 ISCONFIRMCHOICE 为 true 表示在退出窗口的原因是点击了确认按钮
     * 将 获取到的teanum 通过静态变量返回到上层窗口
     * 关闭当前窗口
     * 作用: 返回选择的教师编号, 防止用户打错教师编号
     */
    private void confirm_choice_MousePressed(MouseEvent e) {
        String num = get_key_by_row_col("编号"); // 根据选中的行列获取 教师编号
        if (num.equals("")) { // 如果返回的是"" 则表示没有选中任何行则返回
            return;
        }
        infoFrame.isConfirmChoice = true;
        infoFrame.num = num;
        super.jDialog.dispose();
    }

}
