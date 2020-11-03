package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.integration.AbstractDao;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.domain.GeneratedDantai;
import jp.co.nii.miw.business.domain.GeneratedDantaiDao;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * 生成された 団体 DAO実装クラス<br>
 * table-design-ver 2
 * @author DB管理ツール
 */
abstract class GeneratedDantaiDaoImpl extends AbstractDao implements GeneratedDantaiDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     * SQL文の作成に用いる。
     */
    protected static final String FIELDS = "DANTAI_CODE"
            + ",DANTAI_PASSWD"
            + ",DANTAI_NAME"
            + ",DANTAI_NAME_KANA"
            + ",DANTAI_YUBIN_NO"
            + ",DANTAI_JUSHO"
            + ",DANTAI_JIMU_TANTOSHA_SHIMEI_SEI_KANA"
            + ",DANTAI_JIMU_TANTOSHA_SHIMEI_MEI_KANA"
            + ",DANTAI_JIMU_TANTOSHA_SHIMEI_SEI"
            + ",DANTAI_JIMU_TANTOSHA_SHIMEI_MEI"
            + ",DANTAI_JIMU_TANTOSHA_TEL_NO"
            + ",DANTAI_JIMU_TANTOSHA_FAX_NO"
            + ",DANTAI_JIMU_TANTOSHA_MAIL_ADDRESS"
            + ",HASSOSAKI_KBN"
            + ",PASSWD_QUESTION_CODE_1"
            + ",PASSWD_QUESTION_CODE_2"
            + ",PASSWD_ANSWER_1"
            + ",PASSWD_ANSWER_2"
            + ",HOST_YO_DANTAI_SEQ"
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
    protected static final String FIELDS_DECRYPT = "DANTAI_CODE"
            + "," + getSQLForDecryptByUTF8("DANTAI_PASSWD")
            + "," + getSQLForDecryptByUTF8("DANTAI_NAME")
            + "," + getSQLForDecryptByUTF8("DANTAI_NAME_KANA")
            + "," + getSQLForDecryptByUTF8("DANTAI_YUBIN_NO")
            + "," + getSQLForDecryptByUTF8("DANTAI_JUSHO")
            + "," + getSQLForDecryptByUTF8("DANTAI_JIMU_TANTOSHA_SHIMEI_SEI_KANA")
            + "," + getSQLForDecryptByUTF8("DANTAI_JIMU_TANTOSHA_SHIMEI_MEI_KANA")
            + "," + getSQLForDecryptByUTF8("DANTAI_JIMU_TANTOSHA_SHIMEI_SEI")
            + "," + getSQLForDecryptByUTF8("DANTAI_JIMU_TANTOSHA_SHIMEI_MEI")
            + "," + getSQLForDecryptByUTF8("DANTAI_JIMU_TANTOSHA_TEL_NO")
            + "," + getSQLForDecryptByUTF8("DANTAI_JIMU_TANTOSHA_FAX_NO")
            + "," + getSQLForDecryptByUTF8("DANTAI_JIMU_TANTOSHA_MAIL_ADDRESS")
            + "," + getSQLForDecryptByUTF8("HASSOSAKI_KBN")
            + "," + getSQLForDecryptByUTF8("PASSWD_QUESTION_CODE_1")
            + "," + getSQLForDecryptByUTF8("PASSWD_QUESTION_CODE_2")
            + "," + getSQLForDecryptByUTF8("PASSWD_ANSWER_1")
            + "," + getSQLForDecryptByUTF8("PASSWD_ANSWER_2")
            + "," + getSQLForDecryptByUTF8("HOST_YO_DANTAI_SEQ")
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
    public GeneratedDantaiDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedDantaiDao#create(jp.co.nii.miw.business.domain.GeneratedDantai)
     */
    @Override
    public void create(GeneratedDantai bo) {
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
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setString(i++, bo.getDantaiPasswd());
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
            stmt.setString(i++, bo.getPasswdQuestionCode2());
            stmt.setString(i++, bo.getPasswdAnswer1());
            stmt.setString(i++, bo.getPasswdAnswer2());
            stmt.setString(i++, bo.getHostYoDantaiSeq());
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
     * @see jp.co.nii.miw.business.domain.GeneratedDantaiDao#find(jp.co.nii.miw.business.domain.GeneratedDantai, java.lang.String)
     */
    @Override
    public GeneratedDantai find(GeneratedDantai bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " DANTAI_CODE = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getDantaiCode());

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
     * @see jp.co.nii.miw.business.domain.GeneratedDantaiDao#update(jp.co.nii.miw.business.domain.GeneratedDantai)
     */
    @Override
    public void update(GeneratedDantai bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " DANTAI_PASSWD = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_NAME = " + getSQLForEncryptByUTF8("?")
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
                    + ",PASSWD_QUESTION_CODE_2 = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD_ANSWER_1 = " + getSQLForEncryptByUTF8("?")
                    + ",PASSWD_ANSWER_2 = " + getSQLForEncryptByUTF8("?")
                    + ",HOST_YO_DANTAI_SEQ = " + getSQLForEncryptByUTF8("?")
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " DANTAI_CODE = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getDantaiPasswd());
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
            stmt.setString(i++, bo.getPasswdQuestionCode2());
            stmt.setString(i++, bo.getPasswdAnswer1());
            stmt.setString(i++, bo.getPasswdAnswer2());
            stmt.setString(i++, bo.getHostYoDantaiSeq());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
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

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedDantaiDao#remove(jp.co.nii.miw.business.domain.GeneratedDantai)
     */
    @Override
    public void remove(GeneratedDantai bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "DELETE FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " DANTAI_CODE = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
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

    /**
     * 検索結果であるResultSetをBusinessObjectへ詰め替える。<br>
     * 事前条件：引数のboとrsはnullでないこと。
     * 
     * @param bo BusinessObjectのインスタンス
     * @param rs 検索結果のResultSet
     */
    protected void setBoFromResultSet(GeneratedDantai bo, ResultSet rs) {
        try {
            bo.setDantaiCode(rs.getString("DANTAI_CODE"));
            bo.setDantaiPasswd(rs.getString("DANTAI_PASSWD"));
            bo.setDantaiName(rs.getString("DANTAI_NAME"));
            bo.setDantaiNameKana(rs.getString("DANTAI_NAME_KANA"));
            bo.setDantaiYubinNo(rs.getString("DANTAI_YUBIN_NO"));
            bo.setDantaiJusho(rs.getString("DANTAI_JUSHO"));
            bo.setDantaiJimuTantoshaShimeiSeiKana(rs.getString("DANTAI_JIMU_TANTOSHA_SHIMEI_SEI_KANA"));
            bo.setDantaiJimuTantoshaShimeiMeiKana(rs.getString("DANTAI_JIMU_TANTOSHA_SHIMEI_MEI_KANA"));
            bo.setDantaiJimuTantoshaShimeiSei(rs.getString("DANTAI_JIMU_TANTOSHA_SHIMEI_SEI"));
            bo.setDantaiJimuTantoshaShimeiMei(rs.getString("DANTAI_JIMU_TANTOSHA_SHIMEI_MEI"));
            bo.setDantaiJimuTantoshaTelNo(rs.getString("DANTAI_JIMU_TANTOSHA_TEL_NO"));
            bo.setDantaiJimuTantoshaFaxNo(rs.getString("DANTAI_JIMU_TANTOSHA_FAX_NO"));
            bo.setDantaiJimuTantoshaMailAddress(rs.getString("DANTAI_JIMU_TANTOSHA_MAIL_ADDRESS"));
            bo.setHassosakiKbn(rs.getString("HASSOSAKI_KBN"));
            bo.setPasswdQuestionCode1(rs.getString("PASSWD_QUESTION_CODE_1"));
            bo.setPasswdQuestionCode2(rs.getString("PASSWD_QUESTION_CODE_2"));
            bo.setPasswdAnswer1(rs.getString("PASSWD_ANSWER_1"));
            bo.setPasswdAnswer2(rs.getString("PASSWD_ANSWER_2"));
            bo.setHostYoDantaiSeq(rs.getString("HOST_YO_DANTAI_SEQ"));
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
