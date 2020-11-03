package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された 決済ログ BO抽象クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public class GeneratedKessaiLog extends AbstractBusinessObject{

    /** 送受信日付 */
    private String soujushinDate;
    /** 送受信時刻 */
    private String soujushinTime;
    /** ＳＥＱ */
    private String seq;
    /** 申込受付番号 */
    private String moshikomiUketsukeNo;
    /** 年度 */
    private String nendo;
    /** 回数 */
    private String kaisu;
    /** イベントコード */
    private String eventCode;
    /** ユーザーＩＤ */
    private String userId;
    /** 決済代行会社コード */
    private String kessaiDaikouKaishaCode;
    /** メッセージ種別 */
    private String messageShubetsu;
    /** メッセージ本体 */
    private String messageHontai;
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
    protected KessaiLogDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedKessaiLog() {
        soujushinDate = "";
        soujushinTime = "";
        seq = "";
        moshikomiUketsukeNo = "";
        nendo = "";
        kaisu = "";
        eventCode = "";
        userId = "";
        kessaiDaikouKaishaCode = "";
        messageShubetsu = "";
        messageHontai = "";
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
    public GeneratedKessaiLog(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getKessaiLogDao(connectionUser);
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
     * @param soujushinDate 送受信日付
     * @param soujushinTime 送受信時刻
     * @param seq ＳＥＱ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public KessaiLog find(String soujushinDate, String soujushinTime, String seq) {
        return find(soujushinDate, soujushinTime, seq, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param soujushinDate 送受信日付
     * @param soujushinTime 送受信時刻
     * @param seq ＳＥＱ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public KessaiLog lockNoWait(String soujushinDate, String soujushinTime, String seq) {
        return find(soujushinDate, soujushinTime, seq, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param soujushinDate 送受信日付
     * @param soujushinTime 送受信時刻
     * @param seq ＳＥＱ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public KessaiLog lockWaitIfLocked(String soujushinDate, String soujushinTime, String seq) {
        return find(soujushinDate, soujushinTime, seq, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param soujushinDate 送受信日付
     * @param soujushinTime 送受信時刻
     * @param seq ＳＥＱ
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected KessaiLog find(String soujushinDate, String soujushinTime, String seq, String lockMode) {
        KessaiLog bo = new KessaiLog();
        bo.setSoujushinDate(soujushinDate);
        bo.setSoujushinTime(soujushinTime);
        bo.setSeq(seq);
        bo = (KessaiLog) dao.find(bo, lockMode);
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
        sb.append(getSoujushinDate()).append(StringUtility.SEW_DELIMITER)
                .append(getSoujushinTime()).append(StringUtility.SEW_DELIMITER)
                .append(getSeq());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSoujushinDate()).append(StringUtility.SEW_DELIMITER)
                .append(getSoujushinTime()).append(StringUtility.SEW_DELIMITER)
                .append(getSeq()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomiUketsukeNo()).append(StringUtility.SEW_DELIMITER)
                .append(getNendo()).append(StringUtility.SEW_DELIMITER)
                .append(getKaisu()).append(StringUtility.SEW_DELIMITER)
                .append(getEventCode()).append(StringUtility.SEW_DELIMITER)
                .append(getUserId()).append(StringUtility.SEW_DELIMITER)
                .append(getKessaiDaikouKaishaCode()).append(StringUtility.SEW_DELIMITER)
                .append(getMessageShubetsu()).append(StringUtility.SEW_DELIMITER)
                .append(getMessageHontai()).append(StringUtility.SEW_DELIMITER)
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
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 19);
        setSoujushinDate(values[0]);
        setSoujushinTime(values[1]);
        setSeq(values[2]);
        setMoshikomiUketsukeNo(values[3]);
        setNendo(values[4]);
        setKaisu(values[5]);
        setEventCode(values[6]);
        setUserId(values[7]);
        setKessaiDaikouKaishaCode(values[8]);
        setMessageShubetsu(values[9]);
        setMessageHontai(values[10]);
        setRonriSakujoFlg(values[11]);
        setShoriKbn(values[12]);
        setTorokuDate(values[13]);
        setTorokuTime(values[14]);
        setTorokuUserId(values[15]);
        setKoshinDate(values[16]);
        setKoshinTime(values[17]);
        setKoshinUserId(values[18]);
    }

    @Override
    public Object clone() {
        KessaiLog bo = (KessaiLog) super.clone();
        bo.setSoujushinDate(getSoujushinDate());
        bo.setSoujushinTime(getSoujushinTime());
        bo.setSeq(getSeq());
        bo.setMoshikomiUketsukeNo(getMoshikomiUketsukeNo());
        bo.setNendo(getNendo());
        bo.setKaisu(getKaisu());
        bo.setEventCode(getEventCode());
        bo.setUserId(getUserId());
        bo.setKessaiDaikouKaishaCode(getKessaiDaikouKaishaCode());
        bo.setMessageShubetsu(getMessageShubetsu());
        bo.setMessageHontai(getMessageHontai());
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
     * 送受信日付 を取得する。
     * @return 送受信日付
     */
    public String getSoujushinDate() {
        return soujushinDate;
    }

    /**
     * 送受信日付 をセットする。
     * @param soujushinDate 送受信日付
     */
    public void setSoujushinDate(String soujushinDate) {
        this.soujushinDate = soujushinDate;
    }

    /**
     * 送受信時刻 を取得する。
     * @return 送受信時刻
     */
    public String getSoujushinTime() {
        return soujushinTime;
    }

    /**
     * 送受信時刻 をセットする。
     * @param soujushinTime 送受信時刻
     */
    public void setSoujushinTime(String soujushinTime) {
        this.soujushinTime = soujushinTime;
    }

    /**
     * ＳＥＱ を取得する。
     * @return ＳＥＱ
     */
    public String getSeq() {
        return seq;
    }

    /**
     * ＳＥＱ をセットする。
     * @param seq ＳＥＱ
     */
    public void setSeq(String seq) {
        this.seq = seq;
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
     * 決済代行会社コード を取得する。
     * @return 決済代行会社コード
     */
    public String getKessaiDaikouKaishaCode() {
        return kessaiDaikouKaishaCode;
    }

    /**
     * 決済代行会社コード をセットする。
     * @param kessaiDaikouKaishaCode 決済代行会社コード
     */
    public void setKessaiDaikouKaishaCode(String kessaiDaikouKaishaCode) {
        this.kessaiDaikouKaishaCode = kessaiDaikouKaishaCode;
    }

    /**
     * メッセージ種別 を取得する。
     * @return メッセージ種別
     */
    public String getMessageShubetsu() {
        return messageShubetsu;
    }

    /**
     * メッセージ種別 をセットする。
     * @param messageShubetsu メッセージ種別
     */
    public void setMessageShubetsu(String messageShubetsu) {
        this.messageShubetsu = messageShubetsu;
    }

    /**
     * メッセージ本体 を取得する。
     * @return メッセージ本体
     */
    public String getMessageHontai() {
        return messageHontai;
    }

    /**
     * メッセージ本体 をセットする。
     * @param messageHontai メッセージ本体
     */
    public void setMessageHontai(String messageHontai) {
        this.messageHontai = messageHontai;
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
