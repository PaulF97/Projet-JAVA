/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Navire.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author charl
 */
public class Interface {
    
    private ArrayList<Joueur> m_joueurs;
    
    public Interface(boolean deuxHumain){
        
        m_joueurs = new ArrayList<Joueur>();
        m_joueurs.add(new Humain());
        
        if(deuxHumain)
            m_joueurs.add(new Humain());
        else
            m_joueurs.add(new Ordinateur());
    }
    
    public static void main(String[] args) {
        
        
    }
    
    public void sauvegarde(){
        
    }
    
    public void chargement(){
        
        
        
    }
    
    public void jeu(boolean sauvegarde){
        
        if(sauvegarde){
            //charger depuis sauvegarde
        }
        else
            création();
        
        //suite
        
    }
    
    public void création(){
        
        ArrayList<Coord> buffer = new ArrayList<Coord>();
        
        ArrayList<Navire> one = new ArrayList<Navire>();
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
        
        ArrayList<Navire> two = new ArrayList<Navire>();
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
         
        coordAlea(buffer,one);
        coordAlea(buffer,two);
        
        m_joueurs.get(0).initNavires(one);
        m_joueurs.get(1).initNavires(two);
        
        
    }
    
    public void coordAlea(ArrayList<Coord> coord, ArrayList<Navire> navire){
        
        navire.forEach((elem) -> {
        
            ArrayList<Coord> buffer_coord = new ArrayList<Coord>();
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
                
                elem.addCoord(buffer_coord);
           
           
            for(Coord c : buffer_coord)
                coord.add(c);
        });
        
    }
    
    public int intAlea(int a, int b){
        Random rand = new Random();
        return rand.nextInt(b-a+1)+a;
    }
    
    public void menu(){
        
        //switch 
         
    }
    
    public void affichageMenu(){
      
    }
    
    public void aide(){
        //affichage
    }    
}
