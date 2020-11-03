package jp.co.nii.sew.business.domain;

import java.sql.SQLException;

import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.utility.StringUtility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DAO例外クラス ： DataAccessException
 * @author n-machida
 */
public class DataAccessException extends SEWException {

    /* ログ */
    private static Log log = LogFactory.getLog(DataAccessException.class);

    /**
     * コンストラクタ
     *
     */
    public DataAccessException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public DataAccessException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public DataAccessException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public DataAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * この SQLException オブジェクトの SQLState を取得します。
     *
     * @return SQLState 値
     */
    public String getSQLState() {
        String sqlState = null;
        SQLException ex = getSQLException();

        if (ex != null) {
            sqlState = ex.getSQLState();
        }
        return sqlState;
    }

    /**
     * この SQLException オブジェクトのベンダー固有の例外コードを取得します。
     *
     * @return ベンダーのエラーコード
     */
    public int getErrorCode() {
        int errorCode = 0;
        SQLException ex = getSQLException();

        if (ex != null) {
            errorCode = ex.getErrorCode();
        }
        return errorCode;
    }

    /**
     * SQLExceptionを取得する。
     *
     * @return SQLExceptionのインスタンス
     */
    protected SQLException getSQLException() {
        SQLException ex = null;
        Throwable throwable = getCause();

        if (throwable != null) {
            if (throwable instanceof SQLException) {
                ex = (SQLException) throwable;
            } else if (throwable instanceof DataAccessException) {
                DataAccessException ex1 = (DataAccessException) throwable;
                ex = ex1.getSQLException();
            }
        }
        return ex;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        final StringBuilder sb = new StringBuilder().append("message:").append(super.getMessage());
        if (super.getCause() != null) {
            sb.append(StringUtility.NEW_LINE).
                    append("Cause message:").append(super.getCause().toString());
        }
        return sb.toString();
    }
}
