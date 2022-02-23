package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class BaseListFrame extends JInternalFrame {
	private JLabel lblMsg = new JLabel("欢迎使用"); 
	protected JPanel queryPanel = null;
	protected JTextField txtId = null;
	protected JTable table = null;
	protected JComboBox comboBoxPage = null;
	protected JButton btnSerach = null;
	protected JButton btnFirst = null;
	protected JButton btnPre = null;
	protected JButton btnNext = null;
	protected JButton btnLast = null;
	protected JPanel updatePanel = null;
	protected JButton btnInsert = null;
	protected JButton btnUpdate = null;
	protected JButton btnDelete = null;
	private JTextField textField;
	public BaseListFrame() {
		setSize(1060, 520);
		setResizable(true); // 设置允许自由调整窗体大小
		setClosable(true); // 设置提供“关闭”按钮
		setIconifiable(true); // 设置提供“最小化”按钮
		setMaximizable(true); // 设置提供“最大化”按钮
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.add(new JLabel("提示:"));
		panel.add(lblMsg);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		
		queryPanel = new JPanel();
		centerPanel.add(queryPanel, BorderLayout.CENTER);
		queryPanel.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		queryPanel.setBounds(30, 10, 1000, 55);
		queryPanel.setLayout(null);
		
		JLabel label = new JLabel("编号:");
		label.setBounds(24, 21, 100, 23);
		queryPanel.add(label);

		txtId = new JTextField();
		txtId.setBounds(94, 21, 120, 23);
		queryPanel.add(txtId);
		
		btnSerach = new JButton("查询", new ImageIcon("imgs/base/find.png"));
		btnSerach.setBounds(890, 21, 100, 23);
		queryPanel.add(btnSerach);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 80, 1000, 250);
		centerPanel.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBoxPage = new JComboBox();
		comboBoxPage.setBounds(570, 350, 60, 23);
		centerPanel.add(comboBoxPage);
		
		btnFirst = new JButton("第一页", new ImageIcon("imgs/base/first.png"));
		btnFirst.setBounds(630, 350, 100, 23);
		centerPanel.add(btnFirst);

		btnPre = new JButton("上一页", new ImageIcon("imgs/base/pre.png"));
		btnPre.setBounds(730, 350, 100, 23);
		centerPanel.add(btnPre);

		JButton btnNext = new JButton("下一页", new ImageIcon("imgs/base/next.png"));
		btnNext.setBounds(830, 350, 100, 23);
		centerPanel.add(btnNext);

		btnLast = new JButton("最后页" , new ImageIcon("imgs/base/last.png"));
		btnLast.setBounds(930, 350, 100, 23);
		centerPanel.add(btnLast);
		
		updatePanel = new JPanel();
		updatePanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u9AD8\u7EA7\u6743\u9650", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		updatePanel.setBounds(30, 380, 1000, 59);
		centerPanel.add(updatePanel);
		updatePanel.setLayout(null);

		btnDelete = new JButton("删除", new ImageIcon("imgs/base/delete.png"));
		btnDelete.setBounds(880, 20, 100, 23);
		updatePanel.add(btnDelete);

		btnUpdate = new JButton("修改", new ImageIcon("imgs/base/edit.png"));
		btnUpdate.setBounds(780, 20, 100, 23);
		updatePanel.add(btnUpdate);

		btnInsert = new JButton("新增", new ImageIcon("imgs/base/add.png"));
		btnInsert.setBounds(680, 20, 100, 23);
		updatePanel.add(btnInsert);
	}
	protected void loadData() {
		
	}
	protected void setErrorMsg(String msg) {
		lblMsg.setForeground(Color.RED);
		lblMsg.setText(msg);
	}
	protected void setInfoMsg(String msg) {
		lblMsg.setForeground(Color.BLACK);
		lblMsg.setText(msg);
	}
	protected void setOkMsg(String msg) {
		lblMsg.setForeground(Color.GREEN);
		lblMsg.setText(msg);
	}
}
