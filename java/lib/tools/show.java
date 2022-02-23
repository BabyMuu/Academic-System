package lib.tools;

import javax.swing.*;
import java.awt.*;

public class show {
    public static void text_error(JTextField obj, String text_err){
        obj.setText(text_err);
        obj.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        obj.setForeground(Color.RED);
    }
    public static void text_to_default(JTextField obj){
        obj.setText("");
        obj.setForeground(new Color(67,73,34));
    }

    public static void text_pre_msg(JTextField obj, String str) {
        obj.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        obj.setForeground(Color.GRAY);
        obj.setText(str);
    }
    public static void text_error(JLabel obj, String text_err){
        obj.setText(text_err);
        obj.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        obj.setForeground(Color.red);
    }
    public static void text_to_default(JLabel obj){
        obj.setText("");
        obj.setForeground(new Color(67,73,34));
    }

    public static void text_pre_msg(JLabel obj, String str) {
        obj.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        obj.setForeground(Color.GRAY);
        obj.setText(str);
    }
}
