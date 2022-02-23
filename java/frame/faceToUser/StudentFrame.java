/*
 * Created by JFormDesigner on Wed Dec 22 13:19:27 CST 2021
 */

package frame.faceToUser;

import entity.Student;
import frame.Login.FindStudentPwdFrame;
import frame.Login.LoginFrame;
import frame.ca.CASelect;
import frame.clsInfo.ClassInfoSelect;
import frame.score.ScoreSelect;
import frame.select.SelectFrame;
import frame.student.ExamSchSelect;
import frame.student.SetStuPwdFrame;
import frame.student.StudentSelect;
import frame.student.StudentUpdate;
import lib.tools.Common;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;

/**
 * @author unknown
 */
public class StudentFrame extends JFrame {
    public Student student = null;

    public StudentFrame(Student student) {
        this.student = student;
        initComponents();
        initPersonalization(); // ��ʼ�����Ի�����
    }

    protected void initPersonalization() {
        this.frame1.setTitle("ѧ��ϵͳ");
        System.out.println(Common.getNowTimeString());  // ����̨�����ǰʱ��
        this.timeLab.setText(Common.getNowTimeString() + "   user: " + student.getStuName()); // �������ṩʱ��
        this.frame1.setResizable(false); // ���ô��岻�ɱ����Ĵ�С
        this.frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * �鿴ĳ�������Ƿ����
     *
     * @param frameTitle ��������
     * @return true: ������� false: ���岻����
     */
    private boolean checkFrame(String frameTitle) {
        boolean flag = false; // ����Ĭ�ϱ�ʶ
        for (JInternalFrame allFrame : desktopPane1.getAllFrames()) { // ������ǰ����Ӵ����б�
            System.out.print(allFrame.getTitle() + "---");
            if (allFrame.getTitle().equals(frameTitle)) {
                // �ҵ������ҵ��Ĵ���
                allFrame.setVisible(true); // ��ʾ�ɼ�
                flag = true;  // ���ı�ʶ
            } else {
                allFrame.hide(); // ���ط�����, �����ڴ���
            }
        }
        System.out.println();
        return flag;
    }

    private void menuExamSchMousePressed(MouseEvent e) {
        menuExamSchMousePressed();
    }

    private void menuStuManMousePressed(MouseEvent e) {
        menuStuManMousePressed();
    }

    private void menuClassInfoManMousePressed(MouseEvent e) {
        menuClassInfoManMousePressed();
    }

    private void menuLesManMousePressed(MouseEvent e) {
        menuLesManMousePressed();
    }

    private void menuScoManMousePressed(MouseEvent e) {
        menuScoManMousePressed();
    }

    private void treeValueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        String name = path.getLastPathComponent().toString();
        if (name.equals("ͬѧ")) {
            name = "ѧ��";
        }
        if (isParent(name)) return;
        //ȥ���弯����Ѱ��
        if (checkFrame(name + "��ѯ")) return;
        //���û�ҵ�
        switch (name) {
            case "ѧ��":
                menuStuManMousePressed();
                break;
            case "�༶":
                menuClassInfoManMousePressed();
                break;
            case "�γ�":
                menuLesManMousePressed();
                break;
            case "����":
                menuScoManMousePressed();
                break;
            case "���԰���":
                menuExamSchMousePressed();
                break;
            case "�鿴������Ϣ":
                menuShowPersonalInformationMousePressed();
                break;
            case "�޸�����":
                menuChangePasswordMousePressed();
                break;
            case "�����ܱ�":
                menuSetSecretProtectionMousePressed();
                break;
        }

    }

    private boolean isParent(String name) {
        for (String fcd : new String[]{"ϵͳ�˵�", "������Ϣ����", "ҵ�����", "���Թ���", "������Ϣ"}) {
            if (name.equals(fcd))
                return true;
        }
        return false;
    }

