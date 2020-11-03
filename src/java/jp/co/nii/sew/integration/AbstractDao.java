package jp.co.nii.sew.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import jp.co.nii.sew.business.domain.DataAccessException;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.PropertyUtility;
import jp.co.nii.sew.utility.ServiceLocator;
import jp.co.nii.sew.utility.ServiceLocatorException;

/**
 * タイトル:AbstractDao.
 * <p/>
 * 説明:DAO抽象クラス
 * <p/>
 * 著作権:Copyright (c) 2014
 * <p/>
 * 会社名:中央職業能力開発協会
 */
public abstract class AbstractDao {

    /** ログに出力するクラス名 */
    protected static String CLASS_NAME;
    /** 業務コード */
    private static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
    /** スキーマ名 */
    private static final String SCHEMA_NAME = PropertyUtility.getProperty(BUSINESS_CODE + "schema_name");

    /** データソース名 */
    private String jndiNameJdbcDatasource;
    /** コネクション取得の最大試行回数 */
    private static final int RETRY_MAX = 5;
    /** 接続プール取得Exception */
    private static final String RESOURCE_EXCEPTION_NAME = "javax.resource.spi.ResourceAllocationException";
    /** 接続プール取得Exception */
    private static final String POOLING_EXCEPTION_NAME = "com.sun.appserv.connectors.internal.api.PoolingException";
    
    /** データベース文字コードUTF8 */
    private static final String DB_CHARSET_UTF8 = "UTF8";
    /** 暗号化パスワード */
    private static final String ENCRYPT_PASSWORD = PropertyUtility.getProperty(BUSINESS_CODE + "encrypt_password");

    /**
     * コンストラクタ
     * @param datasource データソース
     */
    public AbstractDao(String datasource) {
        CLASS_NAME = this.getClass().getName();
        jndiNameJdbcDatasource = datasource;
    }

    /**
     * スキーマ名を取得する
     * @return スキーマ名
     */
    public static String getSchemaName() {
        return SCHEMA_NAME;
    }

    /**
     * DB接続を取得する
     * @return DB接続
     */
    protected final Connection getConnection() {

        Connection con = null;
        int retry = 1;

        // ServiceLocatorの取得
        final ServiceLocator serviceLocator = ServiceLocator.getInstance();

        try {

            //プールからコネクションを取得
            while (retry == 1 || (retry <= RETRY_MAX && con == null)) {
                try {
                    // データソースの取得
                    final DataSource dataSource = serviceLocator.getDataSource(jndiNameJdbcDatasource);
                    // Connectionの取得
                    con = dataSource.getConnection();

                } catch (SQLException ex) {
                    // データソースを使って接続を取得すると、SQLExcetpion.getErrorCode() は 0を返した。
                    // 接続プール数MAX超を取得するために、Exeptionのクラス名で判断

                    if (RESOURCE_EXCEPTION_NAME.equals(ex.getCause().getClass().getName()) &&
                        POOLING_EXCEPTION_NAME.equals(ex.getCause().getCause().getClass().getName())) {
                            LogGenerate.debugOutput(CLASS_NAME + ".getConnection() SQLException no available connection " + retry);
                            //waitする時間はglassfish側の設定で行う（glassfishの設定でwait時間ゼロにできないため）
//                            try {
//                                LogGenerate.debugOutput(CLASS_NAME + ".getConnection() waiting.....");
//                                //1秒待ってからリトライ
//                                synchronized (this) {
//                                    wait(1000);
//                                }
//                            } catch (InterruptedException e) {
//                                LogGenerate.fatalOutput(CLASS_NAME + ".getConnection() can't wait");
//                            }
                    } else {
                        LogGenerate.fatalOutput(CLASS_NAME + ".getConnection() other SQLException " + ex.getErrorCode() + " " + ex.toString());
                        throw ex;
                    }
                }
                retry++;

            }

            // トランザクション分離レベルをデフォルトでTRANSACTION_READ_COMMITTEDに設定
            // サーバーの設定で行うことにする。
            // サーバーの設定で行ってコードでも行うとエラーになる。
            //con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            // 自動コミットはfalseに設定すると動かないので注意
            //con.setAutoCommit(false);
            // 自動コミットは明示的にtrueに設定しても動かないので注意
            //con.setAutoCommit(true);
            // 自動コミットは暗黙的に的にtrueにしておく

        } catch (ServiceLocatorException ex1) {

            LogGenerate.errWrite(CLASS_NAME, "getConnection()", ex1);
            throw ex1;

//	    log.fatal("データソースを取得できなかった。");
//
//	    // 単体テスト用のDB接続の取得
//	    try {
//		log.info("単体テスト用のDB接続取得を試行。");
//		Class.forName("org.postgresql.Driver");
//	    } catch (ClassNotFoundException ex2) {
//		log.fatal("org.postgresql.Driverが見つからなかった。");
//		throw ex2;
//	    }
//	    con = DriverManager.getConnection("jdbc:postgresql://sep-serv:5432/test", "postgres", "postgres");

        } catch (SQLException ex3) {

            LogGenerate.errWrite(CLASS_NAME, "getConnection()", ex3);
            throw new SQLStateSQLExceptionTranslater().translate("DB接続を取得中に例外発生。", ex3);

        } finally {

            if (con == null) {
                LogGenerate.errWrite("DB接続がnullだった。");
                throw new SQLStateSQLExceptionTranslater().translate("DB接続がnullだった。", null);
            } else {
//                LogGenerate.infoOutput("con=" + con.toString());
                return con;
            }
        }

    }

