package lib.tools;

import com.sun.javafx.tk.TKPulseListener;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Comparator;
import java.util.Enumeration;

public class OwnTable {
    public static void rowSortByInt(JTable table, int colIndex){
        TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>)table.getRowSorter();
        rowSorter.setComparator(colIndex, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2){
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        });
    }

    public static void setTableStyle(JTable jtb) {

        //设置选中行的背景色
        jtb.setSelectionBackground(new Color(224, 242, 255));

        //设置表格每行的高度
        jtb.setRowHeight(21);
        // 设置表格不可移动
        jtb.getTableHeader().setReorderingAllowed(false);
        jtb.getTableHeader().setFont(new Font("kaiTi", Font.BOLD, 18));
        // 设置点击表头自动实现排序
        jtb.setAutoCreateRowSorter(true);
        // 设置表头文字居中显示
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jtb.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(renderer.CENTER);

        // 设置表格中的数据居中显示
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jtb.setDefaultRenderer(Object.class, r);
        // 设置不可选中
        jtb.setFocusable(false);
        // 设置字体
        jtb.setFont(new Font("kaiTi", Font.PLAIN, 18));
        fitTableColumns(jtb);
    }

    // 根据内容自动调节表格的列宽度
    @SuppressWarnings("rawtypes")
    private static void fitTableColumns(JTable myTable) {
        // 设置自动大小
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();
        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) header.getDefaultRenderer().getTableCellRendererComponent
                    (myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent
                        (myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
    }
}
