/*
 * Created by JFormDesigner on Wed Oct 20 22:38:42 CST 2021
 */

package frame.teacher;

import dao.imp.TeacherDaoImp;
import entity.Teacher;
import lib.tools.Common;
import lib.tools.show;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import com.eltima.components.ui.DatePicker;

/**
 * @author unknown
 */
public class TeacherFrame {
    private String msg = "新增"; // 当前窗口作为的

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TeacherFrame() {
        initComponents();  // 初始化窗体骨架
        initPersonalization(); // 初始化个性化内容
        close(); // 设置窗体关闭方式
    }

    public void close() {
        this.frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    ButtonGroup bg;  // 单项选择按钮组

    private void initPersonalization() {
        this.frame1.setModal(true);  // 将窗体设置为模式窗体
        // 给所有文本框添加事件监听
        for (JTextField textField : new JTextField[]{textTeaName, textTeaNum, textRoleId}) {
            textField.addFocusListener(new FocusListener() {
                boolean flag = false; // 文本框是否获取过焦点标识

                @Override
                public void focusGained(FocusEvent e) { // 焦点获取
                    flag = true; // 更改标识
                    if (textField.getForeground() == Color.RED || textField.getForeground() == Color.GRAY) {
                        show.text_to_default(textField); // 文本样式返回默认样式
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // 如果获得过焦点, 且没有填入任何数据 则提示用户
                    if (flag && textField.getText().equals(""))
                        show.text_error(textField, "请填入此项");
                    flag = false;  // 重置标识
                }
            });
        }
        this.frame1.getContentPane().setBackground(Color.GRAY); // 设置关联桌布的背景颜色
        this.bg = new ButtonGroup(); // 创建选择按钮组
        this.bg.add(radioTea); // 将单项选择按钮添加到组中
        this.bg.add(radioInstructor); // 将单项选择按钮添加到组中
        this.DataTeaBtd.a("yyyy-MM-dd"); // 设置日期格式, 年-月-日

        show.text_pre_msg(this.textTeaName, "长度不超过十个汉字");   // 设置文本框默认提示
        show.text_pre_msg(this.textTeaNum, "请输入八位教师编号");    // 设置文本框默认提示

        // 向下拉列表中添加元素
        this.selectTeaTitle.addItem("教授");
        this.selectTeaTitle.addItem("副教师");
        this.selectTeaTitle.addItem("讲师");
        this.selectTeaTitle.addItem("助教");

        // 向下拉列表中添加元素
        this.selectState.addItem("Normal");
        this.selectState.addItem("Holiday");
        this.selectState.addItem("Suspended");

    }

    private void confirmMousePressed(MouseEvent e) {
        boolean isDataEffective = true; // 数据是否有效(用户填写数据是否正确)
        String teaNum = textTeaNum.getText();
        String teaName = textTeaName.getText();
        String password = new String(pwd.getPassword());
        String teaBtdStr = DataTeaBtd.getText();
        int sta = selectState.getSelectedIndex() + 1;
        // 非空判断
        for (String str : new String[]{teaNum, teaName, password, teaBtdStr}) {
            if (str.equals("") || str.equals("请填入此项")) {
                JOptionPane.showMessageDialog(null, "请将所有项输入", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        // 长度判断
        if (teaNum.length() != 8) {
            show.text_error(textTeaNum, "请输入八位教师编号");
            isDataEffective = false;
        }
        if (teaName.length() > 20) {
            show.text_error(textTeaName, "长度不超过十个数字");
            isDataEffective = false;
        }
        // 是否为整数
        if (!Common.isInteger(textRoleId.getText())) {
            show.text_error(textRoleId, "请输入数字");
            isDataEffective = false;
        }

        if (msg.equals("新增") && new TeacherDaoImp().findById(teaNum) != null) {
            show.text_error(textTeaNum, teaNum + "已经存在");
            isDataEffective = false;
        }

        if (!isDataEffective) return;   // 信息验证不正确, 重新输入
        // 获取免验证信息
        int roleId = Integer.parseInt(textRoleId.getText());
        String teaTitle = selectTeaTitle.getSelectedItem().toString();
        String teaTypeId = radioTea.isSelected() ? "1" : "2";
        Date teaBtd = Common.stringChangeToDate(teaBtdStr);
        // 创建教师实体
        Teacher tea = new Teacher(teaNum, teaName, teaTitle, teaTypeId, teaBtd, password, sta, roleId);
        // 保存数据
        String state = saveData(tea, new TeacherDaoImp()) ? "成功" : "失败";
        // 提示输出保存是否成功
        JOptionPane.showMessageDialog(null, msg + "老师" + state, state, JOptionPane.INFORMATION_MESSAGE);
    }

    protected boolean saveData(Teacher tea, TeacherDaoImp tdi) {
        // 信息验证成功 新增数据
        return tdi.insert(tea);
    }

    private void cancelMousePressed(MouseEvent e) {
        this.frame1.dispose();
    }

    private void retMousePressed(MouseEvent e) {
        this.frame1.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        frame1 = new JDialog();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        textTeaName = new JTextField();
        textTeaNum = new JTextField();
        selectTeaTitle = new JComboBox();
        textField2 = new JTextField();
        radioTea = new JRadioButton();
        radioInstructor = new JRadioButton();
        DataTeaBtd = new DatePicker();
        selectState = new JComboBox();
        textRoleId = new JTextField();
        pwd = new JPasswordField();
        confirm = new JButton();
        cancel = new JButton();
        ret = new JButton();
        baseInfo = new JEditorPane();
        textIntroduction = new JTextPane();

        //======== frame1 ========
        {
            frame1.setResizable(false);
            frame1.setTitle("\u6559\u5e08\u6ce8\u518c");
            frame1.setBackground(Color.gray);
            frame1.setName("teacherFrame");
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //---- label1 ----
            label1.setText("\u59d3\u540d");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label1);
            label1.setBounds(85, 50, 75, 35);

            //---- label2 ----
            label2.setText("\u804c\u79f0");
            label2.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label2);
            label2.setBounds(85, 115, 75, 35);

            //---- label3 ----
            label3.setText("\u51fa\u751f\u65e5\u671f");
            label3.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label3);
            label3.setBounds(85, 180, 75, 35);

            //---- label4 ----
            label4.setText("\u89d2\u8272\u7f16\u53f7");
            label4.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            label4.setHorizontalTextPosition(SwingConstants.CENTER);
            frame1ContentPane.add(label4);
            label4.setBounds(85, 245, 75, 35);

            //---- label5 ----
            label5.setText("\u7f16\u53f7");
            label5.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label5);
            label5.setBounds(415, 50, 75, 35);

            //---- label6 ----
            label6.setText("\u6267\u6559\u7c7b\u578b");
            label6.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label6);
            label6.setBounds(415, 115, 75, 35);

            //---- label7 ----
            label7.setText("\u72b6\u6001");
            label7.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label7);
            label7.setBounds(415, 180, 75, 35);

            //---- label8 ----
            label8.setText("\u5bc6\u7801");
            label8.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label8);
            label8.setBounds(415, 245, 75, 35);

