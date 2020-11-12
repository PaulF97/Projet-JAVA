/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Navire;

/**
 *
 * @author charl
 */
 abstract class Navire {
    
     protected int m_taille;
     protected int m_puissanceTir;
     protected Coord[] m_coord;
     
     abstract void tirer();
     
     public void deplacer(Coord[] caseOccupe){
         
     }
     
     
    
}
