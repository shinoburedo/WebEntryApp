package jp.co.nii.sew.business.domain;

/**
 * DAO例外クラス ： UncategorizedDataAccessException
 * @author n-machida
 */
public class UncategorizedDataAccessException extends DataAccessException {

    /**
     * コンストラクタ
     */
    public UncategorizedDataAccessException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     */
    public UncategorizedDataAccessException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param throwable 実際に発生した例外
     */
    public UncategorizedDataAccessException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public UncategorizedDataAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
