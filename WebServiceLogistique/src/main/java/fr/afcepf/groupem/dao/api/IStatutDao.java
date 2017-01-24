package fr.afcepf.groupem.dao.api;

import java.util.List;

import fr.afcepf.groupem.entities.Statut;
import fr.afcepf.groupem.entities.StatutLine;

public interface IStatutDao {
	
	Statut getStatutByLivraisonId(int idLivraison);
	List<StatutLine> getStatutLinesByStatutId(int idStatut);
	Statut createStatut(Statut statut);
	StatutLine createStatutLine(StatutLine statutLine);
	
	

}