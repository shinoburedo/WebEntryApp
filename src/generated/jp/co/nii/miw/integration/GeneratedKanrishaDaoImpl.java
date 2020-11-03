package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.AbstractDao;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.domain.GeneratedKanrisha;
import jp.co.nii.miw.business.domain.GeneratedKanrishaDao;

/**
 * 生成された 管理者 DAO実装クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
abstract class GeneratedKanrishaDaoImpl extends AbstractDao implements GeneratedKanrishaDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     * SQL文の作成に用いる。
     */
    protected static final String FIELDS = "KANRI_USER_ID"
            + ",HYOJI_NAME"
            + ",PASSWD"
            + ",KENGEN_CODE"
            + ",MAIL_ADDRESS"
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
    protected static final String FIELDS_DECRYPT = getSQLForDecryptByUTF8("KANRI_USER_ID")
            + "," + getSQLForDecryptByUTF8("HYOJI_NAME")
            + "," + getSQLForDecryptByUTF8("PASSWD")
            + "," + getSQLForDecryptByUTF8("KENGEN_CODE")
            + "," + getSQLForDecryptByUTF8("MAIL_ADDRESS")
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
    public GeneratedKanrishaDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedKanrishaDao#create(jp.co.nii.miw.business.domain.GeneratedKanrisha)
     */
    @Override
    public void create(GeneratedKanrisha bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "INSERT INTO " + getSchemaName() + "." + TABLE_NAME + " ( "
                    + FIELDS
                    + " ) VALUES ("
                    + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
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
            stmt.setString(i++, bo.getKanriUserId());
            stmt.setString(i++, bo.getHyojiName());
            stmt.setString(i++, bo.getPasswd());
            stmt.setString(i++, bo.getKengenCode());
            stmt.setString(i++, bo.getMailAddress());
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
     * @see jp.co.nii.miw.business.domain.GeneratedKanrishaDao#find(jp.co.nii.miw.business.domain.GeneratedKanrisha, java.lang.String)
     */
    @Override
    public GeneratedKanrisha find(GeneratedKanrisha bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " KANRI_USER_ID = " + getSQLForEncryptByUTF8("?");

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getKanriUserId());

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
     * @see jp.co.nii.miw.business.domain.GeneratedKanrishaDao#update(jp.co.nii.miw.business.domain.GeneratedKanrisha)
     */
    @Override
    public void update(GeneratedKanrisha bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " HYOJI_NAME = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD = " + getSQLForEncryptByUTF8("?")
                    + ",KENGEN_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",MAIL_ADDRESS = " + getSQLForEncryptByUTF8("?")
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " KANRI_USER_ID = " + getSQLForEncryptByUTF8("?");

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getHyojiName());
            stmt.setString(i++, bo.getPasswd());
            stmt.setString(i++, bo.getKengenCode());
            stmt.setString(i++, bo.getMailAddress());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getKanriUserId());

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
     * @see jp.co.nii.miw.business.domain.GeneratedKanrishaDao#remove(jp.co.nii.miw.business.domain.GeneratedKanrisha)
     */
    @Override
    public void remove(GeneratedKanrisha bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "DELETE FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " KANRI_USER_ID = " + getSQLForEncryptByUTF8("?");

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getKanriUserId());

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
    protected void setBoFromResultSet(GeneratedKanrisha bo, ResultSet rs) {
        try {
            bo.setKanriUserId(rs.getString("KANRI_USER_ID"));
            bo.setHyojiName(rs.getString("HYOJI_NAME"));
            bo.setPasswd(rs.getString("PASSWD"));
            bo.setKengenCode(rs.getString("KENGEN_CODE"));
            bo.setMailAddress(rs.getString("MAIL_ADDRESS"));
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
