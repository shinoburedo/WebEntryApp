package jp.co.nii.sew.business.domain;

/**
 * 整合性制約違反例外
 * @author n-machida
 */
public class IntegrityConstraintViolationException extends DataAccessException {

    /**
     * コンストラクタ
     *
     */
    public IntegrityConstraintViolationException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public IntegrityConstraintViolationException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public IntegrityConstraintViolationException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public IntegrityConstraintViolationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
