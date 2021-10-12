package Model;


public class Livre extends Document {
    private final int nbPage;
    private final String cat;
    private final String tome;
    static final long serialVersionUID = 42L;

    
    /***********************CONSTRUCTOR******************************/
    public Livre(String ISBN,String titre, String auteurs,
            String editeur,int anneEd,String url,int nbPage,String cat,String tome)
    {
        super(ISBN,titre,auteurs,editeur,anneEd,url);
        this.nbPage=nbPage;
        this.cat=cat;
        this.tome=tome;
    }
    /******************************************************************/
    
    
    /***********************GETTERS*************************/
     public int getNbPage() {
        return nbPage;
    }

    public String getCat() {
        return cat;
    }

    public String getTome() {
        return tome;
    }
    
    /******************************************************************/
    
    
@Override
    public String toString(){
        return "C'est un livre : "+super.toString();
    }


    public String consulter (){
        return "C'est un livre : "+super.toString()+
                "et Nombre de pages="+this.nbPage+
                ", categorie: "+this.cat+
                ", Tome: "+this.tome;
    }
    
}
