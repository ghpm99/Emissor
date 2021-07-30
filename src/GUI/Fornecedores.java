/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Qualidade
 */
public class Fornecedores {

    InputStream is = null;
    BufferedReader bis = null;
    InputStreamReader isr = null;

    private String[] listaFornecedores;

    public void getFornecedores() {
        System.out.print(".");
        try {

            is = new FileInputStream(new File("C:\\Emisor\\Fornecedores.txt"));
            isr = new InputStreamReader(is);
            bis = new BufferedReader(isr);

            String linha = null;
            ArrayList<String> listaTemp = new ArrayList<String>();
            System.out.print(".");
            while ((linha = bis.readLine()) != null) {
                listaTemp.add(linha);

            }
            listaFornecedores = new String[listaTemp.size()];
            for (int i = 0; i < listaTemp.size(); i++) {
                listaFornecedores[i] = listaTemp.get(i);
            }
            System.out.print(".");
            for (String a : listaFornecedores) {
                PrincipalGUI.Fornecedores.addItem(a.split(";")[0]);
            }
        } catch (FileNotFoundException ex) {
            //System.out.println(("C:\\Emisor\\Fornecedores.txt"));
            ex.printStackTrace();
            System.out.println("Erro");
        } catch (IOException ex) {
            Logger.getLogger(Fornecedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (bis != null) {
                    bis.close();
                }
                System.out.print(".");
            } catch (IOException ex) {
                Logger.getLogger(Fornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public String getCNPJ(int index) {
        return listaFornecedores[index].split(";")[1];
    }

    //PrincipalGUI.Fornecedores.addItem(linha.split(";")[0]);
}
