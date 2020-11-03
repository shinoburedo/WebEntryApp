package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MoshikomiDantai;
import jp.co.nii.miw.business.domain.MoshikomiDantaiDao;
import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.business.domain.LockException;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

/**
 * 申込団体 DAO実装クラス
 * @author DB管理ツール
 */
public class MoshikomiDantaiDaoImpl extends GeneratedMoshikomiDantaiDaoImpl implements MoshikomiDantaiDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public MoshikomiDantaiDaoImpl(String datasource) {
        super(datasource);
    }

//    @Override
//    public MoshikomiDantai find(MoshikomiDantai bo, String lockMode) {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        String sql = "";
//        try {
//            con = getConnection();
//            sql = "SELECT " + FIELDS_DECRYPT
//                    + " FROM " + getSchemaName() + "." + TABLE_NAME
//                    + " WHERE"
//                    + " MOSHIKOMI_DANTAI_CODE = ?"
//                    + " AND RONRI_SAKUJO_FLG = ? ";
//
//            stmt = con.prepareStatement(sql + lockMode);
//            int i = 1;
//            stmt.setString(i++, bo.getMoshikomiDantaiCode());
//            stmt.setString(i++, bo.getRonriSakujoFlg());
//
//            LogGenerate.debugOutput(getSql(stmt));
//            rs = stmt.executeQuery();
//            if (rs.next()) {
//                setBoFromResultSet(bo, rs);
//            } else {
//                bo = null;
//            }
//        } catch (SQLException ex) {
//            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
//        } finally {
//            close(con, stmt, rs);
//        }
//        return bo;
//    }
    @Override
    public MoshikomiDantai findRetry(MoshikomiDantai dantai) {
        MoshikomiDantai ret = null;
        for (int cnt = 1; cnt <= 5; cnt++) {
            try {
                if (TransactionUtility.isTheCurrentThreadAssociatedWithAnyTransaction()) {
                    setSavepoint("SAVE_POINT_BEFORE_SAIBAN_LOCK", null);
                }
                ret = (MoshikomiDantai) find(dantai, TransactionUtility.LOCK_NOWAIT);
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

    @Override
    public MoshikomiDantai selectMoshikomiDantaiForDntCode(String nendo, String kaisu, String dntCode, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MoshikomiDantai bo = new MoshikomiDantai();
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ? "
                    + " AND DANTAI_CODE = ? "
                    + " AND TETSUDUKI_JOKYO_KBN <> " + getSQLForEncryptByUTF8("?")
                    + " AND RONRI_SAKUJO_FLG = ? ";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, nendo);
            stmt.setString(i++, kaisu);
            stmt.setString(i++, dntCode);
            stmt.setString(i++, MiwConstants.TETSUDUKI_JOKYO_KBN_TORIKESHI);
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
    public MoshikomiDantai findLogin(MoshikomiDantai mDantai, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MoshikomiDantai bo = new MoshikomiDantai();
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ? "
                    + " AND DANTAI_MOSHIKOMI_UKETSUKE_NO = ? "
                    + " AND DANTAI_MOSHIKOMI_PASSWD = " + getSQLForEncryptByUTF8("?")
                    + " AND RONRI_SAKUJO_FLG = ? ";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, mDantai.getNendo());
            stmt.setString(i++, mDantai.getKaisu());
            stmt.setString(i++, mDantai.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, mDantai.getDantaiMoshikomiPasswd());
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
}
