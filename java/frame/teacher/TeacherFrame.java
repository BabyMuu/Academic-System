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
    private String msg = "����"; // ��ǰ������Ϊ��

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TeacherFrame() {
        initComponents();  // ��ʼ������Ǽ�
        initPersonalization(); // ��ʼ�����Ի�����
        close(); // ���ô���رշ�ʽ
    }

    public void close() {
        this.frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    ButtonGroup bg;  // ����ѡ��ť��

    private void initPersonalization() {
        this.frame1.setModal(true);  // ����������Ϊģʽ����
        // �������ı�������¼�����
        for (JTextField textField : new JTextField[]{textTeaName, textTeaNum, textRoleId}) {
            textField.addFocusListener(new FocusListener() {
                boolean flag = false; // �ı����Ƿ��ȡ�������ʶ

                @Override
                public void focusGained(FocusEvent e) { // �����ȡ
                    flag = true; // ���ı�ʶ
                    if (textField.getForeground() == Color.RED || textField.getForeground() == Color.GRAY) {
                        show.text_to_default(textField); // �ı���ʽ����Ĭ����ʽ
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // �����ù�����, ��û�������κ����� ����ʾ�û�
                    if (flag && textField.getText().equals(""))
                        show.text_error(textField, "���������");
                    flag = false;  // ���ñ�ʶ
                }
            });
        }
        this.frame1.getContentPane().setBackground(Color.GRAY); // ���ù��������ı�����ɫ
        this.bg = new ButtonGroup(); // ����ѡ��ť��
        this.bg.add(radioTea); // ������ѡ��ť��ӵ�����
        this.bg.add(radioInstructor); // ������ѡ��ť��ӵ�����
        this.DataTeaBtd.a("yyyy-MM-dd"); // �������ڸ�ʽ, ��-��-��

        show.text_pre_msg(this.textTeaName, "���Ȳ�����ʮ������");   // �����ı���Ĭ����ʾ
        show.text_pre_msg(this.textTeaNum, "�������λ��ʦ���");    // �����ı���Ĭ����ʾ

        // �������б������Ԫ��
        this.selectTeaTitle.addItem("����");
        this.selectTeaTitle.addItem("����ʦ");
        this.selectTeaTitle.addItem("��ʦ");
        this.selectTeaTitle.addItem("����");

        // �������б������Ԫ��
        this.selectState.addItem("Normal");
        this.selectState.addItem("Holiday");
        this.selectState.addItem("Suspended");

    }

    private void confirmMousePressed(MouseEvent e) {
        boolean isDataEffective = true; // �����Ƿ���Ч(�û���д�����Ƿ���ȷ)
        String teaNum = textTeaNum.getText();
        String teaName = textTeaName.getText();
        String password = new String(pwd.getPassword());
        String teaBtdStr = DataTeaBtd.getText();
        int sta = selectState.getSelectedIndex() + 1;
        // �ǿ��ж�
        for (String str : new String[]{teaNum, teaName, password, teaBtdStr}) {
            if (str.equals("") || str.equals("���������")) {
                JOptionPane.showMessageDialog(null, "�뽫����������", "����", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        // �����ж�
        if (teaNum.length() != 8) {
            show.text_error(textTeaNum, "�������λ��ʦ���");
            isDataEffective = false;
        }
        if (teaName.length() > 20) {
            show.text_error(textTeaName, "���Ȳ�����ʮ������");
            isDataEffective = false;
        }
        // �Ƿ�Ϊ����
        if (!Common.isInteger(textRoleId.getText())) {
            show.text_error(textRoleId, "����������");
            isDataEffective = false;
        }

        if (msg.equals("����") && new TeacherDaoImp().findById(teaNum) != null) {
            show.text_error(textTeaNum, teaNum + "�Ѿ�����");
            isDataEffective = false;
        }

        if (!isDataEffective) return;   // ��Ϣ��֤����ȷ, ��������
        // ��ȡ����֤��Ϣ
        int roleId = Integer.parseInt(textRoleId.getText());
        String teaTitle = selectTeaTitle.getSelectedItem().toString();
        String teaTypeId = radioTea.isSelected() ? "1" : "2";
        Date teaBtd = Common.stringChangeToDate(teaBtdStr);
        // ������ʦʵ��
        Teacher tea = new Teacher(teaNum, teaName, teaTitle, teaTypeId, teaBtd, password, sta, roleId);
        // ��������
        String state = saveData(tea, new TeacherDaoImp()) ? "�ɹ�" : "ʧ��";
        // ��ʾ��������Ƿ�ɹ�
        JOptionPane.showMessageDialog(null, msg + "��ʦ" + state, state, JOptionPane.INFORMATION_MESSAGE);
    }

    protected boolean saveData(Teacher tea, TeacherDaoImp tdi) {
        // ��Ϣ��֤�ɹ� ��������
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
