package jp.co.nii.sew.business.domain;

import jp.co.nii.sew.business.domain.GeneratedYubinBangoJisho;
import java.util.List;
import java.util.LinkedList;

/**
 * 郵便番号辞書 BOクラス
 * @author DB管理ツール
 */
public class YubinBangoJisho extends GeneratedYubinBangoJisho {

    /**
     * インスタンスを生成する。
     */
    public YubinBangoJisho() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public YubinBangoJisho(String connectionUser) {
        super(connectionUser);
    }
    
    /**
     * 郵便番号で検索する。
     * @param yubinBango 郵便番号７桁
     * @param lockMode ロック方法
     */
    public List findYubinBango(String yubinBango, String lockMode) {
        List ret = new LinkedList();
        ret = dao.findYubinBango(yubinBango, lockMode);
        return ret;
    }
    
}