    /**
     * コネクション、ステートメントを閉じる。
     * @param con コネクション
     * @param stmt プリペアドステートメント
     */
    protected static final void close(final Connection con, final PreparedStatement stmt) {
        close(stmt);
        close(con);
    }

    /**
     * コネクション、ステートメント、リサルトセットを閉じる。
     * @param con コネクション
     * @param stmt プリペアドステートメント
     * @param rs リサルトセット
     */
    protected static final void close(final Connection con, final PreparedStatement stmt, final ResultSet rs) {
        close(stmt);
        close(con);
        close(rs);
    }

    /**
     * Connectionを閉じる。
     * @param con コネクション
     */
    private static final void close(final Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LogGenerate.infoOutput("ConnectionのCloseでエラーが発生した。");
            }
        }
    }

    /**
     * PreparedStatementを閉じる。
     * @param stmt プリペアドステートメント
     */
    private static final void close(final PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LogGenerate.infoOutput("PreparedStatementのCloseでエラーが発生した。");
            }
        }
    }

    /**
     * PreparedStatementを閉じる。
     * @param rs リサルトセット
     */
    private static final void close(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LogGenerate.infoOutput("ResultSetのCloseでエラーが発生した。");
            }
        }
    }

    /**
     * 保存のポイント名を設定する。
     * @param savepointName 保存のポイント名
     * @param ut 使用中のユーザートランザクション
     */
    public final void setSavepoint(final String savepointName, final UserTransaction ut) {
        Connection con = null;
        PreparedStatement stmt = null;
        final String sql = "SAVEPOINT " + savepointName;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            LogGenerate.debugOutput(getSql(stmt));
            stmt.execute();
        } catch (SQLException ex) {
            savepointExceptionHandler(ex, ut, stmt);
        } finally {
            close(con, stmt);
        }
    }

    /**
     * 保存のポイント名をリリースする。
     * 
     * @param savepointName 保存のポイント名
     * @param ut 使用中のユーザートランザクション
     */
    public final void releaseSavepoint(final String savepointName, final UserTransaction ut) {
        Connection con = null;
        PreparedStatement stmt = null;
        final String sql = "RELEASE SAVEPOINT " + savepointName;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            LogGenerate.debugOutput(getSql(stmt));
            stmt.execute();
        } catch (SQLException ex) {
            savepointExceptionHandler(ex, ut, stmt);
        } finally {
            close(con, stmt);
        }
    }

    /**
     * 保存のポイント名までロールバック。
     * 
     * @param savepointName 保存のポイント名
     * @param ut 使用中のユーザートランザクション
     */
    public final void rollbackToSavepoint(final String savepointName, final UserTransaction ut) {
        Connection con = null;
        PreparedStatement stmt = null;
        final String sql = "ROLLBACK TO SAVEPOINT " + savepointName;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            LogGenerate.debugOutput(getSql(stmt));
            stmt.execute();
        } catch (SQLException ex) {
            savepointExceptionHandler(ex, ut, stmt);
        } finally {
            close(con, stmt);
        }
    }

    /**
     * セーブポイント例外ハンドラー<br>
     * セーブポイントの操作時に発生したSQL例外のハンドラー
     * <br>
     * データはトランザクションABORT時にROLLBACKした状態になる。<br>
     * 但し、COMMIT でも ROLLBACKでも（結果は変わらないが）どちらかをやらないと<br>
     * DB処理ができない。<br>
     * 結果は変わらないがデータの状態に合わせてROLLBACKを呼ぶことにする。<br>
     * 利用側では、やり直すしかないのでここでROLLBACKするところまで行う。<br>
     * @param ex 発生したSQL例外
     * @param ut 使用中のユーザートランザクション
     * @param stmt 実行中のステートメント
     * @throws DataAccessException
     */
    private final void savepointExceptionHandler(final SQLException ex, final UserTransaction ut, final PreparedStatement stmt)
            throws DataAccessException {
        // データはトランザクションABORT時にROLLBACKした状態になる。
        // 但し、COMMIT でも ROLLBACKでも（結果は変わらないが）どちらかをやらないと
        // DB処理ができない。
        // 結果は変わらないがデータの状態に合わせてROLLBACKを呼ぶことにする。
        // 利用側では、やり直すしかないのでここでROLLBACKするところまで行う。
        if (ut != null) {
            TransactionUtility.rollback(ut);
        }
        throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
    }

    /**
     * SQL文を取得する<br>
     * PreparedStatementの場合は、引数?に値が設定された状態のSQL文
     * @param stmt SQLステートメント
     * @return SQL文
     */
    protected final String getSql(final Statement stmt) {
        if (stmt == null) {
            return "";
        } else {
            return stmt.toString();
        }
    }

