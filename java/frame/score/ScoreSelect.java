package frame.score;

import dao.imp.ScoreDAOImp;
import entity.Score;
import frame.Login.LoginFrame;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class ScoreSelect extends SelectFrame {
    ScoMoreQuery scoMoreQuery;
    public static boolean ISCONFIRM;

    public ScoreSelect() {
        super();
    }

    public ScoreSelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    public void initPersonalization() {
        if (LoginFrame.ROLE.equals("Student")) {
            super.label1.setText("�γ�����");
        } else {
            super.label1.setText("ѧ��");
        }
        super.btnMore.setVisible(true);
        super.label2.setText("���Ա��");
        super.jInternalFrame.setTitle("������ѯ");
    }


    @Override
    protected void setPage() {
        // �������ҳ������
        super.comboNowPage.removeAllItems();
        // ��ȡ����ҳ��
        if (ISALL) {
            super.pageCount = new ScoreDAOImp().getPageCount(pageSize, null);
            System.out.println("ISALL");
            ISALL = false;
        } else {
            super.pageCount = new ScoreDAOImp().getPageCount(pageSize, attrValue);
        }
        System.out.println(pageCount);
        super.labPageCount.setText("��" + super.pageCount + "ҳ");
        // ��ҳ����ӵ������
        for (int i = 1; i <= super.pageCount; i++) super.comboNowPage.addItem("��" + i + "ҳ");
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };
        String[] colNames = new String[]{"�������", "���Ա��", "ѧ�����", "����"};
        if (LoginFrame.ROLE.equals("Student")) {
            colNames[2] = "�γ�����";
        }
        System.out.println(LoginFrame.ROLE);
        for (String colName : colNames) {
            model.addColumn(colName);
        }
        List<Score> scores = new ScoreDAOImp().findByMap(attrValue, nowPage, pageSize);

        if (scores == null) {
            JOptionPane.showMessageDialog(null, "��ѯʧ��,�����ѯ����");
            return false;
        }
        for (Score score : scores) {
            model.addRow(new Object[]{score.getScoreId(), score.getEsId(), score.getStuNum(),
                    score.getScore()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String attr = LoginFrame.ROLE.equals("Student") ? "lesName" : "stuNum";
        String stuNum = super.text1.getText().trim();
        String esId = super.text2.getText().trim();
        if (stuNum.equals("") && esId.equals("")) {
            ISALL = true;
        } else if (!stuNum.equals("") && !esId.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (stuNum.equals("")) {
            attrValue.put("esId", esId);
        } else {
            attrValue.put(attr, stuNum);
        }
        setData();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String scoreId = get_key_by_row_col("�������");
        if (scoreId.equals("")) {
            return;
        }
        ScoreUpdate scoreUpdate = new ScoreUpdate(scoreId);
        scoreUpdate.setVisible(true);

        setData();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
        scoMoreQuery = new ScoMoreQuery(attrValue, staticAttr);
        scoMoreQuery.setModal(true); // ����Ϊģʽ����
        scoMoreQuery.setVisible(true);    // ���ÿɼ�
        if (ISCONFIRM) {    // �����more�����ڵ���ȷ��
            setData();
            ISCONFIRM = false; // ��������ΪĬ��ֵ
        }
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String scoreId = get_key_by_row_col("�������");
        if (scoreId.equals("")) {
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��" + scoreId + "��?");
        if (deleteConfirm == 0) {
            ScoreDAOImp scoreDAOImp = new ScoreDAOImp();
            boolean test = scoreDAOImp.delete(scoreId);
            System.out.println(test);
            setData();

        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        ScoreFrame scoreFrame = new ScoreFrame();
        scoreFrame.ScoreFrame.setVisible(true);
        setData();
    }
}
