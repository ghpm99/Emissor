/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cadastro;

import Referencias.Referencias;

import java.io.*;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Qualidade
 */
public class Salvar {

    private String nome = null;
    private File arquivo = null;
    private FileOutputStream fos = null;
    private Properties props;

    public Salvar(String nome) {
        this.nome = nome;
        this.props = new Properties();
        
        
    }

    public void carregar(){
        try {
            if (!Referencias.getPastaCertificados(nome.substring(0, 1)).exists()) {
               return;
            }

            this.arquivo = new File("C:\\Emisor\\PRODUTOS\\" + nome.substring(0, 1) + "\\" + nome + ".txt");

            if (!arquivo.exists()) {
                return;
            } 
            this.props.load(new FileInputStream(arquivo));
           
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
   
    
    public void criar() {

        try {
            if (!Referencias.getPastaCertificados(nome.substring(0, 1)).exists()) {
                Referencias.getPastaCertificados(nome.substring(0, 1)).mkdirs();
            }

            this.arquivo = new File("C:\\Emisor\\PRODUTOS\\" + nome.substring(0, 1) + "\\" + nome + ".txt");

            if (!arquivo.exists()) {
                arquivo.createNewFile();
            } 
            this.fos = new FileOutputStream(arquivo);
           
        } catch (IOException e) {
            e.printStackTrace();
        } 

    }

    public void salvarTXT() {
        try {
            this.props.store(fos, "Certificado do produto " + nome);
        } catch (IOException ex) {
            Logger.getLogger(Salvar.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Salvar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setPropriedade(String nome, String valor){
        this.props.setProperty(nome, valor);
    }
    
    public String getPropriedade(String nome){
        return this.props.getProperty(nome);
    }
    
}
