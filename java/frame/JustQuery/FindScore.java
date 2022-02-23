package frame.JustQuery;

import dao.imp.FindCaDaoImp;
import dao.imp.ScoreDAOImp;
import db.DBManager;
import entity.FindCA;
import entity.Score;
import frame.score.ScoMoreQuery;
import frame.score.ScoreFrame;
import frame.score.ScoreUpdate;
import frame.select.SelectFrame;

import javax.annotation.processing.SupportedOptions;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author : BabyMuu
 * @File : FinaScore
 * @Time : 2021/12/26 14:31
 */
public class FindScore extends SelectFrame {


    @Override
    protected void setPage() {
        super.pageCount = 1;
        super.pageSize = 1000;
    }
    public FindScore(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }
    @Override
    protected void initPersonalization() {
        super.label1.setText("�༶���");
        super.label2.setText("�γ�����");
        super.jInternalFrame.setTitle("���������ѯ");
        super.btnMore.setVisible(true);
        super.hidePageControl();
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        // ���ñ������
        for (String colName : new String[]{"ѧ��","����","�γ̺�","�γ�","����","�༶��"}) {
            model.addColumn(colName);
        }

        // ���ݲ�ѯ���� ��ѯ����
        List<FindCA> teas = new FindCaDaoImp().findByMap(attrValue);
        System.out.println(teas);
        if (teas == null) {     // ���������Ϊ0 ���ʾû�в�ѯ������
            JOptionPane.showMessageDialog(null, "û���ҵ�����Ҫ�ҵĽ�ʦ\n�����²鿴����ɸѡ����");
            return false;
        }
        // ��������е����
        for (FindCA t : teas) {
            model.addRow(new Object[]{t.getStuNum(),t.getStuName(),t.getLesId(),t.getLesName(),t.getScore(),t.getClsId()});
        }
        this.tableList.setModel(model); // ������б��������
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear(); // �����������
        // ��ȡ�û���д������
        String clsId = super.text1.getText().trim(); // ��ȡ��һ���ı��������
        String lesName = super.text2.getText().trim(); // ��ȡ�ڶ����ı��������
        // �ж��û���ѯ����
        if (clsId.equals("") && lesName.equals("")) {
            ISALL = true;
        } else if (!clsId.equals("") && !lesName.equals("")) {
            JOptionPane.showMessageDialog(null, "�����ٱ���һ��ѡ��Ϊ��");
        } else if (clsId.equals("")) {   // ���ݱ�Ų�ѯ
            attrValue.put("lesName", lesName);
        } else {    // ����������ѯ
            attrValue.put("clsid", clsId);
        }

        setData();
    }
    private String getScoreId(){
        // ��ȡѡ���� ��Ϣ
        int rowSelected = tableList.getSelectedRow(); // ��ȡѡ�е���
        if (rowSelected == -1) { // ��ʾδѡ���κ�һ��
            JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�������");
            return "";
        }
        // ��ȡѡ���еĽ�ʦ��ŵ�������
        int idNum;
        for (idNum = 0; ; idNum++) {
            if ("ѧ��".equals(tableList.getColumnName(idNum)))
                break;
        }
        String stuNum = tableList.getValueAt(rowSelected, idNum).toString();
        DBManager dbManager =new DBManager();
        String sql = "SELECT scoreId from score " +
                " INNER JOIN examschedule on (examschedule.esId = score.esId)" +
                " INNER JOIN coursearrangement on (examschedule.caId = coursearrangement.caId)" +
                " WHERE coursearrangement.teaNum= '"+staticAttr.get("TeaNum")+"' AND stuNum='"+stuNum+"'";
        System.out.println(sql);
        ResultSet rs = dbManager.query(sql);
        try {
            if (rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }
    @Override
    protected void changeMousePressed(MouseEvent e) {
        String scoreId = getScoreId();
        if (scoreId.equals("")) {
            return;
        }
        ScoreUpdate scoreUpdate = new ScoreUpdate(scoreId);
        scoreUpdate.setVisible(true);

        setData();
    }
    public static boolean ISCONFIRM = false;
    ScoMoreQuery scoMoreQuery;
    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
        scoMoreQuery = new ScoMoreQuery(attrValue, staticAttr, this);
        scoMoreQuery.setModal(true); // ����Ϊģʽ����
        scoMoreQuery.setVisible(true);    // ���ÿɼ�
        if (ISCONFIRM) {    // �����more�����ڵ���ȷ��
            setData();
            ISCONFIRM = false; // ��������ΪĬ��ֵ
        }
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String scoreId = getScoreId();
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
