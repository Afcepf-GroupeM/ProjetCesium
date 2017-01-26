package fr.afcepf.groupem.dao.api;

import fr.afcepf.groupem.entities.Adresse;

public interface IAdresseDao {
	
	Adresse getAdresseByLivraisonId(int idLivraison);
	Adresse createAdresse(Adresse adresse);
	Adresse updateAdresse(Adresse adresse);
	boolean checkAdresse(String lastName, String firstName, int numero, String complement, String typeVoie,
	 String nomVoie, String city, String zipCode, String country);

}
