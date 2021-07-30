/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Certificado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Qualidade
 */
public class Teste {

    public static void main() throws IOException {
        BufferedReader buff = null;
        BufferedWriter buffWriter = null;
        File[] datas;
        File saida;

        datas = new File("C:\\Emisor\\data\\").listFiles();

        for (File a : datas) {
            try {
                buff = new BufferedReader(new FileReader(a));
                buffWriter = new BufferedWriter(new FileWriter(new File("C:\\Emisor\\cert\\" + a.getName())));
                String linha = null;
                for (int i = 0; i < 6; i++) {
                    buffWriter.write(buff.readLine() + "\n");
                }
                buff.readLine();
                buffWriter.write(buff.readLine() + "\n");
                for (int i = 0; i < 51; i++) {
                    buff.readLine();
                }
                buffWriter.write(buff.readLine() + "\n");
                buffWriter.write(buff.readLine() + "\n");
                buffWriter.write(buff.readLine() + "\n");
            //buffWriter.write(buff.readLine() + "\n");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (buff != null) {
                    buff.close();
                }
                if (buffWriter != null) {
                    buffWriter.flush();
                    buffWriter.close();
                }

            }
        }

    }

    public static void main(String[] args){
        for (int i = 1; i < 15; i++) {
                System.out.println("modelo.put(\"q" + i +"\", Certificado.getProduto().getQ"+i+"());");
            }
            for (int i = 1; i < 15; i++) {
                System.out.println("modelo.put(\"v" + i +"\", Certificado.getProduto().getV"+i+"());");
            }
            for (int i = 1; i < 8; i++) {
                System.out.println("modelo.put(\"f" + i +"\", Certificado.getProduto().getF"+i+"());");
            }
            for (int i = 1; i < 8; i++) {
                System.out.println("modelo.put(\"uf" + i +"\", Certificado.getProduto().getUF"+i+"());");
            }
            for (int i = 1; i < 8; i++) {
                System.out.println("modelo.put(\"vf" + i +"\", Certificado.getProduto().getVF"+i+"());");
            }
    }
    
}
