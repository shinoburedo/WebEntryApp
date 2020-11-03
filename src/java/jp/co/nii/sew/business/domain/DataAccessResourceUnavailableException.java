package jp.co.nii.sew.business.domain;

/**
 * データアクセスリソース利用不可能例外
 * @author n-machida
 */
public class DataAccessResourceUnavailableException extends DataAccessException {

    /**
     * コンストラクタ
     *
     */
    public DataAccessResourceUnavailableException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public DataAccessResourceUnavailableException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public DataAccessResourceUnavailableException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public DataAccessResourceUnavailableException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
