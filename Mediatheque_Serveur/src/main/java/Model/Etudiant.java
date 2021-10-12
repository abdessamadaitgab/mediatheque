
package Model;


public class Etudiant extends Client{
    private String CNE;


    public Etudiant(String nom, String prenom, int age, String cne,String cin) {
        super(genererUser(),genererPassword(),nom, prenom, age,cin);
        this.CNE=cne;
    }

    
        public Etudiant(String login , String password, String nom, String prenom, int age, String cne,String cni) {
        super(login,password,nom, prenom, age,cni);
        this.CNE=cne;
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
