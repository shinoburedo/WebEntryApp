package jp.co.nii.sew.business.domain;

import jp.co.nii.sew.utility.StringUtility;

/**
 * DAO取得済みデータ無し例外クラス<br>
 * 当クラスは既に検索済みのデータが、更新・削除時に存在しなくなっていた場合に発生させる例外。<br>
 * 検索時に該当データが存在しない場合の例外ではないので注意。<br>
 *
 * @author n-machida
 *
 */
public class NoSuchDataException extends DataAccessException {

    /**
     * コンストラクタ
     * @param message メッセージ
     */
    public NoSuchDataException(String message) {
        super(message);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return new StringBuilder().append("SQL:").append(super.getMessage()).
                append(StringUtility.NEW_LINE).
                append("message: 該当するデータは、現在存在しなかった。").toString();
    }
}
