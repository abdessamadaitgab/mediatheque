

import DB.*;
import Model.Client;
import Model.Dictionnaire;
import Model.Document;
import Model.Emprunt;
import Model.Etudiant;
import Model.Kindle;
import Model.Livre;
import Model.Magazine;
import Model.Professeur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import static java.time.Instant.now;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import sun.jvm.hotspot.runtime.Threads;
public class mediatheque implements Serializable{
    
    CRUD_Client cd;
    CRUD_Document dd;
    CRUD_Kindle dk;
    CRUD_Emprunt ce;

    static final long serialVersionUID = 42L;
    
    public mediatheque(){
        cd=new CRUD_Client();
        dd=new CRUD_Document();
        dk=new CRUD_Kindle();
        ce=new CRUD_Emprunt();
    }
    
    //GETTERS DE CLIENT
    public LinkedList<Etudiant> getEtudiantByCne(String cne) throws SQLException{
        
        return cd.getEtudiant_By_Cne(cne);
    }
    
    public LinkedList<Etudiant> getClientByName(String Nom) throws SQLException{
        return cd.getEtudiant_By_Nom(Nom);
    }
    
    public LinkedList<Etudiant> getAllEtudiant() throws SQLException{
        return cd.getAllEtudiant();
    }
        public LinkedList<Professeur> getAllProfesseur() throws SQLException{
        return cd.getAllProfesseur();
    }
    
    //GETTERS DE DOCUMENT
     public LinkedList<Document> getAllDocuments() throws SQLException{
        return dd.getAllDocuments();
    }
       public LinkedList<Livre> getAllLivres() throws SQLException{
        return dd.getAllLivre();
    }
         public LinkedList<Dictionnaire> getAllDictionnaires() throws SQLException{
        return dd.getAllDictionnaires();
    }
           public LinkedList<Magazine> getAllMagazines() throws SQLException{
        return dd.getAllMagazines();
    }
     
    public Document getLivretByISBN(String ISBN) throws SQLException{
        return dd.getLivreByISBN(ISBN);
    }
     public Document getDictionnairetByISBN(String ISBN) throws SQLException{
        return dd.getDictionnaireByISBN(ISBN);
    }
      public Document getMagazinetByISBN(String ISBN) throws SQLException{
        return dd.getMagazineByISBN(ISBN);
    }
    
    public LinkedList<Livre> getLivreByTitle(String titre) throws SQLException{
        return dd.getLivreByTitre(titre);
    }
    public LinkedList<Magazine> getMagazineByTitle(String titre) throws SQLException{
        return dd.getMagazineByTitre(titre);
    }
    public LinkedList<Dictionnaire> getDictionnaireByTitle(String titre) throws SQLException{
        return dd.getDictionnaireByTitre(titre);
    }
    
    public LinkedList<Livre> getLivreByEditor(String Editeur) throws SQLException{
        return dd.getLivreByEditeur(Editeur);
    }
     public LinkedList<Dictionnaire> getDictionnaireByEditor(String Editeur) throws SQLException{
        return dd.getDictionnaireByEditeur(Editeur);
    }
      public LinkedList<Magazine> getMagazineByEditor(String Editeur) throws SQLException{
        return dd.getMagazineByEditeur(Editeur);
    }
  
    public LinkedList<Livre> getLivreByAnneEdition(int AnneEdition) throws SQLException{
        return dd.getLivreByAnneeEdition(AnneEdition);
    }
    
    public LinkedList<Dictionnaire> getDictionnaireByAnneEdition(int AnneEdition) throws SQLException{
        return dd.getDictionnaireByAnneeEdition(AnneEdition);
    }
    
    public LinkedList<Magazine> getMagazineByAnneEdition(int AnneEdition) throws SQLException{
        return dd.getMagazineByAnneeEdition(AnneEdition);
    }
    
    
    //GETTERS DE KINDLE
    public Kindle getKindleById(int idK) throws SQLException{
        return dk.getKindleByID(idK);
    }
    
    public ArrayList<Kindle> getKindleByModel(String Model){
        return null;
    }
    
