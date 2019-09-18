package com.vemis.model;

import java.util.List;

public class ModeleDeSimulation {
	private IdentificationModeleDeSimulation identite;
	private List<AuteurModeleDeSimulation> auteurs;
	private ProprietesModeleDeSimulation proprietes;
	private List<ParametreModeleSimulation> parametres;
	private List<HypotheseModeleDeSimulation> hypotheses;
	private List<EntiteModeleDeSimulation> entites;
	private List<String> acteurs;
	private List<String> objets;
	private List<String> activites;
	
	
	public List<EntiteModeleDeSimulation> getEntites() {
		return entites;
	}
	public void setEntites(List<EntiteModeleDeSimulation> entites) {
		this.entites = entites;
	}
	public IdentificationModeleDeSimulation getIdentite() {
		return identite;
	}
	public void setIdentite(IdentificationModeleDeSimulation identite) {
		this.identite = identite;
	}
	public List<AuteurModeleDeSimulation> getAuteurs() {
		return auteurs;
	}
	public void setAuteurs(List<AuteurModeleDeSimulation> articles) {
		this.auteurs = articles;
	}
	public ProprietesModeleDeSimulation getProprietes() {
		return proprietes;
	}
	public void setProprietes(ProprietesModeleDeSimulation proprietes) {
		this.proprietes = proprietes;
	}
	public List<ParametreModeleSimulation> getParametres() {
		return parametres;
	}
	public void setParametres(List<ParametreModeleSimulation> parametres) {
		this.parametres = parametres;
	}
	public List<HypotheseModeleDeSimulation> getHypotheses() {
		return hypotheses;
	}
	public void setHypotheses(List<HypotheseModeleDeSimulation> hypotheses) {
		this.hypotheses = hypotheses;
	}
	public List<String> getActeurs() {
		return acteurs;
	}
	public void setActeurs(List<String> acteurs) {
		this.acteurs = acteurs;
	}
	public List<String> getObjets() {
		return objets;
	}
	public void setObjets(List<String> objets) {
		this.objets = objets;
	}
	public List<String> getActivites() {
		return activites;
	}
	public void setActivites(List<String> activites) {
		this.activites = activites;
	}
	
}
