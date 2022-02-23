/*
 * Created by JFormDesigner on Thu Oct 28 12:57:46 CST 2021
 */

package frame.teacher;

import dao.imp.TeacherDaoImp;
import entity.Teacher;

/**
 * @author unknown
 */
public class TeacherUpdate extends TeacherFrame {

    public TeacherUpdate(String teaNum) {
        super.setMsg("����"); // ���õ�ǰ��������
        TeacherDaoImp tdi = new TeacherDaoImp();
        Teacher tea = tdi.findById(teaNum).get(0); // ���ݹ��Ų��ҽ�ʦ
        super.textTeaNum.setText(tea.getTeaNum()); // ����Ĭ�Ϲ���
        super.textTeaName.setText(tea.getTeaName()); // ����Ĭ������
        // ���ҽ�ʦְ�����
        String[] title = new String[]{"����", "������", "��ʦ", "����"};
        int titleIndex = 0;
        for (; titleIndex < title.length; titleIndex++) {
            if (tea.getTeaTitle().equals(title[titleIndex]))
                break;
        }
        // ������ʦְ��
        super.selectTeaTitle.setSelectedIndex(titleIndex);
        // ���ý�ʦ��ɫ���ͱ��
        if (tea.getTeaTypeId().equals("1")) {
            super.radioTea.setSelected(true);
        } else {
            super.radioInstructor.setSelected(true);
        }
        // ���ý�ʦ����
        super.DataTeaBtd.fd.setValue(tea.getTeaBtd());
        // ��������
        super.pwd.setText(tea.getPassword());
        // ���ý�ɫ���
        super.textRoleId.setText("" + tea.getRoleId());
        // ���ý�ʦ״̬
        super.selectState.setSelectedIndex(tea.getSta() - 1);
        // ���ý�ʦ��Ų��ɸ���
        super.textTeaNum.setFocusable(false);
    }

    @Override
    protected boolean saveData(Teacher tea, TeacherDaoImp tdi) {
        // ��Ϣ��֤�ɹ� ��������
        return tdi.update(tea);
    }
}