    public Kindle getKindleByMac(String Mac) throws SQLException{
        return dk.getKindleByMac(Mac);
    }
    
    public LinkedList<Kindle> getAllKindles() throws SQLException{
        return dk.getKindles();
    } 
 

    //Client
    public Boolean ajouterEtudiant(Etudiant e) throws SQLException{
        return cd.Ajouter_Etudiant(e);
        
    }
     public Boolean ajouterProfesseur(Professeur e) throws SQLException{
        return cd.Ajouter_Professeur(e);
        
    }
    
    
   /* public Boolean modifierClient(Client c){
        
        return false;
    }*/
    
    public Boolean supprClient(String cni) throws SQLException{
        return cd.supprimer(cni);
        
        
    }
    //Document
    public Boolean ajouterDocument(Document d) throws SQLException{
       
       return dd.ajouterDocument(d);
        
    }
    
    /*public Boolean modifierDocument(Document d){
        return false;
    }*/
    
    public Boolean supprDocument(String isbn) throws SQLException{
        return dd.supprimer(isbn);
        
    }
    //Kindle
     public Boolean ajouterKindle(Kindle k) throws SQLException{
        //Saisir les informations de kindle
        //Kindle k=new Kindle();
        
        return dk.ajouterKindle(k);
        
        
    }
    
    /*public Boolean modifierKindle(Kindle k){
        return false;
    }*/
    
    public Boolean supprKindle(int idKindle) throws SQLException{
        //return CRUD_Kindle.supprKindle(idKindle);
        return dk.supprKindle(idKindle);
        
    }
    
    
    //Gestion des emprunts
    
    public Boolean ajouterEmprunt(Emprunt e) throws SQLException{
            //return CRUD_Emprunt.ajouterEmprunt(c,k);
            return ce.AjouterEmprunt(e);
    }
    
   /* public Boolean rendreEmprunt(Emprunt e,Date d){
        //return CRUD_Emprunt.rendreEmprunt(e,d);
        return false;
    }*/
    
    public LinkedList<Kindle> KindlesNonRendu() throws SQLException{
        //return CRUD_Emprunt.KindlesNonRendu();
        return dk.KindlesNonRendu();
    }
    
    public ArrayList<Emprunt> getEmpruntEnCour(){
        //return CRUD_Emprunt.getEmpruntEnCour();
        return null;
    }
    
    public Boolean SupprEmprunt(int IdEmprunt) throws SQLException{
        //retun CRUD_Emprunt.SupprEmprunt(IdEmprunt);
        return ce.supprEmprunt(IdEmprunt);
    }
    
    /*
        Gestion des communications avec les kindles
    
    */
    
    //////////////MAIN////////////////////
    public static void main(String []args) throws SQLException, IOException{
        //afficher le menu au gerant
        //1)Gestion des clients
        //2)Gestion des kindles
        //3)Gestion des documents
      
    /*  Etudiant e=new Etudiant("hamza" , "hamza", "ennaffati", "hamza", 23, "j1312111","ae123456");
      m.ajouterEtudiant(e);
     */
    
      //afficher le menu au gerant
        //1)Gestion des clients
        //2)Gestion des kindles
        //3)Gestion des documents
        
        // attend la connexion dial kindle

        
     /*             int port = 101;
        ServerSocket server = new ServerSocket(101);
       */ 

    /*      while(true){ 
       	Socket ClientSocket = server.accept();
	
	PrintWriter output = new PrintWriter(ClientSocket.getOutputStream(),true);
		
	BufferedReader input = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        
        System.out.println(input.readLine());*/
//            t.start();
     //}//
          //creation de la mediatheque
          
          mediatheque m = new mediatheque();
          m.cd.CRUD_Client("root","");
          m.dd.CRUD_Document("root","");
                 //la mediatheque attend une connexion de la part de Kindle 
       int port = 100;
        ServerSocket server = new ServerSocket(100);
        
        while(true){
            Socket soc = server.accept();
            Thread t = new Thread(new media_thread(soc));
            t.start();
        }
           
     // List l=m.dd.getAllDictionnaires();
      //System.out.print(l);
        }
      
}

   
