package lib.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Common {
    private final static String PATTERN = "yyyy-MM-dd HH:mm:ss:SS EEEE";
    private final static String PATTERNS = "yyyy-MM-dd";
    private final static String PATTERNSWEEK = "yyyy-MM-dd HH:mm EEEE";


    public static String getNowTimeString() {
        return new SimpleDateFormat(PATTERNSWEEK).format(new Date());
    }

    public static Date getNowTimeDate() {
        return new Date();
    }

    public static String dateChangeToString(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String dateChangeToString(Date date) {
        return new SimpleDateFormat(PATTERNS).format(date);
    }

    public static Date stringChangeToDate(String date) {
        try {
            return new SimpleDateFormat(PATTERNS).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date stringChangeToDate(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getAge(Date now, Date btd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int nowYear = calendar.get(1);
        calendar.setTime(btd);
        int btdYear = calendar.get(1);
        return nowYear - btdYear;
    }

    public static String boolChangeToStr(boolean sex) {
        return sex ? "male" : "female";
    }

    public static char boolChangeToBit(boolean sex) {
        return sex ? '1' : '0';
    }


    public static boolean isInteger(String str) {
        if (str.equals("")) return false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
//        System.out.println(getNowTimeString());
        System.out.println(isInteger("zhangsna"));
    }
}
