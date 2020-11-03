//package test.selenium.it.daf;
//
//
//import test.selenium.it.AbstractIntegrationTestCase;
//import jp.co.nii.miw.business.domain.Moshikomi;
//import org.junit.Test;
//
//public class SampleTest extends AbstractIntegrationTestCase {
//
//    @Test
//    public void test() throws Throwable {
//
//////        //テーブル全件削除
//////        deleteAll("jizen_joho");
////        //ジョブ
////        createDbForKibanFromFileWithHeader(JizenMoshikomi.class, getTestDataPath("JizenJoho.txt"));
////        
////        // 準備
////        // DB
////        // 事前申込
//////        createDbJizenMoshikomi();
////
////
////        selenium.open("/SampleWebApp/");
////        selenium.click("link=申込機能");
////        selenium.waitForPageToLoad("30000");
////        assertEquals("申込みログイン", selenium.getTitle());
////
////        selenium.type("userid", "00000085");
////        selenium.type("passwd", "ikezawa");
////        selenium.click("submit");
////        selenium.waitForPageToLoad("30000");
////        assertEquals("申込同意規約", selenium.getTitle());
////        selenium.click("submit");
////        selenium.waitForPageToLoad("30000");
////        assertEquals("申込情報入力", selenium.getTitle());
//    }
//
//    private void createDbJizenMoshikomi() {
//        //テーブル全件削除
//        deleteAll("jizen_joho");
//        //ジョブ
//        createDbForKibanFromFileWithHeader(Moshikomi.class, getTestDataPath("JobImp.txt"));
//        
////        //試験問題見出し
////        createDbForGyomuFromFileWithHeader(ShikenMondaiMidashi.class, getTestDataPath("ShikenMondaiMidashiImp.txt"));
////        // パスワード変更日付を、システム日付を基準に89,90,91日前に更新する
////        String date91mae = DateTimeUtility.addDateFromyyyyMMdd(today, Calendar.DAY_OF_MONTH, -91);
////        String date90mae = DateTimeUtility.addDateFromyyyyMMdd(today, Calendar.DAY_OF_MONTH, -90);
////        String date89mae = DateTimeUtility.addDateFromyyyyMMdd(today, Calendar.DAY_OF_MONTH, -89);
////        executeSql("UPDATE COMMON.SEP_USER SET PASSWD_HENKO_DATE = '" + date91mae + "' WHERE USER_ID = 'testuser7'");
////        executeSql("UPDATE COMMON.SEP_USER SET PASSWD_HENKO_DATE = '" + date90mae + "' WHERE USER_ID = 'testuser8'");
////        executeSql("UPDATE COMMON.SEP_USER SET PASSWD_HENKO_DATE = '" + date89mae + "' WHERE USER_ID = 'testuser9'");
//    }
//}
