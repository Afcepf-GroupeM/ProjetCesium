package fr.afcepf.groupem.business.api;


import java.util.List;

import fr.afcepf.groupem.entities.Livraison;
import fr.afcepf.groupem.entities.Statut;
import fr.afcepf.groupem.entities.StatutLine;

public interface IStatutBus {
	
	
	Statut getStatutByLivraisonId(int idLivraison);
	List<StatutLine> getStatutLinesByStatutId(int idStatut);
	Statut createNewStatut(Livraison livraison);
	Statut addStatutLine(Statut statut, StatutLine statutLine);
	
	

}
