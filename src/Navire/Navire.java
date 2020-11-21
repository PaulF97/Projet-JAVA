/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Navire;
import java.util.ArrayList;

/**
 *
 * @author charl
 */
    public abstract class Navire {
    
     protected int m_taille;
     protected int m_puissanceTir;
     protected Coord m_coord;
     protected boolean m_initCoord;
     
     abstract void tirer();
     
     public void deplacer(Coord[] caseOccupe){
         
     }
     
     public void addCoord(Coord coord){
         m_coord = coord;
         m_initCoord = true;
     }
     
    public int getTaille (){
        return m_taille;
    }
    
    public boolean getInitCoord(){
        return m_initCoord;
    }
    
    public Coord getCoord(){
        return m_coord;
    }
}
