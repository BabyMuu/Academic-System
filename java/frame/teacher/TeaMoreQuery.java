/*
 * Created by JFormDesigner on Sun Nov 07 20:47:31 CST 2021
 */

package frame.teacher;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import dao.imp.TeacherDaoImp;
import lib.tools.MapTools;

/**
 * @author BabyMuu
 */
public class TeaMoreQuery extends JDialog {
    private Map<String, String> attrValue;    // 属性 - 值
    private Map<String, String> staticAttr;

    public TeaMoreQuery(Map<String, String> map) {
        this.attrValue = map;
        initComponents();
    }

    public TeaMoreQuery(Map<String, String> attrValue, Map<String, String> staticAttr) {
        this.attrValue = attrValue;
        this.staticAttr = staticAttr;
        initComponents();
        if (staticAttr != null) {
            for (String s : staticAttr.keySet()) {
                if (s.equals("teaTypeId")) {
                    this.teaType.setVisible(false);
                }
            }
        }
    }

    private void okButtonMousePressed(MouseEvent e) {
        if (attrValue != null) attrValue.clear(); // 如果以前查询过 则里面应该有数据, 所以传进应清空map
        MapTools.mapExtend(attrValue, staticAttr);
        String name = this.textTeaName.getText();
        String num = this.textTeaNum.getText();
        StringBuilder title = new StringBuilder();
        StringBuilder type = new StringBuilder();
        StringBuilder state = new StringBuilder();
        // 非空判断, 如果不为空 则添加属性名和属性值到map
        // 1 名字
        if (!name.equals("")) attrValue.put("teaName", name);
        // 2 编号
        if (!num.equals("")) attrValue.put("teaNum", num);
        // 3 职称
        for (JCheckBox jCheckBox : new JCheckBox[]{this.cBProfessor, this.cbAssociateProfessor, this.cbLecturer, this.cbTeachingAssistant}) {
            if (jCheckBox.isSelected()) title.append(jCheckBox.getText().trim()).append(" ");
        }
        if (title.length() != 0) attrValue.put("teaTitle", title.toString().trim());
        // 4 执教类型
        if (this.cBTea.isSelected()) type.append("1 ");
        if (this.cBTeaInstructor.isSelected()) type.append("2 ");
        if (type.length() != 0) attrValue.put("teaTypeId", type.toString().trim());
        // 5 状态
        int index_state = 0;
        for (JCheckBox jCheckBox : new JCheckBox[]{this.cbNormal, this.cBHoliday, this.cbSuspended}) {
            index_state++;
            if (jCheckBox.isSelected()) state.append(index_state).append(" ");
        }
        if (state.length() != 0) attrValue.put("state", state.toString().trim());

        if (attrValue.size() == 0) {
            JOptionPane.showMessageDialog(null, "请至少填入一项来进行查询");
            return;
        }
        TeacherDaoImp tdi = new TeacherDaoImp();
        if (tdi.findByMap(attrValue, 1, 1) != null) {
            TeacherSelect.ISCONFIRM = true;
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "没有找到您想要找的教师\n请重新查看您的筛选条件");
        }
    }

    private void cancelButtonMousePressed(MouseEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        contentPanel = new JPanel();
        teaType = new JPanel();
        label6 = new JLabel();
        cBTea = new JCheckBox();
        cBTeaInstructor = new JCheckBox();
        label1 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        label7 = new JLabel();
        cBProfessor = new JCheckBox();
        cbAssociateProfessor = new JCheckBox();
        cbNormal = new JCheckBox();
        cbSuspended = new JCheckBox();
        cBHoliday = new JCheckBox();
        cbLecturer = new JCheckBox();
        cbTeachingAssistant = new JCheckBox();
        textTeaName = new JTextField();
        textTeaNum = new JTextField();
        okButton = new JButton();
        cancelButton = new JButton();
        baseInfo = new JEditorPane();

        //======== this ========
        setTitle("\u6559\u5e08\u67e5\u8be2(\u9ad8\u7ea7\u67e5\u8be2)");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== contentPanel ========
        {
            contentPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
                    javax.swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax
                    .swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java
                    .awt.Font("Dialo\u0067", java.awt.Font.BOLD, 12), java.awt
                    .Color.red), contentPanel.getBorder())); contentPanel.addPropertyChangeListener(new java.beans.
                PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("borde\u0072".
                        equals(e.getPropertyName())) throw new RuntimeException();
            }
        });
            contentPanel.setLayout(null);

            //======== panel1 ========
            {
                teaType.setBackground(Color.gray);
                teaType.setLayout(null);

                //---- label6 ----
                label6.setText("\u6267\u6559\u7c7b\u578b");
                label6.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
                label6.setHorizontalAlignment(SwingConstants.CENTER);
                teaType.add(label6);
                label6.setBounds(0, 0, 85, 35);

                //---- cBTea ----
                cBTea.setText("\u8bb2\u5e08");
                cBTea.setBackground(Color.gray);
                cBTea.setBorderPainted(true);
                cBTea.setBorder(new MatteBorder(1, 1, 1, 0, Color.black));
                cBTea.setHorizontalAlignment(SwingConstants.CENTER);
                teaType.add(cBTea);
                cBTea.setBounds(85, 5, 65, 35);

                //---- cBTeaInstructor ----
                cBTeaInstructor.setText("\u52a9\u6559");
                cBTeaInstructor.setBackground(Color.gray);
                cBTeaInstructor.setBorderPainted(true);
                cBTeaInstructor.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
                cBTeaInstructor.setHorizontalAlignment(SwingConstants.CENTER);
                teaType.add(cBTeaInstructor);
                cBTeaInstructor.setBounds(150, 5, 80, 35);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < teaType.getComponentCount(); i++) {
                        Rectangle bounds = teaType.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = teaType.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    teaType.setMinimumSize(preferredSize);
                    teaType.setPreferredSize(preferredSize);
                }
            }
            contentPanel.add(teaType);
            teaType.setBounds(445, 105, 390, 65);

            //---- label1 ----
            label1.setText("\u59d3\u540d");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(label1);
            label1.setBounds(75, 40, 85, 35);

            //---- label2 ----
            label2.setText("\u804c\u79f0");
            label2.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(label2);
            label2.setBounds(75, 165, 80, 35);

            //---- label5 ----
            label5.setText("\u7f16\u53f7");
            label5.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(label5);
            label5.setBounds(430, 40, 85, 35);

            //---- label7 ----
            label7.setText("\u72b6\u6001");
            label7.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(label7);
            label7.setBounds(75, 105, 80, 35);

            //---- cBProfessor ----
            cBProfessor.setText("\u6559\u6388");
            cBProfessor.setBackground(Color.gray);
            cBProfessor.setBorder(new MatteBorder(1, 1, 1, 0, Color.black));
            cBProfessor.setBorderPainted(true);
            cBProfessor.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(cBProfessor);
            cBProfessor.setBounds(155, 165, 65, 35);

            //---- cbAssociateProfessor ----
            cbAssociateProfessor.setText("\u526f\u6559\u6388");
            cbAssociateProfessor.setBackground(Color.gray);
            cbAssociateProfessor.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
            cbAssociateProfessor.setBorderPainted(true);
            cbAssociateProfessor.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(cbAssociateProfessor);
            cbAssociateProfessor.setBounds(220, 165, 70, 35);

            //---- cbNormal ----
            cbNormal.setText("\u6b63\u5e38");
            cbNormal.setBackground(Color.gray);
            cbNormal.setBorder(new MatteBorder(1, 1, 1, 0, Color.black));
            cbNormal.setBorderPainted(true);
            cbNormal.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(cbNormal);
            cbNormal.setBounds(155, 105, 65, 35);

            //---- cbSuspended ----
            cbSuspended.setText("\u79bb\u804c");
            cbSuspended.setBackground(Color.gray);
            cbSuspended.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
            cbSuspended.setBorderPainted(true);
            cbSuspended.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(cbSuspended);
            cbSuspended.setBounds(290, 105, 65, 35);

            //---- cBHoliday ----
            cBHoliday.setText("\u5047\u671f\u4e2d");
            cBHoliday.setBackground(Color.gray);
            cBHoliday.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
            cBHoliday.setBorderPainted(true);
            cBHoliday.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(cBHoliday);
            cBHoliday.setBounds(220, 105, 70, 35);

            //---- cbLecturer ----
            cbLecturer.setText("\u8bb2\u5e08");
            cbLecturer.setBackground(Color.gray);
            cbLecturer.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
            cbLecturer.setBorderPainted(true);
            cbLecturer.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(cbLecturer);
            cbLecturer.setBounds(290, 165, 65, 35);

            //---- cbTeachingAssistant ----
            cbTeachingAssistant.setText("\u52a9\u6559");
            cbTeachingAssistant.setBackground(Color.gray);
            cbTeachingAssistant.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
            cbTeachingAssistant.setBorderPainted(true);
            cbTeachingAssistant.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(cbTeachingAssistant);
            cbTeachingAssistant.setBounds(355, 165, 65, 35);

            //---- textTeaName ----
            textTeaName.setBorder(LineBorder.createBlackLineBorder());
            textTeaName.setForeground(Color.white);
            textTeaName.setBackground(Color.gray);
            textTeaName.setFont(new Font("Courier New", Font.PLAIN, 18));
            textTeaName.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(textTeaName);
            textTeaName.setBounds(155, 40, 225, 35);

            //---- textTeaNum ----
            textTeaNum.setBorder(LineBorder.createBlackLineBorder());
            textTeaNum.setForeground(Color.white);
            textTeaNum.setBackground(Color.gray);
            textTeaNum.setFont(new Font("Courier New", Font.PLAIN, 18));
            textTeaNum.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(textTeaNum);
            textTeaNum.setBounds(530, 40, 240, 35);

            //---- okButton ----
            okButton.setText("OK");
            okButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    okButtonMousePressed(e);
                }
            });
            contentPanel.add(okButton);
            okButton.setBounds(320, 230, 80, 35);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            contentPanel.add(cancelButton);
            cancelButton.setBounds(475, 230, 80, 35);
            cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    cancelButtonMousePressed(e);
                }
            });
            //---- baseInfo ----
            baseInfo.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "\u57fa\u7840\u4fe1\u606f", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                    new Font("\u6977\u4f53", Font.BOLD, 16), Color.orange));
            baseInfo.setBackground(Color.gray);
            baseInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            baseInfo.setEditable(false);
            baseInfo.setAutoscrolls(false);
            contentPanel.add(baseInfo);
            baseInfo.setBounds(0, 0, 855, 307);

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
        contentPanel.setBounds(0, 0, 860, 315);

        contentPane.setPreferredSize(new Dimension(870, 345));
        setSize(870, 345);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel contentPanel;
    private JPanel teaType;
    private JLabel label6;
    JCheckBox cBTea;
    JCheckBox cBTeaInstructor;
    private JLabel label1;
    private JLabel label2;
    private JLabel label5;
    private JLabel label7;
    JCheckBox cBProfessor;
    JCheckBox cbAssociateProfessor;
    JCheckBox cbNormal;
    JCheckBox cbSuspended;
    JCheckBox cBHoliday;
    JCheckBox cbLecturer;
    JCheckBox cbTeachingAssistant;
    JTextField textTeaName;
    JTextField textTeaNum;
    JButton okButton;
    private JButton cancelButton;
    private JEditorPane baseInfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
