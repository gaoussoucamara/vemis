package com.vemis.model;

public class IdentificationModeleDeSimulation {
	private int numeroModele;
	private String nomModele;
	private String titrePublication;
	private String journalPublication;
	private String courteDescription;
	private String MaladieCiblee;
	
	
	public String getMaladieCiblee() {
		return MaladieCiblee;
	}
	public void setMaladieCiblee(String maladieCiblee) {
		MaladieCiblee = maladieCiblee;
	}
	public int getNumeroModele() {
		return numeroModele;
	}
	public void setNumeroModele(int numeroModele) {
		this.numeroModele = numeroModele;
	}
	public String getNomModele() {
		return nomModele;
	}
	public void setNomModele(String nomModele) {
		this.nomModele = nomModele;
	}
	public String getTitrePublication() {
		return titrePublication;
	}
	public void setTitrePublication(String titrePublication) {
		this.titrePublication = titrePublication;
	}
	public String getJournalPublication() {
		return journalPublication;
	}
	public void setJournalPublication(String journalPublication) {
		this.journalPublication = journalPublication;
	}
	public String getCourteDescription() {
		return courteDescription;
	}
	public void setCourteDescription(String courteDescription) {
		this.courteDescription = courteDescription;
	}
	
	
}
