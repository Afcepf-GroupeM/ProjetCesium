package fr.afcepf.groupem.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.afcepf.groupem.business.api.ITranspoteurBus;
import fr.afcepf.groupem.dao.api.ITransporteurDao;
import fr.afcepf.groupem.entities.Transporteur;

public class TransporteurBus implements ITranspoteurBus {
	
	
	@Autowired
	ITransporteurDao transporteurDao;

	@Override
	public Transporteur chooseTransporteurByDelaiMax(int delai) {
		List<Transporteur> listTransporteurs = transporteurDao.getAllTransporteurs();
		
		
		return null;
	}

}
