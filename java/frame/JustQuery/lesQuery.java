package frame.JustQuery;

import dao.imp.ClassInfoDAOImp;
import entity.ClassInfo;
import frame.select.SelectFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

/**
 * @Author : BabyMuu
 * @File : FindLs
 * @Time : 2021/12/25 17:53
 */
public class lesQuery extends SelectFrame {

    @Override
    protected void setPage() {
        super.pageCount = 1;
        super.pageSize = 1000;
    }
    public lesQuery(boolean isDialog, Map<String, String> staticAttr) {
        super(isDialog, staticAttr);
    }
    @Override
    protected void initPersonalization() {
        super.jInternalFrame.getContentPane().removeAll();
        super.jInternalFrame.getContentPane().add(this.scrollPane1);
        super.scrollPane1.setBounds(50, 85, 920, 573);
        super.jInternalFrame.setTitle("班级查询");
    }

    @Override
    protected boolean setModel() {
        model = new DefaultTableModel();
        // 设置表格列名
        for (String colName : new String[]{"班级编号","班级名称","年级","班级人数"}) {
            model.addColumn(colName);
        }
        // 根据查询条件 查询数据
        List<ClassInfo> teas = new ClassInfoDAOImp().findByMap(attrValue, nowPage, pageSize);
        if (teas == null) {     // 如果数据量为0 则表示没有查询到数据
            JOptionPane.showMessageDialog(null, "没有找到您想要找的教师\n请重新查看您的筛选条件");
            return false;
        }
        // 添加数据行到表格
        for (ClassInfo t : teas) {
            model.addRow(new Object[]{ t.getClsId(),t.getClsName(),t.getGrade(),t.getStuCount()});
        }
        this.tableList.setModel(model); // 给表格列表添加数据
        return true;
    }

    @Override
    protected void searchMousePressed(MouseEvent e) {
    }

    @Override
    protected void changeMousePressed(MouseEvent e) {
    }

    @Override
    protected void insertMousePressed(MouseEvent e) {
    }

    @Override
    protected void buttonMoreMousePressed(MouseEvent e) {
    }

    @Override
    protected void deleteMousePressed(MouseEvent e) {

    }
}