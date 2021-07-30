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
public class Arquivos {
    
    public static final String PASTABUSCA = "C:\\Users\\Qualidade\\Desktop\\CertificadosEmail";
    
    public static final String PASTASAIDA = "G:\\CertificadosOriginal\\";
    
    public static final String BACKUP = "C:\\Users\\Qualidade\\Desktop\\Renomeados\\";
    
    public static final String DISCOREMOVIVEL = "E:\\Teste\\";
    
    public static final File PASTABUSCAFILE = new File(PASTABUSCA);
    
    public static final File PASTASAIDAFILE = new File(PASTASAIDA);
    
    public static final File BACKUPFILE = new File(BACKUP);
    
    public static final Arquivos ARQUIVO = new Arquivos();
    
    public static final File PASTATESTE = new File("C:\\Users\\Qualidade\\Desktop\\CertificadosEmail");
    
    public File getLocal(){
        File a = new File(getClass().getResource(" ").getFile());
        return a;
    }
    
}
