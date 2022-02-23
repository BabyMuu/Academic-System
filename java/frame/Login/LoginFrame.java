/*
 * Created by JFormDesigner on Thu Oct 28 21:10:23 CST 2021
 */

package frame.Login;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import dao.imp.StudentDaoImp;
import dao.imp.TeacherDaoImp;
import entity.Student;
import entity.Teacher;
import frame.faceToUser.AdminFrame;
import frame.faceToUser.StudentFrame;
import frame.faceToUser.TeaFrame;
import lib.tools.*;

/**
 * @author unknown
 */
public class LoginFrame extends JFrame {
    public static String ROLE = "";

    public LoginFrame() {
        initComponents();
        initPersonalization();
    }

    AdminFrame mainFrame;
    StudentFrame studentFrame;
    TeaFrame teaFrame;

    protected void initPersonalization() {
        this.frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void submit() {
        String userName = textId.getText().trim();
        String passWord = new String(textPwd.getPassword());
        show.text_to_default(HintMsg);
        if (userName.equals("") || passWord.equals("")) {
            show.text_error(HintMsg, "帐号密码不能为空");
            return;
        }
        if (radioFirst.isSelected()) {
            if (userName.length() != 8) {
                show.text_error(HintMsg, "教师编号应为八位");
                return;
            }
            List<Teacher> teachers = new TeacherDaoImp().findById(userName);
            if (teachers == null) {
                show.text_error(HintMsg, "教师编号不存在");
                return;
            } else {
                if (!passWord.equals(teachers.get(0).getPassword())) {
                    show.text_error(HintMsg, "密码错误");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "登录成功, 欢迎您 " + teachers.get(0).getTeaName());
            if (teachers.get(0).getRoleId() == 2) {
                ROLE = "Admin";
                mainFrame = new AdminFrame(teachers.get(0));
            } else {
                ROLE = "Teacher";
                teaFrame = new TeaFrame(teachers.get(0));
            }

        } else {
            if (userName.length() != 12) {
                show.text_error(HintMsg, "学生编号应为12位");
                return;
            }
            List<Student> students = new StudentDaoImp().findById(userName);
            if (students == null) {
                show.text_error(HintMsg, "学生编号不存在");
                return;
            } else {
                if (passWord.equals(students.get(0).getPassword())) {

                } else {
                    show.text_error(HintMsg, "密码错误");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "登录成功, 欢迎您 " + students.get(0).getStuName());
            ROLE = "Student";
            studentFrame = new StudentFrame(students.get(0));
        }
        this.frame1.dispose();
    }

    private void btnForgotPasswordMousePressed(MouseEvent e) {
        show.text_error(HintMsg, "");
        if (radioFirst.isSelected()) {
            show.text_error(HintMsg, "只有学生可以通过问题找回密码,老师请联系管理员!");
            return;
        }

        FindStudentPwdFrame findStudentPwdFrame = new FindStudentPwdFrame();
        findStudentPwdFrame.setModal(true);
        findStudentPwdFrame.setVisible(true);
    }

    private void btnResetMousePressed(MouseEvent e) {
        radioFirst.setSelected(true);
        textId.setText("");
        textPwd.setText("");
    }


    private void submitKeyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            submit();
        }
    }

    private void btnLoginMousePressed(MouseEvent e) {
        submit();
    }

    private void btnCallUsMousePressed(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "如果您对本作品设计,用户体验等方面有很好的建议和意见；\n" +
                "如果您在使用过程中发现了错误的文字或链接；\n" +
                "如果您想与作者在某一方面进行合作；\n" +
                "欢迎您通过以下联系方式跟我们联系：\n" +
                "E-mail：BabyMuu@vip.qq.com");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        frame1 = new JFrame();
        radioFirst = new JRadioButton();
        radioSecond = new JRadioButton();
        labeUser = new JLabel();
        labelPwd = new JLabel();
        labeRole = new JLabel();
        textId = new JTextField();
        textPwd = new JPasswordField();
        btnLogin = new JButton();
        btnReset = new JButton();
        btnForgotPassword = new JButton();
        btnCallUs = new JButton();
        HintMsg = new JLabel();
        label2 = new JLabel();
        label1 = new JLabel();

        //======== frame1 ========
        {
            frame1.setResizable(false);
            frame1.setTitle("\u7cfb\u7edf\u767b\u5f55-\u6210\u7ee9\u7ba1\u7406\u5206\u6790\u7cfb\u7edf");
            frame1.setVisible(true);
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //---- radioFirst ----
            radioFirst.setText("\u6559\u5e08");
            radioFirst.setSelected(true);
            radioFirst.setBorder(new MatteBorder(1, 1, 1, 0, Color.black));
            radioFirst.setBorderPainted(true);
            radioFirst.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(radioFirst);
            radioFirst.setBounds(350, 295, 90, 35);

            //---- radioSecond ----
            radioSecond.setText("\u5b66\u751f");
            radioSecond.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
            radioSecond.setBorderPainted(true);
            radioSecond.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(radioSecond);
            radioSecond.setBounds(440, 295, 110, 35);

            //---- labeUser ----
            labeUser.setText("\u5e10\u53f7: ");
            labeUser.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            labeUser.setHorizontalAlignment(SwingConstants.RIGHT);
            labeUser.setIcon(new ImageIcon(getClass().getResource("/lib/png/user.png")));
            labeUser.setForeground(Color.orange);
            frame1ContentPane.add(labeUser);
            labeUser.setBounds(250, 345, 105, 35);

            //---- labelPwd ----
            labelPwd.setText("\u5bc6\u7801: ");
            labelPwd.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            labelPwd.setHorizontalAlignment(SwingConstants.RIGHT);
            labelPwd.setIcon(new ImageIcon(getClass().getResource("/lib/png/password.png")));
            labelPwd.setForeground(Color.orange);
            frame1ContentPane.add(labelPwd);
            labelPwd.setBounds(250, 395, 105, 35);

            //---- labeRole ----
            labeRole.setText("\u89d2\u8272: ");
            labeRole.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            labeRole.setHorizontalAlignment(SwingConstants.RIGHT);
            labeRole.setIcon(new ImageIcon(getClass().getResource("/lib/png/role.png")));
            labeRole.setForeground(Color.orange);
            frame1ContentPane.add(labeRole);
            labeRole.setBounds(250, 295, 105, 35);

            //---- textId ----
            textId.setBorder(LineBorder.createBlackLineBorder());
            textId.setForeground(new Color(102, 102, 102));
            textId.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    submitKeyPressed(e);
                }
            });
            frame1ContentPane.add(textId);
            textId.setBounds(350, 345, 200, 35);

            //---- textPwd ----
            textPwd.setBorder(LineBorder.createBlackLineBorder());
            textPwd.setForeground(new Color(102, 102, 102));
            textPwd.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    submitKeyPressed(e);
                }
            });
            frame1ContentPane.add(textPwd);
            textPwd.setBounds(350, 395, 200, 35);

            //---- btnLogin ----
            btnLogin.setIcon(new ImageIcon(getClass().getResource("/lib/png/login.png")));
            btnLogin.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnLoginMousePressed(e);
                }
            });
            frame1ContentPane.add(btnLogin);
            btnLogin.setBounds(245, 445, 50, 50);

            //---- btnReset ----
            btnReset.setIcon(new ImageIcon(getClass().getResource("/lib/png/reset.png")));
            btnReset.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnResetMousePressed(e);
                }
            });
            frame1ContentPane.add(btnReset);
            btnReset.setBounds(435, 445, 50, 50);

            //---- btnForgotPassword ----
            btnForgotPassword.setSelectedIcon(new ImageIcon(getClass().getResource("/lib/png/forgetPassword.png")));
            btnForgotPassword.setIcon(new ImageIcon(getClass().getResource("/lib/png/forgetPassword.png")));
            btnForgotPassword.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnForgotPasswordMousePressed(e);
                }
            });
            frame1ContentPane.add(btnForgotPassword);
            btnForgotPassword.setBounds(340, 445, 50, 50);

            //---- btnCallUs ----
            btnCallUs.setIcon(new ImageIcon(getClass().getResource("/lib/png/CallUs.png")));
            btnCallUs.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnCallUsMousePressed(e);
                }
            });
            frame1ContentPane.add(btnCallUs);
            btnCallUs.setBounds(525, 445, 50, 50);
            frame1ContentPane.add(HintMsg);
            HintMsg.setBounds(0, 495, 800, 27);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/lib/png/me3.png")));
            frame1ContentPane.add(label2);
            label2.setBounds(-15, 25, 840, 310);

            //---- label1 ----
            label1.setText("img");
            label1.setIcon(new ImageIcon(getClass().getResource("/lib/png/home.png")));
            frame1ContentPane.add(label1);
            label1.setBounds(0, 0, 800, 535);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < frame1ContentPane.getComponentCount(); i++) {
                    Rectangle bounds = frame1ContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = frame1ContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                frame1ContentPane.setMinimumSize(preferredSize);
                frame1ContentPane.setPreferredSize(preferredSize);
            }
            frame1.setSize(800, 555);
            frame1.setLocationRelativeTo(frame1.getOwner());
        }

        //---- roleGroup ----
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(radioFirst);
        roleGroup.add(radioSecond);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JFrame frame1;
    protected JRadioButton radioFirst;
    protected JRadioButton radioSecond;
    private JLabel labeUser;
    private JLabel labelPwd;
    private JLabel labeRole;
    protected JTextField textId;
    protected JPasswordField textPwd;
    private JButton btnLogin;
    private JButton btnReset;
    private JButton btnForgotPassword;
    private JButton btnCallUs;
    private JLabel HintMsg;
    private JLabel label2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
