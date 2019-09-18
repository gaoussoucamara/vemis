package com.vemis.dao;

import java.util.List;
import java.util.Map;

import com.vemis.model.ModeleDeSimulation;

public interface ModeleDeSimulationDao {
	Map<String, List<String>> valeursDunElementDansunModele(String element);
	Map<String, List<String>> valeursElementsDunModele(String activite, String acteur, String objet);
	List<String> individusDuneClasse(String classe);
	List<String> sousClassesDuneClasse(String classe);
	String classeDUnIndividu(String individu, String superClasse);
	void ajouterUnModeleDeSimulation(ModeleDeSimulation modele);
}
