package Rubbish;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class jklja23 extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 定义组件
	private JScrollPane scpDemo;
	@SuppressWarnings("unused")
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;

	// 构造方法
	public jklja23() {
		// 窗体的相关属性的定义
		super("JTable数据绑定示例");
		this.setSize(330, 400);
		this.setLayout(null);
		this.setLocation(100, 50);
		// 创建组件
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(10, 50, 300, 270);
		this.btnShow = new JButton("显示数据");
		this.btnShow.setBounds(10, 10, 300, 30);
		// 给按钮注册监听
		this.btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				btnShow_ActionPerformed(ae);
			}
		});
		// 将组件加入到窗体中
		add(this.scpDemo);
		add(this.btnShow);
		// 显示窗体
		this.setVisible(true);
	}

	// 点击按钮时的事件处理
	public void btnShow_ActionPerformed(ActionEvent ae) {
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Own";
		// 以下是连接数据源和显示数据的具体处理方法，请注意下
		try {
			// 获得连接
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url, "sa", "sa");
			// 建立查询条件
			String sql = "select id, pass, isAdmin from logindata";
			Statement pstm = conn.createStatement();
			// 执行查询
			ResultSet rs = pstm.executeQuery(sql);
			// 计算有多少条记录
			int count = 0;
			while (rs.next()) {
				count++;
			}
			rs = pstm.executeQuery(sql);
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info = new Object[count][4];
			count = 0;
			while (rs.next()) {
				info[count][0] = rs.getString("id");
				info[count][1] = rs.getString("pass");
				info[count][2] = rs.getBoolean("isAdmin");
				count++;
			}
			// 定义表头
			String[] title = { "帐号", "密码", "是否为管理员"};
			// 创建JTable
			this.tabDemo = new JTable(info, title);
			// 显示表头
			this.jth = this.tabDemo.getTableHeader();
			// 将JTable加入到带滚动条的面板中
			this.scpDemo.getViewport().add(tabDemo);
		}  catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new jklja23();
	}
}
