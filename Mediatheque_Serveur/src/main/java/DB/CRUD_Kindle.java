
package DB;
import java.util.*;

import Model.Kindle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CRUD_Kindle {
     Statement stmt;
    
    public void CRUD_Kindle(String username, String password) throws SQLException {
    
    Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/mediatheque?serverTimezone=UTC",
                         username,
                         password);
   
    stmt = con.createStatement();

}
    
    public LinkedList<Kindle> getKindles() throws SQLException{
         String query="select * from kindle ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Kindle> kindles= new   LinkedList<> ();
        
        while (rs.next()) {
           String modele=rs.getString("Modele");
           String mac=rs.getString("Mac");
          
           float pouces=rs.getFloat("Pouces");
           
          
           Kindle k = new Kindle(modele, mac, pouces);
           kindles.add(k);
       }

       return kindles;
    }
     public Kindle getKindleByID(int id) throws SQLException{

        String query="select * from kindle where id='"+id+"' ";
        ResultSet rs=stmt.executeQuery(query);
       
        if (rs.next()) {
            String modele=rs.getString("Modele");
           String mac=rs.getString("Mac");
          
           float pouces=rs.getFloat("Pouces");
           
          
           Kindle k = new Kindle(modele, mac, pouces);
           
           return k;
       }
        return null;

    }
     public Kindle getKindleByMac(String Mac) throws SQLException{

        String query="select * from kindle where mac='"+Mac+"' ";
        ResultSet rs=stmt.executeQuery(query);
       
        if (rs.next()) {
            String modele=rs.getString("Modele");
           String mac=rs.getString("Mac");
          
           float pouces=rs.getFloat("Pouces");
           
          
           Kindle k = new Kindle(modele, mac, pouces);
           
           return k;
       }
        return null;

    }
    
    //Kindle
     public boolean ajouterKindle(Kindle k) throws SQLException{
        String query="insert into kindle(modele,pouces,mac)  values ('"+k.getModele()+"','"+k.getPouces()+"','"+k.getMac()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
    }
    
    public boolean modifierModeleKindle(Kindle k) throws SQLException{
        
        String query="update livre set modele='"+k.getModele()+"' ";
        int rs=stmt.executeUpdate(query);
       
        
        return(rs!=0);


    }
     public boolean modifierMacKindle(Kindle k) throws SQLException{
        
        String query="update livre set mac='"+k.getMac()+"' ";
        int rs=stmt.executeUpdate(query);
       
        
        return(rs!=0);


    }
      public boolean modifierPucesKindle(Kindle k) throws SQLException{
        
        String query="update livre set pouces='"+k.getPouces()+"' ";
        int rs=stmt.executeUpdate(query);
       
        
        return(rs!=0);


    }

    
    public boolean supprKindle(int id) throws SQLException{
         
        String query="delete * from livre where id ='"+id+"' ";
        int rs=stmt.executeUpdate(query);
       
        
        return(rs!=0);
        
    }
        public LinkedList<Kindle> KindlesNonRendu() throws SQLException{
        
       String query="select * from kindle where dateretour = NULL";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Kindle> kindles= new   LinkedList<> ();
        
        while (rs.next()) {
           String modele=rs.getString("Modele");
           String mac=rs.getString("Mac");
          
           float pouces=rs.getFloat("Pouces");
           
          
           Kindle k = new Kindle(modele, mac, pouces);
           kindles.add(k);
       }

       return kindles;
}
        public Kindle KindleEnCours(String cin) throws SQLException{
                
       String query="select * from kindle,connexion where kindle.mac=connexion.mackindle AND connexion.cinclient = '"+cin+"'";
        ResultSet rs=stmt.executeQuery(query);
        if(rs.next()) {
            

           String modele=rs.getString("Modele");
           String mac=rs.getString("Mac");
          
           float pouces=rs.getFloat("Pouces");
           
          
           Kindle k = new Kindle(modele, mac, pouces);
           return k;
        }
        return null;
}
        public boolean kindlevole(String mac) throws SQLException{
            String query="select * from kindle where mac='"+mac+"' and vole ='"+1+"'";
        ResultSet rs=stmt.executeQuery(query);
        if(rs.next()){
            return true;
        }
          return false;  
        }
}
