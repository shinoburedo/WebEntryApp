package jp.co.nii.sew.business.domain;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.integration.DaoFactory;

/**
 * 生成された 郵便番号辞書 BO抽象クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public class GeneratedYubinBangoJisho extends AbstractBusinessObject{

    /** ＳＥＱ */
    private String seq;
    /** 郵便番号 */
    private String yubinNo;
    /** 県コード */
    private String kenCode;
    /** 都道府県名 */
    private String todofukenName;
    /** 市区町村名 */
    private String shikuchosonName;
    /** 町域名 */
    private String choikiName;
    /** 都道府県名カナ */
    private String todofukenNameKana;
    /** 市区町村名カナ */
    private String shikuchosonNameKana;
    /** 町域名カナ */
    private String choikiNameKana;
    /** 地方公共団体コード */
    private String chihoKokyoDantaiCode;
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
    protected YubinBangoJishoDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedYubinBangoJisho() {
        seq = "";
        yubinNo = "";
        kenCode = "";
        todofukenName = "";
        shikuchosonName = "";
        choikiName = "";
        todofukenNameKana = "";
        shikuchosonNameKana = "";
        choikiNameKana = "";
        chihoKokyoDantaiCode = "";
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
    public GeneratedYubinBangoJisho(String connectionUser) {
        this();
        dao = new DaoFactory().getYubinBangoJishoDao(connectionUser);
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
     * @param seq ＳＥＱ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public YubinBangoJisho find(String seq) {
        return find(seq, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param seq ＳＥＱ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public YubinBangoJisho lockNoWait(String seq) {
        return find(seq, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param seq ＳＥＱ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public YubinBangoJisho lockWaitIfLocked(String seq) {
        return find(seq, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param seq ＳＥＱ
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected YubinBangoJisho find(String seq, String lockMode) {
        YubinBangoJisho bo = new YubinBangoJisho();
        bo.setSeq(seq);
        bo = (YubinBangoJisho) dao.find(bo, lockMode);
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
        sb.append(getSeq());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSeq()).append(StringUtility.SEW_DELIMITER)
                .append(getYubinNo()).append(StringUtility.SEW_DELIMITER)
                .append(getKenCode()).append(StringUtility.SEW_DELIMITER)
                .append(getTodofukenName()).append(StringUtility.SEW_DELIMITER)
                .append(getShikuchosonName()).append(StringUtility.SEW_DELIMITER)
                .append(getChoikiName()).append(StringUtility.SEW_DELIMITER)
                .append(getTodofukenNameKana()).append(StringUtility.SEW_DELIMITER)
                .append(getShikuchosonNameKana()).append(StringUtility.SEW_DELIMITER)
                .append(getChoikiNameKana()).append(StringUtility.SEW_DELIMITER)
                .append(getChihoKokyoDantaiCode()).append(StringUtility.SEW_DELIMITER)
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
        setSeq(values[0]);
        setYubinNo(values[1]);
        setKenCode(values[2]);
        setTodofukenName(values[3]);
        setShikuchosonName(values[4]);
        setChoikiName(values[5]);
        setTodofukenNameKana(values[6]);
        setShikuchosonNameKana(values[7]);
        setChoikiNameKana(values[8]);
        setChihoKokyoDantaiCode(values[9]);
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
        YubinBangoJisho bo = (YubinBangoJisho) super.clone();
        bo.setSeq(getSeq());
        bo.setYubinNo(getYubinNo());
        bo.setKenCode(getKenCode());
        bo.setTodofukenName(getTodofukenName());
        bo.setShikuchosonName(getShikuchosonName());
        bo.setChoikiName(getChoikiName());
        bo.setTodofukenNameKana(getTodofukenNameKana());
        bo.setShikuchosonNameKana(getShikuchosonNameKana());
        bo.setChoikiNameKana(getChoikiNameKana());
        bo.setChihoKokyoDantaiCode(getChihoKokyoDantaiCode());
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
     * 郵便番号 を取得する。
     * @return 郵便番号
     */
    public String getYubinNo() {
        return yubinNo;
    }

    /**
     * 郵便番号 をセットする。
     * @param yubinNo 郵便番号
     */
    public void setYubinNo(String yubinNo) {
        this.yubinNo = yubinNo;
    }

    /**
     * 県コード を取得する。
     * @return 県コード
     */
    public String getKenCode() {
        return kenCode;
    }

    /**
     * 県コード をセットする。
     * @param kenCode 県コード
     */
    public void setKenCode(String kenCode) {
        this.kenCode = kenCode;
    }

    /**
     * 都道府県名 を取得する。
     * @return 都道府県名
     */
    public String getTodofukenName() {
        return todofukenName;
    }

    /**
     * 都道府県名 をセットする。
     * @param todofukenName 都道府県名
     */
    public void setTodofukenName(String todofukenName) {
        this.todofukenName = todofukenName;
    }

    /**
     * 市区町村名 を取得する。
     * @return 市区町村名
     */
    public String getShikuchosonName() {
        return shikuchosonName;
    }

    /**
     * 市区町村名 をセットする。
     * @param shikuchosonName 市区町村名
     */
    public void setShikuchosonName(String shikuchosonName) {
        this.shikuchosonName = shikuchosonName;
    }

    /**
     * 町域名 を取得する。
     * @return 町域名
     */
    public String getChoikiName() {
        return choikiName;
    }

    /**
     * 町域名 をセットする。
     * @param choikiName 町域名
     */
    public void setChoikiName(String choikiName) {
        this.choikiName = choikiName;
    }

    /**
     * 都道府県名カナ を取得する。
     * @return 都道府県名カナ
     */
    public String getTodofukenNameKana() {
        return todofukenNameKana;
    }

    /**
     * 都道府県名カナ をセットする。
     * @param todofukenNameKana 都道府県名カナ
     */
    public void setTodofukenNameKana(String todofukenNameKana) {
        this.todofukenNameKana = todofukenNameKana;
    }

    /**
     * 市区町村名カナ を取得する。
     * @return 市区町村名カナ
     */
    public String getShikuchosonNameKana() {
        return shikuchosonNameKana;
    }

    /**
     * 市区町村名カナ をセットする。
     * @param shikuchosonNameKana 市区町村名カナ
     */
    public void setShikuchosonNameKana(String shikuchosonNameKana) {
        this.shikuchosonNameKana = shikuchosonNameKana;
    }

    /**
     * 町域名カナ を取得する。
     * @return 町域名カナ
     */
    public String getChoikiNameKana() {
        return choikiNameKana;
    }

    /**
     * 町域名カナ をセットする。
     * @param choikiNameKana 町域名カナ
     */
    public void setChoikiNameKana(String choikiNameKana) {
        this.choikiNameKana = choikiNameKana;
    }

    /**
     * 地方公共団体コード を取得する。
     * @return 地方公共団体コード
     */
    public String getChihoKokyoDantaiCode() {
        return chihoKokyoDantaiCode;
    }

    /**
     * 地方公共団体コード をセットする。
     * @param chihoKokyoDantaiCode 地方公共団体コード
     */
    public void setChihoKokyoDantaiCode(String chihoKokyoDantaiCode) {
        this.chihoKokyoDantaiCode = chihoKokyoDantaiCode;
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
