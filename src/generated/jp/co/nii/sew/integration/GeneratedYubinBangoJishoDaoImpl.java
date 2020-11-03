package jp.co.nii.sew.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.AbstractDao;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.sew.business.domain.GeneratedYubinBangoJisho;
import jp.co.nii.sew.business.domain.GeneratedYubinBangoJishoDao;

/**
 * 生成された 郵便番号辞書 DAO実装クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
abstract class GeneratedYubinBangoJishoDaoImpl extends AbstractDao implements GeneratedYubinBangoJishoDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     * SQL文の作成に用いる。
     */
    protected static final String FIELDS = "SEQ"
            + ",YUBIN_NO"
            + ",KEN_CODE"
            + ",TODOFUKEN_NAME"
            + ",SHIKUCHOSON_NAME"
            + ",CHOIKI_NAME"
            + ",TODOFUKEN_NAME_KANA"
            + ",SHIKUCHOSON_NAME_KANA"
            + ",CHOIKI_NAME_KANA"
            + ",CHIHO_KOKYO_DANTAI_CODE"
            + ",RONRI_SAKUJO_FLG"
            + ",SHORI_KBN"
            + ",TOROKU_DATE"
            + ",TOROKU_TIME"
            + ",TOROKU_USER_ID"
            + ",KOSHIN_DATE"
            + ",KOSHIN_TIME"
            + ",KOSHIN_USER_ID";

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     *  暗号化対象項目を複号するSQL文の作成に用いる。
     */
    protected static final String FIELDS_DECRYPT = "SEQ"
            + "," + "YUBIN_NO"
            + "," + "KEN_CODE"
            + "," + "TODOFUKEN_NAME"
            + "," + "SHIKUCHOSON_NAME"
            + "," + "CHOIKI_NAME"
            + "," + "TODOFUKEN_NAME_KANA"
            + "," + "SHIKUCHOSON_NAME_KANA"
            + "," + "CHOIKI_NAME_KANA"
            + "," + "CHIHO_KOKYO_DANTAI_CODE"
            + "," + "RONRI_SAKUJO_FLG"
            + "," + "SHORI_KBN"
            + "," + "TOROKU_DATE"
            + "," + "TOROKU_TIME"
            + "," + "TOROKU_USER_ID"
            + "," + "KOSHIN_DATE"
            + "," + "KOSHIN_TIME"
            + "," + "KOSHIN_USER_ID";

    /**
     * インスタンスを生成する。<br>
     * データソースを決定
     * @param datasource データソース名
     */
    public GeneratedYubinBangoJishoDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedYubinBangoJishoDao#create(jp.co.nii.miw.business.domain.GeneratedYubinBangoJisho)
     */
    @Override
    public void create(GeneratedYubinBangoJisho bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "INSERT INTO " + getSchemaName() + "." + TABLE_NAME + " ( "
                    + FIELDS
                    + " ) VALUES ("
                    + " ?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + " )";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getSeq());
            stmt.setString(i++, bo.getYubinNo());
            stmt.setString(i++, bo.getKenCode());
            stmt.setString(i++, bo.getTodofukenName());
            stmt.setString(i++, bo.getShikuchosonName());
            stmt.setString(i++, bo.getChoikiName());
            stmt.setString(i++, bo.getTodofukenNameKana());
            stmt.setString(i++, bo.getShikuchosonNameKana());
            stmt.setString(i++, bo.getChoikiNameKana());
            stmt.setString(i++, bo.getChihoKokyoDantaiCode());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            LogGenerate.debugOutput(getSql(stmt));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt);
        }
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedYubinBangoJishoDao#find(jp.co.nii.miw.business.domain.GeneratedYubinBangoJisho, java.lang.String)
     */
    @Override
    public GeneratedYubinBangoJisho find(GeneratedYubinBangoJisho bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " SEQ = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getSeq());

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
     * @see jp.co.nii.miw.business.domain.GeneratedYubinBangoJishoDao#update(jp.co.nii.miw.business.domain.GeneratedYubinBangoJisho)
     */
    @Override
    public void update(GeneratedYubinBangoJisho bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " YUBIN_NO = ?"
                    + ",KEN_CODE = ?"
                    + ",TODOFUKEN_NAME = ?"
                    + ",SHIKUCHOSON_NAME = ?"
                    + ",CHOIKI_NAME = ?"
                    + ",TODOFUKEN_NAME_KANA = ?"
                    + ",SHIKUCHOSON_NAME_KANA = ?"
                    + ",CHOIKI_NAME_KANA = ?"
                    + ",CHIHO_KOKYO_DANTAI_CODE = ?"
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " SEQ = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getYubinNo());
            stmt.setString(i++, bo.getKenCode());
            stmt.setString(i++, bo.getTodofukenName());
            stmt.setString(i++, bo.getShikuchosonName());
            stmt.setString(i++, bo.getChoikiName());
            stmt.setString(i++, bo.getTodofukenNameKana());
            stmt.setString(i++, bo.getShikuchosonNameKana());
            stmt.setString(i++, bo.getChoikiNameKana());
            stmt.setString(i++, bo.getChihoKokyoDantaiCode());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getSeq());

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

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedYubinBangoJishoDao#remove(jp.co.nii.miw.business.domain.GeneratedYubinBangoJisho)
     */
    @Override
    public void remove(GeneratedYubinBangoJisho bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "DELETE FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " SEQ = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getSeq());

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

    /**
     * 検索結果であるResultSetをBusinessObjectへ詰め替える。<br>
     * 事前条件：引数のboとrsはnullでないこと。
     * 
     * @param bo BusinessObjectのインスタンス
     * @param rs 検索結果のResultSet
     */
    protected void setBoFromResultSet(GeneratedYubinBangoJisho bo, ResultSet rs) {
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
