package frame.JustQuery.lesson;

import dao.imp.LessonDaoImp;
import entity.Lesson;

public class LessonUpdate extends LessonJFrame {
    private String lesId;
    public LessonUpdate(String lesId) {
        this.lesId = lesId;
        super.setMsg("¸ü¸Ä");
        LessonDaoImp ldi = new LessonDaoImp();
        Lesson lesson = ldi.findById(lesId).get(0);
        super.textLesName.setText(lesson.getLesName());
        super.textLesContext.setText(lesson.getContext());
        super.textHours.setText(lesson.getHours() + "");
        super.textScore.setText(lesson.getScore() + "");

    }
    @Override
    protected boolean saveData(Lesson les, LessonDaoImp ldi){
        les.setLesId(Integer.parseInt(lesId));
        return ldi.update(les);
    }
}
