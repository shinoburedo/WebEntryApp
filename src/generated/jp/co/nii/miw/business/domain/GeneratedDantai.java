package jp.co.nii.miw.business.domain;
import jp.co.nii.sew.business.domain.AbstractBusinessObject;
import jp.co.nii.miw.integration.MiwDaoFactory;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 生成された 団体 BO抽象クラス<br>
 * table-design-ver 2
 * @author DB管理ツール
 */
public class GeneratedDantai extends AbstractBusinessObject{

    /** 団体コード */
    private String dantaiCode;
    /** 団体パスワード */
    private String dantaiPasswd;
    /** 団体名 */
    private String dantaiName;
    /** 団体名カナ */
    private String dantaiNameKana;
    /** 団体郵便番号 */
    private String dantaiYubinNo;
    /** 団体住所 */
    private String dantaiJusho;
    /** 団体事務担当者氏名（姓）カナ */
    private String dantaiJimuTantoshaShimeiSeiKana;
    /** 団体事務担当者氏名（名）カナ */
    private String dantaiJimuTantoshaShimeiMeiKana;
    /** 団体事務担当者氏名（姓） */
    private String dantaiJimuTantoshaShimeiSei;
    /** 団体事務担当者氏名（名） */
    private String dantaiJimuTantoshaShimeiMei;
    /** 団体事務担当者電話番号 */
    private String dantaiJimuTantoshaTelNo;
    /** 団体事務担当者ＦＡＸ番号 */
    private String dantaiJimuTantoshaFaxNo;
    /** 団体事務担当者メールアドレス */
    private String dantaiJimuTantoshaMailAddress;
    /** 発送先区分 */
    private String hassosakiKbn;
    /** パスワード質問コード１ */
    private String passwdQuestionCode1;
    /** パスワード質問コード２ */
    private String passwdQuestionCode2;
    /** パスワード回答１ */
    private String passwdAnswer1;
    /** パスワード回答２ */
    private String passwdAnswer2;
    /** ホスト用団体ＳＥＱ */
    private String hostYoDantaiSeq;
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
    protected DantaiDao dao;

