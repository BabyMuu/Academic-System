package frame.JustQuery;

import dao.imp.StudentDaoImp;
import dao.imp.TeacherDaoImp;
import entity.Student;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @Author : BabyMuu
 * @File : Findstu
 * @Time : 2021/12/24 23:25
 */
public class StuQuery extends SelectFrame {
    List<Integer> clsIds;

    @Override
    protected void setPage() {
        super.pageCount = 1;
        super.pageSize = 1000;
    }

    public StuQuery(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, null, false);
        clsIds = new TeacherDaoImp().getClsidByTeaNum(staticAttr.get("teaNum"), staticAttr.get("teaTypeId"));
        StringBuilder sb = new StringBuilder();
        for (Integer clsId : clsIds) {
            sb.append(clsId).append(" ");
        }
        staticAttr.remove("teaTypeId");
        staticAttr.remove("teaNum");
        attrValue.put("clsId", sb.toString());
        staticAttr.clear();
        setData();
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("ѧ��ѧ��");
        super.label2.setText("ѧ���༶");
        super.frame1.setTitle("ѧ������");
        super.hidePageControl();
        super.confirm.setVisible(false);
        super.change.setVisible(false);
        super.ret.setVisible(false);
        super.delete.setVisible(false);
    }

    @Override
    protected boolean setModel() {

        super.jInternalFrame.setTitle("ѧ����ѯ");
        model = new DefaultTableModel();
        for (String colName : new String[]{"���", "�༶", "����", "����", "�Ա�", "״̬"}) {
            model.addColumn(colName);
        }
        List<Student> stus;

        stus = new StudentDaoImp().findByMap(attrValue, 1, 1000);
        attrValue.clear();
        if (stus == null) {
            JOptionPane.showMessageDialog(null, "��ѯѧ��ʧ��,�����ѯ����");
            return false;
        }
        for (Student t : stus) {
            model.addRow(new Object[]{t.getStuNum(), t.getClsId(), t.getStuName(),
                    t.getStuBtd(), t.isMale() ? "��" : "Ů", t.getSta()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        // ��ȡ�û���д������
        String stuNum = super.text1.getText().trim(); // ��ȡ��һ���ı��������
        String clsid = super.text2.getText().trim(); // ��ȡ�ڶ����ı��������
        // �ж��û���ѯ����
        if (stuNum.equals("") && clsid.equals("")) {
            StringBuilder sb = new StringBuilder();
            for (Integer clsId : clsIds) {
                sb.append(clsId).append(" ");
            }
            attrValue.put("clsId", sb.toString());
        } else if (!stuNum.equals("") && !clsid.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (stuNum.equals("")) {   // ���ݰ༶����ѯ
            if (clsIds.contains(Integer.parseInt(clsid))) {
                attrValue.put("clsId", clsid);
            } else {
                JOptionPane.showMessageDialog(null, "�����clsid������, ����������");
                return;
            }
        } else {    // ����ѧ�Ų�ѯ
            attrValue.put("StuNum", stuNum);
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
