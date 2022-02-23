/*
 * Created by JFormDesigner on Wed Dec 22 19:45:19 CST 2021
 */

package frame.faceToUser;

import entity.FindCA;
import entity.Teacher;
import frame.JustQuery.CAQuery;
import frame.JustQuery.FindScore;
import frame.JustQuery.lesQuery;
import frame.JustQuery.StuQuery;
import frame.Login.LoginFrame;
import frame.score.ScoreAnalysisForTea;
import frame.select.SelectFrame;
import frame.teacher.TeacherUpdate;
import lib.tools.Common;


import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;

/**
 * @author unknown
 */
public class TeaFrame extends JFrame {
    public Teacher teacher = null;
    public FindCA findCA = null;
    public DefaultTableModel model;

    public TeaFrame(Teacher teacher) {
        this.teacher = teacher;
        System.out.println(teacher.getTeaTypeId().equals("1") ? "教师" : "助教");
        initComponents();
        initPersonalization(); // 初始化个性化内容
    }

    protected void initPersonalization() {
        System.out.println(Common.getNowTimeString());  // 控制台输出当前时间
        this.timeLab.setText(Common.getNowTimeString() + "   user: " + teacher.getTeaName()); // 给窗体提供时间
        this.frame1.setResizable(false); // 设置窗体不可被更改大小
        this.frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * 查看某个窗体是否存在
     *
     * @param frameTitle 窗体名称
     * @return true: 窗体存在 false: 窗体不存在
     */
    private boolean checkFrame(String frameTitle) {
        boolean flag = false; // 设置默认标识
        for (JInternalFrame allFrame : desktopPane1.getAllFrames()) { // 遍历当前存的子窗体列表
            System.out.print(allFrame.getTitle() + "-");

            if (allFrame.getTitle().equals(frameTitle)) {
                // 找到了想找到的窗体
                allFrame.setVisible(true); // 显示可见
                flag = true;  // 更改标识
            } else {
                allFrame.hide(); // 隐藏非所找, 但存在窗体
            }
        }
        System.out.println();
        return flag;
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

    private void menuScoreAnalysisMousePressed(MouseEvent e) {
        menuScoreAnalysisMousePressed();
    }

    private boolean isParent(String name) {
        for (String fcd : new String[]{"系统菜单", "基础信息管理", "业务管理", "考试管理", "个人信息"}) {
            if (name.equals(fcd))
                return true;
        }
        return false;
    }

    private void treeValueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        String name = path.getLastPathComponent().toString();
        if (name.equals("同学")) {
            name = "学生";
        }
        if (isParent(name)) return;
        //去窗体集合中寻找
        if (checkFrame(name + "查询")) return;
        //如果没找到
        switch (name) {
            case "学生":
                menuStuManMousePressed();
                break;
            case "班级":
                menuClassInfoManMousePressed();
                break;
            case "课程":
                menuLesManMousePressed();
                break;
            case "分数管理":
                menuScoManMousePressed();
                break;
            case "查看个人信息":
                menuShowPersonalInformationMousePressed();
                break;
            case "成绩分析":
                menuScoreAnalysisMousePressed();
                break;
        }
    }

    private void menuChangeAccountMousePressed(MouseEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "您确定要退出帐号么?", "切换帐号", JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0) {
            this.frame1.dispose();
            new LoginFrame();
        }
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
        menu2 = new JMenu();
        menu3 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu4 = new JMenu();
        menuItem1 = new JMenuItem();
        menu5 = new JMenu();
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
                            menuStuManMousePressed(e);
                        }
                    });
                    menu1.add(menuStuMan);

                    //---- menuItem3 ----
                    menuItem3.setText("\u73ed\u7ea7\u7ba1\u7406");
                    menuItem3.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuClassInfoManMousePressed(e);
                        }
                    });
                    menu1.add(menuItem3);

                    //---- menuItem2 ----
                    menuItem2.setText("\u8bfe\u7a0b\u7ba1\u7406");
                    menuItem2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuLesManMousePressed(e);
                        }
                    });
                    menu1.add(menuItem2);
                }
                menuBar1.add(menu1);

                //======== menu2 ========
                {
                    menu2.setText("\u4e1a\u52a1\u7ba1\u7406");
                }
                menuBar1.add(menu2);

                //======== menu3 ========
                {
                    menu3.setText("\u8003\u8bd5\u7ba1\u7406");

                    //---- menuItem5 ----
                    menuItem5.setText("\u5206\u6570\u5206\u6790");
                    menuItem5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuScoreAnalysisMousePressed(e);
                        }
                    });
                    menu3.add(menuItem5);

                    //---- menuItem4 ----
                    menuItem4.setText("\u5206\u6570\u7ba1\u7406");
                    menuItem4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuScoManMousePressed(e);
                        }
                    });
                    menu3.add(menuItem4);
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
                            menuShowPersonalInformationMousePressed(e);
                        }
                    });
                    menu4.add(menuItem1);
                }
                menuBar1.add(menu4);

                //======== menu5 ========
                {
                    menu5.setText("\u5207\u6362\u5e10\u53f7");
                    menu5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuChangeAccountMousePressed(e);
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
                            node1.add(new DefaultMutableTreeNode("\u5b66\u751f"));
                            node1.add(new DefaultMutableTreeNode("\u8bfe\u7a0b"));
                            add(node1);
                            node1 = new DefaultMutableTreeNode("\u8003\u8bd5\u7ba1\u7406");
                            node1.add(new DefaultMutableTreeNode("\u5206\u6570\u7ba1\u7406"));
                            node1.add(new DefaultMutableTreeNode("\u6210\u7ee9\u5206\u6790"));
                            add(node1);
                            node1 = new DefaultMutableTreeNode("\u4e2a\u4eba\u4fe1\u606f");
                            node1.add(new DefaultMutableTreeNode("\u67e5\u770b\u4e2a\u4eba\u4fe1\u606f"));
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
                panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
                        javax.swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax
                        .swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java
                        .awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt
                        .Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.
                    PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("\u0062ord\u0065r".
                            equals(e.getPropertyName())) throw new RuntimeException();
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

    private void menuStuManMousePressed() {
        if (checkFrame("学生查询")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("teaNum", teacher.getTeaNum() + "");
        staticAttr.put("teaTypeId", teacher.getTeaTypeId() + "");
        SelectFrame ts = new StuQuery(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuClassInfoManMousePressed() {
        if (checkFrame("班级查询")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("TeaNum", teacher.getTeaNum() + "");
        SelectFrame ts = new lesQuery(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuLesManMousePressed() {
        if (checkFrame("课程安排查询")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("teaNum", teacher.getTeaNum() + "");
        SelectFrame ts = new CAQuery(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuScoManMousePressed() {
        if (checkFrame("分数管理查询")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("TeaNum", teacher.getTeaNum() + "");
        SelectFrame ts = new FindScore(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuScoreAnalysisMousePressed() {
        if (checkFrame("成绩分析查询")) {
            return;
        }
        Map<String, String> staticAttr = new HashMap<>();
        staticAttr.put("teaNum", teacher.getTeaNum() + "");
        SelectFrame ts = new ScoreAnalysisForTea(false, staticAttr);
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuShowPersonalInformationMousePressed(MouseEvent e) {
        menuShowPersonalInformationMousePressed();
    }

    private void menuShowPersonalInformationMousePressed() {
        TeacherUpdate teacherUpdate = new TeacherUpdate(teacher.getTeaNum());
        teacherUpdate.confirm.setVisible(false);
        teacherUpdate.frame1.setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JFrame frame1;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuStuMan;
    private JMenuItem menuItem3;
    private JMenuItem menuItem2;
    private JMenu menu2;
    private JMenu menu3;
    private JMenuItem menuItem5;
    private JMenuItem menuItem4;
    private JMenu menu4;
    private JMenuItem menuItem1;
    private JMenu menu5;
    private JTree tree1;
    private JDesktopPane desktopPane1;
    private JPanel panel1;
    private JLabel timeLab;
    private JPanel panel2;
    private JLabel InfoMsg;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
