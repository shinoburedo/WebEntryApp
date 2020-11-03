package jp.co.nii.sew.business.service;

import javax.transaction.UserTransaction;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * JTA用javaファイル
 * @author 
 */
public abstract class AbstractService {
    
    /** ログに出力するクラス名 */
    protected static String CLASS_NAME;
    
    public AbstractService() {
        CLASS_NAME = getClass().getName();
    }
    
    /** ユーザートランザクション */
    private UserTransaction userTransaction;
    /**
     * トランザクションを取得
     */
    protected final void getTransaction() {
        this.userTransaction = TransactionUtility.get();
    }

    /**
     * トランザクションを開始
     */
    protected final void beginTransaction() {
        TransactionUtility.begin(userTransaction);
    }

    /**
     * トランザクションをコミット
     */
    protected final void commitTransaction() {
        TransactionUtility.commit(userTransaction);
    }

    /**
     * トランザクションをロールバック
     */
    protected final void rollbackTransaction() {
        TransactionUtility.rollback(userTransaction);
    }
}
