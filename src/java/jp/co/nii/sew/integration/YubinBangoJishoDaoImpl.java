package jp.co.nii.sew.integration;

import jp.co.nii.sew.integration.GeneratedYubinBangoJishoDaoImpl;
import java.util.List;
import java.util.LinkedList;


import jp.co.nii.sew.business.domain.YubinBangoJisho;
import jp.co.nii.sew.business.domain.YubinBangoJishoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

/**
 * 郵便番号辞書 DAO実装クラス
 * @author DB管理ツール
 */
public class YubinBangoJishoDaoImpl extends GeneratedYubinBangoJishoDaoImpl implements YubinBangoJishoDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public YubinBangoJishoDaoImpl(String datasource) {
        super(datasource);
    }
    
    /**
     * 郵便番号で検索する。
     * @param yubinBango 郵便番号７桁
     * @param lockMode ロック方法
     */
    public List findYubinBango(String yubinBango, String lockMode) {
        
        List ret = new LinkedList();
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " YUBIN_NO = ?"
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, yubinBango);
            stmt.setString(i++, MiwConstants.FLG_OFF);

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                YubinBangoJisho bo = new YubinBangoJisho();
                setBoFromResultSet(bo, rs);
                ret.add(bo);
            }
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }
        
        return ret;
    }
    
    /**
     * 検索結果であるResultSetをBusinessObjectへ詰め替える。<br>
     * 事前条件：引数のboとrsはnullでないこと。
     * 
     * @param bo BusinessObjectのインスタンス
     * @param rs 検索結果のResultSet
     */
    protected void setBoFromResultSet(YubinBangoJisho bo, ResultSet rs) {
        try {
            bo.setSeq(rs.getString("SEQ"));
            bo.setYubinNo(rs.getString("YUBIN_NO"));
            bo.setKenCode(rs.getString("KEN_CODE"));
            bo.setTodofukenName(rs.getString("TODOFUKEN_NAME"));
            bo.setShikuchosonName(rs.getString("SHIKUCHOSON_NAME"));
            bo.setChoikiName(rs.getString("CHOIKI_NAME"));
            bo.setTodofukenNameKana(rs.getString("TODOFUKEN_NAME_KANA"));
            bo.setShikuchosonNameKana(rs.getString("SHIKUCHOSON_NAME_KANA"));
            bo.setChoikiNameKana(rs.getString("CHOIKI_NAME_KANA"));
            bo.setChihoKokyoDantaiCode(rs.getString("CHIHO_KOKYO_DANTAI_CODE"));
            
            bo.setRonriSakujoFlg(rs.getString("RONRI_SAKUJO_FLG"));
            bo.setShoriKbn(rs.getString("SHORI_KBN"));
            bo.setTorokuDate(rs.getString("TOROKU_DATE"));
            bo.setTorokuTime(rs.getString("TOROKU_TIME"));
            bo.setTorokuUserId(rs.getString("TOROKU_USER_ID"));
            bo.setKoshinDate(rs.getString("KOSHIN_DATE"));
            bo.setKoshinTime(rs.getString("KOSHIN_TIME"));
            bo.setKoshinUserId(rs.getString("KOSHIN_USER_ID"));
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(null, ex);
        }
    }
    
    
}
