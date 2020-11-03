package jp.co.nii.sew.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * サービスロケーター<br>
 * 以下のオブジェクトの検索とキャッシュを行う<br>
 * ・JDBC DataSource<br>
 * ・UserTransaction
 * 以下は、SEPでは不要<br>
 * ・EJBローカルホームオブジェクト<br>
 * ・EJBリモートホームオブジェクト<br>
 * ・Queue<br>
 * ・QueueConnectionFactory<br>
 *
 * @author n-machida
 */
public class ServiceLocator {

    /** ログ */
    private static Log log = LogFactory.getLog(ServiceLocator.class);
    /** このクラスのシングルトンインスタンス */
    private static ServiceLocator _instance;
    /** 初期コンテキスト（システム環境設定のルートのようなもの） */
    private InitialContext initialContext;
    /** サービスのキャッシュ */
    private Map<String, Object> cache;
    /** JDBCデータソースのJNDI名  */
//    public final static String JNDI_NAME_JDBC_DATASOURCE = "jdbc/postgresql_test_xa";
//    public final static String JNDI_NAME_JDBC_DATASOURCE = "java:comp/env/PostgreSQL_JDBC";
    /** ユーザートランザクションのJNDI名 */
    public final static String JNDI_NAME_USER_TRANSACTION = "java:comp/UserTransaction";
    /** ユーザートランザクションの短縮JNDI名 */
    public final static String JNDI_NAME_USER_TRANSACTION_SHORT = "jta/usertransaction";
    /** トランザクションマネージャーのJNDI名 */
    public static final String JNDI_NAME_TRANSACTION_MANAGER = "java:comp/TransactionManager";

    /**
     * static初期化子
     * クラスがメモリ上にロードされるとき、一回だけ実行される。
     * その後、いくらインスタンス化されようとも、決して実行されることはない。
     */
    static {
        try {
            _instance = new ServiceLocator();
        } catch (ServiceLocatorException se) {
            log.fatal("アプリケーション全体に影響するエラーが発生しました。"
                    + "ServiceLocatorのインスタンス作成が失敗しました。"
                    + "このクラスを使用したサービスの検索は全て失敗します。", se);
        }
    }

    /**
     * コンストラクタ
     *
     * @throws ServiceLocatorException　初期処理が失敗した場合に投げられる。
     */
    private ServiceLocator() throws ServiceLocatorException {
        try {
            initialContext = new InitialContext();
            Map<String, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<String, Object>());
            cache = synchronizedMap;
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }

    /**
     * サービスロケーターのインスタンスを返す
     *
     * @return ServiceLocator サービスロケーター
     */
    static public ServiceLocator getInstance() {
        return _instance;
    }

    /**
     * JDBCデータソースを取得する
     *
     * @param dataSourceName JDBCデータソース名
     * @return JDBCデータソース
     * @throws ServiceLocatorException
     */
    public DataSource getDataSource(String dataSourceName) throws ServiceLocatorException {
        DataSource dataSource = null;
        try {
            if (cache.containsKey(dataSourceName)) {
                //				log.debug("getDataSource from cache.");
                dataSource = (DataSource) cache.get(dataSourceName);
            } else {
                //				log.debug("getDataSource from lookup.");
                dataSource = (DataSource) initialContext.lookup(dataSourceName);
                cache.put(dataSourceName, dataSource);
            }
        } catch (NamingException nex) {
            throw new ServiceLocatorException(nex);
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return dataSource;
    }

    /**
     * UserTransaction Service Locator
     *
     * @param userTransactionName
     * @return UserTransaction nullのことはない
     * @throws ServiceLocatorException
     */
    public UserTransaction getUserTransaction(String userTransactionName) throws ServiceLocatorException {
        UserTransaction transaction = null;
        try {
            //			log.debug("getUserTransaction from lookup.");
            transaction = (UserTransaction) initialContext.lookup(userTransactionName);
        } catch (NamingException nex) {
            throw new ServiceLocatorException(nex);
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return transaction;
    }

    /**
     * トランザクションマネージャーを取得する
     *
     * @return TransactionManagerのインスタンス
     * @throws ServiceLocatorException
     */
    public TransactionManager getTransactionManager() throws ServiceLocatorException {
        TransactionManager tm = null;
        try {
            tm = (TransactionManager) initialContext.lookup(JNDI_NAME_TRANSACTION_MANAGER);
        } catch (NamingException nex) {
            throw new ServiceLocatorException(nex);
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return tm;
    }
}
