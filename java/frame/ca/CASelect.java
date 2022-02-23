package frame.ca;

import dao.imp.CourseArrangementDAOImp;
import entity.CourseArrangement;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class CASelect extends SelectFrame {
    public CASelect() {
        super();
    }

    public CASelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("�༶���");
        super.label2.setText("�γ�����");
        super.jInternalFrame.setTitle("�γ̰��Ų�ѯ");
    }

    @Override
    protected void setPage() {
        super.comboNowPage.removeAllItems();
        if (ISALL) {
            super.pageCount = new CourseArrangementDAOImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new CourseArrangementDAOImp().getPageCount(pageSize, attrValue);
        }
        System.out.println(super.pageCount);
        for (int i = 1; i <= super.pageCount; i++) {
            super.comboNowPage.addItem("��" + i + "ҳ");
        }
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        for (String colName : new String[]{"�γ̰��ű��", "�γ�����", "�༶���", "��ʦ���", "ѧ��", "ѧ��"}) {
            model.addColumn(colName);
        }
        List<CourseArrangement> courseArrangements = new CourseArrangementDAOImp().findByMap(attrValue, nowPage, pageSize);
        if (courseArrangements == null) {
            JOptionPane.showMessageDialog(null, "��ѯʧ��,�����ѯ����");
            return false;
        }
        for (CourseArrangement ci : courseArrangements) {
            model.addRow(new Object[]{ci.getCaId(), ci.getLesName(), ci.getClsId(), ci.getTeaNum(), ci.getYear(), ci.getSemester()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String clsId = super.text1.getText().trim();
        String lesName = super.text2.getText().trim();
        if (lesName.equals("") && clsId.equals("")) {
            ISALL = true;
        } else if (!lesName.equals("") && !clsId.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (lesName.equals("")) {
            attrValue.put("clsId", clsId);
        } else {
            attrValue.put("lesName", lesName);
        }
        setData();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        int rowSelected = tableList.getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�������");
            return;
        }
        if (!"�γ̰��ű��".equals(tableList.getColumnName(0))) {
            JOptionPane.showMessageDialog(null, "�뽫�������Ϊ��һ��");
            return;
        }
        String caId = tableList.getValueAt(rowSelected, 0).toString();
        CAUpdate caUpdate = new CAUpdate(caId);
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String lesId = get_key_by_row_col("�γ̱��");
        String clsId = get_key_by_row_col("�༶���");
        if (lesId.equals("")) {
            return;
        }
        int lesId1 = Integer.parseInt(lesId);
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ���༶�� " + clsId + "  " + " �γ̱�ţ� " + lesId + "��?");
        if (deleteConfirm == 0) {
            CourseArrangementDAOImp courseArrangementDAOImp = new CourseArrangementDAOImp();
            boolean test = courseArrangementDAOImp.delete(lesId1);
            System.out.println(test);
            setData();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        CAFrame caFrame = new CAFrame();
        caFrame.courseFrame.setVisible(true);
        setModel();
        setPage();
    }
}
