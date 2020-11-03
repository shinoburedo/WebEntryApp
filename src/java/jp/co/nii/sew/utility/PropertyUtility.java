package jp.co.nii.sew.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

/**
 * プロパティファイルユーティリティ
 * プロパティファイルより値を読み込む
 * プロパティファイルへ値を書き込む
 * @author Namie Minegishi(NII)<BR>
 * Machida - readPropertiesFileメソッドでXMLのプロパティファイルに対応<BR>
 * Machida - saveXMLPropertiesFileメソッド追加<BR>
 * Machida - savePlainPropertiesFileメソッド追加
 */
public class PropertyUtility {

    /** プロパティファイル名　WEB-INF/classesディレクトリのファイルを読み書きする。 */
    private static final String PROPERTY_FILE_NAME = "/MiwProperties.properties";
    /** saveの文字エンコーディング */
    private static final String ENCODING = "Shift_JIS"; //UTF-8 or Shift_JIS
    /** Singletonのインスタンス */
    private static PropertyUtility instance = new PropertyUtility();
    /** プロパティファイルの内容 */
    private static Properties properties = null;

    /**
     * SingletonのためにprivateとしてgetInstance()からのみ呼ばれる
     */
    private PropertyUtility() {
    }

    /**
     * このメソッドでのみコンストラクタ呼び出す
     * @return PropertyUtility 1つだけしかないインスタンス
     */
    private static PropertyUtility getInstance() {
        if (instance == null) {
            synchronized (PropertyUtility.class) {
                if (instance == null) {
                    instance = new PropertyUtility();
                }
            }
        }
        return instance;
    }

    /**
     * Propertiesがnullのときのみ、readPropertiesFileを呼び出す
     * @return Properties 読み込んだプロパティの内容
     */
    private Properties getProperties() {
        if (properties == null) {
            readPropertiesFile();
        }
        return properties;
    }

    /**
     * このメソッドでのみ読み込みのファイルアクセスを行う
     * 最初にXMLとして読み込み、xmlとしての読み込みに失敗したら
     * キーと要素が対になったプロパティリストとして読み込む
     */
    private synchronized void readPropertiesFile() {
        if (properties != null) {
            //呼び出し前のチェックとこのメソッドの呼び出しの間に他スレッドに割り込まれたときのため
            return;
        }

        properties = new Properties();
        InputStream in = null;

        in = this.getClass().getResourceAsStream(PROPERTY_FILE_NAME);
        /*
        try {
        //xmlとして読み込む
        properties.loadFromXML(in);
        LogGenerate.infoOutput(
        this.getClass().getName()
        + ".readPropertiesFile loaded "
        + PROPERTY_FILE_NAME
        + " as a .xml file.");
        } catch (InvalidPropertiesFormatException ipfe) {
         */
        try {
            //xmlとしての読み込みに失敗したら
            //キーと要素が対になったプロパティリストとして読み込む
            properties.load(in);
            LogGenerate.infoOutput(
                    this.getClass().getName()
                    + ".readPropertiesFile loaded "
                    + PROPERTY_FILE_NAME
                    + " as a .properties file.");
        } catch (IOException ioe1) {
            ioe1.printStackTrace();
        } /*
        } catch (IOException ioe2) {
        ioe2.printStackTrace();
        }
         */ finally {
            try {
                in.close();
            } catch (IOException ioe3) {
                ioe3.printStackTrace();
            }
        }

    }

    /**
     * プロパティの値を取得する。該当するものがないときはdafaultValueを返す
     * @param key プロパティのキー
     * @param dafaultValue プロパティがないときに返す値
     * @return String プロパティの値
     */
    public static String getProperty(String key, String dafaultValue) {
        return getInstance().getProperties().getProperty(key, dafaultValue);
    }

    /**
     * プロパティの値を取得する
     * @param key プロパティのキー
     * @return String プロパティの値
     */
    public static String getProperty(String key) {
        return getInstance().getProperties().getProperty(key);
    }

    /**
     * プロパティ名の列挙を取得する
     * @return Enumeration プロパティ名の列挙
     */
    public static Enumeration propertyNames() {
        return getInstance().getProperties().propertyNames();
    }

    /**
     * クラス変数のinstanceとpropertiesをnullにする
     */
    public static void initFile() {
        properties = null;
        instance = null;
    }

    /**
     * XMLのプロパティリストを新規作成する
     * 日本語を直接扱える
     * Shift_JISでエンコーディングする
     * @param newfilename ファイル名　既存のファイルの場合、ファイルを上書きする
     * @param prop Properties プロパティの内容
     * @param comment コメントに指定する文字
     */
    /*
    public static void createXMLPropertiesFile(
    String newfilename,
    Properties prop,
    String comment) {
    
    OutputStream stream = null;
    
    try {
    stream = new FileOutputStream(newfilename);
    prop.storeToXML(stream, comment, ENCODING);
    } catch (IOException e) {
    e.printStackTrace();
    } finally {
    try {
    stream.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    }
     */
    /**
     * キーと要素が対になったプロパティリストを新規作成する
     * 日本語はnative2asciiによるUnicodeエスケープが必要
     * @param newfilename ファイル名　既存のファイルの場合、ファイルを上書きする
     * @param prop Properties プロパティの内容
     * @param comment コメントに指定する文字
     */
    public static void createPlainPropertiesFile(
            String newfilename,
            Properties prop,
            String comment) {

        OutputStream stream = null;

        try {
            stream = new FileOutputStream(newfilename);
            prop.store(stream, comment);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * XMLのプロパティリストに要素を追加する
     * keyが同じプロパティは上書きする
     * Shift_JISでエンコーディングする
     * @param prop_new Properties　追加するプロパティ
     * @param comment コメントに指定する文字（上書きする）
     */
    /*
    public static void saveXMLPropertiesFile(
    Properties prop_new,
    String comment) {
    
    Properties prop_old = getInstance().getProperties();
    
    String key = null;
    Iterator itr = (Iterator) prop_new.propertyNames();
    while (itr.hasNext()) {
    key = (String) itr.next();
    prop_old.setProperty(key, prop_new.getProperty(key));
    }
    
    try {
    OutputStream stream = new FileOutputStream(PROPERTY_FILE_NAME);
    prop_old.storeToXML(stream, comment, ENCODING);
    stream.close();
    } catch (IOException ex) {
    ex.printStackTrace();
    }
    }
     */
    /**
     * キーと要素が対になったプロパティリストに要素を追加する
     * keyが同じプロパティは上書きする
     * @param prop_new Properties　追加するプロパティ
     * @param comment コメントに指定する文字（上書きする）
     */
    public static void savePlainPropertiesFile(
            Properties prop_new,
            String comment) {

        Properties prop_old = getInstance().getProperties();

        String key = null;
        Iterator itr = (Iterator) prop_new.propertyNames();
        while (itr.hasNext()) {
            key = (String) itr.next();
            prop_old.setProperty(key, prop_new.getProperty(key));
        }

        try {
//			OutputStream stream = new FileOutputStream(PROPERTY_FILE_NAME);
            //バッチ処理用に修正
            OutputStream stream = new FileOutputStream(PropertyUtility.class.getResource(PROPERTY_FILE_NAME).getFile());

            prop_old.store(stream, comment);
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}