/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Random;

/**
 *
 * @author Qualidade
 */
public class Sobre {

    private static String[] frases = {"\"Even giants think they'll always live forever.\"",
        "\"It seems incredible \n But yet it's the truth\"",
        "\"If you still want me, please forgive me\""};

    public static String getVersao() {
        return "V1.01";
    }

    public static String getAutor() {
        return "Guilherme H.";
    }

    public static String getFrase() {
        Random random = new Random();        
        return frases[random.nextInt(3)];
    }

}
