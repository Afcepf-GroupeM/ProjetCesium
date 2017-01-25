package fr.afcepf.groupem.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.dao.api.IAdresseDao;
import fr.afcepf.groupem.entities.Adresse;

@Component
@Transactional
public class AdresseDao implements IAdresseDao {

	@Override
	public Adresse getAdresseByLivraisonId(int idLivraison) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adresse createAdresse(Adresse adresse) {
		// TODO Auto-generated method stub
		return null;
	}

}
