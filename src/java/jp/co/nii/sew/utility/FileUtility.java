package jp.co.nii.sew.utility;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.upload.FormFile;

/**
 * <p>タイトル: ファイル関係共通クラス</p>
 * <p>説明: ファイルのアップロードやダウンロードを行う</p>
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム
 */
public class FileUtility {


	/**
     * ディレクトリパス、ファイル名、拡張子から
     * ファイルパスを作成し、返す
     * @param dirPath ディレクトリパス
     * @param fileName ファイル名(拡張子が付いていてもメソッド内で削除する)
     * @param suffix 新しく付ける拡張子 (例：".csv",".jpg")
     * @return ファイルパス
     */
    public static String getFilePath(String dirPath,String fileName,String suffix){
    	//ファイル名から拡張子を取り除く
    	String str = removeFileExtension(fileName);

    	//ファイル名を作成
		String rename = str + suffix;

    	// ファイルがある場所を設定
		String filePath = dirPath  + "/" + rename;

		return filePath;
    }

    /**
     * 拡張子の取得
     * @param filename 対象ファイル
     * @return 拡張子
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

	/**
	 * ファイル名から拡張子を取り除く
	 * @param filename
	 * @return
	 */
	public static String removeFileExtension(String fileName) {
		int lastDotPos = fileName.lastIndexOf('.');

		if (lastDotPos == -1) {
			return fileName;
		} else if (lastDotPos == 0) {
			return fileName;
		} else {
			return fileName.substring(0, lastDotPos);
		}
	}

	/**
	 * 指定したディレクトリ内の全てのファイル名を
	 * String型の配列で返す
	 * @param dirPath ディレクトリパス
	 * @return ファイル名が格納されているString型の配列
	 */
	public static String[] getDirectoryFilesName(String dirPath) {
		File[] files = getDirectoryFiles(dirPath);
		String[] fileNames = new String[files.length];

		for (int i = 0; i < files.length; i++) {
	        fileNames[i] = files[i].getName();
	    }

		return fileNames;
	}

	/**
	 * 指定したディレクトリ内の指定した拡張子のファイル名を
	 * String型の配列で返す
	 * @param dirPath ディレクトリパス
	 * @param suffix 拡張子(例: ".csv",".txt")
	 * @return ファイル名が格納されているString型の配列
	 */
	public static String[] getDirectoryFilesName(String dirPath,String suffix) {
		File[] files = getDirectoryFiles(dirPath,suffix);
		String[] fileNames = new String[files.length];

		for (int i = 0; i < files.length; i++) {
	        fileNames[i] = files[i].getName();
	    }

		return fileNames;
	}

	/**
	 * 指定したディレクトリ内の全てのファイルを
	 * File型の配列で返す
	 * @param dirPath ディレクトリパス
	 * @return ファイルが格納されているFile型の配列
	 */
	public static File[] getDirectoryFiles(String dirPath) {
		File dir = new File(dirPath);

		File[] files = dir.listFiles();

		return files;
	}

	/**
	 * 指定したディレクトリ内の指定した拡張子のファイルを
	 * File型の配列で返す
	 * @param dirPath ディレクトリパス
	 * @param suffix 拡張子(例: ".csv",".txt")
	 * @return ファイルが格納されているFile型の配列
	 */
	public static File[] getDirectoryFiles(String dirPath,String suffix) {
		File dir = new File(dirPath);

		File[] files = dir.listFiles(getFileSuffixFilter(suffix));

		return files;
	}



