package fr.afcepf.groupem.dao.api;

import java.util.List;

import fr.afcepf.groupem.entities.Adresse;

public interface IAdresseDao {
	
	Adresse getAdresseByLivraisonId(int idLivraison);
	Adresse createAdresse(Adresse adresse);
	Adresse updateAdresse(Adresse adresse);
	List<Adresse> getAll();

}
