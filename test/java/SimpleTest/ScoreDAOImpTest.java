//package SimpleTest;
//
//import dao.imp.ScoreDAOImp;
//import entity.Score;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ScoreDAOImpTest {
//
//    @Test
//    void findAll() {
//        ScoreDAOImp cai = new ScoreDAOImp();
//        List<Score> cas = new ArrayList<>();
//        cas = cai.findAll();
//        System.out.println(cas.size());
//
//        for (Score ca : cas){
//            System.out.println(ca);
//        }
//    }
//
//    @Test
//    void findById() {
////        ScoreDAOImp cai = new ScoreDAOImp();
////        Score ca = cai.findById(10);
////        System.out.println(ca);
//    }
//
//    @Test
//    void insert() {
//        Score ca = new Score(
//                25, "202007030334", 100
//        );
//        ScoreDAOImp cadi = new ScoreDAOImp();
//        System.out.println(cadi.insert(ca));
//    }
//
//    @Test
//    void update() {
////        ScoreDAOImp cadi = new ScoreDAOImp();
////        Score ca = cadi.findById(839);
////        ca.setScore(89);
////        System.out.println(cadi.update(ca));
//    }
//
//    @Test
//    void delete() {
//        ScoreDAOImp cadi = new ScoreDAOImp();
//        System.out.println(cadi.delete(839));
//    }
//}