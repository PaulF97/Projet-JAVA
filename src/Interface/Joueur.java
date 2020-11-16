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
    
    private ArrayList<Grille> m_grilles;
    private ArrayList<Navire> m_navires;
    
    public Joueur(){
        m_grilles = new ArrayList<>();
        m_navires = new ArrayList<>();
        
    }
    
    public void initNavires(ArrayList<Navire> navires){
        
        m_navires = navires;
    }
    
    public void initGrille(){
        
        m_grilles.add(new Grille(m_navires));
        m_grilles.add(new Grille());
    }
    
    public void affichageConsole(){
        for(Grille elem : m_grilles){
            elem.affichageConsole();
        }
    }
    
    public abstract void tourJeu();
            
}
