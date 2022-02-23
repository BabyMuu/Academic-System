/*
 * Created by JFormDesigner on Thu Oct 28 15:11:39 CST 2021
 */

package frame.examSche;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;

import com.eltima.components.ui.*;
import dao.imp.CourseArrangementDAOImp;
import dao.imp.ExamScheduleDAOImp;
import entity.ExamSchedule;
import lib.tools.Common;
import lib.tools.show;

/**
 * @author unknown
 */
public class ESJFrame extends JFrame {
    private String msg = "新增";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ESJFrame() {
        initComponents();
        initPersonalization();
        setFrame();
    }

    public void setFrame() {
        this.esFrame.getContentPane().setBackground(Color.GRAY);
        this.esFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void initPersonalization() {
        this.Dataexam.a("yyyy-MM-dd");
        for (JTextField textField : new JTextField[]{textCAId, textContext, textState}) {
            textField.addFocusListener(new FocusListener() {
                boolean flag = false; // 文本框是否获取过焦点

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
        show.text_pre_msg(textState, "1: 结束 2: 未开始 3:正在考试中");
    }

    private void confirmMousePressed(MouseEvent e) {
        boolean isDataEffective = true;
        String caId = textCAId.getText();
        String context = textContext.getText();
        String stateStr = textState.getText();
        String examTime = Dataexam.getText();
        // 非空判断
        for (String str : new String[]{caId, context, stateStr, examTime}) {
            if (str.equals("") || str.equals("请填入此项")) {
                JOptionPane.showMessageDialog(null, "请将所有项输入", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

        }
        // 长度判断
        if (context.length() > 20) {
            show.text_error(textContext, "长度不应超过二十个字符");
            isDataEffective = false;
        }
        // 是否为整数
        if (!Common.isInteger(caId)) {
            show.text_error(textCAId, "请输入数字");
            isDataEffective = false;
        } else if (new CourseArrangementDAOImp().findById(caId, 0,0, false).size() == 0) {
            show.text_error(textCAId, "没有此课程编号");
            isDataEffective = false;
        }

        Date examData = Common.stringChangeToDate(examTime);
        if (!isDataEffective) return;   // 信息验证不正确, 重新输入


        ExamScheduleDAOImp edi = new ExamScheduleDAOImp();

        ExamSchedule exam = new ExamSchedule(Integer.parseInt(caId), examData, Integer.parseInt(stateStr), context);

        String state = saveData(exam, edi) ? "成功" : "失败";
        JOptionPane.showMessageDialog(null, msg + "考试" + state, state, JOptionPane.INFORMATION_MESSAGE);
    }

    protected boolean saveData(ExamSchedule exam,ExamScheduleDAOImp edi) {
        // 信息验证成功 保存数据
        return edi.insert(exam);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        esFrame = new JFrame();
        confirm2 = new JButton();
        cancel = new JButton();
        ret = new JButton();
        label1 = new JLabel();
        label4 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        textContext = new JTextField();
        textCAId = new JTextField();
        Dataexam = new DatePicker();
        textState = new JTextField();
        editorPane1 = new JEditorPane();

        //======== esFrame ========
        {
            esFrame.setVisible(true);
            esFrame.setResizable(false);
            esFrame.setTitle("\u65b0\u5efa\u8bfe\u7a0b");
            esFrame.setBackground(Color.gray);
            esFrame.setMaximizedBounds(new Rectangle(0, 0, 0, 0));
            esFrame.setName("teacherFrame");
            Container esFrameContentPane = esFrame.getContentPane();
            esFrameContentPane.setLayout(null);

            //---- confirm2 ----
            confirm2.setText("Confirm");
            confirm2.setBorder(new EmptyBorder(5, 5, 5, 5));
            confirm2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    confirmMousePressed(e);
                }
            });
            esFrameContentPane.add(confirm2);
            confirm2.setBounds(180, 355, 110, confirm2.getPreferredSize().height);

            //---- cancel ----
            cancel.setText("Cancel");
            cancel.setBorder(new EmptyBorder(5, 5, 5, 5));
            esFrameContentPane.add(cancel);
            cancel.setBounds(365, 355, 110, cancel.getPreferredSize().height);

            //---- ret ----
            ret.setText("Return");
            ret.setBorder(new EmptyBorder(5, 5, 5, 5));
            esFrameContentPane.add(ret);
            ret.setBounds(550, 355, 110, ret.getPreferredSize().height);

            //---- label1 ----
            label1.setText("\u8bfe\u7a0b\u5b89\u6392\u7f16\u53f7");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            esFrameContentPane.add(label1);
            label1.setBounds(60, 110, 105, 35);

            //---- label4 ----
            label4.setText("\u8003\u8bd5\u65f6\u95f4");
            label4.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            esFrameContentPane.add(label4);
            label4.setBounds(90, 205, 75, 35);

            //---- label8 ----
            label8.setText("\u72b6\u6001");
            label8.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setHorizontalTextPosition(SwingConstants.CENTER);
            esFrameContentPane.add(label8);
            label8.setBounds(420, 205, 75, 35);

            //---- label9 ----
            label9.setText("\u8003\u8bd5\u6982\u8ff0");
            label9.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            esFrameContentPane.add(label9);
            label9.setBounds(420, 110, 75, 35);

            //---- textContext ----
            textContext.setBorder(LineBorder.createBlackLineBorder());
            textContext.setForeground(new Color(67, 73, 74));
            esFrameContentPane.add(textContext);
            textContext.setBounds(510, 110, 200, 30);

            //---- textCAId ----
            textCAId.setBorder(LineBorder.createBlackLineBorder());
            esFrameContentPane.add(textCAId);
            textCAId.setBounds(180, 110, 200, 30);

            //---- Dataexam ----
            Dataexam.setBorder(LineBorder.createBlackLineBorder());
            Dataexam.setForeground(new Color(102, 102, 102));
            esFrameContentPane.add(Dataexam);
            Dataexam.setBounds(180, 205, 200, 30);

            //---- textState ----
            textState.setBorder(LineBorder.createBlackLineBorder());
            esFrameContentPane.add(textState);
            textState.setBounds(510, 205, 200, 30);

            //---- editorPane1 ----
            editorPane1.setBorder(new TitledBorder(new LineBorder(Color.lightGray), "\u57fa\u672c\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                    new Font("\u96b6\u4e66", Font.BOLD, 16), Color.orange));
            editorPane1.setEditable(false);
            editorPane1.setBackground(Color.gray);
            esFrameContentPane.add(editorPane1);
            editorPane1.setBounds(50, 30, 700, 285);

            esFrameContentPane.setPreferredSize(new Dimension(810, 450));
            esFrame.setSize(810, 450);
            esFrame.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JFrame esFrame;
    private JButton confirm2;
    private JButton cancel;
    private JButton ret;
    private JLabel label1;
    private JLabel label4;
    private JLabel label8;
    private JLabel label9;
    protected JTextField textContext;
    protected JTextField textCAId;
    protected DatePicker Dataexam;
    protected JTextField textState;
    private JEditorPane editorPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
