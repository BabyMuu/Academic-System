//package SimpleTest;
//
//import dao.imp.ExamScheduleDAOImp;
//import entity.ExamSchedule;
//import org.junit.jupiter.api.Test;
//import lib.tools.Common;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class ExamScheduleDAOImpTest {
//
//    @Test
//    void findAll() {
//        ExamScheduleDAOImp cai = new ExamScheduleDAOImp();
//        List<ExamSchedule> cas = new ArrayList<>();
//        cas = cai.findAll();
//        System.out.println(cas.size());
//
//        for (ExamSchedule ca : cas){
//            System.out.println(ca);
//        }
//    }
//
//    @Test
//    void findById() {
////        ExamScheduleDAOImp cai = new ExamScheduleDAOImp();
////        ExamSchedule ca = cai.findById(25);
////        System.out.println(ca);
//    }
//
//    @Test
//    void insert(){
//        ExamSchedule ca = new ExamSchedule(
//                25, Common.stringChangeToDate("1987-01-12"),  0, "hello world"
//        );
//        ExamScheduleDAOImp cadi = new ExamScheduleDAOImp();
//        System.out.println(cadi.insert(ca));
//    }
//
//    @Test
//    void update() {
////        ExamScheduleDAOImp cadi = new ExamScheduleDAOImp();
////        ExamSchedule ca = cadi.findById(26);
////        ca.setState(1);
////        System.out.println(cadi.update(ca));
//    }
//
//    @Test
//    void delete() {
//        ExamScheduleDAOImp cadi = new ExamScheduleDAOImp();
//        System.out.println(cadi.delete(26));
//    }
//}