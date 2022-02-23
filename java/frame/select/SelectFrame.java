/*
 * Created by JFormDesigner on Wed Oct 27 23:46:43 CST 2021
 */

package frame.select;


import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import frame.Login.LoginFrame;
import lib.tools.*;

/**
 * @author unknown
 */
public abstract class SelectFrame {

    protected String value = "";
    protected int textNum;
    protected int nowPage = 1;  // 当前页号
    protected int pageSize = 20;   // 一页有 pagesize 行数据
    protected int pageCount = 1;   // 总页数
    protected boolean isDialog = false;
    public Map<String, String> staticAttr;
    protected Map<String, String> attrValue = new HashMap<>();
    public DefaultTableModel model;
    public static boolean ISALL = false;
    private boolean isSetData = true;

    /**
     * 默认构造方法
     */
    public SelectFrame() {
        init();  // 初始化其他部件
    }

    /**
     * 当需要窗体为模式窗体时的构造方法
     *
     * @param isDialog 是否选择该窗体为模式窗体
     */
    public SelectFrame(boolean isDialog) {
        this.isDialog = isDialog;
        init();
    }

    public SelectFrame(boolean isDialog, Map<String, String> staticAttr) {
        this.staticAttr = staticAttr;
        this.isDialog = isDialog;
        init();
    }

    public SelectFrame(boolean isDialog, Map<String, String> staticAttr, boolean isSetData) {
        this.staticAttr = staticAttr;
        this.isDialog = isDialog;
        this.isSetData = isSetData;
        init();
    }

    public void init() {
        if (isDialog) initDiolog(); // 模式窗体新建
        else initFrame();  // 正常窗体创建

        initComponents();  // 搭建窗体骨架
        // ---- 全局个性化 ----
        // 为按钮添加图标
        search.setIcon(new ImageIcon("java/lib/png/search.png"));
        confirm.setIcon(new ImageIcon("java/lib/png/add.png"));
        change.setIcon(new ImageIcon("java/lib/png/change.png"));
        delete.setIcon(new ImageIcon("java/lib/png/delete.png"));
        if (isDialog) {
            System.out.println("diolog");
            delete.setVisible(false);
        }
        if (LoginFrame.ROLE.equals("Student")) {
            hideBtn();
        }
        // 自定义表格
        OwnTable.setTableStyle(tableList);
        // ---- 个性化 ----
        initPersonalization();
        // ---- 设置数据 ----
        ISALL = staticAttr == null;
        if (isSetData) {
            setData();
        }
    }

    protected void hideBtn() {
        this.change.setVisible(false);
        this.ret.setVisible(false);
        this.delete.setVisible(false);
        this.confirm.setVisible(false);
    }

    protected void hidePageControl() {
        this.button1.setVisible(false);
        this.button2.setVisible(false);
        this.button3.setVisible(false);
        this.button4.setVisible(false);
        this.comboNowPage.setVisible(false);
    }

    public JInternalFrame jInternalFrame;
    public JDialog jDialog = new JDialog();

    /* 创建正常窗体 */
    private void initFrame() {
        jInternalFrame = new JInternalFrame("", false, true, false, false);
        jInternalFrame.setVisible(true); // 设置可见
        jInternalFrame.setBackground(Color.gray); // 设置背景颜色
        jInternalFrame.setName("teacherFrame"); // 设置窗体名称
        jInternalFrame.setBounds(0, 0, 1040, 755); // 设置窗体大小
        jInternalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置窗体关闭方式
    }

    /* 创建模式窗体 */
    private void initDiolog() {
        jDialog = new JDialog();
        jDialog.setResizable(false);
        jDialog.setBackground(Color.gray);
        jDialog.setName("teacherFrame");
        jDialog.setBounds(0, 0, 1040, 755);
        jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jDialog.setLocationRelativeTo(null); //设置窗体居中
    }

    protected abstract void setPage();

    protected abstract void initPersonalization(); // 个性化窗口

    protected abstract boolean setModel(); // 加载数据

    protected abstract void searchMousePressed(MouseEvent e);  // 查询窗口事件

    protected abstract void changeMousePressed(MouseEvent e); // 更改事件

    protected abstract void insertMousePressed(MouseEvent e); // 插入事件

    protected abstract void buttonMoreMousePressed(MouseEvent e); // 更多按钮事件

    protected abstract void deleteMousePressed(MouseEvent e); // 删除事件

