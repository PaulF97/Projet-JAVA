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
public class SousMarin extends Navire{
    
    public SousMarin(){
        m_taille = 1;
        m_puissanceTir = 1;
    }

    @Override
    void tirer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
