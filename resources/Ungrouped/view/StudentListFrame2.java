package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class StudentListFrame2 extends BaseFrame {
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	public StudentListFrame2() {
		setTitle("学生基础信息管理");
		setResizable(true); // 设置允许自由调整窗体大小
		setClosable(true); // 设置提供“关闭”按钮
		setIconifiable(true); // 设置提供“最小化”按钮
		setMaximizable(true); // 设置提供“最大化”按钮

		setBounds(-35, -618, 1100, 700);
		getContentPane().setLayout(null);

		String[] columnNames = new String[] { "id", "name", "hp", "damage" };
		// 表格中的内容，是一个二维数组
		String[][] heros = new String[][] { { "1", "盖伦", "616", "100" }, { "2", "提莫", "512", "102" },
				{ "3", "奎因", "832", "200" } };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 93, 979, 332);
		getContentPane().add(scrollPane);

		table = new JTable(heros, columnNames);
		
		scrollPane.setViewportView(table);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(550, 454, 60, 23);
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nowPage = comboBox.getSelectedItem().toString();
				System.out.println(nowPage);
			}
		});
		JButton btnNewButton = new JButton("第一页");
		btnNewButton.setBounds(612, 454, 100, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("上一页");
		btnNewButton_1.setBounds(712, 454, 100, 23);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("下一页");
		btnNewButton_2.setBounds(812, 454, 100, 23);
		getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("最后页");
		btnNewButton_3.setBounds(912, 454, 100, 23);
		getContentPane().add(btnNewButton_3);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(30, 10, 982, 69);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("学生姓名:");
		label.setBounds(24, 26, 100, 21);
		panel.add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(94, 26, 120, 21);
		panel.add(textField);

		JLabel label_1 = new JLabel("学生电话:");
		label_1.setBounds(244, 26, 100, 21);
		panel.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(314, 26, 120, 21);
		panel.add(textField_1);

		JButton button_3 = new JButton("查询");
		button_3.setBounds(862, 26, 100, 21);
		panel.add(button_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "\u529F\u80FD\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(33, 487, 979, 59);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button_2.setBounds(869, 20, 100, 23);
		panel_1.add(button_2);

		JButton button_1 = new JButton("修改");
		button_1.setBounds(769, 20, 100, 23);
		panel_1.add(button_1);

		JButton button = new JButton("新增");
		button.setBounds(669, 20, 100, 23);
		panel_1.add(button);

	}
	private void loadTableData() {
		
	}
}
