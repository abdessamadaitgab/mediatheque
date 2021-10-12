
package Model;

import java.util.Date;


public class Emprunt {
    private static int idE=0;
    private final int Id;
    private final int IdClient;
    private final int IdKindle;
    private final Date dateEmprunt;
    private Date dateRetour;
    private int longitude;
    private int altitude;


    public Emprunt(int idc, int idk, Date dE ) {
        this.Id=++idE;
        this.IdClient=idc;
        this.IdKindle=idk;
        this.dateEmprunt=dE;
        this.dateRetour=null;
    }


    public int getIdClient() {
        return IdClient;
    }


    public int getIdKindle() {
        return IdKindle;
    }


    public int getId() {
        return Id;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

public Date getDate(){
        return this.dateEmprunt;
    }



        public void setDateRetour(Date dt) {
            this.dateRetour=dt;
}

}