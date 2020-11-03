//
//import test.selenium.it.AbstractIntegrationTestCase;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author ikezawa
// */
//public class JizenTest extends AbstractIntegrationTestCase {
//
//    @Test
//    public void test() throws Throwable {
//        //トップページ
//        selenium.open("/SampleWebApp/");
//        selenium.click("link=事前登録機能");
//        selenium.waitForPageToLoad("30000");
//        //インターネット申込み
//        assertEquals("インターネット申込み", selenium.getTitle());
//        selenium.click("submit");
//        selenium.waitForPageToLoad("30000");
//        //資格試験選択
//        assertTrue(selenium.isTextPresent("一級試験"));
//        assertTrue(selenium.isTextPresent("二級試験"));
//        assertTrue(selenium.isTextPresent("三級試験"));
//        assertTrue(selenium.isTextPresent("四級試験"));
//        selenium.click("link=登録可能です");
//        selenium.waitForPageToLoad("30000");
//        //事前登録入力
//        assertEquals("事前登録入力", selenium.getTitle());
//        selenium.type("shimeiSeiKanji", "池澤");
//        selenium.type("shimeiMeiKanji", "直美");
//        selenium.type("shimeiSeiKana", "イケザワ");
//        selenium.type("shimeiMeiKana", "ナオミ");
//        selenium.click("//input[@name='birthEra' and @value='3']");
//        selenium.select("birthYear", "label=56");
//        selenium.select("birthMonth", "label=5");
//        selenium.select("birthDay", "label=16");
//        selenium.type("telNo1", "080");
//        selenium.type("telNo2", "1111");
//        selenium.type("telNo3", "1111");
//        selenium.type("mailAddress", "n-ikezawa@test-sys.net");
//        selenium.type("passwd", "ikezawa");
//        selenium.select("passwdQuestionCode1", "label=好きな食べ物は？");
//        selenium.type("passwdAnswer1", "あ");
//        selenium.click("submit");
//        selenium.waitForPageToLoad("30000");
//        assertEquals("事前登録入力", selenium.getTitle());
//        selenium.click("back");
//        selenium.waitForPageToLoad("30000");
//        assertEquals("事前登録入力", selenium.getTitle());
//        selenium.click("submit");
//        selenium.waitForPageToLoad("30000");
//        assertEquals("事前登録入力", selenium.getTitle());
//        selenium.click("submit");
//        selenium.waitForPageToLoad("30000");
//        assertEquals("資格試験選択", selenium.getTitle());
//    }
//}
