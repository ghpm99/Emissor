/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.File;

/**
 *
 * @author Qualidade
 */
public class Buscar implements Runnable{

    Certificado certificado = new Certificado();
    File file = null;

    public void buscar(File file) {
        this.file = file;

    }

    @Override
    public void run() {
        File[] arquivos = file.listFiles();
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
                certificado.abrir(arquivo);
            }
        }
    }

}
