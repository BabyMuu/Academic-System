//
//package SimpleTest;
//
//import dao.imp.ClassInfoDAOImp;
//import entity.ClassInfo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class testClassInfo {
//    public static void main(String[] args) {
//        testQuery();
////        testQueryById();
//
//    }
//    private void testUpdate(){
//        ClassInfo jinRong = new ClassInfo("jinrou1", "2021-01-01", 42, "19830302");
//        ClassInfoDAOImp cii = new ClassInfoDAOImp();
//        jinRong.setStuCount(38);
//        jinRong.setClsId(54);
//        cii.delete(jinRong.getClsId());
//    }
//    public static void testQuery(){
//        ClassInfoDAOImp cii = new ClassInfoDAOImp();
//        List<ClassInfo> classess = new ArrayList<ClassInfo>();
//        classess = cii.findAll();
//        System.out.println(classess.size());
//
//        for (ClassInfo cls : classess){
//            System.out.println(cls);
//        }
//    }
//    public static void testQueryById(){
//        ClassInfo cls = new ClassInfo();
//        ClassInfoDAOImp cii = new ClassInfoDAOImp();
//        cls = cii.findById(42);
//        System.out.println(cls);
//    }
//}
