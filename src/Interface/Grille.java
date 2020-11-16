/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Navire.*;
import java.util.ArrayList;

/**
 *
 * @author charl
 */
public class Grille {
    
    private ArrayList<Coord> m_point;
    private ArrayList<Navire> m_navire;
    private boolean m_boolNavire;
    
    public Grille(ArrayList<Navire> navires){
        
        m_point = new ArrayList<Coord>();
        m_navire = new ArrayList<Navire>();
        m_navire = navires;
        m_boolNavire = true;
        
        
    }
    
    public Grille(){
        
        m_point = new ArrayList<Coord>();
        m_boolNavire = false;

    }
    
    public void addPoint(Coord coord){
        m_point.add(coord);
    }
    
    public void affichageConsole(){
        
        if(m_boolNavire){
            //affiche grille avec bateau
        }
        else{
            //affiche grille des dégats 
        }
        
    }
    
}
