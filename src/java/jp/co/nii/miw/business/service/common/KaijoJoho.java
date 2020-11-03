package jp.co.nii.miw.business.service.common;

/**
 * 会場一覧用データビーン
 * @author h-katayama
 */
public class KaijoJoho {

    /** 年度 */
    private String nendo;
    /** 回数 */
    private String kaisu;
    /** イベントコード */
    private String eventCode;
    /** 試験地コード */
    private String shikenchiCode;
    /** 試験地名 */
    private String shikenchiName;
    /** 会場コード */
    private String kaijoCode;
    /** 会場名 */
    private String kaijoName;
 

    /**
     * 年度 を取得する。
     * @return 年度
     */
    public String getNendo() {
        return nendo;
    }

    /**
     * 年度 をセットする。
     * @param nendo 年度
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }

    /**
     * 回数 を取得する。
     * @return 回数
     */
    public String getKaisu() {
        return kaisu;
    }

    /**
     * 回数 をセットする。
     * @param kaisu 回数
     */
    public void setKaisu(String kaisu) {
        this.kaisu = kaisu;
    }

    /**
     * イベントコード を取得する。
     * @return イベントコード
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * イベントコード をセットする。
     * @param eventCode イベントコード
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * 試験地コード を取得する。
     * @return 試験地コード
     */
    public String getShikenchiCode() {
        return shikenchiCode;
    }

    /**
     * 試験地コード をセットする。
     * @param shikenchiCode 試験地コード
     */
    public void setShikenchiCode(String shikenchiCode) {
        this.shikenchiCode = shikenchiCode;
    }

    /**
     * 試験地名 を取得する。
     * @return 試験地名
     */
    public String getShikenchiName() {
        return shikenchiName;
    }

    /**
     * 試験地名 をセットする。
     * @param shikenchiName 試験地名
     */
    public void setShikenchiName(String shikenchiName) {
        this.shikenchiName = shikenchiName;
    }

    /**
     * 会場コード を取得する。
     * @return 会場コード
     */
    public String getKaijoCode() {
        return kaijoCode;
    }

    /**
     * 会場コード をセットする。
     * @param kaijoCode 会場コード
     */
    public void setKaijoCode(String kaijoCode) {
        this.kaijoCode = kaijoCode;
    }

    /**
     * 会場名 を取得する。
     * @return 会場名
     */
    public String getKaijoName() {
        return kaijoName;
    }

    /**
     * 会場名 をセットする。
     * @param kaijoName 会場名
     */
    public void setKaijoName(String kaijoName) {
        this.kaijoName = kaijoName;
    }
}
