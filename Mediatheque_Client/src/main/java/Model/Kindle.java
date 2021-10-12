
package Model;


public class Kindle {
    private final String Mac;
    private final String Modele;
    private static int idK=0;
    private int id;
    private boolean Emprunte=false;

    public Kindle(String mac,String modele) {
        this.Mac= new String(mac);
        this.Modele=new String(modele);
        this.setId(++idK);
    }

    public String getMac() {
        return Mac;
    }

    public String getModele() {
        return Modele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEmprunte() {
        return Emprunte;
    }

    public void setEmprunte(boolean emprunte) {
            Emprunte = emprunte;
    }
}
