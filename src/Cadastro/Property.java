package Cadastro;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Qualidade
 */
public class Property implements Runnable {

    File file;
    InputStreamReader isr;
    FileInputStream fis;
    BufferedReader buffer;

    @Override
    public void run() {
        JFileChooser escolha = new JFileChooser();
        if (escolha.showOpenDialog(escolha) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        System.out.println(escolha.getSelectedFile().toString());
        file = escolha.getSelectedFile();
        //abrir();
        abrirXML();
    }

    private void abrir() {
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            buffer = new BufferedReader(isr);
            String linha = null;

            while ((linha = buffer.readLine()) != null) {
                Salvar arquivo = new Salvar(linha.split(";")[0]);
                arquivo.criar();
                arquivo.setPropriedade("Codigo", linha.split(";")[0]);
                arquivo.setPropriedade("Descricao", linha.split(";")[1]);

                arquivo.salvarTXT();
                System.out.println(linha.split(";")[0]);
            }

        } catch (Exception e) {

        } finally {
            try {
                buffer.close();
                isr.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void abrirXML() {
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            buffer = new BufferedReader(isr);

            String linha = null;

            while ((linha = buffer.readLine()) != null) {

                if (linha.startsWith("<tr>")) {
                    continue;
                }
                //15
                if (linha.startsWith("<td ID=\"WVCOL\">")) {
                    if (linha.endsWith("</td>")) {
                        String codigo = linha.replaceAll("<td ID=\"WVCOL\">", "");
                        codigo = codigo.replaceAll("</td>", "");
                        codigo = codigo.replaceAll(" ", "");
                        String descricao = buffer.readLine();
                        descricao = descricao.replaceAll("&nbsp;", "");
                        descricao = descricao.replaceAll("<td ID=\"WVCOL\">", "");
                        descricao = descricao.replaceAll("</td>", "");
                        buffer.readLine();
                        String unidade = buffer.readLine();
                        unidade = unidade.replaceAll("&nbsp;", "");
                        unidade = unidade.replaceAll("<td ID=\"WVCOL\">", "");
                        unidade = unidade.replaceAll("</td>", "");
                        Salvar arquivo = new Salvar(codigo);
                        arquivo.criar();
                        arquivo.setPropriedade("Descricao", descricao);
                        arquivo.setPropriedade("Codigo", codigo);
                        arquivo.setPropriedade("Unidade", unidade);
                        arquivo.setPropriedade("Norma", "");
                        arquivo.setPropriedade("Certificado", "");
                        
                        for (int i = 1; i < 15; i++) {
                            arquivo.setPropriedade("q" + i, "");
                            arquivo.setPropriedade("v" + i, "");
                        }
                        for (int i = 1; i < 8; i++) {
                            arquivo.setPropriedade("f" + i, "");
                            arquivo.setPropriedade("uf" + i, "");
                            arquivo.setPropriedade("vf" + i, "");
                        }
                        arquivo.salvarTXT();
                        System.out.println(codigo + " Descricao: " + descricao + " Unidade: " + unidade);
                    }
                }

            }

        } catch (Exception e) {

        }
    }

}
