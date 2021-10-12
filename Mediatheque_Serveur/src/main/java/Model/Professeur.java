
package Model;


public class Professeur extends Client{

    private String CIN;

    public Professeur(String nom, String prenom, int age, String cin) {
        super(genererUser(),genererPassword(),nom, prenom, age ,cin);
     
    }
    public Professeur(String login , String password, String nom, String prenom, int age,String cni) {
        super(login,password,nom, prenom, age,cni);
    }
    

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
}
