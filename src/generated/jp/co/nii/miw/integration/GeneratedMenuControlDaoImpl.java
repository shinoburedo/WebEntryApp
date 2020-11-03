package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.AbstractDao;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.domain.GeneratedMenuControl;
import jp.co.nii.miw.business.domain.GeneratedMenuControlDao;

/**
 * 生成された メニュー制御 DAO実装クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
abstract class GeneratedMenuControlDaoImpl extends AbstractDao implements GeneratedMenuControlDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     * SQL文の作成に用いる。
     */
    protected static final String FIELDS = "NENDO"
            + ",KAISU"
            + ",EVENT_CODE"
            + ",MENU_CODE"
            + ",KAISHI_DATE"
            + ",KAISHI_TIME"
            + ",SHURYO_DATE"
            + ",SHURYO_TIME"
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
    protected static final String FIELDS_DECRYPT = "NENDO"
            + "," + "KAISU"
            + "," + "EVENT_CODE"
            + "," + "MENU_CODE"
            + "," + "KAISHI_DATE"
            + "," + "KAISHI_TIME"
            + "," + "SHURYO_DATE"
            + "," + "SHURYO_TIME"
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
    public GeneratedMenuControlDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedMenuControlDao#create(jp.co.nii.miw.business.domain.GeneratedMenuControl)
     */
    @Override
    public void create(GeneratedMenuControl bo) {
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
                    + " )";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getMenuCode());
            stmt.setString(i++, bo.getKaishiDate());
            stmt.setString(i++, bo.getKaishiTime());
            stmt.setString(i++, bo.getShuryoDate());
            stmt.setString(i++, bo.getShuryoTime());
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
     * @see jp.co.nii.miw.business.domain.GeneratedMenuControlDao#find(jp.co.nii.miw.business.domain.GeneratedMenuControl, java.lang.String)
     */
    @Override
    public GeneratedMenuControl find(GeneratedMenuControl bo, String lockMode) {
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
                    + " AND MENU_CODE = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getMenuCode());

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
     * @see jp.co.nii.miw.business.domain.GeneratedMenuControlDao#update(jp.co.nii.miw.business.domain.GeneratedMenuControl)
     */
    @Override
    public void update(GeneratedMenuControl bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " KAISHI_DATE = ?"
                    + ",KAISHI_TIME = ?"
                    + ",SHURYO_DATE = ?"
                    + ",SHURYO_TIME = ?"
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?"
                    + " AND MENU_CODE = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getKaishiDate());
            stmt.setString(i++, bo.getKaishiTime());
            stmt.setString(i++, bo.getShuryoDate());
            stmt.setString(i++, bo.getShuryoTime());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getMenuCode());

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
     * @see jp.co.nii.miw.business.domain.GeneratedMenuControlDao#remove(jp.co.nii.miw.business.domain.GeneratedMenuControl)
     */
    @Override
    public void remove(GeneratedMenuControl bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "DELETE FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?"
                    + " AND MENU_CODE = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getMenuCode());

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
    protected void setBoFromResultSet(GeneratedMenuControl bo, ResultSet rs) {
        try {
            bo.setNendo(rs.getString("NENDO"));
            bo.setKaisu(rs.getString("KAISU"));
            bo.setEventCode(rs.getString("EVENT_CODE"));
            bo.setMenuCode(rs.getString("MENU_CODE"));
            bo.setKaishiDate(rs.getString("KAISHI_DATE"));
            bo.setKaishiTime(rs.getString("KAISHI_TIME"));
            bo.setShuryoDate(rs.getString("SHURYO_DATE"));
            bo.setShuryoTime(rs.getString("SHURYO_TIME"));
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