    /**
     * 根据选中行列返回编号
     *
     * @return "" : 错误操作
     * string : 编号
     */
    protected String get_key_by_row_col(String key) {
        int rowSelected = tableList.getSelectedRow(); // 获取选中的行
        if (rowSelected == -1) { // 表示未选中任何一行
            JOptionPane.showMessageDialog(null, "请选择要修改的数据行");
            return "";
        }
        // 获取选中行的教师编号的所在列
        int idNum;
        for (idNum = 0; ; idNum++) {
            if (key.equals(tableList.getColumnName(idNum)))
                break;
        }
        // 根据上面获得的行和列确定教师编号
        return tableList.getValueAt(rowSelected, idNum).toString();
    }

    protected String get_teaName_by_row_col() {
        int rowSelected = tableList.getSelectedRow(); // 获取选中的行
        if (rowSelected == -1) { // 表示未选中任何一行
            JOptionPane.showMessageDialog(null, "请选择要修改的数据行");
            return "";
        }
        // 获取选中行的教师编号的所在列
        int name;
        for (name = 0; ; name++) {
            if ("姓名".equals(tableList.getColumnName(name)))
                break;
        }
        // 根据上面获得的行和列确定教师编号
        return tableList.getValueAt(rowSelected, name).toString();
    }

    protected void btnFirstPageMousePressed(MouseEvent e) { // 第一页 按钮事件
        if (nowPage <= 1) {
            JOptionPane.showMessageDialog(null, "已经是第一页了");
        } else {
            nowPage = 1; // 设置当前页 为 1
            comboNowPage.setSelectedIndex(0);
            setModel(); // 刷新数据
        }
    }

    protected void btnFinalPageMousePressed(MouseEvent e) {
        if (nowPage == pageCount) {
            JOptionPane.showMessageDialog(null, "已经是最后一页了");
        } else {
            nowPage = pageCount; // 设置当前页为最后一页
            comboNowPage.setSelectedIndex(nowPage - 1);
            setModel(); // 刷新数据
        }
    }

    protected void btnNextPageMousePressed(MouseEvent e) {
        if (nowPage >= pageCount) {
            JOptionPane.showMessageDialog(null, "已经是最后一页了");
        } else {
            nowPage++; // 将当前页号 + 1
            comboNowPage.setSelectedIndex(nowPage - 1);
            setModel(); // 刷新数据
        }
    }

    protected void setData() {
        MapTools.mapExtend(attrValue, staticAttr);
        setModel();
        setPage();
    }

    private void btnLastPageMousePressed(MouseEvent e) {
        if (nowPage <= 1) {
            JOptionPane.showMessageDialog(null, "已经是第一页了");
        } else {
            nowPage--;  // 将当前页号 -1
            comboNowPage.setSelectedIndex(nowPage - 1);
            setModel(); // 刷新数据
        }
    }

    private void comboNowPageItemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            if (nowPage == comboNowPage.getSelectedIndex() + 1) return;
            nowPage = comboNowPage.getSelectedIndex() + 1;
            setModel();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        frame1 = new JInternalFrame();
        search = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        text1 = new JTextField();
        text2 = new JTextField();
        btnMore = new JButton();
        confirm = new JButton();
        change = new JButton();
        delete = new JButton();
        ret = new JButton();
        scrollPane1 = new JScrollPane();
        tableList = new JTable();
        editorPane1 = new JEditorPane();
        button1 = new JButton();
        button2 = new JButton();
        comboNowPage = new JComboBox();
        button3 = new JButton();
        button4 = new JButton();
        labPageCount = new JLabel();