            //---- textTeaName ----
            textTeaName.setBorder(LineBorder.createBlackLineBorder());
            textTeaName.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(textTeaName);
            textTeaName.setBounds(175, 50, 200, 30);

            //---- textTeaNum ----
            textTeaNum.setBorder(LineBorder.createBlackLineBorder());
            textTeaNum.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(textTeaNum);
            textTeaNum.setBounds(510, 50, 200, 30);

            //---- selectTeaTitle ----
            selectTeaTitle.setBorder(LineBorder.createBlackLineBorder());
            selectTeaTitle.setForeground(new Color(102, 102, 102));
            selectTeaTitle.setSelectedIndex(-1);
            frame1ContentPane.add(selectTeaTitle);
            selectTeaTitle.setBounds(175, 115, 200, 30);

            //---- textField2 ----
            textField2.setBorder(new MatteBorder(1, 1, 1, 0, Color.black));
            textField2.setBackground(new Color(238, 238, 238));
            frame1ContentPane.add(textField2);
            textField2.setBounds(510, 115, 30, 30);

            //---- radioTea ----
            radioTea.setText("\u6559\u5e08");
            radioTea.setSelected(true);
            radioTea.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
            radioTea.setBorderPainted(true);
            radioTea.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(radioTea);
            radioTea.setBounds(540, 115, 85, 30);

