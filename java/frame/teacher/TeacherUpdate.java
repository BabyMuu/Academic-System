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
        super.setMsg("更改"); // 设置当前窗口类型
        TeacherDaoImp tdi = new TeacherDaoImp();
        Teacher tea = tdi.findById(teaNum).get(0); // 根据工号查找教师
        super.textTeaNum.setText(tea.getTeaNum()); // 设置默认工号
        super.textTeaName.setText(tea.getTeaName()); // 设置默认名字
        // 查找教师职称序号
        String[] title = new String[]{"教授", "副教授", "讲师", "助教"};
        int titleIndex = 0;
        for (; titleIndex < title.length; titleIndex++) {
            if (tea.getTeaTitle().equals(title[titleIndex]))
                break;
        }
        // 设置老师职称
        super.selectTeaTitle.setSelectedIndex(titleIndex);
        // 设置教师角色类型编号
        if (tea.getTeaTypeId().equals("1")) {
            super.radioTea.setSelected(true);
        } else {
            super.radioInstructor.setSelected(true);
        }
        // 设置教师生日
        super.DataTeaBtd.fd.setValue(tea.getTeaBtd());
        // 设置密码
        super.pwd.setText(tea.getPassword());
        // 设置角色编号
        super.textRoleId.setText("" + tea.getRoleId());
        // 设置教师状态
        super.selectState.setSelectedIndex(tea.getSta() - 1);
        // 设置教师编号不可更改
        super.textTeaNum.setFocusable(false);
    }

    @Override
    protected boolean saveData(Teacher tea, TeacherDaoImp tdi) {
        // 信息验证成功 更改数据
        return tdi.update(tea);
    }
}
