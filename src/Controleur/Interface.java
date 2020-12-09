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
import Model.J_Humain;
import Model.Joueur;
import Model.Navire;
import Model.J_Ordinateur;
import Model.N_SousMarin;
import Vue.Console;
import Vue.Graphique;
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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author charl
 */
public class Interface extends JFrame implements ActionListener{
    
    private ArrayList<Joueur> m_joueurs;
    private boolean m_sauvegarde;
    private boolean m_console;
    Graphique graph = new Graphique();
    private String id;
    private boolean m_deuxHumain;

    
    private JTextField données = new JTextField();
    private JButton entrée = new JButton("ok");
    private JButton choix1 = new JButton("commencer la partie");
    private JButton choix2 = new JButton("charger une partie");
    private JButton choix3 = new JButton("aide");
    private JButton choix4 = new JButton("quitter");

    public Interface(){
        
        m_joueurs = new ArrayList<Joueur>();
        id = graph.utilisateur("Comment tu t'appelles ?");
        System.out.println(id);
    }
    
    public void jeu(){
            
         m_console = true;
         /*      m_deuxHumain = true;
         m_sauvegarde = true;*/
            
           création();
           /*    //affichage(0);
           
           sauvegarde();
           
           deplacer(0);
           
           affichage(0);*/
    }
       
    public void addJoueur(){
        
        m_joueurs.clear();
        m_joueurs.add(new J_Humain());

        
         
        if(m_deuxHumain)

            m_joueurs.add(new J_Humain());
        else
            m_joueurs.add(new J_Ordinateur());
    }

      
    public void deplacer(int joueur){
        //popup deplacer
        
        Coord bateau = caseStringToCoord("K10");
        Coord deplace = caseStringToCoord("k13");
        
        int navire = 0;
        boolean plusCoord = true;
        int compt = 0;
        
        for(Navire auto : m_joueurs.get(joueur).getNavire()){
            for(int i = 0; i<auto.getTaille();++i){
                if(auto.getHonrizontal()){
                    if(auto.getCoord().getX()+i == bateau.getX()){
                        navire = compt;
                        if(deplace.getX() > auto.getCoord().getX())
                            plusCoord = true;
                        else
                            plusCoord = false;
                    }
                    
                }
                else{
                    if(auto.getCoord().getY()+i == bateau.getY()){
                        navire = compt;
                        if(deplace.getY() > auto.getCoord().getY())
                            plusCoord = true;
                        else
                            plusCoord = false;
                    }
                }     
            }

            
            compt +=1;
        }
        
        
        if(navireHonrizon(joueur, navire)){
            
             if(plusCoord)
                  m_joueurs.get(joueur).getNavire().get(navire).addCoord(new Coord(getCoordNavire(joueur,navire,0)+1, getCoordNavire(joueur,navire,1)));
             else
                  m_joueurs.get(joueur).getNavire().get(navire).addCoord(new Coord(getCoordNavire(joueur,navire,0)-1, getCoordNavire(joueur,navire,1)));
            
        }
        else{
            
             if(plusCoord)
                  m_joueurs.get(joueur).getNavire().get(navire).addCoord(new Coord(getCoordNavire(joueur,navire,0), getCoordNavire(joueur,navire,1)+1));
             else
                  m_joueurs.get(joueur).getNavire().get(navire).addCoord(new Coord(getCoordNavire(joueur,navire,0), getCoordNavire(joueur,navire,1)-1));
             
        }   
    }
    
    public boolean navireHonrizon(int joueur, int navire){
        return m_joueurs.get(joueur).getNavire().get(navire).getHonrizontal();
    }
    
    public int getCoordNavire(int joueur, int navire, int coordChoix){
        if(coordChoix == 0)
            return m_joueurs.get(joueur).getNavire().get(navire).getCoord().getX();
        else
            return m_joueurs.get(joueur).getNavire().get(navire).getCoord().getY();
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
        
        return new Coord(x,y);
    }
    

    public void création(){
                
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
        
        System.out.println("Veuillez saisir le nom de la partie à jouer : ");
        Scanner scanner = new Scanner(System.in);
        nom = scanner.nextLine();
        
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
                
                for(Coord coord : joueur.getAttaque()){
                    tampon.write(Integer.toString(coord.getX())+"\n");
                    tampon.write(Integer.toString(coord.getY())+"\n");
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
        ArrayList<Integer> honrizon = new ArrayList<>();
        
        for(Joueur joueur : m_joueurs){

            boolean boucleDeux = true;
            int compt = 0;
            
            do{       
                boolean boucleUne = true;
                ArrayList<Coord> buffer_coord = new ArrayList<>();
                
                do{       
                    if(buffer.get(0) != 11111){
                        
                        honrizon.add(buffer.get(0));
                        Coord coord = new Coord(buffer.get(1), buffer.get(2));
                        buffer_coord.add(coord);
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
                                    buffer_coord.remove(0);
                                }
                            
                                 joueur.initNavires(one);
                                 numero = false;
                            }
                            else{
                                for(Navire navire : two){
                                    navire.addCoord(buffer_coord.get(0));
                                    buffer_coord.remove(0);
                                }
                            
                                 joueur.initNavires(two);
                            }
                            break;
                        default:
                            for(Coord i : buffer_coord)
                                joueur.addPoint(i, compt-1);
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
        Container Demarrage = this.getContentPane(); // création du container
        Demarrage.setLayout(new GridLayout(0,1)); // dimensionnement des cases
     
        setSize(400, 400); // taille du container
      
        // capturer les évènements de chaque boutons
        choix1.addActionListener(this);
        choix2.addActionListener(this);
        choix3.addActionListener(this);
        choix4.addActionListener(this);
        
        // ajout des boutons & informations dans le conteneur
        Demarrage.add(label);
        Demarrage.add(choix1);
        Demarrage.add(choix2);
        Demarrage.add(choix3);
        Demarrage.add(choix4);
 
    }
       
    @Override // excécution après capture
    public void actionPerformed(ActionEvent ae) {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (ae.getSource() == choix1){ // création de la partie
        m_sauvegarde = false;
        jeu();
        affichage(0);
        graph.MenuCommencer();

        }else if (ae.getSource() == choix2){ // chargé une partie
        m_sauvegarde = true;
        jeu();
        
        
        }else if (ae.getSource() == choix3){ // afficher les règles du jeu
        // lorsque le trosième bouton est sélectionné
            try {
                graph.Menuaide();
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if (ae.getSource() == choix4){ // quitter
            sauvegarde();    
            graph.MenuQuitter();
        
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
