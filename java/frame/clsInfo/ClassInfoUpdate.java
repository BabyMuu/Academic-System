package frame.clsInfo;

import dao.imp.ClassInfoDAOImp;
import entity.ClassInfo;

public class ClassInfoUpdate extends ClassInfoFrame {
    private String clsId;

    public ClassInfoUpdate(String clsId) {
        this.clsId = clsId;
        super.classInfoFrame.setTitle("更改" + clsId + "号数据");
        super.setMsg("更改");
        ClassInfoDAOImp clsdi  = new ClassInfoDAOImp();
        ClassInfo cls = clsdi.findById(clsId, 0,0, false).get(0);

        super.textClsName.setText(cls.getClsName());
        super.textTeaNum.setText(cls.getTeaNum());
        super.textStuCount.setText(cls.getStuCount() + "");
        super.ClassGrade.fd.setValue(cls.getGrade());
    }

    @Override
    protected boolean saveData(ClassInfo cls, ClassInfoDAOImp cii) {
        cls.setClsId(Integer.parseInt(clsId));
        // 信息验证成功 保存数据
        return cii.update(cls);
    }
}
