/*
 * Created by JFormDesigner on Wed Dec 22 10:47:13 CST 2021
 */

package frame.score;

import dao.imp.ScoreDAOImp;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

import frame.JustQuery.FindScore;
import lib.tools.show;

/**
 * @author unknown
 */
public class ScoMoreQuery extends JDialog {
    private Map<String, String> attrValue;    // 属性 - 值
    private Map<String, String> staticAttr;
    FindScore fs;

    public ScoMoreQuery(Map<String, String> map) {
        this.attrValue = map;
        initComponents();
    }

    public ScoMoreQuery(Map<String, String> attrValue, Map<String, String> staticAttr) {
        this.attrValue = attrValue;
        this.staticAttr = staticAttr;
        initComponents();
    }

    public ScoMoreQuery(Map<String, String> attrValue, Map<String, String> staticAttr, FindScore fs) {
        this.fs = fs;
        this.attrValue = attrValue;
        this.staticAttr = staticAttr;
        initComponents();
    }

    private void okButtonMousePressed(MouseEvent e) {
        if (attrValue != null) attrValue.clear(); // 如果以前查询过 则里面应该有数据, 所以传进应清空map
        String scoId = textScoNum.getText();
        String esId = textEsNum.getText();
        String stuNum = textStuNum.getText();
        String scoMin = textScoMin.getText();
        String scoMax = textScoMax.getText();
        // 检测
        if (!scoMin.equals("") || !(scoMax.equals(""))) {
            if (scoMin.equals("")) {
                int max = Integer.parseInt(textScoMax.getText());
                if (max <= 100 && max >= 0) {
                    attrValue.put("bet", "0 " + max);
                } else {
                    show.text_error(textScoMax, ">=0 or <= 100");
                    return;
                }
            } else if (scoMax.equals("")) {
                int min = Integer.parseInt(textScoMin.getText());
                if (min <= 100 && min >= 0) {
                    attrValue.put("bet", min + " 100");
                } else {
                    show.text_error(textScoMin, ">=0 or <= 100");
                }
            } else {
                int max = Integer.parseInt(textScoMax.getText());
                int min = Integer.parseInt(textScoMin.getText());
                if (max >= min) {
                    attrValue.put("bet", min + " " + max);
                } else {
                    JOptionPane.showMessageDialog(null, "分数区间小的应该小于等于分数区间大的");
                }
            }
        }

        if (!scoId.equals("")) {
            attrValue.put("scoreId", scoId);
        }
        if (!esId.equals("")) {
            attrValue.put("esId", esId);
        }
        if (!stuNum.equals("")) {
            attrValue.put("stuNum", stuNum);
        }
        if (attrValue.size() == 0) {
            JOptionPane.showMessageDialog(null, "请至少填入一项来进行查询");
            return;
        }
        ScoreDAOImp sdi = new ScoreDAOImp();
        if (sdi.findByMap(attrValue, 1, 1) != null) {
            if (fs == null) {
                ScoreSelect.ISCONFIRM = true;
            } else {
                FindScore.ISCONFIRM = true;
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "没有找到您想要找的分数信息\n请重新查看您的筛选条件");
        }
    }

