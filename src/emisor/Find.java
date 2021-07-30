/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emisor;

import Certificado.Export;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Qualidade
 */
public class Find {

    public static void salvarTxt(String[] dados) {
        FileWriter escrever = null;
        File arquivo = null;
        try {
            arquivo = new File("C:\\Emisor\\data\\" + dados[0] + ".cert");

            escrever = new FileWriter(arquivo);

            for (String a : dados) {
                escrever.write(a + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                escrever.close();
                Export.copyFile(arquivo, new File("E:\\data\\" + dados[0] + ".cert"));
            } catch (IOException ex) {
                Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public static File procurarTxt(String nome) {
        return new File("C:\\Emisor\\data\\" + nome + ".cert");
    }

}
