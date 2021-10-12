package DB;

import Model.Emprunt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;


public class CRUD_Emprunt {
   
    
    Statement stmt;
    
    public void CRUD_Emprunt(String username, String password) throws SQLException {
    
    Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/mediatheque?serverTimezone=UTC",
                         username,
                         password);
   
    stmt = con.createStatement();

}
    
   public boolean AjouterEmprunt(Emprunt e) throws SQLException{
         String query="insert into Emprunt (idClient,idKindle,dateEmprunt) values ('"+e.getIdClient()+"','"+e.getIdKindle()+"','"+e.getDate()+"')";
         int nbUpdated = stmt.executeUpdate(query);
         return nbUpdated!=0;
        
    }
    
    
     LinkedList<Emprunt> GetALLEmprunt() throws SQLException{

        String query="select * from emprunt ";
        ResultSet rs=stmt.executeQuery(query);
        
        LinkedList<Emprunt> Emprunts= new   LinkedList<Emprunt> ();
        
        while (rs.next()) {
           int id=rs.getInt("id");
            int idc=rs.getInt("IdClient");
             int idk=rs.getInt("IdKindle");
               Date dt=rs.getDate("dateEmprunt");
                Emprunt e=new Emprunt(idc,idk,dt);
               Emprunts.add(e);
       }

       return Emprunts;
    }
     
       public LinkedList<Emprunt> getEmpruntByClient(int idc) throws SQLException{

        String query="select * from Emprunt where IdClient ='"+idc+"' ";
        ResultSet rs=stmt.executeQuery(query);
         LinkedList<Emprunt> Emprunts= new   LinkedList<> ();

           while (rs.next()) {
           int id=rs.getInt("id");
             int idk=rs.getInt("IdKindle");
               Date dt=rs.getDate("dateEmprunt");
                Emprunt e=new Emprunt(idc,idk,dt);
               Emprunts.add(e);
       }

       return Emprunts;
       
       

    }
        public boolean modifierDateretour(Emprunt e , Date dt) throws SQLException{
            
        
        String query="update Emprunt set dateRetour='"+dt+"'where id='"+e.getId()+"'";
        int rs=stmt.executeUpdate(query);
       
        
        return(rs!=0);
    }
      
       
       
    
         public boolean supprEmprunt(int id) throws SQLException{
        
        
        String query="delete * from Emprunt where id ='"+id+"' ";
        int rs=stmt.executeUpdate(query);
       
        
        return(rs!=0);
    }

       
       

    
}