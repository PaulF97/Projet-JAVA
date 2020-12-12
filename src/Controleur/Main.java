
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author charl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        
        Interface test = new Interface();
        test.Container();
        test.setVisible(true);

        test.jeu();
 

    }

}
