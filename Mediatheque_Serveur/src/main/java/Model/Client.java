
package Model;


public class Client extends Utilisateur{
    static private int idClient=0;
    private String Nom;
    private String Prenom;
    private int Age;
    private String cni;
    private int[] LesFavoris;
	
    public Client(String login, String password,String nom, String prenom, int age,String cni) {
      //  super(genererUser(),genererPassword());
        super(login,password);
        idClient++;
        this.Nom=new String(nom);
        this.Prenom=new String(prenom);
        this.setAge(age);
        this.cni=new String(cni);
    }

    public Client(Client c) {
        super(genererUser(),genererPassword());
        this.Nom=new String(c.getNom());
        this.Prenom=new String(c.getPrenom());
        this.Age=c.getAge();
    }
    
    public static String genererUser(){
        return "";
    }
    public static String genererPassword(){
        return "";
    }

    public String toString() {    
        return "Le Nom : "+this.getNom()+"\n Le prenom : "+this.getPrenom()+"\n L'Age : "+this.getAge();
    }


    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }
    public String getPrenom() {
        return Prenom;
    }
    public void setPrenom(String prenom) {
        Prenom = prenom;
    }


    public int getAge() {
        return Age;
    }


    public void setAge(int age) {
        Age = age;
    }
    public String getCni() {
        return cni;
    }


    public void setCni(String cni) {
        cni=cni;
    }
}
