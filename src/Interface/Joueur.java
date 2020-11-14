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
public class Joueur {
    
    private ArrayList<Grille> m_grilles;
    private ArrayList<Navire> m_navires;
    
    public Joueur(){
        m_grilles = new ArrayList<>();
        m_navires = new ArrayList<>();
        
        m_navires.add(new Cuirasse());
        m_navires.add(new Croiseur());
        m_navires.add(new Croiseur());
        m_navires.add(new Destroyer());
        m_navires.add(new Destroyer());
        m_navires.add(new Destroyer());
        m_navires.add(new SousMarin());
        m_navires.add(new SousMarin());
        m_navires.add(new SousMarin());
        m_navires.add(new SousMarin());
        
    }
    
    public void initNavires(Navire navire){
        
        m_navires.add(navire);
        
    }
    
            
}
