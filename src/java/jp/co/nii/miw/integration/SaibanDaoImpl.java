package jp.co.nii.miw.integration;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.GeneratedSaiban;
import jp.co.nii.miw.business.domain.Saiban;
import jp.co.nii.miw.business.domain.SaibanDao;
import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.business.domain.LockException;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

/**
 * 採番 DAO実装クラス
 * @author DB管理ツール
 */
public class SaibanDaoImpl extends GeneratedSaibanDaoImpl implements SaibanDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public SaibanDaoImpl(String datasource) {
        super(datasource);
    }
    
    /* (non-Javadoc)
     * @see jp.co.nii.how.business.domain.GeneratedSaibanDao#find(jp.co.nii.how.business.domain.GeneratedSaiban, java.lang.String)
     */
    @Override
    public Saiban findRetry(Saiban bo) {
        Saiban ret = null;
        for (int cnt = 1; cnt <= 5; cnt++) {
            try {
                if (TransactionUtility.isTheCurrentThreadAssociatedWithAnyTransaction()) {
                    setSavepoint("SAVE_POINT_BEFORE_SAIBAN_LOCK", null);
                }
                ret = (Saiban) find(bo, TransactionUtility.LOCK_NOWAIT);
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
                    throw new SEWException("採番テーブルロック取得エラー", ex);

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
                            throw new SEWException("採番テーブルロック取得例外発生", iex);
                        }
                    }
                }
            }
        }
        return ret;
    }
    
     /* (non-Javadoc)
     * @see jp.co.nii.mia.business.domain.GeneratedSaibanDao#find(jp.co.nii.mia.business.domain.GeneratedSaiban, java.lang.String)
     */
    @Override
    public GeneratedSaiban find(GeneratedSaiban bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NO_ID = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getNoId());
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
}
