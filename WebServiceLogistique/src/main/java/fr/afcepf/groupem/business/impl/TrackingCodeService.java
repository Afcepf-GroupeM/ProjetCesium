package fr.afcepf.groupem.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.business.api.ITrackingCodeService;
import fr.afcepf.groupem.entities.Transporteur;

@Component
@Transactional
public class TrackingCodeService implements ITrackingCodeService {
	
	
	@Autowired
	

	@Override
	public String generateTrackingCode(Transporteur transporteur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transporteur getTransporteurByTrackingCode(String trackingCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
