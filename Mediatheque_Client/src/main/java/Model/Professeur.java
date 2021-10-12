
package Model;


public class Professeur extends Client{

    private String CIN;

    public Professeur(String nom, String prenom, int age, String cin) {
        super(nom, prenom, age);
        this.CIN=cin;

    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
}
