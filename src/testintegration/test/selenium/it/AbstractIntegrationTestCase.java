package test.selenium.it;



import java.util.Iterator;
import java.util.TreeMap;
import java.util.SortedMap;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.utility.FileUtility;
import jp.co.nii.sew.business.domain.BusinessObject;
import jp.co.nii.sew.integration.AbstractDao;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.server.SeleniumServer;

/**
 * オンラインおよびバッチ系共通の抽象クラス。
 * @author t-hattori
 */
public abstract class AbstractIntegrationTestCase<T extends AbstractDao> {

    protected static final Connection rotatedCon = getConnection();
    protected static final String SCHEMA_NAME_COMMON = "eddt";
    protected static final String DATA_SOURCE_NAME = "eddt_edd_DS";
    protected static Selenium selenium;
    /** 画面のURI ログイン画面 */
    protected static final String URI_LOGIN = "/SampleWebApp";
    /** 画面のURI 試験選択画面 */
    protected static final String URI_SHIKEN_SENTAKU = "/SEP/ShikenSentaku.jsf";
    /** 画面のURI バッチ処理制御画面 */
    protected static final String URI_BATCH_SEIGYO = "/SEP/BatchSeigyo.jsf";
    /** 画面のid フォーム */
    public static final String FORMID_FORM = "form";
    /** 画面のid ボタン「戻る」 */
    protected static final String FORMID_BUTTON_BACK = FORMID_FORM + ":buttonsRight:back";
    /** 画面のid ボタン「ログアウト」 */
    protected static final String FORMID_BUTTON_LOGOUT = FORMID_FORM + ":buttonsRight:logout";
    /** 画面のid ボタン「ﾀﾞｳﾝﾛｰﾄﾞ画面」 */
    protected static final String FORMID_BUTTON_GOTO_DOWNLOAD = FORMID_FORM + ":buttonsRight:gotoDownload";
    /** 画面のid ヘッダ部メッセージ欄 */
    protected static final String FORMID_MESSAGE = FORMID_FORM + ":message";
    /** 画面のid パン屑リスト 担当試験選択 */
    protected static final String FORMID_BREADCRUNB_TANTO_SHIKE_SENTAKU = FORMID_FORM + ":breadcrumbToShikenSentaku:breadcrumb";
    /** 画面のid パン屑リスト 試験メニュー */
    protected static final String FORMID_BREADCRUNB_SHIKEN_MENU = FORMID_FORM + ":breadcrumbToMenu:breadcrumb";
    /** 画面のid パン屑リスト システム管理メニュー */
    protected static final String FORMID_BREADCRUNB_SYSTEM_KANRI_MENU = FORMID_FORM + ":breadcrumbToSystemKanriMenu:breadcrumb";
    /** 画面のid パン屑リスト バッチ処理制御 */
    protected static final String FORMID_BREADCRUNB_BATCH_SEIGYO = FORMID_FORM + ":breadcrumbToBatchSeigyo:breadcrumb";
    /** 画面のid パン屑リスト ファイルダウンロード */
    protected static final String FORMID_BREADCRUNB_FILE_DOWNLOAD = FORMID_FORM + ":breadcrumbToFileDownload:breadcrumb";
    private static final String DEFAULT_AP_BROWSER = "*firefox";
    private static final String DEFAULT_AP_URL = "http://localhost:8080/";
    private static final String DEFAULT_LOGIN_USER = "test";
    private static final String DEFAULT_LOGIN_PASSWORD = "testpass";
    private static final String DEFAULT_DB_URL = "jdbc:postgresql://172.18.23.102:5432/eddt";
    private static final String DEFAULT_DB_USER = "eddsys";
    private static final String DEFAULT_DB_PASSWORD = "eddsys";
    private static SeleniumServer seleniumServer;
    private static String apBrowser;
    private static String apUrl;
    private static String loginUser;
    private static String loginPassword;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    /** 文字コード・セット名 UTF-8 */
    public static final String CHARACTER_SET_NAME_UTF8 = "UTF-8";
    // すべてのテストメソッドでモックにしたいクラスはフィールドに宣言する。
    @Mocked(methods = {"getConnection", "close(Connection)"})
    AbstractDao dao;

    /**
     * テストクラス実行前に1度だけ呼び出される。<br>
     * 処理内容を変更したい場合はサブクラスで隠蔽してください。
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        // 使用するブラウザやテスト先のURLをカスタマイズする場合の記述方法。
//        setApBrowser("*iexplore");
//        setApUrl("http://sep-serv:8000/");
        seleniumStart();
        // 以下のテーブルのデータは事前に準備しておく必要があります。
        // ★SEP_USERテーブル
        // ★TANTO_SHIKENテーブル
        // ★SEP_USER_KENGENテーブル
        // ログインユーザーをカスタマイズする場合の記述方法。
//        setLoginUser("test");
//        setLoginPassword("testpass2");
//        sepLogin();
    }

    /**
     * テストクラス実行後に1度だけ呼び出される。<br>
     * 処理内容を変更したい場合はサブクラスで隠蔽してください。
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        sepLogout();
        seleniumStop();
    }

    /**
     * 各テストメソッド実行前に呼び出される。<br>
     * 処理内容を変更したい場合はサブクラスでオーバーライドする。
     */
    @Before
    public void setUp() {
        NonStrictExpectationsForDbAccess();
    }

    /**
     * Seleniumを起動する。
     */
    protected static void seleniumStart() throws Exception {
        // Seleniumサーバーを起動する。
        seleniumServerStart();
        // Seleniumインスタンスを起動する。
        seleniumInstanceStart();
        // ブラウザを最大化する。
//        selenium.windowMaximize();
        // ログイン時にウィンドウサイズを設定する。
        // ブラウザの操作速度を200ミリ秒に設定する。
        selenium.setSpeed("200");
    }

    /**
     * Seleniumを停止する。
     */
    protected static void seleniumStop() {
        // 使い回しのコネクションをクローズする。
        close(rotatedCon);
        // Seleniumインスタンスを停止する。
        seleniumInstanceStop();
        // Seleniumサーバーを停止する。
        seleniumServerStop();
    }

    /**
     * Seleniumサーバー（Selenium Remote Control Server）を起動する。<br>
     * Seleniumサーバーは、デフォルト設定では、<br>
     * 自端末（localhost）の4444番ポートでサービスを開始します。
     */
    protected static void seleniumServerStart() throws Exception {
        seleniumServer = new SeleniumServer();
        seleniumServer.start();
    }

    /**
     * Seleniumサーバーを停止する。
     */
    protected static void seleniumServerStop() {
        seleniumServer.stop();
    }

    /**
     * Seleniumインスタンスを起動する。<br>
     * DefaultSeleniumのコンストラクタの引数は順に以下のとおり。<br>
     * 1. Seleniumサーバーのホスト名（デフォルトはlocalhost）<br>
     * 2. Seleniumサーバーのポート番号（デフォルトは4444）<br>
     * 3. 使用するブラウザ<br>
     * 4. テスト先のURL
     */
    protected static void seleniumInstanceStart() {
        selenium = new DefaultSelenium("localhost", seleniumServer.getPort(), getApBrowser(), getApUrl());
        selenium.start();
    }

    /**
     * Seleniumインスタンスを停止する。
     */
    protected static void seleniumInstanceStop() {
        selenium.stop();
    }

//    /**
//     * SEPにログインする。
//     */
//    protected static void sepLogin() {
//        sepLogin(getLoginUser(), getLoginPassword());
//    }

//    /**
//     * SEPにログインする。
//     * @param userId ユーザーＩＤ
//     * @param passwd パスワード
//     */
//    protected static void sepLogin(final String userId, final String passwd) {
//        openSep();
//        selenium.type(AbstractLoginIntegrationTest.FORMID_INPUT_USER_ID, userId);
//        selenium.type(AbstractLoginIntegrationTest.FORMID_INPUT_PASSWD, passwd);
//        selenium.click(AbstractLoginIntegrationTest.FORMID_BUTTON_LOGIN);
//        selenium.waitForPageToLoad("30000");
//    }

    protected static void openSep() {
        selenium.open(URI_LOGIN);
        resizeWindow();
    }

    /**
     * SEPからログアウトする。
     */
    protected static void sepLogout() {
//        selenium.click(FORMID_BUTTON_LOGOUT);
        selenium.getConfirmation();
        selenium.waitForPageToLoad("30000");
    }

    protected static void resizeWindow() {
        // ウィンドウ位置を変更
        selenium.getEval("this.page().getCurrentWindow().moveTo(0, 0);");
        // ウィンドウサイズを変更（画面解像度1024×768での最大表示と同じ位のサイズ）
        selenium.getEval("this.page().getCurrentWindow().resizeTo(1032, 768);");
    }

