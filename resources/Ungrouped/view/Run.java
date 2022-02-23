package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Run {
	public static void main(String[] args) {
		
		try {
			// 设置观感 
//			UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel());
			//设置水印
			//SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceMosaicWatermark");
			//设置渐变渲染
			//SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.WaveGradientPainter");
			//窗体与对话框使用新皮肤
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		JFrame.setDefaultLookAndFeelDecorated(true);
		//LoginFrame frame = new LoginFrame();
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
	
}
