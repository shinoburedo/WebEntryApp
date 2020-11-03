package jp.co.nii.miw.integration;

import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.domain.MoshikomishaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.miw.business.domain.GeneratedMoshikomi;
import jp.co.nii.miw.business.domain.GeneratedMoshikomisha;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.business.domain.LockException;
import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * 申込者 DAO実装クラス
 * @author DB管理ツール
 */
public class MoshikomishaDaoImpl extends GeneratedMoshikomishaDaoImpl implements MoshikomishaDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public MoshikomishaDaoImpl(String datasource) {
        super(datasource);
    }

    @Override
    public Moshikomisha find(Moshikomisha bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " USER_ID = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getRonriSakujoFlg());

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

    /**
     * 検索する。<br>
     * 検索キーとしてＩＤ・パスワードを指定する。
     * @param userID ＩＤ
     * @param nendo 年度
     * @param ki 季
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Moshikomisha lockFind(String userId, String lockMode) {
        Moshikomisha bo = new Moshikomisha();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " USER_ID = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, userId);
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

    /**
     * 検索する。<br>
     * 検索キーとしてＩＤ・パスワードを指定する。
     * @param userID ＩＤ
     * @param passwd パスワード
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Moshikomisha findLogin(String userId, String passwd, String lockMode) {
        Moshikomisha bo = new Moshikomisha();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " USER_ID = ?"
                    + " AND"
                    + " PASSWD = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, userId);
            stmt.setString(i++, passwd);
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

    /**
     * 検索する。<br>
     * 検索キーとしてＩＤ・申込有フラグを指定する。
     * @param userID ＩＤ
     * @param moshikomiAriFlg 申込有フラグ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Moshikomisha findDouble(String userId, String moshikomiAriFlg, String lockMode) {
        Moshikomisha bo = new Moshikomisha();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " USER_ID = ?"
                    + " AND"
                    + " MOSHIKOMI_ARI_FLG = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, userId);
            stmt.setString(i++, moshikomiAriFlg);
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
    public Moshikomisha findDoubleForShimeiEtc(MskToroku mskInf, String lockMode) {
        Moshikomisha bo = new Moshikomisha();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " SHIMEI_SEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " SHIMEI_MEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " BIRTHDAY = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " ( TEL_NO = " + getSQLForEncryptByUTF8("?") + " OR CELLPHONE_NO = " +  getSQLForEncryptByUTF8("?") + " )"
                    + " AND"
                    + " MOSHIKOMI_ARI_FLG = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, mskInf.getShimeiSeiKana());
            stmt.setString(i++, mskInf.getShimeiMeiKana());
            stmt.setString(i++, mskInf.getBirthday());
            stmt.setString(i++, MiwStringUtility.getConcateWithParenthesis(mskInf.getTelNo(),mskInf.getExtNo()));
            stmt.setString(i++, mskInf.getCellphoneNo());
            stmt.setString(i++, MiwConstants.FLG_ON);
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

    /**
     * 検索する。<br>
     * 検索キー：ＩＤ・カナ氏名・生年月日・申込有フラグ・論理削除フラグ
     * @param moshikomisha 
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Moshikomisha findForPswRem(Moshikomisha moshikomisha, String lockMode) {
        Moshikomisha bo = new Moshikomisha();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " USER_ID = ?"
                    + " AND"
                    + " SHIMEI_SEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " SHIMEI_MEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " BIRTHDAY = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " MOSHIKOMI_ARI_FLG = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, moshikomisha.getUserId());
            stmt.setString(i++, moshikomisha.getShimeiSeiKana());
            stmt.setString(i++, moshikomisha.getShimeiMeiKana());
            stmt.setString(i++, moshikomisha.getBirthday());
            stmt.setString(i++, moshikomisha.getMoshikomiAriFlg());
            stmt.setString(i++, moshikomisha.getRonriSakujoFlg());

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

    /* (non-Javadoc)
     * @see jp.co.nii.how.business.domain.GeneratedAnketoKaitoDao#find(jp.co.nii.how.business.domain.GeneratedAnketoKaito, java.lang.String)
     */
    @Override
    public Moshikomisha findRetry(Moshikomisha moshikomisha) {
        Moshikomisha ret = null;
        for (int cnt = 1; cnt <= 5; cnt++) {
            try {
                if (TransactionUtility.isTheCurrentThreadAssociatedWithAnyTransaction()) {
                    setSavepoint("SAVE_POINT_BEFORE_SAIBAN_LOCK", null);
                }
                ret = (Moshikomisha) find(moshikomisha, TransactionUtility.LOCK_NOWAIT);
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
    
    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomishaDao#update(jp.co.nii.miw.business.domain.GeneratedMoshikomisha)
     */
    @Override
    public void updateAfterKessai(Moshikomisha bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " MOSHIKOMI_ARI_FLG = " + getSQLForEncryptByUTF8("?")
                    + ",SHORI_KBN = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " USER_ID = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiAriFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getUserId());

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
