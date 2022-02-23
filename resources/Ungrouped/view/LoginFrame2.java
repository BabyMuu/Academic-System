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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class LoginFrame2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Font select1 = new Font("微软雅黑", Font.PLAIN, 15);
	private Font select2 = new Font("微软雅黑", Font.BOLD, 15);
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public LoginFrame2() {
		setTitle("\u7CFB\u7EDF\u767B\u9646");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u7CFB\u7EDF\u767B\u9646");
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		label.setBounds(259, 46, 107, 29);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.BLUE);
		label_1.setBounds(42, 190, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_2.setBounds(42, 131, 54, 15);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(142, 128, 262, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(142, 187, 262, 21);
		contentPane.add(textField_1);
		
		JButton btnLogin = new JButton("\u767B  \u5F55");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.BLUE);
		btnLogin.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
		btnLogin.setBounds(173, 236, 93, 23);
		contentPane.add(btnLogin);
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin.setForeground(Color.BLUE);
				btnLogin.setBackground(Color.WHITE);
				btnLogin.setFont(select1);
			}
		});
		btnNewButton_1.setBounds(409, 283, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin.setForeground(Color.WHITE);
				btnLogin.setBackground(Color.BLUE);
				btnLogin.setFont(select2);
			}
		});
		btnNewButton_2.setBounds(353, 316, 112, 15);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(173, 264, 93, 23);
		contentPane.add(btnNewButton);
	}
	void des() {
		super.dispose();
	}
}
