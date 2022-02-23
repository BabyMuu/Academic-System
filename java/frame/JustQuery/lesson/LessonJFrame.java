/*
 * Created by JFormDesigner on Sat Oct 23 18:29:02 CST 2021
 */

package frame.JustQuery.lesson;

import dao.imp.LessonDaoImp;
import entity.Lesson;
import lib.tools.Common;
import lib.tools.show;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class LessonJFrame extends JFrame {
    private String msg = "新增";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LessonJFrame() {
        initComponents();
        initPersonalization();
        close();
    }

    public void close() {
        this.lessonFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void initPersonalization() {
        this.lessonFrame.getContentPane().setBackground(Color.GRAY);
        this.lessonFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        // 聚焦到出错为文本框 清空文本框内容
        for (JTextField textField : new JTextField[]{textLesName, textLesContext, textScore, textHours}) {
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


    private void textLesNameFocusGained(FocusEvent e) {
        if (textLesName.getText().equals("长度小于20个汉字")) {
            show.text_to_default(textLesName);
        }
    }

    private void textLesContextFocusGained(FocusEvent e) {
        if (textLesContext.getText().equals("长度小于20个汉字")) {
            show.text_to_default(textLesContext);
        }
    }

    private void textHoursFocusGained(FocusEvent e) {
        if (textLesContext.getText().equals("请输入数字")) {
            show.text_to_default(textLesContext);
        }
    }

    private void textScoreFocusGained(FocusEvent e) {
        if (textLesContext.getText().equals("请输入数字")) {
            show.text_to_default(textLesContext);
        }
    }

    private void confirmMousePressed(MouseEvent e) {
        boolean isDataEffective = true;
        String lesName = this.textLesName.getText().trim();
        String content = this.textLesContext.getText().trim();
        // check user input
        // 非空判断
        for (String str : new String[]{lesName, content}) {
            if (str.equals("") || str.equals("请填入此项")) {
                JOptionPane.showMessageDialog(null, "请将所有项输入", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        // textLesNum textClsId textLesYear 是否为整数
        for (JTextField textField : new JTextField[]{textHours, textScore}) {
            if (!Common.isInteger(textField.getText().trim())) {
                show.text_error(textField, "Please enter digits");
                isDataEffective = false;
            }
        }
        if (!isDataEffective) {
            JOptionPane.showMessageDialog(null, "请将所有项填入", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int hours = Integer.parseInt(this.textHours.getText().trim());
        int score = Integer.parseInt(this.textScore.getText().trim());
        if (score > 6) {
            show.text_error(textScore, "学分值设置过大");
            isDataEffective = false;
        }
        if (hours > 80) {
            show.text_error(textHours, "学时过长");
            isDataEffective = false;
        }
        // save data
        if (!isDataEffective) return;
        LessonDaoImp ldi = new LessonDaoImp();
        Lesson les = new Lesson(lesName, content, score, hours);
        String state = saveData(les, ldi) ? "成功" : "失败";
        JOptionPane.showMessageDialog(null, msg + "课程" + state, state, JOptionPane.INFORMATION_MESSAGE);
    }

    protected boolean saveData(Lesson les, LessonDaoImp ldi) {
        return ldi.insert(les);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        lessonFrame = new JFrame();
        confirm2 = new JButton();
        cancel = new JButton();
        ret = new JButton();
        label1 = new JLabel();
        label4 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        textLesContext = new JTextField();
        textLesName = new JTextField();
        textHours = new JTextField();
        textScore = new JTextField();
        editorPane1 = new JEditorPane();

        //======== lessonFrame ========
        {
            lessonFrame.setVisible(true);
            lessonFrame.setResizable(false);
            lessonFrame.setTitle("\u65b0\u5efa\u8bfe\u7a0b");
            lessonFrame.setBackground(Color.gray);
            lessonFrame.setMaximizedBounds(new Rectangle(0, 0, 0, 0));
            lessonFrame.setName("teacherFrame");
            Container lessonFrameContentPane = lessonFrame.getContentPane();
            lessonFrameContentPane.setLayout(null);

            //---- confirm2 ----
            confirm2.setText("Confirm");
            confirm2.setBorder(new EmptyBorder(5, 5, 5, 5));
            confirm2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    confirmMousePressed(e);
                }
            });
            lessonFrameContentPane.add(confirm2);
            confirm2.setBounds(180, 355, 110, confirm2.getPreferredSize().height);

            //---- cancel ----
            cancel.setText("Cancel");
            cancel.setBorder(new EmptyBorder(5, 5, 5, 5));
            lessonFrameContentPane.add(cancel);
            cancel.setBounds(365, 355, 110, cancel.getPreferredSize().height);

            //---- ret ----
            ret.setText("Return");
            ret.setBorder(new EmptyBorder(5, 5, 5, 5));
            lessonFrameContentPane.add(ret);
            ret.setBounds(550, 355, 110, ret.getPreferredSize().height);

            //---- label1 ----
            label1.setText("\u8bfe\u7a0b\u540d\u79f0");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            lessonFrameContentPane.add(label1);
            label1.setBounds(90, 110, 75, 35);

            //---- label4 ----
            label4.setText("\u5b66\u65f6");
            label4.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            lessonFrameContentPane.add(label4);
            label4.setBounds(90, 205, 75, 35);

            //---- label8 ----
            label8.setText("\u5b66\u5206");
            label8.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setHorizontalTextPosition(SwingConstants.CENTER);
            lessonFrameContentPane.add(label8);
            label8.setBounds(420, 205, 75, 35);

            //---- label9 ----
            label9.setText("\u8bfe\u7a0b\u7b80\u4ecb");
            label9.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            lessonFrameContentPane.add(label9);
            label9.setBounds(420, 110, 75, 35);

            //---- textLesContext ----
            textLesContext.setBorder(LineBorder.createBlackLineBorder());
            textLesContext.setForeground(new Color(67, 73, 74));
            textLesContext.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    textLesContextFocusGained(e);
                }
            });
            lessonFrameContentPane.add(textLesContext);
            textLesContext.setBounds(510, 110, 200, 30);

            //---- textLesName ----
            textLesName.setBorder(LineBorder.createBlackLineBorder());
            textLesName.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    textLesNameFocusGained(e);
                }
            });
            lessonFrameContentPane.add(textLesName);
            textLesName.setBounds(180, 110, 200, 30);

            //---- textHours ----
            textHours.setBorder(LineBorder.createBlackLineBorder());
            textHours.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    textHoursFocusGained(e);
                }
            });
            lessonFrameContentPane.add(textHours);
            textHours.setBounds(180, 205, 200, 30);

            //---- textScore ----
            textScore.setBorder(LineBorder.createBlackLineBorder());
            textScore.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    textScoreFocusGained(e);
                }
            });
            lessonFrameContentPane.add(textScore);
            textScore.setBounds(510, 205, 200, 30);

            //---- editorPane1 ----
            editorPane1.setBorder(new TitledBorder(new LineBorder(Color.lightGray), "\u57fa\u672c\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                    new Font("\u96b6\u4e66", Font.BOLD, 16), Color.orange));
            editorPane1.setEditable(false);
            editorPane1.setBackground(Color.gray);
            lessonFrameContentPane.add(editorPane1);
            editorPane1.setBounds(50, 30, 700, 285);

            lessonFrameContentPane.setPreferredSize(new Dimension(810, 450));
            lessonFrame.setSize(810, 450);
            lessonFrame.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JFrame lessonFrame;
    private JButton confirm2;
    private JButton cancel;
    private JButton ret;
    private JLabel label1;
    private JLabel label4;
    private JLabel label8;
    private JLabel label9;
    protected JTextField textLesContext;
    protected JTextField textLesName;
    protected JTextField textHours;
    protected JTextField textScore;
    private JEditorPane editorPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

