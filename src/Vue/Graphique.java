package Vue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * gère les sous-menus de l'interface graphique
 * @author fishe
 */

public class Graphique extends JFrame{
 
    private JTextField données = new JTextField();
    private JButton entrée = new JButton("ok");
    private JButton choix1 = new JButton("commencer la partie");
    private JButton choix2 = new JButton("charger une partie");
    private JButton choix3 = new JButton("aide");
    private JButton choix4 = new JButton("quitter");
    
    
     
    public void Menuaide() throws FileNotFoundException, UnsupportedEncodingException, IOException{

    JOptionPane aide = new JOptionPane(); // création de la boite de dialogue
    File regle = new File("src\\files\\test.txt"); // emplacement du fichier
    ArrayList<String> données = new ArrayList<String>();
    String ligne;

            try{
                FileReader lecture_fichier = new FileReader(regle); // fichier qu'on souhaite lire
                BufferedReader lecture = new BufferedReader(lecture_fichier); // permet de lire le fichier ligne par ligne
                   
                    while((ligne = lecture.readLine()) != null){ // lorsque la ligne n'est pas vide
                        données.add(ligne); // stockage d'une ligne dans un ArrayList
                        données.add("\n");
                        System.out.println(ligne);
                    }
             
                aide.showMessageDialog(null, données , "Règles du jeu", JOptionPane.INFORMATION_MESSAGE); // affichage du contenu dans PopUp
     
                    lecture.close(); // fermeture de la mémoire tampon
  
                // Exception     
                }catch(FileNotFoundException e){ // dans le cas ou le fichier est introuvable
                    System.err.println("le fichier " + regle.toString() + " est introuvable");
                } 
        }  
         
    

 /**
 * gère le popUp pour quitter la partie
 * @author fishe
 */
    public void MenuQuitter(){
    
        JOptionPane quitter = new JOptionPane();

        int dernier_message =  quitter.showConfirmDialog(null, "voulez vous vraiment quitter la partie ?", "arrêt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(dernier_message != JOptionPane.NO_OPTION &&  dernier_message != JOptionPane.CLOSED_OPTION){
            System.exit(0); // quitte si appuie sur OUI
        }
    }
    
    
 /**
 * gère le popUp pour charger la partie
 * @author fishe
 * @return nom
 */
    public String MenuCharger(){
    
        JOptionPane charger = new JOptionPane();

        String nom = charger.showInputDialog(null, "Veuillez saisir la partie que vous souhaiter charger\n"
        + "Sinon tapez 'exit' ", "charger une partie", JOptionPane.QUESTION_MESSAGE); // saisie du message

        return nom;
    
    }

    
 /**
 * gère le popUp quand la partie commence
 * @author fishe
 */
    public void MenuCommencer(){
          
      JOptionPane commence = new JOptionPane();

      commence.showMessageDialog(null, "Bonjour vous disposez de \n "
              + "1 cuirassé\n "
              + "2 croisseurs\n"
              + "3 destroyeurs\n"
              + "4 sous-marins\n"
              +"Vous pouvez jouer contre un humain où un ordinateur\n"
              + "Bonne partie !!", "Commencer", JOptionPane.INFORMATION_MESSAGE);
      
    }
}
