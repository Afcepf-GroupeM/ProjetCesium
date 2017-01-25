package fr.afcepf.groupem.dao.api;

import java.util.List;

import fr.afcepf.groupem.entities.Livraison;
import fr.afcepf.groupem.entities.Statut;
import fr.afcepf.groupem.entities.StatutLine;

public interface IStatutDao {
	
	Statut getStatutByLivraisonId(int idLivraison);
	List<StatutLine> getStatutLinesByStatutId(int idStatut);
	Statut createStatut(Livraison livraison);
	StatutLine createStatutLine(StatutLine statutLine);
	Statut updateStatut(Statut statut);
	
	

}
