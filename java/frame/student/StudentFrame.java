/*
 * Created by JFormDesigner on Wed Oct 20 22:39:44 CST 2021
 */

package frame.student;

import com.eltima.components.ui.*;
import dao.imp.ClassInfoDAOImp;
import dao.imp.StudentDaoImp;
import entity.ClassInfo;
import entity.Student;

import frame.Login.LoginFrame;
import lib.tools.Common;
import lib.tools.show;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class StudentFrame extends JPanel {
    protected String msg = "新增";
    private String grade = "2020";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public StudentFrame() {
        initComponents();
        initPersonalization();
        close();
    }

    public StudentFrame(String grade) {
        this.grade = grade;
        initComponents();
        initPersonalization();
        close();
    }

    public void close() {
        this.frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void confirmMousePressed(MouseEvent e) {
        String stuName = this.textStuName.getText();
        String stuNum = this.textStuNum.getText();
        String password = new String(pwd.getPassword());
        // 非空判断
        if (stuName.equals("") || stuNum.equals("") || password.equals("") || DatastuBtd.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "请将所有项输入", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        boolean isDataEffective = true;
        // stuNum 长度检测
        if (stuNum.length() != 12) {
            show.text_error(textStuNum, "请输入十二位学号");
            isDataEffective = false;
        }
        if (stuName.length() > 20) {
            show.text_error(textStuName, "长度小于10个汉字");
            isDataEffective = false;
        }
        // stuNum 是否存在检测
        if (msg.equals("新增") && new StudentDaoImp().findById(stuNum, 1, 1) != null) {
            show.text_error(textStuNum, stuNum + "已经存在");
            isDataEffective = false;
        }

        System.out.println(this.selectCls.getSelectedItem().toString() + " " + grade);
        int clsId = new ClassInfoDAOImp().findByNameAndGrade(this.selectCls.getSelectedItem().toString(), grade);
        System.out.println("clsId: " + clsId);
        Date stuBtd = Common.stringChangeToDate(DatastuBtd.getText());
        int sta = selectState.getSelectedIndex() + 1;
        boolean isMale = radioMale.isSelected();
        int roleId = Integer.parseInt(textRoleId.getText());

        System.out.println(sta);
        if (!isDataEffective) return;
        Student stu = new Student(stuNum, clsId, stuName, stuBtd, password, sta, isMale, roleId);
        StudentDaoImp sdi = new StudentDaoImp();
        String state = this.saveData(stu, sdi) ? "成功" : "失败";
        JOptionPane.showMessageDialog(null, msg + "学生" + state, state, JOptionPane.INFORMATION_MESSAGE);
    }

    protected boolean saveData(Student stu, StudentDaoImp sdi) {
        System.out.println(stu);
        return sdi.insert(stu);
    }

    private void initPersonalization() {
        this.frame1.getContentPane().setBackground(Color.GRAY);
        this.frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        for (ClassInfo cls : new ClassInfoDAOImp().findByGrade(grade, false)) {
            selectCls.addItem(cls.getClsName());
        }

        this.DatastuBtd.a("yyyy-MM-dd");    // 日期格式

        selectState.addItem("正常");
        selectState.addItem("停学");
        selectState.addItem("留校察看");

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioMale);
        bg.add(radioFemale);
        // 聚焦到出错为文本框 清空文本框内容
        if (!LoginFrame.ROLE.equals("Student"))
            for (JTextField textField : new JTextField[]{textStuName, textStuNum, textRoleId}) {
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
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frame1.dispose();
            }
        });
        ret.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frame1.dispose();
            }
        });
        show.text_pre_msg(textStuName, "长度小于10个汉字");
        show.text_pre_msg(textStuNum, "请输入十二位学号");
    }

    public JFrame frame1;

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        frame1 = new JFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        textStuName = new JTextField();
        textStuNum = new JTextField();
        selectCls = new JComboBox();
        textField2 = new JTextField();
        radioMale = new JRadioButton();
        radioFemale = new JRadioButton();
        DatastuBtd = new DatePicker();
        panel1 = new JPanel();
        selectState = new JComboBox();
        label7 = new JLabel();
        textRoleId = new JTextField();
        label4 = new JLabel();
        label8 = new JLabel();
        pwd = new JPasswordField();
        confirm = new JButton();
        cancel = new JButton();
        ret = new JButton();
        baseInfo = new JEditorPane();
        textIntroduction = new JTextPane();

        //======== frame1 ========
        {
            frame1.setVisible(true);
            frame1.setResizable(false);
            frame1.setTitle("\u5b66\u751f\u6ce8\u518c");
            frame1.setBackground(Color.gray);
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //---- label1 ----
            label1.setText("\u59d3\u540d");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label1);
            label1.setBounds(85, 50, 75, 35);

            //---- label2 ----
            label2.setText("\u73ed\u7ea7");
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

            //---- label5 ----
            label5.setText("\u7f16\u53f7");
            label5.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label5);
            label5.setBounds(415, 50, 75, 35);

            //---- label6 ----
            label6.setText("\u6027\u522b");
            label6.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label6);
            label6.setBounds(415, 115, 75, 35);

            //---- textStuName ----
            textStuName.setBorder(LineBorder.createBlackLineBorder());
            frame1ContentPane.add(textStuName);
            textStuName.setBounds(175, 50, 200, 30);

            //---- textStuNum ----
            textStuNum.setBorder(LineBorder.createBlackLineBorder());
            textStuNum.setForeground(new Color(67, 73, 74));
            frame1ContentPane.add(textStuNum);
            textStuNum.setBounds(510, 50, 200, 30);

            //---- selectCls ----
            selectCls.setBorder(LineBorder.createBlackLineBorder());
            frame1ContentPane.add(selectCls);
            selectCls.setBounds(175, 115, 200, 30);

            //---- textField2 ----
            textField2.setBorder(new MatteBorder(1, 1, 1, 0, Color.black));
            textField2.setBackground(new Color(238, 238, 238));
            frame1ContentPane.add(textField2);
            textField2.setBounds(510, 115, 30, 30);

            //---- radioMale ----
            radioMale.setText("\u7537\u6027");
            radioMale.setSelected(true);
            radioMale.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
            radioMale.setBorderPainted(true);
            frame1ContentPane.add(radioMale);
            radioMale.setBounds(540, 115, 85, 30);

            //---- radioFemale ----
            radioFemale.setText("\u5973\u6027");
            radioFemale.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
            radioFemale.setBorderPainted(true);
            frame1ContentPane.add(radioFemale);
            radioFemale.setBounds(625, 115, 85, 30);

            //---- DatastuBtd ----
            DatastuBtd.setBorder(LineBorder.createBlackLineBorder());
            frame1ContentPane.add(DatastuBtd);
            DatastuBtd.setBounds(175, 180, 200, 30);

            //======== panel1 ========
            {
                panel1.setBackground(Color.gray);
                panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
                        javax.swing.border.EmptyBorder(0, 0, 0, 0), "", javax
                        .swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java
                        .awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt
                        .Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.
                    PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("\u0062ord\u0065r".
                            equals(e.getPropertyName())) throw new RuntimeException();
                }
            });
                panel1.setLayout(null);

                //---- selectState ----
                selectState.setBorder(LineBorder.createBlackLineBorder());
                panel1.add(selectState);
                selectState.setBounds(450, 30, 200, 30);

                //---- label7 ----
                label7.setText("\u72b6\u6001");
                label7.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
                label7.setHorizontalAlignment(SwingConstants.CENTER);
                panel1.add(label7);
                label7.setBounds(355, 30, 75, 35);

                //---- textRoleId ----
                textRoleId.setBorder(LineBorder.createBlackLineBorder());
                panel1.add(textRoleId);
                textRoleId.setBounds(115, 95, 200, 30);

                //---- label4 ----
                label4.setText("\u89d2\u8272\u7f16\u53f7");
                label4.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                label4.setHorizontalTextPosition(SwingConstants.CENTER);
                panel1.add(label4);
                label4.setBounds(25, 90, 75, 35);

                //---- label8 ----
                label8.setText("\u5bc6\u7801");
                label8.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
                label8.setHorizontalAlignment(SwingConstants.CENTER);
                panel1.add(label8);
                label8.setBounds(355, 90, 75, 35);

                //---- pwd ----
                pwd.setBorder(LineBorder.createBlackLineBorder());
                panel1.add(pwd);
                pwd.setBounds(450, 90, 200, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            frame1ContentPane.add(panel1);
            panel1.setBounds(60, 150, 670, 145);

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
            confirm.setBounds(160, 500, 110, confirm.getPreferredSize().height);

            //---- cancel ----
            cancel.setText("Cancel");
            cancel.setBorder(new EmptyBorder(5, 5, 5, 5));
            frame1ContentPane.add(cancel);
            cancel.setBounds(350, 500, 110, cancel.getPreferredSize().height);

            //---- ret ----
            ret.setText("Return");
            ret.setBorder(new EmptyBorder(5, 5, 5, 5));
            frame1ContentPane.add(ret);
            ret.setBounds(540, 500, 110, ret.getPreferredSize().height);

            //---- baseInfo ----
            baseInfo.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "\u57fa\u7840\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                    new Font("\u6977\u4f53", Font.BOLD, 16), Color.orange));
            baseInfo.setBackground(Color.gray);
            baseInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            baseInfo.setEditable(false);
            frame1ContentPane.add(baseInfo);
            baseInfo.setBounds(55, 5, 700, 298);

            //---- textIntroduction ----
            textIntroduction.setBorder(new TitledBorder(null, "\u5b66\u751f\u7b80\u4ecb", TitledBorder.CENTER, TitledBorder.TOP,
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
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label5;
    private JLabel label6;
    protected JTextField textStuName;
    protected JTextField textStuNum;
    protected JComboBox selectCls;
    private JTextField textField2;
    protected JRadioButton radioMale;
    protected JRadioButton radioFemale;
    protected DatePicker DatastuBtd;
    public JPanel panel1;
    protected JComboBox selectState;
    private JLabel label7;
    protected JTextField textRoleId;
    private JLabel label4;
    private JLabel label8;
    protected JPasswordField pwd;
    public JButton confirm;
    private JButton cancel;
    private JButton ret;
    private JEditorPane baseInfo;
    private JTextPane textIntroduction;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
