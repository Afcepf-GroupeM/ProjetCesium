package fr.afcepf.groupem.dao.api;

import fr.afcepf.groupem.entities.Adresse;

public interface IAdresseDao {
	
	Adresse getAdresseByLivraisonId(int idLivraison);
	Adresse createAdresse(Adresse adresse);
	

}
