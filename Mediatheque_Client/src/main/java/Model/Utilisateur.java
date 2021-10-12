
package Model;


public abstract class Utilisateur {
    private String login,password;
    
    public Utilisateur(String login,String password){
        this.login=new String(login);
        this.password=new String(password);
    }
}
