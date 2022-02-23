package SimpleTest;

import dao.imp.RestPwdDaoImp;

import entity.RestPwd;

import java.util.List;

public class testRestPwd {
    public static void testFindAll() {
        RestPwdDaoImp rpdi = new RestPwdDaoImp();

        List<RestPwd> rps = rpdi.findAll();
        for (RestPwd RestPwd : rps) {

            System.out.println(RestPwd);
        }
    }

    public static void testFindById() {
        RestPwdDaoImp rpdi = new RestPwdDaoImp();

        RestPwd RestPwd = rpdi.findById("202007030330");

        System.out.println(RestPwd);
    }

    public static void testInsert() {
        RestPwdDaoImp rpdi = new RestPwdDaoImp();

        RestPwd stu = new RestPwd(
                "202007030330",
                "1",
                "1",
                "2",
                "2",
                "3",
                "3",
                5
        );

        System.out.println (rpdi.insert(stu));
        ;
    }

    public static void testUpdate() {
        RestPwdDaoImp rpdi = new RestPwdDaoImp();
        RestPwd stu = rpdi.findById("202007030330");
        stu.setAns1("zhangsan");
        rpdi.update(stu);
    }

    public static void testDel() {
        RestPwdDaoImp rpdi = new RestPwdDaoImp();
        rpdi.delete("202007030330");

    }

    public static void main(String[] args) {
//        testFindAll();
//        testInsert();
//        testUpdate();
        
        testDel();
        testFindById();
    }
}
