package com.vemis.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.vemis.dao.ModeleDeSimulationDao;
import com.vemis.dao.ModeleDeSimulationDaoImpl;
import com.vemis.dao.OntologieUtilitaire;
import com.vemis.model.AuteurModeleDeSimulation;
import com.vemis.model.EntiteModeleDeSimulation;
import com.vemis.model.HypotheseModeleDeSimulation;
import com.vemis.model.IdentificationModeleDeSimulation;
import com.vemis.model.Login;
import com.vemis.model.ModeleDeSimulation;
import com.vemis.model.ParametreModeleSimulation;
import com.vemis.model.ProprietesModeleDeSimulation;
import com.vemis.utilitaires.Suppression;
import com.vemis.utilitaires.Validateur;
import com.vemis.utilitaires.ValidationResponse;

@Controller
@SessionAttributes("modeleInSession")
public class ParametrageModeles {
	ModeleDeSimulation modele = new ModeleDeSimulation();
	ModeleDeSimulationDao msd = new ModeleDeSimulationDaoImpl();	
	
	String jump;
	String messageToAjouterModele = "";
	
	@RequestMapping(value = "/ajouterModele", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("ajouterModele");
	    modele.setIdentite(new IdentificationModeleDeSimulation());
	    modele.setProprietes(new ProprietesModeleDeSimulation());	
	    modele.setAuteurs(new ArrayList<AuteurModeleDeSimulation>());
	    modele.setHypotheses(new ArrayList<HypotheseModeleDeSimulation>());
	    modele.setParametres(new ArrayList<ParametreModeleSimulation>());
	    modele.setEntites(new ArrayList<EntiteModeleDeSimulation>());
	    	    
	    mav.addObject("identiteModele", modele.getIdentite());
	    mav.addObject("proprietesModele", modele.getProprietes());
	    mav.addObject("auteursModele", modele.getAuteurs());//mettre en session les auteurs
	    mav.addObject("hypothesesModele", modele.getHypotheses());
	    mav.addObject("parametresModele", modele.getParametres());
	    mav.addObject("entitesModele", modele.getEntites());
	    mav.addObject("supprimerAuteurModele", new Suppression());
	    mav.addObject("supprimerHypotheseModele", new Suppression());
	    mav.addObject("supprimerParametreModele", new Suppression());
	    mav.addObject("supprimerEntiteModele", new Suppression());
	    mav.addObject("modeleInSession", modele);
	    
	    mav.addObject("unAuteurModele", new AuteurModeleDeSimulation());//un auteur pour le formulaire du modal
	    mav.addObject("uneHypotheseModele", new HypotheseModeleDeSimulation());
	    mav.addObject("unParametreModele", new ParametreModeleSimulation());
	    mav.addObject("uneEntiteModele", new EntiteModeleDeSimulation());
	    mav.addObject("dimensions", msd.valeursDunElementDansunModele("Dimension"));
	    mav.addObject("fonctionsObjectives", msd.valeursDunElementDansunModele("FonctionObjective"));
	    mav.addObject("naturesFonctionsObjectives", msd.individusDuneClasse("NatureDeFonctionObjective"));
	    mav.addObject("naturesDHypotheses", msd.individusDuneClasse("NatureDHypothese"));
	    mav.addObject("elementsDeQuantification", msd.individusDuneClasse("TypeQuantification"));
	    mav.addObject("typesDeParametres", msd.sousClassesDuneClasse("Parametre"));
	    mav.addObject("elementsDeModele", msd.valeursElementsDunModele("Activite", "Acteur", "Objet"));
	    mav.addObject("typeDeModele", msd.valeursDunElementDansunModele("TypeDeModele"));
	    mav.addObject("langagesDImplementation", msd.individusDuneClasse("LangageDImplementation"));
	    mav.addObject("plateformesDeSimulation", msd.individusDuneClasse("PlateformeDeSimulation"));
	    mav.addObject("maladiesCibles", msd.individusDuneClasse("MaladieCible"));
	    /*jump = "identification";
	    mav.addObject("jump", jump);*/
	    
	    return mav;
	  }
	
