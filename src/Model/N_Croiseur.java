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
public class N_Croiseur extends Navire{
    
    public N_Croiseur(){
        
        super();
        
        m_taille = 5;
        m_puissanceTir = 4;
        m_initCoord = false;
        m_caractere = 'C';
        m_nom = "Croiseur";
    }
}
