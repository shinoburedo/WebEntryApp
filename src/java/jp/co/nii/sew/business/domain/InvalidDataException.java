package jp.co.nii.sew.business.domain;

/**
 * データアクセスリソース利用不可能例外
 * @author n-machida
 */
public class InvalidDataException extends DataAccessException {

    /**
     * コンストラクタ
     *
     */
    public InvalidDataException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public InvalidDataException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public InvalidDataException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public InvalidDataException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
