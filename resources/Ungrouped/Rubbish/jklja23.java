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
	// �������
	private JScrollPane scpDemo;
	@SuppressWarnings("unused")
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;

	// ���췽��
	public jklja23() {
		// �����������ԵĶ���
		super("JTable���ݰ�ʾ��");
		this.setSize(330, 400);
		this.setLayout(null);
		this.setLocation(100, 50);
		// �������
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(10, 50, 300, 270);
		this.btnShow = new JButton("��ʾ����");
		this.btnShow.setBounds(10, 10, 300, 30);
		// ����ťע�����
		this.btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				btnShow_ActionPerformed(ae);
			}
		});
		// ��������뵽������
		add(this.scpDemo);
		add(this.btnShow);
		// ��ʾ����
		this.setVisible(true);
	}

	// �����ťʱ���¼�����
	public void btnShow_ActionPerformed(ActionEvent ae) {
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Own";
		// ��������������Դ����ʾ���ݵľ��崦��������ע����
		try {
			// �������
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url, "sa", "sa");
			// ������ѯ����
			String sql = "select id, pass, isAdmin from logindata";
			Statement pstm = conn.createStatement();
			// ִ�в�ѯ
			ResultSet rs = pstm.executeQuery(sql);
			// �����ж�������¼
			int count = 0;
			while (rs.next()) {
				count++;
			}
			rs = pstm.executeQuery(sql);
			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			Object[][] info = new Object[count][4];
			count = 0;
			while (rs.next()) {
				info[count][0] = rs.getString("id");
				info[count][1] = rs.getString("pass");
				info[count][2] = rs.getBoolean("isAdmin");
				count++;
			}
			// �����ͷ
			String[] title = { "�ʺ�", "����", "�Ƿ�Ϊ����Ա"};
			// ����JTable
			this.tabDemo = new JTable(info, title);
			// ��ʾ��ͷ
			this.jth = this.tabDemo.getTableHeader();
			// ��JTable���뵽���������������
			this.scpDemo.getViewport().add(tabDemo);
		}  catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new jklja23();
	}
}
