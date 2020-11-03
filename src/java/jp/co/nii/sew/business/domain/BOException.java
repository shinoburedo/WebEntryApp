package jp.co.nii.sew.business.domain;

import jp.co.nii.sew.SEWException;

/**
 * BOクラス ： BOException
 *
 * @author n-machida
 *
 */
public class BOException extends SEWException {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public BOException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message
     *            メッセージ
     */
    public BOException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable
     *            実際に発生した例外
     */
    public BOException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     *
     * @param message
     *            メッセージ
     * @param throwable
     *            実際に発生した例外
     */
    public BOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
