package jp.co.nii.sew;

/**
 * SEPの例外基底クラス<br>
 *
 * SEPで独自に定義する例外は全てこのサブクラスとする。<br>
 * SEPの例外は、非チェック例外とする。これにより、例外のcatchやthrowを強制されなくなり、<br>
 * 例外の対応をしていなくてもコンパイラはコンパイルエラーとしてチェックしてくれなくなる。<br>
 * 反面、コードが読みやすくなり、インターフェースも汚染されなくなる。<br>
 * 例外をcatchして処理したい場合は、プログラマが意識してtry catchブロックを記述すること。<br>
 * スレッドの起動の起点となるservlet等のクラスは、SEP例外を含めて例外を漏れなくcatchすること。<br>
 *
 * @author n-machida
 */
public class SEWException extends RuntimeException {

    /**
     * コンストラクタ
     */
    public SEWException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     */
    public SEWException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param throwable 実際に発生した例外
     */
    public SEWException(Throwable throwable) {
        super(throwable);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param throwable 実際に発生した例外
     */
    public SEWException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
