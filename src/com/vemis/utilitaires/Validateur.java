package com.vemis.utilitaires;

import com.vemis.model.AuteurModeleDeSimulation;
import com.vemis.model.IdentificationModeleDeSimulation;
import com.vemis.model.ModeleDeSimulation;
import com.vemis.model.ProprietesModeleDeSimulation;

public class Validateur {
	public boolean estValide(IdentificationModeleDeSimulation identite) {
		if ((identite.getMaladieCiblee()==null || identite.getMaladieCiblee().isEmpty())) {
			return false;
		}
		return true;
	}
	
	public boolean estValide(ProprietesModeleDeSimulation proprietes) {
		if (
				(proprietes.getDimensionModele()==null || proprietes.getDimensionModele().isEmpty())
				||(proprietes.getElementDeFonctionObjective()==null || proprietes.getElementDeFonctionObjective().isEmpty())
				||(proprietes.getFonctionObjective()==null || proprietes.getFonctionObjective().isEmpty())
				||(proprietes.getLangageImplementation()==null || proprietes.getLangageImplementation().isEmpty())
				||(proprietes.getNatureFonctionObjective()==null || proprietes.getNatureFonctionObjective().isEmpty())
				||(proprietes.getOutilModelisation()==null || proprietes.getOutilModelisation().isEmpty())
				||(proprietes.getPlateformeSimulation()==null || proprietes.getPlateformeSimulation().isEmpty())) {
			return false;
		}
		return true;
	}
	
	public boolean estValide(ModeleDeSimulation modele) {
		if(
				((!estValide(modele.getIdentite())))
				|| (!estValide(modele.getProprietes()))
				|| (modele.getAuteurs()==null || modele.getAuteurs().isEmpty())
				|| (modele.getEntites()==null || modele.getEntites().isEmpty())
				|| (modele.getHypotheses()==null || modele.getHypotheses().isEmpty())
				|| (modele.getParametres()==null || modele.getParametres().isEmpty())
				) {
			return false;
		}
		return true;
	}
}
