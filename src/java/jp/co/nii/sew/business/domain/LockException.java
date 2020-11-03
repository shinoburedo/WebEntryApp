package jp.co.nii.sew.business.domain;

/**
 * DAOロック例外クラス
 * ・（理由コード: 2）デッドロックのために、トランザクションがロールバックされた。
 * ・（理由コード:68）ロック・タイムアウトのために、トランザクションがロールバックされた。
 * ・（理由コード:72）トランザクションに関係するDB2 Data Links Manager に関連するエラーのために、トランザクションがロールバックされました。
 * ・（理由コード:80）タイムアウトのために、ステートメントが正常に実行されていません。
 * DB2のSQLコード : -911 デッドロックまたはタイムアウトのため、現在のトランザクションがロールバックされました。
 * DB2のSQLコード : -912 データベースに対するロック要求の最大値に達しました。
 * DB2のSQLコード : -913 実行がデッドロックまたはタイムアウトによって失敗しました。
 * @author n-machida
 */
public class LockException extends DataAccessException {

    /**
     * コンストラクタ
     *
     */
    public LockException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public LockException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param throwable 実際に発生した例外
     */
    public LockException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public LockException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

