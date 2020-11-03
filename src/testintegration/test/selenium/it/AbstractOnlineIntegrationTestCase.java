package test.selenium.it;



import com.thoughtworks.selenium.SeleniumException;
import java.util.List;
import static org.junit.Assert.*;

/**
 * オンライン系の結合テストの抽象クラス。<br>
 * オンライン系の結合テストクラスは、このクラスを継承します。<br>
 * @author t-hattori
 */
public abstract class AbstractOnlineIntegrationTestCase extends AbstractIntegrationTestCase {

    /**
     * 画面をオープンする。<br>
     * 　オープン後に、画面タイトルを確認する。
     * @param buttonLabel オンライン選択画面でクリックするボタンのラベル
     * @param gamenTitle  オープンする画面タイトル（検証に使用する。）
     */
    protected void openGamen(String buttonLabel, String gamenTitle) {
        //アクション 目的の画面まで移動
        selenium.open(URI_SHIKEN_SENTAKU);
//        selenium.select(ShikenSentakuIntegrationTest.FORMID_INPUT_TANTO_SHIKEN_CODE, "value=SAMPLE");
//        selenium.click(ShikenSentakuIntegrationTest.FORMID_BUTTON_NEXT);
        selenium.waitForPageToLoad("30000");
//        selenium.click(ShikenMenuIntegrationTest.FORMID_BUTTON_ONLINE);
        selenium.waitForPageToLoad("30000");
        selenium.click("xpath=//input[@value='" + buttonLabel + "']");
        selenium.waitForPageToLoad("30000");
        //タイトル
        assertEquals(gamenTitle, selenium.getTitle());
    }

    /**
     * 使用不可を確認する。
     * @param xPath  フィールドのXPATH <br>
     *         例)//input[@id='ABCDEFG'] <br>
     *         先頭にxpath=を付ける必要はない。
     * @param id フィールドのID
     * @return 使用不可の時、true
     */
    protected boolean isDisabled(String xPath, String id) {
        if (selenium.getXpathCount(xPath).intValue() > 0) {
            return "disabled".equals(selenium.getAttribute("id=" + id + "@disabled"));
        }
        return false;
    }

    /**
     * インプットフィールドの使用不可を確認する。
     * @param id インプットフィールドのID
     * @return 使用不可の時、true。
     */
    protected boolean isDisabledInputField(String id) {
        String xPath = "//input[@id='" + id + "']/@disabled";
        return isDisabled(xPath, id);
    }

    /**
     * セレクトフィールドの使用不可を確認する。
     * @param id セレクトフィールドのID
     * @return 使用不可の時、true
     */
    protected boolean isDisabledSelectField(String id) {
        String xPath = "//select[@id='" + id + "']/@disabled";
        return isDisabled(xPath, id);
    }

    /**
     * チェックボックスのチェックONを確認する。
     * @param id チェックボックスのID
     * @return チェックON、true
     */
    protected boolean isChecked(String id) {
        try {
            return "checked".equals(selenium.getAttribute("id=" + id + "@checked"));
        } catch (SeleniumException ex) {
//            ex.printStackTrace();
            if (selenium.isElementPresent(id)) {
                return false;
            } else {
                throw ex;
            }
        }
    }

    /**
     * メッセージの検証
     * @param message メッセージ
     * @param error エラー表示の有無
     */
    protected void kenshoMessage(String message, boolean error) {
        assertEquals(message, selenium.getText("//table[@id='" + FORMID_MESSAGE + "']/tbody/tr/td"));
        if (error) {
            assertEquals("messageError", selenium.getAttribute("//table[@id='" + FORMID_MESSAGE + "']/tbody/tr@class"));
        } else {
            assertEquals("messageInfo", selenium.getAttribute("//table[@id='" + FORMID_MESSAGE + "']/tbody/tr@class"));
        }
    }

    /**
     * メッセージのブランク表示の検証
     */
    protected void kenshoMessageIsBlank() {
        assertEquals(1, selenium.getXpathCount("//div[@id='" + FORMID_MESSAGE + "']"));
    }

    /**
     * メッセージの検証
     * @param id メッセージの位置の<td>を表すid
     * @param message メッセージ
     * @param error エラー表示の有無
     */
    protected void kenshoMessage(String id, String message, boolean error) {
        assertEquals(message, selenium.getText(id));
        String idOfTr = id.substring(0, id.length() - 3);
        if (error) {
            assertEquals("messageError", selenium.getAttribute(idOfTr + "@class"));
        } else {
            assertEquals("messageInfo", selenium.getAttribute(idOfTr + "@class"));
        }
    }

//    /**
//     * 最新のスレッドを取得する。
//     * @return スレッド
//     */
//    protected Thread getLatestThread() {
//        //ここからデータ検証
//        // 最新スレッド取得
//        List<Thread> threadList = findAllForKiban(Thread.class, "THREAD");
//        Thread resultThread = threadList.get(threadList.size() - 1);
//        return resultThread;
//    }

    /**
     * 画面ヘッダ部 画面名の検証
     * @param gameName 画面名
     */
    protected void kenshoGamenName(String gameName) {
        // 画面名
        assertEquals(gameName, selenium.getText("//form[@id='" + FORMID_FORM + "']/table[1]/tbody/tr/td/table[2]/tbody/tr[3]/td[2]"));
    }

    /**
     * 画面ヘッダ部 試験名の検証
     * @param shikenName 試験名
     */
    protected void kenshoShikenName(String shikenName) {
        // 試験名
        if (shikenName == null || shikenName.length() == 0) {
            assertEquals("", selenium.getText("//form[@id='" + FORMID_FORM + "']/table[1]/tbody/tr/td/table[1]/tbody/tr[1]/td[1]/span"));
        } else {
            assertEquals("【" + shikenName + "】", selenium.getText("//form[@id='" + FORMID_FORM + "']/table[1]/tbody/tr/td/table[1]/tbody/tr[1]/td[1]/span"));
        }
    }

    /**
     * 画面ヘッダ部 ユーザー名の検証
     * @param userName ユーザー名
     */
    protected void kenshoUserName(String userName) {
        // ユーザー名
        if (userName == null || userName.length() == 0) {
            assertEquals("", selenium.getText("//form[@id='" + FORMID_FORM + "']/table[1]/tbody/tr/td/table[1]/tbody/tr[2]/td[1]"));
        } else {
            assertEquals("利用者名 ： " + userName, selenium.getText("//form[@id='" + FORMID_FORM + "']/table[1]/tbody/tr/td/table[1]/tbody/tr[2]/td[1]"));
        }
    }
}