        //======== frame1 ========
        {
            Container frameContentPane;
            if (isDialog) {
                frameContentPane = jDialog.getContentPane();
                this.confirm.setVisible(false);
                this.change.setVisible(false);
                this.ret.setVisible(false);
            } else {
                frameContentPane = jInternalFrame.getContentPane();
            }
            frameContentPane.setLayout(null);
            //======== scrollPane1 ========
            {

                //---- tableList ----
                tableList.setRowHeight(21);
                scrollPane1.setViewportView(tableList);
            }
            frameContentPane.add(scrollPane1);
            scrollPane1.setBounds(70, 140, 900, 448);
            //---- search ----
            search.setBorder(LineBorder.createBlackLineBorder());
            search.setIcon(null);
            search.setSelectedIcon(null);
            search.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    searchMousePressed(e);
                }
            });
            frameContentPane.add(search);
            search.setBounds(865, 40, 85, 60);

            //---- label1 ----
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            frameContentPane.add(label1);
            label1.setBounds(85, 50, 75, 35);

            //---- label2 ----
            label2.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            frameContentPane.add(label2);
            label2.setBounds(405, 50, 110, 35);

            //---- text1 ----
            text1.setBorder(LineBorder.createBlackLineBorder());
            text1.setForeground(new Color(102, 102, 102));
            frameContentPane.add(text1);
            text1.setBounds(175, 50, 200, 30);

            //---- text2 ----
            text2.setBorder(LineBorder.createBlackLineBorder());
            text2.setForeground(new Color(102, 102, 102));
            frameContentPane.add(text2);
            text2.setBounds(535, 50, 200, 30);

            //---- button5 ----
            btnMore.setText("More");
            btnMore.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    buttonMoreMousePressed(e);
                }
            });
            frameContentPane.add(btnMore);
            btnMore.setVisible(false);
            btnMore.setBounds(745, 50, 80, 30);

            //---- confirm ----
            confirm.setBorder(new EmptyBorder(5, 5, 5, 5));
            confirm.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    insertMousePressed(e);
                }
            });
            frameContentPane.add(confirm);
            confirm.setBounds(265, 640, 80, 60);

            //---- change ----
            change.setBorder(new EmptyBorder(5, 5, 5, 5));
            change.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    changeMousePressed(e);
                }
            });
            frameContentPane.add(change);
            change.setBounds(455, 640, 80, 60);

            //---- ret ----
            ret.setText("Return");
            ret.setBorder(new EmptyBorder(5, 5, 5, 5));
            frameContentPane.add(ret);

            //---- delete ----
//            delete.setText("Delete");
            delete.setBorder(new EmptyBorder(5, 5, 5, 5));
            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    deleteMousePressed(e);
                }
            });
            frameContentPane.add(delete);
            delete.setBounds(625, 640, 80, 60);


            //---- editorPane1 ----
            editorPane1.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.lightGray), "\u67e5\u8be2", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                    new Font("\u96b6\u4e66", Font.BOLD, 16), Color.orange));
            editorPane1.setEditable(false);
            editorPane1.setBackground(new Color(238, 238, 238));
            frameContentPane.add(editorPane1);
            editorPane1.setBounds(68, 20, 767, 105);

            //---- button1 ----
            button1.setText("\u4e0b\u4e00\u9875");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnNextPageMousePressed(e);
                }
            });
            frameContentPane.add(button1);
            button1.setBounds(840, 600, 80, 30);

            //---- button2 ----
            button2.setText("\u4e0a\u4e00\u9875");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnLastPageMousePressed(e);
                }
            });
            frameContentPane.add(button2);
            button2.setBounds(750, 600, 80, 30);

            //---- comboNowPage ----
            comboNowPage.addItemListener(e -> comboNowPageItemStateChanged(e));
            frameContentPane.add(comboNowPage);
            comboNowPage.setBounds(460, 600, 80, 30);

            //---- button3 ----
            button3.setText("\u7b2c\u4e00\u9875");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnFirstPageMousePressed(e);
                }
            });
            frameContentPane.add(button3);
            button3.setBounds(90, 600, 80, 30);

            //---- button4 ----
            button4.setText("\u6700\u7ec8\u9875");
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnFinalPageMousePressed(e);
                }
            });
            frameContentPane.add(button4);
            button4.setBounds(180, 600, 80, 30);

            //---- labPageCount ----
            labPageCount.setHorizontalAlignment(SwingConstants.CENTER);
            frameContentPane.add(labPageCount);
            labPageCount.setBounds(370, 600, 80, 30);

            frameContentPane.setPreferredSize(new Dimension(1040, 755));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JInternalFrame frame1;
    protected JButton search;
    protected JLabel label1;
    protected JLabel label2;
    protected JTextField text1;
    protected JTextField text2;
    protected JButton btnMore;
    protected JButton confirm;
    protected JButton change;
    protected JButton delete;
    protected JButton ret;
    protected JScrollPane scrollPane1;
    protected JTable tableList;
    protected JEditorPane editorPane1;
    protected JButton button1;
    protected JButton button2;
    protected JComboBox comboNowPage;
    protected JButton button3;
    protected JButton button4;
    protected JLabel labPageCount;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

