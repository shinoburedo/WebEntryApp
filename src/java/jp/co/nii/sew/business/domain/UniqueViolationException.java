package jp.co.nii.sew.business.domain;

/**
 * 一意性制約違反例外
 * @author n-machida
 */
public class UniqueViolationException extends IntegrityConstraintViolationException {

    /**
     * コンストラクタ
     *
     */
    public UniqueViolationException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public UniqueViolationException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public UniqueViolationException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public UniqueViolationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