    /**
     * コンストラクタ
     */
    public GeneratedDantai() {
        dantaiCode = "";
        dantaiPasswd = "";
        dantaiName = "";
        dantaiNameKana = "";
        dantaiYubinNo = "";
        dantaiJusho = "";
        dantaiJimuTantoshaShimeiSeiKana = "";
        dantaiJimuTantoshaShimeiMeiKana = "";
        dantaiJimuTantoshaShimeiSei = "";
        dantaiJimuTantoshaShimeiMei = "";
        dantaiJimuTantoshaTelNo = "";
        dantaiJimuTantoshaFaxNo = "";
        dantaiJimuTantoshaMailAddress = "";
        hassosakiKbn = "";
        passwdQuestionCode1 = "";
        passwdQuestionCode2 = "";
        passwdAnswer1 = "";
        passwdAnswer2 = "";
        hostYoDantaiSeq = "";
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
    public GeneratedDantai(String connectionUser) {
        this();
        dao = new MiwDaoFactory().getDantaiDao(connectionUser);
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
     * @param dantaiCode 団体コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai find(String dantaiCode) {
        return find(dantaiCode, TransactionUtility.NO_LOCK);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待たない。
     * @param dantaiCode 団体コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai lockNoWait(String dantaiCode) {
        return find(dantaiCode, TransactionUtility.LOCK_NOWAIT);
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param dantaiCode 団体コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai lockWaitIfLocked(String dantaiCode) {
        return find(dantaiCode, TransactionUtility.LOCK_WAIT);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param dantaiCode 団体コード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Dantai find(String dantaiCode, String lockMode) {
        Dantai bo = new Dantai();
        bo.setDantaiCode(dantaiCode);
        bo = (Dantai) dao.find(bo, lockMode);
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
        sb.append(getDantaiCode());
        return sb.toString();
    }

    /**
     * 全フィールドの値をもつタブ区切りの文字列を取得する。
     * @return 全フィールドの値をもつタブ区切りの文字列
     */
    public String getAllFieldsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDantaiCode()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiPasswd()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiName()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiNameKana()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiYubinNo()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiJusho()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiJimuTantoshaShimeiSeiKana()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiJimuTantoshaShimeiMeiKana()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiJimuTantoshaShimeiSei()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiJimuTantoshaShimeiMei()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiJimuTantoshaTelNo()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiJimuTantoshaFaxNo()).append(StringUtility.SEW_DELIMITER)
                .append(getDantaiJimuTantoshaMailAddress()).append(StringUtility.SEW_DELIMITER)
                .append(getHassosakiKbn()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswdQuestionCode1()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswdQuestionCode2()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswdAnswer1()).append(StringUtility.SEW_DELIMITER)
                .append(getPasswdAnswer2()).append(StringUtility.SEW_DELIMITER)
                .append(getHostYoDantaiSeq()).append(StringUtility.SEW_DELIMITER)
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
        String[] values = tabSeparatedValue.split(StringUtility.SEW_DELIMITER, 27);
        setDantaiCode(values[0]);
        setDantaiPasswd(values[1]);
        setDantaiName(values[2]);
        setDantaiNameKana(values[3]);
        setDantaiYubinNo(values[4]);
        setDantaiJusho(values[5]);
        setDantaiJimuTantoshaShimeiSeiKana(values[6]);
        setDantaiJimuTantoshaShimeiMeiKana(values[7]);
        setDantaiJimuTantoshaShimeiSei(values[8]);
        setDantaiJimuTantoshaShimeiMei(values[9]);
        setDantaiJimuTantoshaTelNo(values[10]);
        setDantaiJimuTantoshaFaxNo(values[11]);
        setDantaiJimuTantoshaMailAddress(values[12]);
        setHassosakiKbn(values[13]);
        setPasswdQuestionCode1(values[14]);
        setPasswdQuestionCode2(values[15]);
        setPasswdAnswer1(values[16]);
        setPasswdAnswer2(values[17]);
        setHostYoDantaiSeq(values[18]);
        setRonriSakujoFlg(values[19]);
        setShoriKbn(values[20]);
        setTorokuDate(values[21]);
        setTorokuTime(values[22]);
        setTorokuUserId(values[23]);
        setKoshinDate(values[24]);
        setKoshinTime(values[25]);
        setKoshinUserId(values[26]);
    }

    @Override
    public Object clone() {
        Dantai bo = (Dantai) super.clone();
        bo.setDantaiCode(getDantaiCode());
        bo.setDantaiPasswd(getDantaiPasswd());
        bo.setDantaiName(getDantaiName());
        bo.setDantaiNameKana(getDantaiNameKana());
        bo.setDantaiYubinNo(getDantaiYubinNo());
        bo.setDantaiJusho(getDantaiJusho());
        bo.setDantaiJimuTantoshaShimeiSeiKana(getDantaiJimuTantoshaShimeiSeiKana());
        bo.setDantaiJimuTantoshaShimeiMeiKana(getDantaiJimuTantoshaShimeiMeiKana());
        bo.setDantaiJimuTantoshaShimeiSei(getDantaiJimuTantoshaShimeiSei());
        bo.setDantaiJimuTantoshaShimeiMei(getDantaiJimuTantoshaShimeiMei());
        bo.setDantaiJimuTantoshaTelNo(getDantaiJimuTantoshaTelNo());
        bo.setDantaiJimuTantoshaFaxNo(getDantaiJimuTantoshaFaxNo());
        bo.setDantaiJimuTantoshaMailAddress(getDantaiJimuTantoshaMailAddress());
        bo.setHassosakiKbn(getHassosakiKbn());
        bo.setPasswdQuestionCode1(getPasswdQuestionCode1());
        bo.setPasswdQuestionCode2(getPasswdQuestionCode2());
        bo.setPasswdAnswer1(getPasswdAnswer1());
        bo.setPasswdAnswer2(getPasswdAnswer2());
        bo.setHostYoDantaiSeq(getHostYoDantaiSeq());
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
     * 団体パスワード を取得する。
     * @return 団体パスワード
     */
    public String getDantaiPasswd() {
        return dantaiPasswd;
    }

    /**
     * 団体パスワード をセットする。
     * @param dantaiPasswd 団体パスワード
     */
    public void setDantaiPasswd(String dantaiPasswd) {
        this.dantaiPasswd = dantaiPasswd;
    }

    /**
     * 団体名 を取得する。
     * @return 団体名
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * 団体名 をセットする。
     * @param dantaiName 団体名
     */
    public void setDantaiName(String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /**
     * 団体名カナ を取得する。
     * @return 団体名カナ
     */
    public String getDantaiNameKana() {
        return dantaiNameKana;
    }

    /**
     * 団体名カナ をセットする。
     * @param dantaiNameKana 団体名カナ
     */
    public void setDantaiNameKana(String dantaiNameKana) {
        this.dantaiNameKana = dantaiNameKana;
    }

    /**
     * 団体郵便番号 を取得する。
     * @return 団体郵便番号
     */
    public String getDantaiYubinNo() {
        return dantaiYubinNo;
    }

    /**
     * 団体郵便番号 をセットする。
     * @param dantaiYubinNo 団体郵便番号
     */
    public void setDantaiYubinNo(String dantaiYubinNo) {
        this.dantaiYubinNo = dantaiYubinNo;
    }

    /**
     * 団体住所 を取得する。
     * @return 団体住所
     */
    public String getDantaiJusho() {
        return dantaiJusho;
    }

    /**
     * 団体住所 をセットする。
     * @param dantaiJusho 団体住所
     */
    public void setDantaiJusho(String dantaiJusho) {
        this.dantaiJusho = dantaiJusho;
    }

    /**
     * 団体事務担当者氏名（姓）カナ を取得する。
     * @return 団体事務担当者氏名（姓）カナ
     */
    public String getDantaiJimuTantoshaShimeiSeiKana() {
        return dantaiJimuTantoshaShimeiSeiKana;
    }

    /**
     * 団体事務担当者氏名（姓）カナ をセットする。
     * @param dantaiJimuTantoshaShimeiSeiKana 団体事務担当者氏名（姓）カナ
     */
    public void setDantaiJimuTantoshaShimeiSeiKana(String dantaiJimuTantoshaShimeiSeiKana) {
        this.dantaiJimuTantoshaShimeiSeiKana = dantaiJimuTantoshaShimeiSeiKana;
    }

    /**
     * 団体事務担当者氏名（名）カナ を取得する。
     * @return 団体事務担当者氏名（名）カナ
     */
    public String getDantaiJimuTantoshaShimeiMeiKana() {
        return dantaiJimuTantoshaShimeiMeiKana;
    }

    /**
     * 団体事務担当者氏名（名）カナ をセットする。
     * @param dantaiJimuTantoshaShimeiMeiKana 団体事務担当者氏名（名）カナ
     */
    public void setDantaiJimuTantoshaShimeiMeiKana(String dantaiJimuTantoshaShimeiMeiKana) {
        this.dantaiJimuTantoshaShimeiMeiKana = dantaiJimuTantoshaShimeiMeiKana;
    }

    /**
     * 団体事務担当者氏名（姓） を取得する。
     * @return 団体事務担当者氏名（姓）
     */
    public String getDantaiJimuTantoshaShimeiSei() {
        return dantaiJimuTantoshaShimeiSei;
    }

    /**
     * 団体事務担当者氏名（姓） をセットする。
     * @param dantaiJimuTantoshaShimeiSei 団体事務担当者氏名（姓）
     */
    public void setDantaiJimuTantoshaShimeiSei(String dantaiJimuTantoshaShimeiSei) {
        this.dantaiJimuTantoshaShimeiSei = dantaiJimuTantoshaShimeiSei;
    }

    /**
     * 団体事務担当者氏名（名） を取得する。
     * @return 団体事務担当者氏名（名）
     */
    public String getDantaiJimuTantoshaShimeiMei() {
        return dantaiJimuTantoshaShimeiMei;
    }

    /**
     * 団体事務担当者氏名（名） をセットする。
     * @param dantaiJimuTantoshaShimeiMei 団体事務担当者氏名（名）
     */
    public void setDantaiJimuTantoshaShimeiMei(String dantaiJimuTantoshaShimeiMei) {
        this.dantaiJimuTantoshaShimeiMei = dantaiJimuTantoshaShimeiMei;
    }

    /**
     * 団体事務担当者電話番号 を取得する。
     * @return 団体事務担当者電話番号
     */
    public String getDantaiJimuTantoshaTelNo() {
        return dantaiJimuTantoshaTelNo;
    }

    /**
     * 団体事務担当者電話番号 をセットする。
     * @param dantaiJimuTantoshaTelNo 団体事務担当者電話番号
     */
    public void setDantaiJimuTantoshaTelNo(String dantaiJimuTantoshaTelNo) {
        this.dantaiJimuTantoshaTelNo = dantaiJimuTantoshaTelNo;
    }

    /**
     * 団体事務担当者ＦＡＸ番号 を取得する。
     * @return 団体事務担当者ＦＡＸ番号
     */
    public String getDantaiJimuTantoshaFaxNo() {
        return dantaiJimuTantoshaFaxNo;
    }

    /**
     * 団体事務担当者ＦＡＸ番号 をセットする。
     * @param dantaiJimuTantoshaFaxNo 団体事務担当者ＦＡＸ番号
     */
    public void setDantaiJimuTantoshaFaxNo(String dantaiJimuTantoshaFaxNo) {
        this.dantaiJimuTantoshaFaxNo = dantaiJimuTantoshaFaxNo;
    }

    /**
     * 団体事務担当者メールアドレス を取得する。
     * @return 団体事務担当者メールアドレス
     */
    public String getDantaiJimuTantoshaMailAddress() {
        return dantaiJimuTantoshaMailAddress;
    }

    /**
     * 団体事務担当者メールアドレス をセットする。
     * @param dantaiJimuTantoshaMailAddress 団体事務担当者メールアドレス
     */
    public void setDantaiJimuTantoshaMailAddress(String dantaiJimuTantoshaMailAddress) {
        this.dantaiJimuTantoshaMailAddress = dantaiJimuTantoshaMailAddress;
    }

    /**
     * 発送先区分 を取得する。
     * @return 発送先区分
     */
    public String getHassosakiKbn() {
        return hassosakiKbn;
    }

    /**
     * 発送先区分 をセットする。
     * @param hassosakiKbn 発送先区分
     */
    public void setHassosakiKbn(String hassosakiKbn) {
        this.hassosakiKbn = hassosakiKbn;
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
     * ホスト用団体ＳＥＱ を取得する。
     * @return ホスト用団体ＳＥＱ
     */
    public String getHostYoDantaiSeq() {
        return hostYoDantaiSeq;
    }

    /**
     * ホスト用団体ＳＥＱ をセットする。
     * @param hostYoDantaiSeq ホスト用団体ＳＥＱ
     */
    public void setHostYoDantaiSeq(String hostYoDantaiSeq) {
        this.hostYoDantaiSeq = hostYoDantaiSeq;
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
