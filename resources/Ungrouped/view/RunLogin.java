package view;


import javax.swing.JFrame;
import javax.swing.UIManager;


public class RunLogin {
	public static void main(String[] args) {
		
		try {
			// 设置观感 
//			UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel());
			//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel());
			//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel());
			// 设置水印
			//SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceMosaicWatermark");
			// 设置渐变渲染
			//SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.WaveGradientPainter");
			
			JFrame.setDefaultLookAndFeelDecorated(true);
			//JDialog.setDefaultLookAndFeelDecorated(true);
			//JInternalFrame.setDefaultLocale(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		LoginFrame frame = new LoginFrame();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
