/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;
import Model.Coord;
import Model.Navire;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
        
/**
 *
 * @author charl
 */
public abstract class Joueur {
    
    private Map<Coord, Boolean>  m_attaque;
    private ArrayList<Coord> m_defense;
    private ArrayList<Navire> m_navires;
    
    public Joueur(){
        m_attaque = new HashMap<>();
        m_defense = new ArrayList<>();
        m_navires = new ArrayList<>();
        
    }
    
    public void initNavires(ArrayList<Navire> navires){
        
        m_navires = navires;
    }
    
    public void addPoint(Coord coord,int choix, Boolean bool){
        if(choix == 0)
            m_attaque.put(coord, bool);
        else
            m_defense.add(coord);
    }
    
    public Map<Coord, Boolean> getAttaque (){
        return m_attaque;
    }
    
    public ArrayList<Coord> getDefense(){
        return m_defense;
    }
    
    public ArrayList<Navire> getNavire(){
        return m_navires;
    }
    
    public void addAttaque(Map<Coord, Boolean> attaque){
        
        attaque.keySet().forEach((coord) -> {
            m_attaque.put(coord, attaque.get(coord));
        });
                   
    }
    
    public abstract void tourJeu();
    
    public String typeNavire(Coord coord){
        
        int c_x = coord.getX();
        int c_y = coord.getY();
        
        for(Navire elem : m_navires){
             
             int n_x = elem.getCoord().getX();
             int n_y = elem.getCoord().getY();
                     
             if(elem.getHonrizontal()){
                 for(int i = 0; i < elem.getTaille();++i)
                     if(n_x+i == c_x && n_y == c_y)
                        return elem.getNom();
             }
                   
             else{
                 for(int i = 0; i < elem.getTaille();++i)
                     if(n_y+i == c_y && n_x == c_x)
                        return elem.getNom();
             }   
        }
        
        return "RIEN";
    }
    
     public boolean deplacer(Coord bateau, Coord deplace){
         
         int navire = -1;
         int compt = 0;
         boolean occupe = false;
         
         int b_x = bateau.getX();
         int b_y = bateau.getY();
         
         int d_x = deplace.getX();
         int d_y = deplace.getY();
        
         
         for(Navire elem : m_navires){
             
             int n_x = elem.getCoord().getX();
             int n_y = elem.getCoord().getY();
                     
             if(elem.getHonrizontal()){
                 for(int i = 0; i < elem.getTaille();++i){
                     
                     if(n_x+i == b_x && n_y == b_y)
                        navire = compt;
                     
                     if(n_x+i == d_x && n_y == d_y)
                         occupe = true;
                     else
                         occupe = false;
                 }
             }
             else
             {
                 for(int i = 0; i < elem.getTaille();++i){
                     if(n_y+i == b_y && n_x == b_x)
                        navire = compt;
                     
                     if(n_y+i == d_y && n_x == d_x)
                         occupe = true;
                      else
                         occupe = false;
                 }
             }
             compt+=1;
         }
         
         if(m_navires.get(navire).getTouché()){
             return false;
         }
         else if(m_navires.get(navire).getNom() == "Sous-marin"){
                 if(m_navires.get(navire).getCoord().getX() < d_x && m_navires.get(navire).getCoord().getY() == d_y )
                     m_navires.get(navire).addCoord(new Coord(m_navires.get(navire).getCoord().getX()+1, m_navires.get(navire).getCoord().getY() ));
                 else if (m_navires.get(navire).getCoord().getX() > d_x && m_navires.get(navire).getCoord().getY() == d_y )
                     m_navires.get(navire).addCoord(new Coord(m_navires.get(navire).getCoord().getX()-1, m_navires.get(navire).getCoord().getY() ));
             
                 if(m_navires.get(navire).getCoord().getY() < d_y && m_navires.get(navire).getCoord().getX() == d_x)
                     m_navires.get(navire).addCoord(new Coord(m_navires.get(navire).getCoord().getX(), m_navires.get(navire).getCoord().getY()+1 ));
                 else if(m_navires.get(navire).getCoord().getY() > d_y && m_navires.get(navire).getCoord().getX() == d_x)
                     m_navires.get(navire).addCoord(new Coord(m_navires.get(navire).getCoord().getX(), m_navires.get(navire).getCoord().getY()-1 ));
                 
             return true;
         }
         else if(occupe)
             return false;
         else{
             if(m_navires.get(navire).getHonrizontal()){
                 if(m_navires.get(navire).getCoord().getX() < d_x && m_navires.get(navire).getCoord().getY() == d_y )
                     m_navires.get(navire).addCoord(new Coord(m_navires.get(navire).getCoord().getX()+1, m_navires.get(navire).getCoord().getY() ));
                 else if (m_navires.get(navire).getCoord().getX() > d_x && m_navires.get(navire).getCoord().getY() == d_y )
                     m_navires.get(navire).addCoord(new Coord(m_navires.get(navire).getCoord().getX()-1, m_navires.get(navire).getCoord().getY() ));
             }
             else{
                 if(m_navires.get(navire).getCoord().getY() < d_y && m_navires.get(navire).getCoord().getX() == d_x)
                     m_navires.get(navire).addCoord(new Coord(m_navires.get(navire).getCoord().getX(), m_navires.get(navire).getCoord().getY()+1 ));
                 else if(m_navires.get(navire).getCoord().getY() > d_y && m_navires.get(navire).getCoord().getX() == d_x)
                     m_navires.get(navire).addCoord(new Coord(m_navires.get(navire).getCoord().getX(), m_navires.get(navire).getCoord().getY()-1 ));
             }
                        
             return true;
         }
     }
     
     public Map<Coord, Boolean> tirer(ArrayList<Coord> coord){
         
          Map<Coord, Boolean> caseTouche = new HashMap<>();
          
          for(Navire elem : m_navires){
              
              int n_x = elem.getCoord().getX();
              int n_y = elem.getCoord().getY();
             
              if(elem.getHonrizontal()){
                  for(int i = 0; i < elem.getTaille();++i){
                      for(Coord auto : coord){
                           if(auto.getX() == n_x+i && auto.getY() == n_y){
                                 caseTouche.put(auto, true);
                                  m_defense.add(elem.getCoord());
                                 elem.addTouché(true);
                          }
                           else
                               caseTouche.put(auto, false);
                      }
                 }
              }
              else{
                  for(int i = 0; i < elem.getTaille();++i)
                      for(Coord auto : coord){
                           if(auto.getX() == n_x && auto.getY() == n_y+i){
                                 caseTouche.put(auto, true);
                                  m_defense.add(elem.getCoord());
                                 elem.addTouché(true);
                          }
                           else
                               caseTouche.put(auto, false);
                      }
              }
          }
         
         return caseTouche;
     }
}
