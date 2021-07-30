/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Certificado;

import GUI.PrincipalGUI;
import java.awt.print.PrinterException;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import org.apache.pdfbox.pdmodel.*;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 *
 * @author Qualidade
 */
public class Export {

    public static void salvar(String[] dados) {
        try {
            PrincipalGUI.progresso(50);
            DocumentTemplateFactory document = new DocumentTemplateFactory();
            DocumentTemplate template = document.getTemplate(new File("C:\\Emisor\\Certificado.odt"));
            Map modelo = new HashMap();

            modelo.put("ncertificado", dados[0]);
            modelo.put("npedido", dados[1]);
            modelo.put("nnota", dados[2]);
            modelo.put("cnpj", dados[3]);
            modelo.put("cliente", dados[4]);
            modelo.put("codproduto", dados[5]);
            modelo.put("descricao", dados[6]);
            modelo.put("quantidade", dados[7]);
            modelo.put("norma", dados[8]);
            modelo.put("noriginal", dados[9]);

            for (int i = 1; i < 15; i++) {
                modelo.put("q" + i, dados[9 + i]);
            }
            for (int i = 1; i < 15; i++) {
                modelo.put("v" + i, dados[23 + i]);
            }
            for (int i = 1; i < 8; i++) {
                modelo.put("f" + i, dados[37 + i]);
            }
            for (int i = 1; i < 8; i++) {
                modelo.put("uf" + i, dados[44 + i]);
            }
            for (int i = 1; i < 8; i++) {
                modelo.put("vf" + i, dados[51 + i]);
            }

            modelo.put("tratamento", dados[59]);
            modelo.put("complemento", dados[60]);
            modelo.put("complementodescricao", dados[61]);
            PrincipalGUI.progresso(85);
            template.createDocument(modelo, new FileOutputStream("C:\\Emisor\\ODT\\" + dados[0] + ".odt"));

            OfficeManager office = null;

            office = new DefaultOfficeManagerConfiguration().setOfficeHome(new File("C:\\Program Files (x86)\\OpenOffice 4")).buildOfficeManager();
            office.start();

            OfficeDocumentConverter converter = new OfficeDocumentConverter(office);

            converter.convert(new File("C:\\Emisor\\ODT\\" + dados[0] + ".odt"), new File("C:\\Emisor\\Certificados\\" + dados[0] + ".pdf"));

            office.stop();

            File pdf = new File("C:\\Emisor\\Certificados\\" + dados[0] + ".pdf");
            File desti = new File("C:\\Users\\Qualidade\\Desktop\\" + dados[0] + ".pdf");
            System.out.println("Copiando...");
            //copyFile(pdf, desti);
            copyFile(pdf, new File("E:\\PDF\\" + dados[0] + ".pdf"));
            salvar(dados[2], dados[6], dados[0]);
            PrincipalGUI.progresso(100);
            //PDDocument pdf = PDDocument.load(new File(System.getProperty("user.dir") + "\\Certificados\\Certificadopdf.pdf"));
            //pdf.print();
            // pdf.close();

        } catch (Exception e) {

        }
    }

    private static void salvar(String nota, String produto, String certificado) throws IOException {
        File certi = new File("C:\\Emisor\\Certificados\\" + certificado + ".pdf");
        File pdf = new File("G:\\Certificados Tock\\" + nota + "\\" + certificado + ".pdf");
        File txt = new File("G:\\Certificados Tock\\" + nota + "\\" + nota + ".txt");
        if (!new File("G:\\Certificados Tock\\" + nota).exists()) {
            new File("G:\\Certificados Tock\\" + nota).mkdir();
        }
        copyFile(certi, pdf);
        addTXT(txt, produto, certificado);
    }

    private static void addTXT(File txt, String produto, String numero) throws IOException {

        if (!txt.exists()) {
            boolean criou = txt.createNewFile();
            if (criou) {
                System.out.println("Criou");
            } else {
                System.out.println("Falhou");
            }
        }

        BufferedWriter buff = new BufferedWriter(new FileWriter(txt, true));

        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy");

        buff.write(numero + "-" + produto + formatarDate.format(data));
        buff.newLine();
        buff.flush();
        buff.close();
    }

    public static void copyFile(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }

        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;

        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }

    public static void imprimir(String certificado) {
        try {
            PDDocument pdf = PDDocument.load(new File("C:\\Emisor\\Certificados\\" + certificado + ".pdf"));

            pdf.print();
            pdf.close();
        } catch (IOException | PrinterException e) {
            e.printStackTrace();
        }
    }
}
