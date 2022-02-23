package frame.examSche;

import dao.imp.ExamScheduleDAOImp;
import entity.ExamSchedule;

public class ESUpdate extends ESJFrame {
    private String esId;

    public ESUpdate(String esId) {
        this.esId = esId;
        super.esFrame.setTitle("����" + esId + "������");
        super.setMsg("����");
        ExamScheduleDAOImp esdi = new ExamScheduleDAOImp();
        ExamSchedule es = esdi.findById(esId).get(0);

        String[] title = new String[]{"���Ա��", "�γ̰��ű��", "���Ը���", "����ʱ��", "����״̬"};

        super.textCAId.setText(es.getCaId() + "");
        super.textContext.setText(es.getContext());
        super.textState.setText(es.getState() + "");
        super.Dataexam.fd.setValue(es.getExamDate());
    }

    @Override
    protected boolean saveData(ExamSchedule exam,ExamScheduleDAOImp edi) {
        // ��Ϣ��֤�ɹ� ��������
        exam.setEsId(Integer.parseInt(esId));
        return edi.update(exam);
    }
}
