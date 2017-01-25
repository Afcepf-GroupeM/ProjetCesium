package fr.afcepf.groupem.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.business.api.ITransporteurBus;
import fr.afcepf.groupem.dao.api.ITransporteurDao;
import fr.afcepf.groupem.entities.Transporteur;


@Component
@Transactional
public class TransporteurBus implements ITransporteurBus {
	
	
	@Autowired
	ITransporteurDao transporteurDao;

	@Override
	public Transporteur chooseTransporteurByDelaiMax(int delai) {
		Transporteur transporteurChoisi = null;
		List<Transporteur> listTransporteurs = transporteurDao.getAllTransporteurs();
		for (Transporteur transporteur : listTransporteurs) {
			if(transporteur.getDelaiLivraisonGaranti() <= delai){
				transporteurChoisi = transporteur;
				break;
			}
		}	
		return transporteurChoisi;
	}

}