//    /**
//     * 指定した文字列を暗号化するSQLコードを返す。（暗号化前文字コード：UTF8）<BR>
//     * @param  暗号化する文字列
//     * @return  暗号化SQLコード
//     */
//    protected static String getSQLForEncryptValueByUTF8(String value) {
//        String ret = "encrypt(convert_to('" + value + "','" + DB_CHARSET_UTF8 + "'), '"
//                + ENCRYPT_PASSWORD + "'::bytea, 'aes')";
//        return ret;
//    }
    /**
     * 暗号化SQLコードを返す。（暗号化前文字コード：UTF8）（バインド変数等に使用）<BR>
     * @param  暗号化する項目名
     * @return  暗号化SQLコード
     */
    protected static String getSQLForEncryptByUTF8(String fieldName) {
        String ret = getSchemaName() + "." + "encrypt(convert_to(" + fieldName + ",'" + DB_CHARSET_UTF8 + "'), '"
                + ENCRYPT_PASSWORD + "'::bytea, 'aes')";
        return ret;
    }

    /**
     * 暗号化SQLコードを返す。（暗号化前バイナリデータ）<BR>
     * @param  暗号化する項目名
     * @return  暗号化SQLコード
     */
    protected static String getSQLForEncryptByBynary(String fieldName) {
        String ret = getSchemaName() + "." + "encrypt(" + fieldName + ", '" + ENCRYPT_PASSWORD + "'::bytea, 'aes')";
        return ret;
    }

    /**
     * 復号SQLコードを返す。（復号後文字コード：UTF8）<BR>
     * @param  復号する項目名
     * @return  復号SQLコード
     */
    protected static String getSQLForDecryptByUTF8(String fieldName) {
        String ret = "convert_from(" + getSchemaName() + "." + "decrypt(" + fieldName + ", '" + ENCRYPT_PASSWORD
                + "'::bytea, 'aes'), '" + DB_CHARSET_UTF8 + "') AS " + fieldName;
        return ret;
    }
    
    /**
     * 復号SQLコードを返す。（復号後文字コード：UTF8）<BR>
     * @param  復号する項目名
     * @return  復号SQLコード
     */
    protected static String getSQLForDecryptByUTF8NotAs(String fieldName) {
        String ret = "convert_from(" + getSchemaName() + "." + "decrypt(" + fieldName + ", '" + ENCRYPT_PASSWORD
                + "'::bytea, 'aes'), '" + DB_CHARSET_UTF8 + "') ";
        return ret;
    }
    
    /**
     * 復号SQLコードを返す。（復号後文字コード：UTF8）<BR>
     * テーブル結合対応<BR>
     * @param  復号する項目名
     * @return  復号SQLコード
     */
    protected static String getSQLForDecryptJoinByUTF8(String tableName,String fieldName) {
        String ret = "convert_from(" + getSchemaName() + "." + "decrypt(" + tableName+"."+ fieldName + ", '" + ENCRYPT_PASSWORD
                + "'::bytea, 'aes'), '" + DB_CHARSET_UTF8 + "') AS " + fieldName;
        return ret;
    }

    /**
     * 復号SQLコードを返す。（暗号化前バイナリデータ）<BR>
     * @param  復号する項目名
     * @return  復号SQLコード
     */
    protected static String getSQLForDecryptByBynary(String fieldName) {
        String ret = getSchemaName() + "." + "decrypt(" + fieldName + ", '" + ENCRYPT_PASSWORD + "'::bytea, 'aes') AS " + fieldName;
        return ret;
    }
}
