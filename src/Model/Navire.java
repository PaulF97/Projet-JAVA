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
     protected int m_toucheNbre;
     
     protected Coord m_coord;
     
     protected char m_caractere;
     protected String m_nom;
     
     protected boolean m_honrizontal;
     protected boolean m_initCoord;
     protected boolean m_touche;    
     protected boolean m_vie;    
     protected boolean m_eclairante;
     
     public Navire(){
         m_touche = false;
         m_vie = true;
         m_toucheNbre = 0;

     }
     

         m_eclairante = false;
     }
     
     public boolean getEclairante(){
        return m_eclairante;
    }
    
    public void addEclairante(boolean bool){
        m_eclairante = bool;
    }
     
     public void addVie(boolean bool){
         m_vie = bool;
     }
     
     public void addCoord(Coord coord){
         m_coord = coord;
         m_initCoord = true;
     }
     
     public void addHonrizontal(boolean honrizontal){
         m_honrizontal = honrizontal;
     }
     

     public void addTouche(boolean touche){
         m_touche = touche;
         

         if(touche && m_toucheNbre != m_taille)
             m_toucheNbre +=1;
     }
     
     public int getToucheNbre(){
         return m_toucheNbre;
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
    
    public boolean getVie(){
        return m_vie;
    }
}