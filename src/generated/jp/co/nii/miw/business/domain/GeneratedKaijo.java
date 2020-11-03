package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された 会場 BO抽象クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public class GeneratedKaijo extends AbstractBusinessObject{

    /** 年度 */
    private String nendo;
    /** 回数 */
    private String kaisu;
    /** イベントコード */
    private String eventCode;
    /** 試験地コード */
    private String shikenchiCode;
    /** 会場コード */
    private String kaijoCode;
    /** 会場名 */
    private String kaijoName;
    /** 住所 */
    private String jusho;
    /** 会場案内文 */
    private String kaijoAnnaiBun;
    /** 地図ＵＲＬ */
    private String mapUrl;
    /** 地区内順序 */
    private String chikuNaiJunjo;
    /** 論理削除フラグ */
    private String ronriSakujoFlg;
    /** 処理区分 */
    private String shoriKbn;
    /** 登録日付 */
    private String torokuDate;
    /** 登録時刻 */
    private String torokuTime;
    /** 登録ユーザーＩＤ */
    private String torokuUserId;
    /** 更新日付 */
    private String koshinDate;
    /** 更新時刻 */
    private String koshinTime;
    /** 更新ユーザーＩＤ */
    private String koshinUserId;
    /** DAO */
    protected KaijoDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedKaijo() {
        nendo = "";
        kaisu = "";
        eventCode = "";
        shikenchiCode = "";
        kaijoCode = "";
        kaijoName = "";
        jusho = "";
        kaijoAnnaiBun = "";
        mapUrl = "";
        chikuNaiJunjo = "";
        ronriSakujoFlg = "";
        shoriKbn = "";
        torokuDate = "";
        torokuTime = "";
        torokuUserId = "";
        koshinDate = "";
        koshinTime = "";
        koshinUserId = "";
    }

    /**
     * 接続データソースを設定するコンストラクタ
     * @param connectionUser データソース名
     */
    public GeneratedKaijo(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getKaijoDao(connectionUser);
    }

    /**
     * 登録する。
     */
    public void create() {
        dao.create(this);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード
     * @param shikenchiCode 試験地コード
     * @param kaijoCode 会場コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Kaijo find(String nendo, String kaisu, String eventCode, String shikenchiCode, String kaijoCode) {
        return find(nendo, kaisu, eventCode, shikenchiCode, kaijoCode, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード
     * @param shikenchiCode 試験地コード
     * @param kaijoCode 会場コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Kaijo lockNoWait(String nendo, String kaisu, String eventCode, String shikenchiCode, String kaijoCode) {
        return find(nendo, kaisu, eventCode, shikenchiCode, kaijoCode, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード
     * @param shikenchiCode 試験地コード
     * @param kaijoCode 会場コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Kaijo lockWaitIfLocked(String nendo, String kaisu, String eventCode, String shikenchiCode, String kaijoCode) {
        return find(nendo, kaisu, eventCode, shikenchiCode, kaijoCode, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード
     * @param shikenchiCode 試験地コード
     * @param kaijoCode 会場コード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Kaijo find(String nendo, String kaisu, String eventCode, String shikenchiCode, String kaijoCode, String lockMode) {
        Kaijo bo = new Kaijo();
        bo.setNendo(nendo);
        bo.setKaisu(kaisu);
        bo.setEventCode(eventCode);
        bo.setShikenchiCode(shikenchiCode);
        bo.setKaijoCode(kaijoCode);
        bo = (Kaijo) dao.find(bo, lockMode);
        return bo;
    }

    /**
     * 更新する。
     */
    public void update() {
        dao.update(this);
    }

    /**
     * 削除する。
     */
    public void remove() {
        dao.remove(this);
    }

    @Override
    public String getPrimaryKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNendo()).append(StringUtility.SEW_DELIMITER)
                .append(getKaisu()).append(StringUtility.SEW_DELIMITER)
                .append(getEventCode()).append(StringUtility.SEW_DELIMITER)
                .append(getShikenchiCode()).append(StringUtility.SEW_DELIMITER)
                .append(getKaijoCode());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNendo()).append(StringUtility.SEW_DELIMITER)
                .append(getKaisu()).append(StringUtility.SEW_DELIMITER)
                .append(getEventCode()).append(StringUtility.SEW_DELIMITER)
                .append(getShikenchiCode()).append(StringUtility.SEW_DELIMITER)
                .append(getKaijoCode()).append(StringUtility.SEW_DELIMITER)
                .append(getKaijoName()).append(StringUtility.SEW_DELIMITER)
                .append(getJusho()).append(StringUtility.SEW_DELIMITER)
                .append(getKaijoAnnaiBun()).append(StringUtility.SEW_DELIMITER)
                .append(getMapUrl()).append(StringUtility.SEW_DELIMITER)
                .append(getChikuNaiJunjo()).append(StringUtility.SEW_DELIMITER)
                .append(getRonriSakujoFlg()).append(StringUtility.SEW_DELIMITER)
                .append(getShoriKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getTorokuDate()).append(StringUtility.SEW_DELIMITER)
                .append(getTorokuTime()).append(StringUtility.SEW_DELIMITER)
                .append(getTorokuUserId()).append(StringUtility.SEW_DELIMITER)
                .append(getKoshinDate()).append(StringUtility.SEW_DELIMITER)
                .append(getKoshinTime()).append(StringUtility.SEW_DELIMITER)
                .append(getKoshinUserId());
        return sb.toString();
    }

    /**
     * フィールドの値をもつタブ区切りの文字列から、全フィールドをセットする。
     * @param tabSeparatedValue フィールドの値をもつタブ区切りの文字列
     */
    public void setAllFieldsFromTsv(String tabSeparatedValue) {
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 18);
        setNendo(values[0]);
        setKaisu(values[1]);
        setEventCode(values[2]);
        setShikenchiCode(values[3]);
        setKaijoCode(values[4]);
        setKaijoName(values[5]);
        setJusho(values[6]);
        setKaijoAnnaiBun(values[7]);
        setMapUrl(values[8]);
        setChikuNaiJunjo(values[9]);
        setRonriSakujoFlg(values[10]);
        setShoriKbn(values[11]);
        setTorokuDate(values[12]);
        setTorokuTime(values[13]);
        setTorokuUserId(values[14]);
        setKoshinDate(values[15]);
        setKoshinTime(values[16]);
        setKoshinUserId(values[17]);
    }

    @Override
    public Object clone() {
        Kaijo bo = (Kaijo) super.clone();
        bo.setNendo(getNendo());
        bo.setKaisu(getKaisu());
        bo.setEventCode(getEventCode());
        bo.setShikenchiCode(getShikenchiCode());
        bo.setKaijoCode(getKaijoCode());
        bo.setKaijoName(getKaijoName());
        bo.setJusho(getJusho());
        bo.setKaijoAnnaiBun(getKaijoAnnaiBun());
        bo.setMapUrl(getMapUrl());
        bo.setChikuNaiJunjo(getChikuNaiJunjo());
        bo.setRonriSakujoFlg(getRonriSakujoFlg());
        bo.setShoriKbn(getShoriKbn());
        bo.setTorokuDate(getTorokuDate());
        bo.setTorokuTime(getTorokuTime());
        bo.setTorokuUserId(getTorokuUserId());
        bo.setKoshinDate(getKoshinDate());
        bo.setKoshinTime(getKoshinTime());
        bo.setKoshinUserId(getKoshinUserId());
        return bo;
    }

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

    /**
     * 住所 を取得する。
     * @return 住所
     */
    public String getJusho() {
        return jusho;
    }

    /**
     * 住所 をセットする。
     * @param jusho 住所
     */
    public void setJusho(String jusho) {
        this.jusho = jusho;
    }

    /**
     * 会場案内文 を取得する。
     * @return 会場案内文
     */
    public String getKaijoAnnaiBun() {
        return kaijoAnnaiBun;
    }

    /**
     * 会場案内文 をセットする。
     * @param kaijoAnnaiBun 会場案内文
     */
    public void setKaijoAnnaiBun(String kaijoAnnaiBun) {
        this.kaijoAnnaiBun = kaijoAnnaiBun;
    }

    /**
     * 地図ＵＲＬ を取得する。
     * @return 地図ＵＲＬ
     */
    public String getMapUrl() {
        return mapUrl;
    }

    /**
     * 地図ＵＲＬ をセットする。
     * @param mapUrl 地図ＵＲＬ
     */
    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    /**
     * 地区内順序 を取得する。
     * @return 地区内順序
     */
    public String getChikuNaiJunjo() {
        return chikuNaiJunjo;
    }

    /**
     * 地区内順序 をセットする。
     * @param chikuNaiJunjo 地区内順序
     */
    public void setChikuNaiJunjo(String chikuNaiJunjo) {
        this.chikuNaiJunjo = chikuNaiJunjo;
    }

    /**
     * 論理削除フラグ を取得する。
     * @return 論理削除フラグ
     */
    public String getRonriSakujoFlg() {
        return ronriSakujoFlg;
    }

    /**
     * 論理削除フラグ をセットする。
     * @param ronriSakujoFlg 論理削除フラグ
     */
    public void setRonriSakujoFlg(String ronriSakujoFlg) {
        this.ronriSakujoFlg = ronriSakujoFlg;
    }

    /**
     * 処理区分 を取得する。
     * @return 処理区分
     */
    public String getShoriKbn() {
        return shoriKbn;
    }

    /**
     * 処理区分 をセットする。
     * @param shoriKbn 処理区分
     */
    public void setShoriKbn(String shoriKbn) {
        this.shoriKbn = shoriKbn;
    }

    /**
     * 登録日付 を取得する。
     * @return 登録日付
     */
    public String getTorokuDate() {
        return torokuDate;
    }

    /**
     * 登録日付 をセットする。
     * @param torokuDate 登録日付
     */
    public void setTorokuDate(String torokuDate) {
        this.torokuDate = torokuDate;
    }

    /**
     * 登録時刻 を取得する。
     * @return 登録時刻
     */
    public String getTorokuTime() {
        return torokuTime;
    }

    /**
     * 登録時刻 をセットする。
     * @param torokuTime 登録時刻
     */
    public void setTorokuTime(String torokuTime) {
        this.torokuTime = torokuTime;
    }

    /**
     * 登録ユーザーＩＤ を取得する。
     * @return 登録ユーザーＩＤ
     */
    public String getTorokuUserId() {
        return torokuUserId;
    }

    /**
     * 登録ユーザーＩＤ をセットする。
     * @param torokuUserId 登録ユーザーＩＤ
     */
    public void setTorokuUserId(String torokuUserId) {
        this.torokuUserId = torokuUserId;
    }

    /**
     * 更新日付 を取得する。
     * @return 更新日付
     */
    public String getKoshinDate() {
        return koshinDate;
    }

    /**
     * 更新日付 をセットする。
     * @param koshinDate 更新日付
     */
    public void setKoshinDate(String koshinDate) {
        this.koshinDate = koshinDate;
    }

    /**
     * 更新時刻 を取得する。
     * @return 更新時刻
     */
    public String getKoshinTime() {
        return koshinTime;
    }

    /**
     * 更新時刻 をセットする。
     * @param koshinTime 更新時刻
     */
    public void setKoshinTime(String koshinTime) {
        this.koshinTime = koshinTime;
    }

    /**
     * 更新ユーザーＩＤ を取得する。
     * @return 更新ユーザーＩＤ
     */
    public String getKoshinUserId() {
        return koshinUserId;
    }

    /**
     * 更新ユーザーＩＤ をセットする。
     * @param koshinUserId 更新ユーザーＩＤ
     */
    public void setKoshinUserId(String koshinUserId) {
        this.koshinUserId = koshinUserId;
    }
}
