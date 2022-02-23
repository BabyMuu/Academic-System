/*
 * Created by JFormDesigner on Sat Nov 20 16:41:21 CST 2021
 */

package frame.faceToUser;

import javax.swing.event.*;
import javax.swing.tree.*;

import entity.Teacher;
import frame.JustQuery.ExamQuery;
import frame.JustQuery.ScoreQuery;
import frame.Login.LoginFrame;
import frame.ca.CASelect;
import frame.clsInfo.ClassInfoSelect;
import frame.examSche.ESSelect;
import frame.JustQuery.lesson.LessonSelect;
import frame.score.ScoreAnalysis;
import frame.score.ScoreSelect;
import frame.select.SelectFrame;
import frame.student.StudentSelect;
import frame.teacher.TeacherSelect;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;

import frame.teacher.TeacherUpdate;
import lib.tools.Common;

/**
 * @author unknown
 */
public class AdminFrame extends JFrame {
    public Teacher tea = null;


    public AdminFrame(Teacher tea) {
        this.tea = tea;
        initComponents(); // 初始化基本框架
        initPersonalization(); // 初始化个性化内容
    }

    protected void initPersonalization() {
        System.out.println(Common.getNowTimeString());  // 控制台输出当前时间
        this.timeLab.setText(Common.getNowTimeString() + "   user: " + tea.getTeaName()); // 给窗体提供时间
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
            System.out.print(allFrame.getTitle() + "---");
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

    private void teacherManagerMousePressed(MouseEvent e) {
        if (checkFrame("教师查询")) {  // 查看 教师查询窗体是否存在
            return; // 存在, 显示窗体 即可
        }
        // 不存在, 新建窗体
        SelectFrame ts = new TeacherSelect();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true); // 窗体置顶
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }

    }

    // 向上同理
    private void menuStuManMousePressed(MouseEvent e) {
        if (checkFrame("学生查询")) {
            return;
        }
        SelectFrame ts = new StudentSelect();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    // 向上同理
    private void menuClassInfoManMousePressed(MouseEvent e) {
        if (checkFrame("班级查询")) {
            return;
        }
        SelectFrame ts = new ClassInfoSelect();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuLesManMousePressed(MouseEvent e) {
        if (checkFrame("课程查询")) {
            return;
        }
        SelectFrame ts = new LessonSelect();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuScoManMousePressed(MouseEvent e) {
        if (checkFrame("分数查询")) {
            return;
        }
        SelectFrame ts = new ScoreSelect();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuCAMousePressed(MouseEvent e) {
        if (checkFrame("课程安排查询")) {
            return;
        }
        SelectFrame ts = new CASelect();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuEsMousePressed(MouseEvent e) {
        if (checkFrame("考试安排查询")) {
            return;
        }
        SelectFrame ts = new ESSelect();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuScoreAnalysisMousePressed(MouseEvent e) {
        if (checkFrame("分数分析查询")) {
            return;
        }
        SelectFrame ts = new ScoreAnalysis();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuItem8MousePressed(MouseEvent e) {
        if (checkFrame("全部学生分数信息查询")) {
            return;
        }
        SelectFrame ts = new ScoreQuery();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void menuExamQueryMousePressed(MouseEvent e) {
        if (checkFrame("每场考试信息查询")) {
            return;
        }
        SelectFrame ts = new ExamQuery();
        desktopPane1.add(ts.jInternalFrame);
        try {
            ts.jInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void treeValueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        String name = path.getLastPathComponent().toString();
        if (isParent(name)) return;
        //去窗体集合中寻找
        if (checkFrame(name + "查询")) return;
        //如果没找到
        SelectFrame selectFrame = null;
        switch (name) {
            case "教师":
                selectFrame = new TeacherSelect();
                break;
            case "学生":
                selectFrame = new StudentSelect();
                break;
            case "班级":
                selectFrame = new ClassInfoSelect();
                break;
            case "课程":
                selectFrame = new LessonSelect();
                break;
            case "课程安排":
                selectFrame = new CASelect();
                break;
            case "分数":
                selectFrame = new ScoreSelect();
                break;
            case "考试安排":
                selectFrame = new ESSelect();
                break;
            case "全部学生分数信息":
                selectFrame = new ScoreQuery();
                break;
            case "个人信息":
                menuPersonalInformationMousePressed();
                return;
        }

        selectFrame.jInternalFrame.setVisible(true);
        desktopPane1.add(selectFrame.jInternalFrame);

        try {
            selectFrame.jInternalFrame.setSelected(true);
        } catch (
                PropertyVetoException e1) {
            e1.printStackTrace();
        }
    }

    private boolean isParent(String name) {
        for (String fcd : new String[]{"系统菜单", "基础信息管理", "业务管理", "考试管理"}) {
            if (name.equals(fcd))
                return true;
        }
        return false;
    }

    private void menu5MousePressed(MouseEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "您确定要退出帐号么?", "切换帐号", JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0) {
            this.frame1.dispose();
            new LoginFrame();
        }
    }

    private void menuPersonalInformationMousePressed(MouseEvent e) {
        menuPersonalInformationMousePressed();
    }

    private void menuPersonalInformationMousePressed() {
        TeacherUpdate teacherUpdate = new TeacherUpdate(tea.getTeaNum());
        teacherUpdate.confirm.setVisible(false);
        teacherUpdate.frame1.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        frame1 = new JFrame();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuStuMan = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu2 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem8 = new JMenuItem();
        menu3 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menuItem6 = new JMenuItem();
        menuItem9 = new JMenuItem();
        menu4 = new JMenu();
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
            frame1.setTitle("MainFrame");
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //======== menuBar1 ========
            {

                //======== menu1 ========
                {
                    menu1.setText("\u57fa\u7840\u6570\u636e");

                    //---- menuItem1 ----
                    menuItem1.setText("\u8001\u5e08\u7ba1\u7406");
                    menuItem1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            teacherManagerMousePressed(e);
                        }
                    });
                    menu1.add(menuItem1);

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

                    //---- menuItem5 ----
                    menuItem5.setText("\u8bfe\u7a0b\u5b89\u6392");
                    menuItem5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuCAMousePressed(e);
                        }
                    });
                    menu2.add(menuItem5);

                    //---- menuItem8 ----
                    menuItem8.setText("\u67e5\u770b\u6240\u6709\u5b66\u751f\u6210\u7ee9");
                    menuItem8.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuItem8MousePressed(e);
                        }
                    });
                    menu2.add(menuItem8);
                }
                menuBar1.add(menu2);

                //======== menu3 ========
                {
                    menu3.setText("\u8003\u8bd5\u7ba1\u7406");

                    //---- menuItem4 ----
                    menuItem4.setText("\u5206\u6570\u7ba1\u7406");
                    menuItem4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuScoManMousePressed(e);
                        }
                    });
                    menu3.add(menuItem4);

                    //---- menuItem7 ----
                    menuItem7.setText("\u5206\u6570\u5206\u6790");
                    menuItem7.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuScoreAnalysisMousePressed(e);
                        }
                    });
                    menu3.add(menuItem7);

                    //---- menuItem6 ----
                    menuItem6.setText("\u8003\u8bd5\u5b89\u6392");
                    menuItem6.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuEsMousePressed(e);
                        }
                    });
                    menu3.add(menuItem6);

                    //---- menuItem9 ----
                    menuItem9.setText("\u8003\u8bd5\u4fe1\u606f\u5206\u6790");
                    menuItem9.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuExamQueryMousePressed(e);
                        }
                    });
                    menu3.add(menuItem9);
                }
                menuBar1.add(menu3);

                //======== menu4 ========
                {
                    menu4.setText("\u4e2a\u4eba\u4fe1\u606f");
                    menu4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuPersonalInformationMousePressed(e);
                        }
                    });
                }
                menuBar1.add(menu4);

                //======== menu5 ========
                {
                    menu5.setText("\u5207\u6362\u5e10\u53f7");
                    menu5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menu5MousePressed(e);
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
                            node1.add(new DefaultMutableTreeNode("\u6559\u5e08"));
                            node1.add(new DefaultMutableTreeNode("\u73ed\u7ea7"));
                            node1.add(new DefaultMutableTreeNode("\u5b66\u751f"));
                            node1.add(new DefaultMutableTreeNode("\u8bfe\u7a0b"));
                        add(node1);
                        node1 = new DefaultMutableTreeNode("\u4e1a\u52a1\u7ba1\u7406");
                            node1.add(new DefaultMutableTreeNode("\u8bfe\u7a0b\u5b89\u6392"));
                        add(node1);
                        node1 = new DefaultMutableTreeNode("\u8003\u8bd5\u7ba1\u7406");
                            node1.add(new DefaultMutableTreeNode("\u8003\u8bd5\u5b89\u6392"));
                            node1.add(new DefaultMutableTreeNode("\u5206\u6570"));
                        add(node1);
                        add(new DefaultMutableTreeNode("\u4e2a\u4eba\u4fe1\u606f"));
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
                panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
                swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border
                . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog"
                , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel1. getBorder
                () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
                . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException
                ( ) ;} } );
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JFrame frame1;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuStuMan;
    private JMenuItem menuItem3;
    private JMenuItem menuItem2;
    private JMenu menu2;
    private JMenuItem menuItem5;
    private JMenuItem menuItem8;
    private JMenu menu3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem7;
    private JMenuItem menuItem6;
    private JMenuItem menuItem9;
    private JMenu menu4;
    private JMenu menu5;
    private JTree tree1;
    private JDesktopPane desktopPane1;
    private JPanel panel1;
    private JLabel timeLab;
    private JPanel panel2;
    private JLabel InfoMsg;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
