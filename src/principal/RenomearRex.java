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
public class RenomearRex extends Renomear {

    @Override
    public boolean eValido(String texto) {
        return texto.startsWith("PF ")
                || texto.startsWith("PORCA ")
                || texto.startsWith("PO ")
                || texto.startsWith("REBITES ")
                || texto.startsWith("REBITE ")
                || texto.startsWith("VERGALHÃO ");
    }

}
