/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 *
 * @author Qualidade
 */
public class Excel {

    public static void main(String[] args) {
        criar();

    }

    public static void criar() {

        HSSFWorkbook wb = null;
        //HSSFRow row = sheet.createRow(0);
        POIFSFileSystem pois = null;
        //row.createCell((short) 0).setCellValue("Isto é uma String");
        FileInputStream fos = null;
        try {
            fos = new FileInputStream("workbook.xls");
            pois = new POIFSFileSystem(fos);

            wb = new HSSFWorkbook(pois);
            HSSFSheet sheet = wb.getSheet("Um");
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();

                Iterator celulas = row.iterator();
                while (celulas.hasNext()) {
                    HSSFCell celula = (HSSFCell) celulas.next();
                    HSSFRichTextString rts = celula.getRichStringCellValue();
                    System.out.println(rts.getString());
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {

            }
        }

    }

}
