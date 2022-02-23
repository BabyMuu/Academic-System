package frame.teacher;

import dao.imp.TeacherDaoImp;
import entity.Teacher;
import frame.select.InfoFrame;
import frame.select.SelectFrame;
import lib.tools.MapTools;

import javax.annotation.processing.SupportedOptions;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class TeacherSelect extends SelectFrame {
    public static boolean ISCONFIRM = false;    // �߼���ѯ���Ƿ��ǵ��� ȷ�϶����ص�
    private InfoFrame infoFrame;
    private TeaMoreQuery aq;

    public TeacherSelect() {

    }

    public TeacherSelect(boolean isDiolog) {    // ��ģʽ�����
        super(isDiolog);    // ���ø��෽��
    }

    public TeacherSelect(boolean isDialog, Map<String, String> staticAttr, InfoFrame infoFrame) {
        super(isDialog, staticAttr);
        this.infoFrame = infoFrame;
    }

    @Override
    protected void initPersonalization() {
        // ����label����
        super.label1.setText("����");
        super.label2.setText("��ʦ���");
        super.btnMore.setVisible(true);
        // ���ô�������
        if (isDialog) {
            super.jDialog.setTitle("��ʦ��ѯ");
        } else {
            super.jInternalFrame.setTitle("��ʦ��ѯ");
        }
        // ---- ��Ӱ�ť ----
        JButton confirm_choice = new JButton();
        confirm_choice.setText("Confirm"); // ���ð�ť����
        confirm_choice.setBorder(new EmptyBorder(5, 5, 5, 5)); // ���ð�ť�߿�
        // ��Ӱ�ť�¼�
        confirm_choice.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                confirm_choice_MousePressed(e);
            }
        });
        // ����ť��ӵ�������
        super.jDialog.getContentPane().add(confirm_choice);
        // ����λ�úʹ�С
        confirm_choice.setBounds(460, 640, 80, 60);
    }

    /**
     * ����map��������ҳ��
     */
    @Override
    protected void setPage() {
        // �������ҳ������
        super.comboNowPage.removeAllItems();
        // ��ȡ����ҳ��
        if (ISALL) {
            super.pageCount = new TeacherDaoImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new TeacherDaoImp().getPageCount(pageSize, attrValue);
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
        // ���ñ������
        for (String colName : new String[]{"���", "����", "ְ��", "����", "����"}) {
            model.addColumn(colName);
        }
        // ���ݲ�ѯ���� ��ѯ����
        List<Teacher> teas = new TeacherDaoImp().findByMap(attrValue, nowPage, pageSize);

        if (teas == null) {     // ���������Ϊ0 ���ʾû�в�ѯ������
            JOptionPane.showMessageDialog(null, "û���ҵ�����Ҫ�ҵĽ�ʦ\n�����²鿴����ɸѡ����");
            return false;
        }
        // ��������е����
        for (Teacher t : teas) {
            model.addRow(new Object[]{t.getTeaNum(), t.getTeaName(), t.getTeaTitle(),
                    t.getTeaTypeId(), t.getTeaBtd()});
        }
        super.tableList.setModel(model); // ������б��������
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear(); // �����������
        // ��ȡ�û���д������
        String name = super.text1.getText().trim(); // ��ȡ��һ���ı��������
        String num = super.text2.getText().trim(); // ��ȡ�ڶ����ı��������
        // �ж��û���ѯ����
        if (name.equals("") && num.equals("")) {
            ISALL = true;
        } else if (!name.equals("") && !num.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (name.equals("")) {   // ���ݱ�Ų�ѯ
            attrValue.put("teaNum", num);
        } else {    // ����������ѯ
            attrValue.put("teaName", name);
        }
        // ��������
        MapTools.mapExtend(attrValue, staticAttr);
        setPage();
        setModel();
    }

    /**
     * ���Ľ�ʦ��Ϣ
     */
    @Override
    protected void changeMousePressed(MouseEvent e) {
        String teaNum = get_key_by_row_col("���"); // ����ѡ�е����л�ȡ ��ʦ���
        if (teaNum.equals("")) { // ������ص���"" ���ʾû��ѡ���κ����򷵻�
            return;
        }
        // �����ñ�Ŵ�����´���
        new TeacherUpdate(teaNum).frame1.setVisible(true); // �������岢���ÿɼ�
        // ��������
        setPage();
        setModel();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
        aq = new TeaMoreQuery(attrValue, staticAttr);
        aq.setModal(true); // ����Ϊģʽ����
        aq.setVisible(true);    // ���ÿɼ�
        if (ISCONFIRM) {    // �����more�����ڵ���ȷ��
            setData();
            ISCONFIRM = false; // ��������ΪĬ��ֵ
        }
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String teaNum = get_key_by_row_col("���"); // ����ѡ�е����л�ȡ ��ʦ���
        String teaName = get_teaName_by_row_col();
        if (teaNum.equals("")) { // ������ص���"" ���ʾû��ѡ���κ����򷵻�
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��" + teaName + "��?");
        if (deleteConfirm == 0) {
            TeacherDaoImp teacherDaoImp = new TeacherDaoImp();
            boolean test = teacherDaoImp.delete(teaNum);
            System.out.println(test);
            setPage();
            setModel();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        TeacherFrame teacherFrame = new TeacherFrame();// �����������岢���ÿɼ�
        teacherFrame.frame1.setVisible(true);
        setPage();
        setModel();
    }

    /**
     * ������Ϊģʽ�����ʱ����ȷ�ϰ�ť
     * ���� ISCONFIRMCHOICE Ϊ true ��ʾ���˳����ڵ�ԭ���ǵ����ȷ�ϰ�ť
     * �� ��ȡ����teanum ͨ����̬�������ص��ϲ㴰��
     * �رյ�ǰ����
     * ����: ����ѡ��Ľ�ʦ���, ��ֹ�û�����ʦ���
     */
    private void confirm_choice_MousePressed(MouseEvent e) {
        String num = get_key_by_row_col("���"); // ����ѡ�е����л�ȡ ��ʦ���
        if (num.equals("")) { // ������ص���"" ���ʾû��ѡ���κ����򷵻�
            return;
        }
        infoFrame.isConfirmChoice = true;
        infoFrame.num = num;
        super.jDialog.dispose();
    }

}
