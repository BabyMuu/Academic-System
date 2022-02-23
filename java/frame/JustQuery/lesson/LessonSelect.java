package frame.JustQuery.lesson;

import dao.imp.LessonDaoImp;
import entity.Lesson;
import frame.select.SelectFrame;
import lib.tools.MapTools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;

public class LessonSelect extends SelectFrame {

    @Override
    protected void initPersonalization() {
        super.label1.setText("课程名称");
        super.label2.setText("课程编号");
        super.jInternalFrame.setTitle("课程查询");
    }


    @Override
    protected void setPage() {
        super.comboNowPage.removeAllItems();
        if(ISALL){
            super.pageCount = new LessonDaoImp().getPageCount(pageSize, null);
            ISALL = false;
        }else {
            super.pageCount = new LessonDaoImp().getPageCount(pageSize, attrValue);
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
        for (String colName : new String[]{"课程编号", "课程名称", "概述", "学时", "学分"}) {
            model.addColumn(colName);
        }
        List<Lesson> lessons = null;
        lessons = new LessonDaoImp().findByMap(attrValue, nowPage, pageSize);

        if (lessons == null) {
            JOptionPane.showMessageDialog(null, "没有找到您想要找的课程\n请重新查看您的筛选条件");
            return false;
        }
        for (Lesson t : lessons) {
            model.addRow(new Object[]{t.getLesId(), t.getLesName(), t.getContext(),
                    t.getHours(), t.getScore()});
        }
        super.tableList.setModel(model);
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
        attrValue.clear();
        String name = super.text1.getText().trim();
        String num = super.text2.getText().trim();
        if(name.equals("") && num.equals("")){
            ISALL = true;
        } else if (!name.equals("") && !num.equals("")) {
            JOptionPane.showMessageDialog(null, "请至少保持一个选项为空");
        } else if (name.equals("")) {   // 根据编号查询
            attrValue.put("lesid", num);
        } else {    // 根据姓名查询
            attrValue.put("lesName", name);
        }
        // 加载数据
        MapTools.mapExtend(attrValue, staticAttr);
        setPage();
        setModel();
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
        String lessonNum = get_key_by_row_col("课程编号");
        if(lessonNum.equals("")){
            return;
        }
        new LessonUpdate(lessonNum).lessonFrame.setVisible(true);
        setPage();
        setModel();
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
//        aq = new TeaMoreQuery(attrValue, staticAttr);
//        aq.setModal(true); // 设置为模式窗体
//        aq.setVisible(true);    // 设置可见
//        if (ISCONFIRM) {    // 如果在more窗体内点了确定
//            setPage();  // 设置页数
//            setModel(); // 获取数据
//            ISCONFIRM = false; // 重新设置为默认值}
    }


    @Override
    protected void deleteMousePressed(MouseEvent e) {
        String lessonNum = get_key_by_row_col("课程编号");
        String lessonName = get_key_by_row_col("课程名称");
        if(lessonNum.equals("")){
            return;
        }
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "您确定要删除" + lessonName + "吗?");
        if(deleteConfirm == 0){
            LessonDaoImp lessonDaoImp = new LessonDaoImp();
            boolean test = lessonDaoImp.delete(lessonNum);
            System.out.println(test);
            setPage();
            setModel();
        }
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
        LessonJFrame lessonJFrame = new LessonJFrame();
        lessonJFrame.setVisible(true);
        setModel();
        setPage();

    }
}
