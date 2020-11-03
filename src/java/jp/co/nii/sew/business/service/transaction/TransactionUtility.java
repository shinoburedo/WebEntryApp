package jp.co.nii.sew.business.service.transaction;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import jp.co.nii.sew.utility.ServiceLocator;
import jp.co.nii.sew.utility.ServiceLocatorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * トランザクション・ユーティリティー
 * @author n-minegishi
 */
public class TransactionUtility {

    /** ロックしない */
    public static final String NO_LOCK = "";
    /** ロックする。既にロックされているときはロック解除を待たない。 */
    public static final String LOCK_NOWAIT = " FOR UPDATE NOWAIT";
    /** ロックする。既にロックされているときはロック解除を待つ。 */
    public static final String LOCK_WAIT = " FOR UPDATE";
    /* ログ */
    private static Log log = LogFactory.getLog(TransactionUtility.class);

    /**
     * ユーザートランザクションを取得する
     * @return ユーザートランザクション
     */
    public static final UserTransaction get() throws ServiceLocatorException {
        final ServiceLocator serviceLocator = ServiceLocator.getInstance();
        final UserTransaction utx = serviceLocator.getUserTransaction(ServiceLocator.JNDI_NAME_USER_TRANSACTION);
        return utx;
    }

    /**
     * トランザクションのステータスを取得する。
     * @return javax.transaction.Status
     */
    public static final int getStatus() {
        int status = Status.STATUS_UNKNOWN;
        try {
            // getStatus()を呼ぶためにあらたにget()するけどスレッドと関連付けられはしない。
            status = get().getStatus();
        } catch (SystemException ex) {
            throw new TransactionException("getStatus", ex);
        } finally {
            return status;
        }
    }

    /**
     * 現在のスレッドdがトランザクションが関連付けられているかを判定する。
     *
     * @return トランザクションと関連付けられているとき - true <br>
     * javax.transaction.Status.STATUS_NO_TRANSACTIONのとき - false <br>
     * javax.transaction.Status.STATUS_UNKNOWNのとき - true <br>
     */
    public static final boolean isTheCurrentThreadAssociatedWithAnyTransaction() {
        boolean withTranasaction = false;
        if (getStatus() == Status.STATUS_NO_TRANSACTION) {
            withTranasaction = false;
        } else {
            withTranasaction = true;
        }
        return withTranasaction;
    }

    /**
     * トランザクションを開始する
     * @param utx ユーザートランザクション
     */
    public static final void begin(final UserTransaction utx) throws TransactionException {
        try {
            utx.begin();

//	    try {
//		log.debug("begin()後の beginした userTransaction.getStatus() = " + utx.getStatus());
//	    } catch (SystemException ex) {
//	    }
//	    log.debug("begin()後の TransactionUtility.getStatus() = "+getStatus());
        } catch (NotSupportedException ex) {
            throw new TransactionException("begin", ex);
        } catch (SystemException ex) {
            throw new TransactionException("begin", ex);
        }
    }

    /**
     * トランザクションをコミットする
     * @param utx ユーザートランザクション
     */
    public static final void commit(final UserTransaction utx) throws TransactionException {
        try {
            utx.commit();

//	    try {
//		log.debug("commit()後の commitした userTransaction.getStatus() = " + utx.getStatus());
//	    } catch (SystemException ex) {
//	    }
//	    log.debug("commit()後の TransactionUtility.getStatus() = "+getStatus());
        } catch (IllegalStateException ex) {
            throw new TransactionException("commit", ex);
        } catch (SecurityException ex) {
            throw new TransactionException("commit", ex);
        } catch (RollbackException ex) {
            throw new TransactionException("commit", ex);
        } catch (HeuristicMixedException ex) {
            throw new TransactionException("commit", ex);
        } catch (HeuristicRollbackException ex) {
            throw new TransactionException("commit", ex);
        } catch (SystemException ex) {
            throw new TransactionException("commit", ex);
        }
    }

    /**
     * トランザクションをロールバックする
     * @param utx ユーザートランザクション
     */
    public static final void rollback(final UserTransaction utx) throws TransactionException {
        try {
            utx.rollback();

	    try {
		log.debug("rollback()後の rollbackした userTransaction.getStatus() = " + utx.getStatus());
	    } catch (SystemException ex) {
	    }
	    log.debug("rollback()後の TransactionUtility.getStatus() = "+getStatus());
        } catch (IllegalStateException ex) {
            throw new TransactionException("rollback", ex);
        } catch (SecurityException ex) {
            throw new TransactionException("rollback", ex);
        } catch (SystemException ex) {
            throw new TransactionException("rollback", ex);
        }
    }


}