	/**
	 * 拡張子が一致するファイルを取得するためのフィルター
	 * @param suffix
	 * @return
	 */
	private static FilenameFilter getFileSuffixFilter(String suffix) {
		final String _suffix = suffix;
		return new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean ret = name.endsWith(_suffix);
				return ret;
			}
		};

	}


	/**
     * 拡張子が配列の拡張子と一致するものがあるかのチェック
     * 配列にチェックＯＫな拡張子を入れる
     * @param suffix チェックする拡張子
     * @param fixedSuffix チェックOKの拡張子が格納してある配列
     * @return
     */
    public static boolean checkSuffix(String suffix,String[] fixedSuffix){
    	boolean state = false;

        for (int i = 0; i < fixedSuffix.length; i++) {
            if(fixedSuffix[i].equals(suffix)){
            	state = true;
            	break;
            }
        }
    	return state;
    }

	/**
	 * 指定したファイルパスのファイルをダウンロードする
	 * @param request
	 * @param response
	 * @param filePath ダウンロードしたいファイルのファイルパス
	 * @return ダウンロードに成功したならtrue 失敗ならfalse
	 * @throws Exception
	 */
	public static boolean fileDownload(HttpServletRequest request, HttpServletResponse response,String filePath)
		throws Exception {
		boolean state = false;

		File File = new File(filePath);
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			//ファイルが読み込めたら
			if(File.exists()){
				//HTTPヘッダの出力
				response.setContentType("application/octet-stream");
				response.setHeader("Content-disposition", "attachment; filename=\"" + File.getName() + "\"");
				response.setContentLength((int)File.length());
				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0,pre-check=0");
				response.setHeader("Pragma", "private");

			    in = new BufferedInputStream(new FileInputStream(File));
			    out = new BufferedOutputStream(response.getOutputStream());

			    byte buf[]=new byte[1024];
			    int len;
			    while((len=in.read(buf))!=-1){
			    	out.write(buf,0,len);
			    }

			    state= true;
			}else{
				LogGenerate.debugOutput("ファイルが存在しません。");
				state = false;
			}

		} catch (SocketException e) {
			//●ダウンロード処理中にダウンロードダイアログの「キャンセル」が
			//クリックされた場合の例外。
		} catch (Exception e) {
			//ファイルダウンロード用のHTTPヘッダをリセットします。
			response.reset();
			response.sendError(HttpURLConnection.HTTP_INTERNAL_ERROR , e.toString());
		} finally {
			if (in != null) {
				in.close();
		    }
		    if (out != null) {
		    	out.flush();
		    	out.close();
		    }
		    return state;
		}
	}


	/**
	 * 指定したファイルパスにファイルをアップロードする
	 * @param request
	 * @param fileUp 画面に入力したFormFile型のファイル
	 * @param filePath ファイルをアップロードするパス
	 * @return
	 * @throws Exception
	 */
	public static boolean  fileUpload(FormFile fileUp,String filePath)
		throws Exception {
		boolean state = false;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try{
			//getInputStreamメソッドを使用し、入力ストリームを取得
			InputStream is = fileUp.getInputStream();

			//入力ストリームをバッファリング
			in = new BufferedInputStream(is);


			FileOutputStream fos = new FileOutputStream(filePath);


			//出力ストリームをバッファリング
			out = new BufferedOutputStream(fos);

			int contents = 0;

			//入力データがなくなるまで入出力処理を実行
			while ((contents = in.read()) != -1) {
				out.write(contents);
			}

			state = true;
		}catch (Exception e) {
			throw e;
		}
		finally{
			if (in != null) {
				in.close();
		    }
		    if (out != null) {
		    	out.flush();
		    	out.close();
		    }
		  //一時領域のアップロードデータを削除
			fileUp.destroy();
		}
		return state;
	}

    /**
     * 画像を圧縮して指定したファイルパスに出力する(jpg形式)
     * @param fileUp 画面に入力したFormFile型のファイル
	 * @param filePath ファイルをアップロードするパス
     * @param comp 圧縮率。0～1の間で、1に近いほど圧縮率が低い
     * @return
     * @throws Exception
     */
    public static boolean imageCompressionOutput(FormFile formFile,String filePath,float rate) throws Exception {
    	boolean state = false;
    	ImageWriter writer = null;
    	ImageOutputStream ios = null;
    	try{
	        BufferedImage image = ImageIO.read(formFile.getInputStream());


	        Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
	        if (iter.hasNext()) {
	          writer = (ImageWriter) iter.next();
	        }

	        File file = new File(filePath);
	        //ファイルがすでに存在しても、上書きされない場合があるので削除しておく
	        if(file.exists()){
	        	file.delete();
	        }
	        ios = ImageIO.createImageOutputStream(file);
	        writer.setOutput(ios);
	        JPEGImageWriteParam iwparam
	          = new JPEGImageWriteParam(Locale.getDefault());
	        iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	        //(x.xxf)で圧縮率を設定。
	        //0～1の間で、1に近いほど圧縮率が低い
	        iwparam.setCompressionQuality(rate);

	        writer.write(null,new IIOImage(image,null,null),iwparam);
	        writer.dispose();
	        state = true;

	    }catch (Exception e) {
			throw e;
		}
		finally{
			if (writer != null) {
				writer.dispose();
		    }
		    if (ios != null) {
		    	ios.flush();
		    	ios.close();
		    }
		}
		return state;
    }


	/**
	 * ファイルパスのファイルを削除する
	 * @param filePath ファイルパス
	 * @return ファイルの削除に成功したならtrue  失敗ならfalse
	 * @throws Exception
	 */
	public static boolean  fileDelete(String filePath)
		throws Exception {
		boolean state = false;

		try{
			File File = new File(filePath);

			state =File.delete();

		}catch (Exception e) {
			throw e;
		}
		finally{
			return state;
		}
	}


	 /**
    * ファイルのディレクトリを変更する
    * @param fromFilePath
    * @param toFilePath
    * @return
    */
   public static boolean changeDirectory(String fromFilePath,String toFilePath){

   	File fromFile = new File(fromFilePath);
   	File toFile = new File(toFilePath);

   	//移動元の画像が存在しなかったら
   	if(!fromFile.exists()){
   		return false;
   	}

   	//移動先に同盟のファイルが存在したら削除する
   	if(toFile.exists()){
   		toFile.delete();
   	}
   	boolean state = fromFile.renameTo(toFile);

   	return state;
   }

	/**
	 * 目的のファイルを圧縮し、zipファイルとして一時的に指定したパスへ作成し
	 * その後そのzipファイルをダウンロードする
	 * 一時的に作成したzipファイルは削除する
	 * @param request アクションのリクエスト
	 * @param response アクションのレスポンス
	 * @param targetFiles 圧縮したいファイルのパス配列
	 * @param zipFilePath 作成したzipファイルを一時的に保存する作業ファイルパス
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public static boolean zipFileDownLoad(HttpServletRequest request, HttpServletResponse response,
			String[] targetFiles,String zipFilePath)
	throws IOException, ServletException {

		OutputStream out = null;
		FileInputStream in = null;
		boolean state = false;
		try {
			// ZIPファイルの作成
			createZip(zipFilePath , targetFiles);

			File zipFile = new File(zipFilePath);
			// ファイル名のエンコード
			String zipFileName = new String(zipFile.getName().getBytes("Shift_JIS"), "ISO-8859-1");

			// ZIPファイルのダウンロード
			//HTTPヘッダの出力
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=\"" + zipFileName + "\"");
			response.setContentLength((int)zipFile.length());
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0,pre-check=0");
			response.setHeader("Pragma", "private");


			out = response.getOutputStream();
			in = new FileInputStream(zipFilePath);
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			state =true;
		} catch (IOException e) {

		} finally {

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
				}
			}
		}
		// 作業用ファイルを削除
		try {
			(new File(zipFilePath)).delete();
		} catch (Exception e){
		}
		return state;
	}

	/**
	 * targetFilesをZIP圧縮してzipFileに出力する。
	 * @param zipFile ZIP出力ファイル名
	 * @param targetFiles 入力ファイル名(ディレクトリ)配列
	 * @throws IOException 入出力例外
	 */
	public static void createZip(String zipFile, String[] targetFiles)
	    throws IOException {

		ZipOutputStream out =
			new ZipOutputStream(new FileOutputStream(zipFile));
		//out.setEncoding("MS932");
		for (int i = 0; i < targetFiles.length; i++) {
			File file = new File(targetFiles[i]);
			int deleteLength = file.getPath().length() - file.getName().length();
			createZip(out, file, deleteLength);
		}
		out.close();
	}

	/**
	 * targetFileをoutのZIPエントリへ出力する。
	 * @param out ZIP出力先
	 * @param targetFile 入力ファイル名(ディレクトリ)
	 * @throws IOException 入出力例外
	 */
	private static void createZip(ZipOutputStream out, File targetFile, int deleteLength)
	    throws IOException {

		if (targetFile.isDirectory()) {
			File[] files = targetFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				createZip(out, files[i], deleteLength);
			}
		} else {
			ZipEntry target = new ZipEntry(getEntryPath(targetFile, deleteLength));
			out.putNextEntry(target);
			byte buf[] = new byte[1024];
			int count;
			BufferedInputStream in =
			    new BufferedInputStream(new FileInputStream(targetFile));
			while ((count = in.read(buf, 0, 1024)) != -1) {
				out.write(buf, 0, count);
			}
			in.close();
			out.closeEntry();
			in = null;
			out = null;
		}
	}

	/**
	 * ZIPエントリパスを返す。
	 * @param file ZIPエントリ対象ファイル
	 * @return ZIPエントリのパス
	*/
	private static String getEntryPath(File file, int deleteLength) {
		return file.getPath().replaceAll("\\\\", "/").substring(deleteLength);
	}
        
    /**
     * ファイルを開き全行を入力<BR>
     * キャラクターセット名は、定数クラスから選択する。
     *
     * @param filePathName
     *            ファイルパス名
     * @param charSetName
     *            キャラクターセット名
     * @return 入力した全行の文字列の配列
     * @throws IOException
     */
    public static ArrayList<String> readLines(String filePathName,
            String charSetName) throws IOException {

        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader br = null;

        try {
            // ファイルをオープン
            br = getBufferedReader(filePathName, charSetName);

            String line = null;

            // ファイルEOFまで繰り返し
            while (true) {

                // 1行を入力
                line = br.readLine();

                if (line == null) { // ファイルEOFの場合
                    // 繰り返し終わり
                    break;
                }

                // 入力行を配列に追加
                lines.add(line);

            }

            return lines;

        } finally {
            if (br != null) {
                try {
                    // ファイルをクローズ
                    br.close();
                } catch (IOException e) {
                    LogGenerate.infoOutput("BufferdReaderのCloseでエラーが発生した。 " + filePathName);
                }
            }
        }

    }        

    /**
     * ファイルの入力ストリームを取得
     *
     * @param filePathName
     *            ファイルパス名
     * @param charsetName
     *            文字コード・セット名
     * @return ファイルの入力ストリーム
     * @throws IOException
     */
    public static BufferedReader getBufferedReader(String filePathName,
            String charsetName) throws IOException {

        File file = new File(filePathName);

        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("ファイルが存在しない。:" + filePathName);
        }

        return new BufferedReader(new InputStreamReader(new FileInputStream(
                file), charsetName));

    }
    
}