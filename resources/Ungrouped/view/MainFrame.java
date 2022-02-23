package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class MainFrame extends JFrame {
	JTree tree = null;
	JDesktopPane desktopPane = null;
	public MainFrame() {
		setTitle("学生成绩管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 618);
		setLocationRelativeTo(null);
		
		//上部消息提示面板
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);
		panel_2.add(new JLabel("欢迎您:"));
		JLabel lblUserName = new JLabel();
		lblUserName.setText("张三");
		panel_2.add(lblUserName);
		String dateStr = LocalDateTime.now().toString();
		panel_2.add(new JLabel("    今天是:" + dateStr));
		
		//左边菜单树
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel, BorderLayout.WEST);
		treeInit();

		//中部内容
		panel.add(tree);
		desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);
//		JPanel imgpanel = new JPanel();
//		imgpanel.setLayout(new BorderLayout());
//		imgpanel.add(new JLabel(new ImageIcon("imgs/jldn.jpg")));
//		panel_2.add(imgpanel);
//		desktopPane.add(imgpanel);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("基础数据");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u5217\u8868");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "学生列表");
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u65B0\u589E\u5B66\u751F");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "新增学生");
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("业务数据");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("学生信息列表");
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("新增学生");
		menu_1.add(menuItem_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("帮助");
		menuBar.add(mntmNewMenuItem);
	}
	
	private void treeInit() {	
		DefaultMutableTreeNode sys = new DefaultMutableTreeNode("教学质量分析系统");
		DefaultMutableTreeNode yiji = new DefaultMutableTreeNode("基础数据管理");
		sys.add(yiji);
		DefaultMutableTreeNode ej1 = new DefaultMutableTreeNode("学生列表");
		
		yiji.add(ej1);
		DefaultMutableTreeNode ej2 = new DefaultMutableTreeNode("教师列表");
		
		DefaultMutableTreeNode yiji2 = new DefaultMutableTreeNode("业务数据管理");
		sys.add(yiji2);
		DefaultMutableTreeNode ej21 = new DefaultMutableTreeNode("学生列表");
		yiji2.add(ej21);
		DefaultMutableTreeNode ej22 = new DefaultMutableTreeNode("教师列表");
		yiji2.add(ej22);
		
		yiji.add(ej2);
		tree = new JTree(sys);
		//tree.setRootVisible(false);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();
				//System.out.println(path);
				String name =path.getLastPathComponent().toString();
				
				switch (name) {
				case "学生列表":
					StudentListFrame frame1 = new StudentListFrame();
					frame1.setVisible(true);
					frame1.setBounds(0, 0, frame1.getWidth(), frame1.getHeight()); // 设置内部窗体加载位置及大小
					frame1.setVisible(true); // 设置内部窗体可见
					desktopPane.add(frame1); // 将内部窗体添加到JDesktopPane桌面面板
					try {
						frame1.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "教师列表":
					TeacherListFrame frame2 = new TeacherListFrame();
					frame2.setVisible(true);
					frame2.setVisible(true);
			
					desktopPane.add(frame2); // 将内部窗体添加到JDesktopPane桌面面板
					try {
						frame2.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;

				default:
					break;
				}
			}
		});
		
		
		
	}
}
