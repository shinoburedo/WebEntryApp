package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.integration.AbstractDao;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.domain.GeneratedMoshikomisha;
import jp.co.nii.miw.business.domain.GeneratedMoshikomishaDao;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * 生成された 申込者 DAO実装クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
abstract class GeneratedMoshikomishaDaoImpl extends AbstractDao implements GeneratedMoshikomishaDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     * SQL文の作成に用いる。
     */
    protected static final String FIELDS = "USER_ID"
            + ",MOSHIKOMI_ARI_FLG"
            + ",MAIL_ADDRESS"
            + ",PASSWD"
            + ",SHIMEI_SEI_KANA"
            + ",SHIMEI_MEI_KANA"
            + ",SHIMEI_SEI"
            + ",SHIMEI_MEI"
            + ",BIRTHDAY"
            + ",SEX_CODE"
            + ",TEL_NO"
            + ",CELLPHONE_NO"
            + ",YUBIN_NO"
            + ",JUSHO"
            + ",PASSWD_QUESTION_CODE_1"
            + ",PASSWD_QUESTION_CODE_2"
            + ",PASSWD_ANSWER_1"
            + ",PASSWD_ANSWER_2"
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
    protected static final String FIELDS_DECRYPT = "USER_ID"
            + "," + getSQLForDecryptByUTF8("MOSHIKOMI_ARI_FLG")
            + "," + getSQLForDecryptByUTF8("MAIL_ADDRESS")
            + "," + getSQLForDecryptByUTF8("PASSWD")
            + "," + getSQLForDecryptByUTF8("SHIMEI_SEI_KANA")
            + "," + getSQLForDecryptByUTF8("SHIMEI_MEI_KANA")
            + "," + getSQLForDecryptByUTF8("SHIMEI_SEI")
            + "," + getSQLForDecryptByUTF8("SHIMEI_MEI")
            + "," + getSQLForDecryptByUTF8("BIRTHDAY")
            + "," + getSQLForDecryptByUTF8("SEX_CODE")
            + "," + getSQLForDecryptByUTF8("TEL_NO")
            + "," + getSQLForDecryptByUTF8("CELLPHONE_NO")
            + "," + getSQLForDecryptByUTF8("YUBIN_NO")
            + "," + getSQLForDecryptByUTF8("JUSHO")
            + "," + getSQLForDecryptByUTF8("PASSWD_QUESTION_CODE_1")
            + "," + getSQLForDecryptByUTF8("PASSWD_QUESTION_CODE_2")
            + "," + getSQLForDecryptByUTF8("PASSWD_ANSWER_1")
            + "," + getSQLForDecryptByUTF8("PASSWD_ANSWER_2")
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
    public GeneratedMoshikomishaDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomishaDao#create(jp.co.nii.miw.business.domain.GeneratedMoshikomisha)
     */
    @Override
    public void create(GeneratedMoshikomisha bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "INSERT INTO " + getSchemaName() + "." + TABLE_NAME + " ( "
                    + FIELDS
                    + " ) VALUES ("
                    + " ?"
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
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
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getMoshikomiAriFlg());
            stmt.setString(i++, bo.getMailAddress());
            stmt.setString(i++, bo.getPasswd());
            stmt.setString(i++, bo.getShimeiSeiKana());
            stmt.setString(i++, bo.getShimeiMeiKana());
            stmt.setString(i++, bo.getShimeiSei());
            stmt.setString(i++, bo.getShimeiMei());
            stmt.setString(i++, bo.getBirthday());
            stmt.setString(i++, bo.getSexCode());
            stmt.setString(i++, bo.getTelNo());
            stmt.setString(i++, bo.getCellphoneNo());
            stmt.setString(i++, bo.getYubinNo());
            stmt.setString(i++, bo.getJusho());
            stmt.setString(i++, bo.getPasswdQuestionCode1());
            stmt.setString(i++, bo.getPasswdQuestionCode2());
            stmt.setString(i++, bo.getPasswdAnswer1());
            stmt.setString(i++, bo.getPasswdAnswer2());
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
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomishaDao#find(jp.co.nii.miw.business.domain.GeneratedMoshikomisha, java.lang.String)
     */
    @Override
    public GeneratedMoshikomisha find(GeneratedMoshikomisha bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " USER_ID = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getUserId());

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
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomishaDao#update(jp.co.nii.miw.business.domain.GeneratedMoshikomisha)
     */
    @Override
    public void update(GeneratedMoshikomisha bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " MOSHIKOMI_ARI_FLG = " + getSQLForEncryptByUTF8("?")
                    + ",MAIL_ADDRESS = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD = " + getSQLForEncryptByUTF8("?")
                    + ",SHIMEI_SEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + ",SHIMEI_MEI_KANA = " + getSQLForEncryptByUTF8("?")
                    + ",SHIMEI_SEI = " + getSQLForEncryptByUTF8("?")
                    + ",SHIMEI_MEI = " + getSQLForEncryptByUTF8("?")
                    + ",BIRTHDAY = " + getSQLForEncryptByUTF8("?")
                    + ",SEX_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",TEL_NO = " + getSQLForEncryptByUTF8("?")
                    + ",CELLPHONE_NO = " + getSQLForEncryptByUTF8("?")
                    + ",YUBIN_NO = " + getSQLForEncryptByUTF8("?")
                    + ",JUSHO = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD_QUESTION_CODE_1 = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD_QUESTION_CODE_2 = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD_ANSWER_1 = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD_ANSWER_2 = " + getSQLForEncryptByUTF8("?")
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " USER_ID = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiAriFlg());
            stmt.setString(i++, bo.getMailAddress());
            stmt.setString(i++, bo.getPasswd());
            stmt.setString(i++, bo.getShimeiSeiKana());
            stmt.setString(i++, bo.getShimeiMeiKana());
            stmt.setString(i++, bo.getShimeiSei());
            stmt.setString(i++, bo.getShimeiMei());
            stmt.setString(i++, bo.getBirthday());
            stmt.setString(i++, bo.getSexCode());
            stmt.setString(i++, bo.getTelNo());
            stmt.setString(i++, bo.getCellphoneNo());
            stmt.setString(i++, bo.getYubinNo());
            stmt.setString(i++, bo.getJusho());
            stmt.setString(i++, bo.getPasswdQuestionCode1());
            stmt.setString(i++, bo.getPasswdQuestionCode2());
            stmt.setString(i++, bo.getPasswdAnswer1());
            stmt.setString(i++, bo.getPasswdAnswer2());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
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

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomishaDao#remove(jp.co.nii.miw.business.domain.GeneratedMoshikomisha)
     */
    @Override
    public void remove(GeneratedMoshikomisha bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "DELETE FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " USER_ID = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
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

    /**
     * 検索結果であるResultSetをBusinessObjectへ詰め替える。<br>
     * 事前条件：引数のboとrsはnullでないこと。
     * 
     * @param bo BusinessObjectのインスタンス
     * @param rs 検索結果のResultSet
     */
    protected void setBoFromResultSet(GeneratedMoshikomisha bo, ResultSet rs) {
        try {
            bo.setUserId(rs.getString("USER_ID"));
            bo.setMoshikomiAriFlg(rs.getString("MOSHIKOMI_ARI_FLG"));
            bo.setMailAddress(rs.getString("MAIL_ADDRESS"));
            bo.setPasswd(rs.getString("PASSWD"));
            bo.setShimeiSeiKana(rs.getString("SHIMEI_SEI_KANA"));
            bo.setShimeiMeiKana(rs.getString("SHIMEI_MEI_KANA"));
            bo.setShimeiSei(rs.getString("SHIMEI_SEI"));
            bo.setShimeiMei(rs.getString("SHIMEI_MEI"));
            bo.setBirthday(rs.getString("BIRTHDAY"));
            bo.setSexCode(rs.getString("SEX_CODE"));
            bo.setTelNo(rs.getString("TEL_NO"));
            bo.setCellphoneNo(rs.getString("CELLPHONE_NO"));
            bo.setYubinNo(rs.getString("YUBIN_NO"));
            bo.setJusho(rs.getString("JUSHO"));
            bo.setPasswdQuestionCode1(rs.getString("PASSWD_QUESTION_CODE_1"));
            bo.setPasswdQuestionCode2(rs.getString("PASSWD_QUESTION_CODE_2"));
            bo.setPasswdAnswer1(rs.getString("PASSWD_ANSWER_1"));
            bo.setPasswdAnswer2(rs.getString("PASSWD_ANSWER_2"));
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
