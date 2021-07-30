/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author Qualidade
 */
public class Certificado {

    Renomear rex, belenus, geral;

    public void abrir(File file) {
        rex = new RenomearRex();
        belenus = new RenomearBelenus();
        geral = new RenomearGeral();
        PDDocument pdfDoc = null;
        String text = null;
        PDFTextStripper stripper = null;
        try {
            pdfDoc = PDDocument.load(file);

            stripper = new PDFTextStripper();

            text = stripper.getText(pdfDoc);

            separar(text, file);

        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                if (pdfDoc != null) {
                    pdfDoc.close();
                }
            } catch (IOException ex) {
                
               System.out.println(ex.getMessage());
            }
        }
    }

    public void separar(String certificado, File file) {
        if ((certificado.indexOf("Belenus")) != -1) {
            belenus.setArquivo(file);
            belenus.quebrar(certificado);
        } else if ((certificado.indexOf("Industrial Rex Ltda")) != -1) {
            rex.setArquivo(file);
            rex.quebrar(certificado);
        } else {
            geral.setArquivo(file);
            geral.quebrar(certificado);
        }
    }

}
