package jp.co.nii.sew.integration;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import jp.co.nii.sew.business.domain.DataAccessException;
import jp.co.nii.sew.business.domain.DataAccessResourceUnavailableException;
import jp.co.nii.sew.business.domain.IntegrityConstraintViolationException;
import jp.co.nii.sew.business.domain.InvalidDataAccessResourceUsageException;
import jp.co.nii.sew.business.domain.InvalidDataException;
import jp.co.nii.sew.business.domain.LockException;
import jp.co.nii.sew.business.domain.UncategorizedDataAccessException;
import jp.co.nii.sew.business.domain.UniqueViolationException;
import jp.co.nii.sew.business.service.transaction.TransactionException;
import jp.co.nii.sew.integration.DataAccessResourceStarvationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of SQLExceptionTranslater that uses the
 * SQLState code in the SQL exception.
 * Can't diagnose all problems, but is portable between
 * databases.
 * @author Rod Johnson, n-machida
 * @version $Id: SQLStateSQLExceptionTranslater.java,v 1.1 2002/08/24 08:43:49 Rod Johnson Exp $
 */
public class SQLStateSQLExceptionTranslater implements SQLExceptionTranslater {

    /**
     * PostgreSQLエラーコード
     * PostgreSQL 8.4.2文書/errcodes-appendix.html より
     *
     * 00クラス― 正常終了
     * 00000	正常終了	successful_completion
     *
     * 01000	警告	warning
     * 0100C	動的な結果セットが返された	dynamic_result_sets_returned
     * 01008	暗黙的な0によるビット埋めがある	implicit_zero_bit_padding
     * 01003	集合関数内でNULL値は省略された	null_value_eliminated_in_set_function
     * 01007	権限が付与されていない	privilege_not_granted
     * 01006	権限が剥奪されていない	privilege_not_revoked
     * 01004	文字列データの右側が除去された	string_data_right_truncation
     * 01P01	廃止予定の機能	deprecated_feature
     *
     * 02 クラス ― データがない。(SQL標準ではこれは警告クラス)
     * 02000	データがない	no_data
     * 02001	さらなる動的結果セットは返されなかった	no_additional_dynamic_result_sets_returned
     *
     * 03 クラス ― SQL文の未完了
     * 03000	SQL文は未完了	sql_statement_not_yet_complete
     *
     * 08 クラス ― 接続の例外 >>> DataAccessResourceUnavailableException
     * 08000	接続の例外が発生	connection_exception
     * 08003	接続が存在しない	connection_does_not_exist
     * 08006	接続に失敗した	connection_failure
     * 08001	SQLクライアントはSQL接続を確立できなかった	sqlclient_unable_to_establish_sqlconnection
     * 08004	SQLサーバはSQL接続の確立を拒絶した	sqlserver_rejected_establishment_of_sqlconnection
     * 08007	トランザクションの解決が不明だった	transaction_resolution_unknown
     * 08P01	プロトコル違反	protocol_violation
     *
     * 09 クラス ― トリガによるアクションの例外
     * 09000	トリガによるアクション例外	triggered_action_exception
     *
     * 0A クラス ― サポートされない機能
     * 0A000	サポートされない機能	feature_not_supported
     *
     * 0B クラス ― 無効なトランザクションの初期
     * 0B000	無効なトランザクションの初期化	invalid_transaction_initiation
     *
     * 0F クラス ― ロケータの例外
     * 0F000	ロケータの例外	locator_exception
     * 0F001	無効なロケータ指定	invalid_locator_specification
     *
     * 0L クラス ― 無効な権限付与
     * 0L000	無効な権限付与	invalid_grantor
     * 0LP01	無効な権限付与操作	invalid_grant_operation
     *
     * 0P クラス ― 無効なロールの指定
     * 0P000	無効なロールの指定	invalid_role_specification
     *
     * 20クラス ― Caseが存在しない
     * 20000	CASEが存在しない	case_not_found
     *
     * 21 クラス ― 次数違反
     * 21000	次数違反	cardinality_violation
     *
     * 22 クラス ― データ例外 >>> InvalidDataException
     * 22000	データ例外	data_exception
     * 2202E	配列添え字エラー	array_subscript_error
     * 22021	許容範囲外の文字	character_not_in_repertoire
     * 22008	日付時刻フィールドのオーバーフロー	datetime_field_overflow
     * 22012	ゼロ除算	division_by_zero
     * 22005	代入エラー	error_in_assignment
     * 2200B	エスケープ文字の競合	escape_character_conflict
     * 22022	指示子のオーバーフロー	indicator_overflow
     * 22015	内部フィールドのオーバーフロー	interval_field_overflow
     * 2201E	無効な対数の変数	invalid_argument_for_logarithm
     * 22014	NTILE関数に対する無効な引数	invalid_argument_for_ntile_function
     * 22016	無効なNTH_VALUE関数の引数	invalid_argument_for_nth_value_function
     * 2201F	無効な階乗関数の変数	invalid_argument_for_power_function
     * 2201G	無効な幅バケット関数の変数	invalid_argument_for_width_bucket_function
     * 22018	無効なキャスト文字	invalid_character_value_for_cast
     * 22007	無効な日付時刻の書式	invalid_datetime_format
     * 22019	無効なエスケープ文字	invalid_escape_character
     * 2200D	無効なエスケープバイト（8ビット）	invalid_escape_octet
     * 22025	無効なエスケープシーケンス	invalid_escape_sequence
     * 22P06	エスケープ文字の非標準な使用	nonstandard_use_of_escape_character
     * 22010	無効な指示子パラメータの値	invalid_indicator_parameter_value
     * 22023	無効なパラメータ値	invalid_parameter_value
     * 2201B	無効な正規表現	invalid_regular_expression
     * 2201W	LIMIT句における無効な行番号	invalid_row_count_in_limit_clause
     * 2201X	オフセット句結果における無効な行数	invalid_row_count_in_result_offset_clause
     * 22009	無効なタイムゾーン置換値	invalid_time_zone_displacement_value
     * 2200C	エスケープ文字の無効な使用	invalid_use_of_escape_character
     * 2200G	最も明確な型の不一致	most_specific_type_mismatch
     * 22004	NULL値の不許可	null_value_not_allowed
     * 22002	NULL値、もしくは指示子パラメータがない	null_value_no_indicator_parameter
     * 22003	範囲外の数値	numeric_value_out_of_range
     * 22026	文字列長の不一致	string_data_length_mismatch
     * 22001	文字列データの右側の切り詰め	string_data_right_truncation
     * 22011	部分文字列エラー	substring_error
     * 22027	切り詰めエラー	trim_error
     * 22024	終端のないC文字列	unterminated_c_string
     * 2200F	空の文字列	zero_length_character_string
     * 22P01	浮動小数点例外	floating_point_exception
     * 22P02	無効なテキスト表現	invalid_text_representation
     * 22P03	無効なバイナリ表現	invalid_binary_representation
     * 22P04	コピーファイルの書式不良	bad_copy_file_format
     * 22P05	翻訳できない文字	untranslatable_character
     * 2200L	XML文書ではない	not_an_xml_document
     * 2200M	不正なXMLドキュメント	invalid_xml_document
     * 2200N	不正なXMLコンテント	invalid_xml_content
     * 2200S	不正なXMLコメント	invalid_xml_comment
     * 2200T	不正なXML処理指示	invalid_xml_processing_instruction
     *
     * 23 クラス ― 整合性制約違反 >>> IntegrityConstraintViolationException
     * 23000	整合性制約違反	integrity_constraint_violation
     * 23001	制限違反	restrict_violation
     * 23502	非NULL違反	not_null_violation
     * 23503	外部キー違反	foreign_key_violation
     * 23505	一意性違反	unique_violation
     * 23514	検査違反	check_violation
     *
     * 24 クラス ― 無効なカーソル状態
     * 24000	無効なカーソル状態	invalid_cursor_state
     *
     * 25 クラス ― 無効なトランザクション状態 >>> TransactionException
     * 25000	無効なトランザクション状態	invalid_transaction_state
     * 25001	SQLトランザクションが実行中	active_sql_transaction
     * 25002	分岐トランザクションが既に実行中	branch_transaction_already_active
     * 25008	保持しているカーソルは同一の隔離レベルを要求	held_cursor_requires_same_isolation_level
     * 25003	分岐トランザクションにおける不適切なアクセスモード	inappropriate_access_mode_for_branch_transaction
     * 25004	分岐トランザクションにおける不適切な隔離レベル	inappropriate_isolation_level_for_branch_transaction
     * 25005	分岐トランザクションにおいて活動中のSQLトランザクションが存在しない	no_active_sql_transaction_for_branch_transaction
     * 25006	読み取りのみのSQLトランザクション	read_only_sql_transaction
     * 25007	スキーマとデータ文の混在はサポートしていない	schema_and_data_statement_mixing_not_supported
     * 25P01	活動中のSQLトランザクションが存在しない	no_active_sql_transaction
     * 25P02	失敗したSQLトランザクション内である	in_failed_sql_transaction
     *
     * 26クラス― 無効なSQL文の名前
     * 26000	無効なSQL文の名前	invalid_sql_statement_name
     *
     * 27クラス― トリガによるデータ変更違反
     * 27000	トリガによるデータ変更違反	triggered_data_change_violation
     *
     * 28クラス― 無効な認証指定
     * 28000	無効な認証指定	invalid_authorization_specification
     *
     * 2B クラス ― 依存する権限記述子がまだ存在する
     * 2B000	依存する権限記述子がまだ存在する	dependent_privilege_descriptors_still_exist
     * 2BP01	依存する権限記述子がまだ存在する	dependent_objects_still_exist
     *
     * 2D クラス ― 無効なトランザクションの終了 >>> TransactionException
     * 2D000	無効なトランザクションの終了	invalid_transaction_termination
     *
     * 2F クラス ― SQL関数例外
     * 2F000	SQL関数例外	sql_routine_exception
     * 2F005	実行した関数にRETURN文が存在しない	function_executed_no_return_statement
     * 2F002	SQLデータの変更は許可されていない	modifying_sql_data_not_permitted
     * 2F003	禁止されたSQL文の試行	prohibited_sql_statement_attempted
     * 2F004	SQLデータの読み取りは許可されていない	reading_sql_data_not_permitted
     *
     * 34 クラス ― 無効なカーソル名称
     * 34000	無効なカーソル名称	invalid_cursor_name
     * 38 クラス ― 外部関数例外
     * 38000	外部関数例外	external_routine_exception
     * 38001	含まれるSQLは許可されていない	containing_sql_not_permitted
     * 38002	SQLデータの変更は許可されていない	modifying_sql_data_not_permitted
     * 38003	禁止されたSQL文の試行	prohibited_sql_statement_attempted
     * 38004	SQLデータの読み取りは許可されていない	reading_sql_data_not_permitted
     *
     * 39 クラス ― 外部関数呼び出し例外
     * 39000	外部関数呼び出し例外	external_routine_invocation_exception
     * 39001	無効なSQLSTATEが返された	invalid_sqlstate_returned
     * 39004	NULL 値は許されていない	null_value_not_allowed
     * 39P01	トリガプロトコル違反	trigger_protocol_violated
     * 39P02	SRFプロトコル違反	srf_protocol_violated
     *
     * 3B クラス ― セーブポイント例外
     * 3B000	セーブポイント例外	savepoint_exception
     * 3B001	無効なセーブポイント指定	invalid_savepoint_specification
     *
     * 3D クラス ― 無効なカタログ名称
     * 3D000	無効なカタログ名称	invalid_catalog_name
     *
     * 3F クラス ― 無効なスキーマ名称
     * 3F000	無効なスキーマ名称	invalid_schema_name
     *
     * 40 クラス ― トランザクションロールバック
     * 40000	トランザクションロールバック	transaction_rollback
     * 40002	トランザクション整合性制約違反	transaction_integrity_constraint_violation
     * 40001	シリアライゼーション失敗	serialization_failure
     * 40003	文の完了が不明	statement_completion_unknown
     * 40P01	デッドロックの検出	deadlock_detected >>> LockException
     *
     * 42 クラス ― 構文エラー、もしくはアクセスロール違反 >>> InvalidDataAccessResourceUsageException
     * 42000	構文エラー、もしくはアクセスロール違反	syntax_error_or_access_rule_violation
     * 42601	構文エラー	syntax_error
     * 42501	不十分な権限	insufficient_privilege
     * 42846	強制型変換不可能	cannot_coerce
     * 42803	グループ化エラー	grouping_error
     * 42P20	ウィンドウエラー	windowing_error
     * 42P19	無効な再帰	invalid_recursion
     * 42830	無効な外部キー	invalid_foreign_key
     * 42602	無効な名称	invalid_name
     * 42622	長過ぎる名称	name_too_long
     * 42939	予約語	reserved_name
     * 42804	データ型の不一致	datatype_mismatch
     * 42P18	データ型の未解決	indeterminate_datatype
     * 42809	不正なオブジェクトの型	wrong_object_type
     * 42703	未定義列	undefined_column
     * 42883	未定義関数	undefined_function
     * 42P01	未定義テーブル	undefined_table
     * 42P02	未定義パラメータ	undefined_parameter
     * 42704	未定義オブジェクト	undefined_object
     * 42701	列の重複	duplicate_column
     * 42P03	カーソルの重複	duplicate_cursor
     * 42P04	データベースの重複	duplicate_database
     * 42723	関数の重複	duplicate_function
     * 42P05	準備された文の重複	duplicate_prepared_statement
     * 42P06	スキーマの重複	duplicate_schema
     * 42P07	テーブルの重複	duplicate_table
     * 42712	別名の重複	duplicate_alias
     * 42710	オブジェクトの重複	duplicate_object
     * 42702	曖昧な列	ambiguous_column
     * 42725	曖昧な関数	ambiguous_function
     * 42P08	曖昧なパラメータ	ambiguous_parameter
     * 42P09	曖昧な別名	ambiguous_alias
     * 42P10	無効な列参照	invalid_column_reference
     * 42611	無効な列定義	invalid_column_definition
     * 42P11	無効なカーソル定義	invalid_cursor_definition
     * 42P12	無効なデータベース定義	invalid_database_definition
     * 42P13	無効な関数定義	invalid_function_definition
     * 42P14	無効な準備された文の定義	invalid_prepared_statement_definition
     * 42P15	無効なスキーマ定義	invalid_schema_definition
     * 42P16	無効なテーブル定義	invalid_table_definition
     * 42P17	無効なオブジェクト定義	invalid_object_definition
     *
     * 44 クラス ― 検査オプションに伴う違反
     * 44000	検査オプションに伴う違反	with_check_option_violation
     *
     * 53 クラス ― リソース不足 >>> DataAccessResourceStarvationException
     * 53000	リソース不足	insufficient_resources
     * 53100	ディスク空き容量不足	disk_full
     * 53200	メモリ不足	out_of_memory
     * 53300	接続過多	too_many_connections
     *
     * 54 クラス ― プログラム制限の超過
     * 54000	プログラム制限の超過	program_limit_exceeded
     * 54001	文が複雑過ぎる	statement_too_complex
     * 54011	列数の過多	too_many_columns
     * 54023	引数の過多	too_many_arguments
     *
     * クラス 55 ― 必要条件を満たさないオブジェクト
     * 55000	必要条件を満たさないオブジェクト	object_not_in_prerequisite_state
     * 55006	使用中のオブジェクト	object_in_use
     * 55P02	関数パラメータは変更できない	cant_change_runtime_param
     * 55P03	ロックは使用できない	lock_not_available >>> LockException
     *
     * 57 クラス ― 操作の介入
     * 57000	操作の介入	operator_intervention
     * 57014	問い合わせのキャンセル	query_canceled
     * 57P01	管理者による停止	admin_shutdown
     * 57P02	クラッシュによる停止	crash_shutdown
     * 57P03	現在接続できない	cannot_connect_now
     *
     * 58 クラス ― システムエラー（外部原因によるPostgreSQL自体のエラー）
     * 58030	入出力エラー	io_error
     * 58P01	未定義のファイル	undefined_file
     * 58P02	重複するファイル	duplicate_file
     *
     * F0 クラス ― 設定ファイルエラー
     * F0000	設定ファイルエラー	config_file_error
     * F0001	ロックファイルの存在	lock_file_exists
     *
     * P0 クラス ― PL/pgSQLエラー
     * P0000	PLPGSQLエラー	plpgsql_error
     * P0001	例外の発生	raise_exception
     * P0002	データがありません	no_data_found
     * P0003	行が多すぎます	too_many_rows
     *
     * XX クラス ― 内部エラー
     * XX000	内部エラー	internal_error
     * XX001	データの破損	data_corrupted
     * XX002	インデックスの破損	index_corrupted
     */
    /** データアクセスリソースが利用不可能なことを示す、コード群 */
    private static Set<String> RESOURCE_UNAVAILABLE_CODES = new HashSet<String>();
    /** 不正データを示す、コード群 */
    private static Set<String> INVALID_DATA_CODES = new HashSet<String>();
    /** 整合性制約違反を示す、コード群 */
    private static Set<String> INTEGRITY_VIOLATION_CODES = new HashSet<String>();
    /** 一意性違反を示す、コード群 */
    private static Set<String> UNIQUE_VIOLATION_CODES = new HashSet<String>();
    /** トランザクションエラーを示す、コード群 */
    private static Set<String> TRANSACTION_ERROR_CODES = new HashSet<String>();
    /** ロックエラーを示す、コード群 */
    private static Set<String> LOCK_ERROR_CODES = new HashSet<String>();
    /** データアクセスリソースを正しく使えていないことを示す、コード群 */
    private static Set<String> INVALID_USAGE_CODES = new HashSet<String>();
    /** データアクセスリソース不足を示す、コード群 */
    private static Set<String> RESOURCE_STARVATION_CODES = new HashSet<String>();

