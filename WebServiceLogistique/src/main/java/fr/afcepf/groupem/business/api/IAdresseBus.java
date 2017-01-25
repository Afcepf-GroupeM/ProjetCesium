package fr.afcepf.groupem.business.api;

import fr.afcepf.groupem.entities.Adresse;

public interface IAdresseBus {
	
	Adresse getAdressByLivraisonId(int idLivraison);
	Adresse createAdresse(Adresse adresse);

}
