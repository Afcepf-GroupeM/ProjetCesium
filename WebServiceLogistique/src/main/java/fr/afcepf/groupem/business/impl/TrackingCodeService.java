package fr.afcepf.groupem.business.impl;

import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.business.api.ITrackingCodeService;
import fr.afcepf.groupem.entities.Transporteur;

@Component
@Transactional
public class TrackingCodeService implements ITrackingCodeService {

	

	@Override
	public String generateTrackingCode(Transporteur transporteur, String country, String zipcode) {
		String trackingCodeGenerated = "";
		String prefix = transporteur.getTrackingPrefix();
		Random random = new Random();		
		String countryCode = country.substring(0, 2);
		String regionCode = zipcode.substring(0, 2);
		String randPart1 = String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10));
		char car1 = (char) (random.nextInt(90 - 65 +1) + 65);
		char car2 = (char) (random.nextInt(90 - 65 +1) + 65);
		char car3 = (char) (random.nextInt(90 - 65 +1) + 65);
		String randPart2 = Character.toString(car1) + Character.toString(car2) + Character.toString(car3) ;
		String randPart3 = String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10));
		trackingCodeGenerated = prefix + "-" + countryCode.toUpperCase() + regionCode + "-" + randPart1 + randPart2 + "-" + randPart3; 
		return trackingCodeGenerated;
	}


}
