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
public class N_Cuirasse extends Navire{
    
 /**
 * Initialisation du croiseur
 */
    public N_Cuirasse (){

        
        super();
        
        m_taille = 7;
        m_puissanceTir = 9;
        m_initCoord = false;
        m_caractere = 'I';
         m_nom = "Cuirasse";
    }
}