            //---- radioInstructor ----
            radioInstructor.setText("\u5bfc\u5458");
            radioInstructor.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
            radioInstructor.setBorderPainted(true);
            radioInstructor.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(radioInstructor);
            radioInstructor.setBounds(625, 115, 85, 30);

            //---- DataTeaBtd ----
            DataTeaBtd.setBorder(LineBorder.createBlackLineBorder());
            DataTeaBtd.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(DataTeaBtd);
            DataTeaBtd.setBounds(175, 180, 200, 30);

            //---- selectState ----
            selectState.setBorder(LineBorder.createBlackLineBorder());
            selectState.setForeground(new Color(102, 102, 102));
            selectState.setSelectedIndex(-1);
            frame1ContentPane.add(selectState);
            selectState.setBounds(510, 180, 200, 30);

            //---- textRoleId ----
            textRoleId.setBorder(LineBorder.createBlackLineBorder());
            textRoleId.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(textRoleId);
            textRoleId.setBounds(175, 245, 200, 30);

            //---- pwd ----
            pwd.setBorder(LineBorder.createBlackLineBorder());
            pwd.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(pwd);
            pwd.setBounds(510, 245, 200, 30);

            //---- confirm ----
            confirm.setText("Confirm");
            confirm.setBorder(new EmptyBorder(5, 5, 5, 5));
            confirm.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    confirmMousePressed(e);
                }
            });
            frame1ContentPane.add(confirm);
            confirm.setBounds(110, 500, 110, confirm.getPreferredSize().height);

            //---- cancel ----
            cancel.setText("Cancel");
            cancel.setBorder(new EmptyBorder(5, 5, 5, 5));
            cancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    cancelMousePressed(e);
                }
            });
            frame1ContentPane.add(cancel);
            cancel.setBounds(295, 500, 110, cancel.getPreferredSize().height);

            //---- ret ----
            ret.setText("Return");
            ret.setBorder(new EmptyBorder(5, 5, 5, 5));
            ret.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    retMousePressed(e);
                }
            });
            frame1ContentPane.add(ret);
            ret.setBounds(480, 500, 110, ret.getPreferredSize().height);

            //---- baseInfo ----
            baseInfo.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "\u57fa\u7840\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                new Font("\u6977\u4f53", Font.BOLD, 16), Color.orange));
            baseInfo.setBackground(Color.gray);
            baseInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            baseInfo.setEditable(false);
            frame1ContentPane.add(baseInfo);
            baseInfo.setBounds(55, 10, 700, 296);

            //---- textIntroduction ----
            textIntroduction.setBorder(new TitledBorder(null, "\u6559\u5e08\u7b80\u4ecb", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("\u96b6\u4e66", Font.BOLD, 20), Color.green));
            textIntroduction.setText("faadsf ");
            textIntroduction.setBackground(Color.gray);
            frame1ContentPane.add(textIntroduction);
            textIntroduction.setBounds(55, 315, 700, 170);

            frame1ContentPane.setPreferredSize(new Dimension(810, 575));
            frame1.setSize(810, 575);
            frame1.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JDialog frame1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    protected JTextField textTeaName;
    protected JTextField textTeaNum;
    protected JComboBox selectTeaTitle;
    private JTextField textField2;
    protected JRadioButton radioTea;
    protected JRadioButton radioInstructor;
    protected DatePicker DataTeaBtd;
    protected JComboBox selectState;
    protected JTextField textRoleId;
    protected JPasswordField pwd;
    public JButton confirm;
    private JButton cancel;
    private JButton ret;
    private JEditorPane baseInfo;
    private JTextPane textIntroduction;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
