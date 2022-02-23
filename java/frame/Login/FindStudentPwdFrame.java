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
        JLabel label = new JLabel("�һ�����");
        label.setFont(new Font("����", Font.PLAIN, 16));
        label.setBounds(167, 23, 114, 29);
        mainPanel.add(label);

        txtNum = new JTextField();
        txtNum.setColumns(10);
        txtNum.setBounds(135, 62, 146, 21);
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

        txtA3 = new JTextField();
        txtA3.setColumns(10);
        txtA3.setBounds(135, 318, 236, 21);
        mainPanel.add(txtA3);

        JButton btnRestPwd = new JButton("��������");
        btnRestPwd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setInfoMsg("");
                //1 �ǿ�
                char[] pwds1 = txtNewPwd1.getPassword();
                String pwd1 = new String(pwds1);
                if (pwd1.trim().length() == 0) {
                    setErrorMsg("�����벻��Ϊ��!");
                    return;
                }
                char[] pwds2 = txtNewPwd2.getPassword();
                String pwd2 = new String(pwds2);
                if (pwd2.trim().length() == 0) {
                    setErrorMsg("ȷ�����벻��Ϊ��!");
                    return;
                }
                //2 ����һ��
                if (!pwd1.equals(pwd2)) {
                    setErrorMsg("��������ȷ�����벻һ��!");
                    return;
                }
                //3 �޸�����
                String num = txtNum.getText();
                StudentDaoImp studentDAO = new StudentDaoImp();
                boolean ok = studentDAO.updatePwd(num, pwd1);
                if (ok) {
                    setSuccessMsg("��������ɹ�!");
                } else {
                    setSuccessMsg("��������ʧ��!");
                }
            }
        });
        btnRestPwd.setEnabled(false);
        btnRestPwd.setBounds(278, 450, 93, 23);
        mainPanel.add(btnRestPwd);

        JButton btnCheckA = new JButton("��֤��");
        btnCheckA.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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
                    txtNewPwd1.setEnabled(true);
                    txtNewPwd2.setEnabled(true);
                    btnRestPwd.setEnabled(true);
                    setInfoMsg("������ʾ������ȷ,������������!");
                } else {
                    setErrorMsg("������ʾ�������, ����" + (5 - restPwd.getRequestCount()) + "�λ���!");
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

        JButton btnLoadQ = new JButton("��������");
        btnLoadQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String stuNum = txtNum.getText();
                if (stuNum.trim().length() == 0) {
                    setErrorMsg("ѧ����Ų���Ϊ��!");
                    return;
                }
                if (stuNum.trim().length() != 12) {
                    setErrorMsg("ѧ����ű�����12λ!");
                    return;
                }
                RestPwdDaoImp restPwdDAO = new RestPwdDaoImp();
                RestPwd restPwd = restPwdDAO.findById(stuNum);
                if (restPwd == null) {
                    StudentDaoImp studentDAO = new StudentDaoImp();
                    List<Student> stu = studentDAO.findById(stuNum);
                    if (stu == null) {
                        setErrorMsg("ѧ����Ų�����!");
                        JOptionPane.showMessageDialog(null,
                                "ѧ����Ų�����!", "����",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        setErrorMsg("ѧ��û�������һ���������!");
                        JOptionPane.showMessageDialog(null,
                                "ѧ��û�������һ���������!", "����",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (restPwd.getRequestCount() >= 5) {
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

        JLabel label_8 = new JLabel("������   :");
        label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_8.setBounds(50, 378, 74, 27);
        mainPanel.add(label_8);

        txtNewPwd1 = new JPasswordField();
        txtNewPwd1.setEnabled(false);
        txtNewPwd1.setColumns(10);
        txtNewPwd1.setBounds(135, 378, 236, 21);
        mainPanel.add(txtNewPwd1);

        JLabel label_9 = new JLabel("ȷ������ :");
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
