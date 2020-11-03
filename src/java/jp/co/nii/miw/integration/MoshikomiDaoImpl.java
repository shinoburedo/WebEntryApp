package jp.co.nii.miw.integration;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MoshikomiDao;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jp.co.nii.miw.business.service.dantai.DntInfSearch;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.business.domain.LockException;
import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.utility.StringUtility;

/**
 * 申込 DAO実装クラス
 * @author DB管理ツール
 */
public class MoshikomiDaoImpl extends GeneratedMoshikomiDaoImpl implements MoshikomiDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public MoshikomiDaoImpl(String datasource) {
        super(datasource);
    }

    @Override
    public Moshikomi find(Moshikomi bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
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

    @Override
    public Moshikomi SaishutsuganFind(Moshikomi bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
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
    public Moshikomi lockFind(Moshikomi bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
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
    public Moshikomi findForUserId(Moshikomi bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND USER_ID = " + getSQLForEncryptByUTF8("?")
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getRonriSakujoFlg());

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            if (rs.next()) {
                setBoFromResultSet(bo, rs);
                if (rs.next()) {
                    LogGenerate.errWrite("年度・回数・ユーザＩＤが同じレコードが複数あります。");
                    bo = null;
                }
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
     * ２重登録チェック用の検索。<br>
     * 検索キーとしてＩＤを指定する。
     * @param nendo 年度
     * @param ki 期
     * @param id ＩＤ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Moshikomi findDoubleCheck(String nendo, String ki, String id, String lockMode) {
        Moshikomi bo = new Moshikomi();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
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
                    + " USER_ID = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " TETSUDUKI_JOKYO_KBN IN(" + getSQLForEncryptByUTF8("?") + "," + getSQLForEncryptByUTF8("?") + ")"
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, nendo);
            stmt.setString(i++, ki);
            stmt.setString(i++, id);
            stmt.setString(i++, MiwConstants.TETSUDUKI_JOKYO_KBN_KANRYO);
            stmt.setString(i++, MiwConstants.TETSUDUKI_JOKYO_KBN_KARI);
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

    /**
     * 再申込チェック用の検索。<br>
     * @param nendo 年度
     * @param ki 期
     * @param id ＩＤ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Moshikomi findSaishutsugan(String nendo, String ki, String id, String lockMode) {
        Moshikomi bo = new Moshikomi();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
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
                    + " USER_ID = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " (TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + " OR TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?") + ")"
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ? "
                    + "ORDER BY KARI_UKETSUKE_BI DESC";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, nendo);
            stmt.setString(i++, ki);
            stmt.setString(i++, id);
            stmt.setString(i++, MiwConstants.TETSUDUKI_JOKYO_UKETSUKE_MAE);
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
    public void update(Moshikomi bo, String selEventCode) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " EVENT_CODE = ?"
                    + ",MOSHIKOMI_BAITAI_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KOJIN_DANTAI_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_MOSHIKOMI_UKETSUKE_NO = " + getSQLForEncryptByUTF8("?")
                    + ",USER_ID = " + getSQLForEncryptByUTF8("?")
                    + ",TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",SHIKENCHI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",KAIJO_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",JUKO_KEIKEN = " + getSQLForEncryptByUTF8("?")
                    + ",JITSUMU_KEIKEN = " + getSQLForEncryptByUTF8("?")
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
                    + ",GAZO_ID = " + getSQLForEncryptByUTF8("?")
                    + ",HOSEI_IRAI_KBN = ?"
                    + ",HOSEI_IRAI_BI = ?"
                    + ",HOSEI_IRAI_TIME = ?"
                    + ",HOSEI_TAIO_BI = ?"
                    + ",HOSEI_TAIO_TIME = ?"
                    + ",HOSEI_FINISH_BI = ?"
                    + ",HOSEI_FINISH_TIME = ?"
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getMoshikomiBaitaiKbn());
            stmt.setString(i++, bo.getKojinDantaiKbn());
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getTetsudukiJokyoKbn());
            stmt.setString(i++, bo.getShikenchiCode());
            stmt.setString(i++, bo.getKaijoCode());
            stmt.setString(i++, bo.getJukoKeiken());
            stmt.setString(i++, bo.getJitsumuKeiken());
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
            stmt.setString(i++, bo.getGazoId());
            stmt.setString(i++, bo.getHoseiIraiKbn());
            stmt.setString(i++, bo.getHoseiIraiBi());
            stmt.setString(i++, bo.getHoseiIraiTime());
            stmt.setString(i++, bo.getHoseiTaioBi());
            stmt.setString(i++, bo.getHoseiTaioTime());
            stmt.setString(i++, bo.getHoseiFinishBi());
            stmt.setString(i++, bo.getHoseiFinishTime());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, selEventCode);

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

    @Override
    public void updateSaishutsugan(Moshikomi bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " EVENT_CODE = ?"
                    + ",MOSHIKOMI_BAITAI_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KOJIN_DANTAI_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_MOSHIKOMI_UKETSUKE_NO = " + getSQLForEncryptByUTF8("?")
                    + ",USER_ID = " + getSQLForEncryptByUTF8("?")
                    + ",TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",SHIKENCHI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",JUKO_KEIKEN = " + getSQLForEncryptByUTF8("?")
                    + ",JITSUMU_KEIKEN = " + getSQLForEncryptByUTF8("?")
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
                    + ",GAZO_ID = " + getSQLForEncryptByUTF8("?")
                    + ",HOSEI_IRAI_KBN = ?"
                    + ",HOSEI_IRAI_BI = ?"
                    + ",HOSEI_IRAI_TIME = ?"
                    + ",HOSEI_TAIO_BI = ?"
                    + ",HOSEI_TAIO_TIME = ?"
                    + ",HOSEI_FINISH_BI = ?"
                    + ",HOSEI_FINISH_TIME = ?"
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    //                    + ",TOROKU_DATE = ?"
                    //                    + ",TOROKU_TIME = ?"
                    //                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getMoshikomiBaitaiKbn());
            stmt.setString(i++, bo.getKojinDantaiKbn());
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getTetsudukiJokyoKbn());
            stmt.setString(i++, bo.getShikenchiCode());
            stmt.setString(i++, bo.getJukoKeiken());
            stmt.setString(i++, bo.getJitsumuKeiken());
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
            stmt.setString(i++, bo.getGazoId());
            stmt.setString(i++, bo.getHoseiIraiKbn());
            stmt.setString(i++, bo.getHoseiIraiBi());
            stmt.setString(i++, bo.getHoseiIraiTime());
            stmt.setString(i++, bo.getHoseiTaioBi());
            stmt.setString(i++, bo.getHoseiTaioTime());
            stmt.setString(i++, bo.getHoseiFinishBi());
            stmt.setString(i++, bo.getHoseiFinishTime());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
//            stmt.setString(i++, bo.getTorokuDate());
//            stmt.setString(i++, bo.getTorokuTime());
//            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
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
    
    @Override
    public void updateForMskByCrd(Moshikomi bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_HOHO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_KAMEITEN_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_TORIHIKI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_KINGAKU = " + getSQLForEncryptByUTF8("?")
                    + ",KARI_UKETSUKE_BI = " + getSQLForEncryptByUTF8("?")
                    + ",KARI_UKETSUKE_TIME = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMI_FINISH_BI = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMI_FINISH_TIME = " + getSQLForEncryptByUTF8("?")
                    + ",GAZO_ID = " + getSQLForEncryptByUTF8("?")
                    + ",SHORI_KBN = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getTetsudukiJokyoKbn());
            stmt.setString(i++, bo.getKessaiJokyoKbn());
            stmt.setString(i++, bo.getKessaiHohoKbn());
            stmt.setString(i++, bo.getKessaiKameitenCode());
            stmt.setString(i++, bo.getKessaiTorihikiCode());
            stmt.setString(i++, bo.getKessaiKingaku());
            stmt.setString(i++, bo.getKariUketsukeBi());
            stmt.setString(i++, bo.getKariUketsukeTime());
            stmt.setString(i++, bo.getMoshikomiFinishBi());
            stmt.setString(i++, bo.getMoshikomiFinishTime());
            stmt.setString(i++, bo.getGazoId());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
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

    @Override
    public void updateForMskByCvsPayEasy(Moshikomi bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
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
                    + ",GAZO_ID = " + getSQLForEncryptByUTF8("?")
                    + ",SHORI_KBN = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
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
            stmt.setString(i++, bo.getGazoId());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
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
     * @see jp.co.nii.how.business.domain.GeneratedAnketoKaitoDao#find(jp.co.nii.how.business.domain.GeneratedAnketoKaito, java.lang.String)
     */
    @Override
    public Moshikomi findKessaiRetry(String sid, String kingaku, String messageShubetsu) {
        Moshikomi ret = null;
        for (int cnt = 1; cnt <= 5; cnt++) {
            try {
                if (TransactionUtility.isTheCurrentThreadAssociatedWithAnyTransaction()) {
                    setSavepoint("SAVE_POINT_BEFORE_SAIBAN_LOCK", null);
                }
                ret = (Moshikomi) findKessai(sid, kingaku, messageShubetsu, TransactionUtility.LOCK_NOWAIT);
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

    /* (non-Javadoc)
     * @see jp.co.nii.how.business.domain.GeneratedAnketoKaitoDao#find(jp.co.nii.how.business.domain.GeneratedAnketoKaito, java.lang.String)
     */
    @Override
    public Moshikomi findRetry(Moshikomi moshikomi) {
        Moshikomi ret = null;
        for (int cnt = 1; cnt <= 5; cnt++) {
            try {
                if (TransactionUtility.isTheCurrentThreadAssociatedWithAnyTransaction()) {
                    setSavepoint("SAVE_POINT_BEFORE_SAIBAN_LOCK", null);
                }
                ret = (Moshikomi) find(moshikomi, TransactionUtility.LOCK_NOWAIT);
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

    /**
     * 入金通知時の検索。<br>
     * @param sid 取引コード
     * @param kingaku 金額
     * @param messageShubetsu メッセージ種別
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Moshikomi findKessai(String sid, String kingaku, String messageShubetsu, String lockMode) {

        Moshikomi bo = new Moshikomi();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " KESSAI_TORIHIKI_CODE = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " KESSAI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " KESSAI_KINGAKU = " + getSQLForEncryptByUTF8("?")
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, sid);
            if (messageShubetsu.equals(MiwConstants.MESSAGE_SHUBETSU_RECEIPT)) {
                //入金時
                stmt.setString(i++, MiwConstants.TETSUDUKI_JOKYO_KBN_KARI);
                stmt.setString(i++, MiwConstants.KESSAI_JOKYO_KBN_MI);
            } else {
                //キャンセル時
                stmt.setString(i++, MiwConstants.TETSUDUKI_JOKYO_KBN_KANRYO);
                stmt.setString(i++, MiwConstants.KESSAI_JOKYO_KBN_KAKUNIN);
            }

            stmt.setString(i++, kingaku);
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
    public DntInfSearch selectMskListFromDntInfo(Moshikomi bo, int offset, int limit, String sort) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<MskToroku> list = new ArrayList<MskToroku>();
        DntInfSearch dntInfSrc = null;
        try {
            con = getConnection();

            // 検索と同時に件数も取得する
            sql = "SELECT MOSHIKOMI_UKETSUKE_NO,MOSHIKOMISHA.USER_ID,KAISU,NENDO"
                    + "," + getSQLForDecryptByUTF8("DANTAI_CODE") + ","
                    + getSQLForDecryptByUTF8("SHIMEI_SEI") + "," + getSQLForDecryptByUTF8("SHIMEI_MEI") + ","
                    + getSQLForDecryptByUTF8("SHIMEI_SEI_KANA") + "," + getSQLForDecryptByUTF8("SHIMEI_MEI_KANA") + ","
                    + getSQLForDecryptByUTF8("TEL_NO") + "," + getSQLForDecryptByUTF8("CELLPHONE_NO") + ","
                    + "EVENT_CODE," + getSQLForDecryptByUTF8("SHIKENCHI_CODE") + ", "
                    + getSQLForDecryptByUTF8("TETSUDUKI_JOKYO_KBN") + ", "
                    + " (SELECT COUNT(MOSHIKOMI_UKETSUKE_NO)  FROM " + getMakeSql(bo) + ") AS CNT_ALL"
                    + " FROM " + getMakeSql(bo) + getOrderBySql(sort) + " OFFSET ?  LIMIT ? ";

            // 申込者情報検索
            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setInt(i++, offset);
            stmt.setInt(i++, limit);

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            int cnt_all = 0;
            while (rs.next()) {
                // 申込者情報一覧取得
                MskToroku mskInf = new MskToroku();
                setMskInfFromResultSet(mskInf, rs);
                cnt_all = rs.getInt("CNT_ALL");
                list.add(mskInf);
            }
            dntInfSrc = new DntInfSearch(list);
            dntInfSrc.setSchResuCount(cnt_all);
            
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }
        return dntInfSrc;
    }

    @Override
    public String[] findTorokushaSu(Moshikomi bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] ret = {"0", "0"};
        try {
            con = getConnection();
            String sql = " SELECT "
                    + " COALESCE(SUM(CASE WHEN EVENT_CODE = ? THEN 1 ELSE 0 END ), 0) AS CNT_IKA,"
                    + " COALESCE(SUM(CASE WHEN EVENT_CODE = ? THEN 1 ELSE 0 END ), 0) AS CNT_SHIKA"
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " INNER JOIN " + getSchemaName() + "." + MoshikomishaDaoImpl.TABLE_NAME
                    + " ON " + TABLE_NAME + ".USER_ID = " + getSQLForEncryptByUTF8(MoshikomishaDaoImpl.TABLE_NAME + ".USER_ID")
                    + " AND " + MoshikomishaDaoImpl.TABLE_NAME + ".RONRI_SAKUJO_FLG ='" + MiwConstants.FLG_OFF + "'"
                    + " AND " + MoshikomishaDaoImpl.TABLE_NAME + ".MOSHIKOMI_ARI_FLG = " + getSQLForEncryptByUTF8("'" + MiwConstants.FLG_ON + "'")
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND DANTAI_MOSHIKOMI_UKETSUKE_NO = " + getSQLForEncryptByUTF8("?")
                    + " AND " + TABLE_NAME + ".RONRI_SAKUJO_FLG ='" + MiwConstants.FLG_OFF + "'";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, MiwConstants.JUKEN_KAMOKU_IKA);
            stmt.setString(i++, MiwConstants.JUKEN_KAMOKU_SHIKA);
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            if (rs.next()) {
                ret[0] = StringUtility.convertBlankToZero(rs.getString("CNT_IKA"));
                ret[1] = StringUtility.convertBlankToZero(rs.getString("CNT_SHIKA"));
            }
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }
        return ret;
    }

    /**
     * OrderBy句の生成
     * @param mskSearch
     * @param sortCode
     * @return 
     */
    private String getOrderBySql(String sortCode) {

        String order_by = " ORDER BY ";

        if (sortCode.equals(MiwConstants.SORT_CODE_KANA_ASC)) {
            order_by += "SHIMEI_SEI_KANA ASC,SHIMEI_MEI_KANA ASC,";
        } else if (sortCode.equals(MiwConstants.SORT_CODE_KANA_DESC)) {
            order_by += "SHIMEI_SEI_KANA DESC,SHIMEI_MEI_KANA DESC,";
        }
        order_by += "NENDO,KAISU,MOSHIKOMI_UKETSUKE_NO,EVENT_CODE";
        
        return order_by;

    }
    
    /**
     * 条件文のSQL文
     * @param bo
     * @return 
     */
    private String getMakeSql(Moshikomi bo){
        String sql ="";
        sql = getSchemaName() + "." + TABLE_NAME + " INNER JOIN "
                + getSchemaName() + "." + MoshikomishaDaoImpl.TABLE_NAME
                + " ON MOSHIKOMI.USER_ID= "+ getSQLForEncryptByUTF8 (MoshikomishaDaoImpl.TABLE_NAME + ".USER_ID")
                + " AND " + TABLE_NAME + ".RONRI_SAKUJO_FLG ='" + MiwConstants.FLG_OFF + "'"
                + " AND " + MoshikomishaDaoImpl.TABLE_NAME + ".RONRI_SAKUJO_FLG = '" + MiwConstants.FLG_OFF + "'" 
                + " WHERE NENDO = ? AND KAISU = ? AND DANTAI_CODE = "
                + getSQLForEncryptByUTF8("?") ; 
        
        return sql;
    }
    
        /**
     * 検索結果であるResultSetをBusinessObjectへ詰め替える。<br>
     * 事前条件：引数のboとrsはnullでないこと。
     * 
     * @param mskInf 申込情報のBeanのインスタンス
     * @param rs 検索結果のResultSet
     */
    protected void setMskInfFromResultSet(MskToroku mskInf, ResultSet rs) {
        try {
            mskInf.setNendo(rs.getString("NENDO"));
            mskInf.setKaisu(rs.getString("KAISU"));
            mskInf.setMoshikomiUketsukeNo(rs.getString("MOSHIKOMI_UKETSUKE_NO"));
            mskInf.setUserId(rs.getString("USER_ID"));
            mskInf.setDantaiCode(rs.getString("DANTAI_CODE"));
            mskInf.setEventCode(rs.getString("EVENT_CODE"));
            mskInf.setShikenchiCode(rs.getString("SHIKENCHI_CODE"));
            mskInf.setShimeiSeiKana(rs.getString("SHIMEI_SEI_KANA"));
            mskInf.setShimeiMeiKana(rs.getString("SHIMEI_MEI_KANA"));
            mskInf.setShimeiSei(rs.getString("SHIMEI_SEI"));
            mskInf.setShimeiMei(rs.getString("SHIMEI_MEI"));
            mskInf.setTelNo(rs.getString("TEL_NO"));
            mskInf.setCellphoneNo(rs.getString("CELLPHONE_NO"));
            mskInf.setTetsudukiJokyoKbn(rs.getString("TETSUDUKI_JOKYO_KBN"));

        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(null, ex);
        }
    }

}
