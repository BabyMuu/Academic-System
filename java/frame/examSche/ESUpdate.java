package frame.examSche;

import dao.imp.ExamScheduleDAOImp;
import entity.ExamSchedule;

public class ESUpdate extends ESJFrame {
    private String esId;

    public ESUpdate(String esId) {
        this.esId = esId;
        super.esFrame.setTitle("更改" + esId + "号数据");
        super.setMsg("更改");
        ExamScheduleDAOImp esdi = new ExamScheduleDAOImp();
        ExamSchedule es = esdi.findById(esId).get(0);

        String[] title = new String[]{"考试编号", "课程安排编号", "考试概述", "考试时间", "考试状态"};

        super.textCAId.setText(es.getCaId() + "");
        super.textContext.setText(es.getContext());
        super.textState.setText(es.getState() + "");
        super.Dataexam.fd.setValue(es.getExamDate());
    }

    @Override
    protected boolean saveData(ExamSchedule exam,ExamScheduleDAOImp edi) {
        // 信息验证成功 保存数据
        exam.setEsId(Integer.parseInt(esId));
        return edi.update(exam);
    }
}
