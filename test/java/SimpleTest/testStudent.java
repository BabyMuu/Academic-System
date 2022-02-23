package SimpleTest;

import lib.tools.Common;
import dao.imp.StudentDaoImp;
import entity.Student;

import java.util.List;

public class testStudent {

    public static void testFindAll(){
        StudentDaoImp sdi = new StudentDaoImp();
        List<Student> stus = sdi.findAll();
        for (Student student : stus) {
            System.out.println(student);
        }
    }
    public static void testFindById(){
//        StudentDaoImp sdi = new StudentDaoImp();
//        Student student = sdi.findById("999999999999");
//        System.out.println(student);
    }
    public static void testInsert(){
        StudentDaoImp sdi = new StudentDaoImp();
        Student stu = new Student(
                "999999999999",
                42,
                "zhangsan",
                Common.stringChangeToDate("2020-04-05"),
                "1",
                1,
                true,
                1
        );
        System.out.println(sdi.insert(stu));;
    }
    public static void testUpdate(){
        StudentDaoImp sdi = new StudentDaoImp();
//        Student stu = sdi.findById("999999999999");
//        stu.setMale(false);
//        sdi.update(stu);
    }
    public static void testDel(){
        StudentDaoImp sdi = new StudentDaoImp();
        sdi.delete("999999999999");
    }
    public static void main(String[] args) {
        testFindAll();
//        testInsert();
//        testUpdate();
//        testDel();
//        testFindById();
    }
}
