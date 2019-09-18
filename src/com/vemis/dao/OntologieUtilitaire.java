package com.vemis.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Property;

public class OntologieUtilitaire {
	public void readOntology( String file, OntModel model )
	{
		InputStream in = null;
		try
		{
			in = new FileInputStream( file );
			model.read(in, "RDF/XML");
			in.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
	
	public void saveOntology(String file, OntModel model) {
		PrintStream p = null;
		try {
			p = new PrintStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.writeAll(p, "RDF/XML", null);
    	
    	p.close();
	}
	
	//Liste de toutes les sous classes d'une classe
    public List<String> SubClassOf(OntModel model, String enteteClasse, String classe) {
    	OntClass laClasse = model.getOntClass(enteteClasse + classe);
    	List<String> liste = new ArrayList<String>();
    	for (Iterator<OntClass> i = laClasse.listSubClasses(); i.hasNext(); ) {
    		  OntClass c = (OntClass) i.next();
    		  if ( c.getLocalName() != null && !c.getLocalName().equals( "Nothing" ) ) {
    			  liste.add(c.getLocalName().replace("_", " "));    			  
    		  }    		  
    		}
    	return liste;
    }
    
  //les instances d'une classe
    public List<String> getInstance(OntModel model, String enteteClasse, String classe){
    	OntClass laClasse = model.getOntClass(enteteClasse + classe);
    	List<String> liste = new ArrayList<String>();
        Iterator it = laClasse.listInstances();
        //print out the instances of the Expert class
        while(it.hasNext()){
            Individual oi = (Individual)it.next();
            liste.add(oi.getLocalName().replace("_", " "));    
        }
        return liste;
    }

	public String classeDUnIndividu(OntModel model, String enteteClasse, String individu, String superClasse){
		List<String> classesPossibles = SubClassOf(model, enteteClasse, superClasse);
		Iterator<String> it = classesPossibles.iterator();
		while(it.hasNext()){
			String c = (String) it.next();
			List<String> individusPossibles = getInstance(model, enteteClasse, c);
            if (individusPossibles.contains(individu))
            	return c ;            
        }
		return "";
	}
	
	//************* Ajout d'un individu d'une classe donnée****************
	public Individual addIndividual(OntModel model, String enteteClasse, String individu, String uneClasse) {
    	OntClass laClasse = model.getOntClass(enteteClasse + uneClasse);
    	Individual lindividu = model.createIndividual(enteteClasse + individu, laClasse);  	
    	return lindividu;
    }
	
	//***** Ajout d'une assertion du type: 004 (code utilisateur) aPourNom (nom de propriété) CISSE (nom d'un utilisateur
	public void addObjectPropertyAssertion(OntModel model, String enteteClasse, Individual individu1, String propriete, Individual individu2) {
    	//Individual lindividu1 = model.getIndividual(enteteClasse + individu1);
    	//Individual lindividu2 = model.getIndividual(enteteClasse + individu2);
  	  	Property laPropriete = model.getProperty(enteteClasse + propriete);
  	  individu1.addProperty(laPropriete, individu2);    	    	
    }
/*  
//Requête SPARQL
  public ResultSet executeQuery(OntModel model, String queryString) throws Exception {
		 Query query = QueryFactory.create(queryString) ;
		 ResultSet results;
		 
		 QueryExecution qexec = QueryExecutionFactory.create(query, model);
		 try {
			 results = qexec.execSelect(); 
			 return results;
		 } catch(Exception e) {
			 return null;
		 }
		 finally{
			 //qexec.close();
		 }
	}
  
//Liste de toutes les classes de l'ontologie
  public static void AllClassList(OntModel model) {
      for (Iterator<OntClass> i = model.listClasses(); i.hasNext();) {
          OntClass c = (OntClass) i.next();
          if ( c.getLocalName() != null && !c.getLocalName().equals( "Nothing" ) ) {
          	System.out.println(c);
          }            
       	}
  }*/

}
