package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.integration.AbstractDao;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.domain.GeneratedMoshikomiDantai;
import jp.co.nii.miw.business.domain.GeneratedMoshikomiDantaiDao;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * 生成された 申込団体 DAO実装クラス<br>
 * table-design-ver 2
 * @author DB管理ツール
 */
abstract class GeneratedMoshikomiDantaiDaoImpl extends AbstractDao implements GeneratedMoshikomiDantaiDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     * SQL文の作成に用いる。
     */
    protected static final String FIELDS = "DANTAI_MOSHIKOMI_UKETSUKE_NO"
            + ",NENDO"
            + ",KAISU"
            + ",MOSHIKOMI_BAITAI_KBN"
            + ",DANTAI_CODE"
            + ",DANTAI_MOSHIKOMI_PASSWD"
            + ",MOSHIKOMISHA_SU_IKA"
            + ",MOSHIKOMISHA_SU_SHIKA"
            + ",TETSUDUKI_JOKYO_KBN"
            + ",KESSAI_JOKYO_KBN"
            + ",KESSAI_HOHO_KBN"
            + ",KESSAI_KAMEITEN_CODE"
            + ",KESSAI_TORIHIKI_CODE"
            + ",KESSAI_CONVENIENCE_SHUBETSU"
            + ",KESSAI_CONVENIENCE_HARAIKOMIHYO_URL"
            + ",KESSAI_KIGEN_BI"
            + ",KESSAI_KINGAKU"
            + ",KARI_UKETSUKE_BI"
            + ",KARI_UKETSUKE_TIME"
            + ",MOSHIKOMI_FINISH_BI"
            + ",MOSHIKOMI_FINISH_TIME"
            + ",MOSHIKOMI_MEMO"
            + ",KANRI_MEMO"
            + ",KESSAI_TOKUSOKU_MAIL_SOSHIN_FLG"
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
    protected static final String FIELDS_DECRYPT = "DANTAI_MOSHIKOMI_UKETSUKE_NO"
            + "," + "NENDO"
            + "," + "KAISU"
            + "," + "MOSHIKOMI_BAITAI_KBN"
            + "," + "DANTAI_CODE"
            + "," + getSQLForDecryptByUTF8("DANTAI_MOSHIKOMI_PASSWD")
            + "," + getSQLForDecryptByUTF8("MOSHIKOMISHA_SU_IKA")
            + "," + getSQLForDecryptByUTF8("MOSHIKOMISHA_SU_SHIKA")
            + "," + getSQLForDecryptByUTF8("TETSUDUKI_JOKYO_KBN")
            + "," + getSQLForDecryptByUTF8("KESSAI_JOKYO_KBN")
            + "," + getSQLForDecryptByUTF8("KESSAI_HOHO_KBN")
            + "," + getSQLForDecryptByUTF8("KESSAI_KAMEITEN_CODE")
            + "," + getSQLForDecryptByUTF8("KESSAI_TORIHIKI_CODE")
            + "," + getSQLForDecryptByUTF8("KESSAI_CONVENIENCE_SHUBETSU")
            + "," + getSQLForDecryptByUTF8("KESSAI_CONVENIENCE_HARAIKOMIHYO_URL")
            + "," + getSQLForDecryptByUTF8("KESSAI_KIGEN_BI")
            + "," + getSQLForDecryptByUTF8("KESSAI_KINGAKU")
            + "," + getSQLForDecryptByUTF8("KARI_UKETSUKE_BI")
            + "," + getSQLForDecryptByUTF8("KARI_UKETSUKE_TIME")
            + "," + getSQLForDecryptByUTF8("MOSHIKOMI_FINISH_BI")
            + "," + getSQLForDecryptByUTF8("MOSHIKOMI_FINISH_TIME")
            + "," + getSQLForDecryptByUTF8("MOSHIKOMI_MEMO")
            + "," + getSQLForDecryptByUTF8("KANRI_MEMO")
            + "," + "KESSAI_TOKUSOKU_MAIL_SOSHIN_FLG"
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
    public GeneratedMoshikomiDantaiDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomiDantaiDao#create(jp.co.nii.miw.business.domain.GeneratedMoshikomiDantai)
     */
    @Override
    public void create(GeneratedMoshikomiDantai bo) {
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
                    + ",?"
                    + " )";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getMoshikomiBaitaiKbn());
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setString(i++, bo.getDantaiMoshikomiPasswd());
            stmt.setString(i++, bo.getMoshikomishaSuIka());
            stmt.setString(i++, bo.getMoshikomishaSuShika());
            stmt.setString(i++, bo.getTetsudukiJokyoKbn());
            stmt.setString(i++, bo.getKessaiJokyoKbn());
            stmt.setString(i++, bo.getKessaiHohoKbn());
            stmt.setString(i++, bo.getKessaiKameitenCode());
            stmt.setString(i++, bo.getKessaiTorihikiCode());
            stmt.setString(i++, bo.getKessaiConvenienceShubetsu());
            stmt.setString(i++, bo.getKessaiConvenienceHaraikomihyoUrl());
            stmt.setString(i++, bo.getKessaiKigenBi());
            stmt.setString(i++, bo.getKessaiKingaku());
            stmt.setString(i++, bo.getKariUketsukeBi());
            stmt.setString(i++, bo.getKariUketsukeTime());
            stmt.setString(i++, bo.getMoshikomiFinishBi());
            stmt.setString(i++, bo.getMoshikomiFinishTime());
            stmt.setString(i++, bo.getMoshikomiMemo());
            stmt.setString(i++, bo.getKanriMemo());
            stmt.setString(i++, bo.getKessaiTokusokuMailSoshinFlg());
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
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomiDantaiDao#find(jp.co.nii.miw.business.domain.GeneratedMoshikomiDantai, java.lang.String)
     */
    @Override
    public GeneratedMoshikomiDantai find(GeneratedMoshikomiDantai bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " DANTAI_MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());

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
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomiDantaiDao#update(jp.co.nii.miw.business.domain.GeneratedMoshikomiDantai)
     */
    @Override
    public void update(GeneratedMoshikomiDantai bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " MOSHIKOMI_BAITAI_KBN = ?"
                    + ",DANTAI_CODE = ?"
                    + ",DANTAI_MOSHIKOMI_PASSWD = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMISHA_SU_IKA = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMISHA_SU_SHIKA = " + getSQLForEncryptByUTF8("?")
                    + ",TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_HOHO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_KAMEITEN_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_TORIHIKI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_CONVENIENCE_SHUBETSU = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_CONVENIENCE_HARAIKOMIHYO_URL = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_KIGEN_BI = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_KINGAKU = " + getSQLForEncryptByUTF8("?")
                    + ",KARI_UKETSUKE_BI = " + getSQLForEncryptByUTF8("?")
                    + ",KARI_UKETSUKE_TIME = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMI_FINISH_BI = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMI_FINISH_TIME = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMI_MEMO = " + getSQLForEncryptByUTF8("?")
                    + ",KANRI_MEMO = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_TOKUSOKU_MAIL_SOSHIN_FLG = ?"
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " DANTAI_MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiBaitaiKbn());
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setString(i++, bo.getDantaiMoshikomiPasswd());
            stmt.setString(i++, bo.getMoshikomishaSuIka());
            stmt.setString(i++, bo.getMoshikomishaSuShika());
            stmt.setString(i++, bo.getTetsudukiJokyoKbn());
            stmt.setString(i++, bo.getKessaiJokyoKbn());
            stmt.setString(i++, bo.getKessaiHohoKbn());
            stmt.setString(i++, bo.getKessaiKameitenCode());
            stmt.setString(i++, bo.getKessaiTorihikiCode());
            stmt.setString(i++, bo.getKessaiConvenienceShubetsu());
            stmt.setString(i++, bo.getKessaiConvenienceHaraikomihyoUrl());
            stmt.setString(i++, bo.getKessaiKigenBi());
            stmt.setString(i++, bo.getKessaiKingaku());
            stmt.setString(i++, bo.getKariUketsukeBi());
            stmt.setString(i++, bo.getKariUketsukeTime());
            stmt.setString(i++, bo.getMoshikomiFinishBi());
            stmt.setString(i++, bo.getMoshikomiFinishTime());
            stmt.setString(i++, bo.getMoshikomiMemo());
            stmt.setString(i++, bo.getKanriMemo());
            stmt.setString(i++, bo.getKessaiTokusokuMailSoshinFlg());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());

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
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomiDantaiDao#remove(jp.co.nii.miw.business.domain.GeneratedMoshikomiDantai)
     */
    @Override
    public void remove(GeneratedMoshikomiDantai bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "DELETE FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " DANTAI_MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());

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
    protected void setBoFromResultSet(GeneratedMoshikomiDantai bo, ResultSet rs) {
        try {
            bo.setDantaiMoshikomiUketsukeNo(rs.getString("DANTAI_MOSHIKOMI_UKETSUKE_NO"));
            bo.setNendo(rs.getString("NENDO"));
            bo.setKaisu(rs.getString("KAISU"));
            bo.setMoshikomiBaitaiKbn(rs.getString("MOSHIKOMI_BAITAI_KBN"));
            bo.setDantaiCode(rs.getString("DANTAI_CODE"));
            bo.setDantaiMoshikomiPasswd(rs.getString("DANTAI_MOSHIKOMI_PASSWD"));
            bo.setMoshikomishaSuIka(rs.getString("MOSHIKOMISHA_SU_IKA"));
            bo.setMoshikomishaSuShika(rs.getString("MOSHIKOMISHA_SU_SHIKA"));
            bo.setTetsudukiJokyoKbn(rs.getString("TETSUDUKI_JOKYO_KBN"));
            bo.setKessaiJokyoKbn(rs.getString("KESSAI_JOKYO_KBN"));
            bo.setKessaiHohoKbn(rs.getString("KESSAI_HOHO_KBN"));
            bo.setKessaiKameitenCode(rs.getString("KESSAI_KAMEITEN_CODE"));
            bo.setKessaiTorihikiCode(rs.getString("KESSAI_TORIHIKI_CODE"));
            bo.setKessaiConvenienceShubetsu(rs.getString("KESSAI_CONVENIENCE_SHUBETSU"));
            bo.setKessaiConvenienceHaraikomihyoUrl(rs.getString("KESSAI_CONVENIENCE_HARAIKOMIHYO_URL"));
            bo.setKessaiKigenBi(rs.getString("KESSAI_KIGEN_BI"));
            bo.setKessaiKingaku(rs.getString("KESSAI_KINGAKU"));
            bo.setKariUketsukeBi(rs.getString("KARI_UKETSUKE_BI"));
            bo.setKariUketsukeTime(rs.getString("KARI_UKETSUKE_TIME"));
            bo.setMoshikomiFinishBi(rs.getString("MOSHIKOMI_FINISH_BI"));
            bo.setMoshikomiFinishTime(rs.getString("MOSHIKOMI_FINISH_TIME"));
            bo.setMoshikomiMemo(rs.getString("MOSHIKOMI_MEMO"));
            bo.setKanriMemo(rs.getString("KANRI_MEMO"));
            bo.setKessaiTokusokuMailSoshinFlg(rs.getString("KESSAI_TOKUSOKU_MAIL_SOSHIN_FLG"));
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
