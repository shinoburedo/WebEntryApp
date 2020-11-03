package jp.co.nii.sew.business.domain;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ファイルデータアクセス例外
 * @author n-machida
 */
public class FileDataAccessException extends DataAccessException {

    /* ログ */
    private static Log log = LogFactory.getLog(FileDataAccessException.class);

    /**
     * コンストラクタ
     *
     */
    public FileDataAccessException() {
	super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public FileDataAccessException(String message) {
	super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public FileDataAccessException(Throwable throwable) {
	super(throwable);
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public FileDataAccessException(String message, Throwable throwable) {
	super(message, throwable);
    }

    /**
     * この SQLException オブジェクトの SQLState を取得します。
     *
     * @return SQLState 値
     */
    @Override
    public String getSQLState() {
	return null;
    }

    /**
     * この SQLException オブジェクトのベンダー固有の例外コードを取得します。
     *
     * @return ベンダーのエラーコード
     */
    @Override
    public int getErrorCode() {
	return 0;
    }

    /**
     * SQLExceptionを取得する。
     *
     * @return SQLExceptionのインスタンス
     */
    @Override
    protected SQLException getSQLException() {
	return null;
    }
}
