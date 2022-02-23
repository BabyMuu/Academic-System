/*
 * Created by JFormDesigner on Sun Dec 26 19:33:04 CST 2021
 */

package frame.select.pingbi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class SelectFrame  {

    private void searchMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void buttonMoreMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void insertMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void changeMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void btnNextPageMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void btnLastPageMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void comboNowPageItemStateChanged(ItemEvent e) {
        // TODO add your code here
    }

    private void btnFirstPageMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void btnFinalPageMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        frame1 = new JInternalFrame();
        search = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        text1 = new JTextField();
        text2 = new JTextField();
        text3 = new JTextField();
        editorPane1 = new JEditorPane();
        button5 = new JButton();
        scrollPane1 = new JScrollPane();
        tableList = new JTable();
        panel1 = new JPanel();
        confirm = new JButton();
        change = new JButton();
        ret = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        comboNowPage = new JComboBox();
        button3 = new JButton();
        button4 = new JButton();
        labPageCount = new JLabel();

        //======== frame1 ========
        {
            frame1.setVisible(true);
            frame1.setResizable(false);
            frame1.setBackground(Color.gray);
            frame1.setName("teacherFrame");
            frame1.setPreferredSize(new Dimension(1040, 755));
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

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
            frame1ContentPane.add(search);
            search.setBounds(865, 40, 85, 60);

            //---- label1 ----
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            frame1ContentPane.add(label1);
            label1.setBounds(90, 55, 75, 35);

            //---- label2 ----
            label2.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            frame1ContentPane.add(label2);
            label2.setBounds(405, 40, 110, 35);

            //---- label3 ----
            label3.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            frame1ContentPane.add(label3);
            label3.setBounds(405, 80, 110, 35);

            //---- text1 ----
            text1.setBorder(LineBorder.createBlackLineBorder());
            text1.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(text1);
            text1.setBounds(175, 55, 200, 35);

            //---- text2 ----
            text2.setBorder(LineBorder.createBlackLineBorder());
            text2.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(text2);
            text2.setBounds(535, 40, 200, 35);

            //---- text3 ----
            text3.setBorder(LineBorder.createBlackLineBorder());
            text3.setForeground(new Color(102, 102, 102));
            frame1ContentPane.add(text3);
            text3.setBounds(535, 80, 200, 35);

            //---- editorPane1 ----
            editorPane1.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.lightGray), "\u67e5\u8be2", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                new Font("\u96b6\u4e66", Font.BOLD, 16), Color.orange));
            editorPane1.setEditable(false);
            editorPane1.setBackground(Color.gray);
            frame1ContentPane.add(editorPane1);
            editorPane1.setBounds(68, 20, 767, 105);

            //---- button5 ----
            button5.setText("more");
            button5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    buttonMoreMousePressed(e);
                }
            });
            frame1ContentPane.add(button5);
            button5.setBounds(750, 50, 73, 30);

            //======== scrollPane1 ========
            {

                //---- tableList ----
                tableList.setRowHeight(21);
                scrollPane1.setViewportView(tableList);
            }
            frame1ContentPane.add(scrollPane1);
            scrollPane1.setBounds(50, 15, 920, 573);

            //======== panel1 ========
            {
                panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
                javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax
                . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
                . awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
                . Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .
                PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .
                equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
                panel1.setLayout(null);

                //---- confirm ----
                confirm.setBorder(new EmptyBorder(5, 5, 5, 5));
                confirm.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        insertMousePressed(e);
                    }
                });
                panel1.add(confirm);
                confirm.setBounds(245, 20, 80, 60);

                //---- change ----
                change.setBorder(new EmptyBorder(5, 5, 5, 5));
                change.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        changeMousePressed(e);
                    }
                });
                panel1.add(change);
                change.setBounds(425, 20, 80, 60);

                //---- ret ----
                ret.setText("Return");
                ret.setBorder(new EmptyBorder(5, 5, 5, 5));
                panel1.add(ret);
                ret.setBounds(600, 20, 80, 60);
            }
            frame1ContentPane.add(panel1);
            panel1.setBounds(40, 630, 960, 95);

            //---- button1 ----
            button1.setText("\u4e0b\u4e00\u9875");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnNextPageMousePressed(e);
                }
            });
            frame1ContentPane.add(button1);
            button1.setBounds(840, 600, 80, 30);

            //---- button2 ----
            button2.setText("\u4e0a\u4e00\u9875");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnLastPageMousePressed(e);
                }
            });
            frame1ContentPane.add(button2);
            button2.setBounds(750, 600, 80, 30);

            //---- comboNowPage ----
            comboNowPage.addItemListener(e -> comboNowPageItemStateChanged(e));
            frame1ContentPane.add(comboNowPage);
            comboNowPage.setBounds(460, 600, 80, 30);

            //---- button3 ----
            button3.setText("\u7b2c\u4e00\u9875");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnFirstPageMousePressed(e);
                }
            });
            frame1ContentPane.add(button3);
            button3.setBounds(90, 600, 80, 30);

            //---- button4 ----
            button4.setText("\u6700\u7ec8\u9875");
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btnFinalPageMousePressed(e);
                }
            });
            frame1ContentPane.add(button4);
            button4.setBounds(180, 600, 80, 30);

            //---- labPageCount ----
            labPageCount.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(labPageCount);
            labPageCount.setBounds(370, 600, 80, 30);

            frame1ContentPane.setPreferredSize(new Dimension(1040, 755));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JInternalFrame frame1;
    protected JButton search;
    protected JLabel label1;
    protected JLabel label2;
    protected JLabel label3;
    protected JTextField text1;
    protected JTextField text2;
    protected JTextField text3;
    protected JEditorPane editorPane1;
    private JButton button5;
    protected JScrollPane scrollPane1;
    protected JTable tableList;
    private JPanel panel1;
    protected JButton confirm;
    protected JButton change;
    protected JButton ret;
    protected JButton button1;
    protected JButton button2;
    protected JComboBox comboNowPage;
    protected JButton button3;
    protected JButton button4;
    protected JLabel labPageCount;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
