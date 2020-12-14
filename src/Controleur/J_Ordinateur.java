package Controleur;

import Model.Coord;
import java.util.Random;
/**
 *
 * @author sav
 */
public class J_Ordinateur extends Joueur{
   
    J_Ordinateur(){
        m_nom = "Ordinateur";
    }
    
    //Recuperer les coordonnees sur lesquels l'IA a tiré puis renvoi true / false en fonction de si un navire est touché
    public boolean Cible_Touche()
    {          
       return m_attaque.containsValue(true);
    }
   
    
    
    //Calcul des coordonnées aleatoire "intelligente" sur lesquelles tirer    
    Coord Case_cible()
    {
        int replay =1;
        int a,b;       
        int nb_tir = m_attaque.size();
        int valeur=1,test;
        
        //On va differencier les cas en fonctions du nombre de tir déjà effectués.
        if (nb_tir<50)
        {
            valeur =1;
        }else if (nb_tir<100&&nb_tir>50) 
        {
            valeur =2;
        }else if (nb_tir<150&&nb_tir>100) 
        {
            valeur =3;
        }else if (nb_tir<200&&nb_tir>150) 
        {
            valeur =4;
        }else if (nb_tir>200) 
        {
            valeur =5;
        }
        
        do{
            
        a = intAlea(0, 14);
        b = intAlea(0, 14); 
        
        Coord coordonne_cible = new Coord(a,b);               
        if(Verification_Cible_deja_touche(coordonne_cible))
        {
            switch (valeur)
            {
                //Si l'IA a tiré moins de 50 fois et que son tir concerne une case sur laquelle elle a deja tiré, alors elle va générer
                //une autre case sur laquelle elle a jamais tiré
                case 1 :
                    replay = 1; 
                break; 
                
                //Si l'IA a tiré moins entre 50 et 100 fois et que son tir concerne une case sur laquelle elle a deja tiré, alors elle
                //a 1/5 chance de tirer sur la case déjà choisie, sinon elle va générer une autre case sur laquelle elle a jamais tiré 
                case 2 :
                    test = intAlea(0,4);
                    if (test == 0)
                    {
                       return coordonne_cible;
                    }
                    else 
                    replay = 1;
                break; 
                
                case 3 :
                    test = intAlea(0,4);
                    if (test == 0)
                    {
                        return coordonne_cible;
                    }
                    else 
                    replay = 1;
                break; 
                
                case 4 :
                    test = intAlea(0,2);
                    if (test == 0)
                    {
                        return coordonne_cible;
                    }
                    else 
                    replay = 1;
                break; 
                
                case 5 :
                     test = intAlea(0,1);
                    if (test == 0)
                    {
                        return coordonne_cible;
                    }
                    else 
                    replay = 1;
                break;               
                default :
                    return coordonne_cible;
                                
            }              
        }else if(!Verification_Cible_deja_touche(coordonne_cible))
        {
            return coordonne_cible;
        }
        }while(replay==1); 
        
        return null;      
    }  

    //Retourne les coordonnées d'une cible adjacente aux coordonnées du tir precédent s'il a touché une cible autre qu'un sous-marin.    
    Coord Case_cible_Precis()
    {        
        int a = 15, b = 15;
        Coord coord_ = new Coord (0,0);
        for (Coord elem : m_attaque.keySet())
        {
            coord_ = elem;
        }        
        do
        {
        int case_alea = intAlea(0,3);       
        switch(case_alea) 
        {
        case 0: a = coord_.getX()+1;
                b = coord_.getY();
        break;
        case 1: 
                a = coord_.getX()-1;
                b = coord_.getY();
        break;                
        case 2: 
                a = coord_.getX();
                b = coord_.getY()+1;
        break;
        case 3: 
                a = coord_.getX();
                b = coord_.getY()-1;
        break;
        default:
                a=15;
                b=15;
        }        
        }while(a>14 || a<0 || b>14 || b<14);     
        
        Coord coordonne_cible = new Coord(a,b);
        return coordonne_cible;
    }  
    
    //Methode pour savoir si une case choisis aleatoirement par l'IA a déjà été ciblé par un tir de l'IA précedemment.
    //En paramètre on a la liste des cases sur lesquels l'IA a déjà tiré ainsi que la case que l'on souhaite vérifier.    
    boolean Verification_Cible_deja_touche(Coord Case_a_verifier)
    {
        int nb_tir = m_attaque.size();
        for(int i=0;i<nb_tir;i++)
        {
            Coord coord_ = new Coord (0,0);
            for (Coord elem : m_attaque.keySet())
            {
                coord_ = elem;
            }        
            
            if (coord_ == Case_a_verifier)
            {
                return true;
            }
        }
        return false;
    }
    
    Coord Choix_bateau_Aleatoire()
    {
        Coord coordonnee_bateau;
        int choix_bateau;
        do{
        choix_bateau = intAlea(0, 9); 
        coordonnee_bateau = m_navires.get(choix_bateau).getCoord();
        m_navires.get(choix_bateau).getVie();
        }while(!m_navires.get(choix_bateau).getVie());

   return coordonnee_bateau;
    }
    
    Coord [] Cases_deplacement()
    {        
        Coord Resultat[] = new Coord[1];        
        int choix_bateau;
        boolean horizontal;
        int a=15,b=15;
        Coord coordonnee_bateau_qui_se_deplace,coordonnee_case_surlaquelle_on_se_deplace;      
        do{
        choix_bateau = intAlea(0, 9); 
        coordonnee_bateau_qui_se_deplace = m_navires.get(choix_bateau).getCoord();        
        horizontal = m_navires.get(choix_bateau).getHonrizontal();        
        }while(!m_navires.get(choix_bateau).getVie() || m_navires.get(choix_bateau).getTouche());
    if (horizontal==true) 
    {
        do{
        int alea = intAlea(0, 1);
        switch (alea)
        {
        case 1 : 
            a = coordonnee_bateau_qui_se_deplace.getX();
            b = coordonnee_bateau_qui_se_deplace.getY()+1;  
        break;
        case 0 : 
            a = coordonnee_bateau_qui_se_deplace.getX();
            b = coordonnee_bateau_qui_se_deplace.getY()-1;  
        break;
        }
        }while(a>14 || a<0 || b>14 || b<14);
    }
    else
    {
        do{
        int alea = intAlea(0, 1);
        switch (alea)
        {
        case 1 : 
            a = coordonnee_bateau_qui_se_deplace.getX()+1;
            b = coordonnee_bateau_qui_se_deplace.getY();  
        break;
        case 0 : 
            a = coordonnee_bateau_qui_se_deplace.getX()-1;
            b = coordonnee_bateau_qui_se_deplace.getY();  
        break;
        }
        }while(a>14 || a<0 || b>14 || b<14);
    }
    coordonnee_case_surlaquelle_on_se_deplace = new Coord(a,b);
    Resultat[0] = coordonnee_bateau_qui_se_deplace;
    Resultat[1] = coordonnee_case_surlaquelle_on_se_deplace;
    return Resultat;
    }
    
     public int intAlea(int a, int b){
        Random rand = new Random();
        return rand.nextInt(b-a+1)+a;
    }
}