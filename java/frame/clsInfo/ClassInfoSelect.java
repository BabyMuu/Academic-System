package frame.clsInfo;

import dao.imp.ClassInfoDAOImp;
import entity.ClassInfo;
import frame.Login.LoginFrame;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class ClassInfoSelect extends SelectFrame {
    public ClassInfoSelect() {
        super();
    }

    public ClassInfoSelect(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }

    @Override
    protected void initPersonalization() {
        super.label1.setText("班级名称");
        super.label2.setText("班级编号");
        super.jInternalFrame.setTitle("班级查询");
    }

    @Override
    protected void setPage() {
        // 清除所有页数内容
        super.comboNowPage.removeAllItems();
        // 获取数据页数
        if (ISALL) {
            super.pageCount = new ClassInfoDAOImp().getPageCount(pageSize, null);
            ISALL = false;
        } else {
            super.pageCount = new ClassInfoDAOImp().getPageCount(pageSize, attrValue);
        }
        System.out.println(super.pageCount);
        // 将页数添加到组件里
        for (int i = 1; i <= super.pageCount; i++) {
            super.comboNowPage.addItem("第" + i + "页");
        }

    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        String[] colNames = new String[]{"班级编号", "班级名称", "年级", "班级人数", "教师编号"};
        int index = colNames.length;
        if (LoginFrame.ROLE.equals("Teacher")) index--;
        for (int i = 0; i < index; i++) {
            model.addColumn(colNames[i]);
        }
        List<ClassInfo> classInfos = new ClassInfoDAOImp().findByMap(attrValue, nowPage, pageSize);
        if (classInfos == null) {
            JOptionPane.showMessageDialog(null, "查询失败,请检查查询条件");
            return false;
        }
        for (ClassInfo ci : classInfos) {
            model.addRow(new Object[]{ci.getClsId(), ci.getClsName(), ci.getGrade(), ci.getStuCount(), ci.getTeaNum()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String name = super.text1.getText().trim();
        String num = super.text2.getText().trim();
        if (num.equals("") && name.equals("")) {
            ISALL = true;
        } else if (!num.equals("") && !name.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (num.equals("")) {
            attrValue.put("clsName", name);
        } else {
            attrValue.put("clsId", num);
        }
        setData();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String clsId = get_key_by_row_col("班级编号");
        if (clsId.equals("")) {
            return;
        }
        new ClassInfoUpdate(clsId).classInfoFrame.setVisible(true);
        setData();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {

    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String clsId = get_key_by_row_col("班级编号");
        if (clsId.equals("")) {
            return;
        }
        //int deleteConfirm = JOptionPane.showConfirmDialog(null,"您确定要删除班级名字：" + clsName +  "班级编号：" + clsId + "吗?");
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "您确定要删除班级编号：" + clsId + "吗?");
        int clsId1 = Integer.parseInt(clsId);
        if (deleteConfirm == 0) {
            ClassInfoDAOImp classInfoDAOImp = new ClassInfoDAOImp();
            boolean test = classInfoDAOImp.delete(clsId1);
            System.out.println(test);
            setData();
        }

    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        ClassInfoFrame classInfoFrame = new ClassInfoFrame();
        classInfoFrame.classInfoFrame.setModal(true);
        classInfoFrame.classInfoFrame.setVisible(true);
        setData();
    }
}
