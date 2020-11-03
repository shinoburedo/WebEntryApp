package jp.co.nii.sew.integration;

import jp.co.nii.sew.business.domain.DataAccessException;

/**
 * データアクセスリソース利用不可能例外
 * @author n-machida
 */
public class DataAccessResourceStarvationException extends DataAccessException {

    /**
     * コンストラクタ
     *
     */
    public DataAccessResourceStarvationException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public DataAccessResourceStarvationException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public DataAccessResourceStarvationException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public DataAccessResourceStarvationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
