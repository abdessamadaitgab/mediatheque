
import Model.Dictionnaire;
import Model.Etudiant;
import Model.Kindle;
import Model.Livre;
import Model.Magazine;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class media_thread implements Runnable{
        Socket ClientSocket;

         List l=new ArrayList();
         final int xmin=100;
         final int xmax=1000;
         final int ymin=200;
         final int ymax=700;
         
    public media_thread(Socket soc){
    this.ClientSocket=soc;    
    }

    @Override
    public void run() {

        BufferedReader input = null;
            try {
                
                          mediatheque m = new mediatheque();
                          m.cd.CRUD_Client("root","");
                          m.dd.CRUD_Document("root","");
                          m.dk.CRUD_Kindle("root", "");

                
                PrintWriter output = new PrintWriter(ClientSocket.getOutputStream(),true);
                input = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
                ObjectOutputStream output_objet = new ObjectOutputStream(ClientSocket.getOutputStream());
                Etudiant e = null;
                Kindle k=null;
                boolean repeat=true;
                String login =null;
                String mdp = null;
                //la boucle mére
                while(repeat){
                    //Connexion
                    boolean connected = false;
                    boolean boolkindle=false;
                    while(!connected){
                        login = input.readLine();
                        mdp = input.readLine();
                        
                        boolean cnx1 = m.cd.Connexion(login, mdp);
                      
                       e=m.cd.getEtudiant_By_Login_Password(login, mdp);
                       // k=m.dk.KindleEnCours(e.getCni());
                         if(cnx1)
                         { System.out.println("connexion établie");
                         
                            String fileName = "C:\\Users\\HP\\Mediatheque_Serveur\\src\\main\\java\\File.txt";
                            Scanner scan = new Scanner(new File(fileName));
                         while(scan.hasNextInt()){
                           int line = scan.nextInt();
                           l.add(line);
                           
                         }                          //  System.out.println(l);
                         /*for(int i=0;i<l.size();i++){
                             if (l.get(i).equals(45)){
                             
                                 cnx1=false;
                             }
                                 
                         }*/
                         Random random = new Random();
                         int nb1;
                         nb1 = random.nextInt(l.size());
                         int value1=(int) l.get(nb1);
                         int nb2;
                         nb2 = random.nextInt(l.size());
                         int value2=(int) l.get(nb2);
                         System.out.println("X -> "+value1);
                         System.out.println("Y -> "+value2);

                         
                         if (value1 < 100 || value1>1000 || value2<200 || value2 >700  ){
                             
                             
                                 cnx1=false;
                                 System.out.println("vous etes en dehors du campus");
                                 l=new ArrayList();
                             }
                         else{
                             cnx1=true;
                             l=new ArrayList();
                         }
                         
                         
                         }
                         else { System.out.println("connexion échouée");}
                         
                         /*boolean cnx2 = m.dk.kindlevole(k.getMac());*/
                        
                             
                        boolean cnx= cnx1 ;
                         
                                 
                        
                        output.println(cnx);
                        connected=cnx;
                    }
                    k=m.dk.KindleEnCours(e.getCni());
                   
                   
                    
                    
                    
                    
                    
                    
                    
                    /*
                    while(!boolkindle){
                         Path chemin = Paths.get("kindlevole.txt");
                         InputStream input2 = null;
         
                         input2 = Files.newInputStream(chemin);
             
                         BufferedReader reader = new BufferedReader(new InputStreamReader(input2));
                         String s = null;
                       while((s= reader.readLine()) != null){
                
                       boolkindle= s.equalsIgnoreCase(k.getMac());
             
                       
                           output.println("true");
                           System.out.println("trouve");
                          s=null;
                           
                       break;
                     
                              

                       }
                      
                       output.println("false");
                     input2.close();}*/
                    
                   // System.out.println("Je suis connecté avec le kindle de mac : "+k.getMac()+" du mr/mdm "+e.getLogin() );
                    // lecture des kindles volee
                    
                  
    
 
       
                    
                    //la mediatheque reçoit le choix du client s'il veut faire une recherche wlla consulter document
                    //1- Faire une recherche\n 2-Consulter un document.\n 3-Acceder à mes infos \n 4-Se deconnecter
                    while(connected ){// ila khtar Se deconnecter aykhrej mn la boucle w ay3tih it connecta ila bgha y3awed
                        
                        String espace = input.readLine();
                        switch(espace){
                            //espace Livre
                            case "1":   boolean choice_livre=true;
                            while(choice_livre){
                                String livre = input.readLine();
                                switch(livre){
                                    case "1":   //khtar rechercher
                                        boolean rechercher=true;
                                        while(rechercher){//
                                            String choix_recherche=input.readLine();
                                            switch(choix_recherche){
                                                //1- Recherche par isbn. \n 2-Recherche par auteur.\n 3-Recherche par editeur. \n 4-Recherche par année d'edition.\n 5-Quit
                                                case "1": //isbn
                                                    //output_objet.writeObject(m.dd.getLivreByISBN(input.readLine()));
                                                    Livre l=m.dd.getLivreByISBN(input.readLine());
                                                    output_objet.writeObject(l);
                                                    if(l!=null){
                                                    m.dd.ajouterHistorique(l.getISBN(), e.getCni());}
                                                    boolean liste_isbn=true;
                                                    while(liste_isbn){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" : output_objet.writeObject(l.consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(l));break;
                                                            
                                                            case "3" :  boolean b = m.dd.ajouterFavoris(l.getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                                
                                                            case "4" :liste_isbn=false; break;
                                                            
                                                            default : break;
                                                        }
                                                    
                                                    }
                                                    ;break; 
                                                    
                                                case "2": // auteur
                                                    LinkedList<Livre> livres_auteur =m.dd.getLivreByAuteur(input.readLine());
                                                    output_objet.writeObject(livres_auteur);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(livres_auteur.get(o).getISBN(), e.getCni());
                                                    boolean liste_auteur=true;
                                                    while(liste_auteur){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" : output_objet.writeObject(livres_auteur.get(o).consulter());break;
                                                            
                                                            case "2" : output_objet.writeObject(m.dd.getContenu(livres_auteur.get(o))); break;
                                                            
                                                            case "3" :  boolean b = m.dd.ajouterFavoris(livres_auteur.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                                
                                                            case "4" :liste_auteur=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };
                                                    break;
                                                case "3"://editeur
                                                  //  output_objet.writeObject(m.dd.getLivreByEditeur(input.readLine()));
                                                    //
                                                     LinkedList<Livre> livres_editeur =m.dd.getLivreByEditeur(input.readLine());
                                                    output_objet.writeObject(livres_editeur);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(livres_editeur.get(o).getISBN(), e.getCni());
                                                    boolean liste_editeur=true;
                                                    while(liste_editeur){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(livres_editeur.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(livres_editeur.get(o)));break;
                                                            
                                                            case "3" : boolean b = m.dd.ajouterFavoris(livres_editeur.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                                
                                                            case "4" : liste_editeur=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };
                                                    break;
                                                    
                                                case "4"://annee edition
                                                   // output_objet.writeObject(m.dd.getLivreByAnneeEdition((Integer.parseInt(input.readLine()))));
                                                    //
                                                    LinkedList<Livre> livres_edition =m.dd.getLivreByAnneeEdition((Integer.parseInt(input.readLine())));
                                                    output_objet.writeObject(livres_edition);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(livres_edition.get(o).getISBN(), e.getCni());
                                                    boolean liste_edition=true;
                                                    while(liste_edition){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(livres_edition.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(livres_edition.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(livres_edition.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                                
                                                            case "4" : liste_edition=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };
                                                    break;
                                                
                                                            
                                                case "5"://editeur
                                                  //  output_objet.writeObject(m.dd.getLivreByEditeur(input.readLine()));
                                                    //
                                                     LinkedList<Livre> livres_titre =m.dd.getLivreByTitre(input.readLine());
                                                    output_objet.writeObject(livres_titre);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(livres_titre.get(o).getISBN(), e.getCni());
                                                    boolean liste_editeur=true;
                                                    while(liste_editeur){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(livres_titre.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(livres_titre.get(o)));break;
                                                            
                                                            case "3" : boolean b = m.dd.ajouterFavoris(livres_titre.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                                
                                                            case "4" : liste_editeur=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };
                                                    break;
                                                           
                                                case "6":   livres_auteur=null;
                                                            livres_edition=null;
                                                            livres_titre=null;
                                                            livres_editeur=null;
                                                            rechercher=false;break;    
                                                  
                                                    
                                                default:break;//;
                                            }
                                            
                                        };break;
                                    case "2":   //khtar consulter
                                        output_objet.writeObject(m.dd.getAllLivre());
                                        
                                        break;//
                                    case "3":choice_livre=false;//
                                    default:break;
                                    
                                };break;
                            };break;
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            //espace Dictionnaire
                            case "2" :  boolean choice_dictionnaire=true;
                            while(choice_dictionnaire){
                                String dictionnaire = input.readLine();
                                switch(dictionnaire){
                                    case "1":   //khtar rechercher
                                        boolean rechercher=true;
                                        while(rechercher){//
                                            String choix_recherche=input.readLine();
                                            switch(choix_recherche){
                                                //1- Recherche par isbn. \n 2-Recherche par auteur.\n 3-Recherche par editeur. \n 4-Recherche par année d'edition.\n 5-Quit
                                                case "1": //isbn
                                                    Dictionnaire d = m.dd.getDictionnaireByISBN(input.readLine());
                                                    output_objet.writeObject(d);
                                                    m.dd.ajouterHistorique(d.getISBN(), e.getCni());
                                                    //
                                                    boolean liste_isbn_dict=true;
                                                    while(liste_isbn_dict){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(d.consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(d));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(d.getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" :liste_isbn_dict=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }; 
                                                    
                                                    break;
                                                case "2": // auteur
                                                    //output_objet.writeObject(m.dd.getDictionnaireByAuteur(input.readLine()));
                                                    //
                                                    LinkedList<Dictionnaire> livres_auteur_dict =m.dd.getDictionnaireByAuteur(input.readLine());
                                                    output_objet.writeObject(livres_auteur_dict);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(livres_auteur_dict.get(o).getISBN(), e.getCni());
                                                    boolean liste_auteur_dict=true;
                                                    while(liste_auteur_dict){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(livres_auteur_dict.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(livres_auteur_dict.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(livres_auteur_dict.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            case "4" :liste_auteur_dict=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };livres_auteur_dict=null;
                                                    
                                                    
                                                    break;
                                                case "3"://editeur
                                                    //output_objet.writeObject(m.dd.getDictionnaireByEditeur(input.readLine()));
                                                    //
                                                     LinkedList<Dictionnaire> dicts_editeur =m.dd.getDictionnaireByEditeur(input.readLine());
                                                    output_objet.writeObject(dicts_editeur);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(dicts_editeur.get(o).getISBN(), e.getCni());
                                                    boolean liste_editeur_dict=true;
                                                    while(liste_editeur_dict){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(dicts_editeur.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(dicts_editeur.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(dicts_editeur.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" :liste_editeur_dict=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };dicts_editeur=null;
                                                    
                                                    
                                                    break;
                                                case "4"://annee edition
                                                    //output_objet.writeObject(m.dd.getDictionnaireByAnneeEdition((Integer.parseInt(input.readLine()))));
                                                    //
                                                    LinkedList<Dictionnaire> dicts_edition =m.dd.getDictionnaireByAnneeEdition((Integer.parseInt(input.readLine())));
                                                    output_objet.writeObject(dicts_edition);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(dicts_edition.get(o).getISBN(), e.getCni());
                                                    boolean liste_edition_dict=true;
                                                    while(liste_edition_dict){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(dicts_edition.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(dicts_edition.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(dicts_edition.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" :liste_edition_dict=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };dicts_edition=null;
                                                    
                                                    
                                                    break;
                                                
                                                            
                                                case "5"://editeur
                                                    //output_objet.writeObject(m.dd.getDictionnaireByEditeur(input.readLine()));
                                                    //
                                                     LinkedList<Dictionnaire> dicts_titre =m.dd.getDictionnaireByTitre(input.readLine());
                                                    output_objet.writeObject(dicts_titre);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(dicts_titre.get(o).getISBN(), e.getCni());
                                                    boolean liste_titre_dict=true;
                                                    while(liste_titre_dict){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(dicts_titre.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(dicts_titre.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(dicts_titre.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" :liste_titre_dict=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };dicts_titre=null;
                                                    
                                                    
                                                    break;
                                                            
                                                case "6":   livres_auteur_dict=null;
                                                            dicts_edition=null;
                                                            dicts_titre=null;
                                                            dicts_editeur=null;
                                                            rechercher=false;break;            
                                                    
                                                default:break;//;
                                            }
                                            
                                        };break;
                                    case "2":   
                                        
                                        output_objet.writeObject(m.dd.getAllDictionnaires());
                                        
                                        
                                        break;//
                                    case "3":choice_dictionnaire=false;//
                                    default:break;
                                    
                                };break;
                            };break;
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            //espace Magazine
                            case "3" :  boolean choice_magazine=true;
                            while(choice_magazine){
                                String magazine = input.readLine();
                                switch(magazine){
                                    case "1":   //khtar rechercher
                                        boolean rechercher=true;
                                        while(rechercher){//
                                            String choix_recherche=input.readLine();
                                            switch(choix_recherche){
                                                //1- Recherche par isbn. \n 2-Recherche par auteur.\n 3-Recherche par editeur. \n 4-Recherche par année d'edition.\n 5-Quit
                                                case "1": //isbn
                                                    Magazine mag = m.dd.getMagazineByISBN(input.readLine());
                                                    output_objet.writeObject(mag);
                                                    m.dd.ajouterHistorique(mag.getISBN(), e.getCni());
                                                    //
                                                    boolean liste_isbn_magazine=true;
                                                    while(liste_isbn_magazine){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(mag.consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(mag));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(mag.getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" : liste_isbn_magazine=false;break;
                                                            
                                                            default :break;
                                                        }
                                                    }; 
                                                    
                                                    break;
                                                case "2": // auteur
                                                    //output_objet.writeObject(m.dd.getMagazineByAuteur(input.readLine()));
                                                    //
                                                    LinkedList<Magazine> mags_auteur =m.dd.getMagazineByAuteur(input.readLine());
                                                    output_objet.writeObject(mags_auteur);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(mags_auteur.get(o).getISBN(), e.getCni());
                                                    boolean liste_auteur_magazine=true;
                                                    while(liste_auteur_magazine){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(mags_auteur.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(mags_auteur.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(mags_auteur.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" : liste_auteur_magazine=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };
                                                    
                                                    
                                                    break;
                                                case "3"://editeur
                                                    //output_objet.writeObject(m.dd.getMagazineByEditeur(input.readLine()));
                                                    //
                                                    LinkedList<Magazine> mags_editeur =m.dd.getMagazineByEditeur(input.readLine());
                                                    output_objet.writeObject(mags_editeur);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(mags_editeur.get(o).getISBN(), e.getCni());
                                                    boolean liste_editeur_magazine=true;
                                                    while(liste_editeur_magazine){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(mags_editeur.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(mags_editeur.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(mags_editeur.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" :liste_editeur_magazine=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };
                                                    
                                                    
                                                    break;
                                                case "4"://annee edition
                                                   // output_objet.writeObject(m.dd.getMagazineByAnneeEdition((Integer.parseInt(input.readLine()))));
                                                    //
                                                    LinkedList<Magazine> mags_edition =m.dd.getMagazineByAnneeEdition((Integer.parseInt(input.readLine())));
                                                    output_objet.writeObject(mags_edition);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(mags_edition.get(o).getISBN(), e.getCni());
                                                    boolean liste_edition_magazine=true;
                                                    while(liste_edition_magazine){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(mags_edition.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(mags_edition.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(mags_edition.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" :liste_edition_magazine=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };
                                                    
                                                    
                                                    break;
                                                
                                                            
                                                            
                                                case "5"://titre
                                                    //output_objet.writeObject(m.dd.getMagazineByEditeur(input.readLine()));
                                                    //
                                                    LinkedList<Magazine> mags_titre =m.dd.getMagazineByTitre(input.readLine());
                                                    output_objet.writeObject(mags_titre);
                                                    //
                                                    if(input.readLine().equals("y")){
                                                        int o = Integer.parseInt(input.readLine());
                                                        m.dd.ajouterHistorique(mags_titre.get(o).getISBN(), e.getCni());
                                                    boolean liste_titre_magazine=true;
                                                    while(liste_titre_magazine){
                                                        String lire_favoris = input.readLine();
                                                        switch(lire_favoris){
                                                            case "1" :output_objet.writeObject(mags_titre.get(o).consulter());break;
                                                            
                                                            case "2" :output_objet.writeObject(m.dd.getContenu(mags_titre.get(o)));break;
                                                            
                                                            case "3" :boolean b = m.dd.ajouterFavoris(mags_titre.get(o).getISBN(),e.getCni());
                                                                        output.println(b);break;
                                                            
                                                            case "4" :liste_titre_magazine=false;break;
                                                            
                                                            default : break;
                                                        }
                                                    }
                                                    };
                                                    
                                                    
                                                    break;            
                                                            
                                                    
                                            case "6":   
                                                            mags_auteur=null;
                                                            mags_edition=null;
                                                            mags_titre=null;
                                                            mags_editeur=null;
                                                            rechercher=false;break;
                                                            
                                                default:break;//;
                                            }
                                            
                                        };break;
                                    case "2":   //khtar consulter
                                       output_objet.writeObject(m.dd.getAllMagazines());

                                        break;//
                                    case "3":choice_magazine=false;//
                                    default:break;
                                    
                                };break;
                                
                            };break;
                            
                            
                            
                            //getAllFavoris
                            case "4" : output_objet.writeObject(m.dd.getAllFavoris(e.getCni())) ;break;
                            
                            case "5" : output_objet.writeObject(m.dd.Afficher_Historique(e.getCni())) ;break;
                            /*
                            //Espace info
                            case "5" :  ;break;
                            */
                            case "6" : connected = false;break; 
                            
                            default : System.out.println("Vous n'avez pas saisie correctement un choix\n"); break;
                            
                            
                            
                            
                        }
                        
                    }
                    
                    
                    
                    /*   switch(input.readLine()){
                    case "1":
                    //System.out.println(m.dd.getAllDocuments());
                    //  Livre l = (Livre) m.dd.getLivreByISBN("100");
                    // output_objet.writeObject(m.dd.getAllDocuments().getFirst());
                    break;
                    case "2":
                    // output.println("Consulter2");
                    break;
                    case "Quit":
                    // output.println("quit");
                    break;
                    default: System.out.println("Khouya dekhel 1 wlla 2 wlla 3 bla bsala");
                    
                    
                    }*/
                    
                    
                    
                }  } catch (IOException ex) {
                Logger.getLogger(media_thread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(media_thread.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(media_thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    }
        
}
