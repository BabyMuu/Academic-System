package frame.JustQuery;

import dao.imp.ClassInfoDAOImp;
import entity.ClassInfo;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

/**
 * @Author : BabyMuu
 * @File : FindLs
 * @Time : 2021/12/25 17:53
 */
public class lesQuery extends SelectFrame {

    @Override
    protected void setPage() {
        super.pageCount = 1;
        super.pageSize = 1000;
    }
    public lesQuery(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }
    @Override
    protected void initPersonalization() {
        super.jInternalFrame.getContentPane().removeAll();
        super.jInternalFrame.getContentPane().add(this.scrollPane1);
        super.scrollPane1.setBounds(50, 85, 920, 573);
        super.jInternalFrame.setTitle("�༶��ѯ");
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        // ���ñ������
        for (String colName : new String[]{"�༶���","�༶����","�꼶","�༶����"}) {
            model.addColumn(colName);
        }
        // ���ݲ�ѯ���� ��ѯ����
        List<ClassInfo> teas = new ClassInfoDAOImp().findByMap(attrValue, nowPage, pageSize);
        if (teas == null) {     // ���������Ϊ0 ���ʾû�в�ѯ������
            JOptionPane.showMessageDialog(null, "û���ҵ�����Ҫ�ҵĽ�ʦ\n�����²鿴����ɸѡ����");
            return false;
        }
        // ��������е����
        for (ClassInfo t : teas) {
            model.addRow(new Object[]{ t.getClsId(),t.getClsName(),t.getGrade(),t.getStuCount()});
        }
        this.tableList.setModel(model); // ������б��������
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
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