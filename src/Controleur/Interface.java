/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;



import Model.Coord;
import Model.N_Croiseur;
import Model.N_Cuirasse;
import Model.N_Destroyer;
import Model.Navire;
import Model.N_SousMarin;
import Vue.Console;
import Vue.Graphique;
import Vue.Musique;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author charl
 */
public class Interface extends JFrame implements ActionListener{
    
    private ArrayList<Joueur> m_joueurs;
    private boolean m_sauvegarde;
    private boolean m_config_joueur;
    private boolean m_console;
    private boolean m_partie;
    private boolean m_quitter;
    private boolean m_sauvegardeQuitter;
    private String id;
    private String id1;
    private String id2;
    private boolean m_deuxHumain;
    
    Graphique graph = new Graphique();
    Musique son = new Musique();


    

    private JButton choix1 = new JButton("commencer la partie");
    private JButton choix2 = new JButton("charger une partie");
    private JButton choix3 = new JButton("Sauvegarder");
    private JButton choix4 = new JButton("aide");
    private JButton choix5 = new JButton("Quitters");

    

    public Interface(){
        
        m_sauvegardeQuitter = false;
        m_quitter = false;
        m_partie = false;
        m_joueurs = new ArrayList<Joueur>();
        m_config_joueur = false;
        id = null;
        id1 = null;
        id2 = null;
        /*        id = graph.utilisateur("Comment tu t'appelles ?");
        System.out.println(id);*/
       
    }
    
