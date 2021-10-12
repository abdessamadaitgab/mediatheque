import Model.Etudiant;
import Model.Professeur;
import java.io.Serializable;
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
public class CRUD_Client implements Serializable{
    
        Statement stmt;

        public void CRUD_Client(String username, String password) throws SQLException {
    
    Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/mediatheque?serverTimezone=UTC",
                         username,
                         password);
   
    stmt = con.createStatement();

}
    
    public boolean Connexion(String login, String password) throws SQLException{
     String query="select * from client where login like'"+login+"'AND password='"+password+"'  ";

       ResultSet rs=stmt.executeQuery(query);
       
       return rs.next();
    }
        
        
    boolean Ajouter_Etudiant(Etudiant e) throws SQLException{
        String login = e.getLogin();
        String password = e.getPassword();
      String cin="";
            cin = e.getCni();
        String nom = e.getNom();
        String prenom = e.getPrenom();
        int age = e.getAge();
        String cne = e.getCNE();
        int type=1;
                
    String query ="INSERT INTO client (cne,cni,nom,prenom,login,password,type, age) Values ('" +cne+ "','" +cin+ "','" +nom+ "','" +prenom+ "','" +login+ "','" +password+ "','" +type+ "','" +age+ "') ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }
      
    boolean Ajouter_Professeur(Professeur e) throws SQLException{
        String login = e.getLogin();
        String password = e.getPassword();
      String cin="";
            cin = e.getCni();
        String nom = e.getNom();
        String prenom = e.getPrenom();
        int age = e.getAge();
        String cne = "";
        int type=0;
                
    String query ="INSERT INTO client (cne,cni,nom,prenom,login,password,type, age) Values('" +cne+ "','" +cin+"','"+nom+"','"+prenom+"','"+login+"','"+password+"','"+type+"','"+age+"') ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }
 
    
    boolean Modifier_Etudiant_Login(String cne, String login) throws SQLException{
         int type=1;
        String query="update client set login="+login+" where cne='"+cne+"'AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }
     boolean Modifier_Professeur_Login(String cin, String login) throws SQLException{
         int type=0;
        String query="update client set login="+login+"where cni='"+cin+"'AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }

    boolean Modifier_Etudiant_Password(String cne, String password) throws SQLException{
         int type=1;
        String query="update client set Password="+password+"where cne='"+cne+"'AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }
    boolean Modifier_Professeur_Password(String cin, String password) throws SQLException{
        int type=0;
        String query="update client set Password="+password+"where cni='"+cin+"'AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }
    
    
      /*  boolean Modifier_Etudiant_Cin(String cne, String cin) throws SQLException{
        
        String query="update etudiant set Cin="+cin+"where Cne="+cne+"'";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }*/
    

    
     boolean Modifier_Etudiant_Nom(String cne, String nom) throws SQLException{
         int type=1;
        String query="update client set nom= '"+nom+"' where cne='"+cne+"' AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    } 
     
     boolean Modifier_Professeur_Nom(String cin, String nom) throws SQLException{
        int type=0;
        String query="update client set nom='"+nom+"' where cni='"+cin+"'AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    } 
        
 
     
         boolean Modifier_Etudiant_Prenom(String cne, String prenom) throws SQLException{
         int type=1;
        String query="update client set prenom='" +prenom+ "' where cne='"+cne+"' AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    } 
          boolean Modifier_Professeur_Prenom(String cni, String prenom) throws SQLException{
        int type=0;
        String query="update client set prenom='"+prenom+"' where cin='"+cni+"'AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    } 
 
     
             boolean Modifier_Etudiant_Age(String cne, int age) throws SQLException{
         int type=1;
        String query="update client set age="+age+" where cne= '"+cne+"' AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }  
                 boolean Modifier_Professeur_Age(String cni, int age) throws SQLException{
        int type=0;
        String query="update client set age="+age+" where cni='"+cni+"'AND type='"+type+"' ";
        int nbUpdated = stmt.executeUpdate(query);  
        return nbUpdated!=0;
    }  
     
        
    
    
    boolean supprimer(String cin) throws SQLException{
        String query="delete from client where cni ='"+cin+"' ";
        int rs=stmt.executeUpdate(query);
        return(rs!=0);
    }

        
        
    
    
    LinkedList<Etudiant> getEtudiant_By_Login( String login) throws SQLException{
        int type=1;
          String query="select * from client where login like'"+login+"'AND type='"+type+"'  ";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Etudiant> Etudiants= new   LinkedList<Etudiant> ();

        while(rs.next()){
            int id=rs.getInt("id");
           String login1=rs.getString("login");
           String password=rs.getString("password");
          String cin=rs.getString("cni");
           String nom=rs.getString("nom");
           String prenom=rs.getString("prenom");
           String cne=rs.getString("Cne"); 
           int age = rs.getInt("Age");
           Etudiant e = new Etudiant(login1, password, nom, prenom,age, cne,cin);
           Etudiants.add(e);
       }
        return Etudiants;

        
  
    }
    
        
    Etudiant getEtudiant_By_Login_Password( String login, String pass) throws SQLException{
        int type=1;
          String query="select * from client where login like'"+login+"'AND password='"+pass+"' AND type='"+type+"'  ";
        ResultSet rs=stmt.executeQuery(query);
       if(rs.next()){
            int id=rs.getInt("id");
           String login1=rs.getString("login");
           String password=rs.getString("password");
          String cin=rs.getString("cni");
           String nom=rs.getString("nom");
           String prenom=rs.getString("prenom");
           String cne=rs.getString("Cne"); 
           int age = rs.getInt("Age");
           Etudiant e = new Etudiant(login1, password, nom, prenom,age, cne,cin);
       
        return e;}
       return null;

        
  
    }
    
    
    
        String getCin_byLogin_Password( String login, String pass) throws SQLException{
        int type=1;
          String query="select * from client where login like'"+login+"'AND password='"+pass+"' AND type='"+type+"'  ";
        ResultSet rs=stmt.executeQuery(query);
        if(rs.next()){
          return rs.getString("cni");
        }
        return "";
        }
    
    
    
    
    
    LinkedList<Professeur> getProfesseur_By_Login( String login) throws SQLException{
        int type=0;
          String query="select * from client where login like'"+login+"' AND type='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
         LinkedList<Professeur> Professeurs= new   LinkedList<Professeur> ();

        while(rs.next()){
           String login1=rs.getString("Login");
           String password1=rs.getString("Password");
           String cin1=rs.getString("Cni");
           String nom1=rs.getString("Nom");
           String prenom1=rs.getString("Prenom");
           int age = rs.getInt("Age");
           String cne1=rs.getString("Cne"); 
           Professeur p = new Professeur(login1, password1,nom1, prenom1,age ,cin1);
Professeurs.add(p);       }
        return Professeurs;
        
          
        
  
    }

    
    
    

        Etudiant getEtudiant_By_Cin( String cin) throws SQLException{
     int type=1;
        
          String query="select * from client where Cni like'"+cin+"'AND type='"+type+"'  ";
        ResultSet rs=stmt.executeQuery(query);

       if(rs.next()){
           String login1=rs.getString("Login");
           String password1=rs.getString("Password");
           String cin1=rs.getString("Cni");
           String nom1=rs.getString("Nom");
           String prenom1=rs.getString("Prenom");
           int age = rs.getInt("Age");
           String cne1=rs.getString("Cne"); 
           Etudiant p = new Etudiant(login1, password1,nom1, prenom1,age ,cin1,cne1);
       
        return p;
       }
       return null;
    }
        
    LinkedList<Professeur> getProfesseur_By_Cin( String cin) throws SQLException{
     int type=0;
        
          String query="select * from client where Cni like'"+cin+"'AND type='"+type+"'  ";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Professeur> Professeurs= new   LinkedList<Professeur> ();

        while(rs.next()){
           String login1=rs.getString("Login");
           String password1=rs.getString("Password");
           String cin1=rs.getString("Cni");
           String nom1=rs.getString("Nom");
           String prenom1=rs.getString("Prenom");
           int age = rs.getInt("Age");
           String cne1=rs.getString("Cne"); 
           Professeur p = new Professeur(login1, password1,nom1, prenom1,age ,cin1);
Professeurs.add(p);       }
        return Professeurs;
        
    }
    



    LinkedList<Etudiant>  getEtudiant_By_Nom( String nom) throws SQLException{
int type=1;
          String query="select * from client where Nom like'"+nom+"'AND type='"+type+"'  ";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Etudiant> Etudiants= new   LinkedList<Etudiant> ();

        while(rs.next()){
           String login1=rs.getString("Login");
           String password1=rs.getString("Password");
          String cin1=rs.getString("Cni");
           String nom1=rs.getString("Nom");
           String prenom1=rs.getString("Prenom");
           int age = rs.getInt("Age");
           String cne1=rs.getString("Cne"); 
           Etudiant e = new Etudiant(login1, password1,nom1, prenom1,age , cne1,cin1);
           Etudiants.add(e);
       }
        return Etudiants;
        
        
    }
        LinkedList<Professeur> getProfesseur_By_Nom( String nom) throws SQLException{
   
        int type=0;
          String query="select * from client where nom like'"+nom+"'AND type='"+type+"'  ";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Professeur> Professeurs= new   LinkedList<Professeur> ();

        while(rs.next()){
           String login1=rs.getString("Login");
           String password1=rs.getString("Password");
           String cin1=rs.getString("Cni");
           String nom1=rs.getString("Nom");
           String prenom1=rs.getString("Prenom");
           int age = rs.getInt("Age");
           String cne1=rs.getString("Cne"); 
           Professeur p = new Professeur(login1, password1,nom1, prenom1,age ,cin1);
Professeurs.add(p);       }
        return Professeurs;
        
    }
    



   LinkedList<Etudiant>  getEtudiant_By_Prenom( String prenom) throws SQLException{

  
        int type=1;
          String query="select * from client where Prenom like '"+prenom+"' AND type='"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Etudiant> Etudiants= new   LinkedList<Etudiant> ();
        while(rs.next()){
           String login1=rs.getString("login");
           String password1=rs.getString("password");
          String cin1=rs.getString("cni");
           String nom1=rs.getString("nom");
           String prenom1=rs.getString("prenom");
           int age = rs.getInt("age");
           String cne1=rs.getString("cne"); 
           Etudiant e = new Etudiant(login1, password1,nom1, prenom1,age , cne1,cin1);
           Etudiants.add(e);
       }
        return Etudiants;
        
        }
       LinkedList<Professeur> getProfesseur_By_Prenom( String prenom) throws SQLException{
   
        int type=0;
          String query="select * from client where Prenom like '"+prenom+"' AND type= '"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Professeur> Professeurs= new   LinkedList<Professeur> ();

        while(rs.next()){
           String login1=rs.getString("Login");
           String password1=rs.getString("Password");
           String cin1=rs.getString("Cni");
           String nom1=rs.getString("Nom");
           String prenom1=rs.getString("Prenom");
           int age = rs.getInt("Age");
           String cne1=rs.getString("Cne"); 
           Professeur p = new Professeur(login1, password1,nom1, prenom1,age ,cin1);
Professeurs.add(p);       }
        return Professeurs;
        
    }
    
        
    
        LinkedList<Etudiant>  getEtudiant_By_Cne( String cne) throws SQLException{

  
        int type=1;
          String query="select * from client where Cne like '"+cne+"' AND type= '"+type+"' ";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Etudiant> Etudiants= new   LinkedList<Etudiant> ();

        while(rs.next()){
           String login1=rs.getString("Login");
           String password1=rs.getString("Password");
          String cin1=rs.getString("Cni");
           String nom1=rs.getString("Nom");
           String prenom1=rs.getString("Prenom");
           int age = rs.getInt("Age");
           String cne1=rs.getString("Cne"); 
           Etudiant e = new Etudiant(login1, password1,nom1, prenom1,age , cne1,cin1);
           Etudiants.add(e);
       }
        return Etudiants;
        
        }
        LinkedList<Etudiant>  getAllEtudiant() throws SQLException{

   int type=1;
          String query="select * from client where type='"+type+"'";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Etudiant> Etudiants= new   LinkedList<Etudiant> ();

        while(rs.next()){
           String login1=rs.getString("login");
           String password1=rs.getString("password");
          String cin1=rs.getString("cni");
           String nom1=rs.getString("nom");
           String prenom1=rs.getString("prenom");
           int age = rs.getInt("age");
           String cne1=rs.getString("cne"); 
           Etudiant e = new Etudiant(login1, password1,nom1, prenom1,age , cne1,cin1);
           Etudiants.add(e);
       }
        return Etudiants;
        
        }
         LinkedList<Professeur> getAllProfesseur( ) throws SQLException{
   
        int type=0;
          String query="select * from client where type='"+type+"'";
        ResultSet rs=stmt.executeQuery(query);
               LinkedList<Professeur> Professeurs= new   LinkedList<Professeur> ();

        while(rs.next()){
           String login1=rs.getString("Login");
           String password1=rs.getString("Password");
           String cin1=rs.getString("Cni");
           String nom1=rs.getString("Nom");
           String prenom1=rs.getString("Prenom");
           int age = rs.getInt("Age");
           String cne1=rs.getString("Cne"); 
           Professeur p = new Professeur(login1, password1,nom1, prenom1,age ,cin1);
Professeurs.add(p);       }
        return Professeurs;
        
    }
        
    
    
    
}
