package com.vemis.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

import com.vemis.model.ModeleDeSimulation;
import com.vemis.utilitaires.Constantes;

public class ModeleDeSimulationDaoImpl implements ModeleDeSimulationDao {
	
	static final String inputFileName_vemis_models = Constantes.VEMIS_MODELS;
	static final String outputFileName_vemis_models = Constantes.VEMIS_MODELS_OUT;
	static final String enteteClasses = Constantes.VEMIS_MODELS_ENTETE;
	OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
	OntologieUtilitaire ou = new OntologieUtilitaire();  	
	
	public ModeleDeSimulationDaoImpl() {
		ou.readOntology(inputFileName_vemis_models, model );
	}
	
	public Map<String, List<String>> valeursDunElementDansunModele(String element){
		Map<String, List<String>> laMap = new HashMap<>();
		List<String> sousClassesDimension = ou.SubClassOf(model, enteteClasses, element);
		for (Iterator<String> i = sousClassesDimension.iterator(); i.hasNext();) {
			String elt = i.next();
			List<String> individusDimension = ou.getInstance(model, enteteClasses, elt);
			laMap.put(elt, individusDimension);
		}
		return laMap;
	}
	
	public Map<String, List<String>> valeursElementsDunModele(String activite, String acteur, String objet){
		Map<String, List<String>> laMap = new HashMap<>();
		List<String> individusActivite = ou.getInstance(model, enteteClasses, activite);
		List<String> individusActeur = ou.getInstance(model, enteteClasses, acteur);
		List<String> individusObjet = ou.getInstance(model, enteteClasses, objet);
		laMap.put(activite, individusActivite);
		laMap.put(acteur, individusActeur);
		laMap.put(objet, individusObjet);
		
		return laMap;
	}
		
	public List<String> individusDuneClasse(String classe){
		return ou.getInstance(model, enteteClasses, classe);
	}
	
	public List<String> sousClassesDuneClasse(String classe){
		return ou.SubClassOf(model, enteteClasses, classe);
	}
	
	public String classeDUnIndividu(String individu, String superClasse) {
		return ou.classeDUnIndividu(model, enteteClasses, individu, superClasse);
	}
	
	public void ajouterUnModeleDeSimulation(ModeleDeSimulation modele) {
		//********************* Ajout du modèle (son nom)****************
		Individual ModeleDeSimulation = ou.addIndividual(model, enteteClasses, modele.getIdentite().getNomModele(), "ModeleDeSimulation");
		
		//********************* Ajout de l'identification du modèle (son nom)****************
		Individual Description = ou.addIndividual(model, enteteClasses, modele.getIdentite().getCourteDescription(), "Description");
		Individual Journal = ou.addIndividual(model, enteteClasses, modele.getIdentite().getJournalPublication(), "JournalDePublication");
		Individual Titre = ou.addIndividual(model, enteteClasses, modele.getIdentite().getTitrePublication(), "TitreDePublication");
		Individual MaladieCible = ou.addIndividual(model, enteteClasses, modele.getIdentite().getMaladieCiblee(), "MaladieCible");
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "aPourDescription", Description);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "aPourJournalDePublication", Journal);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "aPourTitreDePublication", Titre);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "aPourMaladieCible", MaladieCible);
		
		//********************* Ajout des propriétés du modèle (son nom)****************
		Individual FonctionObjective = ou.addIndividual(model, enteteClasses, modele.getProprietes().getFonctionObjective(), "FonctionObjective");
		Individual NatureDeFonctionObjective = ou.addIndividual(model, enteteClasses, modele.getProprietes().getNatureFonctionObjective(), "NatureDeFonctionObjective");
		Individual ElementDeFonctionObjective = ou.addIndividual(model, enteteClasses, modele.getProprietes().getElementDeFonctionObjective(), "ElementDeFonctionObjective");
		Individual Dimension = ou.addIndividual(model, enteteClasses, modele.getProprietes().getDimensionModele(), "Dimension");
		Individual TypeDeModele = ou.addIndividual(model, enteteClasses, modele.getProprietes().getOutilModelisation(), "TypeDeModele");
		Individual LangageDImplementation = ou.addIndividual(model, enteteClasses, modele.getProprietes().getLangageImplementation(), "LangageDImplementation");
		Individual PlateformeDeSimulation = ou.addIndividual(model, enteteClasses, modele.getProprietes().getPlateformeSimulation(), "PlateformeDeSimulation");
		
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "aPourFonction", FonctionObjective);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "aPourNatureDeFonctionObjective", NatureDeFonctionObjective);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "aPourElementDeFonctionObjective", ElementDeFonctionObjective);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "aPourDimension", Dimension);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "estEnBase", TypeDeModele);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "estImplemente", LangageDImplementation);
		ou.addObjectPropertyAssertion(model, enteteClasses, ModeleDeSimulation, "estSimule", PlateformeDeSimulation);
		
		//********************* Ajout des auteurs du modèle (son nom)****************
		
		
		
		//************ DEBUT - Enregistrement de tout le modèle dans la base de connaissances ***********
		ou.saveOntology(outputFileName_vemis_models, model);
		//************ FIN - Enregistrement de tout le modèle dans la base de connaissances ***********
	}
}