    public void jeu(){
            
        m_console = true;
        creation();

        int j1 = 0;
        int j2 = 1;
        boolean gagnant = false;
        
        do{
            
            for(Joueur elem : m_joueurs){

                if(j1 == 0 || (m_deuxHumain && j1 == 1))
                    affichage(j1);

                    choix(j1,j2);

                if(j1 == 0 || (m_deuxHumain && j1 == 1))
                    affichage(j1);
                    j1+=1;
                    j2-=1;

                    if(elem.perdant()) // J1 gagne
                        gagnant = true;

                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

        }while(!gagnant);
        

        m_joueurs.get(j1);
        graph.PopUpGagne(m_joueurs);
 
    }
    
    public void choix(int j1, int j2){
        
        String choix;
        J_Humain joueur = new J_Humain();

        if(m_joueurs.get(j1).getClass() == joueur.getClass()){
            
            choix = graph.choixAction();
        
            switch(choix){
                
                case "tirer":
                    
                        String bateauSelectionne;
                        String caseTirer;
                        bateauSelectionne = graph.SelectionBateau();
                        caseTirer = graph.PopUpTirer();
                        tirer(j1,j2, caseStringToCoord(bateauSelectionne), caseStringToCoord(caseTirer));
                        
                        break;
            
                case "deplacer":
                    
                        String bateau;
                        String deplacement;
                         bateau = graph.SelectionBateau();
                        deplacement = graph.PopUpDeplacer();
                        
                        if(m_joueurs.get(j1).deplacer(caseStringToCoord(bateau), caseStringToCoord(deplacement)))
                            JOptionPane.showMessageDialog(null, "déplacement ok");

                        else
                            JOptionPane.showMessageDialog(null, "déplacement nok");
                        
                        break;
            }
        
        }
        else{
            //Ordi
        }
    }
    
     public void tirer(int joueur1, int joueur2, Coord bateauSelectionne, Coord caseTirer){
        
        
        Map<Coord, Boolean> casetouche = new HashMap<>();
           
        if(!m_joueurs.get(joueur1).navireMort(bateauSelectionne)){
            switch(m_joueurs.get(joueur1).typeNavire(bateauSelectionne)){//navire selectionné

            case "Sous-marin" :
                casetouche = m_joueurs.get(joueur2).tirer(coordTouche(caseTirer, 1), true);
                m_joueurs.get(joueur1).addAttaque(casetouche);
            break;
            case "Destroyer" :
                casetouche = m_joueurs.get(joueur2).tirer(coordTouche(caseTirer, 1), false);
                m_joueurs.get(joueur1).addAttaque(casetouche);
            break;
            case "Croiseur" :
                casetouche = m_joueurs.get(joueur2).tirer(coordTouche(caseTirer, 4), false);
                m_joueurs.get(joueur1).addAttaque(casetouche);
            break;
            case "Cuirasse" :
                casetouche = m_joueurs.get(joueur2).tirer(coordTouche(caseTirer, 9), false);
                m_joueurs.get(joueur1).addAttaque(casetouche);
            break;
            default :
            //pas de bateau selectionné
            }
        }

    }
     
    public ArrayList<Coord> coordTouche(Coord tire, int degat){
         ArrayList<Coord> coord = new ArrayList<>();
         
         switch(degat){
        
            case 1 :
                    coord.add(tire);
            break;
            case 4 :
                    coord.add(tire);
                    coord.add(new Coord(tire.getX()-1, tire.getY()));
                    coord.add(new Coord(tire.getX()+1, tire.getY()));
                    coord.add(new Coord(tire.getX(), tire.getY()+1));
                    coord.add(new Coord(tire.getX(), tire.getY()-1));
            break;
            case 9 :
                    coord.add(tire);
                    coord.add(new Coord(tire.getX()-1, tire.getY()));
                    coord.add(new Coord(tire.getX()+1, tire.getY()));
                    coord.add(new Coord(tire.getX(), tire.getY()+1));
                    coord.add(new Coord(tire.getX(), tire.getY()-1));
                    coord.add(new Coord(tire.getX()-1, tire.getY()+1));
                    coord.add(new Coord(tire.getX()+1, tire.getY()+1));
                    coord.add(new Coord(tire.getX()-1, tire.getY()-1));
                    coord.add(new Coord(tire.getX()+1, tire.getY()-1));
            break;
           
         }
         return coord;
     }

       
    public void addJoueur(){
        
        m_joueurs.clear();
        m_joueurs.add(new J_Humain());



        if(m_deuxHumain)

            m_joueurs.add(new J_Humain());
        else
            m_joueurs.add(new J_Ordinateur());
    }
    
    public Coord caseStringToCoord(String coordNom){
        int y = coordNom.charAt(0)-97;
        int x;
        
        if(y < 0)
            y+=32;
        
        if(coordNom.length() == 2)
             x = Character.getNumericValue(coordNom.charAt(1));
        else{
            x = Character.getNumericValue(coordNom.charAt(1))*10;
            x += Character.getNumericValue(coordNom.charAt(2))-1;
        }
        
        return new Coord(x-1,y);
    }

    public void creation(){
                
        ArrayList<Navire> one = new ArrayList<>();
        one.add(new N_Cuirasse());
        one.add(new N_Croiseur());
        one.add(new N_Croiseur());
        one.add(new N_Destroyer());
        one.add(new N_Destroyer());
        one.add(new N_Destroyer());
        one.add(new N_SousMarin());
        one.add(new N_SousMarin());
        one.add(new N_SousMarin());
        one.add(new N_SousMarin());
        
        ArrayList<Navire> two = new ArrayList<>();
        two.add(new N_Cuirasse());
        two.add(new N_Croiseur());
        two.add(new N_Croiseur());
        two.add(new N_Destroyer());
        two.add(new N_Destroyer());
        two.add(new N_Destroyer());
        two.add(new N_SousMarin());
        two.add(new N_SousMarin());
        two.add(new N_SousMarin());
        two.add(new N_SousMarin());
        
        addJoueur();
         
        if(m_sauvegarde){
            chargement(one,two);
        }
        else{
          
            coordAlea(one);
            coordAlea(two);
            m_joueurs.get(0).initNavires(one);
            m_joueurs.get(1).initNavires(two);
        }
    }
    
    public void coordAlea(ArrayList<Navire> navire){
        
        ArrayList<Coord> coord = new ArrayList<>();
        
        navire.forEach((Navire elem) -> {
        
            boolean existant = false;
            Coord buffer; 
            int direction;
               
                do{
                    buffer = new Coord(intAlea(0,14), intAlea(0,14));
                    direction = intAlea(0,1);
                    existant = false;
                    
                    for(int i =0;i<elem.getTaille();++i){

                        if(buffer.getX()+i > 14 || buffer.getY()+i > 14)
                            existant = true;
                        
                        for(Coord auto : coord){

                            if(direction == 1){
                                if(auto.getX() == buffer.getX()+i && auto.getY() == buffer.getY())
                                    existant = true; 
                            }
                            else{
                                if(auto.getX() == buffer.getX() && auto.getY() == buffer.getY()+i)
                                    existant = true;
                            }
                        }
                    }
                    
                }while(existant);
                
                elem.addCoord(buffer);
                
                if(direction == 0){
                    elem.addHonrizontal(false);
                    for(int i =0;i<elem.getTaille();++i)
                        coord.add(new Coord(buffer.getX(),buffer.getY()+i));
                }
                else{
                    elem.addHonrizontal(true);
                    for(int i =0;i<elem.getTaille();++i)
                        coord.add(new Coord(buffer.getX()+i,buffer.getY()));
                }
        });
        
    }
    
    public int intAlea(int a, int b){
        Random rand = new Random();
        return rand.nextInt(b-a+1)+a;
    }
    
     public void sauvegarde(){
        
        FileWriter monFichier = null;
        BufferedWriter tampon = null;
        String nom;
        
        nom = graph.MenuSauvegarde();
        
        try {
            monFichier = new FileWriter(nom);
            tampon = new BufferedWriter(monFichier);

            for(Joueur joueur : m_joueurs){
                for(Navire navire : joueur.getNavire()){
                    tampon.write(Integer.toString(Boolean.compare(navire.getHonrizontal(), false))+"\n");
                    tampon.write(Integer.toString(navire.getCoord().getX())+"\n");
                    tampon.write(Integer.toString(navire.getCoord().getY())+"\n");
                }
                
                tampon.write(Integer.toString(11111)+"\n");
                
                for(Coord coord : joueur.getAttaque().keySet()){
                    tampon.write(Integer.toString(coord.getX())+"\n");
                    tampon.write(Integer.toString(coord.getY())+"\n");
                    if(joueur.getAttaque().get(coord))
                         tampon.write("1\n");
                    else
                        tampon.write("0\n");
                }
                
                tampon.write(Integer.toString(11111)+"\n");
                
                for(Coord coord : joueur.getDefense()){
                    tampon.write(Integer.toString(coord.getX())+"\n");
                    tampon.write(Integer.toString(coord.getY())+"\n");
                }
                
                tampon.write(Integer.toString(11111)+"\n");
                tampon.write(Integer.toString(22222)+"\n");
            }
   
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
          try {
            tampon.flush(); 
            tampon.close();
            monFichier.close();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
    }
     
    public boolean chargement(ArrayList<Navire> one, ArrayList<Navire> two){
        
        ArrayList<Integer> buffer = new ArrayList<>();
        File unFichier = null;
        String nom;
        
        do{

            nom = graph.MenuCharger();
            unFichier = new File(nom);
            
            if("exit".equals(nom))
                break;
            
        }while(!unFichier.exists());

        if(!"exit".equals(nom)){

                FileReader monFichier = null;
                BufferedReader tampon = null;

                try {
                    monFichier = new FileReader(nom);
                    tampon = new BufferedReader(monFichier);

                    while (true) {
                      String ligne = tampon.readLine();
                      
                      if (ligne == null)
                        break;
                      
                      buffer.add(Integer.valueOf(ligne));
                    } 
                } catch (IOException exception) {
                    exception.printStackTrace();
                } finally {
                  try {
                    tampon.close();
                    monFichier.close();
                  } catch(IOException exception1) {
                      exception1.printStackTrace();
                  }
                }
             
            remplirAttributs(buffer,one, two);
            
            return true;
        }
        else
            return false;
      
    }
    
    public void remplirAttributs(ArrayList<Integer> buffer, ArrayList<Navire> one, ArrayList<Navire> two){
        
        boolean numero = true;
        
        for(Joueur joueur : m_joueurs){

            boolean boucleDeux = true;
            int compt = 0;
            
            do{       
                boolean boucleUne = true;
                ArrayList<Coord> buffer_coord = new ArrayList<>();
                ArrayList<Boolean> honrizon = new ArrayList<>();
                ArrayList<Boolean> buffer_attaque_boolean = new ArrayList<>();
                
                do{       
                    if(buffer.get(0) != 11111){
                        
                        if(compt == 0){
                            
                            if(buffer.get(0) == 1)
                                honrizon.add(true);
                            else
                                honrizon.add(false);
                            
                        }
                        
                        
                        Coord coord = new Coord(buffer.get(1), buffer.get(2));
                        buffer_coord.add(coord);
                        
                        if(compt != 0){
                            
                            if(buffer.get(3) == 1)
                                buffer_attaque_boolean.add(true);
                            else
                                buffer_attaque_boolean.add(false);
                            
                            buffer.remove(0);
                        }
                        
                        buffer.remove(0);
                        buffer.remove(0);
                        buffer.remove(0);
                    }
                    else
                        boucleUne = false;
                        
                }while(boucleUne);
                
                buffer.remove(0);
                                
                switch (compt) {
                        case 0:
                            if(numero){
                                for(Navire navire : one){
                                    navire.addCoord(buffer_coord.get(0));
                                    navire.addHonrizontal(honrizon.get(0));
                                    honrizon.remove(0);
                                    buffer_coord.remove(0);
                                }
                            
                                 joueur.initNavires(one);
                                 numero = false;
                            }
                            else{
                                for(Navire navire : two){
                                    navire.addCoord(buffer_coord.get(0));
                                    navire.addHonrizontal(honrizon.get(0));
                                    honrizon.remove(0);
                                    buffer_coord.remove(0);
                                }
                            
                                 joueur.initNavires(two);
                            }
                            break;
                        default:
                            for(int i = 0;i< buffer_coord.size();++i)
                                joueur.addPoint(buffer_coord.get(i), compt-1, buffer_attaque_boolean.get(i) );
                            break;
                }
                
                compt +=1;
                
                if(buffer.get(0) == 22222)
                    boucleDeux = false;

            }while(boucleDeux);
            buffer.remove(0);
        }
    }
    
    public void Container(){
        
        // phrase
        JLabel label = new JLabel(" Bienvenue au jeu de la bataille naval ! ");
        
         // création de la boite de dialogue
        setTitle(" Menu "); // texte d'entrée
        Container Container = this.getContentPane(); // création du container
        Container.setLayout(new GridLayout(0,1)); // dimensionnement des cases

        setSize(400, 400); // taille du container
      
        // capturer les évènements de chaque boutons
        choix1.addActionListener(this);
        choix2.addActionListener(this);
        choix3.addActionListener(this);
        choix4.addActionListener(this);
        choix5.addActionListener(this);

        
        choix1.setBackground(Color.BLUE);
        choix2.setBackground(Color.WHITE);
        choix3.setBackground(Color.RED);
        choix4.setBackground(Color.GREEN);
        choix5.setBackground(Color.MAGENTA);

        
        // ajout des boutons & informations dans le conteneur
        Container.add(label);
        Container.add(choix1);
        Container.add(choix2);
        Container.add(choix3);
        Container.add(choix4);
        Container.add(choix5);

        
    }
       
    @Override // excécution après capture
    public void actionPerformed(ActionEvent ae) {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (ae.getSource() == choix1){ // création de la partie
            m_config_joueur = graph.nombreJoueur();
                if(m_config_joueur){ // un seul joueur
                    id = graph.utilisateur("Comment tu t'appelles ?");
                    JOptionPane.showMessageDialog(null, "Bienvenue parmi nous " + id);
                } else{
                    id1 = graph.utilisateur("Comment tu t'appelles joueur 1? ");
                    id2 = graph.utilisateur("Comment tu t'appelles joueur 2 ");
                    JOptionPane.showMessageDialog(null, "Bienvenue parmi nous " + id1 + " et " +id2);
                }
                
            m_sauvegarde = false;
            m_partie = true;
            graph.MenuCommencer();
            jeu();
            
            
        }else if (ae.getSource() == choix2){ // chargé une partie
            m_sauvegarde = true;
            m_partie = true;
            jeu();
        
        }else if (ae.getSource() == choix3){ // Suvegarder durant la partie
        // lorsque le trosième bouton est sélectionné
            if(m_partie == true){
                sauvegarde();
            } else{
                graph.DefaultSauvegarde();
            }

        }else if (ae.getSource() == choix4){ // aide
            try {
                graph.Menuaide();
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else if(ae.getSource() == choix5){
            m_quitter = graph.MenuQuitter();
           
            if(m_quitter == true){ // sauvegarde si on souhaite quitter
                m_sauvegardeQuitter = graph.SauvegardeQuitter();
                System.out.println(m_sauvegardeQuitter);
                if(m_sauvegardeQuitter){
                    sauvegarde();
                    System.exit(0); // ferme le jeu
                }else {
                    System.exit(0);
               
                }
        }
            
        }else {
            
        }
    }
      
    public void affichage(int joueur){
        if(m_console){
            Console console = new Console(m_joueurs);
            //console.clearScreen();
            console.affichage(joueur);
        }
        else{

            //mode graphique
           Graphique graph = new Graphique();
        }
    }
}
