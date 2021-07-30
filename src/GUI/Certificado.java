/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Certificado.Export;
import emisor.Find;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Qualidade
 */
public class Certificado {

    private String[] dados;

    public Certificado(String[] dados) {
        this.dados = dados;
    }

    public void setDados(String[] dados){
        this.dados = dados;
    }
    
    public void gerarNumero() {
        try {
            File file = new File("C:\\Emisor\\config.data");
            PrincipalGUI.progresso(25);
            Properties pro = new Properties();

            pro.load(new FileInputStream(file));

            int numero = Integer.parseInt(pro.getProperty("numero")) + 1;

            System.out.println(numero);

            this.dados[0] = String.valueOf(numero);

            pro.setProperty("numero", dados[0]);

            pro.store(new FileOutputStream(file), "Configuracoes");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar() {
        PrincipalGUI.progresso(45);
        Find.salvarTxt(dados);
        PrincipalGUI.progresso(85);
        Export.salvar(dados);
        
    }

}
