package jp.co.nii.sew.business.domain;

public interface BusinessObject {
    
    /** CSVフォーマットの文字列の区切り文字 */
    public static final String CSV_DELIMITER = ",";

    /**
     * 主キーに該当する永続属性を文字連結して返す。
     * ログ出力時に利用する。
     * @return カンマ区切りで文字連結された主キー永続属性の文字列
     */
    public abstract String getPrimaryKey();


}
