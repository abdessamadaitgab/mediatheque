package Model;

public class Magazine extends Document{
    
    private String periodicite;
    private final int mois;
    private final int jour;
        static final long serialVersionUID = 42L;

    
    /***********************CONSTRUCTORS********************************/
    public Magazine(String ISBN,String titre, String auteurs,
            String editeur,int anneEd,String url,String period,int mois,int jour)
    {
        super(ISBN,titre,auteurs,editeur,anneEd,url);
        
        this.periodicite=period;
        this.jour=jour;
        this.mois=mois;
    }

   
    /******************************************************************/
    
    
    
    /***********************SETTERS AND GETTERS*************************/
     public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public int getMois() {
        return mois;
    }

    public int getJour() {
        return jour;
    }
    /******************************************************************/
    
    @Override
    public String toString(){
        return "C'est un magazine : "+super.toString();
    }
    
            public String consulter (){
        return "C'est un dictionnaire : "+super.toString()+
                "et Sa périodicité="+this.periodicite+
                ", Date de sortie: "+this.jour+"/"+this.mois;
    }
    
    
    
}
