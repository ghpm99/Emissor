/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cadastro;

/**
 *
 * @author Qualidade
 */
public class Atualizar {
    
    public static void Atualizar(String[] dados){
        
        
        Salvar arquivo = new Salvar(dados[5]);
        arquivo.criar();
        arquivo.setPropriedade("Descricao", dados[6]);
        arquivo.setPropriedade("Unidade", dados[7].split(" ")[1]);
        arquivo.setPropriedade("Norma", dados[8]);
        arquivo.setPropriedade("Certificado", dados[9]);
        
        for (int i = 1; i < 15; i++) {
            arquivo.setPropriedade("q"+i, dados[9+i]);
            
        }
        for (int i = 1; i < 15; i++) {
            arquivo.setPropriedade("v"+i, dados[23+i]);
            //dados[23 + i] = arquivo.getPropriedade("v" + i);
        }
        for (int i = 1; i < 8; i++) {
            arquivo.setPropriedade("f"+i, dados[37+i]);
            //dados[37 + i] = arquivo.getPropriedade("f" + i);
        }
        for (int i = 1; i < 8; i++) {
            arquivo.setPropriedade("uf"+i, dados[44+i]);
            //dados[44 + i] = arquivo.getPropriedade("uf" + i);
        }
        for (int i = 1; i < 8; i++) {
            arquivo.setPropriedade("vf"+i, dados[51+i]);
            //dados[51 + i] = arquivo.getPropriedade("vf" + i);
        }
        arquivo.salvarTXT();
    }
}