    private void cancelButtonMousePressed(MouseEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        contentPanel = new JPanel();
        labScoreId = new JLabel();
        labEsId = new JLabel();
        labStuNum = new JLabel();
        labScoBet = new JLabel();
        textScoNum = new JTextField();
        textEsNum = new JTextField();
        textStuNum = new JTextField();
        textScoMin = new JTextField();
        textScoMax = new JTextField();
        label2 = new JLabel();
        okButton = new JButton();
        cancelButton = new JButton();
        baseInfo = new JEditorPane();

        //======== this ========
        setTitle("\u6559\u5e08\u67e5\u8be2(\u9ad8\u7ea7\u67e5\u8be2)");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== contentPanel ========
        {
            contentPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0
                    , 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM
                    , new java.awt.Font("D\u0069alog", java.awt.Font.BOLD, 12), java.awt.Color.red),
                    contentPanel.getBorder()));
            contentPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e
                ) {if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException();}
            });
            contentPanel.setLayout(null);

            //---- labScoreId ----
            labScoreId.setText("\u5206\u6570\u7f16\u53f7");
            labScoreId.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            labScoreId.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(labScoreId);
            labScoreId.setBounds(70, 40, 85, 35);

            //---- labEsId ----
            labEsId.setText("\u8003\u8bd5\u7f16\u53f7");
            labEsId.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            labEsId.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(labEsId);
            labEsId.setBounds(430, 40, 85, 35);

            //---- labStuNum ----
            labStuNum.setText("\u5b66\u751f\u7f16\u53f7");
            labStuNum.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            labStuNum.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(labStuNum);
            labStuNum.setBounds(75, 115, 80, 35);

            //---- labScoBet ----
            labScoBet.setText("\u5206\u6570\u533a\u95f4");
            labScoBet.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            labScoBet.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(labScoBet);
            labScoBet.setBounds(430, 115, 85, 35);

            //---- textScoNum ----
            textScoNum.setBorder(LineBorder.createBlackLineBorder());
            textScoNum.setForeground(Color.white);
            textScoNum.setBackground(Color.gray);
            textScoNum.setFont(new Font("Courier New", Font.PLAIN, 18));
            textScoNum.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(textScoNum);
            textScoNum.setBounds(155, 40, 225, 35);

            //---- textEsNum ----
            textEsNum.setBorder(LineBorder.createBlackLineBorder());
            textEsNum.setForeground(Color.white);
            textEsNum.setBackground(Color.gray);
            textEsNum.setFont(new Font("Courier New", Font.PLAIN, 18));
            textEsNum.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(textEsNum);
            textEsNum.setBounds(530, 40, 240, 35);

            //---- textStuNum ----
            textStuNum.setBorder(LineBorder.createBlackLineBorder());
            textStuNum.setForeground(Color.white);
            textStuNum.setBackground(Color.gray);
            textStuNum.setFont(new Font("Courier New", Font.PLAIN, 18));
            textStuNum.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(textStuNum);
            textStuNum.setBounds(155, 115, 225, 35);

            //---- textScoMin ----
            textScoMin.setBorder(LineBorder.createBlackLineBorder());
            textScoMin.setForeground(Color.white);
            textScoMin.setBackground(Color.gray);
            textScoMin.setFont(new Font("Courier New", Font.PLAIN, 18));
            textScoMin.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(textScoMin);
            textScoMin.setBounds(530, 115, 95, 35);

            //---- textScoMax ----
            textScoMax.setBorder(LineBorder.createBlackLineBorder());
            textScoMax.setForeground(Color.white);
            textScoMax.setBackground(Color.gray);
            textScoMax.setFont(new Font("Courier New", Font.PLAIN, 18));
            textScoMax.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(textScoMax);
            textScoMax.setBounds(675, 115, 95, 35);

            //---- label2 ----
            label2.setText("~");
            label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
            label2.setHorizontalTextPosition(SwingConstants.CENTER);
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(label2);
            label2.setBounds(625, 115, 50, 35);

            //---- okButton ----
            okButton.setText("OK");
            okButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    okButtonMousePressed(e);
                }
            });
            contentPanel.add(okButton);
            okButton.setBounds(320, 195, 80, 35);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    cancelButtonMousePressed(e);
                }
            });
            contentPanel.add(cancelButton);
            cancelButton.setBounds(475, 195, 80, 35);

            //---- baseInfo ----
            baseInfo.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "\u57fa\u7840\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                    new Font("\u6977\u4f53", Font.BOLD, 16), Color.orange));
            baseInfo.setBackground(Color.gray);
            baseInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            baseInfo.setEditable(false);
            baseInfo.setAutoscrolls(false);
            contentPanel.add(baseInfo);
            baseInfo.setBounds(0, 0, 855, 290);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < contentPanel.getComponentCount(); i++) {
                    Rectangle bounds = contentPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = contentPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                contentPanel.setMinimumSize(preferredSize);
                contentPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(contentPanel);
        contentPanel.setBounds(0, 0, 870, 300);

        contentPane.setPreferredSize(new Dimension(870, 330));
        setSize(870, 330);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel contentPanel;
    private JLabel labScoreId;
    private JLabel labEsId;
    private JLabel labStuNum;
    private JLabel labScoBet;
    JTextField textScoNum;
    JTextField textEsNum;
    JTextField textStuNum;
    JTextField textScoMin;
    JTextField textScoMax;
    private JLabel label2;
    JButton okButton;
    private JButton cancelButton;
    private JEditorPane baseInfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
