package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された 申込団体 BO抽象クラス<br>
 * table-design-ver 2
 * @author DB管理ツール
 */
public class GeneratedMoshikomiDantai extends AbstractBusinessObject{

    /** 団体申込受付番号 */
    private String dantaiMoshikomiUketsukeNo;
    /** 年度 */
    private String nendo;
    /** 回数 */
    private String kaisu;
    /** 申込媒体区分 */
    private String moshikomiBaitaiKbn;
    /** 団体コード */
    private String dantaiCode;
    /** 団体申込パスワード */
    private String dantaiMoshikomiPasswd;
    /** 申込者数（医科） */
    private String moshikomishaSuIka;
    /** 申込者数（歯科） */
    private String moshikomishaSuShika;
    /** 手続状況区分 */
    private String tetsudukiJokyoKbn;
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
    protected MoshikomiDantaiDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedMoshikomiDantai() {
        dantaiMoshikomiUketsukeNo = "";
        nendo = "";
        kaisu = "";
        moshikomiBaitaiKbn = "";
        dantaiCode = "";
        dantaiMoshikomiPasswd = "";
        moshikomishaSuIka = "";
        moshikomishaSuShika = "";
        tetsudukiJokyoKbn = "";
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
    public GeneratedMoshikomiDantai(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getMoshikomiDantaiDao(connectionUser);
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
     * @param dantaiMoshikomiUketsukeNo 団体申込受付番号
     * @param nendo 年度
     * @param kaisu 回数
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai find(String dantaiMoshikomiUketsukeNo, String nendo, String kaisu) {
        return find(dantaiMoshikomiUketsukeNo, nendo, kaisu, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param dantaiMoshikomiUketsukeNo 団体申込受付番号
     * @param nendo 年度
     * @param kaisu 回数
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai lockNoWait(String dantaiMoshikomiUketsukeNo, String nendo, String kaisu) {
        return find(dantaiMoshikomiUketsukeNo, nendo, kaisu, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param dantaiMoshikomiUketsukeNo 団体申込受付番号
     * @param nendo 年度
     * @param kaisu 回数
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai lockWaitIfLocked(String dantaiMoshikomiUketsukeNo, String nendo, String kaisu) {
        return find(dantaiMoshikomiUketsukeNo, nendo, kaisu, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param dantaiMoshikomiUketsukeNo 団体申込受付番号
     * @param nendo 年度
     * @param kaisu 回数
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected MoshikomiDantai find(String dantaiMoshikomiUketsukeNo, String nendo, String kaisu, String lockMode) {
        MoshikomiDantai bo = new MoshikomiDantai();
        bo.setDantaiMoshikomiUketsukeNo(dantaiMoshikomiUketsukeNo);
        bo.setNendo(nendo);
        bo.setKaisu(kaisu);
        bo = (MoshikomiDantai) dao.find(bo, lockMode);
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
        sb.append(getDantaiMoshikomiUketsukeNo()).append(StringUtility.SEW_DELIMITER)
                .append(getNendo()).append(StringUtility.SEW_DELIMITER)
                .append(getKaisu());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDantaiMoshikomiUketsukeNo()).append(StringUtility.SEW_DELIMITER)
                .append(getNendo()).append(StringUtility.SEW_DELIMITER)
                .append(getKaisu()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomiBaitaiKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiCode()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiMoshikomiPasswd()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomishaSuIka()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomishaSuShika()).append(StringUtility.SEW_DELIMITER)
                .append(getTetsudukiJokyoKbn()).append(StringUtility.SEW_DELIMITER)
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
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 32);
        setDantaiMoshikomiUketsukeNo(values[0]);
        setNendo(values[1]);
        setKaisu(values[2]);
        setMoshikomiBaitaiKbn(values[3]);
        setDantaiCode(values[4]);
        setDantaiMoshikomiPasswd(values[5]);
        setMoshikomishaSuIka(values[6]);
        setMoshikomishaSuShika(values[7]);
        setTetsudukiJokyoKbn(values[8]);
        setKessaiJokyoKbn(values[9]);
        setKessaiHohoKbn(values[10]);
        setKessaiKameitenCode(values[11]);
        setKessaiTorihikiCode(values[12]);
        setKessaiConvenienceShubetsu(values[13]);
        setKessaiConvenienceHaraikomihyoUrl(values[14]);
        setKessaiKigenBi(values[15]);
        setKessaiKingaku(values[16]);
        setKariUketsukeBi(values[17]);
        setKariUketsukeTime(values[18]);
        setMoshikomiFinishBi(values[19]);
        setMoshikomiFinishTime(values[20]);
        setMoshikomiMemo(values[21]);
        setKanriMemo(values[22]);
        setKessaiTokusokuMailSoshinFlg(values[23]);
        setRonriSakujoFlg(values[24]);
        setShoriKbn(values[25]);
        setTorokuDate(values[26]);
        setTorokuTime(values[27]);
        setTorokuUserId(values[28]);
        setKoshinDate(values[29]);
        setKoshinTime(values[30]);
        setKoshinUserId(values[31]);
    }

    @Override
    public Object clone() {
        MoshikomiDantai bo = (MoshikomiDantai) super.clone();
        bo.setDantaiMoshikomiUketsukeNo(getDantaiMoshikomiUketsukeNo());
        bo.setNendo(getNendo());
        bo.setKaisu(getKaisu());
        bo.setMoshikomiBaitaiKbn(getMoshikomiBaitaiKbn());
        bo.setDantaiCode(getDantaiCode());
        bo.setDantaiMoshikomiPasswd(getDantaiMoshikomiPasswd());
        bo.setMoshikomishaSuIka(getMoshikomishaSuIka());
        bo.setMoshikomishaSuShika(getMoshikomishaSuShika());
        bo.setTetsudukiJokyoKbn(getTetsudukiJokyoKbn());
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
     * 団体申込パスワード を取得する。
     * @return 団体申込パスワード
     */
    public String getDantaiMoshikomiPasswd() {
        return dantaiMoshikomiPasswd;
    }

    /**
     * 団体申込パスワード をセットする。
     * @param dantaiMoshikomiPasswd 団体申込パスワード
     */
    public void setDantaiMoshikomiPasswd(String dantaiMoshikomiPasswd) {
        this.dantaiMoshikomiPasswd = dantaiMoshikomiPasswd;
    }

    /**
     * 申込者数（医科） を取得する。
     * @return 申込者数（医科）
     */
    public String getMoshikomishaSuIka() {
        return moshikomishaSuIka;
    }

    /**
     * 申込者数（医科） をセットする。
     * @param moshikomishaSuIka 申込者数（医科）
     */
    public void setMoshikomishaSuIka(String moshikomishaSuIka) {
        this.moshikomishaSuIka = moshikomishaSuIka;
    }

    /**
     * 申込者数（歯科） を取得する。
     * @return 申込者数（歯科）
     */
    public String getMoshikomishaSuShika() {
        return moshikomishaSuShika;
    }

    /**
     * 申込者数（歯科） をセットする。
     * @param moshikomishaSuShika 申込者数（歯科）
     */
    public void setMoshikomishaSuShika(String moshikomishaSuShika) {
        this.moshikomishaSuShika = moshikomishaSuShika;
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
