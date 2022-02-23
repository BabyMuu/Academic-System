package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TeacherListFrame extends BaseListFrame {
	public TeacherListFrame() {
		//1 
		setTitle("老师基本信息管理");
		
		//
		addEvent();
		
		//updatePanel.setVisible(false);
		loadData();
		//btnLast.setEnabled(false);
	}
	@Override
	protected void loadData() {
		DefaultTableModel tm = new DefaultTableModel();
		tm.addColumn("编号");
		tm.addColumn("姓名");
		tm.addColumn("类别");
		tm.addColumn("职称");
		for (int i = 1; i <= 20 ; i++) {
			tm.addRow(new String[] {"" + i, "姓名" + i, "导员", "无"});	
		}
		table.setModel(tm);
		
		comboBoxPage.addItem(1);
		comboBoxPage.addItem(2);
		comboBoxPage.addItem(3);
		comboBoxPage.setSelectedIndex(2);
		
	}
	private void addEvent() {
		btnSerach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText();
				setErrorMsg(id);
			}
		});
		comboBoxPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String i = comboBoxPage.getSelectedItem().toString();
				setInfoMsg(i);
			}
		});
	}

}
