package jp.co.nii.sew.business.service;

import java.io.*;
import java.io.OutputStream;
import java.util.ArrayList;
import jp.co.nii.sew.utility.LogGenerate;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * タイトル:PoiXlsAction
 * 説明:POIによるエクセルファイルの作成、ダウンロードを行う
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author  K.kanahara
 */
public class PoiXlsAction {

    /**
     * Arrayリストを元にExcelWorkbookを作成する
     * @param ArrayList[] DataArray
     * @param ArrayList CellWidArray
     * @param String Version
     * @param HttpServletResponse res
     * @throws IOException
     * @throws Exception
     */
    public boolean CreateWorkbook(ArrayList[] DataArray,
            HttpServletResponse res) throws IOException, Exception {

        CellStyle style = null;
        int widArrayStartNum_x = 0;
        int higArrayStartNum_y = 0;
        int widArrayEndNum_x = 0;
        int higArrayEndNum_y = 0;
        int widStartNum = 0;
        int higStartNum = 0;
        
        //★セル幅指定用のArrayList
        ArrayList CellWidArray = new ArrayList();
        CellWidArray.add(2304);//A列
        CellWidArray.add(2304);//B列
        CellWidArray.add(2304);//C列
        CellWidArray.add(2304);//以下、左の列から順番に指定していく
        CellWidArray.add(2304);
        CellWidArray.add(2304);
        
        //★Excelのバージョンを指定する
        String Version = "2007";

        try {

            //★ワークブックファイル作成
            String bookname = "ブック名";
            Workbook wb = CreateBook(Version);

            //★シート作成、シート名設定、シート内セル幅設定
            //セル幅の指定がない場合は空のArrayListを渡してください
            String sheetname = "シート名";
            Sheet sheet = CreateSheet(wb, sheetname, CellWidArray);

            
            
            /*●以下はスタイルを変更するときに複製が必要です●*/
            //スタイル設定
            style = wb.createCellStyle();
            //★スタイル指定は必要に応じて追加。何もない場合は上記１文のみでOK
            style.setBorderTop(CellStyle.BORDER_MEDIUM);
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            style.setBorderLeft(CellStyle.BORDER_MEDIUM);


            //★エクセルへの描画開始位置指定(1から始まる値で指定)
            widStartNum = 1;
            higStartNum = 1;

            //★ArrayListの読み込み開始位置を指定(0から始まる値で指定)
            widArrayStartNum_x = 0;
            higArrayStartNum_y = 0;

            //★ArrayListの読み込み終了位置を指定(0から始まる値で指定、0だと配列があるかぎりになる)
            widArrayEndNum_x = 1;
            higArrayEndNum_y = 1;

            //明細行作成
            CreateCellBlock(sheet, DataArray, widStartNum, higStartNum,
                    widArrayStartNum_x, higArrayStartNum_y,
                    widArrayEndNum_x, higArrayEndNum_y, style);

            /*●複製ここまで●*/

            

            
            
            //OutputStreamを作成しダウンロードさせる。
            //作成するバージョンにより拡張子が異なるため、バージョンは必須。
            DownloadXls(res,wb,bookname,Version);
            
            
        } catch (IOException e) {
            System.out.println(e.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }

        //値を返却
        return true;
        
    }

    /**
     * バージョン別にExcelWorkbookを作成する
     * @param String Version
     * @return Workbook wb
     * @throws Exception
     */
    private Workbook CreateBook(String Version) throws Exception {
        //バージョン判断
        Workbook wb = null;
        try {
            if ("2007".equals(Version)) {
                wb = new XSSFWorkbook();
            } else if ("2003".equals(Version)) {
                wb = new HSSFWorkbook();
            } else {
                LogGenerate.infoOutput("Versionの指定間違い");
                throw new Exception("Versionの指定間違い");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
        return wb;
    }

    /**
     * シートを作成する
     * @param Workbook wb
     * @param String sheetname
     * @param ArrayList CellWidArray
     * @return Sheet sheet
     * @throws Exception
     */
    private Sheet CreateSheet(Workbook wb, String sheetname, ArrayList CellWidArray) throws Exception {

        Sheet sheet = null;

        try {
            //シート作成
            sheet = wb.createSheet(sheetname);

            //シート内のセル幅を指定
            if (!CellWidArray.isEmpty()) {
                for (int i = 0; i < CellWidArray.size(); i++) {
                    sheet.setColumnWidth(i, (Integer) CellWidArray.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }

        return sheet;
    }

    /**
     * ArrayListと位置指定によってセルを作成する
     * @param Sheet sheet
     * @param ArrayList[] DataArray
     * @param widStartNum
     * @param higStartNum
     * @param widArrayStartNum_x
     * @param higArrayStartNum_y
     * @param widArrayEndNum_x
     * @param higArrayEndNum_y
     * @param CellStyle style
     * @throws Exception
     */
    private void CreateCellBlock(Sheet sheet,
            ArrayList[] DataArray,
            int widStartNum,
            int higStartNum,
            int widArrayStartNum_x,
            int higArrayStartNum_y,
            int widArrayEndNum_x,
            int higArrayEndNum_y,
            CellStyle style) throws Exception {

        try {

            Row row = null;
            Cell cell = null;

            //ArrayListの値取得終了値を設定する
            int x_end = widArrayEndNum_x;
            int y_end = higArrayEndNum_y;

            //ArrayListの値取得終了値(行)指定が0だったら、現在のArrayListの最大を終了値とする
            if (higArrayEndNum_y == 0) {
                y_end = DataArray.length;
            }

            //明細行出力
            for (int y = higArrayStartNum_y; y < y_end; y++) {
                //新規行作成
                row = sheet.createRow(y + higStartNum - 1);

                //ArrayListの値取得終了値(列)指定が0だったら、現在のArrayListの最大を終了値とする
                if (widArrayEndNum_x == 0) {
                    x_end = DataArray[y].size();
                }

                for (int x = widArrayStartNum_x; x < x_end; x++) {
                    //新規セル作成
                    cell = row.createCell(x + widStartNum - 1);
                    cell.setCellValue((String) DataArray[y].get(x));

                    //セルに対してスタイル設定
                    cell.setCellStyle(style);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * ExcelWorkbookをダウンロードさせる
     * @param HttpServletResponse res
     * @param Workbook wb
     * @param String fileName
     * @throws IOException
     * @throws Exception
     */
    public void DownloadXls(final HttpServletResponse res,
            Workbook wb,
            String fileName,
            String Version)
            throws IOException, Exception {

        String xlsname = "";

        try {

            if (Version.equals("2003")) {
                xlsname = ".xls\"";
            } else if (Version.equals("2007")) {
                xlsname = ".xlsx\"";
            } else {
            }

            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            res.setHeader("Content-Type", "charset=UTF-8");
            res.setHeader("Content-Disposition",
                    "attachment;filename=\"" + fileName + xlsname);
            res.setContentType("application/vnd.ms-excel");
            OutputStream out = res.getOutputStream();
            wb.write(out);
            out.close();

        } catch (IOException e) {
            System.out.println(e.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }

        //return true;
    }
}
