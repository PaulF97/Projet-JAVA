/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Navire.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author charl
 */
public class Interface extends JFrame{
    
    private ArrayList<Joueur> m_joueurs;
    private boolean m_sauvegarde; 
    private Object panneau;
    
    public Interface(boolean deuxHumain){
        
        m_joueurs = new ArrayList<Joueur>();
        m_joueurs.add(new Humain());
        
        if(deuxHumain)
            m_joueurs.add(new Humain());
        else
            m_joueurs.add(new Ordinateur());
    }
    
    public Interface(){
   
        affichageMenu();
    }
   
    public static void main(String[] args) {
        
        Interface test = new Interface();
        test.setVisible(true);

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
    
    public void menu(){
       
        Object[] options = {"lancer une partie", "charger une partie", "Aide", "Quitter"};
        int choix = JOptionPane.showOptionDialog(null, this, "Bataille Naval", JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, null, options, options[1]);
   
        affichageMenu();
  
        //switch
        switch(choix){
            case 1:
                break;
            case 2:
                break;
            case 3:
  
                aide();
                break;
            case 4:
                
                break;
            case JOptionPane.CLOSED_OPTION:
                return;
        }  
    }
    
    public void affichageMenu(){
        
        boutonValider();
        
    }
    
    public void boutonValider(){
        
        // créations des check box boutons
        JCheckBox choix1 = new JCheckBox("commencer la partie");
        JCheckBox choix2 = new JCheckBox("charger une partie");
        JCheckBox choix3 = new JCheckBox("aide");
        JCheckBox choix4 = new JCheckBox("quitter");
        
        
         // création de la boite de dialogue
        setTitle(" Menu "); // texte d'entrée
        Container Demarrage = this.getContentPane(); // création du container
        Demarrage.setLayout(new GridLayout(0,1)); // dimensionnement des cases
        
        JLabel label = new JLabel(" Bienvenue au jeu de la bataille naval ! ");
        
        setSize(400, 400); // taille du container
        
        Demarrage.add(label);
        
        // affichage des checkboxs
        Demarrage.add(choix1);
        Demarrage.add(choix2);
        Demarrage.add(choix3);
        Demarrage.add(choix4);
        Demarrage.setVisible(true);
        
        
        //choix1.setSelected(true);
        traitement(choix1, choix2, choix3, choix4);
    }
 
   
  
    // traitement du choix
    public void traitement(JCheckBox premier, JCheckBox deuxieme, JCheckBox troisieme, JCheckBox quatrieme){
        
        if(premier.isSelected()){
             System.out.println("test1");
        } else if(deuxieme.isSelected()){
             System.out.println("test2");
        } else if(troisieme.isSelected()){
             System.out.println("test3");
        } else if(quatrieme.isSelected()){
             System.out.println("test4");
        } else{
           // System.out.println("default");
        }
    }
    
    public void aide(){
        //affichage
    }  
    
    public void affichage(){
        //a faire, suite pour tester
        
        for(Joueur joueur : m_joueurs){
            
            System.out.println("\nJ");
              
            for(Navire navire : joueur.getNavire())
                System.out.println("N: "+navire.getCoord().getX()+" "+navire.getCoord().getY());
                
                             
                
             for(Coord coord : joueur.getAttaque())
                System.out.println("A: "+coord.getX()+" "+coord.getY());
                
                                
            for(Coord coord : joueur.getDefense())
                 System.out.println("D: "+coord.getX()+" "+coord.getY());
                
            }
    }

   
}
