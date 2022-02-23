package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class LoginFrame extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;

	public LoginFrame() {
		setTitle("\u7CFB\u7EDF\u767B\u9646");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(528, 320);
		JLabel imgLbl = new JLabel(new ImageIcon("imgs/fendou3.jpg"));
		getContentPane().add(imgLbl, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel imgWeixin = new JLabel(new ImageIcon("imgs/weixin.jpg"));
		imgWeixin.setBounds(0, 1, 73, 100);
		panel.add(imgWeixin);
		
		JLabel lbl1 = new JLabel("类型:", SwingConstants.RIGHT);
		lbl1.setBounds(130, 10, 100, 23);
		panel.add(lbl1);
		JLabel lbl2 = new JLabel("用户:", new ImageIcon("imgs/base/user.png"), SwingConstants.RIGHT);
		lbl2.setBounds(130, 40, 100, 23);
		panel.add(lbl2);
		JLabel lbl3 = new JLabel("密码:", new ImageIcon("imgs/base/pass.png"), SwingConstants.RIGHT);
		lbl3.setBounds(130, 70, 100, 23);
		panel.add(lbl3);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		comboBox.setBounds(230, 10, 140, 23);
		comboBox.addItem("教师登录");
		comboBox.addItem("学生登录");
		comboBox.setSelectedIndex(1);
		
		textField = new JTextField();
		textField.setBounds(230, 40, 140, 23);
		panel.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(230, 70, 140, 23);
		panel.add(passwordField);
		
		JButton btn1 = new JButton("找回密码");
		btn1.setBounds(380, 10, 93, 23);
		panel.add(btn1);
		
		JButton btnOK = new JButton("登    录");
		btnOK.setBounds(380, 40, 93, 23);
		panel.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
		
		JButton button3 = new JButton("退    出");
		button3.setBounds(380, 70, 93, 23);
		panel.add(button3);
	}
}
