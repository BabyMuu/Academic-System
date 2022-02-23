//package SimpleTest;
//
//import dao.imp.CourseArrangementDAOImp;
//import entity.CourseArrangement;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class CourseArrangementDAOImpTest {
//
//    @Test
//    void findAll() {
//        CourseArrangementDAOImp cai = new CourseArrangementDAOImp();
//        List<CourseArrangement> cas = new ArrayList<>();
//        cas = cai.findAll();
//        System.out.println(cas.size());
//
//        for (CourseArrangement ca : cas){
//            System.out.println(ca);
//        }
//    }
//
//    @Test
//    void findById() {
////        CourseArrangementDAOImp cai = new CourseArrangementDAOImp();
////        CourseArrangement ca = cai.findById(10);
////        System.out.println(ca);
//    }
//
//    @Test
//    void insert() {
//        CourseArrangement ca = new CourseArrangement(
//                1, "19820103", 42, 2020, 2
//        );
//        CourseArrangementDAOImp cadi = new CourseArrangementDAOImp();
//        System.out.println(cadi.insert(ca));
//    }
//
//    @Test
//    void update() {
////        CourseArrangementDAOImp cadi = new CourseArrangementDAOImp();
////        CourseArrangement ca = cadi.findById(25);
////        ca.setYear(2045);
////        System.out.println(cadi.update(ca));
//    }
//
//    @Test
//    void delete() {
//        CourseArrangementDAOImp cadi = new CourseArrangementDAOImp();
//        System.out.println(cadi.delete(29));
//    }
//}