    /* ログ */
    private static Log log = LogFactory.getLog(SQLStateSQLExceptionTranslater.class);

    // Populate reference data
    static {
        RESOURCE_UNAVAILABLE_CODES.add("08");
        INVALID_DATA_CODES.add("22");
        INTEGRITY_VIOLATION_CODES.add("23");
        UNIQUE_VIOLATION_CODES.add("23505");
        TRANSACTION_ERROR_CODES.add("25");
        TRANSACTION_ERROR_CODES.add("2D");
        TRANSACTION_ERROR_CODES.add("3B");
        LOCK_ERROR_CODES.add("40P01");
        LOCK_ERROR_CODES.add("55P03");
        INVALID_USAGE_CODES.add("42");
        RESOURCE_STARVATION_CODES.add("53");

    }

    /**
     * @see SQLExceptionTranslater#translate(String,SQLException)
     */
    @Override
    public DataAccessException translate(String sql, SQLException sqlex) {
        if (sqlex == null) {
            log.debug("Translating SQLException with sql was '" + sql + "'");
        } else {
            log.debug("Translating SQLException with SQLState='" + sqlex.getSQLState()
                    + "' and errorCode=" + sqlex.getErrorCode()
                    + " and message=" + sqlex.getMessage() + "; sql was '" + sql + "'");
        }

        String sqlstate = "";
        if (sqlex != null) {
            sqlstate = sqlex.getSQLState();
            if (sqlstate != null) {

                // 先に5桁での判定
                if (UNIQUE_VIOLATION_CODES.contains(sqlstate)) {
                    throw new UniqueViolationException(sqlstate + ":" + sql, sqlex);
                }
                if (LOCK_ERROR_CODES.contains(sqlstate)) {
                    throw new LockException(sqlstate + ":" + sql, sqlex);
                }

                // 2桁（クラス）での判定
                String classCode = sqlstate.substring(0, 2);
                if (RESOURCE_UNAVAILABLE_CODES.contains(classCode)) {
                    throw new DataAccessResourceUnavailableException(sqlstate + ":" + sql, sqlex);
                }
                if (INVALID_DATA_CODES.contains(classCode)) {
                    throw new InvalidDataException(sqlstate + ":" + sql, sqlex);
                }
                if (INTEGRITY_VIOLATION_CODES.contains(classCode)) {
                    throw new IntegrityConstraintViolationException(sqlstate + ":" + sql, sqlex);
                }
                if (TRANSACTION_ERROR_CODES.contains(classCode)) {
                    throw new TransactionException(sqlstate + ":" + sql, sqlex);
                }
                if (INVALID_USAGE_CODES.contains(classCode)) {
                    throw new InvalidDataAccessResourceUsageException(sqlstate + ":" + sql, sqlex);
                }
//                if (RESOURCE_STARVATION_CODES.contains(classCode)) {
//                    throw new DataAccessResourceStarvationException(sqlstate + ":" + sql, sqlex);
//                }
            }
        }

        // We couldn't identify it more precisely
        return new UncategorizedDataAccessException(sqlstate + ":" + sql, sqlex);
    }
}
