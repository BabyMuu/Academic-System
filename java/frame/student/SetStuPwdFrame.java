package frame.student;

import dao.imp.RestPwdDaoImp;
import entity.RestPwd;
import frame.Login.BaseDialog;

import java.awt.*;
import java.awt.event.*;

import lib.tools.show;

import javax.swing.*;


public class SetStuPwdFrame extends BaseDialog {
    String stuNum;
    String msg;

    private JTextField txtNum;
    private JTextField txtA3;
    private JTextField txtA2;
    private JTextField txtA1;
    private JTextField txtQ1;
    private JTextField txtQ2;
    private JTextField txtQ3;
    private JButton btnCheckA;
    private JButton confirmSubmission;

    public SetStuPwdFrame(String stuNum) {
        this.stuNum = stuNum;
        setSize(430, 545);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setTitle("�ܱ�����");
        JLabel label = new JLabel("�һ�����");
        label.setFont(new Font("����", Font.PLAIN, 16));
        label.setBounds(167, 23, 114, 29);
        mainPanel.add(label);

        txtNum = new JTextField();
        txtNum.setColumns(10);
        txtNum.setBounds(135, 62, 146, 21);
        txtNum.setText(stuNum);
        txtNum.setEnabled(false);
        mainPanel.add(txtNum);

        JLabel label_1 = new JLabel("ѧ�����:");
        label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_1.setBounds(50, 58, 74, 27);
        mainPanel.add(label_1);

        JLabel label_2 = new JLabel("����һ    :");
        label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_2.setBounds(50, 103, 74, 27);
        mainPanel.add(label_2);

        JLabel label_3 = new JLabel("��һ    :");
        label_3.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_3.setBounds(50, 140, 74, 27);
        mainPanel.add(label_3);

        JLabel label_4 = new JLabel("�����    :");
        label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_4.setBounds(50, 193, 74, 27);
        mainPanel.add(label_4);

        JLabel label_5 = new JLabel("�𰸶�    :");
        label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_5.setBounds(50, 230, 74, 27);
        mainPanel.add(label_5);

        JLabel label_6 = new JLabel("������    :");
        label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_6.setBounds(50, 277, 74, 27);
        mainPanel.add(label_6);

        JLabel label_7 = new JLabel("����    :");
        label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_7.setBounds(50, 314, 74, 27);
        mainPanel.add(label_7);


        btnCheckA = new JButton("��֤��");
        btnCheckA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setInfoMsg("");
                String a1 = txtA1.getText();
                if (a1.trim().length() == 0) {
                    setErrorMsg("��һ����Ϊ��!");
                    return;
                }
                String a2 = txtA2.getText();
                if (a2.trim().length() == 0) {
                    setErrorMsg("�𰸶�����Ϊ��!");
                    return;
                }
                String a3 = txtA3.getText();
                if (a2.trim().length() == 0) {
                    setErrorMsg("��������Ϊ��!");
                    return;
                }
                String stuNum = txtNum.getText();
                RestPwdDaoImp restPwdDAO = new RestPwdDaoImp();
                //biz
                RestPwd restPwd = restPwdDAO.findById(stuNum);
                if (restPwd.getRequestCount() >= 5) {
                    setErrorMsg("�ش�������������þ�,����ϵ����Ա!");
                    return;
                }
                if (a1.equals(restPwd.getAns1()) && a2.equals(restPwd.getAns2()) && a3.equals(restPwd.getAns3())) {
                    restPwdDAO.updateRequestNum(stuNum, 0);
                    setSuccessMsg("�ܱ���֤��ȷ, �����������ܱ�");
                    btnCheckA.setVisible(false);
                    msg = "�޸�";
                    setRestPwd();
                } else {
                    setErrorMsg("������ʾ�������, ����" + (5 - restPwd.getRequestCount()) + "�λ���!");
                    restPwdDAO.updateRequestNum(stuNum, restPwd.getRequestCount() + 1);
                }
            }
        });
        btnCheckA.setBounds(189, 349, 93, 23);
        mainPanel.add(btnCheckA);

        txtA1 = new JTextField();
        txtA1.setColumns(10);
        txtA1.setBounds(135, 144, 236, 21);
        mainPanel.add(txtA1);

        txtA2 = new JTextField();
        txtA2.setColumns(10);
        txtA2.setBounds(135, 234, 236, 21);
        mainPanel.add(txtA2);

        txtA3 = new JTextField();
        txtA3.setColumns(10);
        txtA3.setBounds(135, 318, 236, 21);
        mainPanel.add(txtA3);

        txtQ1 = new JTextField();
        txtQ1.setEditable(false);
        txtQ1.setColumns(10);
        txtQ1.setBounds(135, 103, 236, 21);
        mainPanel.add(txtQ1);

        txtQ2 = new JTextField();
        txtQ2.setEditable(false);
        txtQ2.setColumns(10);
        txtQ2.setBounds(135, 193, 236, 21);
        mainPanel.add(txtQ2);

        txtQ3 = new JTextField();
        txtQ3.setEditable(false);
        txtQ3.setColumns(10);
        txtQ3.setBounds(135, 277, 236, 21);
        mainPanel.add(txtQ3);

        JButton btnLoadQ = new JButton("��������");
        btnLoadQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String stuNum = txtNum.getText();
                RestPwdDaoImp restPwdDAO = new RestPwdDaoImp();
                RestPwd restPwd = restPwdDAO.findById(stuNum);
                if (restPwd == null) {
                    JOptionPane.showMessageDialog(null, "����δ�����ܱ�����, �������ܱ�����");
                    setInfoMsg("�ܱ����⼰�𰸳���������20���ַ�����");
                    msg = "����";
                    btnCheckA.setVisible(false);
                    setRestPwd();
                } else {
                    if (restPwd.getRequestCount() == 5) {
                        setErrorMsg("�ش�������������þ�,����ϵ����Ա!");
                    } else {
                        setInfoMsg("����" + (5 - restPwd.getRequestCount()) + "�λش����!");
                        txtA1.setText("");
                        txtA2.setText("");
                        txtA3.setText("");
                        txtQ1.setText(restPwd.getQues1());
                        txtQ2.setText(restPwd.getQues2());
                        txtQ3.setText(restPwd.getQues3());
                    }
                }
            }
        });
        btnLoadQ.setBounds(278, 62, 93, 21);
        mainPanel.add(btnLoadQ);

        for (JTextField o : new JTextField[]{txtA1, txtA2, txtA3, txtQ1, txtQ2, txtQ3}) {
            o.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (o.getForeground() == Color.RED) {
                        show.text_to_default(o);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (o.isEditable() && o.getText().equals("")) {
                        show.text_error(o, "�����Ϊ��");
                    }
                }
            });
        }
    }

    private void retDefault() {
        for (JTextField o : new JTextField[]{txtA1, txtA2, txtA3, txtQ1, txtQ2, txtQ3}) {
            show.text_to_default(o);
        }
        for (JTextField o : new JTextField[]{txtQ1, txtQ2, txtQ3}) {
            o.setEditable(false);
        }
        confirmSubmission.setVisible(false);
        btnCheckA.setVisible(true);
    }

    private void setRestPwd() {
        for (JTextField o : new JTextField[]{txtQ1, txtQ2, txtQ3}) {
            o.setEditable(true);
        }
        confirmSubmission = new JButton();
        confirmSubmission.setBounds(278, 349, 93, 23);
        confirmSubmission.setText("ȷ���޸�");
        mainPanel.add(confirmSubmission);
        confirmSubmission.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean flag = false;
                for (JTextField o : new JTextField[]{txtA1, txtA2, txtA3, txtQ1, txtQ2, txtQ3}) {
                    o.setFocusable(true);
                    if (o.getText().equals("")) {
                        show.text_error(o, "�����Ϊ��");
                        flag = true;
                    } else if (o.getText().length() > 20) {
                        show.text_error(o, "����Ȳ�Ӧ����20���ַ�");
                        flag = true;
                    }
                }
                if (flag) return;
                RestPwdDaoImp rpdi = new RestPwdDaoImp();
                RestPwd restPwd = new RestPwd(stuNum,
                        txtQ1.getText().trim(), txtA1.getText().trim(),
                        txtQ2.getText().trim(), txtA2.getText().trim(),
                        txtQ3.getText().trim(), txtA3.getText().trim(),
                        0);
                String successOrFail;
                int choiceIndex = -1;
                if (msg.equals("����")) {
                    if (rpdi.insert(restPwd)) {
                        choiceIndex = JOptionPane.showConfirmDialog(null, "�ܱ��������óɹ�", "�Ƿ񷵻�", JOptionPane.OK_OPTION);
                    } else {
                        choiceIndex = JOptionPane.showConfirmDialog(null, "��������ʧ��", "ʧ��", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if (rpdi.update(restPwd)) {
                        choiceIndex = JOptionPane.showConfirmDialog(null, "�ܱ������޸ĳɹ�", "�Ƿ񷵻�", JOptionPane.OK_OPTION);
                    } else {
                        choiceIndex = JOptionPane.showConfirmDialog(null, "�����޸�ʧ��", "ʧ��", JOptionPane.WARNING_MESSAGE);
                    }
                }
                if (choiceIndex == 0) {
                    dispose();
                } else {
                    retDefault();
                }
            }
        });
    }
}
