package SimpleTest;

import db.DBManager;
import entity.ScoreResearch;

import javax.naming.spi.DirStateFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author : BabyMuu
 * @File : daoimp
 * @Time : 2021/12/26 16:50
 */
public class daoimp {

    public static void main(String[] args) {
        String content = "3019��12��";

        boolean isMatch = Pattern.matches("[1,2][0,9][0-9][0-9]��[0,1][0-9]��", content);

        System.out.println("�ַ������Ƿ������ 'runoob' ���ַ���? " + isMatch);
    }
}
