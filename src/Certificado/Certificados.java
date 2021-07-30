/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Certificado;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 *
 * @author Qualidade
 */
public class Certificados {

    //certi
    public void criar() throws IOException, DocumentTemplateException {
        DocumentTemplateFactory document = new DocumentTemplateFactory();
        DocumentTemplate template = document.getTemplate(new File("C:\\Emisor\\Certificado.odt"));
        Map modelo = new HashMap();

        modelo.put("ncertificado", "438.781");
        modelo.put("npedido", "871.598");
        modelo.put("nnota", "93.859");
        modelo.put("cnpj", "sem cnpj");
        modelo.put("cliente", "Tock Parafusos");
        modelo.put("codproduto", "AP20PL");
        modelo.put("descricao", "ARRUELA LISA M20");
        modelo.put("quantidade", "1.400");
        modelo.put("norma", "125 A");
        modelo.put("noriginal", "985412");

        for (int i = 1; i <= 14; i++) {
            modelo.put("q" + i, i);
            modelo.put("v" + i, i);
        }
        for (int i = 1; i <= 7; i++) {
            modelo.put("f" + i, i);
            modelo.put("uf" + i, i);
            modelo.put("vf" + i, i);
        }
        modelo.put("complemento", "Modelo");
        modelo.put("complementodescricao", "Teste");
        modelo.put("tratamento","sem");

        template.createDocument(modelo, new FileOutputStream(System.getProperty("user.dir") + "\\Certificados\\Certificado.odt"));

        OfficeManager office = null;
        try {
            office = new DefaultOfficeManagerConfiguration().setOfficeHome(new File("C:\\Program Files (x86)\\OpenOffice 4")).buildOfficeManager();
            office.start();

            OfficeDocumentConverter converter = new OfficeDocumentConverter(office);

            converter.convert(new File(System.getProperty("user.dir") + "\\Certificados\\Certificado.odt"), new File(System.getProperty("user.dir") + "\\Certificados\\Certificadopdf.pdf"));

            PDDocument pdf = PDDocument.load(new File(System.getProperty("user.dir") + "\\Certificados\\Certificadopdf.pdf"));
            pdf.print();
            pdf.close();

        } catch (Exception e) {
            
        } finally {
            if (office != null) {
                office.stop();
            }
        }

    }

}