    /**
     * テストクラス内でDBアクセスするための期待値を記録する。<br>
     * （JNDIでコネクションを取得できないため）
     */
    protected void NonStrictExpectationsForDbAccess() {
        try {
            new NonStrictExpectations() {
                // close()をモック化するのは、コネクションを使い回すため。

//                @Mocked(methods = {"getConnection", "close(Connection)"})
//                final AbstractDao unused = null;

                {
                    invoke(dao, "getConnection");
                    result = rotatedCon;
                }
            };
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * テストクラス内でDBのコネクションが必要なときに使用する。
     * @return コネクション
     */
    protected static Connection getConnection() {
        Connection con;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPassword());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return con;
    }

    /**
     * ステートメントをクローズする。
     * @param stmt ステートメント
     */
    private static void close(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * コネクションをクローズする。
     * @param con コネクション
     */
    protected static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * テストクラス内で、指定した基盤系テーブルを全件削除するときに使用する。
     * @param tableName テーブル名
     */
    protected void deleteAllForKiban(String tableName) {
        deleteAll(true, tableName);
    }

    /**
     * テストクラス内で、指定した業務系テーブルを全件削除するときに使用する。
     * @param tableName テーブル名
     */
    protected void deleteAll(String tableName) {
        deleteAll(false, tableName);
    }

    /**
     * テストクラス内で指定したテーブルを全件削除するときに使用する。
     * @param isKiban 基盤系かどうか
     * @param tableName テーブル名
     */
    private void deleteAll(boolean isKiban, String tableName) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM " + SCHEMA_NAME_COMMON + "." + tableName;
        try {
            con = rotatedCon;
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            close(stmt);
        }
    }

    /**
     * テストクラス内でSQLを実行するときに使用する。
     * @param sql SQL文
     */
    protected void executeSql(String sql) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = rotatedCon;
            stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            close(stmt);
        }
    }

    /**
     * テストクラス内で、指定した基盤系テーブルを全件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param tableName テーブル名
     * @return 主キーでソートされたBOのリスト
     */
    protected <T extends BusinessObject> List<T> findAllForKiban(
            Class<T> boClass, String tableName) {
        //return findAllForKiban(boClass, tableName); 2010.7.15
        return findAll(boClass, true, tableName);
    }

    /**
     * テストクラス内で、指定した基盤系テーブルを全件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @return 主キーでソートされたBOのリスト
     */
    protected <T extends BusinessObject> List<T> findAllForKiban(
            Class<T> boClass) {
        return findAll(boClass, true);
    }

    /**
     * テストクラス内で、指定した業務系テーブルを全件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param tableName テーブル名
     * @return 主キーでソートされたBOのリスト
     */
    protected <T extends BusinessObject> List<T> findAllForGyomu(
            Class<T> boClass, String tableName) {
        return findAll(boClass, false, tableName);
    }

    /**
     * テストクラス内で、指定した業務系テーブルを全件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @return 主キーでソートされたBOのリスト
     */
    protected <T extends BusinessObject> List<T> findAllForGyomu(
            Class<T> boClass) {
        return findAll(boClass, false);
    }

    /**
     * テストクラス内でデータを全件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param isKiban 基盤系かどうか
     * @return 主キーでソートされたBOのリスト
     */
    private <T extends BusinessObject> List<T> findAll(Class<T> boClass,
            boolean isKiban) {

        return findAll(boClass, isKiban, getTableName(boClass));
    }

    /**
     * テストクラス内でデータを全件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param isKiban 基盤系かどうか
     * @param tableName テーブル名
     * @return 主キーでソートされたBOのリスト
     */
    private <T extends BusinessObject> List<T> findAll(Class<T> boClass,
            boolean isKiban, String tableName) {

        return findWhere(boClass, isKiban, tableName, "");
//        // BOのクラス名からDAOImplのクラス名を取得する。
//        String daoImplClassName = "jp.co.nii.sep.integration.db." +
//                boClass.getSimpleName() + "DaoImpl";
//        Connection con = null;
//        PreparedStatement stmt = null;
//        String sql = "SELECT * FROM " + getSchemaName(isKiban) + "." + tableName +
//                getOrderByPk(boClass, isKiban);
//        List<T> boList = new ArrayList<T>();
//        try {
//            con = getConnection();
//            stmt = con.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                T bo = newBo(boClass, isKiban);
//                // ResultSetをBOの全フィールドにセットする。
//                Deencapsulation.invoke(Deencapsulation.newInstance(daoImplClassName),
//                        "setBoFromResultSet", bo, rs);
//                boList.add(bo);
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        } finally {
//            close(stmt);
//            close(con);
//        }
//        return boList;
    }

    /**
     * 渡されたBOのリストを主キー順にソートして、ソートされたBOのリストを返す。
     * @param <T> 型
     * @param boList BOのリスト
     * @return 主キーでソートされたBOのリスト
     */
    protected <T extends BusinessObject> List<T> getSortedBoList(List<T> boList) {
        // SortedMapを使ってBOのリストを主キー順にソートする。
        List<T> sortedBoList = new ArrayList<T>();
        SortedMap<String, T> sortedMap = new TreeMap<String, T>();
        for (T bo : boList) {
            sortedMap.put(bo.getPrimaryKey(), bo);
        }
        Iterator<String> it = sortedMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            sortedBoList.add(sortedMap.get(key));
        }
        return sortedBoList;
    }
    /**
     * ファイル（タブ区切りデータ）から基盤系BOのリストを作成する。<br>
     * ファイル中のデータに、ヘッダーがないときに使います。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param path BOにセットするデータのパス名
     * @return BOのリスト
     */
    protected <T extends BusinessObject> List<T> createBoListForKibanFromFile(
            Class<T> boClass, String path) {
        return createBoListFromFile(boClass, true, path, false);
    }

    /**
     * ファイル（タブ区切りデータ）から基盤系DBにデータを登録する。<br>
     * ファイル中のデータに、ヘッダーがないときに使います。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param path BOにセットするデータのパス名
     */
    protected <T extends BusinessObject> void createDbForKibanFromFile(Class<T> boClass,
            String path) {
        createDbFromBoList(createBoListForKibanFromFile(boClass, path));
    }

    /**
     * ファイル（タブ区切りデータ）から基盤系BOのリストを作成する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param path BOにセットするデータのパス名
     * @return BOのリスト
     */
    protected <T extends BusinessObject> List<T> createBoListForKibanFromFileWithHeader(
            Class<T> boClass, String path) {
        return createBoListFromFile(boClass, true, path, true);
    }

    /**
     * ファイル（タブ区切りデータ）から基盤系BOのリストを作成する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。<br>
     * データのファイル名は、BOのクラス名+"Exp.txt"。
     * @param <T> 型
     * @param boClass BOのクラス
     * @return BOのリスト
     */
    protected final <T extends BusinessObject> List<T> createBoListForKibanFromFileWithHeader(final Class<T> boClass) {
        return createBoListForKibanFromFileWithHeader(boClass, getTestDataPath(boClass.getSimpleName() + "Exp.txt"));
    }

    /**
     * ファイル（タブ区切りデータ）から基盤系BOのリストを作成する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。<br>
     * データのファイル名は、BOのクラス名+"Exp"+suffix+".txt"。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param suffix ファイル名の接尾語
     * @return BOのリスト
     */
    protected final <T extends BusinessObject> List<T> createBoListForKibanFromFileWithHeaderBySuffix(final Class<T> boClass, String suffix) {
        if (suffix == null) {
            suffix = "";
        }
        return createBoListForKibanFromFileWithHeader(boClass, getTestDataPath(boClass.getSimpleName() + "Exp" + suffix + ".txt"));
    }

    /**
     * ファイル（タブ区切りデータ）から基盤系DBにデータを登録する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param path BOにセットするデータのパス名
     */
    protected <T extends BusinessObject> void createDbForKibanFromFileWithHeader(
            Class<T> boClass, String path) {
        createDbFromBoList(createBoListForKibanFromFileWithHeader(boClass, path));
    }

    /**
     * ファイル（タブ区切りデータ）から基盤系DBにデータを登録する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。<br>
     * データのファイル名は、BOのクラス名+"Imp.txt"。
     * @param <T> 型
     * @param boClass BOのクラス
     */
    protected <T extends BusinessObject> void createDbForKibanFromFileWithHeader(
            Class<T> boClass) {
        createDbFromBoList(createBoListForKibanFromFileWithHeader(boClass, getTestDataPath(boClass.getSimpleName() + "Imp.txt")));
    }

    /**
     * ファイル（タブ区切りデータ）から基盤系DBにデータを登録する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。<br>
     * データのファイル名は、BOのクラス名+"Imp"+suffix+".txt"。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param suffix ファイル名の接尾語
     */
    protected <T extends BusinessObject> void createDbForKibanFromFileWithHeaderBySuffix(
            Class<T> boClass, String suffix) {
        if (suffix == null) {
            suffix = "";
        }
        createDbForKibanFromFileWithHeader(boClass, getTestDataPath(boClass.getSimpleName() + "Imp" + suffix + ".txt"));
    }

    /**
     * ファイル（タブ区切りデータ）から業務系BOのリストを作成する。<br>
     * ファイル中のデータに、ヘッダーがないときに使います。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param path BOにセットするデータのパス名
     * @return 登録したBOのリスト
     */
    protected <T extends BusinessObject> List<T> createBoListForGyomuFromFile(
            Class<T> boClass, String path) {
        return createBoListFromFile(boClass, false, path, false);
    }

    /**
     * ファイル（タブ区切りデータ）から業務系DBにデータを登録する。<br>
     * ファイル中のデータに、ヘッダーがないときに使います。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param path BOにセットするデータのパス名
     */
    protected <T extends BusinessObject> void createDbForGyomuFromFile(
            Class<T> boClass, String path) {
        createDbFromBoList(createBoListForGyomuFromFile(boClass, path));
    }

    /**
     * ファイル（タブ区切りデータ）から業務系BOのリストを作成する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param path BOにセットするデータのパス名
     * @return BOのリスト
     */
    protected <T extends BusinessObject> List<T> createBoListForGyomuFromFileWithHeader(
            Class<T> boClass, String path) {
        return createBoListFromFile(boClass, false, path, true);
    }

    /**
     * ファイル（タブ区切りデータ）から業務系BOのリストを作成する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。<br>
     * データのファイル名は、BOのクラス名+"Exp"+suffix+".txt"。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param suffix ファイル名の接尾語
     * @return BOのリスト
     */
    protected final <T extends BusinessObject> List<T> createBoListForGyomuFromFileWithHeaderBySuffix(final Class<T> boClass, String suffix) {
        if (suffix == null) {
            suffix = "";
        }
        return createBoListForGyomuFromFileWithHeader(boClass, getTestDataPath(boClass.getSimpleName() + "Exp" + suffix + ".txt"));
    }

    /**
     * ファイル（タブ区切りデータ）から業務系DBにデータを登録する。<br>
     * ファイル中のデータに、ヘッダーがあるときに使います。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param path BOにセットするデータのパス名
     */
    protected <T extends BusinessObject> void createDbForGyomuFromFileWithHeader(
            Class<T> boClass, String path) {
        createDbFromBoList(createBoListForGyomuFromFileWithHeader(boClass, path));
    }

    /**
     * ファイル（タブ区切りデータ）からBOのリストを作成する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param isKiban 基盤系かどうか
     * @param path BOにセットするデータのパス名
     * @param hasHeader ファイル中のデータのヘッダーの有無
     * @return BOのリスト
     */
    private <T extends BusinessObject> List<T> createBoListFromFile(
            Class<T> boClass, boolean isKiban, String path, boolean hasHeader) {
        List<T> boList = new ArrayList<T>();
        try {
            List<String> testDataList = FileUtility.readLines(path,
                    CHARACTER_SET_NAME_UTF8);
            for (int i = 0; i < testDataList.size(); i++) {
                if ((i == 0) && hasHeader) {
                    // ヘッダー行は読み飛ばす。
                    continue;
                }
                String testData = testDataList.get(i);
                T bo = newBo(boClass, isKiban);
                // タブ区切りデータをBOの全フィールドにセットする。
                Deencapsulation.invoke(bo, "setAllFieldsFromTsv", testData);
                boList.add(bo);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return boList;
    }

    /**
     * スキーマ名を取得する。
     * @param isKiban 基盤系かどうか
     * @return スキーマ名
     */
    private String getSchemaName() {
        return SCHEMA_NAME_COMMON;
    }

    /**
     * ORDER BY句を取得する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param isKiban 基盤系かどうか
     * @return ORDER BY句
     */
    private <T extends BusinessObject> String getOrderByPk(Class<T> boClass,
            boolean isKiban) {
        T bo = newBo(boClass, isKiban);
        int pkColumnCount = bo.getPrimaryKey().split(StringUtility.SEW_DELIMITER, -1).length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pkColumnCount; i++) {
            if (i == 0) {
                sb.append(" ORDER BY 1");
            } else {
                sb.append(",").append(i + 1);
            }
        }
        return sb.toString();
    }

    /**
     * BOをnewする。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param isKiban 基盤系かどうか
     * @return BOのオブジェクト
     */
    private <T extends BusinessObject> T newBo(Class<T> boClass, boolean isKiban) {
//        if (isKiban) {
            // 基盤系BOをnewする。
            return Deencapsulation.newInstance(boClass, DATA_SOURCE_NAME);
//        }
//        // 業務系BOをnewする。
//        return Deencapsulation.newInstance(boClass, tantoShiken);
    }

    /**
     * BOのリストからDBにデータを登録する。
     * @param <T> 型
     * @param boList BOのリスト
     */
    private <T extends BusinessObject> void createDbFromBoList(List<T> boList) {
        for (T bo : boList) {
            // テーブル更新履歴に書き込まないでDBに登録する。
            Deencapsulation.invoke(bo, "create");
        }
    }

//    /**
//     * ファイルからFileLineのリストを作成する。<br>
//     * ファイル中のデータに、ヘッダーがないときに使います。<br>
//     * ファイルの形式は、対応するFileLineクラスに合わせること。
//     * @param <T> 型
//     * @param fileLineClass FileLineのクラス
//     * @param path FileLineにセットするデータのパス名
//     * @return FileLineのリスト
//     */
//    protected <T extends FileLine> List<T> createFileLineListFromFile(
//            Class<T> fileLineClass, String path) {
//        return createFileLineListFromFile(fileLineClass, path, false);
//    }
//
//    /**
//     * ファイルからFileLineのリストを作成する。<br>
//     * ファイル中のデータに、ヘッダーがあるときに使います。<br>
//     * ファイルの形式は、対応するFileLineクラスに合わせること。
//     * @param <T> 型
//     * @param fileLineClass FileLineのクラス
//     * @param path FileLineにセットするデータのパス名
//     * @return FileLineのリスト
//     */
//    protected <T extends FileLine> List<T> createFileLineListFromFileWithHeader(
//            Class<T> fileLineClass, String path) {
//        return createFileLineListFromFile(fileLineClass, path, true);
//    }
//
//    /**
//     * ファイルからFileLineのリストを作成する。<br>
//     * ファイルの形式は、対応するFileLineクラスに合わせること。
//     * @param <T> 型
//     * @param fileLineClass FileLineのクラス
//     * @param path FileLineにセットするデータのパス名
//     * @param hasHeader ファイル中のデータのヘッダーの有無
//     * @return FileLineのリスト
//     */
//    private <T extends FileLine> List<T> createFileLineListFromFile(
//            Class<T> fileLineClass, String path, boolean hasHeader) {
//        List<T> fileLineList = new ArrayList<T>();
//        try {
//            List<String> testDataList = FileUtility.readLines(path,
//                    CodeDictionary.CHARACTER_SET_NAME_UTF8);
//            for (int i = 0; i < testDataList.size(); i++) {
//                if ((i == 0) && hasHeader) {
//                    // ヘッダー行は読み飛ばす。
//                    continue;
//                }
//                String testData = testDataList.get(i);
//                // FileLineをnewする。
//                T fileLine = Deencapsulation.newInstance(fileLineClass);
//                // データをFileLineの全フィールドにセットする。
//                fileLine.setAllField(testData);
//                fileLineList.add(fileLine);
//            }
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        return fileLineList;
//    }
//
//    /**
//     * ファイルの行数を取得する。<br>
//     * ファイル中のデータに、ヘッダーがないときに使います。
//     * @param path ファイルのパス名
//     * @return ファイルの行数
//     */
//    protected int getRowCountFromFile(String path) {
//        return getRowCountFromFile(path, false);
//    }
//
//    /**
//     * ファイルの行数を取得する。<br>
//     * ファイル中のデータに、ヘッダーがあるときに使います。
//     * @param path ファイルのパス名
//     * @return ファイルの行数
//     */
//    protected int getRowCountFromFileWithHeader(String path) {
//        return getRowCountFromFile(path, true);
//    }
//
//    /**
//     * ファイルの行数を取得する。
//     * @param path ファイルのパス名
//     * @param hasHeader ファイル中のデータのヘッダーの有無
//     * @return ファイルの行数
//     */
//    private int getRowCountFromFile(String path, boolean hasHeader) {
//        int rowCount;
//        try {
//            List<String> rowList = FileUtility.readLines(path, CodeDictionary.CHARACTER_SET_NAME_UTF8);
//            rowCount = rowList.size();
//            if (hasHeader) {
//                rowCount = rowCount - 1;
//            }
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        return rowCount;
//    }
//
//    /**
//     * ファイルの存在を確認する。
//     * @param path ファイルのパス名
//     */
//    protected static boolean existsFile(String path) {
//        return FileUtility.isFileExists(path);
//    }
//
//    /**
//     * ファイルを削除する。
//     * @param path ファイルのパス名
//     */
//    protected static void deleteFile(String path) {
//        if (existsFile(path)) {
//            try {
//                FileUtility.deleteFile(path);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//    }

    /**
     * ファイルをコピーする。<br>
     * コピー先パス名のファイルがすでに存在する場合は上書きされます。
     * @param srcPath コピー元パス名
     * @param dstPath コピー先パス名
     */
    protected static void copyFile(String srcPath, String dstPath) {
        FileChannel srcChannel = null;
        FileChannel dstChannel = null;
        try {
            srcChannel = new FileInputStream(srcPath).getChannel();
            dstChannel = new FileOutputStream(dstPath).getChannel();
            srcChannel.transferTo(0, srcChannel.size(), dstChannel);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (srcChannel != null) {
                    srcChannel.close();
                }
                if (dstChannel != null) {
                    dstChannel.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * ファイルを追加コピーする。<br>
     * コピー先パス名のファイルがすでに存在する場合は追加書きされます。
     * @param srcPath コピー元パス名
     * @param dstPath コピー先パス名
     */
    protected static void appendFile(String srcPath, String dstPath) {
        FileOutputStream out = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(srcPath);
            out = new FileOutputStream(dstPath, true);
            byte[] byteArray = new byte[256];
            int len = 0;
            while ((len = in.read(byteArray)) != -1) {
                out.write(byteArray, 0, len);
            }
            out.flush();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
//        FileChannel srcChannel = null;
//        FileChannel dstChannel = null;
//        try {
//            srcChannel = new FileInputStream(srcPath).getChannel();
//            dstChannel = new FileOutputStream(dstPath).getChannel();
//            //
////            dstChannel.position(dstChannel.size());
////            srcChannel.transferTo(0, srcChannel.size(), dstChannel);
//            //
////            dstChannel.transferFrom(srcChannel, dstChannel.size(), srcChannel.size());
//            dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        } finally {
//            try {
//                if (srcChannel != null) {
//                    srcChannel.close();
//                }
//                if (dstChannel != null) {
//                    dstChannel.close();
//                }
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
    }

    /**
     * 結合テスト用データの絶対パスを取得する。<br>
     * 結合テスト用データは、結合テストクラスパッケージ名の後ろに、".testdata"を<br>
     * 付加したパッケージに置くようにします。
     * @param testDataName 結合テスト用データファイル名
     */
    protected String getTestDataPath(String testDataName) {
//        String relativePath = "test/" + this.getClass().getCanonicalName().replace(".", "/") + suffix;
        String relativePath = "src/testintegration/" + this.getClass().getPackage().getName().replace(".", "/")
                + "/testdata/" + testDataName;
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }

    /**
     * 指定されたミリ秒数待つ。
     * @param millis 待機する時間（ミリ秒数）
     */
    protected void sleep(long millis) {
        try {
            java.lang.Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return the apBrowser
     */
    public static String getApBrowser() {
        if (apBrowser == null) {
            setApBrowser(DEFAULT_AP_BROWSER);
        }
        return apBrowser;
    }

    /**
     * @param aApBrowser the apBrowser to set
     */
    public static void setApBrowser(String aApBrowser) {
        apBrowser = aApBrowser;
    }

    /**
     * @return the apUrl
     */
    public static String getApUrl() {
        if (apUrl == null) {
            setApUrl(DEFAULT_AP_URL);
        }
        return apUrl;
    }

    /**
     * @param aApUrl the apUrl to set
     */
    public static void setApUrl(String aApUrl) {
        apUrl = aApUrl;
    }

    /**
     * @return the loginUser
     */
    public static String getLoginUser() {
        if (loginUser == null) {
            setLoginUser(DEFAULT_LOGIN_USER);
        }
        return loginUser;
    }

    /**
     * @param aLoginUser the loginUser to set
     */
    public static void setLoginUser(String aLoginUser) {
        loginUser = aLoginUser;
    }

    /**
     * @return the loginPassword
     */
    public static String getLoginPassword() {
        if (loginPassword == null) {
            setLoginPassword(DEFAULT_LOGIN_PASSWORD);
        }
        return loginPassword;
    }

    /**
     * @param aLoginPassword the loginPassword to set
     */
    public static void setLoginPassword(String aLoginPassword) {
        loginPassword = aLoginPassword;
    }

    /**
     * @return the dbUrl
     */
    public static String getDbUrl() {
        if (dbUrl == null) {
            setDbUrl(DEFAULT_DB_URL);
        }
        return dbUrl;
    }

    /**
     * @param aDbUrl the dbUrl to set
     */
    public static void setDbUrl(String aDbUrl) {
        dbUrl = aDbUrl;
    }

    /**
     * @return the dbUser
     */
    public static String getDbUser() {
        if (dbUser == null) {
            setDbUser(DEFAULT_DB_USER);
        }
        return dbUser;
    }

    /**
     * @param aDbUser the dbUser to set
     */
    public static void setDbUser(String aDbUser) {
        dbUser = aDbUser;
    }

    /**
     * @return the dbPassword
     */
    public static String getDbPassword() {
        if (dbPassword == null) {
            setDbPassword(DEFAULT_DB_PASSWORD);
        }
        return dbPassword;
    }

    /**
     * @param aDbPassword the dbPassword to set
     */
    public static void setDbPassword(String aDbPassword) {
        dbPassword = aDbPassword;
    }

    /**
     * テストクラス内で、指定した基盤系テーブルを条件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param tableName テーブル名
     * @param where ＷＨＥＲＥ句（Ｗｈｅｒｅから記述すること）
     * @return 主キーでソートされたBOのリスト
     */
    protected <T extends BusinessObject> List<T> findWhereForKiban(
            Class<T> boClass, String tableName, String where) {
        return findWhere(boClass, true, tableName, where);
    }

    /**
     * テストクラス内で、指定した基盤系テーブルを条件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param tableName テーブル名
     * @param where ＷＨＥＲＥ句（Ｗｈｅｒｅから記述すること）
     * @return 主キーでソートされたBOのリスト
     */
    protected <T extends BusinessObject> List<T> findWhereForGyomu(
            Class<T> boClass, String tableName, String where) {
        return findWhere(boClass, false, tableName, where);
    }

    /**
     * テストクラス内でデータを条件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param isKiban 基盤系かどうか
     * @param where where句
     * @return 主キーでソートされたBOのリスト
     */
    private <T extends BusinessObject> List<T> findWhere(Class<T> boClass,
            boolean isKiban, String where) {
        return findWhere(boClass, isKiban, getTableName(boClass), where);
    }

    /**
     * テストクラス内でデータを条件検索するときに使用する。
     * @param <T> 型
     * @param boClass BOのクラス
     * @param isKiban 基盤系かどうか
     * @param tableName テーブル名
     * @param where where句
     * @return 主キーでソートされたBOのリスト
     */
    private <T extends BusinessObject> List<T> findWhere(Class<T> boClass,
            boolean isKiban, String tableName, String where) {
        // BOのクラス名からDAOImplのクラス名を取得する。
        String daoImplClassName = getDaoImplClassName(boClass);
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM " + getSchemaName() + "." + tableName + " " + where + " "
                + getOrderByPk(boClass, isKiban);
        List<T> boList = new ArrayList<T>();
        try {
            con = rotatedCon;
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                T bo = newBo(boClass, isKiban);
                // ResultSetをBOの全フィールドにセットする。
                Deencapsulation.invoke(Deencapsulation.newInstance(daoImplClassName),
                        "setBoFromResultSet", bo, rs);
                boList.add(bo);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            close(stmt);
        }
        return boList;
    }

    /**
     * テストクラス内で、指定した基盤系テーブルを条件削除するときに使用する。
     * @param boClass BOのクラス
     * @param where ＷＨＥＲＥ句（Ｗｈｅｒｅから記述すること）
     */
    protected final void deleteWhereForKiban(final Class boClass, final String where) {
        deleteWhereForKiban(getTableName(boClass), boClass, where);
    }

    /**
     * テストクラス内で、指定した基盤系テーブルを条件削除するときに使用する。
     * @param tableName テーブル名
     * @param boClass BOのクラス
     * @param where ＷＨＥＲＥ句（Ｗｈｅｒｅから記述すること）
     */
    protected final void deleteWhereForKiban(final String tableName, final Class boClass, final String where) {
        final List<BusinessObject> boList = findWhereForKiban(boClass, tableName, where);
        for (BusinessObject bo : boList) {
            Deencapsulation.invoke(bo, "remove");
        }
    }

    /**
     * テーブルなどの繰り返し内のコンポーネントのIDを取得する<br>
     * （例）<br>
     * idTable　：　form:ichiran<br>
     * id　　　　：　tantoShikenCode<br>
     * index　　：　0<br>
     * 戻り値　：　form:ichiran:0:tantoShikenCode<br>
     * @param tableId テーブルのID
     * @param id コンポーネントのID
     * @param index インデックス（0?）
     * @return テーブル内のコンポーネントのID
     */
    public static String getIdForTable(String tableId, String id, int index) {
        return tableId + ":" + index + ":" + id;
    }

    /**
     * コンポーネントのvalueに値をセットする<br>
     * JavaScriptを使用して値をセットする。<br>
     * 制限を超える長さの文字列などの、通常のブラウザ操作ではセット不可能な値を、セットすることが可能。<br>
     * ただし、セレクトボックスなどの選択肢があるコンポーネントの場合は、選択肢にない値をセットすることはできない。<br>
     * @param id コンポーネントのID
     * @param value セットする値
     */
    protected void setValueByScript(String id, String value) {
        selenium.getEval("var win = this.page().getCurrentWindow().document.getElementById('" + id + "').value='" + value + "';");
    }

    /**
     * コンポーネントのvalueに値をセットする（selectコンポーネント用）<br>
     * JavaScriptを使用して、指定値の選択肢を追加、選択する。<br>
     * @param id コンポーネントのID
     * @param value セットする値
     */
    protected void setValueByScriptToSelect(String id, String value) {
        // 選択肢を追加する
        selenium.getEval("var sel = this.page().getCurrentWindow().document.getElementById('" + id + "'); sel.options[sel.length] = new Option('labelDummy','" + value + "');");
        // 追加した選択肢を選択する
        selenium.select(id, "value=" + value);
    }

    /**
     * BOのクラスからDAOImplのクラス名を取得する
     * @param <T>
     * @param boClass BOのクラス
     * @return DAOImplのクラス名
     */
    private static final <T extends BusinessObject> String getDaoImplClassName(final Class<T> boClass) {
        return new StringBuilder().append("jp.co.nii.sep.integration.db.").
                append(boClass.getSimpleName()).append("DaoImpl").toString();
    }

    /**
     * BOのクラスからテーブル名を取得する
     * @param <T>
     * @param boClass BOのクラス
     * @return テーブル名
     */
    protected static final <T extends BusinessObject> String getTableName(final Class<T> boClass) {
        final String generatedDaoClassName = new StringBuilder().append(boClass.getPackage().getName()).
                append(".Generated").append(boClass.getSimpleName()).append("Dao").toString();
        try {
            return Deencapsulation.getField(Class.forName(generatedDaoClassName), "TABLE_NAME");
        } catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
//// 以下、DBデータの検証用。
//    /** システム日付 yyyyMMdd */
//    protected final String today = DateTimeUtility.formatToStringyyyyMMddFromMilliSeconds(DateTimeUtility.getSystemDateTimeMills());
//    /** 期待値 システム日付 */
//    protected static final String EXP_VALUT_DATE_TODAY = "@today";
//    /** 期待値 検証しない */
//    protected static final String EXP_VALUT_NOT_ASSERT = "@no";
//    /** 期待値 検証しない */
//    protected static final int EXP_VALUT_NOT_ASSERT_INT = 999999999;
//    /** 期待値 改行 */
//    protected static final String EXP_VALUT_NEW_LINE = "@newline";
//    /** 期待値 タブ */
//    protected static final String EXP_VALUT_TAB = "@tab";
//
//    /**
//     * DBの結果を検証する
//     * 登録・更新項目が存在する。<br>
//     * 登録・更新項目の検証を特別な方法で行う。<br>
//     * @param <T>
//     * @param expResultList 期待値のリスト
//     * @param resultList 結果のリスト
//     * @param action 各テーブル毎の検証
//     */
//    protected final <T extends BusinessObject> void assertDb(final List<T> expResultList, final List<T> resultList, final AssertAction action) {
//        assertDb(expResultList, resultList, action, true, true);
//    }
//
//    /**
//     * DBの結果を検証する
//     * 登録・更新項目が存在する。<br>
//     * @param <T>
//     * @param expResultList 期待値のリスト
//     * @param resultList 結果のリスト
//     * @param action 各テーブル毎の検証
//     * @param customize 登録・更新項目の検証を特別な方法で行うか。trueの場合は特別、falseの場合は普通に検証する。
//     */
//    protected final <T extends BusinessObject> void assertDb(final List<T> expResultList, final List<T> resultList, final AssertAction action, boolean customize) {
//        assertDb(expResultList, resultList, action, customize, true);
//    }
//
//    /**
//     * DBの結果を検証する
//     * @param <T>
//     * @param expResultList 期待値のリスト
//     * @param resultList 結果のリスト
//     * @param action 各テーブル毎の検証
//     * @param customize 登録・更新項目の検証を特別な方法で行うか。trueの場合は特別、falseの場合は普通に検証する。
//     * @param existsTorokuKoshin 登録・更新項目が存在するか。trueの場合は登録・更新項目を検証する、falseの場合は検証しない。
//     */
//    protected final <T extends BusinessObject> void assertDb(final List<T> expResultList, final List<T> resultList, final AssertAction action, boolean customize, boolean existsTorokuKoshin) {
//        // 件数
//        final int expResultCount = expResultList.size();
//        final int resultCount = resultList.size();
//        assertEquals(expResultCount, resultCount);
//        // データ
//        for (int i = 0; i < expResultCount; i++) {
//            final BusinessObject expResultBo = expResultList.get(i);
//            final BusinessObject resultBo = resultList.get(i);
//            final String assertInfo = getAssertMessage(i, expResultBo.getPrimaryKey(), resultBo.getPrimaryKey());
//            // 各テーブル毎の項目の検証
//            action.executeAssert(assertInfo, expResultBo, resultBo);
//            // 登録、更新項目の検証
//            if (existsTorokuKoshin) {
//                if (customize) {
//                    assertTorokuKoshinInfoCustomize(assertInfo, expResultBo, resultBo);
//                } else {
//                    assertTorokuKoshinInfo(assertInfo, expResultBo, resultBo);
//                }
//            }
//        }
//    }
//
//    /**
//     * DBの結果を検証する
//     * @param <T>
//     * @param boClass BOのクラス
//     * @param isKiban 基盤系の場合、true。業務系の場合、false;
//     * @param suffix 期待値ファイルの接尾語
//     * @param customize 登録・更新項目の検証を特別な方法で行うか。trueの場合は特別、falseの場合は普通に検証する。
//     * @param existsTorokuKoshin 登録・更新項目が存在するか。trueの場合は登録・更新項目を検証する、falseの場合は検証しない。
//     */
//    protected final <T extends BusinessObject> void assertDb(final Class<T> boClass, final boolean isKiban, final String suffix, boolean customize, boolean existsTorokuKoshin) {
//        assertDb(boClass, getTableName(boClass), isKiban, suffix, customize, existsTorokuKoshin);
//    }
//
//    /**
//     * DBの結果を検証する
//     * @param <T>
//     * @param boClass BOのクラス
//     * @param tableName テーブル名
//     * @param isKiban 基盤系の場合、true。業務系の場合、false;
//     * @param suffix 期待値ファイルの接尾語
//     * @param customize 登録・更新項目の検証を特別な方法で行うか。trueの場合は特別、falseの場合は普通に検証する。
//     * @param existsTorokuKoshin 登録・更新項目が存在するか。trueの場合は登録・更新項目を検証する、falseの場合は検証しない。
//     */
//    protected final <T extends BusinessObject> void assertDb(final Class<T> boClass, final String tableName, final boolean isKiban, final String suffix, boolean customize, boolean existsTorokuKoshin) {
//        List<T> expResultBoList;
//        List<T> resultBoList;
//        if (isKiban) {
//            expResultBoList = createBoListForKibanFromFileWithHeaderBySuffix(boClass, suffix);
////            resultBoList = findAllForKiban(boClass, tableName);
//            resultBoList = findAllForKiban(boClass);
//        } else {
//            expResultBoList = createBoListForGyomuFromFileWithHeaderBySuffix(boClass, suffix);
//            resultBoList = findAllForGyomu(boClass, tableName);
//        }
//        final String[] getterNameArray = getBoGetterName(boClass);
//        assertDb(expResultBoList, resultBoList, new AssertAction() {
//
//            @Override
//            public void executeAssert(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo) {
//                assertBo(assertMessage, getterNameArray, expResultBo, resultBo, false);
//            }
//        }, customize, existsTorokuKoshin);
//    }
//
//    /**
//     * DBの結果を検証する
//     * @param <T>
//     * @param boClass BOのクラス
//     * @param isKiban 基盤系の場合、true。業務系の場合、false;
//     * @param suffix 期待値ファイルの接尾語
//     * @param customize 登録・更新項目の検証を特別な方法で行うか。trueの場合は特別、falseの場合は普通に検証する。
//     * @param existsTorokuKoshin 登録・更新項目が存在するか。trueの場合は登録・更新項目を検証する、falseの場合は検証しない。
//     * @param customizeColumnIndexList 検証を特別の仕様で行う項目のindexのリスト
//     */
//    protected final <T extends BusinessObject> void assertDb(final Class<T> boClass, final boolean isKiban, final String suffix, boolean customize, boolean existsTorokuKoshin, final List<Integer> customizeColumnIndexList) {
//        assertDb(boClass, getTableName(boClass), isKiban, suffix, customize, existsTorokuKoshin, customizeColumnIndexList);
//    }
//
//    /**
//     * DBの結果を検証する
//     * @param <T>
//     * @param boClass BOのクラス
//     * @param tableName テーブル名
//     * @param isKiban 基盤系の場合、true。業務系の場合、false;
//     * @param suffix 期待値ファイルの接尾語
//     * @param customize 登録・更新項目の検証を特別な方法で行うか。trueの場合は特別、falseの場合は普通に検証する。
//     * @param existsTorokuKoshin 登録・更新項目が存在するか。trueの場合は登録・更新項目を検証する、falseの場合は検証しない。
//     * @param customizeColumnIndexList 検証を特別の仕様で行う項目のindexのリスト
//     */
//    protected final <T extends BusinessObject> void assertDb(final Class<T> boClass, final String tableName, final boolean isKiban, final String suffix, boolean customize, boolean existsTorokuKoshin, final List<Integer> customizeColumnIndexList) {
//        List<T> expResultBoList;
//        List<T> resultBoList;
//        if (isKiban) {
//            expResultBoList = createBoListForKibanFromFileWithHeaderBySuffix(boClass, suffix);
////            resultBoList = findAllForKiban(boClass, tableName);
//            resultBoList = findAllForKiban(boClass);
//        } else {
//            expResultBoList = createBoListForGyomuFromFileWithHeaderBySuffix(boClass, suffix);
//            resultBoList = findAllForGyomu(boClass, tableName);
//        }
//        final String[] getterNameArray = getBoGetterName(boClass);
//        assertDb(expResultBoList, resultBoList, new AssertAction() {
//
//            @Override
//            public void executeAssert(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo) {
//                assertBoCustomize(assertMessage, getterNameArray, expResultBo, resultBo, false, customizeColumnIndexList);
//            }
//        }, customize, existsTorokuKoshin);
//    }
//
//    /**
//     * BOを検証する<br>
//     * 登録・更新項目も検証する。
//     * @param <T>
//     * @param assertMessage 検証メッセージ
//     * @param boClass BOのクラス
//     * @param expResultBo 期待値BO
//     * @param resultBo 結果BO
//     */
//    protected final <T extends BusinessObject> void assertBo(final String assertMessage, final Class<T> boClass, final BusinessObject expResultBo, final BusinessObject resultBo) {
//        assertBo(assertMessage, boClass, expResultBo, resultBo, true);
//    }
//
//    /**
//     * BOを検証する
//     * @param <T>
//     * @param assertMessage 検証メッセージ
//     * @param boClass BOのクラス
//     * @param expResultBo 期待値BO
//     * @param resultBo 結果BO
//     * @param assertTorokuKoshinInfo 登録・更新項目を検証するか。検証する場合、true。
//     */
//    protected final <T extends BusinessObject> void assertBo(final String assertMessage, final Class<T> boClass, final BusinessObject expResultBo, final BusinessObject resultBo, final boolean assertTorokuKoshinInfo) {
//        final String[] getterNameArray = getBoGetterName(boClass);
//        assertBo(assertMessage, getterNameArray, expResultBo, resultBo, assertTorokuKoshinInfo);
//    }
//
//    /**
//     * BOを検証する
//     * @param <T>
//     * @param assertMessage 検証メッセージ
//     * @param getterNameArray BOのgetterメソッド名の配列
//     * @param expResultBo 期待値BO
//     * @param resultBo 結果BO
//     * @param assertTorokuKoshinInfo 登録・更新項目を検証するか。検証する場合、true。
//     */
//    protected final <T extends BusinessObject> void assertBo(final String assertMessage, final String[] getterNameArray, final BusinessObject expResultBo, final BusinessObject resultBo, final boolean assertTorokuKoshinInfo) {
//        for (String getterName : getterNameArray) {
//            if (!assertTorokuKoshinInfo) {
//                if ("getTorokuDate".equals(getterName)) {
//                    break;
//                }
//            }
//            assertEquals(assertMessage + ":" + getterName,
//                    Deencapsulation.invoke(expResultBo, getterName),
//                    Deencapsulation.invoke(resultBo, getterName));
//        }
//    }
//
//    /**
//     * BOを検証する
//     * @param <T>
//     * @param assertMessage 検証メッセージ
//     * @param boClass BOのクラス
//     * @param expResultBo 期待値BO
//     * @param resultBo 結果BO
//     * @param assertTorokuKoshinInfo 登録・更新項目を検証するか。検証する場合、true。
//     * @param customizeColumnIndexList 検証を特別の仕様で行う項目のindexのリスト
//     */
//    protected final <T extends BusinessObject> void assertBoCustomize(final String assertMessage, final Class<T> boClass, final BusinessObject expResultBo, final BusinessObject resultBo, final boolean assertTorokuKoshinInfo, final List<Integer> customizeColumnIndexList) {
//        final String[] getterNameArray = getBoGetterName(boClass);
//        assertBoCustomize(assertMessage, getterNameArray, expResultBo, resultBo, assertTorokuKoshinInfo, customizeColumnIndexList);
//    }
//
//    /**
//     * BOを検証する
//     * @param <T>
//     * @param assertMessage 検証メッセージ
//     * @param getterNameArray BOのgetterメソッド名の配列
//     * @param expResultBo 期待値BO
//     * @param resultBo 結果BO
//     * @param assertTorokuKoshinInfo 登録・更新項目を検証するか。検証する場合、true。
//     * @param customizeColumnIndexList 検証を特別の仕様で行う項目のindexのリスト
//     */
//    protected final <T extends BusinessObject> void assertBoCustomize(final String assertMessage, final String[] getterNameArray, final BusinessObject expResultBo, final BusinessObject resultBo, final boolean assertTorokuKoshinInfo, final List<Integer> customizeColumnIndexList) {
//        if (customizeColumnIndexList == null || customizeColumnIndexList.isEmpty()) {
//            assertBo(assertMessage, getterNameArray, expResultBo, resultBo, assertTorokuKoshinInfo);
//            return;
//        }
//        int index = 0;
//        for (String getterName : getterNameArray) {
//            if (!assertTorokuKoshinInfo) {
//                if ("getTorokuDate".equals(getterName)) {
//                    break;
//                }
//            }
//            if (customizeColumnIndexList.contains(index)) {
//                assertColumnValueCustomize(assertMessage + ":" + getterName,
//                        Deencapsulation.invoke(expResultBo, getterName),
//                        Deencapsulation.invoke(resultBo, getterName));
//            } else {
//                assertEquals(assertMessage + ":" + getterName,
//                        Deencapsulation.invoke(expResultBo, getterName),
//                        Deencapsulation.invoke(resultBo, getterName));
//            }
//            index++;
//        }
//    }
//
//    /**
//     * BOを検証する
//     * @param <T>
//     * @param assertMessage 検証メッセージ
//     * @param boClass BOのクラス
//     * @param expResultBo 期待値BO
//     * @param resultBo 結果BO
//     * @param assertTorokuKoshinInfo 登録・更新項目を検証するか。検証する場合、true。
//     */
//    protected final <T extends BusinessObject> void assertBoCustomize(final String assertMessage, final Class<T> boClass, final BusinessObject expResultBo, final BusinessObject resultBo, final boolean assertTorokuKoshinInfo) {
//        final String[] getterNameArray = getBoGetterName(boClass);
//        assertBoCustomize(assertMessage, getterNameArray, expResultBo, resultBo, assertTorokuKoshinInfo);
//    }
//
//    /**
//     * BOを検証する
//     * @param <T>
//     * @param assertMessage 検証メッセージ
//     * @param getterNameArray BOのgetterメソッド名の配列
//     * @param expResultBo 期待値BO
//     * @param resultBo 結果BO
//     * @param assertTorokuKoshinInfo 登録・更新項目を検証するか。検証する場合、true。
//     */
//    protected final <T extends BusinessObject> void assertBoCustomize(final String assertMessage, final String[] getterNameArray, final BusinessObject expResultBo, final BusinessObject resultBo, final boolean assertTorokuKoshinInfo) {
//        for (String getterName : getterNameArray) {
//            if (!assertTorokuKoshinInfo) {
//                if ("getTorokuDate".equals(getterName)) {
//                    break;
//                }
//            }
//            assertColumnValueCustomize(assertMessage + ":" + getterName,
//                    Deencapsulation.invoke(expResultBo, getterName),
//                    Deencapsulation.invoke(resultBo, getterName));
//        }
//    }
//
//    /**
//     * 各テーブル毎の検証
//     */
//    protected static interface AssertAction {
//
//        /**
//         * 検証を行う
//         * @param assertMessage 検証のメッセージ
//         * @param expResultBo 期待値Bo
//         * @param resultBo 結果Bo
//         */
//        void executeAssert(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo);
////        void executeAssert(final int i, final String expResultKey, final String resultKey, final BusinessObject expResultBo, final BusinessObject resultBo);
//    }
//
//    /**
//     * 登録・更新項目を検証する
//     * @param assertMessage 検証のメッセージ
//     * @param expResultBo 期待値Bo
//     * @param resultBo 結果Bo
//     */
//    protected final void assertTorokuKoshinInfo(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo) {
//        assertEquals(assertMessage, getTorokuDate(expResultBo), getTorokuDate(resultBo));
//        assertEquals(assertMessage, getTorokuTime(expResultBo), getTorokuTime(resultBo));
//        assertEquals(assertMessage, getTorokuUserId(expResultBo), getTorokuUserId(resultBo));
//        assertEquals(assertMessage, getKoshinDate(expResultBo), getKoshinDate(resultBo));
//        assertEquals(assertMessage, getKoshinTime(expResultBo), getKoshinTime(resultBo));
//        assertEquals(assertMessage, getKoshinUserId(expResultBo), getKoshinUserId(resultBo));
//    }
//
//    /**
//     * 登録・更新項目を検証する<br>
//     * 以下に示す特別な仕様で検証する。<br>
//     * ・"@today"の場合、システム日付で検証する。<br>
//     * ・"@no"の場合、検証をしない。<br>
//     * @param assertMessage 検証のメッセージ
//     * @param expResultBo 期待値Bo
//     * @param resultBo 結果Bo
//     */
//    protected final void assertTorokuKoshinInfoCustomize(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo) {
//        assertColumnValueCustomize(assertMessage, getTorokuDate(expResultBo), getTorokuDate(resultBo));
//        assertColumnValueCustomize(assertMessage, getTorokuTime(expResultBo), getTorokuTime(resultBo));
//        assertEquals(assertMessage, getTorokuUserId(expResultBo), getTorokuUserId(resultBo));
//        assertColumnValueCustomize(assertMessage, getKoshinDate(expResultBo), getKoshinDate(resultBo));
//        assertColumnValueCustomize(assertMessage, getKoshinTime(expResultBo), getKoshinTime(resultBo));
//        assertEquals(assertMessage, getKoshinUserId(expResultBo), getKoshinUserId(resultBo));
//    }
//
//    /**
//     * スレッドで、登録・更新項目を検証する。
//     * @param assertMessage 検証のメッセージ
//     * @param expThread 期待値スレッド
//     * @param resultBo 結果Bo
//     */
//    protected final void assertTorokuKoshinInfoThread(final String assertMessage, final Thread expThread, final BusinessObject resultBo) {
//        assertEquals(assertMessage, expThread.getHasseiDate(), getTorokuDate(resultBo));
//        assertEquals(assertMessage, expThread.getKaishiTime(), getTorokuTime(resultBo));
//        assertEquals(assertMessage, expThread.getUserId(), getTorokuUserId(resultBo));
//        assertEquals(assertMessage, expThread.getHasseiDate(), getKoshinDate(resultBo));
//        assertEquals(assertMessage, expThread.getKaishiTime(), getKoshinTime(resultBo));
//        assertEquals(assertMessage, expThread.getUserId(), getKoshinUserId(resultBo));
//    }
//
//    /**
//     * 登録項目を検証する。<br>
//     * @param assertMessage 検証のメッセージ
//     * @param expResultBo 期待値Bo
//     * @param resultBo 結果Bo
//     */
//    protected final void assertTorokuInfo(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo) {
//        assertEquals(assertMessage, getTorokuDate(expResultBo), getTorokuDate(resultBo));
//        assertEquals(assertMessage, getTorokuTime(expResultBo), getTorokuTime(resultBo));
//        assertEquals(assertMessage, getTorokuUserId(expResultBo), getTorokuUserId(resultBo));
//    }
//
//    /**
//     * 登録項目を検証する<br>
//     * 以下に示す特別な仕様で検証する。<br>
//     * ・"@today"の場合、システム日付で検証する。<br>
//     * ・"@no"の場合、検証をしない。<br>
//     * @param assertMessage 検証のメッセージ
//     * @param expResultBo 期待値Bo
//     * @param resultBo 結果Bo
//     */
//    protected final void assertTorokuInfoCustomize(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo) {
//        assertColumnValueCustomize(assertMessage, getTorokuDate(expResultBo), getTorokuDate(resultBo));
//        assertColumnValueCustomize(assertMessage, getTorokuTime(expResultBo), getTorokuTime(resultBo));
//        assertEquals(assertMessage, getTorokuUserId(expResultBo), getTorokuUserId(resultBo));
//    }
//
//    /**
//     * スレッドで、登録項目を検証する。
//     * @param assertMessage 検証のメッセージ
//     * @param expThread 期待値スレッド
//     * @param resultBo 結果Bo
//     */
//    protected final void assertTorokuInfoThread(final String assertMessage, final Thread expThread, final BusinessObject resultBo) {
//        assertEquals(assertMessage, expThread.getHasseiDate(), getTorokuDate(resultBo));
//        assertEquals(assertMessage, expThread.getKaishiTime(), getTorokuTime(resultBo));
//        assertEquals(assertMessage, expThread.getUserId(), getTorokuUserId(resultBo));
//    }
//
//    /**
//     * 更新項目を検証する。<br>
//     * @param assertMessage 検証のメッセージ
//     * @param expResultBo 期待値Bo
//     * @param resultBo 結果Bo
//     */
//    protected final void assertKoshinInfo(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo) {
//        assertEquals(assertMessage, getKoshinDate(expResultBo), getKoshinDate(resultBo));
//        assertEquals(assertMessage, getKoshinTime(expResultBo), getKoshinTime(resultBo));
//        assertEquals(assertMessage, getKoshinUserId(expResultBo), getKoshinUserId(resultBo));
//    }
//
//    /**
//     * 更新項目を検証する<br>
//     * 以下に示す特別な仕様で検証する。<br>
//     * ・"@today"の場合、システム日付で検証する。<br>
//     * ・"@no"の場合、検証をしない。<br>
//     * @param assertMessage 検証のメッセージ
//     * @param expResultBo 期待値Bo
//     * @param resultBo 結果Bo
//     */
//    protected final void assertKoshinInfoCustomize(final String assertMessage, final BusinessObject expResultBo, final BusinessObject resultBo) {
//        assertColumnValueCustomize(assertMessage, getKoshinDate(expResultBo), getKoshinDate(resultBo));
//        assertColumnValueCustomize(assertMessage, getKoshinTime(expResultBo), getKoshinTime(resultBo));
//        assertEquals(assertMessage, getKoshinUserId(expResultBo), getKoshinUserId(resultBo));
//    }
//
//    /**
//     * スレッドで、更新項目を検証する。
//     * @param assertMessage 検証のメッセージ
//     * @param expThread 期待値スレッド
//     * @param resultBo 結果Bo
//     */
//    protected final void assertKoshinInfoThread(final String assertMessage, final Thread expThread, final BusinessObject resultBo) {
//        assertEquals(assertMessage, expThread.getHasseiDate(), getKoshinDate(resultBo));
//        assertEquals(assertMessage, expThread.getKaishiTime(), getKoshinTime(resultBo));
//        assertEquals(assertMessage, expThread.getUserId(), getKoshinUserId(resultBo));
//    }
//
//    /**
//     * 検証する<br>
//     * 以下に示す特別な仕様で検証する。<br>
//     * ・"@today"の場合、システム日付で検証する。<br>
//     * ・"@no"の場合、検証をしない。<br>
//     * @param assertMessage 検証のメッセージ
//     * @param expResult 期待値
//     * @param result 結果値
//     */
//    protected final void assertColumnValueCustomize(final String assertMessage, String expResult, final String result) {
//        if (EXP_VALUT_NOT_ASSERT.equals(expResult)) {
//            return;
//        }
//        if (EXP_VALUT_DATE_TODAY.equals(expResult)) {
//            expResult = today;
//        }
//        assertEquals(assertMessage, expResult, result);
//    }
//
//    /**
//     * 検証する<br>
//     * 以下に示す特別な仕様で検証する。<br>
//     * ・"@today"の場合、システム日付で検証する。<br>
//     * ・"@today"を含む場合、"@today"をシステム日付に置き換えて検証する。<br>
//     * ・"@no"の場合、検証をしない。<br>
//     * ・"@no"が先頭／末尾の場合、"@no"以外のみを検証をする。（@noは1箇所のみ可能）<br>
//     * ・型がint、かつ、"999999999"（9*9桁）の場合、検証をしない。<br>
//     * ・"@newline"を含む場合、"@newline"を改行に置き換えて検証する。<br>
//     * ・"@tab"を含む場合、"@tab"をタブに置き換えて検証する。<br>
//     * @param assertMessage 検証のメッセージ
//     * @param expResult 期待値
//     * @param result 結果値
//     */
//    protected final void assertColumnValueCustomize(final String assertMessage, Object expResult, final Object result) {
//        // "@no"の場合、検証をしない。
//        if (EXP_VALUT_NOT_ASSERT.equals(expResult)) {
//            return;
//        }
//        // 型がint、かつ、"999999999"（9*9桁）の場合、検証をしない。
//        if ((expResult instanceof Integer) && EXP_VALUT_NOT_ASSERT_INT == ((Integer) expResult).intValue()) {
//            return;
//        }
//
//        boolean startsWith = false;
//        boolean endsWith = false;
//
//        // "@today"の場合、システム日付で検証する。
//        if (EXP_VALUT_DATE_TODAY.equals(expResult)) {
//            expResult = today;
//        } else {
//            if (expResult instanceof String) {
//                String expResultString = (String) expResult;
//                int pos = -1;
//
//                // "@today"を含む場合、"@today"をシステム日付に置き換えて検証する。
//                pos = expResultString.indexOf(EXP_VALUT_DATE_TODAY);
//                if (pos != -1) {
//                    expResultString = expResultString.replaceAll(EXP_VALUT_DATE_TODAY, today);
//                }
//
//                // "@newline"を含む場合、"@newline"を改行に置き換えて検証する。
//                pos = expResultString.indexOf(EXP_VALUT_NEW_LINE);
//                if (pos != -1) {
//                    expResultString = expResultString.replaceAll(EXP_VALUT_NEW_LINE, Constants.NEW_LINE);
//                }
//
//                // "@tab"を含む場合、"@tab"をタブに置き換えて検証する。
//                pos = expResultString.indexOf(EXP_VALUT_TAB);
//                if (pos != -1) {
//                    expResultString = expResultString.replaceAll(EXP_VALUT_TAB, "\t");
//                }
//
//                // "@no"が先頭／末尾の場合、"@no"以外のみを検証をする。（@noは1箇所のみ可能）
//                pos = expResultString.indexOf(EXP_VALUT_NOT_ASSERT);
//                if (pos != -1) {
//                    if (expResultString.startsWith(EXP_VALUT_NOT_ASSERT)) {
//                        expResultString = expResultString.replaceAll(EXP_VALUT_NOT_ASSERT, "");
//                        endsWith = true;
//                    } else if (expResultString.endsWith(EXP_VALUT_NOT_ASSERT)) {
//                        expResultString = expResultString.replaceAll(EXP_VALUT_NOT_ASSERT, "");
//                        startsWith = true;
//                    }
//                }
//
//                expResult = expResultString;
//            }
//        }
//
//        if (startsWith) {
//            assertTrue(assertMessage, ((String) result).startsWith((String) expResult));
//        } else if (endsWith) {
//            assertTrue(assertMessage, ((String) result).endsWith((String) expResult));
//        } else {
//            assertEquals(assertMessage, expResult, result);
//        }
//    }
//
//    /**
//     * DBの検証用のメッセージを取得する
//     * @param i 行のindex（0?）
//     * @param expResultKey 期待値Boのキー文字列
//     * @param resultKey 結果Boのキー文字列
//     * @return DBの検証用のメッセージ
//     */
//    protected static final String getAssertMessage(final int i, final String expResultKey, final String resultKey) {
//        return new StringBuilder().append((i + 1)).append("件目 期待値のキー:").
//                append(expResultKey).append(" 結果値のキー:").append(resultKey).
//                toString();
//    }
//
//    /**
//     * 登録日付を取得する
//     * @param bo Bo
//     * @return 登録日付
//     */
//    protected final String getTorokuDate(final BusinessObject bo) {
//        return Deencapsulation.invoke(bo, "getTorokuDate");
//    }
//
//    /**
//     * 登録時刻を取得する
//     * @param bo Bo
//     * @return 登録時刻
//     */
//    protected final String getTorokuTime(final BusinessObject bo) {
//        return Deencapsulation.invoke(bo, "getTorokuTime");
//    }
//
//    /**
//     * 登録ユーザーＩＤを取得する
//     * @param bo Bo
//     * @return 登録ユーザーＩＤ
//     */
//    protected final String getTorokuUserId(final BusinessObject bo) {
//        return Deencapsulation.invoke(bo, "getTorokuUserId");
//    }
//
//    /**
//     * 更新日付を取得する
//     * @param bo Bo
//     * @return 更新日付
//     */
//    protected final String getKoshinDate(final BusinessObject bo) {
//        return Deencapsulation.invoke(bo, "getKoshinDate");
//    }
//
//    /**
//     * 更新時刻を取得する
//     * @param bo Bo
//     * @return 更新時刻
//     */
//    protected final String getKoshinTime(final BusinessObject bo) {
//        return Deencapsulation.invoke(bo, "getKoshinTime");
//    }
//
//    /**
//     * 更新ユーザーＩＤを取得する
//     * @param bo Bo
//     * @return 更新ユーザーＩＤ
//     */
//    protected final String getKoshinUserId(final BusinessObject bo) {
//        return Deencapsulation.invoke(bo, "getKoshinUserId");
//    }
//
//    /**
//     * BOのgetterメソッド名を取得する。DBの項目に限る。
//     * @param <T>
//     * @param boClass BOのクラス
//     * @return BOのgetterメソッド名の配列
//     */
//    private static final <T extends BusinessObject> String[] getBoGetterName(final Class<T> boClass) {
//        final String[] columnNameArray = getDbColumnName(boClass);
//        final String[] methodNameArray = new String[columnNameArray.length];
//        for (int i = 0; i < columnNameArray.length; i++) {
//            methodNameArray[i] = getGetterNameFromFieldName(getFieldNameFromColumnName(columnNameArray[i]));
//        }
//        return methodNameArray;
//    }
//
//    /**
//     * BOのフィールド名を取得する。DBの項目に限る。
//     * @param <T>
//     * @param boClass BOのクラス
//     * @return BOのフィールド名の配列
//     */
//    private static final <T extends BusinessObject> String[] getBoFieldName(final Class<T> boClass) {
//        final String[] columnNameArray = getDbColumnName(boClass);
//        final String[] fieldNameArray = new String[columnNameArray.length];
//        for (int i = 0; i < columnNameArray.length; i++) {
//            fieldNameArray[i] = getFieldNameFromColumnName(columnNameArray[i]);
//        }
//        return fieldNameArray;
//    }
//
//    /**
//     * DBの項目名を取得する
//     * @param <T>
//     * @param boClass BOのクラス
//     * @return DBの項目名の配列
//     */
//    private static final <T extends BusinessObject> String[] getDbColumnName(final Class<T> boClass) {
//        String[] columnNameArray = new String[]{};
//        String daoImplClassName = getDaoImplClassName(boClass);
//        Class daoImplClass = null;
//        try {
//            daoImplClass = Class.forName(daoImplClassName);
//        } catch (ClassNotFoundException ex) {
//            throw new IllegalArgumentException(ex);
//        }
//        if (daoImplClass == null) {
//            return columnNameArray;
//        }
//        String fields = Deencapsulation.getField(daoImplClass, "FIELDS");
//        if (fields == null) {
//            return columnNameArray;
//        }
//        columnNameArray = fields.split(",");
//        return columnNameArray;
//    }
//
//    /**
//     * BOのフィールド名からgetterメソッド名を取得する
//     * @param fieldName BOのフィールド名
//     * @return BOのgetterメソッド名
//     */
//    private static final String getGetterNameFromFieldName(final String fieldName) {
//        final StringBuilder sb = new StringBuilder();
//        sb.append("get").
//                append(Character.toUpperCase(fieldName.charAt(0))).
//                append(fieldName.substring(1, fieldName.length()));
//        return sb.toString();
//    }
//
//    /**
//     * DB項目名からBOのフィールド名を取得する
//     * @param columnName DB項目名
//     * @return BOのフィールド名
//     */
//    private static final String getFieldNameFromColumnName(final String columnName) {
//        final StringBuilder sb = new StringBuilder();
//        boolean kugiri = false;
//        for (char c : columnName.toCharArray()) {
//            if (c == '_') {
//                kugiri = true;
//            } else if (kugiri) {
//                sb.append(c);
//                kugiri = false;
//            } else {
//                sb.append(Character.toLowerCase(c));
//            }
//        }
//        return sb.toString();
//    }
}
