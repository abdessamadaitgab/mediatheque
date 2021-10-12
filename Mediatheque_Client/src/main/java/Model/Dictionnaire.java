
package Model;

public class Dictionnaire extends Document{
    
    private final String langue;
    private final int nbTome;
    static final long serialVersionUID = 42L;

   
    /***********************CONSTRUCTORS********************************/
    public Dictionnaire(String ISBN,String titre, String auteurs,
            String editeur,int anneEd,String url,String langue,int nbTome)
    {
        super(ISBN,titre,auteurs,editeur,anneEd,url);
        this.langue=langue;
        this.nbTome=nbTome;
    }
     /******************************************************************/
    
    
    /***********************SETTERS AND GETTERS*************************/
    public String getLangue() {
        return langue;
    }

    public int getNbTome() {
        return nbTome;
    }
    /******************************************************************/
    
    @Override
    public String toString(){
        return "C'est un Dictionnaire : "+super.toString()+
                "et Sa langue="+this.langue+", nombre de tomes : "+this.nbTome+" Tomes";
    }
}
