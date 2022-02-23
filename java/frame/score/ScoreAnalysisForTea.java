package frame.score;

import dao.imp.ClassInfoDAOImp;
import dao.imp.CourseArrangementDAOImp;
import dao.imp.FindCaDaoImp;
import dao.imp.ScoreSeaDAOImp;
import entity.CourseArrangement;
import entity.ScoreResearch;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : BabyMuu
 * @File : ScoreAnalysisForTea
 * @Time : 2021/12/27 0:07
 */
public class ScoreAnalysisForTea extends SelectFrame {
    String clsId = "0";
    JLabel label3;
    JTextField text3;

    @Override
    protected void setPage() {
        super.pageCount = 1;
        super.pageSize = 1000;
    }

    public ScoreAnalysisForTea(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);

    }

    @Override
    protected void initPersonalization() {
        super.jInternalFrame.setTitle("�ɼ�������ѯ");
        super.hideBtn();
        super.label1.setVisible(false);
        super.label2.setVisible(false);
        super.text1.setVisible(false);
        super.text2.setVisible(false);
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        text1 = new JTextField();
        text2 = new JTextField();
        text3 = new JTextField();

        //---- label1 ----
        label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        super.jInternalFrame.getContentPane().add(label1);
        label1.setBounds(90, 55, 75, 35);

        //---- label2 ----
        label2.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        super.jInternalFrame.getContentPane().add(label2);
        label2.setBounds(405, 40, 110, 35);

        //---- label3 ----
        label3.setFont(new Font("\u6977\u4f53", Font.BOLD, 16));
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        super.jInternalFrame.getContentPane().add(label3);
        label3.setBounds(405, 80, 110, 35);

        //---- text1 ----
        text1.setBorder(LineBorder.createBlackLineBorder());
        text1.setForeground(new Color(102, 102, 102));
        super.jInternalFrame.getContentPane().add(text1);
        text1.setBounds(175, 55, 200, 35);

        //---- text2 ----
        text2.setBorder(LineBorder.createBlackLineBorder());
        text2.setForeground(new Color(102, 102, 102));
        super.jInternalFrame.getContentPane().add(text2);
        text2.setBounds(535, 40, 200, 35);

        //---- text3 ----
        text3.setBorder(LineBorder.createBlackLineBorder());
        text3.setForeground(new Color(102, 102, 102));
        super.jInternalFrame.getContentPane().add(text3);
        text3.setBounds(535, 80, 200, 35);
        //---- editorPane1 ----
        super.editorPane1.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.lightGray), "\u67e5\u8be2", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                new Font("\u96b6\u4e66", Font.BOLD, 16), Color.orange));
        super.editorPane1.setEditable(false);
        super.editorPane1.setBackground(Color.gray);

        super.jInternalFrame.getContentPane().add(editorPane1);

        editorPane1.setBounds(68, 20, 767, 105);

        label1.setText("�༶���");
        label2.setText("�꼶");
        label3.setText("�༶����");
        super.hidePageControl();
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        // ���ñ������
        for (String colName : new String[]{"�꼶", "�༶��", "�γ���", "���Ա��", "��Сֵ", "ƽ��ֵ", "���ֵ", "��������"}) {
            model.addColumn(colName);
        }

        // ���ݲ�ѯ���� ��ѯ����
        List<ScoreResearch> teas = null;
        if (clsId == null || clsId.equals("0") || clsId.equals("")) {
            teas = new FindCaDaoImp().getScores(staticAttr.get("teaNum"));
        } else {
            teas = new ScoreSeaDAOImp().findByClsId(clsId + "", nowPage, pageCount);
        }
        if (teas == null) {     // ���������Ϊ0 ���ʾû�в�ѯ������
            JOptionPane.showMessageDialog(null, "û���ҵ�����Ҫ�ҵĽ�ʦ\n�����²鿴����ɸѡ����");
            return false;
        }
//        List<CourseArrangement> courseArrangements = new CourseArrangementDAOImp().findByMap(staticAttr, 1, 1000);
//        for (ScoreResearch tea : teas) {
//            boolean flag1 = true;
//            for (CourseArrangement courseArrangement : courseArrangements) {
//            }
//        }
        // ��������е����
        for (ScoreResearch t : teas) {
            model.addRow(new Object[]{t.getGrade(), t.getClsName(), t.getLesName(), t.getEsId(), t.getMinScore(), t.getAvgScore(), t.getMaxScore(), t.getPassingNum()});
        }
        this.tableList.setModel(model); // ������б��������
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        clsId = "0";
        String clsIdInner = text1.getText().trim();
        String grade = text2.getText().trim();
        String clsName = text3.getText().trim();
        if (clsIdInner.equals("") && clsName.equals("") && grade.equals("")) {
            ISALL = true;
        } else if (!clsIdInner.equals("") && !clsName.equals("") && !grade.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (clsName.equals("") && grade.equals("")) { // ���ֻѡ�� clsID
            clsId = clsIdInner;
        } else if (grade.equals("") || clsName.equals("")) { // ����༶���꼶û��ͬʱд
            JOptionPane.showMessageDialog(null, "��ͬʱ��д�꼶�Ͱ༶����");
        } else { // ͬʱд�˰༶���꼶
            ClassInfoDAOImp classInfoDAOImp = new ClassInfoDAOImp();
            clsId = classInfoDAOImp.findByNameAndGrade(clsName, grade) + "";
        }
        setData();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {

    }

    @Override
    protected void insertMousePressed(MouseEvent e) {

    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {

    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {

    }
}
