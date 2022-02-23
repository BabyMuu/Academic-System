package frame.Login;

import dao.imp.RestPwdDaoImp;
import dao.imp.StudentDaoImp;
import entity.RestPwd;
import entity.Student;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;


public class FindStudentPwdFrame extends BaseDialog {
    private JTextField txtNum;
    private JTextField txtA3;
    private JTextField txtA2;
    private JTextField txtA1;
    private JTextField txtQ1;
    private JTextField txtQ2;
    private JTextField txtQ3;
    private JPasswordField txtNewPwd1;
    private JPasswordField txtNewPwd2;

    public FindStudentPwdFrame() {
        init();
    }

    public FindStudentPwdFrame(String stuNum) {
        init();
        this.txtNum.setText(stuNum);
        this.txtNum.setEditable(false);
    }

    private void init() {
        setSize(430, 545);
        setResizable(false);
        setLocationRelativeTo(null);
        JLabel label = new JLabel("找回密码");
        label.setFont(new Font("宋体", Font.PLAIN, 16));
        label.setBounds(167, 23, 114, 29);
        mainPanel.add(label);

        txtNum = new JTextField();
        txtNum.setColumns(10);
        txtNum.setBounds(135, 62, 146, 21);
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

        txtA3 = new JTextField();
        txtA3.setColumns(10);
        txtA3.setBounds(135, 318, 236, 21);
        mainPanel.add(txtA3);

        JButton btnRestPwd = new JButton("保存密码");
        btnRestPwd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setInfoMsg("");
                //1 非空
                char[] pwds1 = txtNewPwd1.getPassword();
                String pwd1 = new String(pwds1);
                if (pwd1.trim().length() == 0) {
                    setErrorMsg("新密码不能为空!");
                    return;
                }
                char[] pwds2 = txtNewPwd2.getPassword();
                String pwd2 = new String(pwds2);
                if (pwd2.trim().length() == 0) {
                    setErrorMsg("确认密码不能为空!");
                    return;
                }
                //2 密码一致
                if (!pwd1.equals(pwd2)) {
                    setErrorMsg("新密码与确认密码不一致!");
                    return;
                }
                //3 修改密码
                String num = txtNum.getText();
                StudentDaoImp studentDAO = new StudentDaoImp();
                boolean ok = studentDAO.updatePwd(num, pwd1);
                if (ok) {
                    setSuccessMsg("重置密码成功!");
                } else {
                    setSuccessMsg("重置密码失败!");
                }
            }
        });
        btnRestPwd.setEnabled(false);
        btnRestPwd.setBounds(278, 450, 93, 23);
        mainPanel.add(btnRestPwd);

        JButton btnCheckA = new JButton("验证答案");
        btnCheckA.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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
                    txtNewPwd1.setEnabled(true);
                    txtNewPwd2.setEnabled(true);
                    btnRestPwd.setEnabled(true);
                    setInfoMsg("密码提示问题正确,请输入新密码!");
                } else {
                    setErrorMsg("密码提示问题错误, 还有" + (5 - restPwd.getRequestCount()) + "次机会!");
                    restPwdDAO.updateRequestNum(stuNum, restPwd.getRequestCount() + 1);
                }
            }
        });
        btnCheckA.setBounds(278, 349, 93, 23);
        mainPanel.add(btnCheckA);

        txtA2 = new JTextField();
        txtA2.setColumns(10);
        txtA2.setBounds(135, 234, 236, 21);
        mainPanel.add(txtA2);

        txtA1 = new JTextField();
        txtA1.setColumns(10);
        txtA1.setBounds(135, 144, 236, 21);
        mainPanel.add(txtA1);

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
                if (stuNum.trim().length() == 0) {
                    setErrorMsg("学生编号不能为空!");
                    return;
                }
                if (stuNum.trim().length() != 12) {
                    setErrorMsg("学生编号必须是12位!");
                    return;
                }
                RestPwdDaoImp restPwdDAO = new RestPwdDaoImp();
                RestPwd restPwd = restPwdDAO.findById(stuNum);
                if (restPwd == null) {
                    StudentDaoImp studentDAO = new StudentDaoImp();
                    List<Student> stu = studentDAO.findById(stuNum);
                    if (stu == null) {
                        setErrorMsg("学生编号不存在!");
                        JOptionPane.showMessageDialog(null,
                                "学生编号不存在!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        setErrorMsg("学生没有设置找回密码问题!");
                        JOptionPane.showMessageDialog(null,
                                "学生没有设置找回密码问题!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (restPwd.getRequestCount() >= 5) {
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

        JLabel label_8 = new JLabel("新密码   :");
        label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_8.setBounds(50, 378, 74, 27);
        mainPanel.add(label_8);

        txtNewPwd1 = new JPasswordField();
        txtNewPwd1.setEnabled(false);
        txtNewPwd1.setColumns(10);
        txtNewPwd1.setBounds(135, 378, 236, 21);
        mainPanel.add(txtNewPwd1);

        JLabel label_9 = new JLabel("确认密码 :");
        label_9.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_9.setBounds(50, 415, 74, 27);
        mainPanel.add(label_9);

        txtNewPwd2 = new JPasswordField();
        txtNewPwd2.setEnabled(false);
        txtNewPwd2.setColumns(10);
        txtNewPwd2.setBounds(135, 419, 236, 21);
        mainPanel.add(txtNewPwd2);
    }
}