    private void menuStuManMousePressed() {
        if (checkFrame("ѧ����ѯ")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("clsId", student.getClsId() + "");
        SelectFrame ts = new StudentSelect(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuClassInfoManMousePressed() {
        if (checkFrame("�༶��ѯ")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("clsId", student.getClsId() + "");
        SelectFrame ts = new ClassInfoSelect(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuLesManMousePressed() {
        if (checkFrame("�γ̰��Ų�ѯ")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("clsId", student.getClsId() + "");
        SelectFrame ts = new CASelect(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuExamSchMousePressed() {
        if (checkFrame("���԰��Ų�ѯ")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("clsId", student.getClsId() + "");
        SelectFrame ts = new ExamSchSelect(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuScoManMousePressed() {
        if (checkFrame("��������")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("stuNum", student.getStuNum());
        SelectFrame ts = new ScoreSelect(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuSetSecretProtectionMousePressed() {
        SetStuPwdFrame setStuPwdFrame = new SetStuPwdFrame(student.getStuNum());
        setStuPwdFrame.setModal(true);
        setStuPwdFrame.setVisible(true);

    }

    private void menuChangePasswordMousePressed() {
        FindStudentPwdFrame findStudentPwdFrame = new FindStudentPwdFrame(student.getStuNum());
        findStudentPwdFrame.setModal(true);
        findStudentPwdFrame.setVisible(true);
    }

    private void menuShowPersonalInformationMousePressed() {
        StudentUpdate studentUpdate = new StudentUpdate(student.getStuNum());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        frame1 = new JFrame();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuStuMan = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu3 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menu4 = new JMenu();
        menu5 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menuItem6 = new JMenuItem();
        tree1 = new JTree();
        desktopPane1 = new JDesktopPane();
        panel1 = new JPanel();
        timeLab = new JLabel();
        panel2 = new JPanel();
        InfoMsg = new JLabel();

        //======== frame1 ========
        {
            frame1.setVisible(true);
            frame1.setTitle("TeacherFrame");
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //======== menuBar1 ========
            {

                //======== menu1 ========
                {
                    menu1.setText("\u57fa\u7840\u6570\u636e");

                    //---- menuStuMan ----
                    menuStuMan.setText("\u5b66\u751f\u7ba1\u7406");
                    menuStuMan.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuStuManMousePressed();
                        }
                    });
                    menu1.add(menuStuMan);

                    //---- menuItem3 ----
                    menuItem3.setText("\u73ed\u7ea7\u7ba1\u7406");
                    menuItem3.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuClassInfoManMousePressed();
                        }
                    });
                    menu1.add(menuItem3);

                    //---- menuItem2 ----
                    menuItem2.setText("\u8bfe\u7a0b\u7ba1\u7406");
                    menuItem2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuLesManMousePressed();
                        }
                    });
                    menu1.add(menuItem2);
                }
                menuBar1.add(menu1);

                //======== menu3 ========
                {
                    menu3.setText("\u8003\u8bd5\u7ba1\u7406");

                    //---- menuItem4 ----
                    menuItem4.setText("\u5206\u6570\u67e5\u8be2");
                    menuItem4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuScoManMousePressed();
                        }
                    });
                    menu3.add(menuItem4);

                    //---- menuItem7 ----
                    menuItem7.setText("\u8003\u8bd5\u5b89\u6392");
                    menuItem7.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuExamSchMousePressed();
                        }
                    });
                    menu3.add(menuItem7);
                }
                menuBar1.add(menu3);

                //======== menu4 ========
                {
                    menu4.setText("\u4e2a\u4eba\u4fe1\u606f");

                    //---- menuItem1 ----
                    menuItem1.setText("\u67e5\u770b\u4e2a\u4eba\u4fe1\u606f");
                    menuItem1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuShowPersonalInformationMousePressed();
                        }
                    });
                    menu4.add(menuItem1);

                    //---- menuItem5 ----
                    menuItem5.setText("\u8bbe\u7f6e\u5bc6\u4fdd\u95ee\u9898");
                    menuItem5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuSetSecretProtectionMousePressed();
                        }
                    });
                    menu4.add(menuItem5);

                    //---- menuItem6 ----
                    menuItem6.setText("\u4fee\u6539\u5bc6\u7801");
                    menuItem6.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuChangePasswordMousePressed();
                        }
                    });
                    menu4.add(menuItem6);
                }

                //======== menu5 ========
                menuBar1.add(menu4);
                {
                    menu5.setText("\u5207\u6362\u5e10\u53f7");
                    menu5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuChangeAccountMousePressed();
                        }
                    });
                }
                menuBar1.add(menu5);
            }
            frame1.setJMenuBar(menuBar1);

            //---- tree1 ----
            tree1.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("\u7cfb\u7edf\u83dc\u5355") {
                        {
                            DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("\u57fa\u7840\u4fe1\u606f\u7ba1\u7406");
                            node1.add(new DefaultMutableTreeNode("\u73ed\u7ea7"));
                            node1.add(new DefaultMutableTreeNode("\u540c\u5b66"));
                            node1.add(new DefaultMutableTreeNode("\u8bfe\u7a0b"));
                            add(node1);
                            node1 = new DefaultMutableTreeNode("\u8003\u8bd5\u7ba1\u7406");
                            node1.add(new DefaultMutableTreeNode("\u5206\u6570"));
                            node1.add(new DefaultMutableTreeNode("\u8003\u8bd5\u5b89\u6392"));
                            add(node1);
                            node1 = new DefaultMutableTreeNode("\u4e2a\u4eba\u4fe1\u606f");
                            node1.add(new DefaultMutableTreeNode("\u67e5\u770b\u4e2a\u4eba\u4fe1\u606f"));
                            node1.add(new DefaultMutableTreeNode("\u8bbe\u7f6e\u5bc6\u4fdd"));
                            node1.add(new DefaultMutableTreeNode("\u4fee\u6539\u5bc6\u7801"));
                            add(node1);
                        }
                    }));
            tree1.addTreeSelectionListener(e -> treeValueChanged(e));
            frame1ContentPane.add(tree1);
            tree1.setBounds(10, 32, 185, 755);
            frame1ContentPane.add(desktopPane1);
            desktopPane1.setBounds(200, 32, 1040, 755);

            //======== panel1 ========
            {
                panel1.setBorder(new EtchedBorder());
                panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
                        border.EmptyBorder(0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border.TitledBorder.CENTER
                        , javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("D\u0069alog", java.awt.Font
                        .BOLD, 12), java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(
                    new java.beans.PropertyChangeListener() {
                        @Override
                        public void propertyChange(java.beans.PropertyChangeEvent e) {
                            if ("\u0062order"
                                    .equals(e.getPropertyName())) throw new RuntimeException();
                        }
                    });
                panel1.setLayout(null);

                //---- timeLab ----
                timeLab.setText("text");
                timeLab.setHorizontalAlignment(SwingConstants.CENTER);
                panel1.add(timeLab);
                timeLab.setBounds(460, 0, 230, 35);
            }
            frame1ContentPane.add(panel1);
            panel1.setBounds(0, -2, 1245, 50);

            //======== panel2 ========
            {
                panel2.setBorder(new EtchedBorder());
                panel2.setLayout(null);

                //---- InfoMsg ----
                InfoMsg.setText("Hello World");
                InfoMsg.setHorizontalAlignment(SwingConstants.LEFT);
                panel2.add(InfoMsg);
                InfoMsg.setBounds(5, 0, 1235, 35);
            }
            frame1ContentPane.add(panel2);
            panel2.setBounds(5, 795, 1235, 35);

            frame1ContentPane.setPreferredSize(new Dimension(1250, 885));
            frame1.setSize(1250, 885);
            frame1.setLocationRelativeTo(frame1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void menuChangeAccountMousePressed() {
        int choice = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳��ʺ�ô?", "�л��ʺ�", JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0) {
            this.frame1.dispose();
            new LoginFrame();
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JFrame frame1;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuStuMan;
    private JMenuItem menuItem3;
    private JMenuItem menuItem2;
    private JMenu menu3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem7;
    private JMenu menu4;
    private JMenu menu5;
    private JMenuItem menuItem1;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JTree tree1;
    private JDesktopPane desktopPane1;
    private JPanel panel1;
    private JLabel timeLab;
    private JPanel panel2;
    private JLabel InfoMsg;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
