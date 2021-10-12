
package Model;


public class Etudiant extends Client{
    private String CNE;

    public Etudiant(String nom, String prenom, int age, String cne) {
        super(nom, prenom, age);
        this.CNE=new String(cne);
    }

    public String toString() {
        return "C'est un Etudiant : "+super.toString()+"\n CNE : "+this.getCNE();
    }


    public String getCNE() {
            return CNE;
    }

    public void setCNE(String CNE) {
            this.CNE = CNE;
    }
}
