package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された 申込者 BO抽象クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public class GeneratedMoshikomisha extends AbstractBusinessObject{

    /** ユーザーＩＤ */
    private String userId;
    /** 申込有フラグ */
    private String moshikomiAriFlg;
    /** メールアドレス */
    private String mailAddress;
    /** パスワード */
    private String passwd;
    /** 氏名（姓）カナ */
    private String shimeiSeiKana;
    /** 氏名（名）カナ */
    private String shimeiMeiKana;
    /** 氏名（姓） */
    private String shimeiSei;
    /** 氏名（名） */
    private String shimeiMei;
    /** 生年月日 */
    private String birthday;
    /** 性別コード */
    private String sexCode;
    /** 電話番号 */
    private String telNo;
    /** 携帯電話番号 */
    private String cellphoneNo;
    /** 郵便番号 */
    private String yubinNo;
    /** 住所 */
    private String jusho;
    /** パスワード質問コード１ */
    private String passwdQuestionCode1;
    /** パスワード質問コード２ */
    private String passwdQuestionCode2;
    /** パスワード回答１ */
    private String passwdAnswer1;
    /** パスワード回答２ */
    private String passwdAnswer2;
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
    protected MoshikomishaDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedMoshikomisha() {
        userId = "";
        moshikomiAriFlg = "";
        mailAddress = "";
        passwd = "";
        shimeiSeiKana = "";
        shimeiMeiKana = "";
        shimeiSei = "";
        shimeiMei = "";
        birthday = "";
        sexCode = "";
        telNo = "";
        cellphoneNo = "";
        yubinNo = "";
        jusho = "";
        passwdQuestionCode1 = "";
        passwdQuestionCode2 = "";
        passwdAnswer1 = "";
        passwdAnswer2 = "";
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
    public GeneratedMoshikomisha(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getMoshikomishaDao(connectionUser);
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
     * @param userId ユーザーＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha find(String userId) {
        return find(userId, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param userId ユーザーＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha lockNoWait(String userId) {
        return find(userId, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param userId ユーザーＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha lockWaitIfLocked(String userId) {
        return find(userId, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param userId ユーザーＩＤ
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Moshikomisha find(String userId, String lockMode) {
        Moshikomisha bo = new Moshikomisha();
        bo.setUserId(userId);
        bo = (Moshikomisha) dao.find(bo, lockMode);
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
        sb.append(getUserId());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getUserId()).append(StringUtility.SEW_DELIMITER)
                .append(getMoshikomiAriFlg()).append(StringUtility.SEW_DELIMITER)
                .append(getMailAddress()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswd()).append(StringUtility.SEW_DELIMITER)
                .append(getShimeiSeiKana()).append(StringUtility.SEW_DELIMITER)
                .append(getShimeiMeiKana()).append(StringUtility.SEW_DELIMITER)
                .append(getShimeiSei()).append(StringUtility.SEW_DELIMITER)
                .append(getShimeiMei()).append(StringUtility.SEW_DELIMITER)
                .append(getBirthday()).append(StringUtility.SEW_DELIMITER)
                .append(getSexCode()).append(StringUtility.SEW_DELIMITER)
                .append(getTelNo()).append(StringUtility.SEW_DELIMITER)
                .append(getCellphoneNo()).append(StringUtility.SEW_DELIMITER)
                .append(getYubinNo()).append(StringUtility.SEW_DELIMITER)
                .append(getJusho()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswdQuestionCode1()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswdQuestionCode2()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswdAnswer1()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswdAnswer2()).append(StringUtility.SEW_DELIMITER)
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
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 26);
        setUserId(values[0]);
        setMoshikomiAriFlg(values[1]);
        setMailAddress(values[2]);
        setPasswd(values[3]);
        setShimeiSeiKana(values[4]);
        setShimeiMeiKana(values[5]);
        setShimeiSei(values[6]);
        setShimeiMei(values[7]);
        setBirthday(values[8]);
        setSexCode(values[9]);
        setTelNo(values[10]);
        setCellphoneNo(values[11]);
        setYubinNo(values[12]);
        setJusho(values[13]);
        setPasswdQuestionCode1(values[14]);
        setPasswdQuestionCode2(values[15]);
        setPasswdAnswer1(values[16]);
        setPasswdAnswer2(values[17]);
        setRonriSakujoFlg(values[18]);
        setShoriKbn(values[19]);
        setTorokuDate(values[20]);
        setTorokuTime(values[21]);
        setTorokuUserId(values[22]);
        setKoshinDate(values[23]);
        setKoshinTime(values[24]);
        setKoshinUserId(values[25]);
    }

    @Override
    public Object clone() {
        Moshikomisha bo = (Moshikomisha) super.clone();
        bo.setUserId(getUserId());
        bo.setMoshikomiAriFlg(getMoshikomiAriFlg());
        bo.setMailAddress(getMailAddress());
        bo.setPasswd(getPasswd());
        bo.setShimeiSeiKana(getShimeiSeiKana());
        bo.setShimeiMeiKana(getShimeiMeiKana());
        bo.setShimeiSei(getShimeiSei());
        bo.setShimeiMei(getShimeiMei());
        bo.setBirthday(getBirthday());
        bo.setSexCode(getSexCode());
        bo.setTelNo(getTelNo());
        bo.setCellphoneNo(getCellphoneNo());
        bo.setYubinNo(getYubinNo());
        bo.setJusho(getJusho());
        bo.setPasswdQuestionCode1(getPasswdQuestionCode1());
        bo.setPasswdQuestionCode2(getPasswdQuestionCode2());
        bo.setPasswdAnswer1(getPasswdAnswer1());
        bo.setPasswdAnswer2(getPasswdAnswer2());
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
     * 申込有フラグ を取得する。
     * @return 申込有フラグ
     */
    public String getMoshikomiAriFlg() {
        return moshikomiAriFlg;
    }

    /**
     * 申込有フラグ をセットする。
     * @param moshikomiAriFlg 申込有フラグ
     */
    public void setMoshikomiAriFlg(String moshikomiAriFlg) {
        this.moshikomiAriFlg = moshikomiAriFlg;
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
     * 氏名（姓）カナ を取得する。
     * @return 氏名（姓）カナ
     */
    public String getShimeiSeiKana() {
        return shimeiSeiKana;
    }

    /**
     * 氏名（姓）カナ をセットする。
     * @param shimeiSeiKana 氏名（姓）カナ
     */
    public void setShimeiSeiKana(String shimeiSeiKana) {
        this.shimeiSeiKana = shimeiSeiKana;
    }

    /**
     * 氏名（名）カナ を取得する。
     * @return 氏名（名）カナ
     */
    public String getShimeiMeiKana() {
        return shimeiMeiKana;
    }

    /**
     * 氏名（名）カナ をセットする。
     * @param shimeiMeiKana 氏名（名）カナ
     */
    public void setShimeiMeiKana(String shimeiMeiKana) {
        this.shimeiMeiKana = shimeiMeiKana;
    }

    /**
     * 氏名（姓） を取得する。
     * @return 氏名（姓）
     */
    public String getShimeiSei() {
        return shimeiSei;
    }

    /**
     * 氏名（姓） をセットする。
     * @param shimeiSei 氏名（姓）
     */
    public void setShimeiSei(String shimeiSei) {
        this.shimeiSei = shimeiSei;
    }

    /**
     * 氏名（名） を取得する。
     * @return 氏名（名）
     */
    public String getShimeiMei() {
        return shimeiMei;
    }

    /**
     * 氏名（名） をセットする。
     * @param shimeiMei 氏名（名）
     */
    public void setShimeiMei(String shimeiMei) {
        this.shimeiMei = shimeiMei;
    }

    /**
     * 生年月日 を取得する。
     * @return 生年月日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 生年月日 をセットする。
     * @param birthday 生年月日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 性別コード を取得する。
     * @return 性別コード
     */
    public String getSexCode() {
        return sexCode;
    }

    /**
     * 性別コード をセットする。
     * @param sexCode 性別コード
     */
    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    /**
     * 電話番号 を取得する。
     * @return 電話番号
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * 電話番号 をセットする。
     * @param telNo 電話番号
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * 携帯電話番号 を取得する。
     * @return 携帯電話番号
     */
    public String getCellphoneNo() {
        return cellphoneNo;
    }

    /**
     * 携帯電話番号 をセットする。
     * @param cellphoneNo 携帯電話番号
     */
    public void setCellphoneNo(String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
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
     * パスワード質問コード１ を取得する。
     * @return パスワード質問コード１
     */
    public String getPasswdQuestionCode1() {
        return passwdQuestionCode1;
    }

    /**
     * パスワード質問コード１ をセットする。
     * @param passwdQuestionCode1 パスワード質問コード１
     */
    public void setPasswdQuestionCode1(String passwdQuestionCode1) {
        this.passwdQuestionCode1 = passwdQuestionCode1;
    }

    /**
     * パスワード質問コード２ を取得する。
     * @return パスワード質問コード２
     */
    public String getPasswdQuestionCode2() {
        return passwdQuestionCode2;
    }

    /**
     * パスワード質問コード２ をセットする。
     * @param passwdQuestionCode2 パスワード質問コード２
     */
    public void setPasswdQuestionCode2(String passwdQuestionCode2) {
        this.passwdQuestionCode2 = passwdQuestionCode2;
    }

    /**
     * パスワード回答１ を取得する。
     * @return パスワード回答１
     */
    public String getPasswdAnswer1() {
        return passwdAnswer1;
    }

    /**
     * パスワード回答１ をセットする。
     * @param passwdAnswer1 パスワード回答１
     */
    public void setPasswdAnswer1(String passwdAnswer1) {
        this.passwdAnswer1 = passwdAnswer1;
    }

    /**
     * パスワード回答２ を取得する。
     * @return パスワード回答２
     */
    public String getPasswdAnswer2() {
        return passwdAnswer2;
    }

    /**
     * パスワード回答２ をセットする。
     * @param passwdAnswer2 パスワード回答２
     */
    public void setPasswdAnswer2(String passwdAnswer2) {
        this.passwdAnswer2 = passwdAnswer2;
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
