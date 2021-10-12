
import Model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.*;


public class Kindle implements Serializable{
    static final long serialVersionUID = 42L;
    
    public Boolean Connexion(){
        //Suivi de la localisation
        return false;
    }
    
    public Boolean Authentification(Client c,String login,String password){
        
        return false;
    }
    
    //GETTERS DE DOCUMENT
    public Document getDocumentByISBN(String ISBN){
        return null;
    }
    public ArrayList<Document> getDocumentByTitle(String titre){
        return null;
    }
    
    public ArrayList<Document> getDocumentByEditor(String Editeur){
        return null;
    }
  
    public ArrayList<Document> getDocumentByAnneEdition(int AnneEdition){
        return null;
    }
    public ArrayList<Document> getAllDocuments(){
        return null;
    }
    
    
    public static void main(String []args) throws IOException, ClassNotFoundException{
     //connexion avec mediatheque via sockets
       	String hostname="localhost";
	int port= 100;
		
	Socket Client = new Socket(hostname,port);
		
		
	PrintWriter output = new PrintWriter(Client.getOutputStream(),true);
	
        
	BufferedReader input = new BufferedReader(new InputStreamReader(Client.getInputStream()));

        
        ObjectInputStream input_object = new ObjectInputStream(Client.getInputStream());

        
        
        
        //la liste 
         boolean repeat=true;
         //while le client n'est pas connecté il ne peut pas acceder a la liste
            while (repeat){
               boolean connected = false;
               boolean boolkindle=false;
                while(!connected){
                System.out.println("Entrez votre login");
                String login= (new Scanner(System.in)).nextLine();
                output.println(login);
                System.out.println("Entrez votre mot de passe");
                String mdp = (new Scanner(System.in)).nextLine();
                output.println(mdp);
                String cnx = input.readLine();
                if(cnx.equals(new String("true"))){
                    System.out.println("La connexion est approuvée");
                    connected=true;}
                else{
                    System.out.println("Le login ou le mot de passe est erroné ou bien vous etes en dehors de campus ");
                    connected=false;
                   
                }
                }
                  //  System.out.println("*****************************************************************************************************");
                /*
                
                
                String kindlevolee=input.readLine();
                 if(kindlevolee.equals(new String("true"))){
                      System.out.println("Le kindle est volee");
                      connected=false;
                    }
                 else
                     System.out.println("Le kindle n'est pas  volee");*/

                 
            while(connected  ){// ila khrej mn la liste principale rah fhal ila deconecta
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Choice? =>\n 1- Espace Livre\n 2- Espace dictionnaire\n 3- Espace Magazine\n 4- Consulter les favoris \n 5- Afficher l'historique \n 6- Se Deconnecter");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    String espace = (new Scanner(System.in)).nextLine();
                    output.println(espace);
                    switch(espace){
                        //Esapce livre
case "1" :  boolean choice_livre=true;
            while(choice_livre){
                System.out.println("Vous êtes dans l'espace Livre");
                System.out.println("==============================================================");
            System.out.println("choice? => \n 1- Faire une recherche\n 2-Lister tout les livres .\n 3- Retour ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String livre= (new Scanner(System.in)).nextLine();
            output.println(livre);
                switch(livre){
                    case "1":// le client a choisi de faire une recherche donc an3tiwh liste dial les choix de recherche
                                boolean rechercher=true;
                                while(rechercher){
                                                System.out.println("choice? => \n 1- Recherche par isbn. \n 2-Recherche par auteur.\n 3-Recherche par editeur. \n 4-Recherche par année d'edition.\n 5-Recherche par titre. \n 6-Quit ");
                                                System.out.println(".................................................................................................");
                                                String choix_recherche= (new Scanner(System.in)).nextLine();
                                                output.println(choix_recherche);
                                                    
                                                    
                        
                                                    switch(choix_recherche){
                                                        case "1": //Client a choisit de faire la recherche par ISBN
                                                                  System.out.println("Veuillez entrez l'ISBN:  ");
                                                                  String choix_isbn= (new Scanner(System.in)).nextLine();
                                                                  //kindle aysift isbn likteb client l mediatheque
                                                                  output.println(choix_isbn);
                                                                  //aytle99a livre lisifet mediatheque
                                                                   System.out.println(input_object.readObject());
                                                                   //
                                                                    boolean liste_isbn=true;
                                                                    while(liste_isbn){
                                                                        System.out.println("******************************************************************");
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le livre\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("==============================================================");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" :   System.out.println("Details : ");
                                                                                            System.out.println(input_object.readObject());
                                                                                          
                                                                                          break; //consulter
                                                                               
                                                                               case "2" :   System.out.println("Contenu :");
                                                                                            System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" :
                                                                                            boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le livre existe déja dans les favoris ");
                                                                                            };break;
                                                                               case "4" :liste_isbn=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                   ;break;
//;
                                                        case "2": //Client a choisit de faire la recherche par AUTEUR
                                                                  System.out.println("Veuillez entrez l'auteur:  ");
                                                                  String choix_auteur= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift auteur l mediatheque
                                                                  output.println(choix_auteur);
                                                                  //aytle99a livre lisifet mediatheque
                                                                  LinkedList<Livre> listes_auteur = (LinkedList<Livre>) input_object.readObject(); 
                                                                  int i=0;
                                                                  for(Livre l: listes_auteur){
                                                                      System.out.println(i+"- "+l);
                                                                      i++;
                                                                  }
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc);
                                                                  if(abc.equals("y")){
                                                                      System.out.println("Veuillez choisir un des livres lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                   boolean liste_auteur=true;
                                                                    while(liste_auteur){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le livre\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : 
                                                                                          System.out.println(input_object.readObject());
                                                                                       break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" :  boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le livre existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_auteur=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  }

                                                                        ;break;
                                                                        
                                                                        
                                                        case "3"://Client a choisit de faire la recherche par EDITEUR
                                                                  System.out.println("Veuillez entrez l'editeur:  ");
                                                                  String choix_editeur= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift editeur l mediatheque
                                                                  output.println(choix_editeur);
                                                                  //aytle99a livre lisifet mediatheque
                                                                   //System.out.println(input_object.readObject());
                                                                   //
                                                                   LinkedList<Livre> listes_editeur = (LinkedList<Livre>) input_object.readObject(); 
                                                                  int i_editeur=0;
                                                                  for(Livre l: listes_editeur){
                                                                      System.out.println(i_editeur+"- "+l);
                                                                      i_editeur++;
                                                                  }
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_editeur= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_editeur);
                                                                  if(abc_editeur.equals("y")){
                                                                      System.out.println("Veuillez choisir un des livres lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                   
                                                                   
                                                                   boolean liste_Editeur=true;
                                                                    while(liste_Editeur){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le livre\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le livre existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_Editeur=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  };
                                                                   break;
                                                        case "4"://Client a choisit de faire la recherche par Année EDITION
                                                                  System.out.println("Veuillez entrez l'année d'edition:  ");
                                                                  String choix_edition= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift Année EDITION l mediatheque
                                                                  output.println(choix_edition);
                                                                  //aytle99a livres lisifet mediatheque
                                                                  // System.out.println(input_object.readObject());
                                                                   //
                                                                  LinkedList<Livre> listes_edition = (LinkedList<Livre>) input_object.readObject(); 
                                                                  int i_edition=0;
                                                                  for(Livre l: listes_edition){
                                                                      System.out.println(i_edition+"- "+l);
                                                                      i_edition++;
                                                                  }
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_edition= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_edition);
                                                                  if(abc_edition.equals("y")){
                                                                      System.out.println("Veuillez choisir un des livres lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                   boolean liste_edition=true;
                                                                    while(liste_edition){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le livre\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le livre existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_edition=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  }
                                                                    ;break;
                                                        
                                                        
                                                        case "5": System.out.println("Veuillez entrez le titre:  ");
                                                                  String choix_titre= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift editeur l mediatheque
                                                                  output.println(choix_titre);
                                                                  //aytle99a livre lisifet mediatheque
                                                                   //System.out.println(input_object.readObject());
                                                                   //
                                                                   LinkedList<Livre> listes_titre = (LinkedList<Livre>) input_object.readObject(); 
                                                                  int i_titre=0;
                                                                  for(Livre l: listes_titre){
                                                                      System.out.println(i_titre+"- "+l);
                                                                      i_titre++;
                                                                  }
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_titre= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_titre);
                                                                  if(abc_titre.equals("y")){
                                                                      System.out.println("Veuillez choisir un des livres lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                   
                                                                   
                                                                   boolean liste_titre=true;
                                                                    while(liste_titre){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le livre\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le livre existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_titre=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  };
                                                                   break;            
                                                                    
                                                                   
                                                         case "6"://le client veut sortir de la liste de recherche il retourne à la liste principale
                                                                    listes_edition=null;
                                                                    listes_editeur=null;
                                                                    listes_titre=null;
                                                                    listes_auteur=null;
                                                                    rechercher=false;break;          
                                                                   
                                                        default:break;//;
                                                    }

                                    
                                };break; 

                                                    //;
                    case "2":// le client a choisi de faire une consultation donc an3tiwh liste dial les choix de consultation
                                //System.out.println(input_object.readObject());
                                LinkedList<Livre> all_livres = (LinkedList<Livre>) input_object.readObject();
                                int i_all_livre=0;
                                for(Livre l : all_livres ){
                                System.out.println(i_all_livre+"-" +l);
                                i_all_livre++;        
                                        }
                                all_livres=null;
                            ;break; 

                                
                    case "3":choice_livre=false; break;//quit ou se deconecter;
                    default: System.out.println("Veuillez choisir 1 pour rechercher , 2 pour consulter , 3 pour quitter la liste\n"); break;
                };break;
            };break;
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
                //Espace dictionnaire
case "2" :   boolean choice_dictionnaire=true;
            while(choice_dictionnaire){
                System.out.println("Vous êtes dans l'espace Dictionnaire");
                System.out.println("==============================================================");
            System.out.println("choice? => \n 1- Faire une recherche\n 2-Lister les Dictionnaire.\n 3- Quitter la liste ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String dictionnaire= (new Scanner(System.in)).nextLine();
            output.println(dictionnaire);
                switch(dictionnaire){
                    case "1":// le client a choisi de faire une recherche donc an3tiwh liste dial les choix de recherche
                                boolean rechercher=true;
                                while(rechercher){
                                                System.out.println("choice? => \n 1- Recherche par isbn. \n 2-Recherche par auteur.\n 3-Recherche par editeur. \n 4-Recherche par année d'edition.\n 5-Recherche par titre \n 6-Quit ");
                                                System.out.println(".................................................................................................");
                                                String choix_recherche= (new Scanner(System.in)).nextLine();
                                                output.println(choix_recherche);
                                                    
                                                    
                        
                                                    switch(choix_recherche){
                                                        case "1": //Client a choisit de faire la recherche par ISBN
                                                                  System.out.println("Veuillez entrez l'ISBN:  ");
                                                                  String choix_isbn= (new Scanner(System.in)).nextLine();
                                                                  //kindle aysift isbn likteb client l mediatheque
                                                                  output.println(choix_isbn);
                                                                  //aytle99a livre lisifet mediatheque
                                                                   System.out.println(input_object.readObject());
                                                                   //
                                                                   boolean liste_isbn_dict=true;
                                                                    while(liste_isbn_dict){
                                                                        System.out.println("******************************************************************");
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le dictionnaire\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("==============================================================");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" :  boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_isbn_dict=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    };
                                                                   
                                                                   break;
//;
                                                        case "2": //Client a choisit de faire la recherche par AUTEUR
                                                                  System.out.println("Veuillez entrez l'auteur:  ");
                                                                  String choix_auteur= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift auteur l mediatheque
                                                                  output.println(choix_auteur);
                                                                  //aytle99a livre lisifet mediatheque
                                                                   //System.out.println(input_object.readObject());
                                                                   //
                                                                   LinkedList<Dictionnaire> listes_auteur_dict = (LinkedList<Dictionnaire>) input_object.readObject(); 
                                                                  int i_dict=0;
                                                                  for(Dictionnaire dict: listes_auteur_dict){
                                                                      System.out.println(i_dict+"- "+dict);
                                                                      i_dict++;
                                                                  }
                                                                  listes_auteur_dict=null;
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc);
                                                                  if(abc.equals("y")){
                                                                      System.out.println("Veuillez choisir un des Dictionnaire lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                     boolean liste_auteur_dict=true;
                                                                    while(liste_auteur_dict){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le livre\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_auteur_dict=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  }
                                                                   

                                                                        ;break;
                                                        case "3"://Client a choisit de faire la recherche par EDITEUR
                                                                  System.out.println("Veuillez entrez l'editeur:  ");
                                                                  String choix_editeur= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift editeur l mediatheque
                                                                  output.println(choix_editeur);
                                                                  //aytle99a livre lisifet mediatheque
                                                                  // System.out.println(input_object.readObject());
                                                                    //
                                                                   LinkedList<Dictionnaire> listes_editeur_dict = (LinkedList<Dictionnaire>) input_object.readObject(); 
                                                                  int i_editeur_dict=0;
                                                                  for(Dictionnaire d: listes_editeur_dict){
                                                                      System.out.println(i_editeur_dict+"- "+d);
                                                                      i_editeur_dict++;
                                                                  }
                                                                   listes_editeur_dict=null;
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_editeur= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_editeur);
                                                                  if(abc_editeur.equals("y")){
                                                                      System.out.println("Veuillez choisir un des Dictionnaire lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                     boolean liste_Editeur_dict=true;
                                                                    while(liste_Editeur_dict){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le Dictionnaire\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_Editeur_dict=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  }
                                                                           
                                                                           
                                                                    ;break;
                                                        case "4"://Client a choisit de faire la recherche par Année EDITION
                                                                  System.out.println("Veuillez entrez l'année d'edition:  ");
                                                                  String choix_edition= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift Année EDITION l mediatheque
                                                                  output.println(choix_edition);
                                                                  //aytle99a livres lisifet mediatheque
                                                                 //  System.out.println(input_object.readObject());
                                                                   //
                                                                   LinkedList<Dictionnaire> listes_edition_dict = (LinkedList<Dictionnaire>) input_object.readObject(); 
                                                                  int i_edition_dict=0;
                                                                  for(Dictionnaire d: listes_edition_dict){
                                                                      System.out.println(i_edition_dict+"- "+d);
                                                                      i_edition_dict++;
                                                                  }
                                                                   listes_edition_dict=null;
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_edition= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_edition);
                                                                  if(abc_edition.equals("y")){
                                                                      System.out.println("Veuillez choisir un des Dictionnaires lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                    boolean liste_edition_dict=true;
                                                                    while(liste_edition_dict){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le Dictionnaire\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_edition_dict=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  };
                                                                   
                                                                   
                                                                    break;
                                                        
                                                                    
                                                        case "5"://Client a choisit de faire la recherche par EDITEUR
                                                                  System.out.println("Veuillez entrez l'editeur:  ");
                                                                  String choix_titre= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift editeur l mediatheque
                                                                  output.println(choix_titre);
                                                                  //aytle99a livre lisifet mediatheque
                                                                  // System.out.println(input_object.readObject());
                                                                    //
                                                                   LinkedList<Dictionnaire> listes_titre_dict = (LinkedList<Dictionnaire>) input_object.readObject(); 
                                                                  int i_titre_dict=0;
                                                                  for(Dictionnaire d: listes_titre_dict){
                                                                      System.out.println(i_titre_dict+"- "+d);
                                                                      i_titre_dict++;
                                                                  }
                                                                   listes_titre_dict=null;
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_titre= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_titre);
                                                                  if(abc_titre.equals("y")){
                                                                      System.out.println("Veuillez choisir un des Dictionnaire lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                     boolean liste_titre_dict=true;
                                                                    while(liste_titre_dict){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire le Dictionnaire\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_titre_dict=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  }
                                                                           
                                                                           
                                                                    ;break;         
                                                                    
                                                                    
                                                            case "6"://le client veut sortir de la liste de recherche il retourne à la liste principale
                                                                    listes_edition_dict=null;
                                                                    listes_editeur_dict=null;
                                                                    listes_titre_dict=null;
                                                                    listes_auteur_dict=null;
                                                                    rechercher=false;break;        
                                                                    
                                                        default:break;//;
                                                    }

                                    
                                };break; 

                                                    //;
                    case "2":// le client a choisi de faire une consultation donc an3tiwh liste dial les choix de consultation
                             // System.out.println(input_object.readObject())
                                      LinkedList<Dictionnaire> all_dic = (LinkedList<Dictionnaire>) input_object.readObject();
                                int i_all_dic=0;
                                for(Dictionnaire dic : all_dic){
                                System.out.println(i_all_dic+"-" +dic);
                                i_all_dic++;        
                                        };
                                        all_dic=null;

                        ;break; 

                                
                    case "3":choice_dictionnaire=false; break;//quit ou se deconecter;
                    default: System.out.println("Veuillez choisir 1 pour rechercher , 2 pour consulter , 3 pour quitter la liste\n"); break;
                };break;
            };break;
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
                //Espace magazine
case "3" :    boolean choice_magazine=true;
            while(choice_magazine){
                System.out.println("Vous êtes dans l'espace Magazine");
                System.out.println("==============================================================");
            System.out.println("choice? => \n 1- Faire une recherche\n 2-Lister les Magazine.\n 3- Quitter la liste ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String magazine= (new Scanner(System.in)).nextLine();
            output.println(magazine);
                switch(magazine){
                    case "1":// le client a choisi de faire une recherche donc an3tiwh liste dial les choix de recherche
                                boolean rechercher=true;
                                while(rechercher){
                                                System.out.println("choice? => \n 1- Recherche par isbn. \n 2-Recherche par auteur.\n 3-Recherche par editeur. \n 4-Recherche par année d'edition.\n 5-Recherche par titre \n 6-Quit ");
                                                System.out.println(".................................................................................................");
                                                String choix_recherche= (new Scanner(System.in)).nextLine();
                                                output.println(choix_recherche);
                                                    
                                                    
                        
                                                    switch(choix_recherche){
                                                        case "1": //Client a choisit de faire la recherche par ISBN
                                                                  System.out.println("Veuillez entrez l'ISBN:  ");
                                                                  String choix_isbn= (new Scanner(System.in)).nextLine();
                                                                  //kindle aysift isbn likteb client l mediatheque
                                                                  output.println(choix_isbn);
                                                                  //aytle99a livre lisifet mediatheque
                                                                   System.out.println(input_object.readObject());
                                                                   //
                                                                   boolean liste_isbn_magazine=true;
                                                                    while(liste_isbn_magazine){
                                                                        System.out.println("******************************************************************");
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire la magazine\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("==============================================================");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_isbn_magazine=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    };
                                                                   
                                                                   
                                                                   break;
//;
                                                        case "2": //Client a choisit de faire la recherche par AUTEUR
                                                                  System.out.println("Veuillez entrez l'auteur:  ");
                                                                  String choix_auteur= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift auteur l mediatheque
                                                                  output.println(choix_auteur);
                                                                  //aytle99a livre lisifet mediatheque
                                                                   //System.out.println(input_object.readObject());
                                                                   //
                                                                  LinkedList<Magazine> listes_auteur_mag = (LinkedList<Magazine>) input_object.readObject(); 
                                                                  int i_mag=0;
                                                                  for(Magazine mags: listes_auteur_mag){
                                                                      System.out.println(i_mag+"- "+mags);
                                                                      i_mag++;
                                                                  }
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc);
                                                                  if(abc.equals("y")){
                                                                      System.out.println("Veuillez choisir un des Magazine lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                     boolean liste_auteur_magazine=true;
                                                                    while(liste_auteur_magazine){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire la Magazine\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" :System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_auteur_magazine=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  }

                                                                        ;break;
                                                        case "3"://Client a choisit de faire la recherche par EDITEUR
                                                                  System.out.println("Veuillez entrez l'editeur:  ");
                                                                  String choix_editeur= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift editeur l mediatheque
                                                                  output.println(choix_editeur);
                                                                  //aytle99a livre lisifet mediatheque
                                                                 //  System.out.println(input_object.readObject());
                                                                   LinkedList<Magazine> listes_editeur_mag = (LinkedList<Magazine>) input_object.readObject(); 
                                                                  int i_editeur_mag=0;
                                                                  for(Magazine d: listes_editeur_mag){
                                                                      System.out.println(i_editeur_mag+"- "+d);
                                                                      i_editeur_mag++;
                                                                  }
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_editeur= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_editeur);
                                                                  if(abc_editeur.equals("y")){
                                                                      System.out.println("Veuillez choisir un des Magazine lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                    boolean liste_Editeur_magazine=true;
                                                                    while(liste_Editeur_magazine){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire la Magazine\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_Editeur_magazine=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  };
                                                                   
                                                                   break;
                                                        case "4"://Client a choisit de faire la recherche par Année EDITION
                                                                  System.out.println("Veuillez entrez l'année d'edition:  ");
                                                                  String choix_edition= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift Année EDITION l mediatheque
                                                                  output.println(choix_edition);
                                                                  //aytle99a livres lisifet mediatheque
                                                                   //System.out.println(input_object.readObject());
                                                                   //
                                                                  LinkedList<Magazine> listes_edition_mag = (LinkedList<Magazine>) input_object.readObject(); 
                                                                  int i_edition_mag=0;
                                                                  for(Magazine d: listes_edition_mag){
                                                                      System.out.println(i_edition_mag+"- "+d);
                                                                      i_edition_mag++;
                                                                  }
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_edition= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_edition);
                                                                  if(abc_edition.equals("y")){
                                                                      System.out.println("Veuillez choisir un des Magazine lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                   boolean liste_edition_magazine=true;
                                                                    while(liste_edition_magazine){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire la Magazine\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_edition_magazine=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  };
                                                                   
                                                                    break;
                                                        
                                                                    
                                                                    
                                                        case "5"://Client a choisit de faire la recherche par TITRE
                                                                  System.out.println("Veuillez entrez le titre:  ");
                                                                  String choix_titre= (new Scanner(System.in)).nextLine();
                                                                  //Kindle aysift editeur l mediatheque
                                                                  output.println(choix_titre);
                                                                  //aytle99a livre lisifet mediatheque
                                                                 //  System.out.println(input_object.readObject());
                                                                   LinkedList<Magazine> listes_titre_mag = (LinkedList<Magazine>) input_object.readObject(); 
                                                                  int i_titre_mag=0;
                                                                  for(Magazine d: listes_titre_mag){
                                                                      System.out.println(i_titre_mag+"- "+d);
                                                                      i_titre_mag++;
                                                                  }
                                                                   
                                                                   //
                                                                  System.out.println("............................................................................................");
                                                                  System.out.println("Voulez vous accedez au menu (details-lire-favori)? Entrez y pour oui et n pour non");
                                                                  System.out.println("............................................................................................");
                                                                  String abc_titre= (new Scanner(System.in)).nextLine();
                                                                  output.println(abc_titre);
                                                                  if(abc_titre.equals("y")){
                                                                      System.out.println("Veuillez choisir un des Magazine lister");
                                                                      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                                                              String efg= (new Scanner(System.in)).nextLine();
                                                                              output.println(efg);
                                                                    boolean liste_Editeur_magazine=true;
                                                                    while(liste_Editeur_magazine){
                                                                   System.out.println("Choice? \n 1-Consulter les details\n  2- Lire la Magazine\n 3- Ajouter au favoris\n 4- retour");
                                                                   System.out.println("------------------------------------------------------------------------------");
                                                                   String lire_favoris = (new Scanner(System.in)).nextLine();
                                                                           output.println(lire_favoris);
                                                                    
                                                                           switch(lire_favoris){
                                                                               case "1" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "2" : System.out.println(input_object.readObject());break;
                                                                               
                                                                               case "3" : boolean b =Boolean.parseBoolean(input.readLine());
                                                                                            if(b){
                                                                                            System.out.println("le document est ajouté aux favoris");
                                                                                            }else{
                                                                                                System.out.println("le document existe déja dans les favoris ");
                                                                                            };break;
                                                                                   
                                                                               case "4" :liste_Editeur_magazine=false;break;
                                                                               
                                                                               default : System.out.println("Veuillez saisir un choix de la liste\n");break;
                                                                                   
                                                                               
                                                                           }
                                                                    
                                                                    }
                                                                  };
                                                                   
                                                                   break;            
                                                                    
                                                        case "6"://le client veut sortir de la liste de recherche il retourne à la liste principale
                                                                   listes_edition_mag=null;
                                                                    listes_editeur_mag=null;
                                                                    listes_titre_mag=null;
                                                                    listes_auteur_mag=null;
                                                                    rechercher=false;break;           
                                                                   
                                                                   
                                                        default:break;//;
                                                    }

                                    
                                };break; 

                                                    //;
                    case "2":// le client a choisi de faire une consultation donc an3tiwh liste dial les choix de consultation
                                LinkedList<Magazine> all_mag = (LinkedList<Magazine>) input_object.readObject();
                                int i_all_mag=0;
                                for(Magazine mag : all_mag ){
                                System.out.println(i_all_mag+"-" +mag);
                                i_all_mag++;        
                                        };
                                        all_mag=null;
                             ;break; 

                                
                    case "3":choice_magazine=false; break;//quit ou se deconecter;
                    default: System.out.println("Veuillez choisir 1 pour rechercher , 2 pour consulter , 3 pour quitter la liste\n"); break;
                };break;
            };break;    
                
            
            
            
            
            
            
            //getAllFavoris
case "4" :     System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Liste de Vos Favoris : \n");
                LinkedList<Document> all_fav = (LinkedList<Document>) input_object.readObject();
                                int i_all_fav=0;
                                for(Document fav : all_fav ){
                                System.out.println(i_all_fav+"-" +fav);
                                i_all_fav++;        
                                        }
            ;break;

                //Espace info
/*case "5" : System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            ;break;
  */                  

case "5" :      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Historique : \n");
                LinkedList<Document> all_hist = (LinkedList<Document>) input_object.readObject();
                                int i_all_hist=0;
                                for(Document hist : all_hist ){
                                System.out.println(i_all_hist+"-" +hist);
                                i_all_hist++;        
                                        }
            ;break;
       
            
case "6" : System.out.println("Bye Bye !\n");
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            connected = false;break;


default : System.out.println("Vous n'avez pas saisie correctement un choix\n"); break;                    
                    
                    
                    
                    }
                
               
                }
     
                
                
         /*   System.out.println("choice? => 1- Consulter la liste des documents\n 2-Consulter un document. \n 3-Quit ");
            String choix= (new Scanner(System.in)).nextLine();
            output.write(choix+"\n") ; 
            output.flush();
            switch (choix){
                case "1":
                       // output.println("Consulter");
                    System.out.println(input_object.readObject());
                    break;
                case "2":
                    //output.println("Consulter2");
                    break;
                case "Quit":
                    //output.println("quit");
                    break;
                default: System.out.println("Khouya dekhel 1 wlla 2 wlla 3 bla bsala");
            }*/
        
                
         
               
            }
            Client.close();

    
    }
}