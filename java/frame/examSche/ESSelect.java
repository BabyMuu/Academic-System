package frame.examSche;

import dao.imp.ExamScheduleDAOImp;
import entity.ExamSchedule;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class ESSelect extends SelectFrame {

    public ESSelect() {
        super();
    }

    public ESSelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("���Ա��");
        super.label2.setText("�γ̰��ű��");
        super.jInternalFrame.setTitle("���԰��Ų�ѯ");
    }

    @Override
    protected void setPage() {
        // �������ҳ������
        super.comboNowPage.removeAllItems();
        // ��ȡ����ҳ��
        if (ISALL) {
            super.pageCount = new ExamScheduleDAOImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new ExamScheduleDAOImp().getPageCount(pageSize, attrValue);
        }
        System.out.println(super.pageCount);
        // ��ҳ����ӵ������
        for (int i = 1; i <= super.pageCount; i++) {
            super.comboNowPage.addItem("��" + i + "ҳ");
        }
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        for (String colName : new String[]{"���Ա��", "�γ̰��ű��", "���Ը���", "����ʱ��", "����״̬"}) {
            model.addColumn(colName);
        }
        List<ExamSchedule> examSchedules = new ExamScheduleDAOImp().findByMap(attrValue, nowPage, pageSize);

        if (examSchedules == null) {
            JOptionPane.showMessageDialog(null, "��ѯʧ��,�����ѯ����");
            return false;
        }
        for (ExamSchedule es : examSchedules) {
            model.addRow(new Object[]{es.getEsId(), es.getCaId(), es.getContext(), es.getExamDate(), es.getState()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String caId = super.text1.getText().trim();
        String esId = super.text2.getText().trim();
        if (esId.equals("") && caId.equals("")) {
            ISALL = true;
        } else if (!esId.equals("") && !caId.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (esId.equals("")) {
            attrValue.put("caId", caId);
        } else {
            attrValue.put("esId", esId);
        }
        setPage();
        setModel();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String esId = get_key_by_row_col("���Ա��"); // ����ѡ�е����л�ȡ ��ʦ���
        if (esId.equals("")) { // ������ص���"" ���ʾû��ѡ���κ����򷵻�
            return;
        }
        // �����ñ�Ŵ�����´���
        new ESUpdate(esId).esFrame.setVisible(true); // �������岢���ÿɼ�
        // ��������
        setPage();
        setModel();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {

    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String esId = get_key_by_row_col("���Ա��"); // ����ѡ�е����л�ȡ ��ʦ���
        int esId1 = Integer.parseInt(esId);
        if (esId.equals("")) { // ������ص���"" ���ʾû��ѡ���κ����򷵻�
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ���γ̱�ţ�" + esId + "��?");
        if (deleteConfirm == 0) {
            ExamScheduleDAOImp examScheduleDAOImp = new ExamScheduleDAOImp();
            boolean test = examScheduleDAOImp.delete(esId1);
            System.out.println(test);
            setPage();
            setModel();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        ESJFrame esjFrame = new ESJFrame();
        esjFrame.esFrame.setVisible(true);
        setPage();
        setModel();
    }
}
