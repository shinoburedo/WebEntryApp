package jp.co.nii.miw.integration;

import jp.co.nii.miw.business.MiwConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jp.co.nii.miw.business.domain.DantaiDao;
import jp.co.nii.miw.business.domain.Dantai;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.business.domain.LockException;
import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * 団体 DAO実装クラス
 * @author DB管理ツール
 */
public class DantaiDaoImpl extends GeneratedDantaiDaoImpl implements DantaiDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public DantaiDaoImpl(String datasource) {
        super(datasource);
    }

    @Override
    public Dantai findLogin(String dntCd, String dntPswd, String lockMode) {
        Dantai bo = new Dantai();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " DANTAI_CODE = ?"
                    + " AND"
                    + " DANTAI_PASSWD = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, dntCd);
            stmt.setString(i++, dntPswd);
            stmt.setString(i++, MiwConstants.FLG_OFF);

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            if (rs.next()) {
                setBoFromResultSet(bo, rs);
            } else {
                bo = null;
            }

        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }

        return bo;

    }

    @Override
    public Dantai findRetry(Dantai dantai) {
        Dantai ret = null;
        for (int cnt = 1; cnt <= 5; cnt++) {
            try {
                if (TransactionUtility.isTheCurrentThreadAssociatedWithAnyTransaction()) {
                    setSavepoint("SAVE_POINT_BEFORE_SAIBAN_LOCK", null);
                }
                ret = (Dantai) find(dantai, TransactionUtility.LOCK_NOWAIT);
                break;

            } catch (LockException ex) {
                //ロールバック
                if (TransactionUtility.isTheCurrentThreadAssociatedWithAnyTransaction()) {
                    rollbackToSavepoint("SAVE_POINT_BEFORE_SAIBAN_LOCK", null);
                }
                // 他のトランザクションで更新されていたときは5回までリトライ
                LogGenerate.debugOutput(this.getClass().getName(),
                        " ロック取得エラー　 : " + ex.getErrorCode() + " " + cnt + " 回目 ");

                if (cnt >= 5) {
                    LogGenerate.errorOutput(this.getClass().getName(),
                            "　ロック取得リトライ回数オーバー");
                    throw new SEWException("テーブルロック取得エラー", ex);

                } else {
                    // １秒待ってからリトライ
                    LogGenerate.debugOutput(this.getClass().getName(),
                            "リトライ");
                    synchronized (this) {
                        try {
                            wait(5000);
                        } catch (InterruptedException iex) {
//                                //ロールバック
//                                rollbackTransaction();
                            throw new SEWException("テーブルロック取得例外発生", iex);
                        }
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 検索する。<br>
     * 検索キー：団体コード・団体事務担当者氏名カナ・論理削除フラグ
     * @param dantai 
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Dantai findForPswRem(Dantai dantai, String lockMode) {
        Dantai bo = new Dantai();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " DANTAI_CODE = ?"
                    + " AND"
                    + " DANTAI_JIMU_TANTOSHA_SHIMEI_SEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " DANTAI_JIMU_TANTOSHA_SHIMEI_MEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, dantai.getDantaiCode());
            stmt.setString(i++, dantai.getDantaiJimuTantoshaShimeiSeiKana());
            stmt.setString(i++, dantai.getDantaiJimuTantoshaShimeiMeiKana());
            stmt.setString(i++, dantai.getRonriSakujoFlg());

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            if (rs.next()) {
                setBoFromResultSet(bo, rs);
            } else {
                bo = null;
            }

        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }

        return bo;
    }

    @Override
    public void updateForShutugan(Dantai bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " DANTAI_NAME = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_NAME_KANA = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_YUBIN_NO = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_JUSHO = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_JIMU_TANTOSHA_SHIMEI_SEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_JIMU_TANTOSHA_SHIMEI_MEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_JIMU_TANTOSHA_SHIMEI_SEI = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_JIMU_TANTOSHA_SHIMEI_MEI = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_JIMU_TANTOSHA_TEL_NO = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_JIMU_TANTOSHA_FAX_NO = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_JIMU_TANTOSHA_MAIL_ADDRESS = " + getSQLForEncryptByUTF8("?")
                    + ",HASSOSAKI_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD_QUESTION_CODE_1 = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD_ANSWER_1 = " + getSQLForEncryptByUTF8("?")
                    + ",SHORI_KBN = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " DANTAI_CODE = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getDantaiName());
            stmt.setString(i++, bo.getDantaiNameKana());
            stmt.setString(i++, bo.getDantaiYubinNo());
            stmt.setString(i++, bo.getDantaiJusho());
            stmt.setString(i++, bo.getDantaiJimuTantoshaShimeiSeiKana());
            stmt.setString(i++, bo.getDantaiJimuTantoshaShimeiMeiKana());
            stmt.setString(i++, bo.getDantaiJimuTantoshaShimeiSei());
            stmt.setString(i++, bo.getDantaiJimuTantoshaShimeiMei());
            stmt.setString(i++, bo.getDantaiJimuTantoshaTelNo());
            stmt.setString(i++, bo.getDantaiJimuTantoshaFaxNo());
            stmt.setString(i++, bo.getDantaiJimuTantoshaMailAddress());
            stmt.setString(i++, bo.getHassosakiKbn());
            stmt.setString(i++, bo.getPasswdQuestionCode1());
            stmt.setString(i++, bo.getPasswdAnswer1());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getDantaiCode());

            LogGenerate.debugOutput(getSql(stmt));
            if (stmt.executeUpdate() == 0) {
                throw new NoSuchDataException(getSql(stmt));
            }
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt);
        }
    }
}
