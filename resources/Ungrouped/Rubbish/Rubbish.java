package Rubbish;

import java.io.PrintStream;

public class Rubbish {

    /**
     * StudentDaoImp findAll()
     */
//	Student stu = new Student();
//	stu.setStuId(dbm.rs.getInt(1));
//	stu.setStuName(dbm.rs.getString(2));
//	stu.setStuAddress(dbm.rs.getString(3));
//	stu.setStuBtd(dbm.rs.getDate(4));
//	stu.setStuTel(dbm.rs.getString(5));
//	stu.setStuSex(dbm.rs.getBoolean(6));
//	stu.setStuAge(dbm.rs.getInt(7));
    private static void nomal() {
        long start = System.currentTimeMillis();
        long a = 100;
        for (int i = 0; i < 100000000; i++) {
            a *= 2;
            a /= 2;
        }
        System.out.println("nomal total used:"
                + (System.currentTimeMillis() - start) + "ms");
    }

    private static void bit() {
        long start = System.currentTimeMillis();
        long a = 100;
        for (int i = 0; i < 100000000; i++) {
            a <<= 1;
            a >>= 1;
        }
        System.out.println("bit total used:"
                + (System.currentTimeMillis() - start) + "ms");
    }

    public static void main(String[] args) {
        nomal();
        bit();
    }
}
