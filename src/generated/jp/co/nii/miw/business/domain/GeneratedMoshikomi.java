package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された 申込 BO抽象クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public class GeneratedMoshikomi extends AbstractBusinessObject{

    /** 申込受付番号 */
    private String moshikomiUketsukeNo;
    /** 年度 */
    private String nendo;
    /** 回数 */
    private String kaisu;
    /** イベントコード */
    private String eventCode;
    /** 申込媒体区分 */
    private String moshikomiBaitaiKbn;
    /** 個人団体区分 */
    private String kojinDantaiKbn;
    /** 団体コード */
    private String dantaiCode;
    /** 団体申込受付番号 */
    private String dantaiMoshikomiUketsukeNo;
    /** ユーザーＩＤ */
    private String userId;
    /** 手続状況区分 */
    private String tetsudukiJokyoKbn;
    /** 試験地コード */
    private String shikenchiCode;
    /** 会場コード */
    private String kaijoCode;
    /** 受講経験 */
    private String jukoKeiken;
    /** 実務経験 */
    private String jitsumuKeiken;
    /** 決済状況区分 */
    private String kessaiJokyoKbn;
    /** 決済方法区分 */
    private String kessaiHohoKbn;
    /** 決済加盟店コード */
    private String kessaiKameitenCode;
    /** 決済取引コード */
    private String kessaiTorihikiCode;
    /** 決済コンビニ種別 */
    private String kessaiConvenienceShubetsu;
    /** 決済コンビニ払込票ＵＲＬ */
    private String kessaiConvenienceHaraikomihyoUrl;
    /** 決済期限日 */
    private String kessaiKigenBi;
    /** 決済金額 */
    private String kessaiKingaku;
    /** 仮受付日 */
    private String kariUketsukeBi;
    /** 仮受付時刻 */
    private String kariUketsukeTime;
    /** 申込完了日 */
    private String moshikomiFinishBi;
    /** 申込完了時刻 */
    private String moshikomiFinishTime;
    /** 申込メモ */
    private String moshikomiMemo;
    /** 管理メモ */
    private String kanriMemo;
    /** 画像ＩＤ */
    private String gazoId;
    /** 補正依頼区分 */
    private String hoseiIraiKbn;
    /** 補正依頼日 */
    private String hoseiIraiBi;
    /** 補正依頼時刻 */
    private String hoseiIraiTime;
    /** 補正対応日 */
    private String hoseiTaioBi;
    /** 補正対応時刻 */
    private String hoseiTaioTime;
    /** 補正完了日 */
    private String hoseiFinishBi;
    /** 補正完了時刻 */
    private String hoseiFinishTime;
    /** 決済督促メール送信フラグ */
    private String kessaiTokusokuMailSoshinFlg;
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
    protected MoshikomiDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedMoshikomi() {
        moshikomiUketsukeNo = "";
        nendo = "";
        kaisu = "";
        eventCode = "";
        moshikomiBaitaiKbn = "";
        kojinDantaiKbn = "";
        dantaiCode = "";
        dantaiMoshikomiUketsukeNo = "";
        userId = "";
        tetsudukiJokyoKbn = "";
        shikenchiCode = "";
        kaijoCode = "";
        jukoKeiken = "";
        jitsumuKeiken = "";
        kessaiJokyoKbn = "";
        kessaiHohoKbn = "";
        kessaiKameitenCode = "";
        kessaiTorihikiCode = "";
        kessaiConvenienceShubetsu = "";
        kessaiConvenienceHaraikomihyoUrl = "";
        kessaiKigenBi = "";
        kessaiKingaku = "";
        kariUketsukeBi = "";
        kariUketsukeTime = "";
        moshikomiFinishBi = "";
        moshikomiFinishTime = "";
        moshikomiMemo = "";
        kanriMemo = "";
        gazoId = "";
        hoseiIraiKbn = "";
        hoseiIraiBi = "";
        hoseiIraiTime = "";
        hoseiTaioBi = "";
        hoseiTaioTime = "";
        hoseiFinishBi = "";
        hoseiFinishTime = "";
        kessaiTokusokuMailSoshinFlg = "";
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
    public GeneratedMoshikomi(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getMoshikomiDao(connectionUser);
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
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi find(String moshikomiUketsukeNo, String nendo, String kaisu, String eventCode) {
        return find(moshikomiUketsukeNo, nendo, kaisu, eventCode, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi lockNoWait(String moshikomiUketsukeNo, String nendo, String kaisu, String eventCode) {
        return find(moshikomiUketsukeNo, nendo, kaisu, eventCode, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi lockWaitIfLocked(String moshikomiUketsukeNo, String nendo, String kaisu, String eventCode) {
        return find(moshikomiUketsukeNo, nendo, kaisu, eventCode, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Moshikomi find(String moshikomiUketsukeNo, String nendo, String kaisu, String eventCode, String lockMode) {
        Moshikomi bo = new Moshikomi();
        bo.setMoshikomiUketsukeNo(moshikomiUketsukeNo);
        bo.setNendo(nendo);
        bo.setKaisu(kaisu);
        bo.setEventCode(eventCode);
        bo = (Moshikomi) dao.find(bo, lockMode);
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
        sb.append(getMoshikomiUketsukeNo()).append(StringUtility.SEW_DELIMITER)
                .append(getNendo()).append(StringUtility.SEW_DELIMITER)
                .append(getKaisu()).append(StringUtility.SEW_DELIMITER)
                .append(getEventCode());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMoshikomiUketsukeNo()).append(StringUtility.SEW_DELIMITER)
                .append(getNendo()).append(StringUtility.SEW_DELIMITER)
                .append(getKaisu()).append(StringUtility.SEW_DELIMITER)
                .append(getEventCode()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomiBaitaiKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getKojinDantaiKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiCode()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiMoshikomiUketsukeNo()).append(StringUtility.SEW_DELIMITER)
                .append(getUserId()).append(StringUtility.SEW_DELIMITER)
                .append(getTetsudukiJokyoKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getShikenchiCode()).append(StringUtility.SEW_DELIMITER)
                .append(getKaijoCode()).append(StringUtility.SEW_DELIMITER)
                .append(getJukoKeiken()).append(StringUtility.SEW_DELIMITER)
                .append(getJitsumuKeiken()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiJokyoKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiHohoKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiKameitenCode()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiTorihikiCode()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiConvenienceShubetsu()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiConvenienceHaraikomihyoUrl()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiKigenBi()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiKingaku()).append(StringUtility.SEW_DELIMITER)
                .append(getKariUketsukeBi()).append(StringUtility.SEW_DELIMITER)
                .append(getKariUketsukeTime()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomiFinishBi()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomiFinishTime()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomiMemo()).append(StringUtility.SEW_DELIMITER)
                .append(getKanriMemo()).append(StringUtility.SEW_DELIMITER)
                .append(getGazoId()).append(StringUtility.SEW_DELIMITER)
                .append(getHoseiIraiKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getHoseiIraiBi()).append(StringUtility.SEW_DELIMITER)
                .append(getHoseiIraiTime()).append(StringUtility.SEW_DELIMITER)
                .append(getHoseiTaioBi()).append(StringUtility.SEW_DELIMITER)
                .append(getHoseiTaioTime()).append(StringUtility.SEW_DELIMITER)
                .append(getHoseiFinishBi()).append(StringUtility.SEW_DELIMITER)
                .append(getHoseiFinishTime()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiTokusokuMailSoshinFlg()).append(StringUtility.SEW_DELIMITER)
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
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 45);
        setMoshikomiUketsukeNo(values[0]);
        setNendo(values[1]);
        setKaisu(values[2]);
        setEventCode(values[3]);
        setMoshikomiBaitaiKbn(values[4]);
        setKojinDantaiKbn(values[5]);
        setDantaiCode(values[6]);
        setDantaiMoshikomiUketsukeNo(values[7]);
        setUserId(values[8]);
        setTetsudukiJokyoKbn(values[9]);
        setShikenchiCode(values[10]);
        setKaijoCode(values[11]);
        setJukoKeiken(values[12]);
        setJitsumuKeiken(values[13]);
        setKessaiJokyoKbn(values[14]);
        setKessaiHohoKbn(values[15]);
        setKessaiKameitenCode(values[16]);
        setKessaiTorihikiCode(values[17]);
        setKessaiConvenienceShubetsu(values[18]);
        setKessaiConvenienceHaraikomihyoUrl(values[19]);
        setKessaiKigenBi(values[20]);
        setKessaiKingaku(values[21]);
        setKariUketsukeBi(values[22]);
        setKariUketsukeTime(values[23]);
        setMoshikomiFinishBi(values[24]);
        setMoshikomiFinishTime(values[25]);
        setMoshikomiMemo(values[26]);
        setKanriMemo(values[27]);
        setGazoId(values[28]);
        setHoseiIraiKbn(values[29]);
        setHoseiIraiBi(values[30]);
        setHoseiIraiTime(values[31]);
        setHoseiTaioBi(values[32]);
        setHoseiTaioTime(values[33]);
        setHoseiFinishBi(values[34]);
        setHoseiFinishTime(values[35]);
        setKessaiTokusokuMailSoshinFlg(values[36]);
        setRonriSakujoFlg(values[37]);
        setShoriKbn(values[38]);
        setTorokuDate(values[39]);
        setTorokuTime(values[40]);
        setTorokuUserId(values[41]);
        setKoshinDate(values[42]);
        setKoshinTime(values[43]);
        setKoshinUserId(values[44]);
    }

    @Override
    public Object clone() {
        Moshikomi bo = (Moshikomi) super.clone();
        bo.setMoshikomiUketsukeNo(getMoshikomiUketsukeNo());
        bo.setNendo(getNendo());
        bo.setKaisu(getKaisu());
        bo.setEventCode(getEventCode());
        bo.setMoshikomiBaitaiKbn(getMoshikomiBaitaiKbn());
        bo.setKojinDantaiKbn(getKojinDantaiKbn());
        bo.setDantaiCode(getDantaiCode());
        bo.setDantaiMoshikomiUketsukeNo(getDantaiMoshikomiUketsukeNo());
        bo.setUserId(getUserId());
        bo.setTetsudukiJokyoKbn(getTetsudukiJokyoKbn());
        bo.setShikenchiCode(getShikenchiCode());
        bo.setKaijoCode(getKaijoCode());
        bo.setJukoKeiken(getJukoKeiken());
        bo.setJitsumuKeiken(getJitsumuKeiken());
        bo.setKessaiJokyoKbn(getKessaiJokyoKbn());
        bo.setKessaiHohoKbn(getKessaiHohoKbn());
        bo.setKessaiKameitenCode(getKessaiKameitenCode());
        bo.setKessaiTorihikiCode(getKessaiTorihikiCode());
        bo.setKessaiConvenienceShubetsu(getKessaiConvenienceShubetsu());
        bo.setKessaiConvenienceHaraikomihyoUrl(getKessaiConvenienceHaraikomihyoUrl());
        bo.setKessaiKigenBi(getKessaiKigenBi());
        bo.setKessaiKingaku(getKessaiKingaku());
        bo.setKariUketsukeBi(getKariUketsukeBi());
        bo.setKariUketsukeTime(getKariUketsukeTime());
        bo.setMoshikomiFinishBi(getMoshikomiFinishBi());
        bo.setMoshikomiFinishTime(getMoshikomiFinishTime());
        bo.setMoshikomiMemo(getMoshikomiMemo());
        bo.setKanriMemo(getKanriMemo());
        bo.setGazoId(getGazoId());
        bo.setHoseiIraiKbn(getHoseiIraiKbn());
        bo.setHoseiIraiBi(getHoseiIraiBi());
        bo.setHoseiIraiTime(getHoseiIraiTime());
        bo.setHoseiTaioBi(getHoseiTaioBi());
        bo.setHoseiTaioTime(getHoseiTaioTime());
        bo.setHoseiFinishBi(getHoseiFinishBi());
        bo.setHoseiFinishTime(getHoseiFinishTime());
        bo.setKessaiTokusokuMailSoshinFlg(getKessaiTokusokuMailSoshinFlg());
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
     * 申込受付番号 を取得する。
     * @return 申込受付番号
     */
    public String getMoshikomiUketsukeNo() {
        return moshikomiUketsukeNo;
    }

    /**
     * 申込受付番号 をセットする。
     * @param moshikomiUketsukeNo 申込受付番号
     */
    public void setMoshikomiUketsukeNo(String moshikomiUketsukeNo) {
        this.moshikomiUketsukeNo = moshikomiUketsukeNo;
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
     * 申込媒体区分 を取得する。
     * @return 申込媒体区分
     */
    public String getMoshikomiBaitaiKbn() {
        return moshikomiBaitaiKbn;
    }

    /**
     * 申込媒体区分 をセットする。
     * @param moshikomiBaitaiKbn 申込媒体区分
     */
    public void setMoshikomiBaitaiKbn(String moshikomiBaitaiKbn) {
        this.moshikomiBaitaiKbn = moshikomiBaitaiKbn;
    }

    /**
     * 個人団体区分 を取得する。
     * @return 個人団体区分
     */
    public String getKojinDantaiKbn() {
        return kojinDantaiKbn;
    }

    /**
     * 個人団体区分 をセットする。
     * @param kojinDantaiKbn 個人団体区分
     */
    public void setKojinDantaiKbn(String kojinDantaiKbn) {
        this.kojinDantaiKbn = kojinDantaiKbn;
    }

    /**
     * 団体コード を取得する。
     * @return 団体コード
     */
    public String getDantaiCode() {
        return dantaiCode;
    }

    /**
     * 団体コード をセットする。
     * @param dantaiCode 団体コード
     */
    public void setDantaiCode(String dantaiCode) {
        this.dantaiCode = dantaiCode;
    }

    /**
     * 団体申込受付番号 を取得する。
     * @return 団体申込受付番号
     */
    public String getDantaiMoshikomiUketsukeNo() {
        return dantaiMoshikomiUketsukeNo;
    }

    /**
     * 団体申込受付番号 をセットする。
     * @param dantaiMoshikomiUketsukeNo 団体申込受付番号
     */
    public void setDantaiMoshikomiUketsukeNo(String dantaiMoshikomiUketsukeNo) {
        this.dantaiMoshikomiUketsukeNo = dantaiMoshikomiUketsukeNo;
    }

    /**
     * ユーザーＩＤ を取得する。
     * @return ユーザーＩＤ
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーＩＤ をセットする。
     * @param userId ユーザーＩＤ
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 手続状況区分 を取得する。
     * @return 手続状況区分
     */
    public String getTetsudukiJokyoKbn() {
        return tetsudukiJokyoKbn;
    }

    /**
     * 手続状況区分 をセットする。
     * @param tetsudukiJokyoKbn 手続状況区分
     */
    public void setTetsudukiJokyoKbn(String tetsudukiJokyoKbn) {
        this.tetsudukiJokyoKbn = tetsudukiJokyoKbn;
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
     * 受講経験 を取得する。
     * @return 受講経験
     */
    public String getJukoKeiken() {
        return jukoKeiken;
    }

    /**
     * 受講経験 をセットする。
     * @param jukoKeiken 受講経験
     */
    public void setJukoKeiken(String jukoKeiken) {
        this.jukoKeiken = jukoKeiken;
    }

    /**
     * 実務経験 を取得する。
     * @return 実務経験
     */
    public String getJitsumuKeiken() {
        return jitsumuKeiken;
    }

    /**
     * 実務経験 をセットする。
     * @param jitsumuKeiken 実務経験
     */
    public void setJitsumuKeiken(String jitsumuKeiken) {
        this.jitsumuKeiken = jitsumuKeiken;
    }

    /**
     * 決済状況区分 を取得する。
     * @return 決済状況区分
     */
    public String getKessaiJokyoKbn() {
        return kessaiJokyoKbn;
    }

    /**
     * 決済状況区分 をセットする。
     * @param kessaiJokyoKbn 決済状況区分
     */
    public void setKessaiJokyoKbn(String kessaiJokyoKbn) {
        this.kessaiJokyoKbn = kessaiJokyoKbn;
    }

    /**
     * 決済方法区分 を取得する。
     * @return 決済方法区分
     */
    public String getKessaiHohoKbn() {
        return kessaiHohoKbn;
    }

    /**
     * 決済方法区分 をセットする。
     * @param kessaiHohoKbn 決済方法区分
     */
    public void setKessaiHohoKbn(String kessaiHohoKbn) {
        this.kessaiHohoKbn = kessaiHohoKbn;
    }

    /**
     * 決済加盟店コード を取得する。
     * @return 決済加盟店コード
     */
    public String getKessaiKameitenCode() {
        return kessaiKameitenCode;
    }

    /**
     * 決済加盟店コード をセットする。
     * @param kessaiKameitenCode 決済加盟店コード
     */
    public void setKessaiKameitenCode(String kessaiKameitenCode) {
        this.kessaiKameitenCode = kessaiKameitenCode;
    }

    /**
     * 決済取引コード を取得する。
     * @return 決済取引コード
     */
    public String getKessaiTorihikiCode() {
        return kessaiTorihikiCode;
    }

    /**
     * 決済取引コード をセットする。
     * @param kessaiTorihikiCode 決済取引コード
     */
    public void setKessaiTorihikiCode(String kessaiTorihikiCode) {
        this.kessaiTorihikiCode = kessaiTorihikiCode;
    }

    /**
     * 決済コンビニ種別 を取得する。
     * @return 決済コンビニ種別
     */
    public String getKessaiConvenienceShubetsu() {
        return kessaiConvenienceShubetsu;
    }

    /**
     * 決済コンビニ種別 をセットする。
     * @param kessaiConvenienceShubetsu 決済コンビニ種別
     */
    public void setKessaiConvenienceShubetsu(String kessaiConvenienceShubetsu) {
        this.kessaiConvenienceShubetsu = kessaiConvenienceShubetsu;
    }

    /**
     * 決済コンビニ払込票ＵＲＬ を取得する。
     * @return 決済コンビニ払込票ＵＲＬ
     */
    public String getKessaiConvenienceHaraikomihyoUrl() {
        return kessaiConvenienceHaraikomihyoUrl;
    }

    /**
     * 決済コンビニ払込票ＵＲＬ をセットする。
     * @param kessaiConvenienceHaraikomihyoUrl 決済コンビニ払込票ＵＲＬ
     */
    public void setKessaiConvenienceHaraikomihyoUrl(String kessaiConvenienceHaraikomihyoUrl) {
        this.kessaiConvenienceHaraikomihyoUrl = kessaiConvenienceHaraikomihyoUrl;
    }

    /**
     * 決済期限日 を取得する。
     * @return 決済期限日
     */
    public String getKessaiKigenBi() {
        return kessaiKigenBi;
    }

    /**
     * 決済期限日 をセットする。
     * @param kessaiKigenBi 決済期限日
     */
    public void setKessaiKigenBi(String kessaiKigenBi) {
        this.kessaiKigenBi = kessaiKigenBi;
    }

    /**
     * 決済金額 を取得する。
     * @return 決済金額
     */
    public String getKessaiKingaku() {
        return kessaiKingaku;
    }

    /**
     * 決済金額 をセットする。
     * @param kessaiKingaku 決済金額
     */
    public void setKessaiKingaku(String kessaiKingaku) {
        this.kessaiKingaku = kessaiKingaku;
    }

    /**
     * 仮受付日 を取得する。
     * @return 仮受付日
     */
    public String getKariUketsukeBi() {
        return kariUketsukeBi;
    }

    /**
     * 仮受付日 をセットする。
     * @param kariUketsukeBi 仮受付日
     */
    public void setKariUketsukeBi(String kariUketsukeBi) {
        this.kariUketsukeBi = kariUketsukeBi;
    }

    /**
     * 仮受付時刻 を取得する。
     * @return 仮受付時刻
     */
    public String getKariUketsukeTime() {
        return kariUketsukeTime;
    }

    /**
     * 仮受付時刻 をセットする。
     * @param kariUketsukeTime 仮受付時刻
     */
    public void setKariUketsukeTime(String kariUketsukeTime) {
        this.kariUketsukeTime = kariUketsukeTime;
    }

    /**
     * 申込完了日 を取得する。
     * @return 申込完了日
     */
    public String getMoshikomiFinishBi() {
        return moshikomiFinishBi;
    }

    /**
     * 申込完了日 をセットする。
     * @param moshikomiFinishBi 申込完了日
     */
    public void setMoshikomiFinishBi(String moshikomiFinishBi) {
        this.moshikomiFinishBi = moshikomiFinishBi;
    }

    /**
     * 申込完了時刻 を取得する。
     * @return 申込完了時刻
     */
    public String getMoshikomiFinishTime() {
        return moshikomiFinishTime;
    }

    /**
     * 申込完了時刻 をセットする。
     * @param moshikomiFinishTime 申込完了時刻
     */
    public void setMoshikomiFinishTime(String moshikomiFinishTime) {
        this.moshikomiFinishTime = moshikomiFinishTime;
    }

    /**
     * 申込メモ を取得する。
     * @return 申込メモ
     */
    public String getMoshikomiMemo() {
        return moshikomiMemo;
    }

    /**
     * 申込メモ をセットする。
     * @param moshikomiMemo 申込メモ
     */
    public void setMoshikomiMemo(String moshikomiMemo) {
        this.moshikomiMemo = moshikomiMemo;
    }

    /**
     * 管理メモ を取得する。
     * @return 管理メモ
     */
    public String getKanriMemo() {
        return kanriMemo;
    }

    /**
     * 管理メモ をセットする。
     * @param kanriMemo 管理メモ
     */
    public void setKanriMemo(String kanriMemo) {
        this.kanriMemo = kanriMemo;
    }

    /**
     * 画像ＩＤ を取得する。
     * @return 画像ＩＤ
     */
    public String getGazoId() {
        return gazoId;
    }

    /**
     * 画像ＩＤ をセットする。
     * @param gazoId 画像ＩＤ
     */
    public void setGazoId(String gazoId) {
        this.gazoId = gazoId;
    }

    /**
     * 補正依頼区分 を取得する。
     * @return 補正依頼区分
     */
    public String getHoseiIraiKbn() {
        return hoseiIraiKbn;
    }

    /**
     * 補正依頼区分 をセットする。
     * @param hoseiIraiKbn 補正依頼区分
     */
    public void setHoseiIraiKbn(String hoseiIraiKbn) {
        this.hoseiIraiKbn = hoseiIraiKbn;
    }

    /**
     * 補正依頼日 を取得する。
     * @return 補正依頼日
     */
    public String getHoseiIraiBi() {
        return hoseiIraiBi;
    }

    /**
     * 補正依頼日 をセットする。
     * @param hoseiIraiBi 補正依頼日
     */
    public void setHoseiIraiBi(String hoseiIraiBi) {
        this.hoseiIraiBi = hoseiIraiBi;
    }

    /**
     * 補正依頼時刻 を取得する。
     * @return 補正依頼時刻
     */
    public String getHoseiIraiTime() {
        return hoseiIraiTime;
    }

    /**
     * 補正依頼時刻 をセットする。
     * @param hoseiIraiTime 補正依頼時刻
     */
    public void setHoseiIraiTime(String hoseiIraiTime) {
        this.hoseiIraiTime = hoseiIraiTime;
    }

    /**
     * 補正対応日 を取得する。
     * @return 補正対応日
     */
    public String getHoseiTaioBi() {
        return hoseiTaioBi;
    }

    /**
     * 補正対応日 をセットする。
     * @param hoseiTaioBi 補正対応日
     */
    public void setHoseiTaioBi(String hoseiTaioBi) {
        this.hoseiTaioBi = hoseiTaioBi;
    }

    /**
     * 補正対応時刻 を取得する。
     * @return 補正対応時刻
     */
    public String getHoseiTaioTime() {
        return hoseiTaioTime;
    }

    /**
     * 補正対応時刻 をセットする。
     * @param hoseiTaioTime 補正対応時刻
     */
    public void setHoseiTaioTime(String hoseiTaioTime) {
        this.hoseiTaioTime = hoseiTaioTime;
    }

    /**
     * 補正完了日 を取得する。
     * @return 補正完了日
     */
    public String getHoseiFinishBi() {
        return hoseiFinishBi;
    }

    /**
     * 補正完了日 をセットする。
     * @param hoseiFinishBi 補正完了日
     */
    public void setHoseiFinishBi(String hoseiFinishBi) {
        this.hoseiFinishBi = hoseiFinishBi;
    }

    /**
     * 補正完了時刻 を取得する。
     * @return 補正完了時刻
     */
    public String getHoseiFinishTime() {
        return hoseiFinishTime;
    }

    /**
     * 補正完了時刻 をセットする。
     * @param hoseiFinishTime 補正完了時刻
     */
    public void setHoseiFinishTime(String hoseiFinishTime) {
        this.hoseiFinishTime = hoseiFinishTime;
    }

    /**
     * 決済督促メール送信フラグ を取得する。
     * @return 決済督促メール送信フラグ
     */
    public String getKessaiTokusokuMailSoshinFlg() {
        return kessaiTokusokuMailSoshinFlg;
    }

    /**
     * 決済督促メール送信フラグ をセットする。
     * @param kessaiTokusokuMailSoshinFlg 決済督促メール送信フラグ
     */
    public void setKessaiTokusokuMailSoshinFlg(String kessaiTokusokuMailSoshinFlg) {
        this.kessaiTokusokuMailSoshinFlg = kessaiTokusokuMailSoshinFlg;
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
