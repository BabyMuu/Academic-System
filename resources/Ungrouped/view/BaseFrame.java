package view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class BaseFrame extends JInternalFrame {
	private JLabel msg = new JLabel(); 
	public BaseFrame() {
		setSize(800, 600);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.add(new JLabel("提示"));
	}
	protected void setErrorMsg() {
		
	}
	protected void setInfoMsg() {
		
	}

}