	@RequestMapping(value = "/ajouterIdentiteModeleProcess", method = RequestMethod.POST)
	  public String ajouterIdentiteModeleProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("identiteModele") IdentificationModeleDeSimulation identiteModele,
	  @ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession) {

		//Ajout code d'enregistrement dans l'ontologie ***
		
		//***fin ajout code d'enregistrement dans l'ontolgie
		modeleInSession.setIdentite(identiteModele);
		jump = "identification";
		return "redirect:ajouterModeleProcess";
	  }
	
	@RequestMapping(value = "/ajouterProprietesModeleProcess", method = RequestMethod.POST)
	  public String ajouterProprietesModeleProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("proprietesModele") ProprietesModeleDeSimulation proprietes,
	  @ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession) {

		//Ajout code d'enregistrement dans l'ontologie ***
		
		//***fin ajout code d'enregistrement dans l'ontolgie
		modeleInSession.setProprietes(proprietes);
		jump = "proprietes";
		return "redirect:ajouterModeleProcess";
	  }
	
	@PostMapping(value="/AjouterAuteurProcess")
	public String  AjouterAuteurProcess(Model model,
			@ModelAttribute(value="unAuteurModele") @Validated AuteurModeleDeSimulation unAuteurModele,
			BindingResult result, HttpServletRequest request,
			@ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession){

		if (modeleInSession.getAuteurs().size()!=0) {
			AuteurModeleDeSimulation last = modeleInSession.getAuteurs().get(modeleInSession.getAuteurs().size()-1);
			unAuteurModele.setNumero(last.getNumero()+1);
		} else {
			unAuteurModele.setNumero(1);
		}
        //unAuteurModele.setNumero(modeleInSession.getAuteurs().size()+1);
        modeleInSession.getAuteurs().add(unAuteurModele);
        jump = "auteurs";
        return "redirect:ajouterModeleProcess";
    }
	
	@RequestMapping(value = "/supprimerAuteur", method = RequestMethod.POST)
	  public String supprimerAuteur(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("supprimerAuteurModele") Suppression supprimerAuteurModele,
			  @ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession) {
			
			int trouve = 0;
			int i = 0;
			while(trouve == 0) {
				if (modeleInSession.getAuteurs().get(i).getNumero() == supprimerAuteurModele.getNumeroElement()) {
					trouve = 1;
				}
				i++;
			}
			modeleInSession.getAuteurs().remove(modeleInSession.getAuteurs().get(i-1));
			jump = "auteurs";
		return "redirect:ajouterModeleProcess";
	  }		
	
	@PostMapping(value="/AjouterHypotheseProcess")
	public String  AjouterHypotheseProcess(Model model,
			@ModelAttribute(value="uneHypotheseModele") @Validated HypotheseModeleDeSimulation uneHypotheseModele,
			BindingResult result, HttpServletRequest request,
			@ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession){

		if (modeleInSession.getHypotheses().size()!=0) {
			HypotheseModeleDeSimulation last = modeleInSession.getHypotheses().get(modeleInSession.getHypotheses().size()-1);
			uneHypotheseModele.setNumero(last.getNumero()+1);
		} else {
			uneHypotheseModele.setNumero(1);
		}
		//uneHypotheseModele.setNumero(modeleInSession.getHypotheses().size()+1);
        modeleInSession.getHypotheses().add(uneHypotheseModele);
        jump = "hypotheses";
        return "redirect:ajouterModeleProcess";
    }
	
	@RequestMapping(value = "/supprimerHypothese", method = RequestMethod.POST)
	  public String supprimerHypothese(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("supprimerHypotheseModele") Suppression supprimerHypotheseModele,
			  @ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession) {
			
			int trouve = 0;
			int i = 0;
			while(trouve == 0) {
				if (modeleInSession.getHypotheses().get(i).getNumero() == supprimerHypotheseModele.getNumeroHypothese()) {
					trouve = 1;
				}
				i++;
			}
			modeleInSession.getHypotheses().remove(modeleInSession.getHypotheses().get(i-1));
			jump = "hypotheses";
		return "redirect:ajouterModeleProcess";
	  }		
	
	@PostMapping(value="/AjouterParametreProcess")
	public String  AjouterParametreProcess(Model model,
			@ModelAttribute(value="unParametreModele") @Validated ParametreModeleSimulation unParametreModele,
			BindingResult result, HttpServletRequest request,
			@ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession){

		if (modeleInSession.getParametres().size()!=0) {
			ParametreModeleSimulation last = modeleInSession.getParametres().get(modeleInSession.getParametres().size()-1);
			unParametreModele.setNumeroParametre(last.getNumeroParametre()+1);
		} else {
			unParametreModele.setNumeroParametre(1);
		}
		//uneHypotheseModele.setNumero(modeleInSession.getHypotheses().size()+1);
        modeleInSession.getParametres().add(unParametreModele);
        jump = "parametres";
        return "redirect:ajouterModeleProcess";
    }
	
	@RequestMapping(value = "/supprimerParametre", method = RequestMethod.POST)
	  public String supprimerParametre(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("supprimerParametreModele") Suppression supprimerParametreModele,
			  @ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession) {
			
			int trouve = 0;
			int i = 0;
			while(trouve == 0) {
				if (modeleInSession.getParametres().get(i).getNumeroParametre() == supprimerParametreModele.getNumeroParametre()) {
					trouve = 1;
				}
				i++;
			}
			modeleInSession.getParametres().remove(modeleInSession.getParametres().get(i-1));
			jump = "parametres";
		return "redirect:ajouterModeleProcess";
	  }
	
	@PostMapping(value="/AjouterEntiteProcess")
	public String  AjouterEntiteProcess(Model model,
			@ModelAttribute(value="unParametreModele") @Validated EntiteModeleDeSimulation uneEntiteModele,
			BindingResult result, HttpServletRequest request,
			@ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession){

		if (modeleInSession.getEntites().size()!=0) {
			EntiteModeleDeSimulation last = modeleInSession.getEntites().get(modeleInSession.getEntites().size()-1);
			uneEntiteModele.setNumeroEntite(last.getNumeroEntite()+1);
		} else {
			uneEntiteModele.setNumeroEntite(1);
		}
		uneEntiteModele.setTypeEntite(msd.classeDUnIndividu(uneEntiteModele.getNomEntite(), "Entite"));
        modeleInSession.getEntites().add(uneEntiteModele);
        jump = "entites";
        return "redirect:ajouterModeleProcess";
    }
	
	@RequestMapping(value = "/supprimerEntite", method = RequestMethod.POST)
	  public String supprimerEntite(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("supprimerEntiteModele") Suppression supprimerEntiteModele,
			  @ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession) {
			
			int trouve = 0;
			int i = 0;
			while(trouve == 0) {
				if (modeleInSession.getEntites().get(i).getNumeroEntite() == supprimerEntiteModele.getNumeroEntite()) {
					trouve = 1;
				}
				i++;
			}
			modeleInSession.getEntites().remove(modeleInSession.getEntites().get(i-1));
			jump = "entites";
		return "redirect:ajouterModeleProcess";
	  }
	
	//Action à la quelle sont redirigées toutes les autres
	@RequestMapping(value = "/ajouterModeleProcess", method = RequestMethod.GET)
	  public ModelAndView ajouterModeleProcess(HttpServletRequest request, HttpServletResponse response,			  
			  @ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession) {
		
		ModelAndView mav = new ModelAndView("ajouterModele");		
		
		mav.addObject("proprietesModele", modeleInSession.getProprietes());
		mav.addObject("identiteModele", modeleInSession.getIdentite());
		mav.addObject("auteursModele", modeleInSession.getAuteurs());
		mav.addObject("hypothesesModele", modeleInSession.getHypotheses());
		mav.addObject("parametresModele", modele.getParametres());
		mav.addObject("entitesModele", modele.getEntites());
		mav.addObject("modeleInSession", modeleInSession);
	    mav.addObject("supprimerAuteurModele", new Suppression());
	    mav.addObject("supprimerHypotheseModele", new Suppression());
	    mav.addObject("supprimerParametreModele", new Suppression());
	    mav.addObject("supprimerEntiteModele", new Suppression());
		
        mav.addObject("unAuteurModele", new AuteurModeleDeSimulation());//un auteur pour le formulaire du modal
	    mav.addObject("uneHypotheseModele", new HypotheseModeleDeSimulation());
	    mav.addObject("unParametreModele", new ParametreModeleSimulation());
	    mav.addObject("uneEntiteModele", new EntiteModeleDeSimulation());
	    mav.addObject("dimensions", msd.valeursDunElementDansunModele("Dimension"));
	    mav.addObject("fonctionsObjectives", msd.valeursDunElementDansunModele("FonctionObjective"));
	    mav.addObject("naturesFonctionsObjectives", msd.individusDuneClasse("NatureDeFonctionObjective"));
	    mav.addObject("naturesDHypotheses", msd.individusDuneClasse("NatureDHypothese"));
	    mav.addObject("elementsDeQuantification", msd.individusDuneClasse("TypeQuantification"));
	    mav.addObject("typesDeParametres", msd.sousClassesDuneClasse("Parametre"));
	    mav.addObject("elementsDeModele", msd.valeursElementsDunModele("Activite", "Acteur", "Objet"));
	    mav.addObject("typeDeModele", msd.valeursDunElementDansunModele("TypeDeModele"));
	    mav.addObject("langagesDImplementation", msd.individusDuneClasse("LangageDImplementation"));
	    mav.addObject("plateformesDeSimulation", msd.individusDuneClasse("PlateformeDeSimulation"));
	    mav.addObject("maladiesCibles", msd.individusDuneClasse("MaladieCible"));
	    mav.addObject("jump", jump);
	    mav.addObject("messageToAjouterModele", messageToAjouterModele);

		return mav;
	}
	
	@RequestMapping(value = "/enregistrerModele", method = RequestMethod.POST)
	  public String enregistrerModele(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("modeleInSession") ModeleDeSimulation modeleInSession) {
		Validateur validateur = new Validateur();
			if (!validateur.estValide(modeleInSession)) {
				messageToAjouterModele = "Le modèle n'est pas encore valide";
			} else {
				messageToAjouterModele = "Le modèle est valide";
				msd.ajouterUnModeleDeSimulation(modeleInSession);
			}
			//messageToAjouterModele = "Le modèle n'est pas encore valide";
			jump = "final";
		return "redirect:ajouterModeleProcess";
	  }

}
