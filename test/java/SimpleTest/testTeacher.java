//package SimpleTest;
//
//import dao.imp.TeacherDaoImp;
//import entity.Teacher;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import lib.tools.Common;
//
//public class testTeacher {
//    public static void main(String[] args) {
////        testUpdate();
//        testQuery();
////        testQueryById();
//
//    }
//
//
//    private static void testUpdate() {
//        Teacher zhangsan = new Teacher(
//                "99999999",
//                "zhangsan",
//                "teacher",
//                "1",
//                Common.stringChangeToDate("2019-01-02"),
//                "1",
//                1,
//                1,
//                "",1);
//        TeacherDaoImp tdi = new TeacherDaoImp();
////        tdi.insert(zhangsan);
//        zhangsan.setTeaTitle("leader");
//        tdi.update(zhangsan);
//        tdi.delete(zhangsan.getTeaNum());
//    }
//
//    public static void testQuery() {
//        TeacherDaoImp tdi = new TeacherDaoImp();
//        List<Teacher> teachers = new ArrayList<Teacher>();
//        teachers = tdi.findAll();
//
//        for (Teacher cls : teachers) {
//            System.out.println(cls);
//        }
//    }
//
//    public static void testQueryById() {
////        TeacherDaoImp tdi = new TeacherDaoImp();
////        Teacher tea = tdi.findById("19820103");
////
////        System.out.println(tea);
//    }
//}
