/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Interface.*;

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
 *
 * @author fishe
 */
public class Graphique extends JFrame implements ActionListener{
    
    private JTextField données = new JTextField();
    private JButton entrée = new JButton("ok");
    private JButton choix1 = new JButton("commencer la partie");
    private JButton choix2 = new JButton("charger une partie");
    private JButton choix3 = new JButton("aide");
    private JButton choix4 = new JButton("quitter");
    Interface graphique;

    public Graphique() {
        graphique = new Interface();
    }
    
    
    public void affichageMenu(){
        
        Container();
    }
    
    /**
    * @author Paul
    * but : création du conteneur et des boutons
    * param : rien
    * return : rien
    */
    public void Container(){
        
        // phrase
        JLabel label = new JLabel(" Bienvenue au jeu de la bataille naval ! ");
        
         // création de la boite de dialogue
        setTitle(" Menu "); // texte d'entrée
        Container Demarrage = this.getContentPane(); // création du container
        Demarrage.setLayout(new GridLayout(0,1)); // dimensionnement des cases
     
        setSize(400, 400); // taille du container
      
        // capturer les évènements de chaque boutons
        choix1.addActionListener(this);
        choix2.addActionListener(this);
        choix3.addActionListener(this);
        choix4.addActionListener(this);
        
        // ajout des boutons & informations dans le conteneur
        Demarrage.add(label);
        Demarrage.add(choix1);
        Demarrage.add(choix2);
        Demarrage.add(choix3);
        Demarrage.add(choix4);

    }
    
    /**
    *@author Paul
    * but : création du popUp d'identification
    * param : chaine de caractère
    * return : chaine saisie
    */
    public String utilisateur(String message){
        
        JOptionPane saisie = new JOptionPane(); // création de la boite de dialogue
        
        String nom = saisie.showInputDialog(null, message, " Identification ", JOptionPane.QUESTION_MESSAGE);
        //saisie.showMessageDialog(null, "Vous avez saisie " + nom, null , JOptionPane.INFORMATION_MESSAGE);
        
        return nom;
      
    }
    
    /**
    * @author Paul
    * but : création du PopUp lorsqu'on commence la partie
    * param : rien
    * return : rien
    */
    public void MenuCommence(){
          
      JOptionPane commence = new JOptionPane();

      commence.showMessageDialog(null, "Bonjour vous disposer de \n "
              + "1 cuirassé\n "
              + "2 croisseurs\n"
              + "3 destroyeurs\n"
              + "4 sous-marins\n"
              +"Vous pouvez jouer contre un humain ou un ordinateur\n"
              + "Bonne partie !!", "Commencer", JOptionPane.INFORMATION_MESSAGE);
      
    }
    
    /**
    * @author Paul
    * But : création du popUp permettant de charger une partie
    * param : rien
    * @return : nom de la partie que l'on souhaite charger
    */
    public String MenuCharger(){
          
      JOptionPane charger = new JOptionPane();

      String nom = charger.showInputDialog(null, "Veuillez dire la partie que vous souhaiter charger\n"
              + "Sinon tapez 'exit' ", "charger une partie", JOptionPane.QUESTION_MESSAGE); // saisie du message
        
      return nom;
     
    }
    
    /**
    *@author Paul
    * but : création du popUp de sortie de jeu
    * param : rien
    * return : rien
    */
    public void MenuQuitter(){
          
      JOptionPane quitter = new JOptionPane();

     int dernier_message =  quitter.showConfirmDialog(null, "voulez vous vraiment quitter la partie ?", "arret", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
      
        if(dernier_message != JOptionPane.NO_OPTION && dernier_message != JOptionPane.CANCEL_OPTION && dernier_message != JOptionPane.CLOSED_OPTION){
          System.exit(0); // quitte si appuie sur OUI
        }
    }
    
    /**
    * @author Paul
    * but : méthode exécuter lorsqu'on clique sur un bouton
    * param : ae
    * return : rien
     * @param ae
    */
      @Override // excécution après capture
      public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (ae.getSource() == choix1){ // création de la partie
            graphique.m_sauvegarde = false;
            graphique.jeu();
            graphique.affichage();
            MenuCommence();
            
        }else if (ae.getSource() == choix2){ // chargé une partie
            graphique.m_sauvegarde = true;
            graphique.jeu();
            
            
        }else if (ae.getSource() == choix3){  // afficher les règles du jeu
          
            try { 
                Menuaide();
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } 
           
        }else if (ae.getSource() == choix4){ // quitter
            MenuQuitter();
            
        }else {
            //System.out.println("erreur");
        }
    }

    /**
    * @author Paul
    * but : création du PopUp affichant les règles du jeu
    * param : rien
    * return : rien
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
                } catch(FileNotFoundException e){ // dans le cas ou le fichier est introuvable
                    System.err.println("le fichier " + regle.toString() + " est introuvable");
                } 
    }  
    
}
