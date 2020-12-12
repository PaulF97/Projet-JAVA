package Vue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * gère les sous-menus de l'interface graphique
 * Classe appartient à la partie Vue du pattern MVC
 * @author fishe
 */

public class Graphique extends JFrame{
 
     /**
 * Affiche règle du jeu à partir d'un .txt.
 * Auteur : Paul Fisher 
 * @param question_id  
 * @return non utilisateur choisit 
 */
    public String utilisateur(String question_id){
        
        JOptionPane saisie = new JOptionPane(); // création de la boite de dialogue
       
        String nom = saisie.showInputDialog(null, question_id, " Options ", JOptionPane.QUESTION_MESSAGE);
        //saisie.showMessageDialog(null, "Vous avez saisie " + nom, null , JOptionPane.INFORMATION_MESSAGE);
        
        if(nom == null){
            saisie.showMessageDialog(null,"Vous avez quittez le jeu ! ", "Identification", JOptionPane.CLOSED_OPTION);
          System.exit(0);
        }
        
        saisie.showMessageDialog(null, " Bienvenue " + nom, "Identification", JOptionPane.INFORMATION_MESSAGE);
        
        return nom;
    }
 
    
 /**
 * PopUp indiquant début de partie
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
 * Boite de dialogue pour sauvegarder en cours de jeu
 * @author fishe 
 * @return nom de la partie  
 */
    public String MenuSauvegarde(){
    
        JOptionPane sauvegarde = new JOptionPane();

        String nom_partie = sauvegarde.showInputDialog(null, " Saisir le nom de la partie ", "Sauvegarder", JOptionPane.QUESTION_MESSAGE); // saisie du message

        return nom_partie;
    }
    
 /**
 * Affiche pop si défault de sauvegarde
 * @author fishe 
 */
     public void DefaultSauvegarde(){
        
        JOptionPane sauvegarde = new JOptionPane();
        
        sauvegarde.showMessageDialog(null, " Vous ne pouvez sauvegarder que si une partie est en cours", "Sauvegarder", JOptionPane.WARNING_MESSAGE);
    }
    
     
 /**
 * Affiche règle du jeu à partir d'un .txt
 * @author fishe 
 * @throws java.io.FileNotFoundException 
 * @throws java.io.UnsupportedEncodingException 
 */
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
 * @return true si quitter false si reste 
 */
    public boolean MenuQuitter(){
    
        JOptionPane quitter = new JOptionPane();
        boolean etat = false;
        int dernier_message =  quitter.showConfirmDialog(null, "voulez vous vraiment quitter la partie ?", "arrêt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(dernier_message != JOptionPane.NO_OPTION &&  dernier_message != JOptionPane.CLOSED_OPTION){
            etat = true;
           // System.exit(0); // quitte si appuie sur OUI
        }
        
        return etat;
    }
    
    
 
  

    
}

   

