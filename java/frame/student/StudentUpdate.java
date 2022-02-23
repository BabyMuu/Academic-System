package frame.student;

import dao.imp.ClassInfoDAOImp;
import dao.imp.StudentDaoImp;
import entity.ClassInfo;
import entity.Student;
import frame.Login.LoginFrame;
import frame.student.StudentFrame;
public class StudentUpdate extends StudentFrame {
    String stuNum;
    public StudentUpdate(String stuNum) {
        this.stuNum = stuNum;
        init();
    }
    private void init(){
        if (LoginFrame.ROLE.equals("Student")){
            super.confirm.setVisible(false);
        }
        super.setMsg("更改");
        StudentDaoImp sdi = new StudentDaoImp();
        Student stu = sdi.findById(stuNum, 1, 1).get(0);
        super.textStuNum.setText(stuNum);
        super.textStuName.setText(stu.getStuName());
        String clsName = null;
        for (ClassInfo cls : new ClassInfoDAOImp().findAll(0, 0, false)) {
            if (stu.getClsId() == cls.getClsId()) {
                clsName = cls.getClsName();
                break;
            }
        }
        super.selectCls.setSelectedItem(clsName);

        if (stu.isMale()) {
            super.radioMale.setSelected(true);
        } else {
            super.radioFemale.setSelected(true);
        }

        super.DatastuBtd.fd.setValue((stu.getStuBtd()));

        super.selectState.setSelectedIndex(stu.getSta() - 1);

        super.textRoleId.setText(stu.getRoleId() + "");

        super.pwd.setText(stu.getPassword());

        super.textStuNum.setFocusable(false);
    }
    @Override
    protected boolean saveData(Student stu, StudentDaoImp sdi) {
        // 信息验证成功 保存数据
        return sdi.update(stu);
    }
}
