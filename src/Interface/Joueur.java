/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import java.util.ArrayList;
import Navire.*;
        
/**
 *
 * @author charl
 */
public abstract class Joueur {
    
    private ArrayList<Coord> m_attaque;
    private ArrayList<Coord> m_defense;
    private ArrayList<Navire> m_navires;
    
    public Joueur(){
        m_attaque = new ArrayList<>();
        m_defense = new ArrayList<>();
        m_navires = new ArrayList<>();
        
    }
    
    public void initNavires(ArrayList<Navire> navires){
        
        m_navires = navires;
    }
    
    public void affichageConsole(){
      
    }
    
    public void addPoint(Coord coord,int choix){
        if(choix == 0)
            m_attaque.add(coord);
        else
            m_defense.add(coord);
    }
    
    public ArrayList<Coord> getAttaque (){
        return m_attaque;
    }
    
    public ArrayList<Coord> getDefense(){
        return m_defense;
    }
    
    public ArrayList<Navire> getNavire(){
        return m_navires;
    }
    
    public abstract void tourJeu();
            
}
