package DB;


import Model.Dictionnaire;
import Model.Document;
import Model.Livre;
import Model.Magazine;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rachad
 */
public class CRUD_Document {
    
    Statement stmt;
    
    public void CRUD_Document(String username, String password) throws SQLException {
    
    Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/mediatheque?serverTimezone=UTC",
                         username,
                         password);
   
    stmt = con.createStatement();

}
    
    public boolean ajouterDocument(Document d) throws SQLException{
        if (d instanceof Livre){
            int type=1;
         String query="insert into document(isbn,titre,auteur,editeur,annee,url,type,nbpages,categorie,tome) values ('"+d.getISBN()+"','"+d.getTitre()+"','"+d.getAuteurs()+"','"+d.getEditeur()+"','"+d.getAnneeEdition()+"','"+d.getUrl()+"','"+type+"','"+((Livre)d).getNbPage()+"','"+((Livre) d).getCat()+"','"+((Livre) d).getTome()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
        }
        
        else if (d instanceof Dictionnaire){
            int type=2;
         String query="insert into document(isbn,titre,auteur,editeur,annee,url,type,langue,nbtome) values ('"+d.getISBN()+"','"+d.getTitre()+"','"+d.getAuteurs()+"','"+d.getEditeur()+"','"+d.getAnneeEdition()+"','"+d.getUrl()+"','"+type+"','"+((Dictionnaire)d).getLangue()+"','"+((Dictionnaire) d).getNbTome()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
        }
        else if (d instanceof Magazine){
            int type=3;
         String query="insert into document(isbn,titre,auteur,editeur,annee,url,type,period,mois,jour) values ('"+d.getISBN()+"','"+d.getTitre()+"','"+d.getAuteurs()+"','"+d.getEditeur()+"','"+d.getAnneeEdition()+"','"+d.getUrl()+"','"+type+"','"+((Magazine)d).getPeriodicite()+"','"+((Magazine) d).getMois()+"','"+((Magazine)d).getJour()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
        }
        return false;
            
       
    }
    
    
    public boolean ajouterFavoris(String isbn ,String cin) throws SQLException{
         String query0="select * from favoris where cniClient='"+cin+"' AND IsbnDocument='"+isbn+"'";
        ResultSet rs=stmt.executeQuery(query0);
        
        if(rs.next()){//le document est deja ajouté dans les favoris
            return false;
        }else{
            String format = "dd/MM/yy H:mm:ss";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
            java.util.Date date = new java.util.Date();
            System.out.println(formater.format( date ));
         String query="insert into favoris(cniClient,IsbnDocument,date_favori) values ('"+cin+"','"+isbn+"','"+formater.format( date )+"') ";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
        }
        }
    
    
    
    
    
    
    
    
    
        public boolean ajouterHistorique(String isbn ,String cin) throws SQLException{

            String format = "dd/MM/yy H:mm:ss";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
            java.util.Date date = new java.util.Date();
            System.out.println(formater.format( date ));
         String query="insert into historique(isbn_document,cni_client,date_historique) values ('"+isbn+"','"+cin+"','"+formater.format( date )+"') ";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
        
        }
    
    
         public  String getContenu(Document d) throws SQLException{

        String query="select * from document where  isbn = '"+d.getISBN()+"'";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Livre> livres= new   LinkedList<Livre> ();
        String Contenu="";
        if(rs.next()) {
           Contenu=rs.getString("Contenu");
       }else{
            Contenu="Aucun contenu à afficher";
        }
        return Contenu;
    }
    
    
    
              public  LinkedList<Document> Afficher_Historique(String cni) throws SQLException{

        String query="select * from historique,document where historique.isbn_document=document.isbn AND historique.cni_client ='"+cni+"'";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Document> hist= new   LinkedList<Document> ();
        
        while (rs.next()) {
           String isbn=rs.getString("isbn");
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           String titre=rs.getString("titre");
      
           String url=rs.getString("url");
           Document doc = new Document(isbn,titre,auteur,editeur,edition,url);
           hist.add(doc);
       }

       return hist;
    }
    
         
    
    
    
    
    
    
    
    
     public  LinkedList<Livre> getLivreByTitre(String titre) throws SQLException{

        String query="select * from document where titre like '"+titre+"' AND type='1'";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Livre> livres= new   LinkedList<Livre> ();
        
        while (rs.next()) {
           String isbn=rs.getString("isbn");
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
      
           String url=rs.getString("url");
           String categorie = rs.getString("categorie");
           String tome = rs.getString("tome");
           Livre l = new Livre(isbn,titre,auteur,editeur,edition,url,nbpages,categorie,tome);
           livres.add(l);
       }

       return livres;
    }
     
            public  LinkedList<Dictionnaire> getDictionnaireByTitre(String title) throws SQLException{
        int type=2;
        String query="select * from document where titre like'"+title+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Dictionnaire> livres= new   LinkedList<Dictionnaire> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String langue = rs.getString("langue");
           int nbtome = rs.getInt("nbtome");
           String titre = rs.getString("Titre");
          Dictionnaire d=new Dictionnaire(isbn,titre,auteur,editeur,edition,url,langue,nbtome);
           
           livres.add(d);
       }

       return livres;
    }
              public   LinkedList<Magazine> getMagazineByTitre(String title) throws SQLException{
        int type=3;
        String query="select * from document where titre like'"+title+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Magazine> livres= new   LinkedList<Magazine> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
          Magazine d=new Magazine(isbn,titre,auteur,editeur,edition,url,period,mois,jour);
           
           livres.add(d);
       }

       return livres;
    }
            
     
        public Livre getLivreByISBN(String isbn) throws SQLException{

        String query="select * from document where isbn like'"+isbn+"' AND type='1' ";
        ResultSet rs=stmt.executeQuery(query);
       
        if (rs.next()) {
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String type = rs.getString("categorie");
           String tome = rs.getString("tome");
           String titre = rs.getString("Titre");
           Livre l = new Livre(isbn,titre,auteur,editeur,edition,url,nbpages,type,tome);
           return l;
       }
        return null;

    }
          public  Dictionnaire getDictionnaireByISBN(String isbn) throws SQLException{
        int type=2;
        String query="select * from document where isbn like'"+isbn+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
       
        if (rs.next()) {
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String langue = rs.getString("langue");
           int nbtome = rs.getInt("nbtome");
           String titre = rs.getString("Titre");
          Dictionnaire d=new Dictionnaire(isbn,titre,auteur,editeur,edition,url,langue,nbtome);
           return d;
       }
        return null;

    }
         public  Magazine getMagazineByISBN(String isbn1) throws SQLException{
        int type=3;
        String query="select * from document where isbn like'"+isbn1+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        
        if (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
          Magazine d=new Magazine(isbn,titre,auteur,editeur,edition,url,period,mois,jour);
           
return d;       }

       return null;
    }
           public   LinkedList<Livre>getLivreByEditeur(String Editeur) throws SQLException{
                 int type=1;

        String query="select * from document where Editeur like '"+Editeur+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Livre> livres= new   LinkedList<Livre> ();
        
        while (rs.next()) {
           String isbn=rs.getString("isbn");
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String cat = rs.getString("categorie");
           String tome = rs.getString("tome");
           String titre = rs.getString("Titre");
           Livre l = new Livre(isbn,titre,auteur,editeur,edition,url,nbpages,cat,tome);
            System.out.println(l+"\n");
           livres.add(l);
       }

       return livres;
    }
            public  LinkedList<Dictionnaire> getDictionnaireByEditeur(String Editeur) throws SQLException{
        int type=2;
        String query="select * from document where editeur like'"+Editeur+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Dictionnaire> livres= new   LinkedList<Dictionnaire> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String langue = rs.getString("langue");
           int nbtome = rs.getInt("nbtome");
           String titre = rs.getString("Titre");
          Dictionnaire d=new Dictionnaire(isbn,titre,auteur,editeur,edition,url,langue,nbtome);
           
           livres.add(d);
       }

       return livres;
    }
              public   LinkedList<Magazine> getMagazineByEditeur(String Editeur) throws SQLException{
        int type=3;
        String query="select * from document where editeur like'"+Editeur+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Magazine> livres= new   LinkedList<Magazine> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
          Magazine d=new Magazine(isbn,titre,auteur,editeur,edition,url,period,mois,jour);
           
           livres.add(d);
       }

       return livres;
    }
       
       
     
      public LinkedList<Livre> getLivreByAnneeEdition(int Edition) throws SQLException{
          int type=1;
        String query="select * from document where annee like '"+Edition+"'and type ='"+type+"'  ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Livre> livres= new   LinkedList<Livre> ();
        
        while (rs.next()) {
           String isbn=rs.getString("isbn");
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String cat = rs.getString("categorie");
           String tome = rs.getString("tome");
           String titre = rs.getString("Titre");
           Livre l = new Livre(isbn,titre,auteur,editeur,edition,url,nbpages,cat,tome);
           livres.add(l);
       }

       return livres;
    }
           public   LinkedList<Dictionnaire> getDictionnaireByAnneeEdition(int annee) throws SQLException{
        int type=2;
        String query="select * from document where annee = '"+annee+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Dictionnaire> livres= new   LinkedList<Dictionnaire> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String langue = rs.getString("langue");
           int nbtome = rs.getInt("nbtome");
           String titre = rs.getString("Titre");
          Dictionnaire d=new Dictionnaire(isbn,titre,auteur,editeur,edition,url,langue,nbtome);
           
           livres.add(d);
       }

       return livres;
    }
              public   LinkedList<Magazine> getMagazineByAnneeEdition(int Annee) throws SQLException{
        int type=3;
        String query="select * from document where annee = '"+Annee+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Magazine> livres= new   LinkedList<Magazine> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
          Magazine d=new Magazine(isbn,titre,auteur,editeur,edition,url,period,mois,jour);
           
           livres.add(d);
       }

       return livres;
    }
       
           
            
            
     
        public  LinkedList<Livre> getLivreByAuteur(String Auteur) throws SQLException{
         
        String query="select * from document where Auteur like '"+Auteur+"' AND type='1'  ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Livre> livres= new   LinkedList<Livre> ();
        
        while (rs.next()) {
           String isbn=rs.getString("isbn");
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String auteurs[]={auteur};
           String url=rs.getString("url");
           String cat = rs.getString("categorie");
           String tome = rs.getString("tome");
           String titre = rs.getString("Titre");
           Livre l = new Livre(isbn,titre,auteur,editeur,edition,url,nbpages,cat,tome);
           livres.add(l);
       }

       return livres;
    }
              public   LinkedList<Dictionnaire> getDictionnaireByAuteur(String Auteur) throws SQLException{
        int type=2;
        String query="select * from document where auteur like'"+Auteur+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Dictionnaire> livres= new   LinkedList<Dictionnaire> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String langue = rs.getString("langue");
           int nbtome = rs.getInt("nbtome");
           String titre = rs.getString("Titre");
          Dictionnaire d=new Dictionnaire(isbn,titre,auteur,editeur,edition,url,langue,nbtome);
           
           livres.add(d);
       }

       return livres;
    }
         public LinkedList<Magazine> getMagazineByAuteur(String auteur1) throws SQLException{
        int type=3;
        String query="select * from document where auteur like'"+auteur1+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Magazine> livres= new   LinkedList<Magazine> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
          Magazine d=new Magazine(isbn,titre,auteur,editeur,edition,url,period,mois,jour);
           
           livres.add(d);
       }

       return livres;
    }
     
        
        
        
              public  LinkedList<Livre> getLivreByCat(String Type) throws SQLException{

        String query="select * from livre where categorie like '"+Type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Livre> livres= new   LinkedList<Livre> ();
        
        while (rs.next()) {
           String isbn=rs.getString("isbn");
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("edition");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String type = rs.getString("categorie");
           String tome = rs.getString("tome");
           String titre = rs.getString("Titre");
           Livre l = new Livre(isbn,titre,auteur,editeur,edition,url,nbpages,type,tome);
           livres.add(l);
       }

       return livres;
    }      
        

              
              
     
               public LinkedList<Livre> getLivreByNombrePage(int Nbpages) throws SQLException{

        String query="select * from document where NbPages = '"+Nbpages+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Livre> livres= new   LinkedList<Livre> ();
        
        while (rs.next()) {
           String isbn=rs.getString("isbn");
           String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("edition");
           int nbpages=rs.getInt("nbpages");
           String auteurs[]={auteur};
           String url=rs.getString("url");
           String type = rs.getString("categorie");
           String tome = rs.getString("tome");
           String titre = rs.getString("Titre");
           Livre l = new Livre(isbn,titre,auteur,editeur,edition,url,nbpages,type,tome);
           livres.add(l);
       }

       return livres;
    }            
                  public     LinkedList<Dictionnaire> getDictionnaireByLangue(String langage) throws SQLException{
        int type=2;
        String query="select * from document where langue like'"+langage+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Dictionnaire> livres= new   LinkedList<Dictionnaire> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String langue = rs.getString("langue");
           int nbtome = rs.getInt("nbtome");
           String titre = rs.getString("Titre");
          Dictionnaire d=new Dictionnaire(isbn,titre,auteur,editeur,edition,url,langue,nbtome);
           
           livres.add(d);
       }

       return livres;
    }
              
              public LinkedList<Magazine> getMagazineByPeriodicite(String periodicite) throws SQLException{
        int type=3;
        String query="select * from document where period like'"+periodicite+"' and type ='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Magazine> livres= new   LinkedList<Magazine> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
          Magazine d=new Magazine(isbn,titre,auteur,editeur,edition,url,period,mois,jour);
           
           livres.add(d);
       }

       return livres;
    } 
              
             public  LinkedList<Document> getAllDocuments() throws SQLException{
        int type=3;
        String query="select * from document ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Document> livres= new   LinkedList<Document> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
          Document d=new Document(isbn,titre,auteur,editeur,edition,url);
           
           livres.add(d);
       }

       return livres;
    }  
           
             
             
        public LinkedList<Document> getAllFavoris(String cin) throws SQLException{
        String query="select * from document,favoris where favoris.IsbnDocument=document.isbn AND favoris.cniClient='"+cin+"'";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Document> documents= new   LinkedList<Document> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           String url=rs.getString("url");
           String titre = rs.getString("Titre");
          Document d=new Document(isbn,titre,auteur,editeur,edition,url);
           
           documents.add(d);
       }

       return documents;
    }
             
             
             
             
             
             
             
             
             
             
             
             
         public LinkedList<Livre> getAllLivre() throws SQLException{
        int type=1;
        String query="select * from document where type ='"+type+"'";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Livre> livres= new   LinkedList<Livre> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String cat =rs.getString("categorie");
           String tome = rs.getString("tome");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
          Livre d=new Livre(isbn,titre,auteur,editeur,edition,url,nbpages,cat,tome);
           
           livres.add(d);
       }

       return livres;
    } 
             
    //       String ISBN,String titre, String auteurs,
      //      String editeur,int anneEd,String url,int nbPage,String cat,String tome  
           public  LinkedList<Dictionnaire> getAllDictionnaires() throws SQLException{
        int type=2;
        String query;
        query = "select * from document where type ='"+type+"'";
        ResultSet rs2=stmt.executeQuery(query);
        
        LinkedList<Dictionnaire> livres= new   LinkedList<Dictionnaire> ();
        
        while (rs2.next()) {
                  String isbn=rs2.getString("isbn");
              String editeur=rs2.getString("editeur");
           String auteur=rs2.getString("auteur");
           int edition=rs2.getInt("annee");
           int nbpages=rs2.getInt("nbpages");
           String url=rs2.getString("url");
           String cat =rs2.getString("categorie");
           String tome = rs2.getString("tome");
           String period = rs2.getString("Period");
           int mois = rs2.getInt("mois");
           int jour = rs2.getInt("jour");
           String titre = rs2.getString("Titre");
             String langue = rs2.getString("langue");
           int nbtome = rs2.getInt("nbtome");
          Dictionnaire d=new Dictionnaire(isbn,titre,auteur,editeur,edition,url,langue,nbtome);

           livres.add(d);
       }

       return livres;
    }  
        
        
        
        
        public  LinkedList<Magazine> getAllMagazines() throws SQLException{
        int type=3;
        String query="select * from document where type ='"+type+"'";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Magazine> livres= new   LinkedList<Magazine> ();
        
        while (rs.next()) {
                  String isbn=rs.getString("isbn");
              String editeur=rs.getString("editeur");
           String auteur=rs.getString("auteur");
           int edition=rs.getInt("annee");
           int nbpages=rs.getInt("nbpages");
           String url=rs.getString("url");
           String cat =rs.getString("categorie");
           String tome = rs.getString("tome");
           String period = rs.getString("Period");
           int mois = rs.getInt("mois");
           int jour = rs.getInt("jour");
           String titre = rs.getString("Titre");
           Magazine d=new Magazine(isbn,titre,auteur,editeur,edition,url,period,mois,jour);

           livres.add(d);
       }

       return livres;
    } 
               
             
             
             
             
     public boolean supprimer(String ISBN) throws SQLException{
        String query="delete from document where isbn ='"+ISBN+"' ";
        int rs=stmt.executeUpdate(query);
        return(rs!=0);
    }
    
              
              
    
}
