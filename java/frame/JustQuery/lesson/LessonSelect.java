package frame.JustQuery.lesson;

import dao.imp.LessonDaoImp;
import entity.Lesson;
import frame.select.SelectFrame;
import lib.tools.MapTools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;

public class LessonSelect extends SelectFrame {

    @Override
    protected void initPersonalization() {
        super.label1.setText("�γ�����");
        super.label2.setText("�γ̱��");
        super.jInternalFrame.setTitle("�γ̲�ѯ");
    }


    @Override
    protected void setPage() {
        super.comboNowPage.removeAllItems();
        if(ISALL){
            super.pageCount = new LessonDaoImp().getPageCount(pageSize, null);
            ISALL = false;
        }else {
            super.pageCount = new LessonDaoImp().getPageCount(pageSize, attrValue);
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
        for (String colName : new String[]{"�γ̱��", "�γ�����", "����", "ѧʱ", "ѧ��"}) {
            model.addColumn(colName);
        }
        List<Lesson> lessons = null;
        lessons = new LessonDaoImp().findByMap(attrValue, nowPage, pageSize);

        if (lessons == null) {
            JOptionPane.showMessageDialog(null, "û���ҵ�����Ҫ�ҵĿγ�\n�����²鿴����ɸѡ����");
            return false;
        }
        for (Lesson t : lessons) {
            model.addRow(new Object[]{t.getLesId(), t.getLesName(), t.getContext(),
                    t.getHours(), t.getScore()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String name = super.text1.getText().trim();
        String num = super.text2.getText().trim();
        if(name.equals("") && num.equals("")){
            ISALL = true;
        } else if (!name.equals("") && !num.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (name.equals("")) {   // ���ݱ�Ų�ѯ
            attrValue.put("lesid", num);
        } else {    // ����������ѯ
            attrValue.put("lesName", name);
        }
        // ��������
        MapTools.mapExtend(attrValue, staticAttr);
        setPage();
        setModel();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String lessonNum = get_key_by_row_col("�γ̱��");
        if(lessonNum.equals("")){
            return;
        }
        new LessonUpdate(lessonNum).lessonFrame.setVisible(true);
        setPage();
        setModel();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
//        aq = new TeaMoreQuery(attrValue, staticAttr);
//        aq.setModal(true); // ����Ϊģʽ����
//        aq.setVisible(true);    // ���ÿɼ�
//        if (ISCONFIRM) {    // �����more�����ڵ���ȷ��
//            setPage();  // ����ҳ��
//            setModel(); // ��ȡ����
//            ISCONFIRM = false; // ��������ΪĬ��ֵ}
    }


    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String lessonNum = get_key_by_row_col("�γ̱��");
        String lessonName = get_key_by_row_col("�γ�����");
        if(lessonNum.equals("")){
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��" + lessonName + "��?");
        if(deleteConfirm == 0){
            LessonDaoImp lessonDaoImp = new LessonDaoImp();
            boolean test = lessonDaoImp.delete(lessonNum);
            System.out.println(test);
            setPage();
            setModel();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        LessonJFrame lessonJFrame = new LessonJFrame();
        lessonJFrame.setVisible(true);
        setModel();
        setPage();

    }
}
