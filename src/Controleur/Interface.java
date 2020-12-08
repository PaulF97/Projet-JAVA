/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Model.Coord;
import Model.N_Croiseur;
import Model.N_Cuirasse;
import Model.N_Destroyer;
import Model.J_Humain;
import Model.Joueur;
import Model.Navire;
import Model.J_Ordinateur;
import Model.N_SousMarin;
import Vue.Console;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author charl
 */
public class Interface {
    private ArrayList<Joueur> m_joueurs;
    private boolean m_sauvegarde;
    private boolean m_console;
    

    public Interface(boolean deuxHumain){
        
        m_joueurs = new ArrayList<Joueur>();
        m_joueurs.add(new J_Humain());
        
        if(deuxHumain)
            m_joueurs.add(new J_Humain());
        else
            m_joueurs.add(new J_Ordinateur());
    }
    
        public void jeu(){
            m_sauvegarde = false;
            m_console = true;
    
            création();
            affichage(0);
            
    }

    public void création(){
                
        ArrayList<Navire> one = new ArrayList<>();
        one.add(new N_Cuirasse());
        one.add(new N_Croiseur());
        one.add(new N_Croiseur());
        one.add(new N_Destroyer());
        one.add(new N_Destroyer());
        one.add(new N_Destroyer());
        one.add(new N_SousMarin());
        one.add(new N_SousMarin());
        one.add(new N_SousMarin());
        one.add(new N_SousMarin());
        
        ArrayList<Navire> two = new ArrayList<>();
        two.add(new N_Cuirasse());
        two.add(new N_Croiseur());
        two.add(new N_Croiseur());
        two.add(new N_Destroyer());
        two.add(new N_Destroyer());
        two.add(new N_Destroyer());
        two.add(new N_SousMarin());
        two.add(new N_SousMarin());
        two.add(new N_SousMarin());
        two.add(new N_SousMarin());
         
        if(m_sauvegarde){
            chargement(one,two);
        }
        else{
          
            coordAlea(one);
            coordAlea(two);
            m_joueurs.get(0).initNavires(one);
            m_joueurs.get(1).initNavires(two);
        }
    }
    
    public void coordAlea(ArrayList<Navire> navire){
        
        ArrayList<Coord> coord = new ArrayList<>();
        
        navire.forEach((Navire elem) -> {
        
            boolean existant = false;
            Coord buffer; 
            int direction;
               
                do{
                    buffer = new Coord(intAlea(0,14), intAlea(0,14));
                    direction = intAlea(0,1);
                    existant = false;
                    
                    for(int i =0;i<elem.getTaille();++i){

                        if(buffer.getX()+i > 14 || buffer.getY()+i > 14)
                            existant = true;
                        
                        for(Coord auto : coord){

                            if(direction == 1){
                                if(auto.getX() == buffer.getX()+i && auto.getY() == buffer.getY())
                                    existant = true; 
                            }
                            else{
                                if(auto.getX() == buffer.getX() && auto.getY() == buffer.getY()+i)
                                    existant = true;
                            }
                        }
                    }
                    
                }while(existant);
                
                elem.addCoord(buffer);
                
                if(direction == 0){
                    elem.addHonrizontal(false);
                    for(int i =0;i<elem.getTaille();++i)
                        coord.add(new Coord(buffer.getX(),buffer.getY()+i));
                }
                else{
                    elem.addHonrizontal(true);
                    for(int i =0;i<elem.getTaille();++i)
                        coord.add(new Coord(buffer.getX()+i,buffer.getY()));
                }
        });
        
    }
    
    public int intAlea(int a, int b){
        Random rand = new Random();
        return rand.nextInt(b-a+1)+a;
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
                    tampon.write(Integer.toString(Boolean.compare(navire.getHonrizontal(), false))+"\n");
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
        ArrayList<Integer> honrizon = new ArrayList<>();
        
        for(Joueur joueur : m_joueurs){

            boolean boucleDeux = true;
            int compt = 0;
            
            do{       
                boolean boucleUne = true;
                ArrayList<Coord> buffer_coord = new ArrayList<>();
                
                do{       
                    if(buffer.get(0) != 11111){
                        
                        honrizon.add(buffer.get(0));
                        Coord coord = new Coord(buffer.get(1), buffer.get(2));
                        buffer_coord.add(coord);
                        buffer.remove(0);
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
    
    
    public void affichage(int nbre){
        if(m_console){
            Console console = new Console(m_joueurs);
            console.affichage(nbre);
        }
        else{
            //mode graphique 
        }
    }
    
    
}
