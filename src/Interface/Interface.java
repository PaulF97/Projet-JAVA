/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Navire.*;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level; 
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author charl
 */
public class Interface extends JFrame implements ActionListener{
    
    private ArrayList<Joueur> m_joueurs;
    private boolean m_sauvegarde = false;
    private Object panneau;
    
    private JTextField données = new JTextField();
    private JButton entrée = new JButton("ok");
    private JButton choix1 = new JButton("commencer la partie");
    private JButton choix2 = new JButton("charger une partie");
    private JButton choix3 = new JButton("aide");
    private JButton choix4 = new JButton("quitter");
    

    public Interface(boolean deuxHumain){
        
        m_joueurs = new ArrayList<Joueur>();
        m_joueurs.add(new Humain());
        
        if(deuxHumain)
            m_joueurs.add(new Humain());
        else
            m_joueurs.add(new Ordinateur());
    }
    
   
   
    public static void main(String[] args) {
        
        Interface test = new Interface(false);
        Interface test1 = new Interface(false);
        
        test.affichageMenu();
        test.setVisible(true);
        System.out.println(test1.utilisateur("Comment tu t'appelles ?"));

    }
    
    public void sauvegarde(){
        
        FileWriter monFichier = null;
        BufferedWriter tampon = null;
        String nom;
        
        System.out.println("Veuillez saisir le nom de la partie à jouer : ");
        Scanner scanner = new Scanner(System.in);
        nom = scanner.nextLine();

        try {
            monFichier = new FileWriter(nom);
            tampon = new BufferedWriter(monFichier);

            for(Joueur joueur : m_joueurs){
                for(Navire navire : joueur.getNavire()){
                    tampon.write(Integer.toString(navire.getCoord().getX())+"\n");
                    tampon.write(Integer.toString(navire.getCoord().getY())+"\n");
                }
                
                tampon.write(Integer.toString(11111)+"\n");
                
                for(Coord coord : joueur.getAttaque()){
                    tampon.write(Integer.toString(coord.getX())+"\n");
                    tampon.write(Integer.toString(coord.getY())+"\n");
                }
                
                tampon.write(Integer.toString(11111)+"\n");
                
                for(Coord coord : joueur.getDefense()){
                    tampon.write(Integer.toString(coord.getX())+"\n");
                    tampon.write(Integer.toString(coord.getY())+"\n");
                }
                
                tampon.write(Integer.toString(11111)+"\n");
                tampon.write(Integer.toString(22222)+"\n");
            }
   
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
          try {
            tampon.flush(); 
            tampon.close();
            monFichier.close();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
    }
    
    public boolean chargement(ArrayList<Navire> one, ArrayList<Navire> two){
        
        ArrayList<Integer> buffer = new ArrayList<>();
        File unFichier = null;
        String nom;
        
        do{
            System.out.println("Veuillez saisir le nom de la partie à jouer : \nSi vous ne voulez plus charger une partie, taper 'exit'.");
            Scanner scanner = new Scanner(System.in);
            nom = scanner.nextLine();
            unFichier = new File(nom);
            
            if("exit".equals(nom))
                break;
            
        }while(!unFichier.exists());

        if(!"exit".equals(nom)){

                FileReader monFichier = null;
                BufferedReader tampon = null;

                try {
                    monFichier = new FileReader(nom);
                    tampon = new BufferedReader(monFichier);

                    while (true) {
                      String ligne = tampon.readLine();
                      
                      if (ligne == null)
                        break;
                      
                      buffer.add(Integer.valueOf(ligne));
                    } 
                } catch (IOException exception) {
                    exception.printStackTrace();
                } finally {
                  try {
                    tampon.close();
                    monFichier.close();
                  } catch(IOException exception1) {
                      exception1.printStackTrace();
                  }
                }
             
            remplirAttributs(buffer,one, two);
            
            return true;
        }
        else
            return false;
      
    }
    
    public void remplirAttributs(ArrayList<Integer> buffer, ArrayList<Navire> one, ArrayList<Navire> two){
        
        boolean numero = true;
        
        for(Joueur joueur : m_joueurs){

            boolean boucleDeux = true;
            int compt = 0;
            
            do{       
                boolean boucleUne = true;
                ArrayList<Coord> buffer_coord = new ArrayList<>();
                
                do{       
                    if(buffer.get(0) != 11111){
                        Coord coord = new Coord(buffer.get(0), buffer.get(1));
                        buffer_coord.add(coord);
                        buffer.remove(0);
                        buffer.remove(0);
                    }
                    else
                        boucleUne = false;
                        
                }while(boucleUne);
                
                buffer.remove(0);
                                
                switch (compt) {
                        case 0:
                            if(numero){
                                for(Navire navire : one){
                                    navire.addCoord(buffer_coord.get(0));
                                    buffer_coord.remove(0);
                                }
                            
                                 joueur.initNavires(one);
                                 numero = false;
                            }
                            else{
                                for(Navire navire : two){
                                    navire.addCoord(buffer_coord.get(0));
                                    buffer_coord.remove(0);
                                }
                            
                                 joueur.initNavires(two);
                            }
                            break;
                        default:
                            for(Coord i : buffer_coord)
                                joueur.addPoint(i, compt-1);
                            break;
                }
                
                compt +=1;
                
                if(buffer.get(0) == 22222)
                    boucleDeux = false;

            }while(boucleDeux);
            buffer.remove(0);
        }
    }
    
    public void jeu(){
        
        création();
        
        //suite  
    }
    
    public void création(){
        
        ArrayList<Coord> buffer = new ArrayList<>();
        
        ArrayList<Navire> one = new ArrayList<>();
        one.add(new Cuirasse());
        one.add(new Croiseur());
        one.add(new Croiseur());
        one.add(new Destroyer());
        one.add(new Destroyer());
        one.add(new Destroyer());
        one.add(new SousMarin());
        one.add(new SousMarin());
        one.add(new SousMarin());
        one.add(new SousMarin());
        
        ArrayList<Navire> two = new ArrayList<>();
        two.add(new Cuirasse());
        two.add(new Croiseur());
        two.add(new Croiseur());
        two.add(new Destroyer());
        two.add(new Destroyer());
        two.add(new Destroyer());
        two.add(new SousMarin());
        two.add(new SousMarin());
        two.add(new SousMarin());
        two.add(new SousMarin());
         
        if(m_sauvegarde){
            chargement(one,two);
        }
        else{
            coordAlea(buffer,one);
            coordAlea(buffer,two);
            m_joueurs.get(0).initNavires(one);
            m_joueurs.get(1).initNavires(two);
        }
    }
    
    public void coordAlea(ArrayList<Coord> coord, ArrayList<Navire> navire){
        
        navire.forEach((Navire elem) -> {
        
            ArrayList<Coord> buffer_coord = new ArrayList<>();
            boolean existant = false;
            Coord buffer; 
            int direction = intAlea(0,1);
               
                do{
                    buffer = new Coord(intAlea(0,14), intAlea(0,14));
                    existant = false;
                    
                    for(int i =0;i<elem.getTaille();++i){

                        for(Coord auto : coord){

                            if(direction == 0){
                                if(auto.getX() == buffer.getX()+i && auto.getY() == buffer.getY())
                                    existant = true; 
                            }
                            else{
                                if(auto.getX() == buffer.getX() && auto.getY() == buffer.getY()+i)
                                    existant = true;
                            }
                        }
                    }
                    
                    if(!existant){
                        for(int i =0;i<elem.getTaille();++i){
                            if(direction == 0)
                                 buffer_coord.add(new Coord(buffer.getX()+i,buffer.getY()));
                              else
                                buffer_coord.add(new Coord(buffer.getX(),buffer.getY()+i));
                        }
                    }
                }while(existant);
                
                elem.addCoord(buffer);
           
           
            for(Coord c : buffer_coord)
                coord.add(c);
        });
        
    }
    
    public int intAlea(int a, int b){
        Random rand = new Random();
        return rand.nextInt(b-a+1)+a;
    }
    
    public void affichageMenu(){
        
        Container();
        
    }
    
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
    
    public String utilisateur(String message){
        
        JOptionPane saisie = new JOptionPane(); // création de la boite de dialogue
        
        String nom = saisie.showInputDialog(null, message, " Identification ", JOptionPane.QUESTION_MESSAGE);
        //saisie.showMessageDialog(null, "Vous avez saisie " + nom, null , JOptionPane.INFORMATION_MESSAGE);
        
        return nom;
      
    }
    
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
    
    public void MenuCharger(){
          
      JOptionPane charger = new JOptionPane();

      charger.showMessageDialog(null, "Veuillez dire la partie que vous souhaiter charger\n"
              + "Sinon tapez 'exit' ", "charger une partie", JOptionPane.INFORMATION_MESSAGE);
      
    }
    
    public void MenuQuitter(){
          
      JOptionPane quitter = new JOptionPane();

     int dernier_message =  quitter.showConfirmDialog(null, "voulez vous vraiment quitter la partie ?", "arret", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
      
        if(dernier_message != JOptionPane.NO_OPTION && dernier_message != JOptionPane.CANCEL_OPTION && dernier_message != JOptionPane.CLOSED_OPTION){
          System.exit(0); // quitte si appuie sur OUI
        }
    }
    
      @Override // excécution après capture
      public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (ae.getSource() == choix1){ // création de la partie
           // System.out.println("commencer");
            m_sauvegarde = false;
            jeu();
            affichage();
            MenuCommence();
            
        }else if (ae.getSource() == choix2){ // chargé une partie
            //System.out.println("charger");
            m_sauvegarde = true;
            MenuCharger();
            jeu();
           
            
        }else if (ae.getSource() == choix3){ // afficher les règles du jeu
            Menuaide();
            
        }else if (ae.getSource() == choix4){ // quitter
            MenuQuitter();
            this.setResizable(false); // ferme le menu
        }else {
            //System.out.println("erreur");
        }
    }

 
    public void Menuaide(){
       
        JOptionPane aide = new JOptionPane(); // création de la boite de dialogue
        File regle = new File("C:\\Users\\fishe\\OneDrive\\Documents\\ECE - INGE 3\\java\\projet java\\Projet-JAVA\\Projet-JAVA\\src\\files\\test.txt");
        
        /*        // test existance
        if(regle.exists()){
        System.out.println("existe");
        }*/
     aide.showMessageDialog(null, "in progress.. " , "Règles du jeu", JOptionPane.WARNING_MESSAGE); // affichage des règles du jeu

        
    }  
    
    public void affichage()
    {
       //----------------------------------------------------      
    int cote = 15;
    int numero_case =1;
    int numero_case2 =1;
        char[] alphabet = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};
        char[] Bateau_Grille_Joueur1 = new char[227];
        char[] Bateau_Grille_Joueur2 = new char[227];
        for(int j=0;j<227;j++)
        {
            Bateau_Grille_Joueur1[j]=' ';
            Bateau_Grille_Joueur2[j]=' ';
        }
        //Modifier la valeur d'une case : 
        Bateau_Grille_Joueur1[Case('c',2)]='X'; 
        
        //Initialiser les bateaux en début de partie :
        Bateau_Grille_Joueur1=Initialisation_Bateau('j',8,5,true);
        Bateau_Grille_Joueur2=Initialisation_Bateau('c',3,3,false);
        
           
        System.out.println("                                                     GRILLE JOUEUR                                                                                                                 GRILLE ADVERSAIRE");
        System.out.println("      1       2       3       4       5       6       7       8       9      10      11      12      13      14      15      "+" "+"*"+" "+"      1       2       3       4       5       6       7       8       9      10      11      12      13      14      15      ");
        System.out.println("   _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______    *"+" "+"   _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ _______    ");
        for (int i=0;i<cote;i++)
        {
            System.out.println("  |       |       |       |       |       |       |       |       |       |       |       |       |       |       |       |   *"+" "+"  |       |       |       |       |       |       |       |       |       |       |       |       |       |       |       |   ");
            System.out.println(alphabet[i]+" |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   "+Bateau_Grille_Joueur1[numero_case++]+"   |   *"+" "+alphabet[i]+" |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   "+Bateau_Grille_Joueur2[numero_case2++]+"   |   ");
            System.out.println("  |_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|   *"+" "+"  |_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|_______|   ");
        }
    }   
    
    public static int Case(char y,int x)
    {    
    int resultat=0;   
    switch (y) {
            case 'a':
            resultat = x;
            break;
            case 'b' :
            resultat = x+15;
            break;
            case 'c' :
            resultat = x+(15)*2;
            break;
            case 'd' :
            resultat = x+(15)*3;
            break;
            case 'e' :
            resultat = x+(15)*4;
            break;
            case 'f' :
            resultat = x+(15)*5;
            break;
            case 'g' :
            resultat = x+(15)*6;
            break;
            case 'h' :
            resultat = x+(15)*7;
            break;
            case 'i' :
            resultat = x+(15)*8;
            break;
            case 'j' :
            resultat = x+(15)*9;
            break;
            case 'k' :
            resultat = x+(15)*10;
            break;
            case 'l' :
            resultat = x+(15)*11;
            break;
            case 'm' :
            resultat = x+(15)*12;
            break;
            case 'n' :
            resultat = x+(15)*13;
            break;
            case 'o' :
            resultat = x+(15)*14;
            break;
            default :
            resultat = 0;
            break;
        }
    return resultat;
 }
    
     public static char[] Initialisation_Bateau(char y,int x, int Taille_Bateau, boolean Orientation_Bateau)
    {  
        int Position = Case(y,x);
        char[] Bateau_Grille_Joueur = new char[227];
        for(int k=0;k<227;k++)
        {
            
            Bateau_Grille_Joueur[k]=' ';       
        }
        //Cas bateau horizontal :
        if (Orientation_Bateau == true)
        {
            for(int i=0;i<Taille_Bateau;i++)
            {
                Bateau_Grille_Joueur[Position+i]='¦';                
            }

        }
        //Cas bateau vertical : 
        if (Orientation_Bateau==false)
        {
            for(int j=0;j<Taille_Bateau;j++)
            {
                Bateau_Grille_Joueur[Position+(15*j)]='¦';
            }
        }
        return Bateau_Grille_Joueur;
    } 
}
