/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;

/**
 *
 * @author Qualidade
 */
public abstract class Renomear {

    public File certificado;

    public void setArquivo(File file) {
        certificado = file;
    }

    public void quebrar(String certificado) {
        String[] linhas = certificado.split("\n");
        for (String linha : linhas) {
            if (eValido(linha)) {
                certificado(linha.substring(0, linha.length() - 1));
                //salvarTxt(linha.substring(0, linha.length() - 1), linhas);

            }
        }
    }

    public abstract boolean eValido(String texto);

    //"C:\\Users\\Qualidade\\Desktop\\Renomeados"
    private void certificado(String nome) {
        String a = substituir(nome);
        File novo = new File(Arquivos.BACKUP + a + ".pdf");
         System.out.println(novo.getName());
        mover(novo);
        
    }

    private void salvarTxt(String nome, String[] conteudo) {
        FileWriter arq = null;
        PrintWriter gravarArq = null;
        String a = substituir(nome);
        try {

            arq = new FileWriter("G:\\" + a + ".txt");
            gravarArq = new PrintWriter(arq);

            for (String cont : conteudo) {
                gravarArq.print(cont + "|");
            }

        } catch (IOException ex) {

        } finally {
            if (arq != null) {
                try {
                    arq.close();
                } catch (IOException ex) {

                }
            }
            if (gravarArq != null) {
                gravarArq.close();
            }
        }

    }

    public void copyFile(File source, File destination) throws IOException {
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

    public String substituir(String arg0) {
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

    public void mover(File depois) {

        boolean sucesso = certificado.renameTo(depois);

        if (sucesso) {
             System.out.println("Renomeou");
        } else {
            boolean suc = certificado.delete();
            if (suc) {
                System.out.println("Excluiu");
            } else {
                 System.out.println("Falhou");
            }
        }

    }

}
