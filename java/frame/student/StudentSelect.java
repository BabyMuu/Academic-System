package frame.student;

import dao.imp.ClassInfoDAOImp;
import dao.imp.StudentDaoImp;
import entity.Student;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class StudentSelect extends SelectFrame {
    public StudentSelect() {
        super();
    }

    public StudentSelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("����");
        super.label2.setText("ѧ�����");
        super.jInternalFrame.setTitle("ѧ����ѯ");
    }

    protected void setPage(String attr, String value) {
    }

    @Override
    protected void setPage() {
        // �������ҳ������
        super.comboNowPage.removeAllItems();
        // ��ȡ����ҳ��
        if (ISALL) {
            super.pageCount = new StudentDaoImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new StudentDaoImp().getPageCount(pageSize, attrValue);
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
        for (String colName : new String[]{"���", "�༶", "����", "����", "�Ա�", "״̬"}) {
            model.addColumn(colName);
        }
        List<Student> stus = null;
        stus = new StudentDaoImp().findByMap(attrValue, nowPage, pageSize);
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
        attrValue.clear(); // �����������
        String name = super.text1.getText().trim();
        String num = super.text2.getText().trim();
        if (name.equals("") && num.equals("")) {
            ISALL = true;
        } else if (!name.equals("") && !num.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (name.equals("")) {   // ���ݱ�Ų�ѯ
            attrValue.put("stuNum", num);
        } else {    // ����������ѯ
            attrValue.put("stuName", name);
        }
        // ��������
        setData();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String stuNum = get_key_by_row_col("���"); // ����ѡ�е����л�ȡ ��ʦ���
        if (stuNum.equals("")) { // ������ص���"" ���ʾû��ѡ���κ����򷵻�
            return;
        }
        // �����ñ�Ŵ�����´���
        new StudentUpdate(stuNum).frame1.setVisible(true); // �������岢���ÿɼ�
        // ��������
        setData();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {

    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String stuNum = get_key_by_row_col("���"); // ����ѡ�е����л�ȡ ��ʦ���
        String stuName = get_key_by_row_col("����");
        if (stuNum.equals("")) { // ������ص���"" ���ʾû��ѡ���κ����򷵻�
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��" + stuName + "��?");
        if (deleteConfirm == 0) {
            StudentDaoImp studentDaoImp = new StudentDaoImp();
            boolean test = studentDaoImp.delete(stuNum);
            System.out.println(test);
            setData();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        String grade = getGrade();
        if (grade.equals("-1")) return;
        StudentFrame studentFrame = new StudentFrame(grade + "");// �����������岢���ÿɼ�
        studentFrame.frame1.setVisible(true);
        setData();
    }

    private String getGrade() {
        String grade;
        while (true) {
            grade = JOptionPane.showInputDialog("�������������ѧ�����꼶");
            if (grade == null){
                return "-1";
            }
            if (2030 < Integer.parseInt(grade) || Integer.parseInt(grade) < 2015) {
                int index = JOptionPane.showConfirmDialog(null, "�꼶��Χ2015~2030, �Ƿ���������", "����", JOptionPane.YES_NO_OPTION);
                if (index == 0) {
                    continue;
                } else {
                    return "-1";
                }
            }
            if (new ClassInfoDAOImp().findByGrade(grade, true).size() == 0) {
                int index = JOptionPane.showConfirmDialog(null, "���꼶��ʱû�а༶, �Ƿ���������", "����", JOptionPane.YES_NO_OPTION);
                if (index == 0) {
                    continue;
                } else {
                    return "-1";
                }
            }
            break;
        }
        return grade;
    }
}
