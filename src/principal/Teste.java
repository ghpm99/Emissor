/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Qualidade
 */
public class Teste {

    public static void main(String[] args) {

     
        //new GUI.Certificado(93859, "AP20PL", "871598", "Sem", "Tock Parafusos", "54", "PC", "Din 934", 0.34f, quimica, fisica, "Sem tratamento", "Nada", "12372198731", "Sem complemento");

    }

    public void nothing() {
        try {
            File file = new File(System.getProperty("user.dir") + "\\config.data");
            Properties pro = new Properties();
            pro.setProperty("numero", "1");
            pro.store(new FileOutputStream(file), "Configuracoes");
        } catch (Exception e) {

        }
    }
}
