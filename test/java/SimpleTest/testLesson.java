//package SimpleTest;
//
//import entity.Lesson;
//import java.util.ArrayList;
//import java.util.List;
//
//public class testLesson {
//    public static void main(String[] args) {
//        testQuery();
////        testQueryById();
////        testUpdate();
//    }
//
//    public static void testUpdate() {
//        Lesson les = new Lesson("math", "math", 4, 38);
//        LessonDaoImp ldi = new LessonDaoImp();
////        ldi.insert(les);
//        les.setHours(16);
//        les.setScore(5);
//        les.setLesId(7);
//        ldi.update(les);
//        ldi.delete(les.getLesId());
//    }
//
//    public static void testQuery() {
//        List<Lesson> lessons = new ArrayList<>();
//        LessonDaoImp ldi = new LessonDaoImp();
//        lessons = ldi.findAll();
//
//        for (Lesson les : lessons) {
//            System.out.println(les);
//        }
//    }
//
////    public static void testQueryById() {
////        Lesson les = new Lesson();
////        LessonDaoImp ldi = new LessonDaoImp();
////        les = ldi.findById(6);
////        System.out.println(les);
////    }
//
//}
