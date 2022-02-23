package frame.ca;

import dao.imp.CourseArrangementDAOImp;
import entity.CourseArrangement;

public class CAUpdate extends CAFrame {
    private String caId;

    public CAUpdate(String caId) {
        this.caId = caId;
        super.courseFrame.setTitle("����" + caId + "������");
        super.setMsg("����");
        CourseArrangementDAOImp cadi = new CourseArrangementDAOImp();
        CourseArrangement ca = cadi.findById(caId, 0, 1).get(0);

        super.textTeaNum.setText(ca.getTeaNum());
        super.textClsId.setText(ca.getClsId() + "");
        super.textLesNum.setText(ca.getLesId() + "");
        super.textLesYear.setText(ca.getYear() + "");
        if (ca.getSemester() == 1) {
            radioFirst.setSelected(true);
        } else {
            radioSecond.setSelected(true);
        }
    }

    @Override
    protected boolean saveData(CourseArrangement ca, CourseArrangementDAOImp cadi) {
        // ��Ϣ��֤�ɹ� ��������
        ca.setCaId(Integer.parseInt(caId));
        return cadi.update(ca);
    }

}
