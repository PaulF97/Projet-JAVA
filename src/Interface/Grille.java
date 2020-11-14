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
    
    public Grille(ArrayList<Navire> navires){
        
        m_point = new ArrayList<Coord>();
        
        navires.forEach((elem) -> {
            elem.getCoord().forEach((buffer) -> {
                m_point.add(buffer);
            });
        });
    }
    
    public Grille(){
        
        m_point = new ArrayList<Coord>();

    }
    
}
