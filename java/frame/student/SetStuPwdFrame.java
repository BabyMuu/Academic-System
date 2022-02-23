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
        this.setTitle("密保问题");
        JLabel label = new JLabel("找回密码");
        label.setFont(new Font("宋体", Font.PLAIN, 16));
        label.setBounds(167, 23, 114, 29);
        mainPanel.add(label);

        txtNum = new JTextField();
        txtNum.setColumns(10);
        txtNum.setBounds(135, 62, 146, 21);
        txtNum.setText(stuNum);
        txtNum.setEnabled(false);
        mainPanel.add(txtNum);

        JLabel label_1 = new JLabel("学生编号:");
        label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_1.setBounds(50, 58, 74, 27);
        mainPanel.add(label_1);

        JLabel label_2 = new JLabel("问题一    :");
        label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_2.setBounds(50, 103, 74, 27);
        mainPanel.add(label_2);

        JLabel label_3 = new JLabel("答案一    :");
        label_3.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_3.setBounds(50, 140, 74, 27);
        mainPanel.add(label_3);

        JLabel label_4 = new JLabel("问题二    :");
        label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_4.setBounds(50, 193, 74, 27);
        mainPanel.add(label_4);

        JLabel label_5 = new JLabel("答案二    :");
        label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_5.setBounds(50, 230, 74, 27);
        mainPanel.add(label_5);

        JLabel label_6 = new JLabel("问题三    :");
        label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_6.setBounds(50, 277, 74, 27);
        mainPanel.add(label_6);

        JLabel label_7 = new JLabel("答案三    :");
        label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_7.setBounds(50, 314, 74, 27);
        mainPanel.add(label_7);


        btnCheckA = new JButton("验证答案");
        btnCheckA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setInfoMsg("");
                String a1 = txtA1.getText();
                if (a1.trim().length() == 0) {
                    setErrorMsg("答案一不能为空!");
                    return;
                }
                String a2 = txtA2.getText();
                if (a2.trim().length() == 0) {
                    setErrorMsg("答案二不能为空!");
                    return;
                }
                String a3 = txtA3.getText();
                if (a2.trim().length() == 0) {
                    setErrorMsg("答案三不能为空!");
                    return;
                }
                String stuNum = txtNum.getText();
                RestPwdDaoImp restPwdDAO = new RestPwdDaoImp();
                //biz
                RestPwd restPwd = restPwdDAO.findById(stuNum);
                if (restPwd.getRequestCount() >= 5) {
                    setErrorMsg("回答密码问题次数用尽,请联系管理员!");
                    return;
                }
                if (a1.equals(restPwd.getAns1()) && a2.equals(restPwd.getAns2()) && a3.equals(restPwd.getAns3())) {
                    restPwdDAO.updateRequestNum(stuNum, 0);
                    setSuccessMsg("密保验证正确, 请重新设置密保");
                    btnCheckA.setVisible(false);
                    msg = "修改";
                    setRestPwd();
                } else {
                    setErrorMsg("密码提示问题错误, 还有" + (5 - restPwd.getRequestCount()) + "次机会!");
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

        JButton btnLoadQ = new JButton("加载问题");
        btnLoadQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String stuNum = txtNum.getText();
                RestPwdDaoImp restPwdDAO = new RestPwdDaoImp();
                RestPwd restPwd = restPwdDAO.findById(stuNum);
                if (restPwd == null) {
                    JOptionPane.showMessageDialog(null, "您还未设置密保问题, 请设置密保问题");
                    setInfoMsg("密保问题及答案长度限制在20个字符以内");
                    msg = "设置";
                    btnCheckA.setVisible(false);
                    setRestPwd();
                } else {
                    if (restPwd.getRequestCount() == 5) {
                        setErrorMsg("回答密码问题次数用尽,请联系管理员!");
                    } else {
                        setInfoMsg("还有" + (5 - restPwd.getRequestCount()) + "次回答机会!");
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
                        show.text_error(o, "此项不能为空");
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
        confirmSubmission.setText("确认修改");
        mainPanel.add(confirmSubmission);
        confirmSubmission.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean flag = false;
                for (JTextField o : new JTextField[]{txtA1, txtA2, txtA3, txtQ1, txtQ2, txtQ3}) {
                    o.setFocusable(true);
                    if (o.getText().equals("")) {
                        show.text_error(o, "此项不能为空");
                        flag = true;
                    } else if (o.getText().length() > 20) {
                        show.text_error(o, "此项长度不应超过20个字符");
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
                if (msg.equals("设置")) {
                    if (rpdi.insert(restPwd)) {
                        choiceIndex = JOptionPane.showConfirmDialog(null, "密保问题设置成功", "是否返回", JOptionPane.OK_OPTION);
                    } else {
                        choiceIndex = JOptionPane.showConfirmDialog(null, "密码设置失败", "失败", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if (rpdi.update(restPwd)) {
                        choiceIndex = JOptionPane.showConfirmDialog(null, "密保问题修改成功", "是否返回", JOptionPane.OK_OPTION);
                    } else {
                        choiceIndex = JOptionPane.showConfirmDialog(null, "密码修改失败", "失败", JOptionPane.WARNING_MESSAGE);
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
