/*
 * Created by JFormDesigner on Wed Oct 27 18:01:53 CST 2021
 */

package frame.clsInfo;

import com.eltima.components.ui.*;
import dao.imp.ClassInfoDAOImp;
import dao.imp.TeacherDaoImp;
import entity.ClassInfo;
import frame.select.InfoFrame;
import frame.teacher.TeacherSelect;
import lib.tools.Common;
import lib.tools.show;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class ClassInfoFrame extends InfoFrame {
    private String msg = "新增";


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ClassInfoFrame() {
        initComponents();
        initPersonalization();
        close();
    }

    public void close() {
        this.classInfoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void initPersonalization() {
        this.classInfoFrame.getContentPane().setBackground(Color.GRAY);
        this.classInfoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.ClassGrade.a("yyyy"); // 设置时间格式为仅显示年份
        // 聚焦到出错为文本框 清空文本框内容
        for (JTextField textField : new JTextField[]{textClsName, textTeaNum, textStuCount}) {
            textField.addFocusListener(new FocusListener() {
                boolean flag = false;

                @Override
                public void focusGained(FocusEvent e) {
                    flag = true;
                    if (textField.getForeground() == Color.RED || textField.getForeground() == Color.GRAY) {
                        show.text_to_default(textField);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (flag == true) {
                        if (textField.getText().equals("")) {
                            show.text_error(textField, "请填入此项");
                        }
                    }
                    flag = false;
                }
            });
        }
    }


    private void confirmMousePressed(MouseEvent e) {
        boolean isDataEffective = true;
        String clsName = this.textClsName.getText().trim();
        String teaNum = this.textTeaNum.getText().trim();
        String stuCountStr = this.textStuCount.getText().trim();
        String gradeStr = this.ClassGrade.getText().trim();
        // check user input
        // 非空判断
        for (String str : new String[]{clsName, teaNum, stuCountStr}) {
            if (str.equals("") || str.equals("请填入此项")) {
                JOptionPane.showMessageDialog(null, "请将所有项输入", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        // textLesNum textClsId textLesYear 是否为整数
        for (JTextField textField : new JTextField[]{textTeaNum, textStuCount}) {
            if (!Common.isInteger(textField.getText().trim())) {
                show.text_error(textField, "Please enter digits");
                isDataEffective = false;
            }
        }
        if (!isDataEffective) {
            JOptionPane.showMessageDialog(null, "请将所有项填入", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int stuCount = Integer.parseInt(this.textStuCount.getText().trim());

        if (stuCount > 40) {
            show.text_error(textStuCount, "学生人数应少于40人");
            isDataEffective = false;
        } else if (stuCount < 0) {
            show.text_error(textStuCount, "学生人数至少为0人");
            isDataEffective = false;
        }
        if (teaNum.length() != 8) {
            show.text_error(textTeaNum, "教师编号应为八位数字");
            isDataEffective = false;
        } else if (new TeacherDaoImp().findById(teaNum) == null) {
            show.text_error(textTeaNum, teaNum + "不存在");
            isDataEffective = false;
        }

        if (Integer.parseInt(gradeStr) > 2099 || Integer.parseInt(gradeStr) < 1900) {
            JOptionPane.showMessageDialog(null, "年份应小于2099大于1900", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // save data
        if (!isDataEffective) return;
        ClassInfoDAOImp cii = new ClassInfoDAOImp();
        ClassInfo cls = new ClassInfo(clsName, gradeStr + "-1-1", stuCount, teaNum);
        String state = saveData(cls, cii) ? "成功" : "失败";
        JOptionPane.showMessageDialog(null, msg + "班级" + state, state, JOptionPane.INFORMATION_MESSAGE);
    }

    protected boolean saveData(ClassInfo cls, ClassInfoDAOImp cii) {
        // 信息验证成功 保存数据
        return cii.insert(cls);
    }

    private void chooseMousePressed(MouseEvent e) {
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("teaTypeId", "2");
        TeacherSelect teacherSelect = new TeacherSelect(true, staticAttr, this);

        teacherSelect.jDialog.setModal(true);
        teacherSelect.jDialog.setVisible(true);

        if (isConfirmChoice) {
            this.textTeaNum.setText(num);
        }
        isConfirmChoice = false;
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        classInfoFrame = new JDialog();
        confirm2 = new JButton();
        cancel = new JButton();
        ret = new JButton();
        label1 = new JLabel();
        label4 = new JLabel();
        button1 = new JButton();
        label8 = new JLabel();
        label9 = new JLabel();
        textClsName = new JTextField();
        ClassGrade = new DatePicker();
        textTeaNum = new JTextField();
        textStuCount = new JTextField();
        baseInfo = new JEditorPane();

        //======== classInfoFrame ========
        {
            classInfoFrame.setResizable(false);
            classInfoFrame.setTitle("\u65b0\u5efa\u73ed\u7ea7");
            classInfoFrame.setBackground(Color.gray);
            classInfoFrame.setName("teacherFrame");
            Container classInfoFrameContentPane = classInfoFrame.getContentPane();
            classInfoFrameContentPane.setLayout(null);

            //---- confirm2 ----
            confirm2.setText("Confirm");
            confirm2.setBorder(new EmptyBorder(5, 5, 5, 5));
            confirm2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    confirmMousePressed(e);
                }
            });
            classInfoFrameContentPane.add(confirm2);
            confirm2.setBounds(180, 355, 110, confirm2.getPreferredSize().height);

            //---- cancel ----
            cancel.setText("Cancel");
            cancel.setBorder(new EmptyBorder(5, 5, 5, 5));
            classInfoFrameContentPane.add(cancel);
            cancel.setBounds(365, 355, 110, cancel.getPreferredSize().height);

            //---- ret ----
            ret.setText("Return");
            ret.setBorder(new EmptyBorder(5, 5, 5, 5));
            classInfoFrameContentPane.add(ret);
            ret.setBounds(550, 355, 110, ret.getPreferredSize().height);

            //---- label1 ----
            label1.setText("\u73ed\u7ea7\u540d\u79f0");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            classInfoFrameContentPane.add(label1);
            label1.setBounds(100, 110, 75, 35);

            //---- label4 ----
            label4.setText("\u6559\u5e08\u7f16\u53f7");
            label4.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            classInfoFrameContentPane.add(label4);
            label4.setBounds(100, 205, 75, 35);

            //---- button1 ----
            button1.setText("\u9009\u62e9");
            button1.setBorder(LineBorder.createBlackLineBorder());
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    chooseMousePressed(e);
                }
            });
            classInfoFrameContentPane.add(button1);
            button1.setBounds(315, 205, 75, 30);

            //---- label8 ----
            label8.setText("\u73ed\u7ea7\u4eba\u6570");
            label8.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setHorizontalTextPosition(SwingConstants.CENTER);
            classInfoFrameContentPane.add(label8);
            label8.setBounds(430, 205, 75, 35);

            //---- label9 ----
            label9.setText("\u5e74\u7ea7");
            label9.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            classInfoFrameContentPane.add(label9);
            label9.setBounds(430, 110, 75, 35);

            //---- textClsName ----
            textClsName.setBorder(LineBorder.createBlackLineBorder());
            classInfoFrameContentPane.add(textClsName);
            textClsName.setBounds(190, 110, 200, 30);

            //---- ClassGrade ----
            ClassGrade.setBorder(LineBorder.createBlackLineBorder());
            classInfoFrameContentPane.add(ClassGrade);
            ClassGrade.setBounds(520, 110, 200, 30);

            //---- textTeaNum ----
            textTeaNum.setBorder(LineBorder.createBlackLineBorder());
            textTeaNum.setEditable(false);
            classInfoFrameContentPane.add(textTeaNum);
            textTeaNum.setBounds(190, 205, 125, 30);

            //---- textStuCount ----
            textStuCount.setBorder(LineBorder.createBlackLineBorder());
            classInfoFrameContentPane.add(textStuCount);
            textStuCount.setBounds(520, 205, 200, 30);

            //---- baseInfo ----
            baseInfo.setBorder(new TitledBorder(new LineBorder(Color.lightGray), "\u57fa\u672c\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                    new Font("\u96b6\u4e66", Font.BOLD, 16), Color.orange));
            baseInfo.setEditable(false);
            baseInfo.setBackground(Color.gray);
            classInfoFrameContentPane.add(baseInfo);
            baseInfo.setBounds(50, 30, 700, 285);

            classInfoFrameContentPane.setPreferredSize(new Dimension(810, 450));
            classInfoFrame.setSize(810, 450);
            classInfoFrame.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    protected JDialog classInfoFrame;
    private JButton confirm2;
    private JButton cancel;
    private JButton ret;
    private JLabel label1;
    private JLabel label4;
    private JButton button1;
    private JLabel label8;
    private JLabel label9;
    protected JTextField textClsName;
    protected DatePicker ClassGrade;
    protected JTextField textTeaNum;
    protected JTextField textStuCount;
    private JEditorPane baseInfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
