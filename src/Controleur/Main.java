

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// sources : stack Overflow, developpez.dev, openClassroom, lix.polytechnique, emi.ac.ma, docle oracle et cours ece
package Controleur;

import Vue.Musique;


/**
 *
 * @author charl
 */
public class Main{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        
        Interface test = new Interface();
        Musique son = new Musique();
        son.start(); // lancement du thread
        test.Container();
        test.setVisible(true);
         
        
    }
}