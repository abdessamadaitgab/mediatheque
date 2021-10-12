
package Model;

import java.io.Serializable;
import java.util.Arrays;


public  class Document implements Serializable{
    
        static final long serialVersionUID = 42L;
        private int id;
	private final String ISBN;
	private final String titre,editeur;
        private String url;
	private String auteurs;
	private final int anneeEdition;
	static private int numId=0;
        
	/***********************CONSTRUCTORS********************************/
	public Document(String ISBN,String titre, String auteurs,
                String editeur,int anneEd,String url)
        {
                this.id=numId++;
		this.ISBN=ISBN;
		this.titre=titre;
		this.auteurs=auteurs;
		this.editeur=editeur;
		this.anneeEdition=anneEd;
		this.url=url;
        }
	
	public Document(Document d) {
		this.ISBN=d.getISBN();
		this.titre=new String(d.getTitre());
		this.auteurs=d.getAuteurs();//Clone()
		this.editeur=new String(d.getEditeur());
		this.anneeEdition=d.getAnneeEdition();
		this.url=new String(d.getUrl());
	}
        
        /******************************************************************/
	
        
        
        
        /***********************SETTERS AND GETTERS*************************/
	public String getISBN() {
		return this.ISBN;
	}
        public int getId(){
            return this.id;
        }
	
	public String getTitre() {
		return this.titre;
	}
	
	public String getEditeur() {
		return this.editeur;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public int getAnneeEdition() {
		return this.anneeEdition;
	}
	
	public String getAuteurs() {
		return this.auteurs;
	}

	
	public void setUrl(String url) {
		this.url=url;
	}
	
	public void setAuteurs(String auteurs) {
		this.auteurs=auteurs;
	}
        /******************************************************************/
	
        @Override
        public String toString(){
            return "[ISBN= "+
                    ISBN+", Titre= "+
                    titre+", Auteur="+
                    auteurs+", Editeur="+
                    editeur+",Annee d'edition="+anneeEdition+"]";
        }
	
        
}
