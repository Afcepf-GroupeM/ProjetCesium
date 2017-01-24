package fr.afcepf.groupem.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.dao.api.ITransporteurDao;
import fr.afcepf.groupem.entities.Transporteur;

@Component
@Transactional
public class TransporteurDao implements ITransporteurDao {

	@Override
	public Transporteur getTransporteurByTrackingCode(String trackingCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
