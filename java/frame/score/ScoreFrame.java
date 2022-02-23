/*
 * Created by JFormDesigner on Thu Oct 28 14:48:02 CST 2021
 */

package frame.score;

import dao.imp.ExamScheduleDAOImp;
import dao.imp.ScoreDAOImp;
import dao.imp.StudentDaoImp;
import entity.Score;
import lib.tools.Common;
import lib.tools.show;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class ScoreFrame extends JFrame {
    private String msg = "新增";
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ScoreFrame() {
        initComponents();
        initPersonalization();
        close();
    }
    public void close (){
        this.ScoreFrame.getContentPane().setBackground(Color.GRAY);
        this.ScoreFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void initPersonalization() {
        for (JTextField textField : new JTextField[]{textESNum,textStuNum,textScore}){
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
                    if (flag == true){
                        if(textField.getText().equals("")){
                            show.text_error(textField,"请填入此项");
                        }
                    }
                    flag = false;
                }
            });
        }

    }

    private void confirmMousePressed(MouseEvent e) {
        boolean isDataEffective = true;
        String esId = textESNum.getText();
        String stuNum = textStuNum.getText();
        String score = textScore.getText();
        // 非空判断
        for (String str : new String[]{esId,stuNum,score}){
            if (str.equals("") || str.equals("请填入此项")){
                JOptionPane.showMessageDialog(null, "请将所有项输入", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        // 长度判断
        if (stuNum.length() != 12) {
            show.text_error(textStuNum, "请输入十二位学生编号");
            isDataEffective = false;
        }else{
            if (new StudentDaoImp().findById(stuNum,1,1).size() == 0){
                show.text_error(textStuNum, "学生编号不存在");
                isDataEffective = false;
            }
        }
        // 是否为整数
        if (!Common.isInteger(textESNum.getText())) {
            show.text_error(textESNum, "请输入数字");
            isDataEffective = false;
        }
        if (!Common.isInteger(textScore.getText())) {
            show.text_error(textScore, "请输入数字");
            isDataEffective = false;
        }else{
            if(new ExamScheduleDAOImp().findById(esId).size() == 0){
                show.text_error(textStuNum, "考试编号不存在");
                isDataEffective = false;
            }
        }


        if (!isDataEffective) return;   // 信息验证不正确, 重新输入
        Score sco = new Score(Integer.parseInt(esId), stuNum, Integer.parseInt(score));
        ScoreDAOImp sdi = new ScoreDAOImp();
        String state = saveData(sco,sdi)? "成功": "失败";
        JOptionPane.showMessageDialog(null, msg + "分数" + state, state, JOptionPane.INFORMATION_MESSAGE);
    }
    protected boolean saveData(Score sco, ScoreDAOImp sdi){
        // 信息验证成功 保存数据
        return sdi.insert(sco);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        ScoreFrame = new JFrame();
        confirm2 = new JButton();
        cancel = new JButton();
        ret = new JButton();
        label1 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        textStuNum = new JTextField();
        textESNum = new JTextField();
        textScore = new JTextField();
        editorPane1 = new JEditorPane();

        //======== ScoreFrame ========
        {
            ScoreFrame.setVisible(true);
            ScoreFrame.setResizable(false);
            ScoreFrame.setTitle("\u65b0\u5efa\u5206\u6570");
            ScoreFrame.setBackground(Color.gray);
            ScoreFrame.setMaximizedBounds(new Rectangle(0, 0, 0, 0));
            ScoreFrame.setName("teacherFrame");
            Container ScoreFrameContentPane = ScoreFrame.getContentPane();
            ScoreFrameContentPane.setLayout(null);

            //---- confirm2 ----
            confirm2.setText("Confirm");
            confirm2.setBorder(new EmptyBorder(5, 5, 5, 5));
            confirm2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    confirmMousePressed(e);
                }
            });
            ScoreFrameContentPane.add(confirm2);
            confirm2.setBounds(180, 355, 110, confirm2.getPreferredSize().height);

            //---- cancel ----
            cancel.setText("Cancel");
            cancel.setBorder(new EmptyBorder(5, 5, 5, 5));
            ScoreFrameContentPane.add(cancel);
            cancel.setBounds(365, 355, 110, cancel.getPreferredSize().height);

            //---- ret ----
            ret.setText("Return");
            ret.setBorder(new EmptyBorder(5, 5, 5, 5));
            ScoreFrameContentPane.add(ret);
            ret.setBounds(550, 355, 110, ret.getPreferredSize().height);

            //---- label1 ----
            label1.setText("\u8003\u8bd5\u7f16\u53f7");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            ScoreFrameContentPane.add(label1);
            label1.setBounds(90, 110, 75, 35);

            //---- label8 ----
            label8.setText("\u6210\u7ee9");
            label8.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setHorizontalTextPosition(SwingConstants.CENTER);
            ScoreFrameContentPane.add(label8);
            label8.setBounds(250, 205, 75, 35);

            //---- label9 ----
            label9.setText("\u5b66\u751f\u7f16\u53f7");
            label9.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            ScoreFrameContentPane.add(label9);
            label9.setBounds(420, 110, 75, 35);

            //---- textStuNum ----
            textStuNum.setBorder(LineBorder.createBlackLineBorder());
            textStuNum.setForeground(new Color(67, 73, 74));
            ScoreFrameContentPane.add(textStuNum);
            textStuNum.setBounds(510, 110, 200, 30);

            //---- textESNum ----
            textESNum.setBorder(LineBorder.createBlackLineBorder());
            ScoreFrameContentPane.add(textESNum);
            textESNum.setBounds(180, 110, 200, 30);

            //---- textScore ----
            textScore.setBorder(LineBorder.createBlackLineBorder());
            ScoreFrameContentPane.add(textScore);
            textScore.setBounds(340, 205, 200, 30);

            //---- editorPane1 ----
            editorPane1.setBorder(new TitledBorder(new LineBorder(Color.lightGray), "\u57fa\u672c\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                new Font("\u96b6\u4e66", Font.BOLD, 16), Color.orange));
            editorPane1.setEditable(false);
            editorPane1.setBackground(Color.gray);
            ScoreFrameContentPane.add(editorPane1);
            editorPane1.setBounds(50, 30, 700, 285);

            ScoreFrameContentPane.setPreferredSize(new Dimension(810, 450));
            ScoreFrame.setSize(810, 450);
            ScoreFrame.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JFrame ScoreFrame;
    private JButton confirm2;
    private JButton cancel;
    private JButton ret;
    private JLabel label1;
    private JLabel label8;
    private JLabel label9;
    protected JTextField textStuNum;
    protected JTextField textESNum;
    protected JTextField textScore;
    private JEditorPane editorPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
