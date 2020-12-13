/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;






/**
 *
 * @author fishe
 */
public class Musique extends Thread {
    
    Graphique liaison = new Graphique();
    
    public void jouer(){    
        try {
            FileInputStream chemin = new FileInputStream("src\\son\\son1.mp3");
            Player jouer = new Player(chemin);
            jouer.play();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "le fichier n'est pas trouvé");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }
    
 }

    
