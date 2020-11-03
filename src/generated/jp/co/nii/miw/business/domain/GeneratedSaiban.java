package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された 採番 BO抽象クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public class GeneratedSaiban extends AbstractBusinessObject{

    /** 番号ＩＤ */
    private String noId;
    /** 現在番号 */
    private String genzaiNo;
    /** 番号下限 */
    private String noKagen;
    /** 番号上限 */
    private String noJogen;
    /** 自動ローテートフラグ */
    private String autoRotateFlg;
    /** 接頭文字列 */
    private String settoString;
    /** 接尾文字列 */
    private String setsubiString;
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
    protected SaibanDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedSaiban() {
        noId = "";
        genzaiNo = "";
        noKagen = "";
        noJogen = "";
        autoRotateFlg = "";
        settoString = "";
        setsubiString = "";
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
    public GeneratedSaiban(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getSaibanDao(connectionUser);
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
     * @param noId 番号ＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Saiban find(String noId) {
        return find(noId, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param noId 番号ＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Saiban lockNoWait(String noId) {
        return find(noId, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param noId 番号ＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Saiban lockWaitIfLocked(String noId) {
        return find(noId, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param noId 番号ＩＤ
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Saiban find(String noId, String lockMode) {
        Saiban bo = new Saiban();
        bo.setNoId(noId);
        bo = (Saiban) dao.find(bo, lockMode);
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
        sb.append(getNoId());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNoId()).append(StringUtility.SEW_DELIMITER)
                .append(getGenzaiNo()).append(StringUtility.SEW_DELIMITER)
                .append(getNoKagen()).append(StringUtility.SEW_DELIMITER)
                .append(getNoJogen()).append(StringUtility.SEW_DELIMITER)
                .append(getAutoRotateFlg()).append(StringUtility.SEW_DELIMITER)
                .append(getSettoString()).append(StringUtility.SEW_DELIMITER)
                .append(getSetsubiString()).append(StringUtility.SEW_DELIMITER)
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
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 15);
        setNoId(values[0]);
        setGenzaiNo(values[1]);
        setNoKagen(values[2]);
        setNoJogen(values[3]);
        setAutoRotateFlg(values[4]);
        setSettoString(values[5]);
        setSetsubiString(values[6]);
        setRonriSakujoFlg(values[7]);
        setShoriKbn(values[8]);
        setTorokuDate(values[9]);
        setTorokuTime(values[10]);
        setTorokuUserId(values[11]);
        setKoshinDate(values[12]);
        setKoshinTime(values[13]);
        setKoshinUserId(values[14]);
    }

    @Override
    public Object clone() {
        Saiban bo = (Saiban) super.clone();
        bo.setNoId(getNoId());
        bo.setGenzaiNo(getGenzaiNo());
        bo.setNoKagen(getNoKagen());
        bo.setNoJogen(getNoJogen());
        bo.setAutoRotateFlg(getAutoRotateFlg());
        bo.setSettoString(getSettoString());
        bo.setSetsubiString(getSetsubiString());
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
     * 番号ＩＤ を取得する。
     * @return 番号ＩＤ
     */
    public String getNoId() {
        return noId;
    }

    /**
     * 番号ＩＤ をセットする。
     * @param noId 番号ＩＤ
     */
    public void setNoId(String noId) {
        this.noId = noId;
    }

    /**
     * 現在番号 を取得する。
     * @return 現在番号
     */
    public String getGenzaiNo() {
        return genzaiNo;
    }

    /**
     * 現在番号 をセットする。
     * @param genzaiNo 現在番号
     */
    public void setGenzaiNo(String genzaiNo) {
        this.genzaiNo = genzaiNo;
    }

    /**
     * 番号下限 を取得する。
     * @return 番号下限
     */
    public String getNoKagen() {
        return noKagen;
    }

    /**
     * 番号下限 をセットする。
     * @param noKagen 番号下限
     */
    public void setNoKagen(String noKagen) {
        this.noKagen = noKagen;
    }

    /**
     * 番号上限 を取得する。
     * @return 番号上限
     */
    public String getNoJogen() {
        return noJogen;
    }

    /**
     * 番号上限 をセットする。
     * @param noJogen 番号上限
     */
    public void setNoJogen(String noJogen) {
        this.noJogen = noJogen;
    }

    /**
     * 自動ローテートフラグ を取得する。
     * @return 自動ローテートフラグ
     */
    public String getAutoRotateFlg() {
        return autoRotateFlg;
    }

    /**
     * 自動ローテートフラグ をセットする。
     * @param autoRotateFlg 自動ローテートフラグ
     */
    public void setAutoRotateFlg(String autoRotateFlg) {
        this.autoRotateFlg = autoRotateFlg;
    }

    /**
     * 接頭文字列 を取得する。
     * @return 接頭文字列
     */
    public String getSettoString() {
        return settoString;
    }

    /**
     * 接頭文字列 をセットする。
     * @param settoString 接頭文字列
     */
    public void setSettoString(String settoString) {
        this.settoString = settoString;
    }

    /**
     * 接尾文字列 を取得する。
     * @return 接尾文字列
     */
    public String getSetsubiString() {
        return setsubiString;
    }

    /**
     * 接尾文字列 をセットする。
     * @param setsubiString 接尾文字列
     */
    public void setSetsubiString(String setsubiString) {
        this.setsubiString = setsubiString;
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
