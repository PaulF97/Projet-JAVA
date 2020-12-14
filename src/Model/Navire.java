/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
         m_eclairante = false;
     }
     
 /**
 * savoir la fusée a été utilisée
 * @return 
 */
     public boolean getEclairante(){
        return m_eclairante;
    }
    
 /**
 * Modification statue fusée eclairante
 * @param bool
 */
    public void addEclairante(boolean bool){
        m_eclairante = bool;
    }
     
/**
 * Modification vie
 * @param bool
 */
     public void addVie(boolean bool){
         m_vie = bool;
     }
     
 /**
 * Modifier les coordonnées du navire
 * @param coord
 */
     public void addCoord(Coord coord){
         m_coord = coord;
         m_initCoord = true;
     }
     
 /**
 * Position horizontale
 * @param honrizontal
 */
     public void addHonrizontal(boolean honrizontal){
         m_honrizontal = honrizontal;
     }
     
/**
 * Modification attribue m_touche
 * @param touche
 */
     public void addTouche(boolean touche){
         m_touche = touche;
         

         if(touche && m_toucheNbre != m_taille)
             m_toucheNbre +=1;
     }
     
 /**
 * combien de fois le bateau est touché
 * @return 
 */
     public int getToucheNbre(){
         return m_toucheNbre;
     }
     
 /**
 * récupérer taille du bateau
 * @return 
 */
    public int getTaille (){
        return m_taille;
    }
    
 /**
 * initialise coordonnées du bateau
 * @return 
 */
    public boolean getInitCoord(){
        return m_initCoord;
    }
    
 /**
 * retourne les coordonnées du bateau
 * @return 
 */
    public Coord getCoord(){
        return m_coord;
    }
    
 /**
 * Position horizontale
 * @return 
 */
    public boolean getHonrizontal(){
        return m_honrizontal;
    }
    
 /**
 * Savoir le caractère du navire sur la grille
 * @return 
 */
    public char getCarac(){
        return m_caractere;
    }
    
 /**
 * Savoir le nom du bateau
 * @return 
 */
    public String getNom(){
        return m_nom;
    }
    
 /**
 * Savoir si le bateau est touché
 * @return 
 */
    public boolean getTouche(){
        return m_touche;
    }
    
 /**
 * Savoir le nombre de vie du bateau
 * @return 
 */
    public boolean getVie(){
        return m_vie;
    }
}