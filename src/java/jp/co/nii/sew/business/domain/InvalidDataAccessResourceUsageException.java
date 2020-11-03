package jp.co.nii.sew.business.domain;

/**
 * データアクセスリソース利用不可能例外
 * @author n-machida
 */
public class InvalidDataAccessResourceUsageException extends DataAccessException {

    /**
     * コンストラクタ
     *
     */
    public InvalidDataAccessResourceUsageException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public InvalidDataAccessResourceUsageException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public InvalidDataAccessResourceUsageException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public InvalidDataAccessResourceUsageException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
