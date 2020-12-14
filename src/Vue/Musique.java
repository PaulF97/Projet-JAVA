/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;




/**
 * Classe permet de gérer la musique 
 * Hérite de la classe Thread pour gérer plusieurs taches
 * Auteur : Paul Fisher
 */


public class Musique extends Thread {
    
    Graphique liaison = new Graphique();
 
/**
 * Permet de programmer le lancement d'un son à l'aide d'un thread
 * but : ne pas perturber l'ensemble du programme lors de la musique
 * Auteur : Paul Fisher
 */
    @Override
    public void run(){
         try {
            FileInputStream chemin = new FileInputStream("src\\son\\son1.mp3"); // emplacement du fichier MP3
           // Player jouer = new Player(chemin);
           // jouer.play();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "le fichier n'est pas trouvé");
        //} catch (JavaLayerException ex) { // problème de format
          //  JOptionPane.showMessageDialog(null, "le format n'est pas le bon");
        }
    }

}    

    
