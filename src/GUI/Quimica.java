/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

/**
 *
 * @author Qualidade
 */
public class Quimica {
    
    private String composicao;
    private float valor;
    
    public Quimica(String compos, float valor){
        this.composicao = compos;
        this.valor = valor;
    }
    
    public String getComposicao(){
        return composicao;
    }
    
    public float getValor(){
        return valor;
    }
    
    public void setComposicao(String composicao){
        if(composicao != null)
            this.composicao = composicao;
    }
    public void setValor(float valor){
        this.valor = valor;
    }
}
