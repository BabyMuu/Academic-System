/*
 * Created by JFormDesigner on Sat Oct 23 17:29:28 CST 2021
 */

package frame.ca;

import dao.imp.ClassInfoDAOImp;
import dao.imp.CourseArrangementDAOImp;
import dao.imp.LessonDaoImp;
import dao.imp.TeacherDaoImp;
import entity.CourseArrangement;
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
public class CAFrame extends InfoFrame {
    private String msg = "新增";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CAFrame() {
        initComponents();
        initPersonalization();
        close();
    }

    public void close() {
        this.courseFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    ButtonGroup bg;

    public void initPersonalization() {
        // 聚焦到出错为文本框 清空文本框内容
        for (JTextField textField : new JTextField[]{textLesNum, textClsId, textLesYear, textTeaNum}) {
            textField.addFocusListener(new FocusListener() {
                boolean flag = false; // 文本框是否获取过焦点

                @Override
                public void focusGained(FocusEvent e) {
                    flag = true;
                    if (textField.getForeground() == Color.RED) {
                        show.text_to_default(textField);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (flag == true) {
                        try {
                            Integer.parseInt(textField.getText().trim());
                        } catch (NumberFormatException exception) {
                            show.text_error(textField, "Please enter digits");
                        }
                    }
                    flag = false;
                }
            });
        }
        // 将单选按钮分组
        bg = new ButtonGroup();
        bg.add(this.radioFirst);
        bg.add(this.radioSecond);
        // 设置桌布背景颜色
        this.courseFrame.getContentPane().setBackground(Color.GRAY);
        // 设置窗口默认关闭方式
        this.courseFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void confirmMousePressed(MouseEvent e) {
        boolean isDataEffective = true;
        String teaNum = this.textTeaNum.getText().trim();

        // check user input
        if (teaNum.equals("")) {
            JOptionPane.showMessageDialog(null, "请将所有项填入", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // textLesNum textClsId textLesYear 是否为整数
        for (JTextField textField : new JTextField[]{textLesNum, textClsId, textLesYear}) {
            if (!Common.isInteger(textField.getText().trim())) {
                show.text_error(textField, "Please enter digits");
                isDataEffective = false;
            }
        }
        if (!isDataEffective) {
            JOptionPane.showMessageDialog(null, "请将所有项填入", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int lesId = Integer.parseInt(this.textLesNum.getText().trim());
        int clsId = Integer.parseInt(this.textClsId.getText().trim());
        int year = Integer.parseInt(this.textLesYear.getText().trim());
        int semester = this.radioFirst.isSelected() ? 1 : 2;
        if (new LessonDaoImp().findById(lesId + "") == null) {
            show.text_error(this.textLesNum, "lesId isn't exists");
            isDataEffective = false;
        }
        if (new TeacherDaoImp().findById(teaNum) == null) {
            show.text_error(this.textTeaNum, "teacher isn't exists");
            isDataEffective = false;
        }
        if (new ClassInfoDAOImp().findById(clsId + "", 0, 0, false) == null) {
            show.text_error(this.textClsId, "clsid isn't exists");
            isDataEffective = false;
        }
        if (1970 > year || year > 2100) {
            show.text_error(this.textLesYear, "Limited 1970-2100");
            isDataEffective = false;
        }
        // save data
        if (!isDataEffective) return;
        CourseArrangementDAOImp cadi = new CourseArrangementDAOImp();
        CourseArrangement ca = new CourseArrangement(lesId, teaNum, clsId, year, semester);
        String state = saveData(ca, cadi) ? "成功" : "失败";
        JOptionPane.showMessageDialog(null, msg + "课程安排" + state, state, JOptionPane.INFORMATION_MESSAGE);
    }

    private void chooseMousePressed(MouseEvent e) {
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("teaTypeId", "1");
        TeacherSelect teacherSelect = new TeacherSelect(true, staticAttr, this);

        teacherSelect.jDialog.setModal(true);
        teacherSelect.jDialog.setVisible(true);
        if (isConfirmChoice) {
            this.textTeaNum.setText(num);
        }
        isConfirmChoice = false;
    }

    protected boolean saveData(CourseArrangement ca, CourseArrangementDAOImp cadi) {
        // 信息验证成功 保存数据
        return cadi.insert(ca);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        courseFrame = new JFrame();
        label1 = new JLabel();
        label4 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        textLesNum = new JTextField();
        textLesYear = new JTextField();
        textTeaNum = new JTextField();
        choice = new JButton();
        textField2 = new JTextField();
        radioFirst = new JRadioButton();
        radioSecond = new JRadioButton();
        textClsId = new JTextField();
        baseInfo = new JEditorPane();
        confirm2 = new JButton();
        cancel = new JButton();
        ret = new JButton();

        //======== courseFrame ========
        {
            courseFrame.setVisible(true);
            courseFrame.setResizable(false);
            courseFrame.setTitle("\u65b0\u5efa\u8bfe\u7a0b\u5b89\u6392");
            courseFrame.setBackground(Color.gray);
            courseFrame.setMaximizedBounds(new Rectangle(0, 0, 0, 0));
            courseFrame.setName("teacherFrame");
            Container courseFrameContentPane = courseFrame.getContentPane();
            courseFrameContentPane.setLayout(null);

            //---- label1 ----
            label1.setText("\u8bfe\u7a0b\u7f16\u53f7");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            courseFrameContentPane.add(label1);
            label1.setBounds(95, 90, 75, 35);

            //---- label4 ----
            label4.setText("\u6559\u5e08\u7f16\u53f7");
            label4.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            courseFrameContentPane.add(label4);
            label4.setBounds(95, 170, 75, 35);

            //---- label8 ----
            label8.setText("\u5b66\u671f");
            label8.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setHorizontalTextPosition(SwingConstants.CENTER);
            courseFrameContentPane.add(label8);
            label8.setBounds(425, 170, 75, 35);

            //---- label9 ----
            label9.setText("\u5b66\u5e74");
            label9.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            courseFrameContentPane.add(label9);
            label9.setBounds(425, 90, 75, 35);

            //---- label10 ----
            label10.setText("\u73ed\u7ea7\u7f16\u53f7");
            label10.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            courseFrameContentPane.add(label10);
            label10.setBounds(95, 245, 75, 35);

            //---- textLesNum ----
            textLesNum.setBorder(LineBorder.createBlackLineBorder());
            courseFrameContentPane.add(textLesNum);
            textLesNum.setBounds(185, 90, 200, 30);

            //---- textLesYear ----
            textLesYear.setBorder(LineBorder.createBlackLineBorder());
            textLesYear.setForeground(new Color(67, 73, 74));
            courseFrameContentPane.add(textLesYear);
            textLesYear.setBounds(515, 90, 200, 30);

            //---- textTeaNum ----
            textTeaNum.setBorder(LineBorder.createBlackLineBorder());
            courseFrameContentPane.add(textTeaNum);
            textTeaNum.setBounds(185, 170, 125, 30);

            //---- choice ----
            choice.setText("\u9009\u62e9");
            choice.setBorder(LineBorder.createBlackLineBorder());
            choice.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    chooseMousePressed(e);
                }
            });
            courseFrameContentPane.add(choice);
            choice.setBounds(310, 170, 75, 30);

            //---- textField2 ----
            textField2.setBorder(new MatteBorder(1, 1, 1, 0, Color.black));
            textField2.setBackground(new Color(238, 238, 238));
            courseFrameContentPane.add(textField2);
            textField2.setBounds(520, 170, 30, 30);

            //---- radioFirst ----
            radioFirst.setText("\u4e0a\u5b66\u671f");
            radioFirst.setSelected(true);
            radioFirst.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
            radioFirst.setBorderPainted(true);
            courseFrameContentPane.add(radioFirst);
            radioFirst.setBounds(550, 170, 85, 30);

            //---- radioSecond ----
            radioSecond.setText("\u4e0b\u5b66\u671f");
            radioSecond.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
            radioSecond.setBorderPainted(true);
            courseFrameContentPane.add(radioSecond);
            radioSecond.setBounds(635, 170, 85, 30);

            //---- textClsId ----
            textClsId.setBorder(LineBorder.createBlackLineBorder());
            courseFrameContentPane.add(textClsId);
            textClsId.setBounds(185, 245, 200, 30);

            //---- baseInfo ----
            baseInfo.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "\u57fa\u7840\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                    new Font("\u6977\u4f53", Font.BOLD, 16), Color.orange));
            baseInfo.setBackground(Color.gray);
            baseInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            baseInfo.setEditable(false);
            courseFrameContentPane.add(baseInfo);
            baseInfo.setBounds(55, 30, 700, 298);

            //---- confirm2 ----
            confirm2.setText("Confirm");
            confirm2.setBorder(new EmptyBorder(5, 5, 5, 5));
            confirm2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    confirmMousePressed(e);
                }
            });
            courseFrameContentPane.add(confirm2);
            confirm2.setBounds(180, 355, 110, confirm2.getPreferredSize().height);

            //---- cancel ----
            cancel.setText("Cancel");
            cancel.setBorder(new EmptyBorder(5, 5, 5, 5));
            courseFrameContentPane.add(cancel);
            cancel.setBounds(365, 355, 110, cancel.getPreferredSize().height);

            //---- ret ----
            ret.setText("Return");
            ret.setBorder(new EmptyBorder(5, 5, 5, 5));
            courseFrameContentPane.add(ret);
            ret.setBounds(550, 355, 110, ret.getPreferredSize().height);

            courseFrameContentPane.setPreferredSize(new Dimension(810, 450));
            courseFrame.setSize(810, 450);
            courseFrame.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    protected JFrame courseFrame;
    private JLabel label1;
    private JLabel label4;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    protected JTextField textLesNum;
    protected JTextField textLesYear;
    protected JTextField textTeaNum;
    private JButton choice;
    private JTextField textField2;
    protected JRadioButton radioFirst;
    protected JRadioButton radioSecond;
    protected JTextField textClsId;
    private JEditorPane baseInfo;
    private JButton confirm2;
    private JButton cancel;
    private JButton ret;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
