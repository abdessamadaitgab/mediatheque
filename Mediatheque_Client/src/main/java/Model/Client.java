
package Model;


public class Client extends Utilisateur {
    static private int idClient=0;
    private String Nom;
    private String Prenom;
    private int Age;
    private int[] LesFavoris;
	
    public Client(String nom, String prenom, int age) {
        super(genererUser(),genererPassword());
        idClient++;
        this.Nom=new String(nom);
        this.Prenom=new String(prenom);
        this.setAge(age);
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
        return "Le Nom : "+this.getNom()+"\n Le prenom : "+this.getPrenom()+"L'Age : "+this.getAge();
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
}
