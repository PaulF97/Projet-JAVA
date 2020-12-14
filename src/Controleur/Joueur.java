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
    
    protected Map<Coord, Boolean>  m_attaque;
    protected Map<Coord, Character>  m_eclairante;
    protected ArrayList<Coord> m_defense;
    protected ArrayList<Navire> m_navires;
    protected String m_nom;
    protected boolean m_destroit;
    
    public Joueur(){
        m_attaque = new HashMap<>();
        m_defense = new ArrayList<>();
        m_navires = new ArrayList<>();
        m_eclairante = new HashMap<>();
        m_destroit = false;
        
    }
    
    /**
     *
     * @return
     */
    public boolean getDestroit(){
        return m_destroit;
    }
    
    /**
     *
     * @param bool
     */
    public void addDestroit(boolean bool){
        m_destroit = bool;
    }
    
    /**
     *
     * @return
     */
    public String getNom(){
        return m_nom;
    }
    
    /**
     *
     * @return
     */
    public Map<Coord, Character> getEclairante(){
        return m_eclairante;
    }
     
    /**
     *
     * @param map
     */
    public void addEclairante(Map<Coord, Character> map){
         m_eclairante.clear();
         m_eclairante = new HashMap<>();
         m_eclairante = map;
         m_destroit = true;
     }
    
    /**
     *
     * @param nom
     */
    public void addNom(String nom){
        m_nom = nom;
    }
    
    /**
     *
     * @param navires
     */
    public void initNavires(ArrayList<Navire> navires){
        
        m_navires = navires;
    }
    
    /**
     *
     * @param coord
     * @param choix
     * @param bool
     */
    public void addPoint(Coord coord,int choix, Boolean bool){
        if(choix == 0)
            m_attaque.put(coord, bool);
        else
            m_defense.add(coord);
    }
    
    /**
     *
     * @return
     */
    public Map<Coord, Boolean> getAttaque (){
        return m_attaque;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Coord> getDefense(){
        return m_defense;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Navire> getNavire(){
        return m_navires;
    }
    
    /**
     *
     * @param attaque
     */
    public void addAttaque(Map<Coord, Boolean> attaque){
            
        attaque.keySet().forEach((coord) -> {
            if(m_attaque.containsKey(coord)){
                    m_attaque.remove(coord);
                    m_attaque.put(coord, true);
            }
            else
                m_attaque.put(coord, attaque.get(coord));
        });
                   
    }
        
    /**
     *
     * @param coord
     * @return
     */
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
    
    /**
     *
     * @param coord
     * @return
     */
    public boolean navireMort(Coord coord){
        
        int c_x = coord.getX();
        int c_y = coord.getY();
        
        for(Navire elem : m_navires){
             
             int n_x = elem.getCoord().getX();
             int n_y = elem.getCoord().getY();
                     
             if(elem.getHonrizontal()){
                 for(int i = 0; i < elem.getTaille();++i)
                     if(n_x+i == c_x && n_y == c_y && !elem.getVie())
                        return true;
             }
                   
             else{
                 for(int i = 0; i < elem.getTaille();++i)
                     if(n_y+i == c_y && n_x == c_x == !elem.getVie())
                        return true;
             }   
        }
        
        return false;
    }
    
    /**
     *
     * @param bateau
     * @param deplace
     * @return
     */
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
                     
                     occupe = n_x+i == d_x && n_y == d_y;
                 }
             }
             else
             {
                 for(int i = 0; i < elem.getTaille();++i){
                     if(n_y+i == b_y && n_x == b_x)
                        navire = compt;
                     
                     occupe = n_y+i == d_y && n_x == d_x;
                 }
             }
             compt+=1;
         }
         
         if(m_navires.get(navire).getTouche()){
             return false;
         }
         else if("Sous-marin".equals(m_navires.get(navire).getNom())){
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
     
    /**
     *
     * @param coord
     * @param sousmarin
     * @return
     */
    public Map<Coord, Boolean> tirer(ArrayList<Coord> coord, boolean sousmarin){
         
          Map<Coord, Boolean> caseTouche = new HashMap<>();
          
          m_navires.forEach((elem) -> {
              int n_x = elem.getCoord().getX();
              int n_y = elem.getCoord().getY();
             
              if("Sous-marin".equals(elem.getNom())){
                  if(sousmarin){
                      coord.forEach((auto) -> {
                          if(auto.getX() == n_x && auto.getY() == n_y){
                              if(!m_defense.contains(auto)){
                                  
                                  if(caseTouche.containsKey(auto) && !caseTouche.get(auto)){
                                      caseTouche.remove(auto);
                                      caseTouche.put(auto, true);
                                   }
                                  else
                                      m_attaque.put(auto, true);
                                  
                                m_defense.add(auto);
                                elem.addTouche(true);
                              }
                          }
                          else{
                              if(!caseTouche.containsKey(auto))
                                  caseTouche.put(auto, false);
                          }
                      });
                  }
              }
              else if(elem.getHonrizontal()){
                  for(int i = 0; i < elem.getTaille();++i){
                      for(Coord auto : coord){
                          if(auto.getX() == n_x+i && auto.getY() == n_y){
                              if(!m_defense.contains(auto)){
                                if(caseTouche.containsKey(auto) && !caseTouche.get(auto)){
                                      caseTouche.remove(auto);
                                      caseTouche.put(auto, true);
                                   }
                                  else
                                      m_attaque.put(auto, true);
                                
                                m_defense.add(auto);
                                elem.addTouche(true);
                              }
                          }
                          else{
                              if(!caseTouche.containsKey(auto))
                                  caseTouche.put(auto, false);
                          }
                      }
                  }
              }
              else{
                  for(int i = 0; i < elem.getTaille();++i)
                      for(Coord auto : coord){
                          if(auto.getX() == n_x && auto.getY() == n_y+i){
                              if(!m_defense.contains(auto)){
                                if(caseTouche.containsKey(auto) && !caseTouche.get(auto)){
                                      caseTouche.remove(auto);
                                      caseTouche.put(auto, true);
                                   }
                                  else
                                      m_attaque.put(auto, true);
                                
                                m_defense.add(auto);
                                elem.addTouche(true);
                              }
                          }
                          else{
                              if(!caseTouche.containsKey(auto))
                                  caseTouche.put(auto, false);
                          }
                      }
              }
              
              
              if(elem.getTaille() == elem.getToucheNbre())
                  elem.addVie(false);
        });
          
         
         return caseTouche;
     }
     
    /**
     *
     * @return
     */
    public boolean perdant(){
         int nbre = 0;
         int sm = 0;
         
         for(Navire elem : m_navires){
             if(!elem.getVie())
                 nbre += 1;
             
             if("Sous-marin".equals(elem.getNom()) && !elem.getVie())
                 sm += 1; 
         }
         
        return nbre == 10 || sm == 4;
     }

    /**
     *
     * @param coord
     * @return
     */
    public Navire navireSelec(Coord coord){
        
        int c_x = coord.getX();
        int c_y = coord.getY();
        
        for(Navire elem : m_navires){
             
             int n_x = elem.getCoord().getX();
             int n_y = elem.getCoord().getY();
                              
            if(n_x == c_x && n_y == c_y)
                return elem;
                       
        }        
        
        return null;
    }

}


