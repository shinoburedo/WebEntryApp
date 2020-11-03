package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された イベント BO抽象クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public class GeneratedEvent extends AbstractBusinessObject{

    /** イベントコード */
    private String eventCode;
    /** イベント名 */
    private String eventName;
    /** 表示順序 */
    private String hyojiJunjo;
    /** イベント料 */
    private String eventRyo;
    /** コンビニ決済事務手数料 */
    private String convenienceKessaiJimuTesuryo;
    /** クレジットカード決済事務手数料 */
    private String creditcardKessaiJimuTesuryo;
    /** ペイジー決済事務手数料 */
    private String payEasyKessaiJimuTesuryo;
    /** 申込者数 */
    private String moshikomishaSu;
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
    protected EventDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedEvent() {
        eventCode = "";
        eventName = "";
        hyojiJunjo = "";
        eventRyo = "";
        convenienceKessaiJimuTesuryo = "";
        creditcardKessaiJimuTesuryo = "";
        payEasyKessaiJimuTesuryo = "";
        moshikomishaSu = "";
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
    public GeneratedEvent(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getEventDao(connectionUser);
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
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Event find(String eventCode) {
        return find(eventCode, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Event lockNoWait(String eventCode) {
        return find(eventCode, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Event lockWaitIfLocked(String eventCode) {
        return find(eventCode, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param eventCode イベントコード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Event find(String eventCode, String lockMode) {
        Event bo = new Event();
        bo.setEventCode(eventCode);
        bo = (Event) dao.find(bo, lockMode);
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
        sb.append(getEventCode());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getEventCode()).append(StringUtility.SEW_DELIMITER)
                .append(getEventName()).append(StringUtility.SEW_DELIMITER)
                .append(getHyojiJunjo()).append(StringUtility.SEW_DELIMITER)
                .append(getEventRyo()).append(StringUtility.SEW_DELIMITER)
                .append(getConvenienceKessaiJimuTesuryo()).append(StringUtility.SEW_DELIMITER)
                .append(getCreditcardKessaiJimuTesuryo()).append(StringUtility.SEW_DELIMITER)
                .append(getPayEasyKessaiJimuTesuryo()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomishaSu()).append(StringUtility.SEW_DELIMITER)
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
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 16);
        setEventCode(values[0]);
        setEventName(values[1]);
        setHyojiJunjo(values[2]);
        setEventRyo(values[3]);
        setConvenienceKessaiJimuTesuryo(values[4]);
        setCreditcardKessaiJimuTesuryo(values[5]);
        setPayEasyKessaiJimuTesuryo(values[6]);
        setMoshikomishaSu(values[7]);
        setRonriSakujoFlg(values[8]);
        setShoriKbn(values[9]);
        setTorokuDate(values[10]);
        setTorokuTime(values[11]);
        setTorokuUserId(values[12]);
        setKoshinDate(values[13]);
        setKoshinTime(values[14]);
        setKoshinUserId(values[15]);
    }

    @Override
    public Object clone() {
        Event bo = (Event) super.clone();
        bo.setEventCode(getEventCode());
        bo.setEventName(getEventName());
        bo.setHyojiJunjo(getHyojiJunjo());
        bo.setEventRyo(getEventRyo());
        bo.setConvenienceKessaiJimuTesuryo(getConvenienceKessaiJimuTesuryo());
        bo.setCreditcardKessaiJimuTesuryo(getCreditcardKessaiJimuTesuryo());
        bo.setPayEasyKessaiJimuTesuryo(getPayEasyKessaiJimuTesuryo());
        bo.setMoshikomishaSu(getMoshikomishaSu());
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
     * イベント名 を取得する。
     * @return イベント名
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * イベント名 をセットする。
     * @param eventName イベント名
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * 表示順序 を取得する。
     * @return 表示順序
     */
    public String getHyojiJunjo() {
        return hyojiJunjo;
    }

    /**
     * 表示順序 をセットする。
     * @param hyojiJunjo 表示順序
     */
    public void setHyojiJunjo(String hyojiJunjo) {
        this.hyojiJunjo = hyojiJunjo;
    }

    /**
     * イベント料 を取得する。
     * @return イベント料
     */
    public String getEventRyo() {
        return eventRyo;
    }

    /**
     * イベント料 をセットする。
     * @param eventRyo イベント料
     */
    public void setEventRyo(String eventRyo) {
        this.eventRyo = eventRyo;
    }

    /**
     * コンビニ決済事務手数料 を取得する。
     * @return コンビニ決済事務手数料
     */
    public String getConvenienceKessaiJimuTesuryo() {
        return convenienceKessaiJimuTesuryo;
    }

    /**
     * コンビニ決済事務手数料 をセットする。
     * @param convenienceKessaiJimuTesuryo コンビニ決済事務手数料
     */
    public void setConvenienceKessaiJimuTesuryo(String convenienceKessaiJimuTesuryo) {
        this.convenienceKessaiJimuTesuryo = convenienceKessaiJimuTesuryo;
    }

    /**
     * クレジットカード決済事務手数料 を取得する。
     * @return クレジットカード決済事務手数料
     */
    public String getCreditcardKessaiJimuTesuryo() {
        return creditcardKessaiJimuTesuryo;
    }

    /**
     * クレジットカード決済事務手数料 をセットする。
     * @param creditcardKessaiJimuTesuryo クレジットカード決済事務手数料
     */
    public void setCreditcardKessaiJimuTesuryo(String creditcardKessaiJimuTesuryo) {
        this.creditcardKessaiJimuTesuryo = creditcardKessaiJimuTesuryo;
    }

    /**
     * ペイジー決済事務手数料 を取得する。
     * @return ペイジー決済事務手数料
     */
    public String getPayEasyKessaiJimuTesuryo() {
        return payEasyKessaiJimuTesuryo;
    }

    /**
     * ペイジー決済事務手数料 をセットする。
     * @param payEasyKessaiJimuTesuryo ペイジー決済事務手数料
     */
    public void setPayEasyKessaiJimuTesuryo(String payEasyKessaiJimuTesuryo) {
        this.payEasyKessaiJimuTesuryo = payEasyKessaiJimuTesuryo;
    }

    /**
     * 申込者数 を取得する。
     * @return 申込者数
     */
    public String getMoshikomishaSu() {
        return moshikomishaSu;
    }

    /**
     * 申込者数 をセットする。
     * @param moshikomishaSu 申込者数
     */
    public void setMoshikomishaSu(String moshikomishaSu) {
        this.moshikomishaSu = moshikomishaSu;
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
