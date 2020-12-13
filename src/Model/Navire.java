/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;

/**
 *
 * @author charl
 */
    public abstract class Navire {
    
     protected int m_taille;
     protected int m_puissanceTir;
     
     protected Coord m_coord;
     
     protected char m_caractere;
     protected String m_nom;
     
     protected boolean m_honrizontal;
     protected boolean m_initCoord;
     protected boolean m_touche;    
     protected boolean m_vie;    
     
     
     public void addCoord(Coord coord){
         m_coord = coord;
         m_initCoord = true;
     }
     
     public void addHonrizontal(boolean honrizontal){
         m_honrizontal = honrizontal;
     }
     

     public void addTouche(boolean touche){
         m_touche = touche;
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
    
    public boolean getHonrizontal(){
        return m_honrizontal;
    }
    
    public char getCarac(){
        return m_caractere;
    }
    
    public String getNom(){
        return m_nom;
    }
    
    public boolean getTouche(){
        return m_touche;
    }
}