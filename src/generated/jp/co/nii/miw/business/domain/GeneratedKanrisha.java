package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された 管理者 BO抽象クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public class GeneratedKanrisha extends AbstractBusinessObject{

    /** 管理ユーザーＩＤ */
    private String kanriUserId;
    /** 表示名 */
    private String hyojiName;
    /** パスワード */
    private String passwd;
    /** 権限コード */
    private String kengenCode;
    /** メールアドレス */
    private String mailAddress;
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
    protected KanrishaDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedKanrisha() {
        kanriUserId = "";
        hyojiName = "";
        passwd = "";
        kengenCode = "";
        mailAddress = "";
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
    public GeneratedKanrisha(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getKanrishaDao(connectionUser);
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
     * @param kanriUserId 管理ユーザーＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Kanrisha find(String kanriUserId) {
        return find(kanriUserId, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param kanriUserId 管理ユーザーＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Kanrisha lockNoWait(String kanriUserId) {
        return find(kanriUserId, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param kanriUserId 管理ユーザーＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Kanrisha lockWaitIfLocked(String kanriUserId) {
        return find(kanriUserId, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param kanriUserId 管理ユーザーＩＤ
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Kanrisha find(String kanriUserId, String lockMode) {
        Kanrisha bo = new Kanrisha();
        bo.setKanriUserId(kanriUserId);
        bo = (Kanrisha) dao.find(bo, lockMode);
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
        sb.append(getKanriUserId());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKanriUserId()).append(StringUtility.SEW_DELIMITER)
                .append(getHyojiName()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswd()).append(StringUtility.SEW_DELIMITER)
                .append(getKengenCode()).append(StringUtility.SEW_DELIMITER)
                .append(getMailAddress()).append(StringUtility.SEW_DELIMITER)
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
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 13);
        setKanriUserId(values[0]);
        setHyojiName(values[1]);
        setPasswd(values[2]);
        setKengenCode(values[3]);
        setMailAddress(values[4]);
        setRonriSakujoFlg(values[5]);
        setShoriKbn(values[6]);
        setTorokuDate(values[7]);
        setTorokuTime(values[8]);
        setTorokuUserId(values[9]);
        setKoshinDate(values[10]);
        setKoshinTime(values[11]);
        setKoshinUserId(values[12]);
    }

    @Override
    public Object clone() {
        Kanrisha bo = (Kanrisha) super.clone();
        bo.setKanriUserId(getKanriUserId());
        bo.setHyojiName(getHyojiName());
        bo.setPasswd(getPasswd());
        bo.setKengenCode(getKengenCode());
        bo.setMailAddress(getMailAddress());
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
     * 管理ユーザーＩＤ を取得する。
     * @return 管理ユーザーＩＤ
     */
    public String getKanriUserId() {
        return kanriUserId;
    }

    /**
     * 管理ユーザーＩＤ をセットする。
     * @param kanriUserId 管理ユーザーＩＤ
     */
    public void setKanriUserId(String kanriUserId) {
        this.kanriUserId = kanriUserId;
    }

    /**
     * 表示名 を取得する。
     * @return 表示名
     */
    public String getHyojiName() {
        return hyojiName;
    }

    /**
     * 表示名 をセットする。
     * @param hyojiName 表示名
     */
    public void setHyojiName(String hyojiName) {
        this.hyojiName = hyojiName;
    }

    /**
     * パスワード を取得する。
     * @return パスワード
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * パスワード をセットする。
     * @param passwd パスワード
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * 権限コード を取得する。
     * @return 権限コード
     */
    public String getKengenCode() {
        return kengenCode;
    }

    /**
     * 権限コード をセットする。
     * @param kengenCode 権限コード
     */
    public void setKengenCode(String kengenCode) {
        this.kengenCode = kengenCode;
    }

    /**
     * メールアドレス を取得する。
     * @return メールアドレス
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * メールアドレス をセットする。
     * @param mailAddress メールアドレス
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
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
