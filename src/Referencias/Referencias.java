/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Referencias;

import java.io.File;
/**
 *
 * @author Qualidade
 */
public class Referencias {
    /**
     * Classe que guarda as referencias para economia de memoria ram
     * 
     */
    
    
    public static final File PASTACERTIFICADOS = new File("C:\\Emisor\\PRODUTOS\\");
    
    public static File getPastaCertificados(String inicial){
        return new File("C:\\Emisor\\PRODUTOS\\" + inicial + "\\");
    }
    
}
