package jp.co.nii.sew.utility;

import jp.co.nii.sew.SEWException;


/**
 * ServiceLocatorの例�? *
 * @author n-minegishi
 *
 */
public class ServiceLocatorException extends SEWException {

    /**
     * コンストラクタ
     */
    public ServiceLocatorException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message メ�?��ージ
     */
    public ServiceLocatorException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param throwable 実際に発生した例�?     */
    public ServiceLocatorException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メ�?��ージ
     * @param throwable 実際に発生した例�?     */
    public ServiceLocatorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
