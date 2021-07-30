/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author Qualidade
 */
public class Iniciar {

     private static Buscar buscar = new Buscar();
    
    public static void main(String[] args){
        System.out.println("Iniciando..");
        System.out.println("Procurando em " + Arquivos.PASTATESTE.getPath());
        buscar.buscar(Arquivos.PASTATESTE);
        System.out.println("Terminou");
    }

}
