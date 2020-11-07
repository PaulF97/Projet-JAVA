/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author fishe
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author fishe
 */
public class Grille {

    /**
     * @param args the command line arguments
     */
   
    // attributs
    protected char[][] ecran;
    char coordonnées[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o'};
    protected int hauteur;
    protected int largeur;
    
    
    public Grille(){ // constructeur
        
        hauteur = 15;
        largeur = 16;
        ecran = new char [hauteur][largeur];
       
        
        for(int i = 0; i < hauteur; i++){
            System.out.print(coordonnées[i]);
            System.out.println(" |_ _| ");        
            for(int j = 0; j < largeur; j++ ){
                System.out.print(" |_ _| "); 
            }
        }
    }
    
}
