package jp.co.nii.miw.integration;



import jp.co.nii.miw.business.domain.Kaijo;
import jp.co.nii.miw.business.domain.KaijoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

/**
 * 開催会場 DAO実装クラス
 * @author DB管理ツール
 */
public class KaijoDaoImpl extends GeneratedKaijoDaoImpl implements KaijoDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public KaijoDaoImpl(String datasource) {
        super(datasource);
    }
    
     @Override
    public Kaijo find(Kaijo bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?"
                    + " AND SHIKENCHI_CODE = ?"
                    + " AND KAIJO_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getShikenchiCode());
            stmt.setString(i++, bo.getKaijoCode());
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
     * 準会場を検索する。
     * @param junkaijoCode 準会場コード
     * @param lockMode ロック方法
     */
    public String findJunkaijo(String nendo, String ki, String junkaijoCode, String lockMode) {
        
        String ret = "";
        Kaijo bo = new Kaijo();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND"
                    + " KAISU = ?"
                    + " AND"
                    + " KAIJO_CODE = ?"
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, nendo);
            stmt.setString(i++, ki);
            stmt.setString(i++, junkaijoCode);
            stmt.setString(i++, MiwConstants.FLG_OFF);

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                ret = rs.getString("KAIJO_NAME");
            }
            
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }
        
        return ret;
    }
    
}
