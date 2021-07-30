/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLNotas;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Qualidade
 */
public class RelatorioXml {

    public void criar() {
        JFileChooser pasta = new JFileChooser();
        pasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (pasta.showOpenDialog(pasta) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        System.out.println(pasta.getSelectedFile().getAbsolutePath());
        buscar(pasta.getSelectedFile());
    }

    private void abrir() {
        File arquivoHtml = null;
        FileInputStream fis = null;
        try {
            arquivoHtml = new File("C:\\Users\\Qualidade\\Desktop\\fornecedores.txt");
            fis = new FileInputStream(arquivoHtml);
            BufferedReader buffer = new BufferedReader(new FileReader(arquivoHtml));
            String linha = null;
            while ((linha = buffer.readLine()) != null) {
                String caminhoNovo = "C:\\XMLFORNECEDORES\\" + linha;
                if (!new File(caminhoNovo).exists()) {
                    new File(caminhoNovo).mkdir();
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException ex) {
            Logger.getLogger(RelatorioXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(File caminhos) {
        File[] arquivos = caminhos.listFiles();
        if (arquivos == null) {
            return;
        }
        for (File arquivo : arquivos) {
            if (arquivo == null) {
                continue;
            }
            if (arquivo.isDirectory()) {
                buscar(arquivo);
            } else {
                carregar(arquivo);
            }

        }

    }

    private void carregar(File file) {

        SAXBuilder builder = new SAXBuilder();
        Document doc = null;

        Element ele = null;
        try {
            doc = builder.build(file);

            ele = (Element) doc.getRootElement();

            List linhas = ele.getChildren();
            Iterator itera = linhas.iterator();
            Namespace name = Namespace.getNamespace("http://www.portalfiscal.inf.br/nfe");

            while (itera.hasNext()) {
                try {
                    Element linha = (Element) itera.next();

                    //System.out.println(linha.getAttribute("Id"));
                    //System.out.println(linha.getAttribute("versao"));
                    Element valor = linha.getChild("infNFe", name);
                    Element ide = valor.getChild("ide", name);
                    Element emit = valor.getChild("emit", name);
                    String nome = emit.getChildText("xNome", name);

                    String cnpj = "-CNPJ " + emit.getChildText("CNPJ", name);
                    String nNota = ide.getChildText("nNF", name);
                    // if(nNota != null && nome != null){
                    nome = substituir(nome).toUpperCase();
                    cnpj = substituir(cnpj);
                    nNota = substituir(nNota);
                    //termin.append(nNota);
                    criarPasta("C:\\XMLFORNECEDORES\\" + nome + cnpj + "\\" + nNota);
                    copyFile(file, new File("C:\\XMLFORNECEDORES\\" + nome + cnpj + "\\" + nNota + "\\" + nNota + ".xml"));
                    //}
                    System.out.println(file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;

                }

            }

        } catch (Exception e) {
            //e.printStackTrace();

        }
    }

    private void criarPasta(String file) {
        File a = new File(file);
        if (!a.exists()) {
            a.mkdirs();

        }

    }

    private void copyFile(File source, File destination) throws IOException {
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

    private String substituir(String arg0) {
        String arg1 = null;

        //invalidos \ / : * ? "< > |
        arg1 = arg0.replaceAll("/", "-");
        arg1 = arg1.replaceAll(":", "");
        arg1 = arg1.replaceAll("<", "");
        arg1 = arg1.replaceAll(">", "");
        arg1 = arg1.replaceAll("|", "");
        arg1 = arg1.replaceAll("   ", "");
        arg1 = arg1.replace('\"', ' ');
        arg1 = arg1.replace('(', ' ');
        arg1 = arg1.replace(')', ' ');
        arg1 = arg1.replaceAll("\n", "");

        return arg1;
    }

}
