package Model;
public class Kindle {
    String modele;
    String mac;
    float pouces;
    boolean emprunte;
    private static int idK=0;
    
    public Kindle(String modele, String mac, float pouces){
        this.modele= new String(modele);
        this.mac= new String(mac);
        this.pouces= pouces;
        emprunte=false;
    }
    
      public Kindle(Kindle kindel){
        this.modele= new String(kindel.getModele());
        this.mac= new String(kindel.getMac());
        this.pouces= kindel.getPouces();
        emprunte=false;
    }
    
    

    public String getModele() {
        return modele;
    }

    public String getMac() {
        return mac;
    }

    public float getPouces() {
        return pouces;
    }

    public boolean isEmprunte() {
        return emprunte;
    }

    public void setEmprunte(boolean emprunte) {
        this.emprunte = emprunte;
    }
    
    
    public String toString(){
        return  "Modele: "+ modele+ "Adresse MAC:"+  mac+"Pouces:"+pouces;
    }

}